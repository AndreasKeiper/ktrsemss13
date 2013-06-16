package akkaenvironment.actors;

import java.io.Serializable;

public class TestActor1Request implements Serializable {

	int a;
	int b;

	public TestActor1Request(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getB() {
		return b;
	}

	public void setB(int b) {
		this.b = b;
	}

}
