<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.gyms.model.*"%>


<%
	GymsService gymSvc = new GymsService();
	List<GymsVO> list = gymSvc.getAllBySta(0);
	pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<title>Insert title here</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				帳號審核 <small> <i class="icon-double-angle-right"></i>
					健身房審核
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
								<th class="center">名稱</th>
								<th class="center">位置</th>
								<th class="center">E-mail</th>
								<th class="center">狀態</th>
<!-- 								<th class="center">簡介</th> -->
								<th class="center">核准</th>
								<th class="center">駁回</th>
							</tr>
						</thead>
						<tbody> 
							<%@ include file="page1.file"%>
							<c:forEach var="gymsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr align="center" valign="middle">
									<td><img src="<%=request.getContextPath()%>/tools/DBGifReader?gym_acc=${gymsVO.gym_acc}"
											style="width: 100px; height: 100px;"></td>
									<td class="center">${gymsVO.gym_acc}</td>
									<td class="center">${gymsVO.gym_name}</td>
									<td class="center">${gymsVO.gym_add}</td>
									<td class="center">${gymsVO.gym_mail}</td>
<%-- 									<td class="center">${gymsVO.gyms_into}</td> --%>
									<td class="center">${gymsVO.gym_sta == 0 ?'未審核':''}</td>
									<td class="center">
										<form method="post"
											action="<%=request.getContextPath()%>/CoaGymApplyCtrl">
											<input type="submit" value="核准"> 
											<input type="hidden"name="gym_acc" value="${gymsVO.gym_acc}"> 
											<input type="hidden" name="gym_no" value="${gymsVO.gym_no}"> 
											<input type="hidden" name="action" value="GymApply_ok">
										</form>
									</td>

									<td class="center"><form method="post"
											action="<%=request.getContextPath()%>/CoaGymApplyCtrl">
											<input type="submit" value="駁回"> 
											<input type="hidden" name="gym_acc" value="${gymsVO.gym_acc}"> 
											<input type="hidden" name="gym_no" value="${gymsVO.gym_no}"> 
											<input type="hidden" name="action" value="GymApply_no">
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