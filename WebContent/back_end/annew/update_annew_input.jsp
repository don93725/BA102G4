<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.annew.model.*"%>
<%
	AnnewVO annewVO = (AnnewVO) request.getAttribute("annewVO"); //AnnewServlet.java (Controller), 存入req的annewVO物件 (包括幫忙取出的annewVO, 也包括輸入資料錯誤時的annewVO物件)
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
			<a href="selectAnnew_page.jsp">首頁</a>
			<h1>
				公告消息修改 <small> <i class="icon-double-angle-right"></i>
						Announces modify
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
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
<table class='table'>
	<tbody>	
	<tr>
		<td>公告標題:</td>
		<td><input class='form-control' type="TEXT" name="ann_title" size="45" style="background-color:#C9FFC9;" value="<%=annewVO.getAnn_title()%>" /></td>
	</tr>
	
		
	<tr>
		<td>公告內文:</td>
		<td><textarea class='form-control' name= "ann_ctx" rows="20" cols="40" style="resize:none;background-color:#C9FFC9;"><%=annewVO.getAnn_ctx()%>
		</textarea></td>
	</tr>
	
	<tr>
		<td>公告圖片:</td>
		<td><img id='pic' height=100 src='${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annewVO.ann_no}'/>
		<br><input class='btn btn-info' type='button' value='修改圖片' onclick='upload();'>
		<input type="file" id='file' name="ann_photo" style='display:none;'/>
		</td>
	</tr>
	<tr>
	<td colspan='2' align='center'><input type="hidden" name="action" value="update">
		<input type="hidden" name="ann_no" value="<%=annewVO.getAnn_no()%>">
		<input class='btn btn-primary' type="submit" value="確認修改" >
		<input class='btn btn-danger' type="reset" value="重置修改">
	</td>
	</tr>
	
	
	</tbody>
</table>
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