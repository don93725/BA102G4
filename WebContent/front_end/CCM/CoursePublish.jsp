<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
</head>
<body>
	<h1>課程上架</h1>
	<form action="<%=request.getContextPath()%>/CCM/CourseManager.do"
		type="post">
		<h3>課程名稱: ${crs_name}</h3>

		<h3>
			課程內容簡述:<br> <br> ${details}
		</h3>
		<br>
		<h3>課程圖片預覽:</h3><br>
			<c:forEach var="course_pictureVO" items="${cpicList}">
				<img src="${course_pictureVO.crs_base}" width="170" height="170" style="margin: 1em;"> 
			</c:forEach>
		<br><br>
		<input type="button" class="btn btn-info" name="send" id="add" value="新增日期"> <input type="button" name="send" class="btn btn-danger" id="delete" value="刪除日期">
		<br>
		報名截止日期:<input type="text" name="deadline" class="deadline" id="start" required="required"><br> 
		開課日期:<input type="text" name="date" class="date" id="end" required="required">&nbsp
		時段:<select name="time0" class="time0" id="">
                                <option value="1">08:00-09:30</option>
                                <option value="2">10:00-11:30</option>
                                <option value="3">13:00-14:30</option>
                                <option value="4">15:00-16:30</option>
                                <option value="5">18:00-19:30</option>     
                                <option value="6">20:00-21:30</option>            
                            </select><br> 
		開課人數:<input type="text" name="class_num" pattern="^([1-9][0-9]){1,2}$" title="只能輸入1-99" minlength="1" maxlength="2" required="required"> &nbsp&nbsp
		人數上限:<input type="text" name="limit" pattern="^([1-9][0-9]){1,2}$" title="只能輸入1-99" minlength="1" maxlength="2" required="required"><br>
		價錢:<input type="text" name="price" pattern="^([1-9][0-9]){1,6}$" minlength="1" maxlength="6" required="required">&nbsp&nbsp&nbsp&nbsp
		<span id="place">
		場地:<select name="p_no">
				<option value="null">無
				<c:forEach var="place_timeVO" items="${ptList}">
					<option value="${place_timeVO.p_no}">${place_timeVO.placeVO.p_name}
				</c:forEach>
		    </select>
		</span><br><br>
		<div id="addtime"></div>
		 <br>
		<input type="submit" class="btn btn-primary" name="send" value="送出"> <input type="reset" class="btn btn-warning" name="reset" value="重新填寫">
		<input type="hidden" name="action" value="course_timeInsert"> 
		<input type="hidden" name="count" id="count" value="0">
		<input type="hidden" name="crs_no" value="${crs_no}">
		<input type="hidden" name="category" value="${category}">
		<input type="hidden" name="action" value="course_timeInsert">
	</form>
</body>
</html>
<script>
$(function(){
	  $('#end').datepicker();
	  $('#start').datepicker({
	    minDate: +3,
	    onSelect: function (dat, inst) {
	      $('#end').datepicker('option', 'minDate', dat);
	    }
	  });
	  $.datepicker.setDefaults({ dateFormat: 'yy-mm-dd' }); 
	});
</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/CCM/JS/AddDate.js"></script>
