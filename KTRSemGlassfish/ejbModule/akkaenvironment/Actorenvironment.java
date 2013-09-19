package akkaenvironment;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

import javax.ejb.ConcurrencyManagement;
import javax.ejb.ConcurrencyManagementType;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;
import akkaenvironment.actors.TestActor;
import akkaenvironment.wrapper.ActorRefTimeWrapper;
import akkaenvironment.wrapper.JobTimeWrapper;
import akkaenvironment.wrapper.PropsPreAvailableWrapper;

/**
 * Singleton Session Bean Implementierung
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton(name = "ejb/Actorenvironment")
@LocalBean
public class Actorenvironment {

	/**
	 * Ausführungssystem der Aktoren
	 */
	private ActorSystem actorsys;

	/**
	 * Enthält alle aktiven Aktorreferenzen, Shared Memory mit
	 * AsyncMailboxActor.
	 */
	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;

	/**
	 * Enthält alle fertigen, asynchronen Antworten, Shared Memory mit
	 * AsyncMailboxActor.
	 */
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;

	/**
	 * Enthält alle explizit vorgehaltenen Props-Instanzen für die
	 * Aktorerzeugung
	 */
	private ConcurrentHashMap<String, PropsPreAvailableWrapper> actorPreTable;

	/**
	 * Referenz auf den Aktor für die Verwaltung von asynchronen Nachrichten
	 */
	private ActorRef asyncActor;

	/**
	 * Vorhaltezeit in Millisekunden für Aktorreferenzen und asynchrone
	 * Nachrichten. Wird an asyncActor beim initialen Programmstart mit
	 * AsyncMailboxActorIniMsg übergeben und in ihm nochmals gehalten.
	 */
	private long storageTime = 600000;

	/**
	 * Constructor für die Instanziierung durch die Java EE-Laufzeitumgebung.
	 * Legt alle Tabellen an und initialisiert den AsyncMailboxActor.
	 */
	public Actorenvironment() {

		actorsys = ActorSystem.create();
		actorRefTable = new ConcurrentHashMap<>();
		jobsTable = new ConcurrentHashMap<>();
		Props async = new Props(AsyncMailboxActor.class);
		asyncActor = actorsys.actorOf(async);
		asyncActor.tell(new AsyncMailboxActorIniMsg(jobsTable, actorRefTable,
				storageTime), null);
		actorPreTable = new ConcurrentHashMap<>();
		generateActorPreTable();
	}

	/**
	 * Erstellt die Inhalte von actorPreTable. Muss aktuell noch händisch
	 * angepasst werden, sollten weitere Aktoren hinzugefügt werden müssen.
	 */
	private void generateActorPreTable() {
		actorPreTable.put(TestActor.class.getName(),
				new PropsPreAvailableWrapper(TestActor.class.getName(),
						"Testactor für Echotest mit String", new Props(
								TestActor.class)));
	}

	/**
	 * Erzeugt aus der übergebenen Props-Instanz einen Aktor und gibt dessen
	 * Identifikationsstring zurück.
	 * 
	 * @param props
	 *            Props-Instanz aus der Class-Instanz des Aktors
	 * @return Aktoridentifikationsstring
	 */
	public String generateActorFromProps(Props props) {
		cleanup();
		ActorRef actor = actorsys.actorOf(props);
		ActorRefTimeWrapper tmp = new ActorRefTimeWrapper(actor,
				(System.currentTimeMillis() + storageTime));
		actorRefTable.put(actor.toString(), tmp);
		return actor.toString();
	}

	/**
	 * Sendet eine Nachricht mit synchroner Antwort an einen Aktor. Läuft die
	 * Wartezeit ab, wird eine Exception geworfen.
	 * 
	 * @param actorid
	 *            Identifikationsstring des Zielaktors.
	 * @param msg
	 *            Instanz der zu versenden Nachricht, muss im Aktor gecastet
	 *            werden.
	 * @param waittime
	 *            Maximale Wartezeit auf die Antwort.
	 * @return Antwortobjekt
	 * @throws Exception
	 *             Wird geworfen bei Ablauf der Wartezeit oder wenn der
	 *             Zielaktor nicht existiert.
	 * 
	 * 
	 */
	public Object sendMessage(String actorid, Object msg, int waittime)
			throws Exception {
		ActorRefTimeWrapper tmp = actorRefTable.get(actorid);
		if (tmp == null) {
			throw new Exception("Actor not available.");
		} else {
			tmp.setTimeout(System.currentTimeMillis() + storageTime);
			ActorRef actor = tmp.getActorref();
			Timeout timeout = new Timeout(Duration.create(waittime, "seconds"));
			Future<Object> future = Patterns.ask(actor, msg, timeout);
			Object result;
			result = Await.result(future, timeout.duration());
			return result;
		}
	}

	/**
	 * Leitet einen asynchronen Auftrag weiter und gibt einen
	 * Jobidentifikationsstring zurück, für den Abruf des Ergebnisses über
	 * getAsyncJobResult(String).
	 * 
	 * @param actorId
	 *            Identifikationsstring des Zielaktors.
	 * @param msg
	 *            Instanz der zu versenden Nachricht, muss im Aktor gecastet
	 *            werden.
	 * @return Jobidentifikationsstring
	 * @throws Exception
	 *             Wird geworfen wenn der Zielaktor nicht existiert.
	 */
	public String dispatchAsyncJob(String actorId, Object msg) throws Exception {
		AsyncMailboxActorJobMsg jobMsg = new AsyncMailboxActorJobMsg(actorId,
				msg);
		Timeout timeout = new Timeout(Duration.create(3, "seconds"));
		Future<Object> future = Patterns.ask(asyncActor, jobMsg, timeout);
		jobMsg = (AsyncMailboxActorJobMsg) Await.result(future,
				timeout.duration());
		if (jobMsg.getActorId() == null) {
			throw new Exception("Actor not available.");
		}
		return jobMsg.getActorId();
	}

	/**
	 * Dient dem asynchronen Ergebnisabruf mit einem Jobidentifikationsstring.
	 * 
	 * @param jobId
	 *            Jobidentifikationsstring
	 * @return Antwortobjekt
	 * @throws Exception
	 *             Wird geworfen wenn keine Antwort für den
	 *             Jobidentifikationsstring vorhanden ist.
	 */
	public Object getAsyncJobResult(String jobId) {
		JobTimeWrapper tmp = jobsTable.remove(jobId);
		if (tmp != null) {
			return tmp.getResultMsg();
		} else {
			return null;
		}
	}

	/**
	 * Löscht alle Aktorreferenzen und Ergebnisnachrichten, deren Vorhaltezeit
	 * abgelaufen ist.
	 */
	public void cleanup() {
		long currentTime = System.currentTimeMillis();
		Collection<ActorRefTimeWrapper> actorcol = actorRefTable.values();
		Collection<JobTimeWrapper> jobcol = jobsTable.values();
		for (ActorRefTimeWrapper wrap : actorcol) {
			if (wrap.getTimeout() < currentTime) {
				wrap.getActorref().tell(akka.actor.PoisonPill.getInstance(),
						null);
				actorcol.remove(wrap);
			}
		}
		for (JobTimeWrapper wrap : jobcol) {
			if (wrap.getTimeout() < currentTime) {
				jobcol.remove(wrap);
			}
		}
	}

	/**
	 * Gibt die actorPreTable zurück.
	 * 
	 * @return actorPreTable
	 */
	public ConcurrentHashMap<String, PropsPreAvailableWrapper> getActorPreTable() {
		return actorPreTable;
	}

	/**
	 * Erzeugt einen Aktor aus den in actorPreTable vorhaltenen Props-Instanzen
	 * 
	 * @param propsid
	 *            Vollqualifizierender Name der Aktorklasse, erhältlich über
	 *            getActorPreTable()
	 * @return Aktoridentifikationsstring
	 */
	public String generateActorFromPreProps(String propsid) {
		cleanup();
		ActorRef actor = actorsys
				.actorOf(actorPreTable.get(propsid).getProps());
		actorRefTable.put(actor.toString(), new ActorRefTimeWrapper(actor,
				System.currentTimeMillis() + storageTime));
		return actor.toString();
	}

}
