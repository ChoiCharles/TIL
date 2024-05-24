package ch07.sec05.exam02;

public class Car {
	public int speed;
	
	public void speedUp() {
		speed += 1;
	}
	
	// 메소드에 final 선언을 하면 오버라이딩할 수 없음
	public final void stop() {
		System.out.println("차를 멈춤");
		speed = 0;
	}
}
