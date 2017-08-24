<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.course_list.model.*"%>
<%@ page import="com.course_picture.model.*"%>
<%@ page import="com.place_time.model.*"%>
<%@ page import="com.members.model.*"%>
<%
	Place_timeService place_timeSVC = new Place_timeService();
	ArrayList<Place_timeVO> plist = (ArrayList) place_timeSVC.getAllUsed(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < plist.size(); i++) {
		plist.get(i).setRp_timeShow((String) getServletContext()
				.getAttribute(String.valueOf(((Place_timeVO) plist.get(i)).getRp_time())));
	}
	pageContext.setAttribute("plist", plist);
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>CourseList</title>
</head>
<%!int count = 0;%>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">場地名稱</th>
								<th>尾款金額</th>
								<th>付費狀態</th>
								<th>付費</th>
								<th>檢舉場地</th>
								<th>評價場地</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="place_timeVO" items="${plist}">
								<tr style="font-weight: bold; font-size: 16px;">
									<td class="center"><a herf=""><font
											style="font-weight: bold; font-size: 18px; cursor: pointer;"
											onmouseover="showBlock('${place_timeVO.pt_no}')"
											onmouseout="hideBlock()">${place_timeVO.placeVO.p_name}</font></a></td>
									<td>$${place_timeVO.pau_price}</td>
									<td>
										<c:if test="${place_timeVO.pau_date == null}">
											<font color='red' style="font-weight:bold;font-size:16px" id="payStatus<%= count %>">未繳費</font>
										</c:if>
										<c:if test="${place_timeVO.pau_date != null}">
											<font color='blue' style="font-weight:bold;font-size:16px" id="payStatus<%= count %>">已繳費</font>
										</c:if>
									</td>
									<td>
										<button class="btn btn-warning pay<%= count %>" onclick="payment(<%= count %>)" style="border-radius:6px;">前往付款</button>
										<a href="#" onclick="window.open('<%=request.getContextPath()%>/front_end/CPM/PayAfter.jsp?pt_no=${place_timeVO.pt_no}','YOOO', config='height=500,width=516');"><button id="pay<%= count %>" style="display:none;">前往付款</button>
									</td>
									<td>
										<c:if test="${place_timeVO.report == 1 || place_timeVO.report == 2}">
											<a href=""><font
												style="font-weight: bold; font-size: 18px; cursor: pointer;"
												onmouseover="showReportBlock('${place_timeVO.pt_no}')"
												onmouseout="hideLeaveBlock()">已檢舉</font></a>
										</c:if> 
										<c:if test="${place_timeVO.report == 0}">
											<button class="btn btn-danger"
												onclick="report('${place_timeVO.pt_no}','${place_timeVO.p_no}')"
												style="border-radius: 6px;">檢舉場地</button>
										</c:if>
									</td>
									<td>
										<c:if test="${place_timeVO.eva == 0}">
											<button class="btn btn-primary"
												onclick="eva(<%=count%>)" style="border-radius: 6px;">評價場地</button>
										</c:if> <c:if test="${place_timeVO.eva != 0}">
											<button class="btn btn-primary"
												onclick="eva(<%=count%>)" style="border-radius: 6px;"
												disabled="true">評價場地</button>
										</c:if>
									</td>

									<a href="#"
										onclick="window.open('<%=request.getContextPath()%>/front_end/CPM/Evaluation.jsp?pt_no=${place_timeVO.pt_no}', 'YOOO', config='height=400,width=500');"><button
											id="evaluation<%=count%>" style="display: none;">評價課程</button></a>
								</tr>
								<button style="display:none;" class="paybtn" onclick="payStatus(<%= count %>)">6666</button>
								<%
									count++;
								%>
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