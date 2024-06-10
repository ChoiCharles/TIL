package ch09.sec04.exam03;

// 로컬 변수를 로컬 클래스에서 사용할 경우 로컬 변수는 final 특성을 가지게 된다
// java 8 이후부터는 명시하지 않아도 되지만 java 7 이전에는 명시해 줘야한다
public class A {
	// 메소드
	public void method1(int arg) {	// final int arg
		// 로컬 변수
		int var = 1;				// final int var = 1;
		
		// 로컬 클래스
		class B {
			// 메소드
			void method2() {
				// 로컬 변수 읽기
				System.out.println("arg: " + arg);	// (o)
				System.out.println("var: " + var);	// (o)
			
				// 로컬 변수 수정
				// arg = 2;		// (x)
				// var = 2;		// (x)
			}
		}
		
		// 로컬 객체 생성
		B b = new B();
		// 로컬 객체 메소드 호출
		b.method2();
		
		// 로컬 변수 수정
		// arg = 3;		// (x)
		// var = 3;		// (x)
	}
}
