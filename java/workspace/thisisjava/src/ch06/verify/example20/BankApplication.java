package ch06.verify.example20;

import java.util.Scanner;

public class BankApplication {
	private static Account[] accountArray = new Account[100];
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) {
		boolean run = true;
		
		while(run) {
			System.out.println("---------------------------------------------");
			System.out.println("1.계좌생성 | 2.계좌목록 | 3.예금 | 4.출금 | 5.종료");
			System.out.println("---------------------------------------------");
			System.out.print("선택> ");
			int flag = Integer.parseInt(scanner.nextLine());
			
			if (flag == 1) {
				createAccount();
			} else if (flag == 2) {
				accountList();
			} else if (flag == 3) {
				addBalance();
			} else if (flag == 4) {
				oddBalance();
			} else if (flag == 5) {
				run = false;
			}
		}
		System.out.println("프로그램 종료");
	}
	
	private static void createAccount() {
		System.out.println("----------");
		System.out.println("계좌생성");
		System.out.println("----------");
		System.out.print("계좌번호: ");
		String ano = scanner.nextLine();
		System.out.print("계좌주: ");
		String name = scanner.nextLine();
		System.out.print("초기입금액: ");
		int balance = Integer.parseInt(scanner.nextLine());
		
		Account newAccount = new Account(ano, name, balance);
		for(int i = 0; i < accountArray.length; i++) {
			if(accountArray[i] == null) {
				accountArray[i] = newAccount;
				break;
			}
		}
	}
	
	private static void accountList() {
		System.out.println("----------");
		System.out.println("계좌목록");
		System.out.println("----------");
		
		for(int i = 0; i < accountArray.length; i++) {
			if(accountArray[i] == null) {
				break;
			}
			System.out.println(accountArray[i].getAno() + "    " + accountArray[i].getName() + "    " + accountArray[i].getBalance());
		}
	}
	
	private static void addBalance() {
		System.out.println("----------");
		System.out.println("예금");
		System.out.println("----------");
		System.out.print("계좌번호: ");
		String inputAno = scanner.nextLine();
		for(int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] == null) {
				break;
			} else if (inputAno.equals(accountArray[i].getAno())) {
				System.out.print("예금액: ");
				int newBalance = Integer.parseInt(scanner.nextLine());
				accountArray[i].setBalance(newBalance + accountArray[i].getBalance());
				break;
			}
		}
	}
	
	private static void oddBalance() {
		System.out.println("----------");
		System.out.println("출금");
		System.out.println("----------");
		System.out.print("계좌번호: ");
		String inputAno = scanner.nextLine();
		for(int i = 0; i < accountArray.length; i++) {
			if (accountArray[i] == null) {
				break;
			} else if (inputAno.equals(accountArray[i].getAno())) {
				System.out.print("출금액: ");
				int newBalance = Integer.parseInt(scanner.nextLine());
				accountArray[i].setBalance(accountArray[i].getBalance() - newBalance);
				System.out.println("결과: 출금이 성공되었습니다.");
				break;
			}
		}
	}
}
