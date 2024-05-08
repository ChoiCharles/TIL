package ch02.sec06;

public class StringExample {
	public static void main(String[] args) {
		/*
		 * 작은 따옴표로 감싼 한 개의 문자는 char타입으로 유니코드로 변환되지만
		 * 큰 따옴표로 감싼 여러 개의 문자들은 유니코드로 변환되지 않는다
		 * 따라서, 큰 따옴표로 감싸인 문자열의 경우 String타입을 지정해야 한다
		 * 여기서 String은 자바 기본타입이 아닌 참조 타입이다
		 */
		
		/*
		 * 이스케이프 문자
		 * 
		 * \" : "(큰 따옴표)문자 포함
		 * \' : '(작은 따옴표)문자 포함
		 * \\ : \(역슬래쉬)문자 포함
		 * \(u16진수) : 16진수 유니코드에 해당하는 문자 포함
		 * \t : 출력 시 탭만큼 띄움
		 * \n : 출력 시 줄바꿈
		 * \r : 출력 시 캐리지 리턴
		 * 
		 */
		
		String name = "홈길동";
		String job = "프로그래머";
		System.out.println(name);
		System.out.println(job);
		
		String str = "나는 \"자바\" 를 배웁니다.";
		System.out.println(str);
		
		str = "번호\t이름\t직업 ";
		System.out.println(str);
		
		System.out.print("나는\n");
		System.out.print("자바를\n");
		System.out.print("배웁니다.");
	}
}
