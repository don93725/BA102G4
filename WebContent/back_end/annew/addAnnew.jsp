<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.annew.model.*"%>
<%
AnnewVO annewVO = (AnnewVO) request.getAttribute("annewVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<title>健貨後台管理系統</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>

	<div class="page-content">
		<div class="page-header">
			<h1>
				公告消息新增 <small> <i class="icon-double-angle-right"></i>
						Announces maker <i class="icon-double-angle-right"></i>  <a href="selectAnnew_page.jsp">回首頁</a>
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
	<h3>資料:</h3>
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

<FORM METHOD="post" ACTION="annew.do" name="form1" enctype="multipart/form-data">
<table class="table">
	<tbody>
	<tr>
		<td>公告標題:</td>
		<td><input class='form-control' type="TEXT" name="ann_title" size="45" style="background-color:#C9FFC9;"
			value="<%= (annewVO==null)? "" : annewVO.getAnn_title()%>" /></td>
	</tr>
	
	<tr>
		<td>公告內文:</td>
		<td><textarea class='form-control' name= "ann_ctx" rows="20" cols="40" style="resize:none;border:2px #00C700 dashed;background-color:#C9FFC9;"></textarea></td>
	</tr>
	
	<tr>
		<td>公告圖片:</td>
		<td>
		<img id='pic' src=''>
		<input class='btn btn-info' type="button" onclick='upload();' value='上傳圖片'/></td>
		<input  id='file' type="file" name="ann_photo" style='display:none;'/>
	</tr>
	
	<tr align='center'>
		<td colspan='2'>
		<input type="hidden" name="action" value="insert">
		<input type="submit" class='btn btn-primary' value="送出新增" >
		<input type="reset" class='btn btn-danger' value="重填" >
		</td>
	</tr>
</tbody>
</table>
<br>
</FORM>
	</div>
</div>



<script type="text/javascript">
window.onload = init;
function init(){
	Preview.file_change();
}
function upload(){
	$('#file').trigger('click');
}

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
						$('#pic').css('display',"block");
						$('#pic').css('height',"200px");
					}
					if (file) {
						reader.readAsDataURL(file);
					}
				}
			});
	}

}
</script>
		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>


