
package ktrsem.ws.clientstub.generated;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for propsPreAvailableMessage complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="propsPreAvailableMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="actorname" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "propsPreAvailableMessage", propOrder = {
    "actorname",
    "description"
})
public class PropsPreAvailableMessage {

    @XmlElement(required = true)
    protected String actorname;
    @XmlElement(required = true)
    protected String description;

    /**
     * Gets the value of the actorname property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActorname() {
        return actorname;
    }

    /**
     * Sets the value of the actorname property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActorname(String value) {
        this.actorname = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
