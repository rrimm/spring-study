package com.mysite.springtest02;

import java.util.ArrayList;

public interface MemberService {
	ArrayList<MemberVO> getMembers();
	int insertMember(MemberVO member);
	public MemberVO selectMember(String id);
//	public int updateMember(String id, String pw, String email);
	public int updateMember(MemberVO member);
	public int deleteMember(String id);
	public int countMember();
}

