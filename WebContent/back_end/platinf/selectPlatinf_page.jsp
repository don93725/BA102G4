<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>平台資訊管理</title></head>
<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#1AFF19;">
  <tr bgcolor='yellow' align='center' valign='middle' height='20'>
    <td><h3>平台資訊管理</h3></td>
  </tr>
</table>

<p></p>

<h3>[資訊查詢]:</h3>
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

<ul>
  <li><a href='listAllPlatinf.jsp'><input type="submit" value="資訊總覽" style="width:80px;height:40px;font-size:15px;"></a> </li> <br><br>
  
  
</ul>


<h3>[新增資訊]</h3>

<ul>
  <li><a href='addPlatinf.jsp'><input type="submit" value="新增" style="width:80px;height:40px;font-size:15px;"></a></li>
</ul>

</body>

</html>
