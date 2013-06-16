package akkaenvironment.actors;

import akka.actor.UntypedActor;

public class TestActor1 extends UntypedActor {

	@Override
	public void onReceive(Object arg0) throws Exception {
		TestActor1Request tmp = (TestActor1Request) arg0;
		TestActor1Response result = new TestActor1Response(tmp.getA()
				+ tmp.getB());
		getSender().tell(result, getSelf());
	}

}
