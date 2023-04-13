package com.mysite.springmybatis;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MemberVO {
	private String id;
	private String name;
	private String email;
	private String phone;
}
