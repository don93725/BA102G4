<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>CourseManager</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
</head>
<body>
	<div class="btn-group-vertical list">
		<form method="post"
			action="<%=request.getContextPath()%>/CCM/CourseManager.do">
			<input type="submit" class="btn btn-default" role="button"
				value="課程的管理"> <input type="hidden" name="action"
				value="courseList">
		</form>
		<form method="post"
			action="<%=request.getContextPath()%>/CCM/CourseManager.do">
			<input type="submit" class="btn btn-default" role="button"
				value="上架中課程"> <input type="hidden" name="action"
				value="coursePublishList">
		</form>
		<form method="post"
			action="<%=request.getContextPath()%>/CCM/CourseManager.do">
			<input type="submit" class="btn btn-default" role="button"
				value="開課中課程"> <input type="hidden" name="action"
				value="courseList">
		</form>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>