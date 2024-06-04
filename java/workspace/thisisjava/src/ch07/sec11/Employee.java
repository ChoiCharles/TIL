package ch07.sec11;

// sealed처리된 클래스를 상속받기 위해서는 final, non-sealed로 선언하거나
// sealed를 사용해 또다른 봉인 클래스로 선언해야한다
public final class Employee extends Person {
	@Override
	public void work() {
		System.out.println("제품을 생산합니다");
	}
}
