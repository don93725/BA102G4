<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.course_picture.model.*"%>
<%@ page import="com.members.model.*"%>
<%
	Course_listService course_listSVC = new Course_listService();
	ArrayList<Course_listVO> crslist = (ArrayList) course_listSVC.getAllOpen(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < crslist.size(); i++) {
		crslist.get(i).getCourseVO().setCategoryChange(
				(String) getServletContext().getAttribute(((Course_listVO) crslist.get(i)).getCourseVO().getCategory()));
	}
	pageContext.setAttribute("crslist", crslist);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>CourseList</title>
</head>
<%! int count=0; %>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">課程名稱</th>
								<th>教練</th>
								<th>場館</th>
								<th>開課日期</th>
								<th>請假</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course_listVO" items="${crslist}">
							<tr>
								<td class="center"><a href=""><font style="font-weight:bold;font-size:18px;cursor: pointer;" onmouseover="showBlock('${course_listVO.ct_no}')" onmouseout="hideBlock()">${course_listVO.courseVO.crs_name}</font></a></td>
								<td>${course_listVO.coachesVO.coa_name}</td>
								<td>${course_listVO.placeVO.p_name}</td>
								<td>${course_listVO.cl_date}</td>
								<td>
									<c:if test="${course_listVO.n_sta == 2}">
										<a href=""><font style="font-weight:bold;font-size:18px;cursor: pointer;" onmouseover="showLeaveBlock('${course_listVO.ct_no}','${course_listVO.stu_acc}')" onmouseout="hideLeaveBlock()">已請假</font></a>
									</c:if>
									<c:if test="${course_listVO.n_sta == 0}">
										<button class="btn btn-info" onclick="leave('${course_listVO.ct_no}','${course_listVO.stu_acc}')" style="border-radius:6px;">填寫請假單</button>
									</c:if>
								</td>
							</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /col-sm-12 -->
		</div>
		<!-- /row -->
	</div>
	<!-- 課程管理 全部 結束 -->
<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
</html>
<script>




$('.carousel').carousel({
	interval: 1000 //changes the speed
})
</script>