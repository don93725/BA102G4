<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.annew.model.*"%>
<%
	AnnewVO annewVO = (AnnewVO) request.getAttribute("annewVO"); //AnnewServlet.java (Controller), 存入req的annewVO物件 (包括幫忙取出的annewVO, 也包括輸入資料錯誤時的annewVO物件)
%>
<html>
<head>
<title>公告消息修改</title></head>



<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>公告消息修改</h3>
		<a href="selectAnnew_page.jsp">回首頁</a></td>
	</tr>
</table>

<h3>資料修改:</h3>
<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="annew.do" name="form1" enctype='multipart/form-data'>
<table border="2" bgcolor="#FFB01C" style="border-color:#0000FF;">
	
	<tr>
		<td>公告標題:</td>
		<td><input type="TEXT" name="ann_title" size="45" style="background-color:#C9FFC9;" value="<%=annewVO.getAnn_title()%>" /></td>
	</tr>
	
		
	<tr>
		<td>公告內文:</td>
		<td align='center'><textarea name= "ann_ctx" rows="20" cols="40" style="resize:none;border:2px #00C700 dashed;background-color:#C9FFC9;"><%=annewVO.getAnn_ctx()%>
		</textarea></td>
	</tr>
	
	<tr rospan='2'>
		<td>公告圖片:</td>
		<td><img id='pic' height=100 src='${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annewVO.ann_no}'/><br><input type="file" id='file' name="ann_photo"/></td>
	</tr>
	
	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="ann_no" value="<%=annewVO.getAnn_no()%>">
<input type="submit" value="確認修改" style="width:80px;height:40px;font-size:15px;"></FORM>

</body>
<script src="https://code.jquery.com/jquery.js"></script>	
<script type="text/javascript">
$(function(){
	Preview.file_change();
})
Preview = new function() {
	var fileInput = $('#file');
	this.file_change = function() {
		$('#file').on('change', function() {
			show(this);
		});
	}
	var show = function(input) {
		if (input.files && input.files[0]) {
			each_img(input.files);
		}
	}			
	var each_img = function(files) {
		$.each(files,function(index, file) {
				if (file.type.match('image')) {
					var reader = new FileReader();				
					reader.onload = function() {
						$('#pic').prop('src',reader.result);
					}
					if (file) {
						reader.readAsDataURL(file);
					}
				}
			});
	}

}
</script>
</html>
