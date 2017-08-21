<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.course_picture.model.*"%>
<%@ page import="com.members.model.*"%>
<%
	Course_listService course_listSVC = new Course_listService();
	ArrayList<Course_listVO> crslist = (ArrayList) course_listSVC.getAllRecord(((MembersVO) session.getAttribute("user")).getMem_acc());
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
								<th>出席狀況</th>
								<th>檢舉課程</th>
								<th>評價課程</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course_listVO" items="${crslist}">
							<tr>
								<td class="center"><a herf=""><font style="font-weight:bold;font-size:18px;cursor: pointer;" onmouseover="showBlock('${course_listVO.ct_no}')" onmouseout="hideBlock()">${course_listVO.courseVO.crs_name}</font></a></td>
								<td>${course_listVO.coachesVO.coa_name}</td>
								<td>
									<c:if test="${course_listVO.n_sta  == 0}">
									<font color='red' style="font-weight:bold;font-size:16px">未出席</font>
									</c:if>
									<c:if test="${course_listVO.n_sta  == 1}">
										<font color='blue' style="font-weight:bold;font-size:16px">出席</font>
									</c:if>
									<c:if test="${course_listVO.n_sta  == 2}">
										<font color='green' style="font-weight:bold;font-size:16px">請假</font>
									</c:if>
								</td>
								<td>
									<c:if test="${course_listVO.report_sta == 1}">
										<a href=""><font style="font-weight:bold;font-size:18px;cursor: pointer;" onmouseover="showReportBlock('${course_listVO.ct_no}','${course_listVO.stu_acc}')" onmouseout="hideLeaveBlock()">已檢舉</font></a>
									</c:if>
									<c:if test="${course_listVO.report_sta == 0}">
										<button class="btn btn-danger" onclick="report('${course_listVO.ct_no}','${course_listVO.stu_acc}')" style="border-radius:6px;">檢舉課程</button>
									</c:if>
								</td>
								<td>
									<c:if test="${course_listVO.feedback == null}">
										<button class="btn btn-primary" onclick="payment(<%= count %>)" style="border-radius:6px;">評價課程</button>
									</c:if>
									<c:if test="${course_listVO.feedback != null}">
										<button class="btn btn-primary" onclick="payment(<%= count %>)" style="border-radius:6px;" disabled="true">評價課程</button>
									</c:if>
								</td>
								
								<a href="#" onclick="window.open('<%=request.getContextPath()%>/front_end/SCM/Evaluation.jsp?ct_no=${course_listVO.ct_no}&stu_acc=${course_listVO.stu_acc}', 'YOOO', config='height=400,width=500');"><button id="pay<%= count %>" style="display:none;">評價課程</button></a>
							</tr>
							<button style="display:none;" class="paybtn" onclick="payStatus(<%= count %>)">6666</button>
							<% count++;%>
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