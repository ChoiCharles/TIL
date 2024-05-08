package ch02.sec02;

public class LongExample {
	public static void main(String[] args) {
		long var1 = 10;
		long var2 = 20L;
		
		// 컴파일러는 int로 인식하기 때문에 오류 발생
		// long var3 = 10000000000;
		
		// L 혹은 l 을 붙여줘 long타입임을 컴파일러에게 알려줘야 한다
		long var4 = 10000000000L;
		
		System.out.println(var1);
		System.out.println(var2);
		System.out.println(var4);
	}
}
