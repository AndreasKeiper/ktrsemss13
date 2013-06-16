
package ktrsem.ws.clientstub;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAsyncJobresult complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAsyncJobresult">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="jobid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAsyncJobresult", propOrder = {
    "jobid"
})
public class GetAsyncJobresult {

    protected String jobid;

    /**
     * Gets the value of the jobid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getJobid() {
        return jobid;
    }

    /**
     * Sets the value of the jobid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setJobid(String value) {
        this.jobid = value;
    }

}
