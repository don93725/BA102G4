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
					���a���|
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
		<center><h2>���a���|</h2></center>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
<!-- 								<th class="center">���|�s��</th> -->
								<th class="center" style="vertical-align:middle;">���a�s��</th>
								<th class="center" style="vertical-align:middle;">���|�̱b��</th>
								<th class="center" style="vertical-align:middle;">���|��]</th>
								<th class="center" style="vertical-align:middle;">�ɶ�</th>
<!-- 								<th class="center">�Q���|���e</th> -->
								<th class="center" style="vertical-align:middle;">�Ӥ�</th>
								<th class="center" style="vertical-align:middle;">���A</th>
								<th class="center" style="vertical-align:middle;">�֭�</th>
								<th class="center" style="vertical-align:middle;">��^</th>
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
									<td style="vertical-align:middle;"><img src="<%=request.getContextPath()%>/tools/DBGifReader?pr_no=${placeReportVO.pr_no}"
															style="width: 100px; height: 100px;"></td>
									<td style="vertical-align:middle;">${placeReportVO.pr_stat==0?'���f��':'�w�f��'}</td>
									<td style="vertical-align:middle;">
										<form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl">
<!-- 											<input type="submit" class="btn btn-primary btn-sm" value="�֭�">  -->
											<input type="button" class="btn btn-primary btn-sm" value="�֭�" onclick="sendClick(<%=++count%>)">
											<button  style="display:none;" class="btn btn-primary btn-sm" id="GGG<%=count%>">�֭�</button>
											<input type="hidden" name="pr_no" value="${placeReportVO.pr_no}"> 
											<input type="hidden" name="pt_no" value="${placeReportVO.pt_no}">
											<input type="hidden" name="action" value="Report">
										</form>
									</td>

									<td style="vertical-align:middle;"><form method="post" action="<%=request.getContextPath()%>/placerep/PlaceRepCtrl">
											<input type="submit" class="btn btn-danger btn-sm" value="��^"> 
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
		  title: "�T�w�n�e�X�f�֡H",
		  text: "���|�����f�ְʧ@�C",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  confirmButtonText: "�O��",
		  cancelButtonText: "��F",
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