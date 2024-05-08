package ch02.sec06;

public class TextBlockExample {
	public static void main(String[] args) {
		// java 13 부터 텍스트 블록 문법이 제공된다
		
		String str1 = "" +
		"{\n" +
		"\t\"id\":\"winter\",\n" +
		"\t\"name\":\"눈송이\"\n" +
		"}";
		
		String str2 = """
				{
					"id":"winter",
					"name":"눈송이"
				}
				""";
		System.out.println(str1);
		System.out.println("-------------------------------------");
		System.out.println(str2);
	}
}
