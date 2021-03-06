
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for generateActorFromPropsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="generateActorFromPropsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actorid" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "generateActorFromPropsResponse", propOrder = {
    "actorid"
})
public class GenerateActorFromPropsResponse {

    protected String actorid;

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

}
