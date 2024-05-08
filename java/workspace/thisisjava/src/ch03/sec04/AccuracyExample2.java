package ch03.sec04;

public class AccuracyExample2 {
	public static void main(String[] args) {
		// 정확한 계산을 위해 정수 연산을 해야 한다
		int apple = 1;
		int totalPieces = apple * 10;
		int number = 7;
		
		int result = totalPieces - number;
		System.out.println("10조각에서 남은 조각: " + result);
		System.out.println("사과 1개에서 남은 양: " + result/10.0);
	}
}
