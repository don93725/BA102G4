<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fitkw.model.*"%>
<%
	FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO"); //EmpServlet.java (Concroller), 存入req的fitkwVO物件 (包括幫忙取出的fitkwVO, 也包括輸入資料錯誤時的fitkwVO物件)
%>
<html>
<head>
<title>健身知識修改</title></head>



<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>健身知識修改</h3>
		<a href="selectFitkw_page.jsp">回首頁</a></td>
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

<FORM METHOD="post" ACTION="fitkw.do" name="form1" enctype='multipart/form-data'>
<table border="2" bgcolor="#FFB01C" style="border-color:#0000FF;">
<!-- 	<tr> -->
<!-- 		<td>知識編號:<font color=red><b></b></font></td> -->
<%-- 		<td><%=fitkwVO.getFik_no()%></td> --%>
<!-- 	</tr> -->
	
	<tr>
		<td>知識標題:</td>
		<td><input type="TEXT" name="fik_title" size="45" style="background-color:#C9FFC9;" value="<%=fitkwVO.getFik_title()%>" /></td>
	</tr>
	
	<jsp:useBean id="fitkwSvc" scope="page" class="com.fitkw.model.FitkwService" />
	<tr>
		<td>知識主題:<font color=red><b></b></font></td>
		<td><select size="1" name="fik_type" style="background-color:#8CFFFF;">
		  <option value="基礎知識"<%if("基礎知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>基礎知識</option> 
		  <option value="增肌知識"<%if("增肌知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>增肌知識</option> 
		  <option value="其他知識"<%if("其他知識".equals(fitkwVO.getFik_type())){
		  out.print("selected");}%>>其他知識</option> 
		  
		
		</select>
		</td>
	</tr>
	
	<tr>
		<td>知識內文:</td>
<%-- 		<td><input type="TEXT" name="fik_ctx" size="45"	value="<%=fitkwVO.getFik_ctx()%>" /></td> --%>
			<td align='center'><textarea name= "fik_ctx" rows="20" cols="40" style="resize:none;border:2px #00C700 dashed;background-color:#C9FFC9;"><%=fitkwVO.getFik_ctx()%>
			</textarea></td>

	</tr>
	
	<tr rospan='2'>
		<td>知識圖片:</td>
		<td><img id='pic' height=100 src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}'/><br><input type="file" id='file' name="fik_photo"/></td>
			
	</tr>

	

</table>
<br>
<input type="hidden" name="action" value="update">
<input type="hidden" name="fik_no" value="<%=fitkwVO.getFik_no()%>">
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
