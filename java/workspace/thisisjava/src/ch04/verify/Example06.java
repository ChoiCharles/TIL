package ch04.verify;

public class Example06 {
	public static void main(String[] args) {
		for (int i = 1; i <= 5; i++) {
			String str = "*";
			String repeat = str.repeat(i);
			System.out.println(repeat);
		}
	}
}
