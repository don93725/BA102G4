<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>

<title>健貨後台管理系統</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				審核管理 <small> <i class="icon-double-angle-right"></i>
					開版審核
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
<div class="row">
		<div class="col-xs-12 col-sm-10 col-sm-offset-1">
			<div class="table-responsive">
				<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
<!-- 								<th class="center">編號</th> -->
								<th class="center" style="vertical-align:middle;">論壇</th>
								<th class="center" style="vertical-align:middle;">介紹</th>
								<th class="center" style="vertical-align:middle;">申請原因</th>
								<th class="center" style="vertical-align:middle;">審核狀態</th>
								<th class="center" style="vertical-align:middle;">審核決定</th>
							</tr>
						</thead>
					<c:forEach var="forum" items="${forums }">
						<tbody>
							<tr>
<%-- 								<td class="center">${forum.forum_no }</td> --%>
								<td class="center" style="vertical-align:middle;"><a
										href='${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${forum.forum_no }'>${forum.forum_name }</a></td>
								<td class="center" style="vertical-align:middle;">${forum.forum_desc }</td>
								<td class="center" style="vertical-align:middle;">${forum.forum_note}</td>
								<td class="center" style="vertical-align:middle;">${forum.forum_stat == 0?'未審核':''}</td>
								<td class="center" style="vertical-align:middle;">
									<a href='${pageContext.request.contextPath}/forum/ForumsManagerCtrl?action=confirm&forum_no=${forum.forum_no }&forum_stat=1&thisPage=${thisPage }&rcv_no=${forum.mem_no}'><button class="btn btn-primary btn-sm" >通過</button></a>
									<a href='${pageContext.request.contextPath}/forum/ForumsManagerCtrl?action=confirm&forum_no=${forum.forum_no }&forum_stat=2&thisPage=${thisPage }'><button class="btn btn-danger btn-sm">不通過</button></a>
								</td>

							</tr>
						</tbody>
					</c:forEach>
				</table>
				<center>
					<jsp:include page="/back_end/forum/ChangePage.jsp"></jsp:include>
				</center>



</div>
</div>
</div>
</div>

		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>