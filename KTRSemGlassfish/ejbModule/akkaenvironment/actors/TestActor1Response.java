package akkaenvironment.actors;

import java.io.Serializable;

public class TestActor1Response implements Serializable {

	int result = 0;

	public TestActor1Response(int result) {
		super();
		this.result = result;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
