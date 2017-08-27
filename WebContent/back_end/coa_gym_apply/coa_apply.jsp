<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.coaches.model.*"%>


<%
	CoachesService coaSvc = new CoachesService();
	List<CoachesVO> list = coaSvc.getAllBySta(0);
	pageContext.setAttribute("list",list);
%>

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
				帳號審核 <small> <i class="icon-double-angle-right"></i>
					教練審核
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

		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<!-- 			<th>檢舉編號</th> -->
								<th class="center">照片</th>
								<th class="center">帳號</th>
								<th class="center">姓名</th>
								<th class="center">性別</th>
								<th class="center">身分證字號</th>
								<th class="center">E-mail</th>
<!-- 								<th class="center">簡介</th> -->
								
								<th class="center">核准</th>
								<th class="center">駁回</th>
							</tr>
						</thead>
						<tbody> 
							<%@ include file="page1.file"%>
							<c:forEach var="coachesVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr align="center" valign="middle">
									<td><img src="<%=request.getContextPath()%>/tools/DBGifReader?coa_acc=${coachesVO.coa_acc}"
											style="width: 100px; height: 100px;"></td>
									<td class="center">${coachesVO.coa_acc}</td>
									<td class="center">${coachesVO.coa_name}</td>
									<td class="center">${coachesVO.coa_sex == 1 ?'男':'女'}</td>
									<td class="center">${coachesVO.coa_mail}</td>
<%-- 									<td class="center">${coachesVO.coa_into}</td> --%>
									<td class="center">${coachesVO.coa_sta == 0 ?'未審核':''}</td>
									<td class="center">
										<form method="post"
											action="<%=request.getContextPath()%>/CoaGymApplyCtrl">
											<input type="submit" class="btn btn-primary" value="核准"> 
											<input type="hidden"name="coa_acc" value="${coachesVO.coa_acc}"> 
											<input type="hidden" name="coa_no" value="${coachesVO.coa_no}"> 
											<input type="hidden" name="action" value="CoaApply_ok">
										</form>
									</td>

									<td class="center"><form method="post"
											action="<%=request.getContextPath()%>/CoaGymApplyCtrl">
											<input type="submit" class="btn btn-danger" value="駁回"> 
											<input type="hidden" name="coa_acc" value="${coachesVO.coa_acc}">
											<input type="hidden" name="action" value="CoaApply_no">
										</form>
									</td>
								</tr>
							</c:forEach>
						<%@ include file="page2.file"%>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>



		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>