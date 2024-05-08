package ch03.sec03;

public class OverflowUnderflowExample {
	public static void main(String[] args) {
		// 최대값을 초과해 최소값이 되버린다
		byte var1 = 125;
		for(int i = 0; i < 5; i++) {
			var1++;
			System.out.println("var1: " + var1);
		}
		
		System.out.println("-----------------");
		// 최소값을 초과해 최대값이 되버린다
		byte var2 = -125;
		for(int i = 0; i < 5; i++) {
			var2--;
			System.out.println("var2: " + var2);
		}
	}
}
