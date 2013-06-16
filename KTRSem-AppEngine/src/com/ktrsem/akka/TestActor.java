package com.ktrsem.akka;

import akka.actor.UntypedActor;

public class TestActor extends UntypedActor {

	@SuppressWarnings("deprecation")
	@Override
	public void onReceive(Object arg0) throws Exception {
		Testmessage test = (Testmessage) arg0;
		Testmessage returnresult = new Testmessage(test.getA(), test.getB(),
				(test.getA() / test.getB()));
		this.getSender().tell(returnresult);
	}

}
