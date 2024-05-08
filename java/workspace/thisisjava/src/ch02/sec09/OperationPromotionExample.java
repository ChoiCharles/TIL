package ch02.sec09;

public class OperationPromotionExample {
	public static void main(String[] args) {
		// 변수를 연산에 사용하지 않는다면
		// 컴파일 단계에서 10 + 20 연산
		byte result1 = 10 + 20;
		System.out.println("result1: " + result1);
		
		// 변수가 연산에 사용될 때 실행 시 연산 실행
		// 이때, 정수 타입 변수가 피연산자로 사용된다면
		// int타입보다 작은 byte, short타입은 int타입으로 자동 변환 된다
		byte v1 = 10;
		byte v2 = 20;
		// v1과 v2의 타입은 byte에서 int로 자동 변환 됬기 때문에 아래 코드는 컴파일 에러 발생
		// byte result2 = v1 + v2;
		int result2 = v1 + v2;
		System.out.println("result2: " + result2);
		
		// 피연산자 중 long타입이 있어 int타입이 자동으로 long타입으로 변환됬다
		byte v3 = 10;
		int v4 = 100;
		long v5 = 1000L;
		long result3 = v3 + v4 + v5;
		System.out.println("result3: " + result3);
		
		// char타입 역시 int로 자동 변환 됬기 때문에 유니코드가 아닌 문자를 출력하기 위해선 추가적인 변환 작업이 필요
		char v6 = 'A';
		char v7 = 1;
		int result4 = v6 + v7;
		System.out.println("result4: " + result4);
		System.out.println("result4: " + (char)result4);
		
		// 정수타입만 연산되기 때문에 소수점 아래는 버려진다
		int v8 = 10;
		int result5 = v8 / 4;
		System.out.println("result5: " + result5);
		
		// 소수점 아래도 구하고 싶다면 결과값을 실수타입으로 지정해줘야 한다
		int v9 = 10;
		double result6 = v9 / 4.0;
		System.out.println("result6: " + result6);
		
		// 피연산자 모두 정수타입인 상태에서 실수타입 결과값을 구할때는
		// 피연산자중 최소 하나를 실수타입으로 변환해 줘야한다
		// 변환하지 않게되면 연산이후 타입이 변경되기 때문에 소수점 아래값이 버려진다
		int v10 = 1;
		int v11 = 2;
		double result7 = (double) v10 / v11;
		System.out.println("result7: " + result7);
	}
}
