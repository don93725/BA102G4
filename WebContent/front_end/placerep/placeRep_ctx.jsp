<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.place_report.model.*"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script type="Text/JavaScript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
<title>placeRep_ctx </title>
</head>
<body>
<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>檢舉原因 - placeRep_ctx.jsp</h3>
				<h3>${user.mem_nickname},你好!!</h3>
				<h3>${placeReportVO.p_no}</h3>
			</td>
		</tr>
		
</table>
<form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl" name="form1" enctype="multipart/form-data">
<table border="0">

	<tr>
		<td>檢舉原因:<font color=red><b>*</b></font></td>
<%-- 		<td><input type="TEXT" name="pr_ctx" size="45" value="${param.pr_ctx}" /></td><td>${errorMsgs.pr_ctx}</td> --%>
		
		<td><textarea name="pr_ctx" rows="4" cols="50"></textarea> </td>
	</tr>
	
	
<!-- 	<tr> -->
<!-- 		<td>被檢舉內容:<font color=red><b>*</b></font></td> -->
<!-- 		<td><input type="hidden" name="ref_ctx" size="45" -->
<%-- 			value="${param.ref_ctx}" /></td><td>${errorMsgs.pr_ctx}</td> --%>
<!-- 	</tr> -->
	
	<tr>
		<td>圖片:</td>
		<td>
			<input type="file" name="pr_pt" id="file" /><br>
			<img  id="img"  style="width:150px;height:150px;">
		</td>
	</tr>	
</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="hidden" name="p_no" value="${placeReportVO.p_no}">
<input type="submit" value="送出新增">
</form>
<script type="text/javascript">
$(function() {
	$("#file").change(function() {
		if (this.files && this.files[0]) {
			var reader = new FileReader();

			reader.onload = function(e) {
				$('#img').attr('src', e.target.result);
			}
			reader.readAsDataURL(this.files[0]);
		}
	});
});
</script>

</body>
</html>