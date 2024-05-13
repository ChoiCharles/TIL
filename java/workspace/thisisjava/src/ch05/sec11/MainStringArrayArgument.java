package ch05.sec11;

public class MainStringArrayArgument {
	public static void main(String[] args) {
		// 이클립스 기준
		// 상단 Run -> Run Configurations
		// Main class 확인 -> Arguments 탭 선택 -> Arguments 에 값 입력 후 실행
		
		if (args.length != 2) {
			System.out.println("프로그램 입력값 부족");
			System.exit(0);
		}
		
		String strNum1 = args[0];
		String strNum2 = args[1];
		
		int num1 = Integer.parseInt(strNum1);
		int num2 = Integer.parseInt(strNum2);
		
		int result = num1 + num2;
		System.out.println(num1 + " + " + num2 + " = " + result);
	}
}
