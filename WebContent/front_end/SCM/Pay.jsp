<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="<%=request.getContextPath()%>/style/css/bootstrap.min.css"
	rel="stylesheet">
<script src="<%=request.getContextPath()%>/style/js/bootstrap.min.js"></script>
<link
	href="<%=request.getContextPath()%>/style/assets/css/bootstrap.min.css"
	rel="stylesheet" />
<%!String stu_acc1; String stu_acc; %>
<% stu_acc1 = request.getParameter("stu_acc"); %>
<% stu_acc = new String( stu_acc1.getBytes("ISO-8859-1"),"UTF-8"); %>
<style>
        .demo-container {
            width: 100%;
            max-width: 350px;
            margin: 50px auto;
        }
        form {
            margin: 30px;
        }
        input {
            width: 200px;
            margin: 10px auto;
            display: block;
        }
    </style>
</head>
<body>
<div class="demo-container">
<div class='card-wrapper'></div>
<!-- CSS is included via this JavaScript file -->
<div class="form-container active">
<form method="post" action="<%=request.getContextPath()%>/SCM/StudentsCourseManager.do">
    <input placeholder="**** **** **** ****" type="tel" name="number">
    <input placeholder="Full name" type="text" name="name">
    <input placeholder="MM/YY" type="tel" name="expiry">
    <input placeholder="CVC" type="number" name="cvc">
    <input type="submit" class="btn btn-warning" value="送出" style="margin-left:3em;border-radius:0px;">
<input type="hidden" name="ct_no" value="<%= request.getParameter("ct_no") %>">
<input type="hidden" name="stu_acc" value="<%= stu_acc %>">
<input type="hidden" name="action" value="pay">
</form>
</div>
</div>
</body>
</html>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front_end/SCM/JS/card/card.css" />
<script src="<%=request.getContextPath()%>/front_end/SCM/JS/card/card.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>

<script>

new Card({
    form: document.querySelector('form'),
    container: '.card-wrapper'
});
</script>