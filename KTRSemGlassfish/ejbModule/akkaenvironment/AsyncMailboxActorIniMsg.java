package akkaenvironment;

import java.util.Hashtable;

import akkaenvironment.wrapper.JobTimeWrapper;

public class AsyncMailboxActorIniMsg {

	private Hashtable<String, JobTimeWrapper> jobstable;

	public AsyncMailboxActorIniMsg(Hashtable<String, JobTimeWrapper> jobstable) {
		this.jobstable = jobstable;
	}

	public String toString() {
		return "Init";
	}

	public Hashtable<String, JobTimeWrapper> getJobstable() {
		return jobstable;
	}

}
