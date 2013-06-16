
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dispatchAsyncJob complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dispatchAsyncJob">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jobmessageasync" type="{http://webservice/}jobMessageAsync" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dispatchAsyncJob", propOrder = {
    "jobmessageasync"
})
public class DispatchAsyncJob {

    protected JobMessageAsync jobmessageasync;

    /**
     * Gets the value of the jobmessageasync property.
     * 
     * @return
     *     possible object is
     *     {@link JobMessageAsync }
     *     
     */
    public JobMessageAsync getJobmessageasync() {
        return jobmessageasync;
    }

    /**
     * Sets the value of the jobmessageasync property.
     * 
     * @param value
     *     allowed object is
     *     {@link JobMessageAsync }
     *     
     */
    public void setJobmessageasync(JobMessageAsync value) {
        this.jobmessageasync = value;
    }

}
