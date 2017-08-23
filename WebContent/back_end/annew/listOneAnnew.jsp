<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.annew.model.*"%>
<%
AnnewVO annewVO = (AnnewVO) request.getAttribute("annewVO"); //AnnewServlet.java(Controller), 存入req的annewVO物件
%>
<html>
<head>
<title>公告消息資料</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='yellow' align='center' valign='middle' height='20'>
		<td>
		<h3>公告消息資料</h3>
		<a href="selectAnnew_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		
		<th>刊登日期</th>
		<th>更新日期</th>
		<th>公告標題</th>
		<th>公告內文</th>
		<th>公告圖片</th>
		<th>附件檔案</th>
	</tr>
	<tr align='center' valign='middle'>
		
		<td><%=annewVO.getAnn_date()%></td>
		<td><%=annewVO.getUpd_date()%></td>
		<td><%=annewVO.getAnn_title()%></td>
		<td><%=annewVO.getAnn_ctx()%></td>
		<td><%=annewVO.getAnn_photo()%></td>
		<td><%=annewVO.getAtt_no()%></td>
		
	</tr>
</table>

</body>
</html>
