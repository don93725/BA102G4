<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
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
</head>
<body>
<div class="col-sm-3">
	<form action="<%=request.getContextPath()%>/CPM/CoachesPlaceManager.do" method="post">
		<div class="form-group " >

		<div class="form-group " >
		  <label for="sel1">場地評價 <span id="starsCrs"><i class="glyphicon glyphicon-star" style="font-size:14px;"></i></span>:</label>
		  <select class="form-control" name="eva" onchange="showStarsCrs()">
		    <option value="1">1</option>
		    <option value="2">2</option>
		    <option value="3">3</option>
		    <option value="4">4</option>
		    <option value="5">5</option>
		  </select>
		</div>
		<div class="form-group">
		  <label for="comment">場地回饋:</label>
		  <textarea class="form-control" rows="5" id="comment" name="eva_ct"></textarea>
		</div>
		<div class="form-group">
		  <input type="submit" class="form-control btn btn-warning" value="送出">
		</div>
		<input type="hidden" name="pt_no" value="<%= request.getParameter("pt_no") %>">
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