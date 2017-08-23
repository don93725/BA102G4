<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fitkw.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    FitkwService fitkwSvc = new FitkwService();
    List<FitkwVO> list = fitkwSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html>
<head>
<title>listAllFitkw.jsp</title>
</head>
<body bgcolor='#E8FFFF'>
<b><font color=red></font></b>
<table border='5' cellpadding='5' cellspacing='0' width='800' style="border-color:#FFD700;">
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>健身知識總覽</h3>
		<a href="selectFitkw_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

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

<table border="2" bgcolor="#DEFFDE" style="border-color:#0000FF;" width='1500'>
	<tr>
		<th>更新日期</th>
		<th>知識主題</th>
		<th>知識標題</th>
		<th>知識內文</th>
		<th>知識圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	<%@ include file="page1.file" %> 
	<c:forEach var="fitkwVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>

			<td>${fitkwVO.upd_date}</td>
			<td>${fitkwVO.fik_type}</td>
			<td align='left'>${fitkwVO.fik_title}</td>
			<td align='left' valign='top'>${fitkwVO.fik_ctx}</td>
			<td><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}'/></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			     <input type="submit" value="修改">
			     <input type="hidden" name="fik_no" value="${fitkwVO.fik_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			    <input type="submit" value="刪除">
			    <input type="hidden" name="fik_no" value="${fitkwVO.fik_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
</table>
<%@ include file="page2.file" %>

</body>
</html>
