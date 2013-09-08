package akkaenvironment;

import java.util.Collection;
import java.util.Hashtable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import akka.actor.ActorRef;
import akka.actor.UntypedActor;
import akkaenvironment.wrapper.ActorRefTimeWrapper;
import akkaenvironment.wrapper.JobTimeWrapper;

public class AsyncMailboxActor extends UntypedActor {

	private ConcurrentHashMap<String, ActorRefTimeWrapper> actorRefTable;
	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;
	private Hashtable<String, ConcurrentLinkedQueue<JobTimeWrapper>> openJobs;
	private long storageTime;

	@Override
	public void onReceive(Object arg0) throws Exception {

		if (arg0.toString().equals("Init")) {
			initMsgReceived(arg0);
		} else if (arg0.toString().equals("JobMsg")) {
			jobMsgReceived(arg0);
		} else {
			resultMsgReceived();
		}
	}

	private void initMsgReceived(Object msg) {
		AsyncMailboxActorIniMsg arg = (AsyncMailboxActorIniMsg) msg;
		jobsTable = arg.getJobstable();
		storageTime = arg.getStorageTime();
		actorRefTable = arg.getActorRefTable();
	}

	private void jobMsgReceived(Object msg) {
		cleanUp();
		AsyncMailboxActorJobMsg jobMsg = (AsyncMailboxActorJobMsg) msg;
		ActorRef receiver = (actorRefTable.get(jobMsg.getActorId()))
				.getActorref();
		String jobId = jobMsg.getActorId() + System.currentTimeMillis();
		if (receiver != null) {
			JobTimeWrapper openJobResult = new JobTimeWrapper(jobId,
					jobMsg.getMsg(), (System.currentTimeMillis() + storageTime));
			receiver.tell(jobMsg.getMsg(), getSelf());
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
	}

	private void resultMsgReceived() {
		JobTimeWrapper result = openJobs.get(getSender().toString()).poll();
		result.setTimeout(System.currentTimeMillis() + storageTime);
		jobsTable.put(result.getJobId(), result);
	}

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
