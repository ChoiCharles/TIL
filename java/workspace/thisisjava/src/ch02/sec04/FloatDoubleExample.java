package ch02.sec04;

public class FloatDoubleExample {
	public static void main(String[] args) {
		/*
		 * float : 32bit, 유효 소수 이하 7자리
		 * double : 64bit, 유효 소수 이하 15자리
		 * float은 지수에 8bit, 가수에 23bit
		 * double은 지수에 11bit, 가수에 52bit
		 * 따라서 double의 정밀도가 더 높다
		 */
		
		/*
		 * 컴파일러는 기본적으로 실수를 double타입으로 해석하므로
		 * float타입에 대입하고 싶다면 f 혹은 F 로 명시해 줘야 한다
		 */
		
		// 정밀도 확인
		float var1 = 0.1234567890123456789f;
		double var2 = 0.1234567890123456789;
		System.out.println("var1: " + var1);
		System.out.println("var2: " + var2);
		
		// 10의 거듭제곱
		double var3 = 3e6;
		float var4 = 3e6F;
		double var5 = 2e-3;
		System.out.println("var3: " + var3);
		System.out.println("var4: " + var4);
		System.out.println("var5: " + var5);
	}
}
