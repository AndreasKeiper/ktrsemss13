package akkaenvironment.actors;

import java.util.List;

import akka.actor.UntypedActor;
import akkaenvironment.actors.stndintf.RemoteActorInterface;

public class TestActorIntf extends UntypedActor implements RemoteActorInterface {

	@Override
	public void onReceive(Object arg0) throws Exception {
		TestMessageIntf msg = (TestMessageIntf) arg0;
		List<Integer> param = (List<Integer>) msg.getData();
		TestMessageIntf resp = new TestMessageIntf();
		Integer result = new Integer(0);
		for (Integer itg : param) {
			result += itg;
		}
		resp.setData(result);
		getSender().tell(resp, getSelf());

	}

}
