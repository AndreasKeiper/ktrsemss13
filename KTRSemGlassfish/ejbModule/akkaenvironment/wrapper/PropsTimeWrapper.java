package akkaenvironment.wrapper;

import akka.actor.Props;

public class PropsTimeWrapper {

	private Props props;
	private long timeout;

	public PropsTimeWrapper(Props props, long timeout) {
		this.props = props;
		this.timeout = timeout;
	}

	public Props getProps() {
		return props;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
