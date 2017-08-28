<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.adapply.model.*"%>
<jsp:useBean id="ADapplyMemSvc" scope="page" class="com.members.model.MembersService"/>

<%
	AD_ApplyService ADSvc = new AD_ApplyService();
	Set<AD_ApplyVO> list = ADSvc.getStat(0);
	pageContext.setAttribute("list", list);
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
				�s�i�ӽмf�� <small> <i class="icon-double-angle-right"></i>
					setCheckAD.jsp
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
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
<!-- 								<th class="center">�s�i�s��</th> -->
<!-- 								<th>�|���y����</th> -->
								<th class="center">�s�i�W��</th>
<!-- 								<th class="center">�I�O���</th> -->
								<th class="center">�s�i�s������</th>
								<th class="center">�s�i�Z�n���</th>
								<th class="center">�s�i�U�[���</th>
								<th class="center">�s�i�ԭz</th>
<!-- 								<th class="center">�s�i�Ϥ�</th> -->
								<th class="center">���A</th>
								<th class="center">�֭�</th>
								<th class="center">��^</th>
							</tr>
						</thead>
						<tbody>
							<%@ include file="page1.file"%>
							<c:forEach var="ad_ApplyVO" items="${list}"
								begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr align="center" valign="middle">
<%-- 									<td class="center">AD-NO.${ad_ApplyVO.ad_no}</td> --%>
<%-- 									<td class="center">${ad_ApplyVO.mem_no}</td> --%>
<%-- 									<td class="center">${ADapplyMemSvc.getOneMem(ad_ApplyVO.mem_no).mem_acc}</td> --%>
									<td class="center">${ad_ApplyVO.ad_name}</td>
<%-- 									<td class="center">${ad_ApplyVO.pay_date}</td> --%>
									<c:if test="${ADapplyMemSvc.getOneMem(ad_ApplyVO.mem_no).mem_rank==1}">
									<td class="center"><a href="<%=request.getContextPath()%>/front_end/CourseDetails/courseInfo.jsp?${ad_ApplyVO.ad_url}">${ad_ApplyVO.ad_name}</td>
									</c:if>
									<td class="center">${ad_ApplyVO.ad_ondate}</td>
									<td class="center">${ad_ApplyVO.ad_offdate}</td>
									<td class="center">${ad_ApplyVO.ad_ctx}</td>
<!-- 									<td class="center"><img -->
<%-- 										src="<%=request.getContextPath()%>/tools/DBGifReader?ad_no=${ad_ApplyVO.ad_no}" --%>
<!-- 										style="width: 100px; height: 100px;"></td> -->
									<td class="center">${ad_ApplyVO.arv_stat==0?'���f��':'�w�f��'}</td>
									
									<td class="center">
										<form method="post"
											action="<%=request.getContextPath()%>/adapply/AD_ApplyCtrl">
											<input type="submit" class="btn btn-primary" value="�֭�"> 
											<input type="hidden" name="ad_no" value="${ad_ApplyVO.ad_no}"> 
											<input type="hidden" name="rcv_no" value="${ad_ApplyVO.mem_no}"> 
											<input type="hidden" name="action" value="OK_AD">
										</form>
									</td>

									<td class="center"><form method="post"
											action="<%=request.getContextPath()%>/adapply/AD_ApplyCtrl">
											<input type="submit" class="btn btn-danger" value="��^"> 
											<input type="hidden" name="ad_no" value="${ad_ApplyVO.ad_no}">
											<input type="hidden" name="rcv_no" value="${ad_ApplyVO.mem_no}">  
											<input type="hidden" name="action" value="NO_AD">
										</form>
									</td>
								</tr>
							</c:forEach>
							<%@ include file="page2.file"%>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /col-sm-12 -->
		</div>
		<!-- /row -->
	</div>
	
		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>