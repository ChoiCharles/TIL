package ch07.sec10.exam01;

public class PhoneExample {
	public static void main(String[] args) {
		// Phone은 추상클래스이기에 new 연산자를 통한 객체생성을 할 수 없다
		// Phone phone = new Phone();
		
		SmartPhone smartPhone = new SmartPhone("홍길동");
		
		smartPhone.turnOn();
		smartPhone.internetSearch();
		smartPhone.turnOff();
	}
}
