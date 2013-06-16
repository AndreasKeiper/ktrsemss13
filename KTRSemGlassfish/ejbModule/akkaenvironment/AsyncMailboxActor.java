package akkaenvironment;

import java.util.Hashtable;

import akka.actor.UntypedActor;
import akkaenvironment.wrapper.JobTimeWrapper;

public class AsyncMailboxActor extends UntypedActor {

	private Hashtable<String, JobTimeWrapper> jobstable;
	private long storageTime = 60000;

	@Override
	public void onReceive(Object arg0) throws Exception {
		if (arg0.toString().equals("Init")) {
			AsyncMailboxActorIniMsg arg = (AsyncMailboxActorIniMsg) arg0;
			jobstable = arg.getJobstable();
		} else {
			JobTimeWrapper tmp = new JobTimeWrapper(arg0, storageTime);
			jobstable.put(getSender().toString(), tmp);
		}

	}

}
