package akkaenvironment.actors;

import akka.actor.UntypedActor;

/**
 * Testaktor der den in TestMessage enthaltenen String in einer TestMessage
 * zurückgibt.
 */
public class TestActor extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		TestMessage tmp = (TestMessage) arg0;
		this.getSender().tell(new TestMessage(tmp.getContent()), getSelf());
	}

}
