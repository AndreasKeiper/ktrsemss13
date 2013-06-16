package akkaenvironment.wrapper;

import akka.actor.ActorRef;

public class ActorRefTimeWrapper {

	private ActorRef actorref;
	private long timeout;

	public ActorRefTimeWrapper(ActorRef actorref, long timeout) {
		this.actorref = actorref;
		this.timeout = timeout;
	}

	public ActorRef getActorref() {
		return actorref;
	}

	public long getTimeout() {
		return timeout;
	}

	public void setTimeout(long timeout) {
		this.timeout = timeout;
	}

}
