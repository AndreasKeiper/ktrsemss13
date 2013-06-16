package ktrsem.ws.clientstub;

import java.net.URL;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

import ktrsem.ws.clientstub.generated.JobMessage;
import ktrsem.ws.clientstub.generated.JobMessageAsync;
import ktrsem.ws.clientstub.generated.PropsPreAvailableMessage;
import ktrsem.ws.clientstub.generated.WebServiceAccess;
import ktrsem.ws.clientstub.generated.WebServiceAccessService;

public class WebServiceAccessStub implements WebServiceAccess {

	private WebServiceAccessService service;
	private WebServiceAccess proxy;

	public WebServiceAccessStub() {
		super();
		service = new WebServiceAccessService();
		proxy = service.getWebServiceAccessPort();
	}

	public WebServiceAccessStub(URL wsdlLocation) {
		service = new WebServiceAccessService(wsdlLocation);
		proxy = service.getWebServiceAccessPort();
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "sendMessage", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.SendMessage")
	@ResponseWrapper(localName = "sendMessageResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.SendMessageResponse")
	@Action(input = "http://webservice/WebServiceAccess/sendMessageRequest", output = "http://webservice/WebServiceAccess/sendMessageResponse")
	public byte[] sendMessage(
			@WebParam(name = "jobmessage", targetNamespace = "") JobMessage jobmessage) {
		return proxy.sendMessage(jobmessage);
	}

	@Override
	@WebMethod
	@WebResult(name = "propsid", targetNamespace = "")
	@RequestWrapper(localName = "addPropsOnly", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.AddPropsOnly")
	@ResponseWrapper(localName = "addPropsOnlyResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.AddPropsOnlyResponse")
	@Action(input = "http://webservice/WebServiceAccess/addPropsOnlyRequest", output = "http://webservice/WebServiceAccess/addPropsOnlyResponse")
	public int addPropsOnly(
			@WebParam(name = "props", targetNamespace = "") byte[] props) {
		return proxy.addPropsOnly(props);
	}

	@Override
	@WebMethod
	@WebResult(name = "preavailable", targetNamespace = "")
	@RequestWrapper(localName = "getPreAvailableProps", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GetPreAvailableProps")
	@ResponseWrapper(localName = "getPreAvailablePropsResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GetPreAvailablePropsResponse")
	@Action(input = "http://webservice/WebServiceAccess/getPreAvailablePropsRequest", output = "http://webservice/WebServiceAccess/getPreAvailablePropsResponse")
	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		return proxy.getPreAvailableProps();
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generatePreAvailableActor", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GeneratePreAvailableActor")
	@ResponseWrapper(localName = "generatePreAvailableActorResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GeneratePreAvailableActorResponse")
	@Action(input = "http://webservice/WebServiceAccess/generatePreAvailableActorRequest", output = "http://webservice/WebServiceAccess/generatePreAvailableActorResponse")
	public String generatePreAvailableActor(
			@WebParam(name = "propsid", targetNamespace = "") String propsid) {
		return proxy.generatePreAvailableActor(propsid);
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generateActorDirectly", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GenerateActorDirectly")
	@ResponseWrapper(localName = "generateActorDirectlyResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GenerateActorDirectlyResponse")
	@Action(input = "http://webservice/WebServiceAccess/generateActorDirectlyRequest", output = "http://webservice/WebServiceAccess/generateActorDirectlyResponse")
	public String generateActorDirectly(
			@WebParam(name = "props", targetNamespace = "") byte[] props) {
		return proxy.generateActorDirectly(props);
	}

	@Override
	@WebMethod
	@WebResult(name = "actorid", targetNamespace = "")
	@RequestWrapper(localName = "generateActorIndirectly", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GenerateActorIndirectly")
	@ResponseWrapper(localName = "generateActorIndirectlyResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GenerateActorIndirectlyResponse")
	@Action(input = "http://webservice/WebServiceAccess/generateActorIndirectlyRequest", output = "http://webservice/WebServiceAccess/generateActorIndirectlyResponse")
	public String generateActorIndirectly(
			@WebParam(name = "propsid", targetNamespace = "") int propsid) {
		return proxy.generateActorIndirectly(propsid);
	}

	@Override
	@WebMethod
	@WebResult(name = "jobid", targetNamespace = "")
	@RequestWrapper(localName = "dispatchAsyncJob", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.DispatchAsyncJob")
	@ResponseWrapper(localName = "dispatchAsyncJobResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.DispatchAsyncJobResponse")
	@Action(input = "http://webservice/WebServiceAccess/dispatchAsyncJobRequest", output = "http://webservice/WebServiceAccess/dispatchAsyncJobResponse")
	public String dispatchAsyncJob(
			@WebParam(name = "jobmessageasync", targetNamespace = "") JobMessageAsync jobmessageasync) {
		return proxy.dispatchAsyncJob(jobmessageasync);
	}

	@Override
	@WebMethod
	@WebResult(name = "resultmessage", targetNamespace = "")
	@RequestWrapper(localName = "getAsyncJobresult", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GetAsyncJobresult")
	@ResponseWrapper(localName = "getAsyncJobresultResponse", targetNamespace = "http://webservice/", className = "ktrsem.ws.clientstub.GetAsyncJobresultResponse")
	@Action(input = "http://webservice/WebServiceAccess/getAsyncJobresultRequest", output = "http://webservice/WebServiceAccess/getAsyncJobresultResponse")
	public byte[] getAsyncJobresult(
			@WebParam(name = "jobid", targetNamespace = "") String jobid) {
		return proxy.getAsyncJobresult(jobid);
	}

}
