package akkaenvironment;

import java.util.concurrent.ConcurrentHashMap;

import akkaenvironment.wrapper.JobTimeWrapper;

public class AsyncMailboxActorIniMsg {

	private ConcurrentHashMap<String, JobTimeWrapper> jobsTable;
	private long storageTime;

	public AsyncMailboxActorIniMsg(
			ConcurrentHashMap<String, JobTimeWrapper> jobstable,
			long storagetime) {
		this.jobsTable = jobstable;
		this.storageTime = storagetime;
	}

	public String toString() {
		return "Init";
	}

	public ConcurrentHashMap<String, JobTimeWrapper> getJobstable() {
		return jobsTable;
	}

	public long getStorageTime() {
		return storageTime;
	}
}
