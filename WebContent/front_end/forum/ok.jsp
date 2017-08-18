<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>msg</title>
</head>
<body>
<h1><%=request.getAttribute("msg")%></h1>

<a href="${pageContext.request.contextPath}/forum/ForumCtrl">返回討論區大廳</a>
</body>
</html>