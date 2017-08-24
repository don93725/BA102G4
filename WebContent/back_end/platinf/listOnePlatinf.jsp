<%@ page contentType="text/html; charset=Big5"%>
<%@ page import="com.platinf.model.*"%>
<%
PlatinfVO platinfVO = (PlatinfVO) request.getAttribute("platinfVO"); //PlatinfServlet.java(Controller), 存入req的platinfVO物件
%>
<html>
<head>
<title>平台資訊資料 - listOnePlatinf.jsp</title>
</head>
<body bgcolor='white'>

<table border='1' cellpadding='5' cellspacing='0' width='600'>
	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
		<td>
		<h3>平台資訊資料 - ListOnePlatinf.jsp</h3>
		<a href="selectPlatinf_page.jsp">回首頁</a>
		</td>
	</tr>
</table>

<table border='1' bordercolor='#CCCCFF' width='600'>
	<tr>
		<th>資訊編號</th>
		<th>公司地址</th>
		<th>聯絡電話</th>
		<th>客服信箱</th>
		<th>隱私權政策</th>
		<th>更新日期</th>
		<th>圖片</th>
	</tr>
	<tr align='center' valign='middle'>
		<td><%=platinfVO.getPin_no()%></td>
		<td><%=platinfVO.getCom_address()%></td>
		<td><%=platinfVO.getCp_no()%></td>
		<td><%=platinfVO.getCs_email()%></td>
		<td><%=platinfVO.getPr_policy()%></td>
		<td><%=platinfVO.getUpd_date()%></td>
		<td><%=platinfVO.getPin_photo()%></td>
		
	</tr>
</table>

</body>
</html>
