<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

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
	<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl"
			name="form1" enctype='multipart/form-data'>
			<div class="row"  style="height:40px;">
				<div class="col-md-12 col-md-offset-1 ">

					<div class="col-md-2">
						<div class="input-group">
							<input type="text"
								name="mgr_no" class="form-control" placeholder="請輸入編號" style="height:34px;">
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<input type="text"
								name="mgr_name" class="form-control" placeholder="請輸入姓名" style="height:34px;">
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							
							<select name="mgr_job" style="height:34px;width:145px;">
								<option value="">請選擇職位
								<option value="0">管理員		
								<option value="1">系統管理員		
							</select>
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							
								<select name="mgr_status" style="height:34px;width:145px;">
									<option value="">請選擇狀態	
									<option value="0">帳號起用中		
									<option value="1">帳號以停權		
								</select>
						</div>
					</div>
					<div class="col-md-2">
						<button type="submit" class="btn btn-sm btn-info"><i class="icon-search nav-search-icon"></i>查詢</button>
						<input type="hidden" name="action" value="listMgr_ByCompositeQuery">
					</div>
								
				</div>
			</div>
		</FORM>
	
	<%-- 錯誤表列 --%>
	<c:if test="${not empty errorMsgs}">
		<font color='red'>請修正以下錯誤:
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