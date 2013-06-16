package com.ktrsem.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.Duration;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.util.Timeout;

import com.ktrsem.akka.TestActor;
import com.ktrsem.akka.Testmessage;

/*
 * MIME Types:
 * 	akka/props : Props
 * 	akka/msg : Object
 */

public class MainServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6950467745925225680L;

	private Hashtable<Integer, Props> keyedprops = new Hashtable<Integer, Props>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		test(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String mime = req.getContentType();
		if (mime.contentEquals("akka/props")) {
			try {
				resp.getOutputStream().print(gotProps(req, resp));
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (mime.contentEquals("akka/msg")) {
			int actorhash = (int) req.getAttribute("id");
			byte[] buffer = new byte[req.getContentLength()];
			req.getInputStream().read(buffer);
			Object result = null;
			try {
				Object message = deserialize(buffer);
				result = taskExecution(actorhash, message, 60);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (result != null) {
				resp.getOutputStream().write(serialize(result));
			} else {
				resp.getOutputStream().print("Error");
			}

		}

	}

	private int gotProps(HttpServletRequest req, HttpServletResponse resp)
			throws IOException, ClassNotFoundException {
		byte[] buffer = new byte[req.getContentLength()];
		req.getInputStream().read(buffer);
		Props props = (Props) deserialize(buffer);
		int hash = props.hashCode();
		keyedprops.put(hash, props);
		return hash;
	}

	public static byte[] serialize(Object obj) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		ObjectOutputStream os = new ObjectOutputStream(out);
		os.writeObject(obj);
		return out.toByteArray();
	}

	public static Object deserialize(byte[] data) throws IOException,
			ClassNotFoundException {
		ByteArrayInputStream in = new ByteArrayInputStream(data);
		ObjectInputStream is = new ObjectInputStream(in);
		return is.readObject();
	}

	private Object taskExecution(int propshash, Object msg, int time) {
		Props props = keyedprops.get(propshash);
		if (props != null) {

			ActorSystem requestsys = ActorSystem.create();
			ActorRef ref = requestsys.actorOf(props);
			Timeout timeout = new Timeout(Duration.create(time, "seconds"));
			Future<Object> future = Patterns.ask(ref, msg, timeout);
			Object result;
			try {
				result = (String) Await.result(future, timeout.duration());
			} catch (Exception e) {
				return null;
			}
			return result;
		} else {
			return null;
		}
	}

	private void test(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		Testmessage initmsg = new Testmessage(10, 2, 0);
		Props actor = new Props(TestActor.class);
		int hash = actor.hashCode();
		keyedprops.put(hash, actor);
		Testmessage result = (Testmessage) taskExecution(hash, initmsg, 60);
		resp.getOutputStream().print(result.getResult());
	}
}
