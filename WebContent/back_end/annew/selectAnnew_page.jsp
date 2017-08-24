<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.annew.model.*"%>
<%
AnnewVO annewVO = (AnnewVO) request.getAttribute("annewVO");
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
			公告查詢  <i class="icon-double-angle-right"></i>
						
			<h1>
				公告查詢  <small><i class="icon-double-angle-right"></i> Announce Search</small>
						
				
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
					
						
						<p></p>
						
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
						<div class='col-sm-12'>
						<a href='listAllAnnew.jsp'><button class='btn btn-default'>公告總覽</button></a>
						<a href='addAnnew.jsp'><button class='btn btn-default'>新增公告</button></a>
						</div>
						  
						  
						    <FORM METHOD="post" ACTION="annew.do" >
						      <label for="exampleInputEmail1"> <img src="images/flower.gif"><b>輸入標題<img src="images/flower.gif">:</b></label>
						       <div class='col-sm-12'>
						       <div class='col-sm-6'>
						       		<div class="input-group">
								  <input type="text" class='form-control' name="ann_title" style="background-color:#FFDEDE;">
								  <div class="input-group-btn">
								    <input class='btn btn-default form-control' type="submit" value="送出">
								  </div>
								</div>
						       </div>
						       </div>
						        
						        
						        <input type="hidden" name="action" value="getPart_By_Title">
						    </FORM>
						  
						    <FORM METHOD="post" ACTION="annew.do" >
						        <label for="exampleInputEmail1"><img src="images/flower.gif"><b>輸入日期<img src="images/flower.gif">:</b></label>
						       <div class='col-sm-12'>
						       <div class='col-sm-6'>
						       		<div class="input-group">
						       		<input type="date" class='form-control' name="ann_date" style="background-color:#D6D6FF;">
								  <div class="input-group-btn">
								  	<input class='btn btn-default form-control' type="submit" value="送出">
								  </div>
								</div>
						       </div></div>
						        
						        
						        <input type="hidden" name="action" value="getPart_By_Date">
						    </FORM>
						  
						  
						  
						
	</div>
</div>
<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>

</html>
