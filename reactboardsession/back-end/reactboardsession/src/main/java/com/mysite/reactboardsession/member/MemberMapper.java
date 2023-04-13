package com.mysite.reactboardsession.member;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface MemberMapper {
	
	@Select("select count(*) as 'cnt' from member where id=#{id} and pw=#{pw}")
	public int login(MemberVO member);
	
	@Insert("insert into member values (#{id}, #{pw}, #{email})")
	public int insertMember(MemberVO member);

}
