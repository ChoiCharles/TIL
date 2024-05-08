package ch03.sec06;

public class CompareOperatorExample {
	public static void main(String[] args) {
		int num1 = 10;
		int num2 = 10;
		boolean result1 = (num1 == num2);
		boolean result2 = (num1 != num2);
		boolean result3 = (num1 <= num2);
		System.out.println("result1: " + result1);
		System.out.println("result2: " + result2);
		System.out.println("result3: " + result3);
		
		char char1 = 'A';
		char char2 = 'B';
		boolean result4 = (char1 < char2);
		System.out.println("result4: " + result4);
		
		// 타입이 다르다면 자동으로 변환해 비교한다
		int num3 = 1;
		double num4 = 1.0;
		boolean result5 = (num3 == num4);
		System.out.println("result5: " + result5);
		
		// 부동 소수점을 사용하는 실수 타입은 0.1을 정확히 표현할수 없으며
		// float과 double의 정밀도 차이에 의해 다르게 나타나기 때문에 비교 전에 float으로 변환시켜 비교해야 한다
		float num5 = 0.1f;
		double num6 = 0.1;
		boolean result6 = (num5 == num6);
		boolean result7 = (num5 == (float)num6);
		System.out.println("result6: " + result6);
		System.out.println("result7: " + result7);
		
		// 문자열은 비교연산자가 아닌 별도의 함수로 비교한다
		String str1 = "자바";
		String str2 = "java";
		boolean result8 = (str1.equals(str2));
		boolean result9 = (! str1.equals(str2));
		System.out.println("result8: " + result8);
		System.out.println("result9: " + result9);
	}
}
