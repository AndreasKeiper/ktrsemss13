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

/**
 * Nach JAX-WS annotierte Klasse für die Erzeugung des Webserviceendpunktes.
 */
@WebService
public class WebServiceAccess {

	/**
	 * Logger
	 */
	Logger logger = Logger.getLogger(WebServiceAccess.class.getName());

	@EJB(name = "ejb/Actorenvironment")
	private Actorenvironment actorenv;

	/**
	 * Erzeugt eine Aktorinstanz aus einer serialisierten Props-Instanz.
	 * 
	 * @param props
	 *            Serialisierte Props-Instanz
	 * @return Aktoridentifikationsstring
	 * @throws ServerFault
	 *             Wird im Falle eines Fehlers geworfen.
	 */
	@WebMethod
	@WebResult(name = "actorid")
	public String generateActorFromProps(@WebParam(name = "props") byte[] props)
			throws ServerFault {
		try {
			Props tmp = (Props) SerializationHelper.deserialize(props);
			String actor = actorenv.generateActorFromProps(tmp);
			logger.info("Actor created from Props: " + actor);
			return actor;

		} catch (Exception e) {
			logger.warning("Direct actor generation failed! Error: "
					+ e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	/**
	 * Bearbeitet synchrone Nachrichten.
	 * 
	 * @param msg
	 *            In JobMessage gekapselte Nachricht
	 * @return Serialisiertes Antwortobjekt
	 * @throws ServerFault
	 *             Exception für die die Fehlerübertragung per JAX-WS
	 */
	@WebMethod
	@WebResult(name = "resultmessage")
	public byte[] sendMessage(@WebParam(name = "jobmessage") JobMessage msg)
			throws ServerFault {
		String actorid = msg.getActorid();
		int waittime = msg.getWaittime();
		byte[] content = msg.getMsg();
		logger.info("sendMessage to actor: " + actorid);
		try {

			Object msgobj = (Object) SerializationHelper.deserialize(content);
			Object respobj = actorenv.sendMessage(actorid, msgobj, waittime);
			return SerializationHelper.serialize(respobj);
		} catch (Exception e) {
			logger.warning("sendMessage failed! Error: " + e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

	/**
	 * Bearbeitet asynchrone Nachrichten.
	 * 
	 * @param msg
	 *            In JobMessageAsync gekapselte Nachricht
	 * @return Postkastenreferenzstring
	 * @throws ServerFault
	 *             Exception für die die Fehlerübertragung per JAX-WS
	 */
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

	/**
	 * Dient dem Abruf von asynchronen Antworten
	 * 
	 * @param jobid
	 *            Postkastenreferenzstring
	 * @return Serialisiertes Antwortobjekt
	 * @throws ServerFault
	 *             Exception für die die Fehlerübertragung per JAX-WS
	 */
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

	/**
	 * Überträgt die Inhalte von actorPreTable aus Actorenvironment
	 * 
	 * @return actorPreTable aus Actorenvironment
	 */
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

	/**
	 * Erzeugt einen Aktor aus den in actorPreTable vorhaltenen Props-Instanzen
	 * 
	 * @param propsid
	 *            Vollqualifizierender Name der Aktorklasse, erhältlich über
	 *            getActorPreTable()
	 * @return Aktoridentifikationsstring
	 * @throws ServerFault
	 *             Exception für die die Fehlerübertragung per JAX-WS
	 */
	@WebMethod
	@WebResult(name = "actorid")
	public String generatePreAvailableActor(
			@WebParam(name = "propsid") String propsid) throws ServerFault {
		try {
			String tmp = actorenv.generateActorFromPreProps(propsid);
			logger.info("Preavailable actor created: " + tmp);
			return tmp;
		} catch (Exception e) {
			logger.warning("generatePreAvailableActor failed! Error: "
					+ e.getMessage());
			throw new ServerFault(e.getMessage());
		}
	}

}
