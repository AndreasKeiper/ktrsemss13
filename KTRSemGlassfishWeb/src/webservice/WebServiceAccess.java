package webservice;

import helper.SerializationHelper;

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

	@WebMethod
	@WebResult(name = "actorid")
	public String generateActorFromProps(@WebParam(name = "props") byte[] props)
			throws ServerFault {
		try {
			Props tmp = (Props) SerializationHelper.deserialize(props);
			return actorenv.generateActorFromProps(tmp);
		} catch (Exception e) {
			logger.warning("Direct actor generation failed! Error: "
					+ e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] sendMessage(@WebParam(name = "jobmessage") JobMessage msg)
			throws ServerFault {
		String actorid = msg.getActorid();
		int waittime = msg.getWaittime();
		byte[] content = msg.getMsg();

		try {
			Object msgobj = (Object) SerializationHelper.deserialize(content);
			Object respobj = actorenv.sendMessage(actorid, msgobj, waittime);
			return SerializationHelper.serialize(respobj);
		} catch (Exception e) {
			logger.warning("sendMessage failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	@WebMethod
	@WebResult(name = "jobid")
	public String dispatchAsyncJob(
			@WebParam(name = "jobmessageasync") JobMessageAsync msg)
			throws ServerFault {

		try {
			return actorenv.dispatchAsyncJob(msg.getActorid(),
					SerializationHelper.deserialize(msg.getMsg()));
		} catch (Exception e) {
			logger.warning("dispatchAsyncJob failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] getAsyncJobresult(@WebParam(name = "jobid") String jobid)
			throws ServerFault {
		Object tmp = actorenv.getAsyncJobResult(jobid);
		try {
			if (tmp != null) {
				return SerializationHelper.serialize(tmp);
			} else {
				throw new ServerFault("No message");
			}
		} catch (Exception e) {
			logger.warning("getAsyncJobresult failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
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
			@WebParam(name = "propsid") String propsid) throws ServerFault {
		try {
			return actorenv.generateActorFromPreProps(propsid);
		} catch (Exception e) {
			logger.warning("generatePreAvailableActor failed! Error: "
					+ e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

}
