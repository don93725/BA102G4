<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<link href="<%= request.getContextPath() %>/style/css/bootstrap.css" rel="stylesheet">
	
<style>

        form {
            margin: 30px;
        }
        input {
            width: 200px;
            margin: 10px auto;
            display: block;
        }
    </style>
<% String stu_acc1 = request.getParameter("stu_acc"); %>
<% String stu_acc = new String( stu_acc1.getBytes("ISO-8859-1"),"UTF-8"); %>
</head>
<body>
<div class="col-sm-3">
	<form action="<%=request.getContextPath()%>/SCM/StudentsCourseManager.do" method="post">
		<div class="form-group " >
		  <label for="sel1">教練評價 <span id="stars"><i class="glyphicon glyphicon-star" style="font-size:14px;"></i></span>:</label>
		  <select class="form-control" name="evaluation_coa" onchange="showStars()">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		  </select>
		</div>
		<div class="form-group " >
		  <label for="sel1">課程評價 <span id="starsCrs"><i class="glyphicon glyphicon-star" style="font-size:14px;"></i></span>:</label>
		  <select class="form-control" name="evaluation_crs" onchange="showStarsCrs()">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		  </select>
		</div>
		<div class="form-group">
		  <label for="comment">課程回饋:</label>
		  <textarea class="form-control" rows="5" id="comment" name="feedback"></textarea>
		</div>
		<div class="form-group">
		  <input type="submit" class="form-control btn btn-warning" value="送出">
		</div>
		<input type="hidden" name="ct_no" value="<%= request.getParameter("ct_no") %>">
		<input type="hidden" name="stu_acc" value="<%= stu_acc %>">
		<input type="hidden" name="action" value="evaluation">
	</form>
</div>
</body>
</html>
<script src="<%= request.getContextPath() %>/style/js/jquery.js"></script>
<script src="<%=request.getContextPath()%>/style/js/bootstrap.min.js"></script>
<script>
	function showStars(){
		var stars = "";
		for(var i=0;i<parseInt($("select[name='evaluation_coa']").val());i++){
			stars = stars + '<i class="glyphicon glyphicon-star" style="font-size:14px;"></i>';
		}
		$("#stars").html(stars);
	}
	
	function showStarsCrs(){
		var stars = "";
		for(var i=0;i<parseInt($("select[name='evaluation_crs']").val());i++){
			stars = stars + '<i class="glyphicon glyphicon-star" style="font-size:14px;"></i>';
		}
		$("#starsCrs").html(stars);
	}
</script>