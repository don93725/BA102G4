<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.membersreport.model.*"%>

<%
	MembersReportService MRSvc = new MembersReportService();
	List<MembersReportVO> list = MRSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>listAllMemRep</title>
</head>
<body>
	<h3>會員檢舉審核_總覽 - ListAllMemRep.jsp</h3>
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
			<th>檢舉編號</th>
			<th>被檢舉者</th>
			<th>檢舉者</th>
			<th>檢舉原因</th>
			<th>時間</th>
			<th>被檢舉內容</th>
			<th>照片</th>
			<th>狀態</th>
			<th>核准</th>
			<th>駁回</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="membersReportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align="center" valign="middle">
				<td>${membersReportVO.mr_no}</td>
				<td>${membersReportVO.mr_def}</td>
				<td>${membersReportVO.mr_pro}</td>
				<td>${membersReportVO.mr_ctx}</td>
				<td>
				<fmt:formatDate type="both"  dateStyle="long" timeStyle="long"  
				value="${membersReportVO.mr_time}" />
				</td>
				<td>${membersReportVO.ref_ctx}</td>
				<td><img
					src="<%=request.getContextPath()%>/tools/DBGifReader?mr_no=${membersReportVO.mr_no}"
					style="width: 100px; height: 100px;"></td>
				<td>${membersReportVO.mr_stat}</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/memrep/MemRepCtrl">
						<input type="submit" value="核准"> 
						<input type="hidden" name="mr_no" value="${membersReportVO.mr_no}"> 
						<input type="hidden" name="mr_def" value="${membersReportVO.mr_def}">
						<input type="hidden" name="action" value="Report">
					</form>
				</td>

				<td><form method="post"
						action="<%=request.getContextPath()%>/memrep/MemRepCtrl">
						<input type="submit" value="駁回"> <input type="hidden"
							name="mr_no" value="${membersReportVO.mr_no}"> <input
							type="hidden" name="action" value="NO_Report">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>

</body>
</html>