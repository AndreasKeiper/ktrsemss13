package akkaenvironment.actors;

import akka.actor.UntypedActor;

public class TestActor extends UntypedActor {

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Object arg0) throws Exception {
		this.getSender().tell(new Testmessage("Actor works!!!"));
	}

}
