<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

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
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl"
			name="form1" enctype='multipart/form-data'>
			<div class="row"  style="height:40px;">
				<div class="col-md-12 col-md-offset-1 ">

					<div class="col-md-2">
						<div class="input-group">
							<input type="text"
								name="mgr_no" class="form-control" placeholder="�п�J�s��" style="height:34px;">
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<input type="text"
								name="mgr_name" class="form-control" placeholder="�п�J�m�W" style="height:34px;">
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							
							<select name="mgr_job" style="height:34px;width:145px;">
								<option value="">�п��¾��
								<option value="0">�޲z��		
								<option value="1">�t�κ޲z��		
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							
								<select name="mgr_status" style="height:34px;width:145px;">
									<option value="">�п�ܪ��A	
									<option value="0">�b���_�Τ�		
									<option value="1">�b���w���v		
								</select>
						</div>
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-sm btn-info"><i class="icon-search nav-search-icon"></i>�d��</button>
						<input type="hidden" name="action" value="listMgr_ByCompositeQuery">
					</div>
								
				</div>
			</div>
		</FORM>
	
	<%-- ���~��C --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>�Эץ��H�U���~:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
<c:if test="${param.action !='listMgr_ByCompositeQuery'}">
	<jsp:include page="/back_end/mgr/listAllMgrForSelectPage.jsp" />
</c:if>		

	
<c:if test="${not empty listMgr_ByCompositeQuery}">			
	<jsp:include page="/back_end/mgr/listMgr_ByCompositeQueryForSelectPage.jsp" />		
</c:if>						
					
					
<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>