package ch05.verify;

import java.util.Scanner;

public class Example09 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		boolean flag = true;
		String studentNum = "0";
		int[] scores = new int[1];
		String score;
		
		while (flag) {
			System.out.println("------------------------------");
			System.out.println("1. 학생수 | 2. 점수입력 | 3. 점수리스트 | 4. 분석 | 5. 종료");
			System.out.println("------------------------------");
			System.out.print("선택> ");
			String function = scanner.nextLine();
			
			if (function.equals("1")) {
				System.out.print("학생수> ");
				studentNum = scanner.nextLine();
			} else if (function.equals("2")) {
				int totalStudent = Integer.parseInt(studentNum);
				int[] newScores = new int[totalStudent];
				scores = newScores;
				
				for (int i = 0; i < scores.length; i++) {
					System.out.print("scores[" + i + "]> ");
					score = scanner.nextLine();
					scores[i] = Integer.parseInt(score);
				}
			} else if (function.equals("3")) {
				for (int i = 0; i < scores.length; i++) {
					System.out.println("scores[" + i + "]> " + scores[i]);
				}
			} else if (function.equals("4")) {
				int scoresSum = 0;
				int heightScore = 0;
				for (int i = 0; i < scores.length; i++) {
					scoresSum += scores[i];
					if (heightScore < scores[i]) {
						heightScore = scores[i];
					}
				}
				double scoresAvg = (double) scoresSum / scores.length;
				System.out.println("최고 점수 : " + heightScore);
				System.out.println("평균 점수 : " + scoresAvg);
				
			} else if (function.equals("5")) {
				System.out.println("프로그램 종료");
				flag = false;
			} else {
				System.out.println("다시 입력해주세요");
			}
		}
	}
}
