<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.annew.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>公告消息資料</title>
</head>
<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='800' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>公告消息資料</h3>
		<a href="selectAnnew_page.jsp">回首頁</a>
		</td>
	</tr>
</table>
<br>
<table border='2' bgcolor="#DEFFDE" style="border-color:#0000FF;" width='1500'>
	<tr>
		
		<th>刊登日期</th>
		<th>更新日期</th>
		<th>公告標題</th>
		<th>公告內文</th>
		<th>公告圖片</th>
<!-- 		<th>附件檔案</th> -->
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<c:forEach var="annew" items='${annewVO}' >
	<!-- for( AnnewVO annew : annewVO{
	  
	} -->
	<tr align='center' valign='middle'>
		
		<td>${annew.ann_date}</td>
		<td>${annew.upd_date}</td>
		<td align='left'>${annew.ann_title}</td>
		<td align='left' valign='top'>${annew.ann_ctx}</td>
		<td><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annew.ann_no}'/></td>
<%-- 		<td>${annew.att_no}</td> --%>
		
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/annew/annew.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="ann_no" value="${annew.ann_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/annew/annew.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="ann_no" value="${annew.ann_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
		</td>
		
	</tr>
	</c:forEach>
</table>

</body>
</html>
