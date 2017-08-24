<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fitkw.model.*"%>
<%
FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO");
%>

<html>
<head>
<title>健身知識新增</title></head>

<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>健身知識新增</h3>
		<a href="selectFitkw_page.jsp">回首頁</a>
		</td>

	</tr>
</table>

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

<FORM METHOD="post" ACTION="fitkw.do" name="form1" enctype="multipart/form-data">
<table border="2" bgcolor="#FFB01C" style="border-color:#0000FF;">

	<tr>
		<td>知識標題:</td>
		<td><input type="TEXT" name="fik_title" size="45"  style="background-color:#C9FFC9;"
			value="<%= (fitkwVO==null)? "" : fitkwVO.getFik_title()%>" /></td>
	</tr>
	
	<jsp:useBean id="fitkwSvc" scope="page" class="com.fitkw.model.FitkwService" />
	<tr>
		<td>知識主題:<font color=red><b></b></font></td>
		<td><select size="1" name="fik_type" style="background-color:#8CFFFF;">
			<option value="基礎知識">基礎知識</option>
            <option value="增肌知識">增肌知識</option>
            <option value="其他知識">其他知識</option>
		</select></td>
	</tr>
	
	<tr>
		<td>知識內文:</td>
<!-- 		<td><input type="TEXT" name="fik_ctx" size="45" -->
<%-- 			value="<%= (fitkwVO==null)? "" : fitkwVO.getFik_ctx()%>" /></td> --%>
			
			<td align='center'><textarea name= "fik_ctx" rows="20" cols="40" style="resize:none;border:2px #00C700 dashed;background-color:#C9FFC9;"></textarea></td>
			
	</tr>
	
	<tr>
		<td>知識圖片:</td>
		<td><input type="file" name="fik_photo"/></td>
	</tr>

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" style="width:80px;height:40px;font-size:15px;"></FORM>
</body>

</html>
