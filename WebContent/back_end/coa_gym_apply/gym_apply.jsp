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

<title>���f��x�޲z�t��</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				�b���f�� <small> <i class="icon-double-angle-right"></i>
					�����мf��
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
<c:if test="${not empty errorMsgs}">
			<font color='red'>�ЭץH�U���~:
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
					<table id="sample-table-1" class="table table-striped table-hover" style="text-align:center;">
						<thead class="aaa">
							<tr>
								<!-- 			<th>���|�s��</th> -->
								<th class="center" style="vertical-align:middle;">�Ӥ�</th>
								<th class="center" style="vertical-align:middle;">�b��</th>
								<th class="center" style="vertical-align:middle;">�W��</th>
								<th class="center" style="vertical-align:middle;">��m</th>
								<th class="center" style="vertical-align:middle;">E-mail</th>
								<th  class="center" style="vertical-align:middle;">���A</th>
<!-- 								<th class="center">²��</th> -->
								<th class="center" style="vertical-align:middle;">�֭�</th>
								<th class="center" style="vertical-align:middle;">��^</th>
							</tr>
						</thead>
						<tbody> 
							<%@ include file="page1.file"%>
							<c:forEach var="gymsVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr align="center" valign="middle">
									<td class="center" style="vertical-align:middle;"><img src="<%=request.getContextPath()%>/tools/DBGifReader?gym_acc=${gymsVO.gym_acc}"
											style="width: 100px; height: 100px;"></td>
									<td class="center" style="vertical-align:middle;">${gymsVO.gym_acc}</td>
									<td class="center" style="vertical-align:middle;">${gymsVO.gym_name}</td>
									<td class="center" style="vertical-align:middle;">${gymsVO.gym_add}</td>
									<td class="center" style="vertical-align:middle;">${gymsVO.gym_mail}</td>
<%-- 									<td class="center">${gymsVO.gyms_into}</td> --%>
									<td class="center" style="vertical-align:middle;">${gymsVO.gym_sta == 0 ?'���f��':''}</td>
									<td class="center" style="vertical-align:middle;">
										<form method="post"
											action="<%=request.getContextPath()%>/CoaGymApplyCtrl">
											<input type="submit" class="btn btn-primary btn-sm" value="�֭�"> 
											<input type="hidden" name="gym_acc" value="${gymsVO.gym_acc}"> 
											<input type="hidden" name="gym_no" value="${gymsVO.gym_no}"> 
											<input type="hidden" name="action" value="GymApply_ok">
										</form>
									</td>

									<td class="center" style="vertical-align:middle;"><form method="post"
											action="<%=request.getContextPath()%>/CoaGymApplyCtrl">
											<input type="submit" class="btn btn-danger btn-sm" value="��^"> 
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