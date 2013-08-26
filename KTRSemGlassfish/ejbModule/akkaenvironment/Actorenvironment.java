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
import akkaenvironment.actors.TestMessage;
import akkaenvironment.wrapper.ActorRefTimeWrapper;
import akkaenvironment.wrapper.JobTimeWrapper;
import akkaenvironment.wrapper.PropsPreAvailableWrapper;

/**
 * Session Bean implementation class Actorenvironment
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton(name = "ejb/Actorenvironment")
@LocalBean
public class Actorenvironment {

	private ActorSystem actorsys;
	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;
	private ConcurrentHashMap<String, PropsPreAvailableWrapper> actorPreTable;
	private ActorRef asyncActor;
	private long storageTime = 60000;

	public Actorenvironment() {

		actorsys = ActorSystem.create();
		actorRefTable = new ConcurrentHashMap<>();
		jobsTable = new ConcurrentHashMap<>();
		Props async = new Props(AsyncMailboxActor.class);
		asyncActor = actorsys.actorOf(async);
		asyncActor.tell(new AsyncMailboxActorIniMsg(jobsTable, storageTime),
				null);
		actorPreTable = new ConcurrentHashMap<>();
		generateActorPreTable();
	}

	private void generateActorPreTable() {
		actorPreTable.put(TestActor.class.getName(),
				new PropsPreAvailableWrapper(TestActor.class.getName(),
						"Testactor für Echotest mit String", new Props(
								TestActor.class)));
	}

	public String generateActorFromProps(Props props) {
		ActorRef actor = actorsys.actorOf(props);
		ActorRefTimeWrapper tmp = new ActorRefTimeWrapper(actor,
				(System.currentTimeMillis() + storageTime));
		actorRefTable.put(actor.toString(), tmp);
		return actor.toString();
	}

	public Object sendMessage(String actorid, Object msg, int waittime) {
		ActorRefTimeWrapper tmp = actorRefTable.get(actorid);
		if (tmp == null) {
			return null;
		} else {
			ActorRef actor = tmp.getActorref();
			Timeout timeout = new Timeout(Duration.create(waittime, "seconds"));
			Future<Object> future = Patterns.ask(actor, msg, timeout);
			Object result;
			try {
				result = Await.result(future, timeout.duration());
			} catch (Exception e) {
				return null;
			}
			return result;
		}
	}

	public String test() {
		TestMessage initmsg = new TestMessage("Bla");
		Props props = new Props(TestActor.class);
		String actor = generateActorFromProps(props);
		TestMessage result = (TestMessage) sendMessage(actor, initmsg, 1000000);
		return result.getContent();
	}

	public String dispatchAsyncJob(String actorid, Object msg) {
		ActorRef tmp = (actorRefTable.get(actorid)).getActorref();
		if (tmp != null) {
			tmp.tell(msg, asyncActor);
			return tmp.toString();
		} else {
			return "0";
		}
	}

	public Object getAsyncJobresult(String sourceactor) {
		JobTimeWrapper tmp = jobsTable.get(sourceactor);
		if (tmp != null) {
			return tmp.getResultMsg();
		} else {
			return null;
		}
	}

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

	public ConcurrentHashMap<String, PropsPreAvailableWrapper> getActorPreTable() {
		return actorPreTable;
	}

	public String generateActorfromPreProps(String propsid) {
		ActorRef actor = actorsys
				.actorOf(actorPreTable.get(propsid).getProps());
		actorRefTable.put(actor.toString(), new ActorRefTimeWrapper(actor,
				storageTime));
		return actor.toString();
	}

}
