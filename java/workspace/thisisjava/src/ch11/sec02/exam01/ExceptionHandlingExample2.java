package ch11.sec02.exam01;

public class ExceptionHandlingExample2 {
	public static void printLength(String data) {
		try {
			int result = data.length();
			System.out.println("문자 수: " + result);
		} catch (NullPointerException e) {
			// 예외가 발생한 이유만
			System.out.println(e.getMessage());
			
			// 예외의 종류도
			//System.out.println(e.toString());
			
			// 예외가 어디서 발생했는지 까지
			//e.printStackTrace();
		} finally {
			System.out.println("[마무리 실행]\n");
		}
	}
	
	public static void main(String[] args) {
		System.out.println("[프로그램 시작]\n");
		printLength("ThisIsJava");
		printLength(null);
		System.out.println("[프로그램 종료]");
	}
}
