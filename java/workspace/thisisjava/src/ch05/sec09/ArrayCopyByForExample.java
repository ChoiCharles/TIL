package ch05.sec09;

public class ArrayCopyByForExample {
	public static void main(String[] args) {
		// 배열의 길이는 한번정해지면 바꿀수 없기 때문에 배열 길이를 변경하려면 새로만든 배열에 복사해야한다
		int[] oldIntArray = {1, 2, 3};
		int[] newIntArray = new int[5];
		
		for (int i = 0; i < oldIntArray.length; i++) {
			newIntArray[i] = oldIntArray[i];
		}
		
		for (int i = 0; i < newIntArray.length; i++) {
			System.out.print(newIntArray[i] + ", ");
		}
	}
}
