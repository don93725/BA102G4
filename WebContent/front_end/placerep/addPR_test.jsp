<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.place.model.*" %>
<%-- <%@ page import="com.place_time.model.*" %> --%>
 <% 
//  	PlaceService PSvc = new PlaceService();
//  	List<PlaceVO> list = PSvc.getAll();
//  	pageContext.setAttribute("list", list);
 %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>addPR_test</title>
</head>
<body>
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>場地檢舉按鈕實作 - addPR_test.jsp</h3>
				<h3>${user.mem_nickname},你好!!</h3>
			</td>
		</tr>
	</table>
	
	<h4>
		場地檢舉:<font color=red><b>*</b></font>為必填欄位
	</h4>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message.value}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	<table border='1'>
		<tr>
			<th>場地編號</th>
			<th>健身房帳號</th>
			<th>場地名稱</th>
			<th>審核狀態</th>
			<th>檢舉</th>
		</tr>
		
		<c:forEach var="placeVO" items="${list}" >
			<tr align="center" valign="middle">
				
				<td>${placeVO.p_no}</td>
				<td>${placeVO.g_acc}</td>
				<td>${placeVO.p_name}</td>
				<td>${placeVO.status}</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl"
						enctype='multipart/form-data'>
						<input type="submit" value="檢舉"> 
						<input type="hidden" name="p_no" value="${placeVO.p_no}">
						<input type="hidden" name="action" value="addPR">
					</form>
				</td>

				
		</c:forEach>
	</table>
	
	
</body>
</html>