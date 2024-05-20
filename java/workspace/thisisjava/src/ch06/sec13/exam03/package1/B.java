package ch06.sec13.exam03.package1;

public class B {
	public void method() {
		A a = new A();
		
		a.field1 = 1;
		a.field2 = 1;
		// 클래스가 달라 접근불가
		// a.field3 = 1;
		
		a.method1();
		a.method2();
		// 클래스가 달라 접근불가
		// a.method3();
	}
}
