package akkaenvironment;

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
			AsyncMailboxActorIniMsg arg = (AsyncMailboxActorIniMsg) arg0;
			jobsTable = arg.getJobstable();
			storageTime = arg.getStorageTime();
			actorRefTable = arg.getActorRefTable();
		} else if (arg0.toString().equals("JobMsg")) {
			AsyncMailboxActorJobMsg jobMsg = (AsyncMailboxActorJobMsg) arg0;
			ActorRef receiver = (actorRefTable.get(jobMsg.getActorId()))
					.getActorref();
			String jobId = jobMsg.getActorId() + System.currentTimeMillis();
			if (receiver != null) {
				JobTimeWrapper openJobResult = new JobTimeWrapper(jobId,
						jobMsg.getMsg(), 0);
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

		} else {
			JobTimeWrapper result = openJobs.get(getSender().toString()).poll();
			result.setTimeout(System.currentTimeMillis() + storageTime);
			jobsTable.put(result.getJobId(), result);
		}

	}

}
