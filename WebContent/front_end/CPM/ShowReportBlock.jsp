<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div style="margin:2em;">
		<h3 style="font-weight:bold;"><font color="#e02429">檢舉原因:</font></h3>
		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:16px;font-weight:bold;">${placeReportVO.pr_ctx}</font>
		</div>
		<img alt="" src="<%= request.getContextPath()%>/front_end/CourseDetails/images/label.png" style=" position:absolute;right:0;bottom:0;" width="60" height="60">
	</div>
</body>
</html>