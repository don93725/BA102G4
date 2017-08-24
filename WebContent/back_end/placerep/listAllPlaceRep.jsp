<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.place_report.model.*"%>

<%
	PlaceReportService PRSvc = new PlaceReportService();
	List<PlaceReportVO> list = PRSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html>
<html>
<head>
<title>���f��x�޲z�t��</title>
</head>
<body>
<h3>�|�����|�f��_�`�� - ListAllPlaceRep.jsp</h3>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�ЭץH�U���~:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	<table border='1'>
		<tr>
			<th>���|�s��</th>
			<th>���a�s��</th>
			<th>���|��</th>
			<th>���|��]</th>
			<th>�ɶ�</th>
			<th>�Q���|���e</th>
			<th>�Ӥ�</th>
			<th>���A</th>
			<th>�֭�</th>
			<th>��^</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="placeReportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align="center" valign="middle">
				<td>${placeReportVO.pr_no}</td>
				<td>${placeReportVO.p_no}</td>
				<td>${placeReportVO.mem_no}</td>
				<td>${placeReportVO.pr_ctx}</td>
				<td><fmt:formatDate type="both"  dateStyle="long" timeStyle="long"  value="${placeReportVO.pr_time}" /></td>
				<td>${placeReportVO.ref_ctx}</td>
				<td><img
					src="<%=request.getContextPath()%>/tools/DBGifReader?pr_no=${placeReportVO.pr_no}"
					style="width: 100px; height: 100px;"></td>
				<td>${placeReportVO.pr_stat}</td>
				<td>
					<form method="post"
						action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl" >
						<input type="submit" value="�֭�"> 
						<input type="hidden" name="pr_no" value="${placeReportVO.pr_no}"> 
						<input type="hidden" name="p_no" value="${placeReportVO.p_no}">
						<input type="hidden" name="action" value="Report">
					</form>
				</td>

				<td><form method="post"
						action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl" >
						<input type="submit" value="��^"> <input type="hidden"
							name="pr_no" value="${placeReportVO.pr_no}"> <input
							type="hidden" name="action" value="NO_Report">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
</body>
</html>