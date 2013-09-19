package akkaenvironment.wrapper;

/**
 * Wrapperklasse um die gekapselten Daten mit einer Verfallszeit zu versehen.
 */
public class JobTimeWrapper {

	private String jobId;
	private Object job;
	private long timeout;

	public JobTimeWrapper(String jobId, Object job, long timeout) {
		this.jobId = jobId;
		this.job = job;
		this.timeout = timeout;
	}

	public String getJobId() {
		return jobId;
	}

	public Object getResultMsg() {
		return job;
	}

	public void setResultMsg(Object job) {
		this.job = job;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
