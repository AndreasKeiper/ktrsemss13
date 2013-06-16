
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generatePreAvailableActor complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generatePreAvailableActor">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="propsid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generatePreAvailableActor", propOrder = {
    "propsid"
})
public class GeneratePreAvailableActor {

    protected String propsid;

    /**
     * Gets the value of the propsid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPropsid() {
        return propsid;
    }

    /**
     * Sets the value of the propsid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPropsid(String value) {
        this.propsid = value;
    }

}
