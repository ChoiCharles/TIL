package ch05.sec06;

public class ArrayCreateByNewExample {
	public static void main(String[] args) {
		// new 타입[]에서 대괄호 안에 숫자를 넣어 배열의 길이를 미리 지정해놓을 수 있다
		int[] arr1 = new int[3];
		for (int i = 0; i < 3; i++) {
			System.out.print("arr1[" + i + "] : " + arr1[i] + ", ");
		}
		System.out.println();
		
		arr1[0] = 10;
		arr1[1] = 20;
		arr1[2] = 30;
		for (int i = 1; i < 3; i++) {
			System.out.print("arr1[" + i + "] : " + arr1[i] + ", ");
		}
		System.out.println("\n");
		
		double[] arr2 = new double[3];
		for (int i = 0; i < 3; i++) {
			System.out.print("arr2[" + i + "] : " + arr2[i] + ", ");
		}
		System.out.println();
		
		arr2[0] = 0.1;
		arr2[1] = 0.2;
		arr2[2] = 0.3;
		for (int i = 0; i < 3; i++) {
			System.out.print("arr2[" + i + "] : " + arr2[i] + ", ");
		}
		System.out.println("\n");
		
		String[] arr3 = new String[3];
		for (int i = 0; i < 3; i++) {
			System.out.print("arr3[" + i + "] : " + arr3[i] + ", ");
		}
		System.out.println();
		
		arr3[0] = "1월";
		arr3[1] = "2월";
		arr3[2] = "3월";
		for (int i = 0; i < 3; i++) {
			System.out.print("arr3[" + i + "] : " + arr3[i] + ", ");
		}
		System.out.println();
	}
}
