package ktrsem.ws.clientstub;

import java.net.URL;
import java.util.List;

import ktrsem.ws.clientstub.generated.JobMessage;
import ktrsem.ws.clientstub.generated.JobMessageAsync;
import ktrsem.ws.clientstub.generated.PropsPreAvailableMessage;

public class WebServiceAccessClientStub {

	WebServiceAccessStub stub;

	public WebServiceAccessClientStub() {
		stub = new WebServiceAccessStub();
	}

	public WebServiceAccessClientStub(URL wsdlLocation) {
		stub = new WebServiceAccessStub(wsdlLocation);
	}

	public byte[] sendMessage(JobMessage jobmessage) {

		return stub.sendMessage(jobmessage);

	}

	public List<PropsPreAvailableMessage> getPreAvailableProps() {
		return stub.getPreAvailableProps();
	}

	public String generatePreAvailableActor(String propsid) {
		return stub.generatePreAvailableActor(propsid);
	}

	public String dispatchAsyncJob(JobMessageAsync jobmessageasync) {
		return stub.dispatchAsyncJob(jobmessageasync);
	}

	public byte[] getAsyncJobresult(String jobid) {
		return stub.getAsyncJobresult(jobid);
	}
}
