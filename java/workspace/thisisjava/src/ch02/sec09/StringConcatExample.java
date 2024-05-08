package ch02.sec09;

public class StringConcatExample {
	public static void main(String[] args) {
		// + 연산은 앞에서부터 연산된다
		int result1 = 10 + 2 + 8;
		System.out.println("result1: " + result1);
		
		// 피연산자 중 문자열이 있을 때 나머지 피연산자도 문자열로 자동 변환되어 연산된다
		String result2 = 10 + 2 + "8";
		System.out.println("result2: " + result2);
		
		String result3 = 10 + "2" + 8;
		System.out.println("result3: " + result3);
		
		String result4 = "10" + 2 + 8;
		System.out.println("result4: " + result4);
		
		// 괄호는 최우선으로 연산된다
		String result5 = "10" + (2 + 8);
		System.out.println("result5: " + result5);
	}
}
