package ch04.verify;

import java.util.Scanner;

public class Example07 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		
		boolean flag = true;
		String inputString;
		int totalCash = 0;
		String cash;
		
		while (flag) {
			System.out.println("------------------------");
			System.out.println("1. 예금 | 2. 출금 | 3. 잔고 | 4. 종료");
			System.out.println("------------------------");
			System.out.print("선택> ");
			inputString = scanner.nextLine();
			
			if (inputString.equals("1")) {
				System.out.print("예금액> ");
				cash = scanner.nextLine();
				totalCash += Integer.parseInt(cash);
				System.out.println(" ");
			} else if (inputString.equals("2")) {
				System.out.print("출금액> ");
				cash = scanner.nextLine();
				totalCash -= Integer.parseInt(cash);
				System.out.println(" ");
			} else if (inputString.equals("3")) {
				System.out.println("잔고> " + totalCash);
			} else if (inputString.equals("4")) {
				System.out.println("프로그램 종료");
				flag = false;
			} else {
				System.out.println(inputString);
			}
			
		}
	}
}
