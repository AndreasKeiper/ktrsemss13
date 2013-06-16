
package ktrsem.ws.clientstub.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getPreAvailablePropsResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getPreAvailablePropsResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="preavailable" type="{http://webservice/}propsPreAvailableMessage" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getPreAvailablePropsResponse", propOrder = {
    "preavailable"
})
public class GetPreAvailablePropsResponse {

    protected List<PropsPreAvailableMessage> preavailable;

    /**
     * Gets the value of the preavailable property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preavailable property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreavailable().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PropsPreAvailableMessage }
     * 
     * 
     */
    public List<PropsPreAvailableMessage> getPreavailable() {
        if (preavailable == null) {
            preavailable = new ArrayList<PropsPreAvailableMessage>();
        }
        return this.preavailable;
    }

}
