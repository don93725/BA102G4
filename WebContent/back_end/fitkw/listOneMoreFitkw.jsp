<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.fitkw.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>健身知識資料</title></head>
<body bgcolor='#E8FFFF'>
<table border='5' cellpadding='5' cellspacing='0' width='800' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>健身知識資料</h3>
		<a href="selectFitkw_page.jsp">回首頁</a>
		</td>
	</tr>
</table>
<br>
<table border='2' bgcolor="#DEFFDE" style="border-color:#0000FF;" width='1500'>
	<tr>

		<th>更新日期</th>
		<th>知識主題</th>
		<th>知識標題</th>
		<th>知識內文</th>
		<th>知識圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="fitkw" items='${fitkwVO}' >
	<tr align='center' valign='middle'>

		<td>${fitkw.upd_date}</td>
		<td>${fitkw.fik_type}</td>
		<td align='left'>${fitkw.fik_title}</td>
		<td align='left' valign='top'>${fitkw.fik_ctx}</td>
		<td><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkw.fik_no}'/></td>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="fik_no" value="${fitkw.fik_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ann_no" value="${fitkw.fik_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
		</td>
		
	</tr>
	</c:forEach>
</table>

</body>
</html>
