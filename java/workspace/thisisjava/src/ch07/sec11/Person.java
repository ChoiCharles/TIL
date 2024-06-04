package ch07.sec11;

// Java 15 부터 sealed로 permits로 상속가능한 자식 클래스 외의 자식클래스 생성을 막을 수 있다
public sealed class Person permits Employee, Manager {
	public String name;
	
	public void work() {
		System.out.println("하는 일이 결정되지 않았습니다");
	}
}
