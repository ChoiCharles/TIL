package ch02.sec03;

public class CharExample {
	public static void main(String[] args) {
		char c1 = 'A';	// 'A' 와 매핑되는 숫자 65를 자동으로 대입
		char c2 = 65;	// 유니코드 65는 문자로 'A'를 뜻함
		
		char c3 = '가';
		char c4 = 44032;
		
		System.out.println(c1);
		System.out.println(c2);
		System.out.println(c3);
		System.out.println(c4);
		
		/*
		 * 단순 초기화 목적으로 작은 따옴표만 사용하면 컴파일 에러가 발생
		 * 따라서 공백을 넣어 초기화 해야함
		 * 
		 * char c = '';		// 컴파일 에러
		 * 
		 * char c = ' ';	// 변수 c를 공백으로 초기화
		 */
	}
}
