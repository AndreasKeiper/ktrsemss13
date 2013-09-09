package ktrsem.testclient;

import helper.SerializationHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ktrsem.ws.clientstub.WebServiceAccessStub;
import ktrsem.ws.clientstub.generated.JobMessage;
import ktrsem.ws.clientstub.generated.JobMessageAsync;
import ktrsem.ws.clientstub.generated.PropsPreAvailableMessage;
import ktrsem.ws.clientstub.generated.ServerFault_Exception;
import akka.actor.Props;
import akkaenvironment.actors.TestActor;
import akkaenvironment.actors.TestMessage;

public class TestMain {

	private WebServiceAccessStub stub = new WebServiceAccessStub();
	private String testmsg = "lkasödlfjpowierlksdjfölaskdjfpoiwerksdjfaölsdjfpoweiru";

	public static void main(String[] args) {

		TestMain test = new TestMain();
		String result = test.testdispatchAsyncJob();

		try {
			Thread.currentThread().sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		test.testgetAsyncJobResult(result);
	}

	// works
	public void testsendMessage() {

		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		List<String> actorids = new ArrayList<>();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
			try {
				actorids.add(stub.generatePreAvailableActor(msg.getActorname()));
			} catch (ServerFault_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (String actorid : actorids) {
			System.out.println(actorid);
		}
		byte[] req = null;
		try {
			req = SerializationHelper.serialize(new TestMessage(testmsg));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JobMessage msg = new JobMessage();
		msg.setActorid(actorids.get(0));
		msg.setMessage(req);
		msg.setWaittime(10000);
		TestMessage resp = null;

		try {
			resp = (TestMessage) SerializationHelper.deserialize(stub
					.sendMessage(msg));
		} catch (ClassNotFoundException | IOException | ServerFault_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(resp.getContent());

	}

	// works
	public void testgetPreAvailableProps() {

		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
		}

	}

	// works
	public void testgeneratePreAvailableActor() {

		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		List<String> actorids = new ArrayList<>();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
			try {
				actorids.add(stub.generatePreAvailableActor(msg.getActorname()));
			} catch (ServerFault_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (String actorid : actorids) {
			System.out.println(actorid);
		}

	}

	public void testgenerateActorFromProps() {

		byte[] tmp = null;
		try {
			tmp = SerializationHelper.serialize(new Props(TestActor.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actorid = "Actor generation from Props failed!";
		try {
			actorid = stub.generateActorFromProps(tmp);
		} catch (ServerFault_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(actorid);

	}

	// works
	public String testdispatchAsyncJob() {

		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		List<String> actorids = new ArrayList<>();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
			try {
				actorids.add(stub.generatePreAvailableActor(msg.getActorname()));
			} catch (ServerFault_Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		for (String actorid : actorids) {
			System.out.println(actorid);
		}

		byte[] req = null;
		try {
			req = SerializationHelper.serialize(new TestMessage(testmsg));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JobMessageAsync msg = new JobMessageAsync();
		msg.setActorid(actorids.get(0));
		msg.setMessage(req);

		System.out.println(msg.getActorid());

		String jobid = "dispatch asynchjob failed!";
		try {
			jobid = stub.dispatchAsyncJob(msg);
		} catch (ServerFault_Exception e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		System.out.println(jobid);
		return jobid;
	}

	public void testgetAsyncJobResult(String jobId) {
		TestMessage resp = null;
		try {
			resp = (TestMessage) SerializationHelper.deserialize(stub
					.getAsyncJobresult(jobId));
		} catch (ClassNotFoundException | IOException | ServerFault_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(resp.getContent());
	}

}
