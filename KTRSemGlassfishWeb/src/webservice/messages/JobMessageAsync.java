package webservice.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Nachrichtenklasse für die Übertragung von asynchronen per JAX-WS
 */
@XmlType
public class JobMessageAsync {

	/**
	 * Identifikationsstring des Zielaktors
	 */
	private String actorId;

	/**
	 * Serialisiertes Nachrichtenobjekt
	 */
	private byte[] msg;

	public JobMessageAsync() {
		super();
	}

	public JobMessageAsync(String actorid, byte[] msg) {
		super();
		this.actorId = actorid;
		this.msg = msg;
	}

	public String getActorid() {
		return actorId;
	}

	@XmlElement(name = "actorid", required = true)
	public void setActorid(String actorId) {
		this.actorId = actorId;
	}

	public byte[] getMsg() {
		return msg;
	}

	@XmlElement(name = "message", required = true)
	public void setMsg(byte[] msg) {
		this.msg = msg;
	}

}
