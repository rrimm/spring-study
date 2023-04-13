<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="com.mysite.springmybatis.MemberVO"%>
<%
MemberVO vo = (MemberVO) request.getAttribute("memberVO");
%>
<html>
<head>
<title>회원관리 시스템 관리자모드(회원 정보 수정)</title>
</head>
<script language="javascript">
	function res() {
		location.href = "memberlist.me";
	}

	function update() {
		location.href = "updateform.me.submit";
	}
</script>
<body>
	<center>
		<form name="updateform" action="update.me" method="post">
			<table border=1>
				<tr align=center>
					<td>아이디</td>
					<td>이름</td>
					<td>이메일</td>
					<td>전화번호</td>
					<td></td>
				</tr>
				<tr align=center>
					<td><input type="hidden" name="id" value=<%=vo.getId()%> /></td>
					<td><input type="text" name="name" value=<%=vo.getName()%> /></td>
					<td><input type="text" name="email" size=30
						value=<%=vo.getEmail()%> /></td>
					<td><input type="text" name="phone" size=30
						value=<%=vo.getPhone()%> /></td>
					<td colspan="2"><a href="javascript:updateform.submit()">수정</a>&nbsp;&nbsp;
						<a href="memberlist.me">리스트</a></td>
				</tr>
			</table>
	</center>
</body>
</html>