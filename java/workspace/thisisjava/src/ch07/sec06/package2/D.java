package ch07.sec06.package2;

import ch07.sec06.package1.A;

public class D extends A {
	public D() {
		super();
	}
	
	public void method() {
		this.field = "value";
		this.method();
	}
	
	// 직접 객체 생성하는건 안됨
	public void method2() {
		A a = new A();
		a.field = "value";
		a.method();
	}
}
