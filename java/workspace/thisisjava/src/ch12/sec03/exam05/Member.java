package ch12.sec03.exam05;

import lombok.Data;

@Data
public class Member {
	private String id;
	private String name;
	private int age;
}

// 모든 필드를 초기화하는 생성자 포함
/*
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	private String id;
	private String name;
	private int age;
}
*/

// final필드는 setter가 만들어지지 않지만
// @NonNull은 null이 아닌 값으로 setter를 통해 변경할 수 있다
/*
import lombok.Data;
import lombok.NonNull;

@Data
public class Member {
	private final String id;
	@NonNull private String name;
	private int age;
}
*/