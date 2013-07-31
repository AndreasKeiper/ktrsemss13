package akkaenvironment;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

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
import akkaenvironment.actors.TestActor1;
import akkaenvironment.actors.Testmessage;
import akkaenvironment.wrapper.ActorRefTimeWrapper;
import akkaenvironment.wrapper.JobTimeWrapper;
import akkaenvironment.wrapper.PropsPreAvailableWrapper;
import akkaenvironment.wrapper.PropsTimeWrapper;

/**
 * Session Bean implementation class Actorenvironment
 */
@ConcurrencyManagement(ConcurrencyManagementType.BEAN)
@Singleton(name = "ejb/Actorenvironment")
@LocalBean
public class Actorenvironment {

	private ActorSystem actorsys;
	private Hashtable<Integer, PropsTimeWrapper> propsTable;
	private Hashtable<String, ActorRefTimeWrapper> actorRefTable;
	private Hashtable<String, JobTimeWrapper> jobsTable;
	private Hashtable<String, PropsPreAvailableWrapper> actorPreTable;
	private ActorRef asyncActor;
	private String packagename = "TestActor1";
	private ArrayClassLoader loader;

	public Actorenvironment() {

		actorsys = ActorSystem.create();
		propsTable = new Hashtable<>();
		actorRefTable = new Hashtable<>();
		jobsTable = new Hashtable<>();
		Props async = new Props(AsyncMailboxActor.class);
		asyncActor = actorsys.actorOf(async);
		asyncActor.tell(new AsyncMailboxActorIniMsg(jobsTable), null);
		actorPreTable = new Hashtable<>();
		loader = new ArrayClassLoader(Thread.currentThread()
				.getContextClassLoader());
		generateActorPreTable();
	}

	private void generateActorPreTable() {

	}

	// remote not possible
	public String generateActorDirectly(Props props, long timeout) {
		ActorRef actor = actorsys.actorOf(props);
		ActorRefTimeWrapper tmp = new ActorRefTimeWrapper(actor,
				(System.currentTimeMillis() + timeout));
		actorRefTable.put(actor.toString(), tmp);
		return actor.toString();
	}

	// remote not possible
	public int addPropsOnly(Props props, long timeout) {
		PropsTimeWrapper tmp = new PropsTimeWrapper(props,
				System.currentTimeMillis() + timeout);
		int hash = tmp.hashCode();
		propsTable.put(hash, tmp);
		return hash;
	}

	// remote not possible
	public String generateActorIndirectly(int propsid, long timeout) {
		PropsTimeWrapper props = propsTable.get(propsid);
		if (props == null) {
			return null;
		} else {
			ActorRef actorref = actorsys.actorOf(props.getProps());
			actorRefTable.put(actorref.toString(), new ActorRefTimeWrapper(
					actorref, System.currentTimeMillis() + timeout));
			return actorref.toString();
		}
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
		Testmessage initmsg = new Testmessage("Bla");
		Props props = new Props(TestActor.class);
		String actor = generateActorDirectly(props, 1000000);
		Testmessage result = (Testmessage) sendMessage(actor, initmsg, 1000000);
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
		Collection<PropsTimeWrapper> propscol = propsTable.values();
		Collection<ActorRefTimeWrapper> actorcol = actorRefTable.values();
		Collection<JobTimeWrapper> jobcol = jobsTable.values();
		for (PropsTimeWrapper wrap : propscol) {
			if (wrap.getTimeout() < currentTime) {
				propscol.remove(wrap);
			}
		}
		for (ActorRefTimeWrapper wrap : actorcol) {
			if (wrap.getTimeout() < currentTime) {
				actorcol.remove(wrap);
			}
		}
		for (JobTimeWrapper wrap : jobcol) {
			if (wrap.getTimeout() < currentTime) {
				jobcol.remove(wrap);
			}
		}
	}

	public Hashtable<String, PropsPreAvailableWrapper> getActorPreTable() {
		return actorPreTable;
	}

	public String generateActorfromPreProps(String propsid, long timeout) {
		ActorRef actor = actorsys
				.actorOf(actorPreTable.get(propsid).getProps());
		actorRefTable.put(actor.toString(), new ActorRefTimeWrapper(actor,
				timeout));
		return actor.toString();
	}

	public void defineOwnClass(byte[] clazz) {
		loader.defineClassFromArray(clazz);
	}

	public static List<File> getPackageContent(String packageName)
			throws IOException {
		ArrayList<File> list = new ArrayList<File>();
		TestActor1 tmp = null;
		Enumeration<URL> urls = ClassLoader.getSystemClassLoader()
				.getResources(packageName);
		while (urls.hasMoreElements()) {
			URL url = urls.nextElement();
			File dir = new File(url.getFile());
			for (File f : dir.listFiles()) {
				list.add(f);
			}
		}
		return list;
	}

	public static void showFiles(File[] files) {
		for (File file : files) {
			if (file.isDirectory()) {
				System.out.println("Directory: " + file.getName());
				showFiles(file.listFiles()); // Calls same method again.
			} else {
				System.out.println("File: " + file.getName());
			}
		}
	}

}
