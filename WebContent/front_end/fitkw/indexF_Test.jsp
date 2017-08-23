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
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>
     <tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>健身知識</h3>
		</td>
	</tr>
    <c:forEach var="fitkwVO" items="${list}">
       <tr align='center' valign='middle'>
        <td>${fitkwVO.upd_date}</td>
		<td>${fitkwVO.fik_type}</td>
		<td>${fitkwVO.fik_title}</td>
      </tr>
     </c:forEach>

</body>
</html>