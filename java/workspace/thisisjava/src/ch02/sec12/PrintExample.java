package ch02.sec12;

public class PrintExample {
	public static void main(String[] args) {
		// 출력 방식은 print, println, printf가 있다
		// print() : 괄호 안의 내용을 출력하고 행은 바꾸지 마라
		// println() : 괄호 안의 내용을 출력하고 행을 바꿔라
		// printf("형식문자열", 값1, 값2, ...) : 형식문자열에 맞춰 뒤의 값을 출력해라
		
		// 형식문자열 포멧
		// %[argument_index$][flags][width][.precision]conversion
		// argument_index$ : 값의 순번
		// flags : - or 0	=> - : 오른쪽이 공백으로 채워진다, 0 : 공백이 0으로 채워진다
		// width : 전체 자릿수
		// .precision : 소수 자릿수
		// 변환문자인 conversion을 제외하고 생략 가능하다
		// 변환문자에는 d(정수), f(실수), s(문자열)가 위치한다
		
		// printf 출력 예
		
		System.out.printf("이름: %s", "홍길동");
		System.out.println(" ");
		System.out.printf("나이: %d", 25);
		System.out.println(" ");
		
		// 형식 문자열에 포함될 값이 여러개일 때 값의 순번을 지정해줘야 한다
		System.out.printf("이름: %1$s, 나이: %2$d", "홍길동", 25);
		
		
		int value = 123;
		System.out.printf("상품의 가격: %d원\n", value);
		System.out.printf("상품의 가격: %6d원\n", value);
		System.out.printf("상품의 가격: %-6d원\n", value);
		System.out.printf("상품의 가격: %06d원\n", value);
		
		double area = 3.14159 * 10 * 10;
		System.out.printf("반지름이 %d인 원의 넓이:%10.2f\n", 10, area);
		
		String name = "홍길동";
		String job = "도적";
		System.out.printf("%6d | %-10s | %10s\n", 1, name, job);
	}
}
