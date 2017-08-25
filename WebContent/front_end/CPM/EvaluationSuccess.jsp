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
		<img src="<%=request.getContextPath()%>/front_end/CPM/images/suc.gif" witdh="130" height="130"><font size="50px" style="font-weight:bold;position: relative;top: 35px;">評價成功</font><br>
		<font size="5px" style="font-weight:bold;">場地名稱 :</font> <font size="3px" style="font-weight:bold;">${place_timeVO.placeVO.p_name}</font><br>
		<span style="position: relative;top: 3px;"><font size="4px" style="font-weight:bold;">評價內容:</font> <font size="3px" style="font-weight:bold;">${place_timeVO.eva_ct}</font></span><br>
		<input type="button" class="btn btn-danger" onclick="window.close()" style="position: relative;top: 10px;" value="確認">
	</form>
</body>
</html>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>