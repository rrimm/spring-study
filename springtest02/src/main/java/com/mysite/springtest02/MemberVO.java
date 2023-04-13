package com.mysite.springtest02;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data	// get/set 메서드, default 생성자, 모든 arguement 생성자(equals, toString 등) 등을 자동으로 생성
@Getter
@Setter
//@NoArgsConstructor
//@RequiredArgsConstructor	// final필드, @NonNull 인 필드값만 파라미터를 받는 생성자 구현
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class MemberVO {
private String id;
private String pw;
private String email;

}
