package webservice.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class JobMessage {

	private String actorid;
	private int waittime;
	private byte[] msg;

	public JobMessage() {
		super();
	}

	public JobMessage(String actorid, int waittime, byte[] msg) {
		super();
		this.actorid = actorid;
		this.waittime = waittime;
		this.msg = msg;
	}

	@XmlElement(name = "actorid", required = true)
	public String getActorid() {
		return actorid;
	}

	public void setActorid(String actorid) {
		this.actorid = actorid;
	}

	@XmlElement(name = "waittime", required = true)
	public int getWaittime() {
		return waittime;
	}

	public void setWaittime(int waittime) {
		this.waittime = waittime;
	}

	@XmlElement(name = "message", required = true)
	public byte[] getMsg() {
		return msg;
	}

	public void setMsg(byte[] msg) {
		this.msg = msg;
	}

}
