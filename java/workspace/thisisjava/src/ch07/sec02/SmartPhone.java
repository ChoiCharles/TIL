package ch07.sec02;

// 상속은 하나의 클래스에서만 받을 수 있다
public class SmartPhone extends Phone {
	public boolean wifi;
	
	public SmartPhone(String model, String color) {
		this.model = model;
		this.color = color;
	}
	
	public void setWifi(boolean wifi) {
		this.wifi = wifi;
		System.out.println("와이파이 상태를 변경했습니다");
	}
	
	public void internet() {
		System.out.println("인터넷에 연결합니다");
	}
}
