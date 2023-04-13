package com.mysite.reactboardsession.member;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	private MemberMapper mapper;
	
	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public int login(MemberVO member) {
		int res = mapper.login(member);
		return res;// id와 pw가 일치하는 행수를 반환
	}

	@Override
	public int insertMember(MemberVO member) {
		int res = mapper.insertMember(member);
		return res; // 삽입한 행수를 반환
	}

}
