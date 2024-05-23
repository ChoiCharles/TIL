package ch07.sec03.exam02;

public class SmartPhone extends Phone {
	public SmartPhone(String model, String color) {
		super(model, color);	// 부모 클래스가 매개변수가 필요하면 반드시 작성
		System.out.println("SmartPhone(String model, String color) 생성자 실행됨");
	}
}
