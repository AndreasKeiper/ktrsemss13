package akkaenvironment.actors;

import java.io.Serializable;

public class TestMessage implements Serializable {

	private String content;

	public TestMessage(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}
