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
				<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">�ҵ{�s��</th>
								<th class="center">�ҵ{�W��</th>
								<th class="center">���|�̱b��</th>
								<th class="center">���|�̼ʺ�</th>
								<th class="center">���|��]</th>
								<th class="center">���A</th>
								<th class="center">�֭�</th>
								<th class="center">��^</th>
							</tr>
						</thead>

						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="courseReportVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td class="center">${courseReportVO.ct_no}</td>
									<td class="center">${courseRepCourseSvc.getCourse(courseReportVO.ct_no).crs_name}</td>
									<td class="center">${courseReportVO.stu_acc}</td>
									<td class="center">${courseRepMemSvc.getMemAcc(courseReportVO.stu_acc).mem_nickname}</td>
									<td class="center">${courseReportVO.report_ct}</td>
									<td class="center">${courseReportVO.report_sta==1?'���B�z':''}</td>


									<td class="center">
										<form method="post" action="<%=request.getContextPath()%>/courserep/CourseRepCtrl">
											<input type="submit" class="btn btn-primary" value="�֭�"> 
											<input type="hidden" name="ct_no" value="${courseReportVO.ct_no}"> 
											<input type="hidden" name="action" value="Report">
										</form>
									</td>

									<td class="center">
										<form method="post" action="<%=request.getContextPath()%>/courserep/CourseRepCtrl">
											<input type="submit" class="btn btn-danger" value="��^"> 
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
</html>