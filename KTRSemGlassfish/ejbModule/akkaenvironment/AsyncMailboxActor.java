package akkaenvironment;

import java.util.concurrent.ConcurrentHashMap;

import akka.actor.UntypedActor;
import akkaenvironment.wrapper.JobTimeWrapper;

public class AsyncMailboxActor extends UntypedActor {

	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;
	private long storageTime;

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0.toString().equals("Init")) {
			AsyncMailboxActorIniMsg arg = (AsyncMailboxActorIniMsg) arg0;
			jobsTable = arg.getJobstable();
			storageTime = arg.getStorageTime();
		} else {
			JobTimeWrapper tmp = new JobTimeWrapper(arg0, storageTime);
			jobsTable.put(getSender().toString(), tmp);
		}

	}

}
