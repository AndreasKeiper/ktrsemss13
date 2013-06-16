
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for jobMessageAsync complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="jobMessageAsync">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actorid" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="message" type="{http://www.w3.org/2001/XMLSchema}base64Binary"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jobMessageAsync", propOrder = {
    "actorid",
    "message"
})
public class JobMessageAsync {

    @XmlElement(required = true)
    protected String actorid;
    @XmlElement(required = true)
    protected byte[] message;

    /**
     * Gets the value of the actorid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActorid() {
        return actorid;
    }

    /**
     * Sets the value of the actorid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActorid(String value) {
        this.actorid = value;
    }

    /**
     * Gets the value of the message property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setMessage(byte[] value) {
        this.message = value;
    }

}
