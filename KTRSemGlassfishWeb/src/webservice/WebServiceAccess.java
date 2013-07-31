package webservice;

import helper.SerializationHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import webservice.messages.JobMessage;
import webservice.messages.JobMessageAsync;
import webservice.messages.PropsPreAvailableMessage;
import akka.actor.Props;
import akkaenvironment.Actorenvironment;
import akkaenvironment.wrapper.PropsPreAvailableWrapper;

@WebService
public class WebServiceAccess {

	Logger logger = Logger.getLogger(WebServiceAccess.class.getName());

	@EJB(name = "ejb/Actorenvironment")
	private Actorenvironment actorenv;
	private long timeout = 60000;

	@WebMethod
	@WebResult(name = "actorid")
	public String generateActorDirectly(@WebParam(name = "props") byte[] props) {
		try {
			return actorenv.generateActorDirectly(
					(Props) SerializationHelper.deserialize(props), timeout);
		} catch (ClassNotFoundException | IOException e) {
			logger.warning("Direct actor generation failed!");
			e.printStackTrace();
			return "Error";
		}

	}

	@WebMethod
	@WebResult(name = "propsid")
	public int addPropsOnly(@WebParam(name = "props") byte[] props) {
		try {
			return actorenv.addPropsOnly(
					(Props) SerializationHelper.deserialize(props), timeout);
		} catch (ClassNotFoundException | IOException e) {
			logger.warning("addPropsOnly failed");
			e.printStackTrace();
			return 0;
		}
	}

	@WebMethod
	@WebResult(name = "actorid")
	public String generateActorIndirectly(
			@WebParam(name = "propsid") int propsid) {
		return actorenv.generateActorIndirectly(propsid, timeout);
	}

	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] sendMessage(@WebParam(name = "jobmessage") JobMessage msg) {
		String actorid = msg.getActorid();
		int waittime = msg.getWaittime();
		byte[] content = msg.getMsg();

		try {
			Object msgobj = (Object) SerializationHelper.deserialize(content);
			Object respobj = actorenv.sendMessage(actorid, msgobj, waittime);
			return SerializationHelper.serialize(respobj);
		} catch (IOException | ClassNotFoundException e) {
			logger.warning("sendMessage failed");
			e.printStackTrace();
			return null;
		}
	}

	@WebMethod
	@WebResult(name = "jobid")
	public String dispatchAsyncJob(
			@WebParam(name = "jobmessageasync") JobMessageAsync msg) {

		try {
			return actorenv.dispatchAsyncJob(msg.getActorid(),
					SerializationHelper.deserialize(msg.getMsg()));
		} catch (ClassNotFoundException | IOException e) {
			logger.warning("dispatchAsyncJob failed");
			return "Error";
		}
	}

	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] getAsyncJobresult(@WebParam(name = "jobid") String jobid) {
		try {
			return SerializationHelper.serialize(actorenv
					.getAsyncJobresult(jobid));
		} catch (IOException e) {
			logger.warning("getAsyncJobresult failed");
			return null;
		}
	}

	@WebMethod
	@WebResult(name = "preavailable")
	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		List<PropsPreAvailableMessage> tmp = new ArrayList<>();
		for (PropsPreAvailableWrapper wrap : actorenv.getActorPreTable()
				.values()) {
			tmp.add(new PropsPreAvailableMessage(wrap.getActorName(), wrap
					.getDescription()));
		}
		return tmp;
	}

	@WebMethod
	@WebResult(name = "actorid")
	public String generatePreAvailableActor(
			@WebParam(name = "propsid") String propsid) {
		return actorenv.generateActorfromPreProps(propsid, timeout);
	}

	@WebMethod
	public void defineOwnClass(@WebParam(name = "ownclass") byte[] clazz) {
		actorenv.defineOwnClass(clazz);
	}
}
