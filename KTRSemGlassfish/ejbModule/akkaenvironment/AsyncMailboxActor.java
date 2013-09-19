package akkaenvironment;

import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Logger;

import akka.actor.UntypedActor;
import akkaenvironment.wrapper.ActorRefTimeWrapper;
import akkaenvironment.wrapper.JobTimeWrapper;

/**
 * Aktor für die Verwaltung von asynchronen Nachrichten.
 */
public class AsyncMailboxActor extends UntypedActor {

	/**
	 * Logger
	 */
	Logger logger = Logger.getLogger(AsyncMailboxActor.class.getName());

	/**
	 * Enthält alle aktiven Aktorreferenzen, Shared Memory mit Actorenvironment.
	 */
	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;

	/**
	 * Enthält alle fertigen, asynchronen Antworten, Shared Memory mit
	 * Actorenvironment.
	 */
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;

	/**
	 * Hilfstabelle für die Verwaltung von asynchronen Aufträgen, bis zum
	 * Eintreffen deren Antwort.
	 */
	private Hashtable<String, ConcurrentLinkedQueue<JobTimeWrapper>> openJobs = new Hashtable<>();

	/**
	 * Vorhaltezeit in Millisekunden für Aktorreferenzen und asynchrone
	 * Nachrichten. Wird primär in Actorenvironment gehalten.
	 */
	private long storageTime;

	/**
	 * Methode aus UntypedActor. Überschrieben für die Nachrichtenverarbeitung.
	 * 
	 * @param arg0
	 *            Nachrichtenobjekt
	 */
	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0.toString().equals("Init")) {
			initMsgReceived(arg0);
		} else if (arg0.toString().equals("JobMsg")) {
			jobMsgReceived(arg0);
		} else {
			resultMsgReceived(arg0);
		}
	}

	/**
	 * 
	 * 
	 * @param msg
	 *            Nachrichtenobjekt
	 */
	private void initMsgReceived(Object msg) {
		AsyncMailboxActorIniMsg arg = (AsyncMailboxActorIniMsg) msg;
		jobsTable = arg.getJobstable();
		storageTime = arg.getStorageTime();
		actorRefTable = arg.getActorRefTable();
	}

	/**
	 * Dient der Verarbeitung von neuen, asynchronen Aufträgen. Legt eine
	 * Postkastenqueue in openJobs an und schickt den Jobidentifikationsstring
	 * zurück.
	 * 
	 * @param msg
	 *            Nachrichtenobjekt
	 */
	private void jobMsgReceived(Object msg) {
		cleanUp();
		AsyncMailboxActorJobMsg jobMsg = (AsyncMailboxActorJobMsg) msg;

		ActorRefTimeWrapper wrap = actorRefTable.get(jobMsg.getActorId());

		String jobId = jobMsg.getActorId() + System.currentTimeMillis();

		if (wrap != null) {

			wrap.setTimeout(System.currentTimeMillis() + storageTime);
			JobTimeWrapper openJobResult = new JobTimeWrapper(jobId,
					jobMsg.getMsg(), (System.currentTimeMillis() + storageTime));

			ConcurrentLinkedQueue<JobTimeWrapper> tmpqueue = openJobs
					.get(jobMsg.getActorId());

			if (tmpqueue != null) {

				tmpqueue.add(openJobResult);

			} else {

				tmpqueue = new ConcurrentLinkedQueue<JobTimeWrapper>();
				tmpqueue.add(openJobResult);
				openJobs.put(jobMsg.getActorId(), tmpqueue);
			}

			jobMsg.setActorId(jobId);
			getSender().tell(jobMsg, getSelf());
		} else {
			openJobs.remove(jobMsg.getActorId());
			jobMsg.setMsg(null);
			jobMsg.setActorId(null);
			getSender().tell(jobMsg, getSelf());
		}
		wrap.getActorref().tell(jobMsg.getMsg(), getSelf());
	}

	/**
	 * Verlegt die erhaltene asynchrone Aktorantwort von openJobs nach
	 * jobsTable.
	 * 
	 * @param msg
	 *            Nachrichtenobjekt
	 */
	private void resultMsgReceived(Object msg) {
		JobTimeWrapper result = openJobs.get(getSender().toString()).poll();
		result.setTimeout(System.currentTimeMillis() + storageTime);
		jobsTable.put(result.getJobId(), result);
	}

	/**
	 * Entfernt abgelaufene Aufträge aus openJobs
	 */
	private void cleanUp() {
		Collection<ConcurrentLinkedQueue<JobTimeWrapper>> col = openJobs
				.values();
		for (ConcurrentLinkedQueue<JobTimeWrapper> q : col) {
			if (q.isEmpty()) {
				col.remove(q);
			} else {
				for (JobTimeWrapper w : q) {
					if (w.getTimeout() < System.currentTimeMillis()) {
						q.remove(w);
					}
				}
			}
		}
	}

}
