<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.platinf.model.*"%>
<%
PlatinfVO platinfVO = (PlatinfVO) request.getAttribute("platinfVO");
%>

<html>
<head>
<title>平台資訊新增</title></head>

<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>平台資訊新增</h3>
		<a href="selectPlatinf_page.jsp">回首頁</a>
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

<FORM METHOD="post" ACTION="platinf.do" name="form1" enctype="multipart/form-data">
<table border="2" bgcolor="#FFB01C" style="border-color:#0000FF;">

	
	<tr>
		<td>公司地址:</td>
		<td><input type="TEXT" name="com_address" size="45" style="background-color:#CCCCFF;"
			value="<%= (platinfVO==null)? "" : platinfVO.getCom_address()%>" /></td>
	</tr>
	<tr>
		<td>聯絡電話:</td>
		<td><input type="TEXT" name="cp_no" size="45" style="background-color:#8CFFFF;"
			value="<%= (platinfVO==null)? "" : platinfVO.getCp_no()%>" /></td>
	</tr>
	
	<tr>
		<td>客服信箱:</td>
		<td><input type="TEXT" name="cs_email" size="45" style="background-color:#FFD4D4;"
			value="<%= (platinfVO==null)? "" : platinfVO.getCs_email()%>" /></td>
	</tr>
	
	<tr>
		<td>隱私權政策:</td>
		<td align='center'><textarea name= "pr_policy" rows="20" cols="40" style="resize:none;border:2px #00C700 dashed;background-color:#C9FFC9;"></textarea></td>
	</tr>
	
	<tr>
		<td>圖片:</td>
		<td><input type="file" name="pin_photo"/></td>
	</tr>


</table>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增" style="width:80px;height:40px;font-size:15px;"></FORM>
</body>

</html>
