<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adapply.model.*"%>
<%
	String action = request.getParameter("action");
	if (!("addADBtn".equals(action))) {
		System.out.println("您是打網址進入的");
		response.sendRedirect(request.getContextPath() + "/adapply/AD_ApplyCtrl?action=addADBtn");
	}
%>

<%
	AD_ApplyService ADSvc = new AD_ApplyService();
	Set<AD_ApplyVO> list = ADSvc.getStat(0);
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<title>listAllAD</title>
</head>
<body>
	<h3>廣告申請審核 - setCheckAD.jsp</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修以下錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if> 
	<table border='1'>
		<tr>
			<th>廣告編號</th>
			<th>會員流水號</th>
			<th>付費日期</th>
			<th>廣告名稱</th>
			<th>廣告連結網頁</th>
			<th>廣告刊登日期</th>
			<th>廣告下假日期</th>
			<th>廣告敘述</th>
			<th>廣告圖片</th>
			<th>申請審合狀態</th>
			<th>核准</th>
			<th>駁回</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="ad_ApplyVO" items="${list}" begin="<%=pageIndex%>"
			end="<%=pageIndex+rowsPerPage-1%>">
			<tr align="center" valign="middle">
				<td>${ad_ApplyVO.ad_no}</td>
				<td>${ad_ApplyVO.mem_no}</td>
				<td>${ad_ApplyVO.pay_date}</td>
				<td>${ad_ApplyVO.ad_name}</td>
				<td>${ad_ApplyVO.ad_url}</td>
				<td>${ad_ApplyVO.ad_ondate}</td>
				<td>${ad_ApplyVO.ad_offdate}</td>
				<td>${ad_ApplyVO.ad_ctx}</td>
				<td><img
					src="<%=request.getContextPath()%>/adapply/DBGifReader?ad_no=${ad_ApplyVO.ad_no}"
					style="width: 100px; height: 100px;"></td>
				<td>${ad_ApplyVO.arv_stat}</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/adapply/AD_ApplyCtrl">
						<input type="submit" value="核准"> 
						<input type="hidden" name="ad_no" value="${ad_ApplyVO.ad_no}"> 
						<input type="hidden" name="action" value="OK_AD">
					</form>
				</td>

				<td><form method="post"
						action="<%=request.getContextPath()%>/adapply/AD_ApplyCtrl">
						<input type="submit" value="駁回"> 
						<input type="hidden" name="ad_no" value="${ad_ApplyVO.ad_no}"> 
						<input type="hidden" name="action" value="NO_AD">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>