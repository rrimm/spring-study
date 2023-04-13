package com.mysite.springtest02;

import java.util.ArrayList;

import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements MemberService {

	// 생성자 방식
	MemberMapper mapper;

	public MemberServiceImpl(MemberMapper mapper) { // 생성자
		this.mapper = mapper;
	}

	// 필드 방식
//	@Autowired(required=false) //에러가 발생하지 않도록 할 때 설정: required=false
//	MemberMapper mapper;

	// setter 이용 방식
//	MemberMapper mapper;
//	@Autowired
//	public void setMapper(MemberMapper mapper) {
//		this.mapper = mapper;
//	}

	@Override
	public ArrayList<MemberVO> getMembers() {
		ArrayList<MemberVO> memberlist = mapper.getMembers();
		return memberlist;
	}

	@Override
	public int insertMember(MemberVO member) {
		int res = mapper.insertMember(member);
		return res;
	}

	@Override
	public MemberVO selectMember(String id) {
		MemberVO vo = mapper.selectMember(id);
		return vo;
	}

	public int updateMember(MemberVO member) {
		int res = mapper.updateMember(member);
		return res;
	}
	
	public int deleteMember(String id) {
		int res = mapper.deleteMember(id);
		return res;
	}
	
	public int countMember() {
		int res=mapper.countMember();
		return res;
	}
	
//	public int updateMember(String id, String pw, String email) {
//		int res = mapper.updateMember(id, pw, email);
//		return res;
//	}
}
