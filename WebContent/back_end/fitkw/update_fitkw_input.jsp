<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fitkw.model.*"%>
<%
	FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO"); //EmpServlet.java (Concroller), 存入req的fitkwVO物件 (包括幫忙取出的fitkwVO, 也包括輸入資料錯誤時的fitkwVO物件)
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
				頁面資訊維護 <small>
				<i class="icon-double-angle-right"></i> <a href='${pageContext.request.contextPath }/back_end/fitkw/selectFitkw_page.jsp'>健身知識 </a>
				<i class="icon-double-angle-right"></i> 修改健身知識
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
		<center><h2>修改健身知識</h2></center>
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

<FORM METHOD="post" ACTION="fitkw.do" name="form1" enctype='multipart/form-data'>
<table class='table' style="text-align:center;">
	<tbody>
	<tr>
		<td class="center" style="vertical-align:middle;">知識標題</td>
		<td class="center" style="vertical-align:middle;"><input class='form-control' type="TEXT" name="fik_title" size="45" value="<%=fitkwVO.getFik_title()%>" /></td>
	</tr>
	
	<jsp:useBean id="fitkwSvc" scope="page" class="com.fitkw.model.FitkwService" />
	<tr>
		<td class="center" style="vertical-align:middle;">知識主題<font color=red><b></b></font></td>
		<td class="center" style="vertical-align:middle;"><select class='form-control'  size="1" name="fik_type">
		  <option value="基礎知識"<%if("基礎知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>基礎知識</option> 
		  <option value="增肌知識"<%if("增肌知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>增肌知識</option> 
		  <option value="跑步知識"<%if("跑步知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>跑步知識</option> 
		  <option value="有氧知識"<%if("有氧知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>有氧知識</option> 
		  <option value="其他知識"<%if("其他知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>其他知識</option>
		  
		
		</select>
		</td>
	</tr>
	
	<tr>
		<td class="center" style="vertical-align:middle;">知識內文</td>
<%-- 		<td><input type="TEXT" name="fik_ctx" size="45"	value="<%=fitkwVO.getFik_ctx()%>" /></td> --%>
			<td><textarea class='form-control'  name= "fik_ctx" rows="20" cols="40" style="resize:none;"><%=fitkwVO.getFik_ctx()%>
			</textarea></td>
	</tr>
	
	<tr>
		<td class="center" style="vertical-align:middle;">知識圖片</td>
		<td align="left"><img id='pic' height=100 src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}'/><input type="file" id='file' name="fik_photo" style='display:none;'/><br>	
		<br><input class='btn btn-default' type="button" onclick='upload();' value='上傳圖片'/></td>		
	</tr>
	
	<tr align='center'>
	<td colspan='2'>
		<input type="hidden" name="action" value="update">
		<input type="hidden" name="fik_no" value="<%=fitkwVO.getFik_no()%>">
		<input class='btn btn-primary' type="submit" value="確認修改">
		<input class='btn btn-danger' type="reset" value="重置修改">
	</td>

	</tbody>
	</table>
	</FORM>
	
	</div>
</div>

		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
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
</html>




<html>
<head>
<title>健身知識修改</title></head>







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
