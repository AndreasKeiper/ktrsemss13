package akkaenvironment.wrapper;

import akka.actor.ActorRef;

/**
 * Wrapperklasse um die gekapselten Daten mit einer Verfallszeit zu versehen.
 */
public class ActorRefTimeWrapper {

	private ActorRef actorRef;
	private long timeout;

	public ActorRefTimeWrapper(ActorRef actorref, long timeout) {
		this.actorRef = actorref;
		this.timeout = timeout;
	}

	public ActorRef getActorref() {
		return actorRef;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
