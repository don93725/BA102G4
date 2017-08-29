<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.course_picture.model.*"%>
<%
	Course_listService course_listSVC = new Course_listService();
	ArrayList<Course_listVO> crslist = (ArrayList) course_listSVC.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < crslist.size(); i++) {
		crslist.get(i).getCourseVO().setCategoryChange(
				(String) getServletContext().getAttribute(((Course_listVO) crslist.get(i)).getCourseVO().getCategory()));
	}
	pageContext.setAttribute("crslist", crslist);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<title>CourseList</title>
</head>
<%! int count=0; %>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">課程名稱</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">類別</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">教練</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">報名截止日期</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">價錢</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">選課情形</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">付費狀態</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">退選</th>
								<th class="center" style="vertical-align:middle;background-color:#C1FFC1;" align="center;">付款</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course_listVO" items="${crslist}">
							<tr>
								<td style="vertical-align:middle;" align="center;"><a href="<%=request.getContextPath()%>/front_end/CourseDetails/courseInfo.jsp?ct_no=${course_listVO.ct_no}&crs_no=${course_listVO.course_timeVO.crs_no}"><font style="font-weight:bold;font-size:18px;cursor: pointer;" onmouseover="showBlock('${course_listVO.ct_no}')" onmouseout="hideBlock()">${course_listVO.courseVO.crs_name}</font></a></td>
								<td style="vertical-align:middle;" align="center;">${course_listVO.courseVO.categoryChange}</td>
								<td style="vertical-align:middle;" align="center;">${course_listVO.coachesVO.coa_name}</td>
								<td style="vertical-align:middle;" align="center;">${course_listVO.course_timeVO.deadline}</td>
								<td style="vertical-align:middle;" align="center;">$${course_listVO.course_timeVO.price}</td>
								<td style="vertical-align:middle;" align="center;">${course_listVO.count} / ${course_listVO.course_timeVO.limit}</td>
								<td style="vertical-align:middle;" align="center;">
									<c:if test="${course_listVO.stu_pay_sta  == 0}">
										<font color='red' style="font-weight:bold;font-size:16px" id="payStatus<%= count %>">未繳費</font>
									</c:if>
									<c:if test="${course_listVO.stu_pay_sta  == 1}">
										<font color='blue' style="font-weight:bold;font-size:16px" id="payStatus<%= count %>">已繳費</font>
									</c:if>
								</td>
								<td style="vertical-align:middle;" align="center;"><button class="btn btn-danger" onclick="deleteCourse('${course_listVO.ct_no}','${course_listVO.stu_acc}')">退選</button></td>
								<td style="vertical-align:middle;" align="center;"><button class="btn btn-warning pay<%= count %>" onclick="payment(<%= count %>)">前往付款</button></td>
								<a href="#" onclick="window.open('<%=request.getContextPath()%>/front_end/SCM/Pay.jsp?ct_no=${course_listVO.ct_no}&stu_acc=${course_listVO.stu_acc}', 'YOOO', config='height=500,width=516');"><button id="pay<%= count %>" style="display:none;">前往付款</button></a>
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

</body>
</html>
<script>
$('.carousel').carousel({
	interval: 1000 //changes the speed
})
</script>