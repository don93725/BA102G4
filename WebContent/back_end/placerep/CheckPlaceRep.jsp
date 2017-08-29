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
<title>健貨後台管理系統</title>
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
				檢舉管理 <small> <i class="icon-double-angle-right"></i>
					場地檢舉
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
		<center><h2>場地檢舉</h2></center>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
<!-- 								<th class="center">檢舉編號</th> -->
								<th class="center" style="vertical-align:middle;">場地編號</th>
								<th class="center" style="vertical-align:middle;">檢舉者帳號</th>
								<th class="center" style="vertical-align:middle;">檢舉原因</th>
								<th class="center" style="vertical-align:middle;">時間</th>
<!-- 								<th class="center">被檢舉內容</th> -->
<!-- 								<th class="center" style="vertical-align:middle;">照片</th> -->
								<th class="center" style="vertical-align:middle;">狀態</th>
								<th class="center" style="vertical-align:middle;">核准</th>
								<th class="center" style="vertical-align:middle;">駁回</th>
							</tr>
						</thead>
						<tbody>
							<%int count = 0 ;%>
							<%@ include file="page1.file"%>
							<c:forEach var="placeReportVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr style="vertical-align:middle;">
<%-- 									<td class="center">${placeReportVO.pr_no}</td> --%>
									<td style="vertical-align:middle;">${placeReportVO.pt_no}</td>
									<%-- 				<td>${placeReportVO.mem_no}</td> --%>
									<td style="vertical-align:middle;">${CheckPlaceMemSvc.getOneMem(placeReportVO.mem_no).mem_acc}</td>
									<td style="vertical-align:middle;">${placeReportVO.pr_ctx}</td>
									<td style="vertical-align:middle;"><fmt:formatDate type="both" dateStyle="long"
															timeStyle="long" value="${placeReportVO.pr_time}" /></td>
<%-- 									<td class="center"> ${placeReportVO.ref_ctx}</td> --%>
<%-- 									<td style="vertical-align:middle;"><img src="<%=request.getContextPath()%>/tools/DBGifReader?pr_no=${placeReportVO.pr_no}" style="width: 100px; height: 100px;"></td> --%>
									<td style="vertical-align:middle;">${placeReportVO.pr_stat==0?'未審核':'已審核'}</td>
									<td style="vertical-align:middle;">
										<form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl">
<!-- 											<input type="submit" class="btn btn-primary btn-sm" value="核准">  -->
											<input type="button" class="btn btn-primary btn-sm" value="核准" onclick="sendClick(<%=++count%>)">
											<button  style="display:none;" class="btn btn-primary btn-sm" id="GGG<%=count%>">核准</button>
											<input type="hidden" name="pr_no" value="${placeReportVO.pr_no}"> 
											<input type="hidden" name="pt_no" value="${placeReportVO.pt_no}">
											<input type="hidden" name="action" value="Report">
										</form>
									</td>

									<td style="vertical-align:middle;"><form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl">
											<input type="submit" class="btn btn-danger btn-sm" value="駁回"> 
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
<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
<script type="text/javascript">

function sendClick(c){
	var btn = "#GGG"+c;
	swal({
		  title: "確定要送出審核？",
		  text: "此舉完成審核動作。",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "是的",
		  cancelButtonText: "算了",
		  closeOnConfirm: false,
		  closeOnCancel: false
		},
		function(isConfirm){
		  if (isConfirm) {
			  $(btn).click();
		  } else {
		    swal.close();
		  }
		});
	
}

</script>
</html>