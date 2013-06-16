package com.ktrsem.akka;

public class Testmessage {

	private int a;
	private int b;
	private int result;

	public Testmessage(int a, int b, int result) {
		this.a = a;
		this.b = b;
		this.result = result;
	}

	public int getA() {
		return a;
	}

	public int getB() {
		return b;
	}

	public int getResult() {
		return result;
	}

	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public void setResult(int result) {
		this.result = result;
	}

}
