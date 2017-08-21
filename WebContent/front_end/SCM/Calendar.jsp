<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.course_time.model.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.coaches.model.*"%>
<%@ page import="com.students.model.*"%>
<%@ page import="com.gyms.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	Course_listService course_listSVC = new Course_listService();
	ArrayList<Course_listVO> crslist = (ArrayList) course_listSVC.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
	ArrayList<Course_listVO> crslistOpen = (ArrayList) course_listSVC.getAllOpen(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < crslist.size(); i++) {
		((Course_listVO) crslist.get(i)).setCrs_timeShow((String) getServletContext()
				.getAttribute(String.valueOf(((Course_listVO) crslist.get(i)).getCrs_time())));
	}
	for (int i = 0; i < crslistOpen.size(); i++) {
		((Course_listVO) crslistOpen.get(i)).setCrs_timeShow((String) getServletContext()
				.getAttribute(String.valueOf(((Course_listVO) crslistOpen.get(i)).getCrs_time())));	
	}
	pageContext.setAttribute("crslist", crslist);
	pageContext.setAttribute("crslistOpen", crslistOpen);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/fullcalendar.css" />
<link href="<%= request.getContextPath() %>/style/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/font-awesome.min.css" />
<!-- page specific plugin styles -->
<!-- Custom Fonts -->
<link
	href="<%= request.getContextPath() %>/style/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- jQuery -->
<!--訪客navbar動畫-1-->
<script src="<%= request.getContextPath() %>/style/js/jquery.js"></script>

<!-- Script to Activate the Carousel -->
<!-- Bootstrap Core JavaScript -->
<!--訪客navbar動畫-2-->
<script src="<%= request.getContextPath() %>/style/js/bootstrap.min.js"></script>
<!-- ace settings handler -->
<!--button樣式-->
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/ace.min.css" />
</head>
<body>
			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
				<div class="col-sm-12">
					<div class="space"></div>

					<div id="calendar" onchange="calendarChange()"></div>
				</div>
			</div>
			<span style="display:none;">
				<c:forEach var="course_time" items="${crslist}">
					<c:choose>
							<c:when test="${course_time.crs_time == 1}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (加退選)','${course_time.cl_date}','label-success',8)">
							</c:when>
							<c:when test="${course_time.crs_time == 2}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (加退選)','${course_time.cl_date}','label-success',10)">
							</c:when>
							<c:when test="${course_time.crs_time == 3}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (加退選)','${course_time.cl_date}','label-info',14)">
							</c:when>
							<c:when test="${course_time.crs_time == 4}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (加退選)','${course_time.cl_date}','label-info',16)">
							</c:when>
							<c:when test="${course_time.crs_time == 5}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (加退選)','${course_time.cl_date}','label-danger',19)">
							</c:when>
							<c:otherwise>
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (加退選)','${course_time.cl_date}','label-danger',21)">
							</c:otherwise>
						</c:choose>
				</c:forEach>
				<c:forEach var="course_time" items="${crslistOpen}">
					<c:choose>
							<c:when test="${course_time.crs_time == 1}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (開課)','${course_time.cl_date}','label-success',8)">
							</c:when>
							<c:when test="${course_time.crs_time == 2}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (開課)','${course_time.cl_date}','label-success',10)">
							</c:when>
							<c:when test="${course_time.crs_time == 3}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (開課)','${course_time.cl_date}','label-info',14)">
							</c:when>
							<c:when test="${course_time.crs_time == 4}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (開課)','${course_time.cl_date}','label-info',16)">
							</c:when>
							<c:when test="${course_time.crs_time == 5}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (開課)','${course_time.cl_date}','label-danger',19)">
							</c:when>
							<c:otherwise>
								<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name} ${course_time.crs_timeShow} (開課)','${course_time.cl_date}','label-danger',21)">
							</c:otherwise>
						</c:choose>
				</c:forEach>
			</span>
</body>
</html>



<script
	src="<%=request.getContextPath()%>/style/assets/js/fullcalendar.min.js"></script>


<script>

	

</script>
<style>
.fc-header-title>h2 {
	font-size: 30px;
}

.fc-day-header {
	font-size: 16px;
}

.fc-event-hori {
	border: 2px solid;
	border-radius: 8px;
}

.fc-event-title {
	font-size: 16px;
}

.fc-event-time {
	font-size: 16px;
}

.fc-today {
	background-color: #FFC559;
}

.fc-day-number {
	font-size: 18px;
}
</style>