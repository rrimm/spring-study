package com.mysite.springmember01;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service("memberService")	// service 어노테이션이 사용하는 bean 객체가 만들어질 때 memberService로 생성
public class MemberServiceImpl implements MemberService {

	// 생성자 방식
	MemberMapper mapper;

	public MemberServiceImpl(MemberMapper mapper) { // 생성자
		this.mapper = mapper;
	}

	@Override
	public ArrayList<MemberVO> getMemberList() {
		ArrayList<MemberVO> memberlist = new ArrayList<MemberVO>();
		memberlist = mapper.getMemberList();
		return memberlist;
	}

	@Override
	public int insertMember(MemberVO memberVO) {
		int res = mapper.insertMember(memberVO);
		return res;
	}

	@Override
	public int userCheck(MemberVO memberVO) {
		int res = mapper.userCheck(memberVO);
		return res;
	}
	
	@Override
	public MemberVO selectMember(MemberVO memberVO) {
		MemberVO vo = mapper.selectMember(memberVO);
		return vo;
	}

	@Override
	public int updateMember(MemberVO memberVO) {
		int res = mapper.updateMember(memberVO);
		return res;
	}
	
	@Override
	public int deleteMember(MemberVO memberVO) {
		int res = mapper.deleteMember(memberVO);
		return res;
	}
	
	@Override
	public int countMember() {
		int res=mapper.countMember();
		return res;
	}
	
}
