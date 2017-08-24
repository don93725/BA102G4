<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.queans.model.*"%>
<%
QueansVO queansVO = (QueansVO) request.getAttribute("queansVO"); //QueansServlet.java(Controller), 存入req的queansVO物件
%>
<html>
<head>
<title>常見問題資料</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>常見問題資料</h3>
		<a href="selectQueans_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>問題編號</th>
		<th>更新日期</th>
		<th>問題類別</th>
		<th>問題標題</th>
		<th>解說內文</th>
		<th>問題圖片</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=queansVO.getQue_no()%></td>
		<td><%=queansVO.getUpd_date()%></td>
		<td><%=queansVO.getQue_type()%></td>
		<td><%=queansVO.getQue_title()%></td>
		<td><%=queansVO.getAns_ctx()%></td>
		<td><%=queansVO.getQue_photo()%></td>
		
	</tr>
</table>

</body>
</html>
