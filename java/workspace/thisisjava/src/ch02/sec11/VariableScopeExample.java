package ch02.sec11;

public class VariableScopeExample {
	public static void main(String[] args) {
		// 조건문 반복문 등에서 중괄호 블록 사용가능
		// 이때, 중괄호 블록 내에서 선언된 변수는 중괄호 블록 밖에서 사용 불가능
		int v1 = 15;
		if (v1 > 10) {
			int v2 = v1 - 10;
		}
		
		// 아래 코드에서 v2는 조건문 블록 내에서 선언됬기 때문에 사용할 수 없어 컴파일 에러 발생
		// int v3 = v1 + v2 + 5;
	}
}
