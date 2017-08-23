<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.fitkw.model.*"%>
<%
FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO"); //FitkwServlet.java(Controller), 存入req的fitkwVO物件
%>
<html>
<head>
<title>健身知識資料</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>健身知識資料</h3>
		<a href="selectFitkw_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>知識編號</th>
		<th>更新日期</th>
		<th>知識主題</th>
		<th>知識標題</th>
		<th>知識內文</th>
		<th>知識圖片</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=fitkwVO.getFik_no()%></td>
		<td><%=fitkwVO.getUpd_date()%></td>
		<td><%=fitkwVO.getFik_type()%></td>
		<td><%=fitkwVO.getFik_title()%></td>
		<td><%=fitkwVO.getFik_ctx()%></td>
		<td><%=fitkwVO.getFik_photo()%></td>
		
	</tr>
</table>

</body>
</html>
