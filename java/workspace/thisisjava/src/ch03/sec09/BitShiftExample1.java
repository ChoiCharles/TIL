package ch03.sec09;

public class BitShiftExample1 {
	public static void main(String[] args) {
		// 3비트만큼 왼쪽으로 이동, 빈자리는 0으로, 넘치는건 버림
		int num1 = 1;
		int result1 = num1 << 3;
		int result2 = num1 * (int) Math.pow(2, 3);
		System.out.println("result1: " + result1);
		System.out.println("result2: " + result2);
		
		// 3비트만큼 오른쪽으로 이동, 빈자리는 최상위 부호 비트와 같은값으로, 넘치는건 버림
		int num2 = -8;
		int result3 = num2 >> 3;
		int result4 = num2 / (int) Math.pow(2, 3);
		System.out.println("result3: " + result3);
		System.out.println("result4: " + result4);
	}
}
