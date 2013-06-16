
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jobmessage" type="{http://webservice/}jobMessage" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendMessage", propOrder = {
    "jobmessage"
})
public class SendMessage {

    protected JobMessage jobmessage;

    /**
     * Gets the value of the jobmessage property.
     * 
     * @return
     *     possible object is
     *     {@link JobMessage }
     *     
     */
    public JobMessage getJobmessage() {
        return jobmessage;
    }

    /**
     * Sets the value of the jobmessage property.
     * 
     * @param value
     *     allowed object is
     *     {@link JobMessage }
     *     
     */
    public void setJobmessage(JobMessage value) {
        this.jobmessage = value;
    }

}
