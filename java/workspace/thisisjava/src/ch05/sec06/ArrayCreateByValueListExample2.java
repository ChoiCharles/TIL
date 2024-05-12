package ch05.sec06;

public class ArrayCreateByValueListExample2 {
	public static void main(String[] args) {
		// 배열을 미리 선언한 후 값 목록을 변수에 대입할 수 없다
		// 미리 선언 후 값을 넣고 싶다면, 배열 값 앞에 new 타입[] 을 붙여주면 된다
		int[] scores;
		scores = new int[] {83, 90, 87};
		
		int sum1 = 0;
		for (int i = 0; i < 3; i++) {
			sum1 += scores[i];
		}
		System.out.println("총합 : " + sum1);
		
		// 메서드 호출시 매개변수의 값이 배열일 경우도 마찬가지
		printItem(new int[] {83, 90, 87});
	}
	
	public static void printItem(int[] scores) {
		for (int i = 0; i < 3; i++) {
			System.out.println("scores[" + i + "] : " + scores[i]);
		}
	}
}
