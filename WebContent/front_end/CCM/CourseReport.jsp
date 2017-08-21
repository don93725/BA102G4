<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Course_time.model.*"%>
<%@ page import="com.place_time.model.*"%>
<%@ page import="com.members.model.*"%>
<%
	Place_timeService place_timeSVC = new Place_timeService();
	ArrayList<Place_timeVO> ptList = (ArrayList) place_timeSVC.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
	pageContext.setAttribute("ptList", ptList);
	Course_timeService course_timeSvc = new Course_timeService();
	ArrayList<Course_timeVO> cpList = (ArrayList) course_timeSvc.getAllRecord(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < cpList.size(); i++) {
		((Course_timeVO) cpList.get(i)).setCrs_timeShow((String) getServletContext()
				.getAttribute(String.valueOf(((Course_timeVO) cpList.get(i)).getCrs_time())));
		((Course_timeVO) cpList.get(i)).getCourseVO().setCategoryChange((String) getServletContext()
				.getAttribute(((Course_timeVO) cpList.get(i)).getCourseVO().getCategory()));
	}
	pageContext.setAttribute("cpList", cpList);
%>
<%!int count = 0;%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">課程名稱</th>
								<th>類別</th>
								<th>開課日期-時段</th>
								<th>價錢</th>
								<th>場地</th>
								<th>人數</th>
								<th>觀看詳情</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="course_timeVO" items="${cpList}">
								<span class="hiddenCategory<%=count%>" style="display: none;">${course_timeVO.courseVO.category}</span>
								<span class="hiddenCt_no<%=count%>" style="display: none;">${course_timeVO.ct_no}</span>
								<span class="hiddenP_no<%=count%>" style="display: none;">${course_timeVO.placeVO.p_no}</span>
								<span class="hiddenCrs_time<%=count%>" style="display: none;">${course_timeVO.crs_time}</span>
								<tr>
									<span class="placeList<%=count%>" style="display: none;">
										<select name="p_no" class="place<%=count%>">
											<option value="null">無
												<c:forEach var="place_timeVO" items="${ptList}">
													<option value="${place_timeVO.p_no}">${place_timeVO.placeVO.p_name}
												</c:forEach>
									</select>
									</span>
									<td class="center"><span class="changeCrs_name<%=count%>">${course_timeVO.courseVO.crs_name}</span></td>
									<td><span class="changeCategory<%=count%>">${course_timeVO.courseVO.categoryChange}</span></td>
									<td><span class="changeCrs_date_time<%=count%>">${course_timeVO.crs_date}-${course_timeVO.crs_timeShow}</span></td>
									<td><span class="changePrice<%=count%>">${course_timeVO.price}</span></td>
									<td><span class="changeP_name<%=count%>">${course_timeVO.placeVO.p_name}</span></td>
									<!-- 故意擺兩個 -->
									<td><a class="accordion-toggle" data-toggle="collapse"
										data-parent="#accordion" href="#collapse<%=count%>">${course_timeVO.count}/${course_timeVO.limit}</a></td>
									<td><input type="button" class="btn btn-primary"
										id="see<%=count%>" onclick="slide(<%=count%>)"
										value="觀看詳情">
									</td>
								</tr>
								<tr>
									<td class="center selectStu<%=count%>" style="display:none;" colspan="2">照片</td>
									<td class="selectStu<%=count%>" style="display:none;">姓名(帳號)</td>
									<td class="selectStu<%=count%>" style="display:none;" colspan="2">Email</td>
									<td class="selectStu<%=count%>" style="display:none;" colspan="2"><input type="button" class="btn btn-warning"
										id="" onclick=""
										value="查看">
									</td>
								</tr>
								<%
									count++;
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