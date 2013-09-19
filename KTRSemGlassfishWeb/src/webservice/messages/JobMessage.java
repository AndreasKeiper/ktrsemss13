package webservice.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Nachrichtenklasse für die Übertragung von synchronen Nachrichten per JAX-WS
 */
@XmlType
public class JobMessage {

	/**
	 * Identifikationsstring des Zielaktors
	 */
	private String actorId;

	/**
	 * Maximale Wartezeit auf die synchrone Antwort
	 */
	private int waitTime;

	/**
	 * Serialisiertes Nachrichtenobjekt
	 */
	private byte[] msg;

	public JobMessage() {
		super();
	}

	public JobMessage(String actorid, int waittime, byte[] msg) {
		super();
		this.actorId = actorid;
		this.waitTime = waittime;
		this.msg = msg;
	}

	@XmlElement(name = "actorid", required = true)
	public String getActorid() {
		return actorId;
	}

	public void setActorid(String actorid) {
		this.actorId = actorid;
	}

	@XmlElement(name = "waittime", required = true)
	public int getWaittime() {
		return waitTime;
	}

	public void setWaittime(int waittime) {
		this.waitTime = waittime;
	}

	@XmlElement(name = "message", required = true)
	public byte[] getMsg() {
		return msg;
	}

	public void setMsg(byte[] msg) {
		this.msg = msg;
	}

}
