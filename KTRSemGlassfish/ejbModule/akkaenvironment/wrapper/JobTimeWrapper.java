package akkaenvironment.wrapper;

public class JobTimeWrapper {

	private Object job;
	private long timeout;

	public JobTimeWrapper(Object job, long timeout) {
		this.job = job;
		this.timeout = timeout;
	}

	public Object getResultMsg() {
		return job;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
