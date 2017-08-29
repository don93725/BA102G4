<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course_time.model.*"%>
<%@ page import="com.place_time.model.*"%>
<%@ page import="com.members.model.*"%>
<%
	Place_timeService place_timeSVC = new Place_timeService();
	ArrayList<Place_timeVO> ptList = (ArrayList) place_timeSVC.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
	pageContext.setAttribute("ptList", ptList);
	Course_timeService course_timeSvc = new Course_timeService();
	ArrayList<Course_timeVO> cpList = (ArrayList) course_timeSvc.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < cpList.size(); i++) {
		((Course_timeVO) cpList.get(i)).setCrs_timeShow((String) getServletContext()
				.getAttribute(String.valueOf(((Course_timeVO) cpList.get(i)).getCrs_time())));
		((Course_timeVO) cpList.get(i)).getCourseVO().setCategoryChange((String) getServletContext()
				.getAttribute(((Course_timeVO) cpList.get(i)).getCourseVO().getCategory()));
	}
	pageContext.setAttribute("cpList", cpList);
%>
<%!int countp = 0;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">課程名稱</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">類別</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">報名截止日期</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">開課時間</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">價錢</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">場地</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">人數</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">修改</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">下架</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course_timeVO" items="${cpList}">
								<span class="hiddenCategory<%=countp%>" style="display: none;">${course_timeVO.courseVO.category}</span>
								<span class="hiddenCt_no<%=countp%>" style="display: none;">${course_timeVO.ct_no}</span>
								<span class="hiddenP_no<%=countp%>" style="display: none;">${course_timeVO.placeVO.p_no}</span>
								<span class="hiddenCrs_time<%=countp%>" style="display: none;">${course_timeVO.crs_time}</span>
								<tr>
									<span class="placeList<%=countp%>" style="display: none;">
										<select name="p_no" class="place<%=countp%>">
											<option value="null">無
												<c:forEach var="place_timeVO" items="${ptList}">
													<option value="${place_timeVO.p_no}">${place_timeVO.placeVO.p_name}
												</c:forEach>
									</select>
									</span>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><span class="changeCrs_name<%=countp%>">${course_timeVO.courseVO.crs_name}</span></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><span class="changeCategory<%=countp%>">${course_timeVO.courseVO.categoryChange}</span></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><span class="changeDeadline<%=countp%>">${course_timeVO.deadline}</span></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><span class="changeCrs_date_time<%=countp%>">${course_timeVO.crs_date}<br>${course_timeVO.crs_timeShow}</span></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><span class="changePrice<%=countp%>">${course_timeVO.price}</span></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><span class="changeP_name<%=countp%>">${course_timeVO.placeVO.p_name}</span></td>
									<!-- 故意擺兩個 -->
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><a class="accordion-toggle" data-toggle="collapse"
										data-parent="#accordion" href="#collapse<%=countp%>">${course_timeVO.count}/${course_timeVO.limit}</a></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><input type="button" class="btn btn-primary"
										id="update<%=countp%>" onclick="changeDisabled(3,<%=countp%>)"
										value="我要修改">
										<button class="btn btn-warning" id="updateCommit<%=countp%>"
											onclick="changeDisabled(4,<%=countp%>)"
											style="display: none;">確認修改</button></td>
									<td class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">
									<input type="submit" class="btn btn-warning" onclick="deleteCrstime()" value="下架">
									<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/CCM/CourseManager.do" style="display: none;">
											<input type="submit" class="btn btn-warning" id="deleteCrstime" value="下架">
											<input type="hidden" name="ct_no"
												value="${course_timeVO.ct_no}"><input type="hidden"
												name="action" value="course_timeDelete">
										</FORM></td>
								</tr>
								<%
									countp++;
								%>
							</c:forEach>
						</tbody>
					</table>
					<!-- /.table-responsive -->
				</div>
				<!-- /col-sm-12 -->
			</div>
			<!-- /row -->
		</div>
</body>
</html>