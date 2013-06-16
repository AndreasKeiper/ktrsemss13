package webservice.messages;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class JobMessageAsync {

	private String actorid;
	private byte[] msg;

	public JobMessageAsync(String actorid, byte[] msg) {
		super();
		this.actorid = actorid;
		this.msg = msg;
	}

	public String getActorid() {
		return actorid;
	}

	@XmlElement(name = "actorid", required = true)
	public void setActorid(String actorid) {
		this.actorid = actorid;
	}

	public byte[] getMsg() {
		return msg;
	}

	@XmlElement(name = "message", required = true)
	public void setMsg(byte[] msg) {
		this.msg = msg;
	}

}
