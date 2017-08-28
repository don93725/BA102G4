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
						
			<h1>
				頁面資訊維護  <small><i class="icon-double-angle-right"></i> 公告管理</small>
						
				
			</h1>
		</div>
		<!-- /.page-header -->
		<div class="container">
	<div class='row'>
						<p></p>

						<div class='col-sm-12'>
						
						<a href='listAllAnnew.jsp'><button class='btn btn-warning btn-sm'><i class="icon-eye-open bigger-120"></i> 公告總覽</button></a>
						<a href='addAnnew.jsp'><button class='btn btn-success btn-sm'><i class="icon-plus bigger-120"></i> 新增公告</button></a>
						</div>
						
						<%-- 錯誤表列 --%>
						<c:if test="${not empty errorMsgs}">
						<div class='col-sm-12'>
						<div>
							<font color='red'>請修正以下錯誤:
							<ul>
								<c:forEach var="message" items="${errorMsgs}">
									<li>${message}</li>
								</c:forEach>
							</ul>
							</font>
						</div>
						</div>
						</c:if>
						<div class="row" style="height:40px;"></div>
						
						<div class='col-sm-12'>
						    <FORM METHOD="post" ACTION="annew.do" >
						      <label for="exampleInputEmail1"> <img src="images/flower.gif"><b> 輸入標題 <img src="images/flower.gif"></b></label>
						       <div class='col-sm-12'>
						       

						       <div class="col-sm-6">
						       	<div class="input-group">
								  <input type="text" class='form-control' name="ann_title" placeholder="輸入標題蒐尋">
								  <div class="input-group-btn">
								    <input class='btn btn-default form-control btn-primary' type="submit" value="送出">
								  </div>
						       </div>
						       </div>
						        
						        <input type="hidden" name="action" value="getPart_By_Title">
						       </div>
						    </FORM>
						 </div>
						 
						 <div class='col-sm-12'> 
						    <FORM METHOD="post" ACTION="annew.do" >
						        <label for="exampleInputEmail1"><img src="images/flower.gif"><b> 輸入日期 <img src="images/flower.gif"></b></label>
						       <div class='col-sm-12'>
						       <div class='col-sm-6'>
						       		
						       	<div class="input-group">
						       		<input type="date" class='form-control' name="ann_date">
								  <div class="input-group-btn">
								  	<input class='btn btn-default form-control btn-primary' type="submit" value="送出">
								  </div>
								</div>
						       
						       </div>
						       </div>
						        <input type="hidden" name="action" value="getPart_By_Date">
						    </FORM>
						 </div>
						  
						  
		</div>				
	</div>
</div>
<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>

</html>
