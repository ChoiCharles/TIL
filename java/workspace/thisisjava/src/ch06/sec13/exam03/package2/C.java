package ch06.sec13.exam03.package2;

import ch06.sec13.exam03.package1.*;

public class C {
	public void method() {
		A a = new A();
		
		a.field1 = 1;
		// 패키지가 달라 접근불가
		// a.field2 = 1;
		// 클래스가 달라 접근불가
		// a.field3 = 1;
		
		a.method1();
		// 패키지가 달라 접근불가
		// a.method2();
		// 클래스가 달라 접근불가
		// a.method3();
	}
}
