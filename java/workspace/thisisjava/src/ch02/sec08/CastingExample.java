package ch02.sec08;

public class CastingExample {
	public static void main(String[] args) {
		/*
		 * 큰 허용 범위 타입은 작은 허용 범위 타입으로 자동 타입 변환될 수 없다
		 * 하지만, 큰 허용 범위 타입을 쪼개 작은 허용 범위 타입에 저장할 수 있다
		 * 이를 강제 타입 변환(casting) 이라고 한다
		 * 캐스팅은 연산자로 괄호를 사용해 쪼개는 단위를 줄 수 있다
		 */
		
		int var1 = 10;
		byte var2 = (byte) var1;
		System.out.println(var2);
		
		long var3 = 300;
		int var4 = (int) var3;
		System.out.println(var4);
		
		int var5 = 65;
		char var6 = (char) var5;
		System.out.println(var6);
		
		double var7 = 3.14;
		int var8 = (int) var7;
		System.out.println(var8);
	}
}
