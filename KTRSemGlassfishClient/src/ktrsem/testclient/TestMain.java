package ktrsem.testclient;

import helper.SerializationHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import ktrsem.ws.clientstub.WebServiceAccessStub;
import ktrsem.ws.clientstub.generated.JobMessage;
import ktrsem.ws.clientstub.generated.JobMessageAsync;
import ktrsem.ws.clientstub.generated.PropsPreAvailableMessage;
import akka.actor.Props;
import akkaenvironment.actors.TestActor1;
import akkaenvironment.actors.TestActor1Request;
import akkaenvironment.actors.TestActor1Response;
import akkaenvironment.actors.TestActorIntf;

public class TestMain {

	private WebServiceAccessStub stub = new WebServiceAccessStub();

	public static void main(String[] args) {

		TestMain test = new TestMain();
		test.testdispatchAsyncJob();
	}

	// works
	public void testsendMessage() {

		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		List<String> actorids = new ArrayList<>();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
			actorids.add(stub.generatePreAvailableActor(msg.getActorname()));
		}
		for (String actorid : actorids) {
			System.out.println(actorid);
		}
		byte[] req = null;
		try {
			req = SerializationHelper.serialize(new TestActor1Request(15, 15));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JobMessage msg = new JobMessage();
		msg.setActorid(actorids.get(0));
		msg.setMessage(req);
		msg.setWaittime(10000);
		TestActor1Response resp = null;
		try {
			resp = (TestActor1Response) SerializationHelper.deserialize(stub
					.sendMessage(msg));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resp.getResult());

	}

	// flaws classload
	public void testaddPropsOnly() {

		byte[] tmp = null;
		try {
			tmp = SerializationHelper.serialize(new Props(TestActorIntf.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int propsid = stub.addPropsOnly(tmp);
		System.out.println("testaddPropsOnly successfull with: " + propsid);
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
			actorids.add(stub.generatePreAvailableActor(msg.getActorname()));
		}
		for (String actorid : actorids) {
			System.out.println(actorid);
		}

	}

	// flaws classload
	public void testgenerateActorDirectly() {

		byte[] tmp = null;
		try {
			tmp = SerializationHelper.serialize(new Props(TestActorIntf.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String actorid = stub.generateActorDirectly(tmp);
		System.out.println(actorid);

	}

	// flaws classload
	public void testgenerateActorIndirectly() {

		byte[] tmp = null;
		try {
			tmp = SerializationHelper.serialize(new Props(TestActor1.class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int propsid = stub.addPropsOnly(tmp);
		String actorid = stub.generateActorIndirectly(propsid);
		System.out.println(actorid);
	}

	// works
	public void testdispatchAsyncJob() {

		List<PropsPreAvailableMessage> tmp = stub.getPreAvailableProps();
		List<String> actorids = new ArrayList<>();
		for (PropsPreAvailableMessage msg : tmp) {
			System.out.println(msg.getActorname());
			actorids.add(stub.generatePreAvailableActor(msg.getActorname()));
		}
		for (String actorid : actorids) {
			System.out.println(actorid);
		}
		byte[] req = null;
		try {
			req = SerializationHelper.serialize(new TestActor1Request(15, 15));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JobMessageAsync msg = new JobMessageAsync();
		msg.setActorid(actorids.get(0));
		msg.setMessage(req);
		TestActor1Response resp = null;

		String jobid = stub.dispatchAsyncJob(msg);
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			resp = (TestActor1Response) SerializationHelper.deserialize(stub
					.getAsyncJobresult(jobid));
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(resp.getResult());

	}

	public void testDefineOwnActorClass() {

	}

	private byte[] loadDataFromAny(String name, File classesDir) {

		name = name.replace('.', '/');
		name = name + ".class";

		byte[] ret = null;

		try {
			File f = new File(classesDir.getAbsolutePath(), name);
			FileInputStream fis = new FileInputStream(f);

			ByteBuffer bb = ByteBuffer.allocate(4 * 1024);
			byte[] buf = new byte[1024];
			int readedBytes = -1;

			while ((readedBytes = fis.read(buf)) != -1) {
				bb.put(buf, 0, readedBytes);
			}

			ret = bb.array();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return ret;
	}

}
