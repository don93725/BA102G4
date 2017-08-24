<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.place_report.model.*"%>

<jsp:useBean id="CheckPlaceMemSvc" scope="page" class="com.members.model.MembersService"/>


<%
	PlaceReportService PRSvc = new PlaceReportService();
	List<PlaceReportVO> list = PRSvc.getStat(0);
	pageContext.setAttribute("list", list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<title>���f��x�޲z�t��</title>
<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				���|�޲z <small> <i class="icon-double-angle-right"></i>
					���a���|_���f��
				</small>
			</h1>
		</div>
		<!-- /.page-header -->



		<c:if test="${not empty errorMsgs}">
			<font color='red'>�ЭץH�U���~:
				<ul>
					<c:forEach var="message" items="${errorMsgs}">
						<li>${message}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>
		
		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">���|�s��</th>
								<th class="center">���a�s��</th>
								<th class="center">���|�̱b��</th>
								<th class="center">���|��]</th>
								<th class="center">�ɶ�</th>
<!-- 								<th class="center">�Q���|���e</th> -->
								<th class="center">�Ӥ�</th>
								<th class="center">���A</th>
								<th class="center">�֭�</th>
								<th class="center">��^</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="placeReportVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr align="center" valign="middle">
									<td class="center">${placeReportVO.pr_no}</td>
									<td class="center">${placeReportVO.pt_no}</td>
									<%-- 				<td>${placeReportVO.mem_no}</td> --%>
									<td class="center">${CheckPlaceMemSvc.getOneMem(placeReportVO.mem_no).mem_acc}</td>
									<td class="center">${placeReportVO.pr_ctx}</td>
									<td class="center"><fmt:formatDate type="both" dateStyle="long"
															timeStyle="long" value="${placeReportVO.pr_time}" /></td>
<%-- 									<td class="center"> ${placeReportVO.ref_ctx}</td> --%>
									<td class="center"><img src="<%=request.getContextPath()%>/tools/DBGifReader?pr_no=${placeReportVO.pr_no}"
															style="width: 100px; height: 100px;"></td>
									<td class="center">${placeReportVO.pr_stat==0?'���f��':'�w�f��'}</td>
									<td class="center">
										<form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl">
											<input type="submit" value="�֭�"> <input type="hidden"
													name="pr_no" value="${placeReportVO.pr_no}"> 
											<input type="hidden" name="pt_no" value="${placeReportVO.pt_no}">
											<input type="hidden" name="action" value="Report">
										</form>
									</td>

									<td class="center"><form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl">
											<input type="submit" value="��^"> 
											<input type="hidden" name="pr_no" value="${placeReportVO.pr_no}"> <input
												type="hidden" name="action" value="NO_Report">
										</form>
									</td>
								</tr>
							</c:forEach>
							<%@ include file="page2.file"%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
<%@include file="/back_end/include/ace_setting_footer.file"%></body>
</html>