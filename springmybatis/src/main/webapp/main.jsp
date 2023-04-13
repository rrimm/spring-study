<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mysite.springmybatis.MemberVO"%>
<%
ArrayList<MemberVO> member_list = (ArrayList<MemberVO>) request.getAttribute("member_list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원관리 시스템 메인 페이지</title>
</head>
<body>
	<h3>회원 관리 시스템</h3>
	<center>
		<table border=1>
			<tr>
				<td>아이디</td>
				<td>이름</td>
				<td>이메일</td>
				<td>전화번호</td>
				<td><a href="memberlist.me">리스트</a></td>
			</tr>

		</table>
				
	</center>
	
</body>
</html>