package ch03.sec04;

public class AccuracyExample {
	public static void main(String[] args) {
		// 부동 소수점 방식으로 인해 0.3으로 맞아 떨어지지 않는다
		int apple = 1;
		double pieceUnit = 0.1;
		int number = 7;
		
		double result = apple - number * pieceUnit;
		
		System.out.println("사과 1개에서 남은 양: " + result);
	}
}
