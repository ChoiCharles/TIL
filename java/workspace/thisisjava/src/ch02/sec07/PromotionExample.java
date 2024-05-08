package ch02.sec07;

public class PromotionExample {
	public static void main(String[] args) {
		/*
		 * 허용 범위가 작은 티입이 허용 범위가 큰 타입으로 대입될때
		 * 자동 타입 변환이 일어난다
		 * 
		 * 허용 범위 순서
		 * byte < short, char < int < long < float < double
		 */
		
		byte byteValue = 10;
		int intValue = byteValue;
		System.out.println("intValue: " + intValue);
		
		char charValue = '가';
		intValue = charValue;
		System.out.println("가의 유니코드: " + intValue);
		
		intValue = 50;
		long longValue = intValue;
		System.out.println("longValue: " + longValue);
		
		longValue = 100;
		float floatValue = longValue;
		System.out.println("floatValue: " + floatValue);
		
		floatValue = 100.5F;
		double doubleValue = floatValue;
		System.out.println("doubelValue: " + doubleValue);
	}
}
