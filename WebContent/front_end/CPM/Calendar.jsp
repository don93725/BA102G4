<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.place_time.model.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.coaches.model.*"%>
<%@ page import="com.students.model.*"%>
<%@ page import="com.gyms.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<% 
	Place_timeService place_timeSVC = new Place_timeService();
	ArrayList<Place_timeVO> plist = (ArrayList) place_timeSVC.getAllCoa(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < plist.size(); i++) {
		plist.get(i).setRp_timeShow(
				(String) getServletContext().getAttribute(String.valueOf(((Place_timeVO) plist.get(i)).getRp_time())));
	}
	pageContext.setAttribute("plist", plist);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/fullcalendar.css" />

</head>
<body>
			<!-- PAGE CONTENT BEGINS -->

			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
				<div class="col-sm-9">
					<div class="space"></div>

					<div id="calendar"></div>
				</div>

				<div class="col-sm-3">
					<div class="widget-box transparent">
						<div class="widget-header">
							<h4>時段:</h4>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<div id="external-events">


									<div class="external-event label-success"
										data-class="label-success">
										<i class="glyphicon glyphicon-search"></i> 08:00-09:30
									</div>

									<div class="external-event label-danger"
										data-class="label-danger">
										<i class="glyphicon glyphicon-search"></i> 10:00-11:30
									</div>

									<div class="external-event label-purple"
										data-class="label-purple">
										<i class="glyphicon glyphicon-search"></i> 13:00-14:30
									</div>

									<div class="external-event label-yellow"
										data-class="label-yellow">
										<i class="glyphicon glyphicon-search"></i> 15:00-16:30
									</div>

									<div class="external-event label-pink" data-class="label-pink">
										<i class="glyphicon glyphicon-search"></i> 18:00-19:30
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="glyphicon glyphicon-search"></i> 20:00-21:30
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<span style="display:none;">
				<c:forEach var="place_time" items="${plist}">
					<c:choose>
							<c:when test="${place_time.rp_time == 1}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${place_time.placeVO.p_name}','${place_time.rp_date}','label-success',8)">
							</c:when>
							<c:when test="${place_time.rp_time == 2}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${place_time.placeVO.p_name}','${place_time.rp_date}','label-danger',10)">
							</c:when>
							<c:when test="${place_time.rp_time == 3}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${place_time.placeVO.p_name}','${place_time.rp_date}','label-purple',13)">
							</c:when>
							<c:when test="${place_time.rp_time == 4}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${place_time.placeVO.p_name}','${place_time.rp_date}','label-yellow',15)">
							</c:when>
							<c:when test="${place_time.rp_time == 5}">
								<input type="button" class="calendarbtn" onclick="addCalendar('${place_time.placeVO.p_name}','${place_time.rp_date}','label-pink',18)">
							</c:when>
							<c:otherwise>
								<input type="button" class="calendarbtn" onclick="addCalendar('${place_time.placeVO.p_name}','${place_time.rp_date}','label-info',20)">
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