<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.place_pic.model.*"%>
<%@ page import="com.place.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	PlaceVO pp = ((PlaceVO)request.getAttribute("placeVO"));
	Place_PicService sv = new Place_PicService();
	List list = sv.getAllPPic(pp.getP_no());
	pageContext.setAttribute("piclist", list);
%>
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
	function hello() {
		$('.inline').colorbox({
			inline: true,
			width: "50%",
		});
		var start = '#datepicker';
			  $(start).datepicker({
			    dateFormat: 'yy-mm-dd',
			    minDate : +1
			  });
	}
</script>
<body>
<!-- 我要預定視窗 -->
<%@include file="/front_end/include/orderPlace.file" %>
<!-- 新增場地視窗結束 -->
<!-- googleMap -->
<%@include file="/front_end/include/googleMap2.file" %>
<!-- googleMap結束 -->
	
	<!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
	
	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<div class="col-lg-12">
				<h1>
					場地詳情 <small>場地詳情</small>
				</h1>
			</div>

			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首頁</a></li>
				<li class="active">服務介紹</li>
				<li class="active">找場地</li>
				<li class="active">場地詳情</li>
			</ul>
			<!-- .breadcrumb -->
		</div>

		<!-- 場地詳情 -->
		<!-- Page Content -->
    <div class="container">
    	<div class="row">
    		<div class="col-lg-12">
    			<h2>${placeVO.p_name}</h2>
    		</div>
    		
			<div class="col-xs-12 col-lg-7" style="position:relative;">
				<div class="item">
					<img id="show-image" src="${placeVO.place_picVO.p_base} " style="width:100%;">
				</div>
			</div>
			<div class="col-lg-1 abgne-block-20120106">
				<c:forEach var="pic" items="${piclist}">
					<a href="${pic}"><img src="${pic}" style="width:80%;display:block;margin-bottom:1em;"></a>
				</c:forEach>
			</div>
			<!-- 顯示縮圖 -->
			<script>
			$(function(){
				// 用來顯示大圖片用
				var $showImage = $('#show-image');
			 
				// 當滑鼠移到 .abgne-block-20120106 中的某一個超連結時
				$('.abgne-block-20120106 a').mouseover(function(){
					// 把 #show-image 的 src 改成被移到的超連結的位置
					$showImage.attr('src', $(this).attr('href'));
				}).click(function(){
					// 如果超連結被點擊時, 取消連結動作
					return false;
				});
			});
			</script>
			
			<div class="col-xs-12 col-lg-4" style="height:300px;font-size:18px;background-color:white;">
				<div class="item">
					<div>
						<p style="color:red;">訂金：&nbsp$${placeVO.place_publishVO.pbu_price}</p>
					</div>
					<div>
						<p style="color:red;">尾款：&nbsp$${placeVO.place_publishVO.pau_price}</p>
					</div>
					<div>
						<p>場館名稱：&nbsp${placeVO.p_name}</p>
					</div>
					<div>
						<p>場館介紹：&nbsp${placeVO.p_into}</p>
					</div>
					<div>
						<p>場館地址：&nbsp${placeVO.p_add}
						<a class='inline' href="#map_content" onclick="initMap('${placeVO.p_latlng}')" value="新增圖片">
    						<i class="icon-map"></i> 查看位置
    					</a>
						
						
					</div>
				</div>
			</div>
		</div>
		
		
		<div class="row">		
			<center>
			<div class="col-xs-12 col-sm-12" style="background-color:white;margin-top:3em;border:2px #ccc solid;border-radius:6px;padding: 0px;">
				<div class="col-xs-12 col-sm-4">
					<p style="border-right:2px #ccc solid;margin-top:1em;">
					<span style="position:relative;right:1em;">
						<i class="glyphicon glyphicon-star-empty" style="font-size:16px;"></i>
						<font color="orange" style="font-size:16px;font-weight:bold;">
							<c:out value="${eva.crsAvg == 0?null:eva.crsAvg}" default="尚無評價"  />
						</font><br>
						<font style="font-size:18px;">場地評價</font>
					</span>						
					</p>
				</div>
				
				<div class="col-xs-12 col-sm-4">
					<p style="border-right:2px #ccc solid;margin-top:1em;">
					<span style="position:relative;right:1em;">
						<i class="glyphicon glyphicon-user" style="font-size:16px;"></i><br>
						<font style="font-size:18px;">可容納&nbsp${placeVO.p_cap}&nbsp人</font>
					</span>
					</p>
				</div>
				<c:choose>
					<c:when test="">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">馬上租借</font>
							</p>
						</div>
					</c:when>
					<c:when test="">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">人數已滿</font>
							</p>
						</div>
					</c:when>
					<c:when test="">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">已報名</font>
							</p>
						</div>
					</c:when>
					<c:when test="">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">請先登入</font>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-xs-12 col-sm-4" id="signUp" style="cursor: pointer;">
							<a class='inline' href="#order_content" onclick="hello();">
								<p style="margin-top:1em;">
								<font style="font-size:30px;" id="signUpFont">馬上租借</font>
								</p>
							</a>
						</div>	
					</c:otherwise>
				</c:choose>
			</div>
			</center>
		</div>
		
		<div class="row" style="margin-top:3em;">
			<div class="col-sm-12" style="padding-left:0px;padding-right:0px;">
				
					<ul class="nav nav-tabs" id="myTab">
						<li class="active" style="width:14em;">
							<a data-toggle="tab" href="#xxx">
								<i class="green icon-home bigger-110" style="font-size:20px;"></i>
								<font style="font-size:20px;">課程行事曆</font>
							</a>
						</li>

						<li style="width:14em;">
							<a data-toggle="tab" href="#profile">
								<i class="red glyphicon glyphicon-bullhorn" style="font-size:20px;"></i>
								<font style="font-size:20px;">問與答</font>
									<span class="badge badge-danger" style="font-size:16px;">${course_timeVO.cmtNum}</span>
							</a>
						</li>

						<li style="width:14em;" id="shitMap" onclick="initMap(${course_timeVO.placeVO.p_latlng})">
							<a data-toggle="tab" href="#placedetail">
								<i class="blue glyphicon glyphicon-map-marker" style="font-size:20px;"></i>
								<font style="font-size:20px;" >場地資訊</font>
							</a>
						</li>
					</ul>

					<div class="tab-content" >	
						<div class="row tab-pane active" id="xxx">
				<div class="col-sm-9">
					<div class="space"></div>

					<div id="calendar"></div>
				</div>

				<div class="col-sm-3">
					<div class="widget-box transparent">
						<div class="widget-header">
							<h4>時段:</h4>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<div id="external-events">


									<div class="external-event label-success"
										data-class="label-success">
										<i class="glyphicon glyphicon-search"></i> 08:00-09:30
									</div>

									<div class="external-event label-danger"
										data-class="label-danger">
										<i class="glyphicon glyphicon-search"></i> 10:00-11:30
									</div>

									<div class="external-event label-purple"
										data-class="label-purple">
										<i class="glyphicon glyphicon-search"></i> 13:00-14:30
									</div>

									<div class="external-event label-yellow"
										data-class="label-yellow">
										<i class="glyphicon glyphicon-search"></i> 15:00-16:30
									</div>

									<div class="external-event label-pink" data-class="label-pink">
										<i class="glyphicon glyphicon-search"></i> 18:00-19:30
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="glyphicon glyphicon-search"></i> 20:00-21:30
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
						<p></p>
						<div id="profile" class="tab-pane">
							<%@include file="/front_end/CourseDetails/person_comments.file" %>
						</div>
						<c:if test="${course_timeVO.placeVO.p_no != null }">
							<div id="placedetail" class="tab-pane">
								<div class="map" style="height: 800px;width: 1100px;margin: 0px;padding: 0px;">     
									<div id="map" style="height: 100%;">
									</div>
								</div>
							</div>
						</c:if>
						<c:if test="${course_timeVO.placeVO.p_no == null }">
							<div id="placedetail" class="tab-pane">
								<font style="font-size:40px;" >教練並沒有選擇場地,嗚嗚嗚嗚</font><br>
								<img src="<%= request.getContextPath()%>/front_end/CourseDetails/images/don.jpg" width="900" height="600">
							</div>
						</c:if>
					</div>
				
			</div><!-- /span -->
			
		</div><!-- row end -->
		
		
</div>
</div>
	
			
	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>