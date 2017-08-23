<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.annew.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AnnewService annewSvc = new AnnewService();
    List<AnnewVO> list = annewSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
	
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>公告消息</h3>
		</td>
	</tr>
	<c:forEach var="annewVO" items="${list}">
		<tr align='center' valign='middle'>
			<td>${annewVO.ann_date}</td>
			<td>${annewVO.upd_date}</td>
			<td>${annewVO.ann_title}</td>
		</tr>
	</c:forEach>

</body>
</html>