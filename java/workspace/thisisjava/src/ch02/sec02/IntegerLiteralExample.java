package ch02.sec02;

public class IntegerLiteralExample {
	public static void main(String[] args) {
		
		/*
		 * 정수 : byte, char, short, int, long
		 * 실수 : float, double
		 * 
		 * byte : -128 ~ 127
		 * short : -32,768 ~ 32,767
		 * char : 0 ~ 65,535(유니코드)
		 * int : -2,147,483,648 ~ 2,147,483,647
		 */
		
		
		int var1 = 0b1011;	// 2진수
		int var2 = 0206;	// 8진수
		int var3 = 365;		// 10진수
		int var4 = 0xB3;	// 16진수
		
		System.out.println("var1: " + var1);
		System.out.println("var2: " + var2);
		System.out.println("var3: " + var3);
		System.out.println("var4: " + var4);
	}
}
