<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.membersreport.model.*"%>


<%
	MembersReportService MRSvc = new MembersReportService();
	Set<MembersReportVO> list = MRSvc.getStat(0);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="ADapplyMemSvc" scope="page"
	class="com.members.model.MembersService" />

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<title>健貨後台管理系統</title>

<%@include file="/back_end/include/basic_ace_script.file"%>
</head>
<body>
	<%@include file="/back_end/include/navbar.file"%>
	<%@include file="/back_end/include/sliderBar_breadCrumb.file"%>
	<div class="page-content">
		<div class="page-header">
			<h1>
				檢舉管理<small> <i class="icon-double-angle-right"></i> 會員檢舉_未審核
					- setCheckMemRep
				</small>
			</h1>
		</div>
		<!-- /.page-header -->


		<c:if test="${not empty errorMsgs}">
			<font color='red'>請修以下錯誤:
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
								<!-- 			<th>檢舉編號</th> -->
								<th class="center">檢舉時間</th>
								<th class="center">被檢舉者</th>
								<th class="center">原因</th>
								<th class="center">檢舉者</th>
								<th class="center">被檢舉內容</th>
								<!-- 			<th>照片</th> -->
								<th class="center">狀態</th>
								<th class="center">核准</th>
								<th class="center">駁回</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="membersReportVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr align="center" valign="middle">
<%-- 									<td>${membersReportVO.mr_no}</td> --%>
									<td class="center">${membersReportVO.mr_time}</td>
									<td class="center">${membersReportVO.mr_def}</td>
									<td class="center">${membersReportVO.mr_ctx}</td>
									<td class="center">${membersReportVO.mr_pro}</td>
									<td class="center">${membersReportVO.ref_ctx}</td>
									<!-- 				<td><img -->
									<%-- 					src="<%=request.getContextPath()%>/tools/DBGifReader?mr_no=${membersReportVO.mr_no}" --%>
									<!-- 					style="width: 100px; height: 100px;"></td> -->
									<td class="center">${membersReportVO.mr_stat == 0 ?'未審核':'未審核'}</td>
									<td class="center">
										<form method="post"
											action="<%=request.getContextPath()%>/memrep/MemRepCtrl">
											<input type="submit" value="核准"> <input type="hidden"
												name="mr_no" value="${membersReportVO.mr_no}"> <input
												type="hidden" name="mr_def"
												value="${membersReportVO.mr_def}"> <input
												type="hidden" name="action" value="Report">
										</form>
									</td>

									<td class="center"><form method="post"
											action="<%=request.getContextPath()%>/memrep/MemRepCtrl">
											<input type="submit" value="駁回"> <input type="hidden"
												name="mr_no" value="${membersReportVO.mr_no}"> <input
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
<%@include file="/back_end/include/ace_setting_footer.file"%>
	
</body>
</html>