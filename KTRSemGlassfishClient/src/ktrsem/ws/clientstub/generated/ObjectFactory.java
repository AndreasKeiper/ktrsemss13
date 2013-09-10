
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ktrsem.ws.clientstub.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DispatchAsyncJobResponse_QNAME = new QName("http://webservice/", "dispatchAsyncJobResponse");
    private final static QName _GetPreAvailablePropsResponse_QNAME = new QName("http://webservice/", "getPreAvailablePropsResponse");
    private final static QName _GetAsyncJobresultResponse_QNAME = new QName("http://webservice/", "getAsyncJobresultResponse");
    private final static QName _GenerateActorFromPropsResponse_QNAME = new QName("http://webservice/", "generateActorFromPropsResponse");
    private final static QName _GeneratePreAvailableActor_QNAME = new QName("http://webservice/", "generatePreAvailableActor");
    private final static QName _GeneratePreAvailableActorResponse_QNAME = new QName("http://webservice/", "generatePreAvailableActorResponse");
    private final static QName _GenerateActorFromProps_QNAME = new QName("http://webservice/", "generateActorFromProps");
    private final static QName _GetPreAvailableProps_QNAME = new QName("http://webservice/", "getPreAvailableProps");
    private final static QName _SendMessageResponse_QNAME = new QName("http://webservice/", "sendMessageResponse");
    private final static QName _SendMessage_QNAME = new QName("http://webservice/", "sendMessage");
    private final static QName _ServerFault_QNAME = new QName("http://webservice/", "serverFault");
    private final static QName _DispatchAsyncJob_QNAME = new QName("http://webservice/", "dispatchAsyncJob");
    private final static QName _GetAsyncJobresult_QNAME = new QName("http://webservice/", "getAsyncJobresult");
    private final static QName _GetAsyncJobresultResponseResultmessage_QNAME = new QName("", "resultmessage");
    private final static QName _GenerateActorFromPropsProps_QNAME = new QName("", "props");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ktrsem.ws.clientstub.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPreAvailablePropsResponse }
     * 
     */
    public GetPreAvailablePropsResponse createGetPreAvailablePropsResponse() {
        return new GetPreAvailablePropsResponse();
    }

    /**
     * Create an instance of {@link GenerateActorFromPropsResponse }
     * 
     */
    public GenerateActorFromPropsResponse createGenerateActorFromPropsResponse() {
        return new GenerateActorFromPropsResponse();
    }

    /**
     * Create an instance of {@link GetAsyncJobresultResponse }
     * 
     */
    public GetAsyncJobresultResponse createGetAsyncJobresultResponse() {
        return new GetAsyncJobresultResponse();
    }

    /**
     * Create an instance of {@link DispatchAsyncJobResponse }
     * 
     */
    public DispatchAsyncJobResponse createDispatchAsyncJobResponse() {
        return new DispatchAsyncJobResponse();
    }

    /**
     * Create an instance of {@link GeneratePreAvailableActorResponse }
     * 
     */
    public GeneratePreAvailableActorResponse createGeneratePreAvailableActorResponse() {
        return new GeneratePreAvailableActorResponse();
    }

    /**
     * Create an instance of {@link GeneratePreAvailableActor }
     * 
     */
    public GeneratePreAvailableActor createGeneratePreAvailableActor() {
        return new GeneratePreAvailableActor();
    }

    /**
     * Create an instance of {@link SendMessage }
     * 
     */
    public SendMessage createSendMessage() {
        return new SendMessage();
    }

    /**
     * Create an instance of {@link GetPreAvailableProps }
     * 
     */
    public GetPreAvailableProps createGetPreAvailableProps() {
        return new GetPreAvailableProps();
    }

    /**
     * Create an instance of {@link SendMessageResponse }
     * 
     */
    public SendMessageResponse createSendMessageResponse() {
        return new SendMessageResponse();
    }

    /**
     * Create an instance of {@link GenerateActorFromProps }
     * 
     */
    public GenerateActorFromProps createGenerateActorFromProps() {
        return new GenerateActorFromProps();
    }

    /**
     * Create an instance of {@link GetAsyncJobresult }
     * 
     */
    public GetAsyncJobresult createGetAsyncJobresult() {
        return new GetAsyncJobresult();
    }

    /**
     * Create an instance of {@link ServerFault }
     * 
     */
    public ServerFault createServerFault() {
        return new ServerFault();
    }

    /**
     * Create an instance of {@link DispatchAsyncJob }
     * 
     */
    public DispatchAsyncJob createDispatchAsyncJob() {
        return new DispatchAsyncJob();
    }

    /**
     * Create an instance of {@link JobMessageAsync }
     * 
     */
    public JobMessageAsync createJobMessageAsync() {
        return new JobMessageAsync();
    }

    /**
     * Create an instance of {@link PropsPreAvailableMessage }
     * 
     */
    public PropsPreAvailableMessage createPropsPreAvailableMessage() {
        return new PropsPreAvailableMessage();
    }

    /**
     * Create an instance of {@link JobMessage }
     * 
     */
    public JobMessage createJobMessage() {
        return new JobMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispatchAsyncJobResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "dispatchAsyncJobResponse")
    public JAXBElement<DispatchAsyncJobResponse> createDispatchAsyncJobResponse(DispatchAsyncJobResponse value) {
        return new JAXBElement<DispatchAsyncJobResponse>(_DispatchAsyncJobResponse_QNAME, DispatchAsyncJobResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreAvailablePropsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getPreAvailablePropsResponse")
    public JAXBElement<GetPreAvailablePropsResponse> createGetPreAvailablePropsResponse(GetPreAvailablePropsResponse value) {
        return new JAXBElement<GetPreAvailablePropsResponse>(_GetPreAvailablePropsResponse_QNAME, GetPreAvailablePropsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAsyncJobresultResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getAsyncJobresultResponse")
    public JAXBElement<GetAsyncJobresultResponse> createGetAsyncJobresultResponse(GetAsyncJobresultResponse value) {
        return new JAXBElement<GetAsyncJobresultResponse>(_GetAsyncJobresultResponse_QNAME, GetAsyncJobresultResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateActorFromPropsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "generateActorFromPropsResponse")
    public JAXBElement<GenerateActorFromPropsResponse> createGenerateActorFromPropsResponse(GenerateActorFromPropsResponse value) {
        return new JAXBElement<GenerateActorFromPropsResponse>(_GenerateActorFromPropsResponse_QNAME, GenerateActorFromPropsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePreAvailableActor }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "generatePreAvailableActor")
    public JAXBElement<GeneratePreAvailableActor> createGeneratePreAvailableActor(GeneratePreAvailableActor value) {
        return new JAXBElement<GeneratePreAvailableActor>(_GeneratePreAvailableActor_QNAME, GeneratePreAvailableActor.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GeneratePreAvailableActorResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "generatePreAvailableActorResponse")
    public JAXBElement<GeneratePreAvailableActorResponse> createGeneratePreAvailableActorResponse(GeneratePreAvailableActorResponse value) {
        return new JAXBElement<GeneratePreAvailableActorResponse>(_GeneratePreAvailableActorResponse_QNAME, GeneratePreAvailableActorResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GenerateActorFromProps }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "generateActorFromProps")
    public JAXBElement<GenerateActorFromProps> createGenerateActorFromProps(GenerateActorFromProps value) {
        return new JAXBElement<GenerateActorFromProps>(_GenerateActorFromProps_QNAME, GenerateActorFromProps.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPreAvailableProps }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getPreAvailableProps")
    public JAXBElement<GetPreAvailableProps> createGetPreAvailableProps(GetPreAvailableProps value) {
        return new JAXBElement<GetPreAvailableProps>(_GetPreAvailableProps_QNAME, GetPreAvailableProps.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessageResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "sendMessageResponse")
    public JAXBElement<SendMessageResponse> createSendMessageResponse(SendMessageResponse value) {
        return new JAXBElement<SendMessageResponse>(_SendMessageResponse_QNAME, SendMessageResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendMessage }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "sendMessage")
    public JAXBElement<SendMessage> createSendMessage(SendMessage value) {
        return new JAXBElement<SendMessage>(_SendMessage_QNAME, SendMessage.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ServerFault }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "serverFault")
    public JAXBElement<ServerFault> createServerFault(ServerFault value) {
        return new JAXBElement<ServerFault>(_ServerFault_QNAME, ServerFault.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DispatchAsyncJob }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "dispatchAsyncJob")
    public JAXBElement<DispatchAsyncJob> createDispatchAsyncJob(DispatchAsyncJob value) {
        return new JAXBElement<DispatchAsyncJob>(_DispatchAsyncJob_QNAME, DispatchAsyncJob.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAsyncJobresult }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://webservice/", name = "getAsyncJobresult")
    public JAXBElement<GetAsyncJobresult> createGetAsyncJobresult(GetAsyncJobresult value) {
        return new JAXBElement<GetAsyncJobresult>(_GetAsyncJobresult_QNAME, GetAsyncJobresult.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "resultmessage", scope = GetAsyncJobresultResponse.class)
    public JAXBElement<byte[]> createGetAsyncJobresultResponseResultmessage(byte[] value) {
        return new JAXBElement<byte[]>(_GetAsyncJobresultResponseResultmessage_QNAME, byte[].class, GetAsyncJobresultResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "resultmessage", scope = SendMessageResponse.class)
    public JAXBElement<byte[]> createSendMessageResponseResultmessage(byte[] value) {
        return new JAXBElement<byte[]>(_GetAsyncJobresultResponseResultmessage_QNAME, byte[].class, SendMessageResponse.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "props", scope = GenerateActorFromProps.class)
    public JAXBElement<byte[]> createGenerateActorFromPropsProps(byte[] value) {
        return new JAXBElement<byte[]>(_GenerateActorFromPropsProps_QNAME, byte[].class, GenerateActorFromProps.class, ((byte[]) value));
    }

}
