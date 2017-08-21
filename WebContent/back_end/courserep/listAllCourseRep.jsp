<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*"%>
<%@ page import="com.Course_list.model.*"%>
<%@ page import="com.Course_time.model.*"%>
<%@ page import="com.Course.model.*"%>

	

<%
	course_listService courseRepSvc = new course_listService();
	List<Course_listVO> list = courseRepSvc.getReportSta(1);
	pageContext.setAttribute("list", list);
%>
<jsp:useBean id="courseRepMemSvc" scope="page" class="com.members.model.MembersService"/>
<jsp:useBean id="courseRepCourseSvc" scope="page" class="com.Course.model.CourseService"/>
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
				檢舉管理 <small> <i class="icon-double-angle-right"></i>
					課程檢舉審核_未處理 - ListAllCourseRep.jsp
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
	<table border='1'>
		<tr> 
			<th>課程編號</th>
			<th>課程名稱</th>
			<th>檢舉者帳號</th>
			<th>檢舉者暱稱</th>
			<th>檢舉原因</th>
			<th>狀態</th>
			<th>核准</th>
			<th>駁回</th>
		</tr>
		<%@ include file="page1.file"%>
		<c:forEach var="courseReportVO" items="${list}"
			begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align="center" valign="middle">
				<td>${courseReportVO.ct_no}</td>
				<td>${courseRepCourseSvc.getCourse(courseReportVO.ct_no).crs_name}</td>
				<td>${courseReportVO.stu_acc}</td>
				<td>${courseRepMemSvc.getMemAcc(courseReportVO.stu_acc).mem_nickname}</td>
				<td>${courseReportVO.report_ct}</td>
				<td>${courseReportVO.report_sta==1?'未處理':''}</td>


				<td>
					<form method="post" action="<%=request.getContextPath()%>/courserep/CourseRepCtrl" >
						<input type="submit" value="核准"> 
						<input type="hidden" name="ct_no" value="${courseReportVO.ct_no}"> 
						<input type="hidden" name="action" value="Report">
					</form>
				</td>

				<td><form method="post" action="<%=request.getContextPath()%>/courserep/CourseRepCtrl">
						<input type="submit" value="駁回"> 
						<input type="hidden" name="ct_no" value="${courseReportVO.ct_no}"> 
						<input type="hidden" name="action" value="NO_Report">
					</form></td>
			</tr>
		</c:forEach>
	</table>
	<%@ include file="page2.file"%>
<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>