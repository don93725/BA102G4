<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="<%=request.getContextPath()%>/style/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/style/js/bootstrap.min.js"></script>
<link
	href="<%=request.getContextPath()%>/style/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form style="margin:2em;position: absolute;left: 15%;top:3%;" method="post">
		<img src="<%=request.getContextPath()%>/front_end/SCM/images/666.jpg" witdh="130" height="130"><font size="50px" style="font-weight:bold;position: relative;top: 35px;">付費成功</font><br>
		<font size="5px" style="font-weight:bold;">課程名稱 :</font> <font size="3px" style="font-weight:bold;">${course_listVO.courseVO.crs_name}</font><br>
		<span style="position: relative;top: 3px;"><font size="4px" style="font-weight:bold;">付款金額 :</font> <font size="3px" style="font-weight:bold;">$${course_listVO.course_timeVO.price}</font></span><br>
		<input type="button" class="btn btn-danger" onclick="window.close()" style="position: relative;top: 10px;" value="確認">
	</form>
</body>
</html>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>