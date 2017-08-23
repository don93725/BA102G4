<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.annew.model.*"%>
<%
AnnewVO annewVO = (AnnewVO) request.getAttribute("annewVO");
%>

<html>
<head>
<title>公告消息新增</title></head>

<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>公告消息新增</h3>
		<a href="selectAnnew_page.jsp">回首頁</a>
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

<FORM METHOD="post" ACTION="annew.do" name="form1" enctype="multipart/form-data">
<table border="2" bgcolor="#FFB01C" style="border-color:#0000FF;">

	<tr>
		<td>公告標題:</td>
		<td><input type="TEXT" name="ann_title" size="45" style="background-color:#C9FFC9;"
			value="<%= (annewVO==null)? "" : annewVO.getAnn_title()%>" /></td>
	</tr>
	
	<tr>
		<td>公告內文:</td>
		<td align='center'><textarea name= "ann_ctx" rows="20" cols="40" style="resize:none;border:2px #00C700 dashed;background-color:#C9FFC9;"></textarea></td>
	</tr>
	
	<tr>
		<td>公告圖片:</td>
		<td><input type="file" name="ann_photo"/></td>
	</tr>
	
<!-- 	<tr> -->
<!-- 		<td>附件檔案:</td> -->
<!-- 		<td><input type="file" name="att_no"/></td> -->
<!-- 	</tr> -->

</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" style="width:80px;height:40px;font-size:15px;"></FORM>
</body>

</html>
