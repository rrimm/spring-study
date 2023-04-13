<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="javax.sql.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="com.mysite.springmybatis.MemberVO"%>
<%
MemberVO vo = (MemberVO) request.getAttribute("memberVO");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 추가 페이지</title>
</head>
<body>
	<form name="insertform" action="insert.me" method="post">
		<center>
			<table border=1>
				<tr align=center>
					<td><input type="text" name="id" value=<%=vo.getId()%> /></td>
					<td><input type="text" name="name" value=<%=vo.getName()%> /></td>
					<td><input type="text" name="email" value=<%=vo.getEmail()%> /></td>
					<td><input type="text" name="phone" value=<%=vo.getPhone()%> /></td>
					<td colspan="2"><a href="javascript:insertform.submit()">추가</a></td>
				</tr>
			</table>
		</center>
	</form>
</body>
</html>
