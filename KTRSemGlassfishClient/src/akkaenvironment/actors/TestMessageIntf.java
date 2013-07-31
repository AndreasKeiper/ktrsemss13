package akkaenvironment.actors;

import akkaenvironment.actors.stndintf.RemoteMessageInterface;

public class TestMessageIntf implements RemoteMessageInterface {

	Object data;

	@Override
	public Object getData() {

		return data;
	}

	@Override
	public void setData(Object content) {
		this.data = content;

	}

}
