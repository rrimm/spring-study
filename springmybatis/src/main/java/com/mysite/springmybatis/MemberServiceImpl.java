package com.mysite.springmybatis;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {

	private MemberMapper mapper;

	public MemberServiceImpl(MemberMapper mapper) {
		this.mapper = mapper;
	}

	@Override
	public ArrayList<MemberVO> getMemberlist() {
		ArrayList<MemberVO> member_list = new ArrayList<MemberVO>();
		member_list = mapper.getMemberlist();
		return member_list;
	}

	@Override
	public MemberVO selectMember(MemberVO memberVO) {
		MemberVO vo = mapper.selectMember(memberVO);
		return vo;
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		mapper.insertMember(memberVO);

	}

	@Override
	public void updateMember(MemberVO memberVO) {
		mapper.updateMember(memberVO);

	}

	@Override
	public void deleteMember(MemberVO memberVO) {
		mapper.deleteMember(memberVO);

	}

}
