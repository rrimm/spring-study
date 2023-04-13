package com.mysite.springtest01;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody
//@Controller	// memberController라는 빈 객체가 자동으로 생성되고 이는 spring 프레임워크가 관리
@RestController
public class MemberController {

//@GetMapping("/getMember")
@RequestMapping(value="/getMember", method=RequestMethod.GET)
public MemberVO getMember1(MemberVO member) {	// member 객체는 의존성 주입(DI)으로 전달됨
//	member.setNum(1);
//	member.setName("Kim");
//	member.setAge(27);
	
	return member;
}

//@PostMapping("/postMember1")
@RequestMapping(value="/postMember1", method=RequestMethod.POST)
public MemberVO postMember1(MemberVO member) {	// member 객체는 의존성 주입(DI)으로 전달됨
	System.out.println("member.getNum() : "+ member.getNum());
	System.out.println("member.getName() : "+ member.getName());
	System.out.println("member.getAge() : "+ member.getAge());
	return member;
}

}
