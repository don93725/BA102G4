<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>健貨 - GymHome</title>
		<%@include file="/front_end/include/basicScript.file" %>
</head>
<!-- 網頁路徑 -->
<script>
	var webCtx ;
	$(function(){
 		var path = window.location.pathname;
 		webCtx = path.substring(0, path.indexOf('/', 1));
	})
</script>

<!-- 燈箱 -->
<script>
$(document).ready(function(){
	$(".inline").colorbox({inline:true, width:"40%"});
});
</script>

<body>

	<!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
	
	<!-- Page Content -->
	<div class="container" style="min-height: 100%">

	<!-- 新增場地視窗 -->
	<%@include file="/front_end/include/insertPlace.file" %>	
	<!-- 新增場地視窗結束 -->
	<!-- 修改場地視窗 -->
	<%@include file="/front_end/include/updatePlace.file" %>	
	<!-- 修改場地視窗結束 -->
	<!-- 修改場地圖片視窗 -->
	<%@include file="/front_end/include/insertPlacePic.file" %>	
	<!-- 修改場地圖片視窗結束 -->
	<!-- 上架場地視窗 -->
	<%@include file="/front_end/include/publishPlace.file" %>	
	<!-- 上架場地視窗結束 -->

		<!-- Page Heading/Breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<div class="col-lg-12">
				<h1>
					租借管理 <small>LeasingManagement</small>
				</h1>
			</div>

			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首頁</a></li>
				<li class="active">租借管理</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		<br>
			
		<!-- Content Row -->
		<div class="row">
			<div class="col-lg-12">	
    
			<div class="tabbable">
					<!-- tab那一欄 -->
					<ul class="nav nav-tabs" id="myTab">
						
						<!-- 上/下架管理 -->
						<li class="dropdown" id = ""><a data-toggle="dropdown"
							class="dropdown-toggle" href="#">
							租借管理 &nbsp;<i class="icon-caret-down bigger-110 width-auto"></i></a> <!-- 課程管理細項 -->
							<ul class="dropdown-menu dropdown-info">
								<li>
								<a data-toggle="tab" href="#" onclick="dropdown(1)">場地列表</a>
									<form action="<%= request.getContextPath() %>/PlaceServlet" method="post" style="display: none">
										<input type="submit" class="btn btn-default" id="dropdown1" role="button" value="場地列表">
										<input type="hidden" name="action" value="placeList">
									</form>
								</li>
								
								<li><a data-toggle="tab" href="" onclick="dropdown(2)">已下架</a>
									<form action="<%= request.getContextPath() %>/PlaceServlet" method="post" style="display: none">
										<input type="submit" class="btn btn-default" id="dropdown2" role="button" value="已下架">
										<input type="hidden" name="action" value="placeList">
										<input type="hidden" name="placeList_status" value="0">
									</form>
								</li>
								<li><a data-toggle="tab" href="" onclick="dropdown(3)">已上架</a>
									<form action="<%= request.getContextPath() %>/PlaceServlet" method="post" style="display: none">
										<input type="submit" class="btn btn-default" id="dropdown3" role="button" value="已上架">
										<input type="hidden" name="action" value="placeList">
										<input type="hidden" name="placeList_status" value="1">
									</form>
								</li>
								</li>
							</ul>
						</li>
					</ul>


					<!-- 內容全在這裡 -->
					<div class="tab-content page" style="background-color:white;">
						<jsp:include page="${page}" />
					</div>
					<!-- 內容全在這裡 結束 -->


			</div>
			<!-- col-lg-12 -->
			<div class="vspace-xs-6"></div>
		</div>
		<!-- /row -->
</div>
</div>
	
			
	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
