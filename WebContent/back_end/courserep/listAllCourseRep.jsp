<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.course_time.model.*"%>
<%@ page import="com.course.model.*"%>

	

<%
	Course_listService courseRepSvc = new Course_listService();
	List<Course_listVO> list = courseRepSvc.getReportSta(1);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="courseRepMemSvc" scope="page" class="com.members.model.MembersService"/>
<jsp:useBean id="courseRepCourseSvc" scope="page" class="com.course.model.CourseService"/>
<%-- <jsp:useBean id="courseRepCourseTimeSvc" scope="page" class="com.Course_time.model.Course_timeService"/> --%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<title>listAllCourseRep</title>
<%@include file="/back_end/include/basic_ace_script.file" %>
</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				���|�޲z <small> <i class="icon-double-angle-right"></i>
					�ҵ{���|�f��_���B�z - ListAllCourseRep.jsp
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
				<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
								<th class="center" style="vertical-align:middle;">�ҵ{�s��</th>
								<th class="center" style="vertical-align:middle;">�ҵ{�W��</th>
								<th class="center" style="vertical-align:middle;">���|�̱b��</th>
								<th class="center" style="vertical-align:middle;">���|�̼ʺ�</th>
								<th class="center" style="vertical-align:middle;">���|��]</th>
								<th class="center" style="vertical-align:middle;">���A</th>
								<th class="center" style="vertical-align:middle;">�֭�</th>
								<th class="center" style="vertical-align:middle;">��^</th>
							</tr>
						</thead>

						<tbody>
							<%int count = 0 ;%>
							<%@ include file="page1.file"%>
							<c:forEach var="courseReportVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td class="center" style="vertical-align:middle;">${courseReportVO.ct_no}</td>
									<td class="center" style="vertical-align:middle;">${courseRepCourseSvc.getCourse(courseReportVO.ct_no).crs_name}</td>
									<td class="center" style="vertical-align:middle;">${courseReportVO.stu_acc}</td>
									<td class="center" style="vertical-align:middle;">${courseRepMemSvc.getMemAcc(courseReportVO.stu_acc).mem_nickname}</td>
									<td class="center" style="vertical-align:middle;">${courseReportVO.report_ct}</td>
									<td class="center" style="vertical-align:middle;">${courseReportVO.report_sta==1?'���B�z':''}</td>


									<td class="center" style="vertical-align:middle;">
										<form method="post" action="<%=request.getContextPath()%>/courserep/CourseRepCtrl">
<!-- 											<input type="submit" class="btn btn-primary btn-sm" value="�֭�">  -->
											<input type="button" class="btn btn-primary btn-sm" value="�֭�" onclick="sendClick(<%=++count%>)">
											<button  style="display:none;" class="btn btn-primary btn-sm" id="GGG<%=count%>">�֭�</button>
											<input type="hidden" name="ct_no" value="${courseReportVO.ct_no}"> 
											<input type="hidden" name="action" value="Report">
										</form>
									</td>

									<td class="center" style="vertical-align:middle;">
										<form method="post" action="<%=request.getContextPath()%>/courserep/CourseRepCtrl">
											<input type="submit" class="btn btn-danger btn-sm" value="��^"> 
											<input type="hidden" name="ct_no" value="${courseReportVO.ct_no}"> 
											<input type="hidden" name="action" value="NO_Report">
										</form>
									</td>
								</tr>
								</c:forEach>
								<%@ include file="page2.file"%>
					</tbody>
				</table>
			</div>
				<!-- /.table-responsive -->
		</div>
			<!-- /col-sm-12 -->
	</div>
		<!-- /row -->
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