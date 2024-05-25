package ch07.sec06.package2;

import ch07.sec06.package1.A;

public class C {
	public void method() {
		// 다른 패키지라 상속해야만 사용가능
		A a = new A();
		a.field = "value";
		a.method();
	}
}
