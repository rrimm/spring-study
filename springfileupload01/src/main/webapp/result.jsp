<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mysite.springfileupload01.*"%>
<%
List<FileVO> filelist = (List<FileVO>) request.getAttribute("list");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>upload file 목록</h1>
	<%
	for (int i = 0; i < filelist.size(); i++) {
		FileVO obj = (FileVO)filelist.get(i);
	%>
	<li><%=obj.getOriginfilename()%> 
	<a href="/download?originfilename=<%=obj.getOriginfilename() %>&storedfilename=<%=obj.getStoredfilename()%>">[다운로드]</a></li>
		<%
		}
		%>
</body>
</html>