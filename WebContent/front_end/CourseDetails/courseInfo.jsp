<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.course_time.model.*" %>
<%@ page import="com.course_list.model.*" %>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<% 
	Course_timeService course_timeSVC = new Course_timeService();
	Course_listService course_listSVC = new Course_listService();
	
	Course_listVO eva = course_listSVC.getEva(request.getParameter("ct_no"));
	
	if(session.getAttribute("user")==null){
		pageContext.setAttribute("noLoginNow", 0);
	}else{
		boolean signable = course_listSVC.signable(request.getParameter("ct_no"),((MembersVO) session.getAttribute("user")).getMem_acc());
		pageContext.setAttribute("signable", signable);
	}
	
	Course_timeVO course_timeVO = course_timeSVC.getOneCourse_time(request.getParameter("ct_no"));
		course_timeVO.setCrs_timeShow((String) getServletContext()
				.getAttribute(String.valueOf((course_timeVO.getCrs_time()))));
		course_timeVO.getCourseVO().setCategoryChange((String) getServletContext()
				.getAttribute((course_timeVO.getCourseVO().getCategory())));
	List<Course_timeVO> calendarList = course_timeSVC.getAllByCrs_no(request.getParameter("crs_no"));
	
	for (int i = 0; i < calendarList.size(); i++) {
		((Course_timeVO) calendarList.get(i)).setCrs_timeShow((String) getServletContext()
				.getAttribute(String.valueOf(((Course_timeVO) calendarList.get(i)).getCrs_time())));
	}
	
	pageContext.setAttribute("course_timeVO", course_timeVO);
	pageContext.setAttribute("calendarList", calendarList);
	pageContext.setAttribute("eva", eva);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/fullcalendar.css" />
<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>
	
	
	
    <!-- Page Content -->
    <div class="container">
		<div class="row" style="position:relative;top:2em;">
			<div class="col-xs-12 col-sm-6" style="position:relative;top:1em;">
				<div class="item">
					<img src="${course_timeVO.courseVO.picList.get(0).getCrs_base()}" id="large" width="550">	
					<c:forEach var="couurse_pictureVO" items="${course_timeVO.courseVO.picList}"><img src="${couurse_pictureVO.crs_base }" class="small" width="20%" style="margin-top:1em;"></c:forEach>			
				</div>
			</div>			
			<div class="col-xs-12 col-sm-6" style="background-color:white;border:1px #ccc solid;position:relative;top:1em;">
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3><b>課程名稱:</b></h3>
						<p><font size="5px" style="position:relative;left:1em;">${course_timeVO.courseVO.crs_name}</font></p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3><b>指導教練:</b></h3>
						<p><font size="5px" style="position:relative;left:1em;">${course_timeVO.coachesVO.coa_name}</font></p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3><b>所在場館:</b></h3>
						<p><font size="5px" style="position:relative;left:1em;">${course_timeVO.placeVO.p_name}</font></p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item" style="height:10px;"></div>
				</div>
			</div>
			<div class="col-xs-12 col-sm-6"></div>
			<div class="col-xs-12 col-sm-6" style="background-color:white;border:1px #ccc solid;margin-top:1em;position:relative;top:1em;">
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<p><h3><b>課程詳情:</b></h3>
						<font style="font-size:20px;position:relative;left:1em;">${course_timeVO.courseVO.details}</font></p>
					</div>
				</div>
				
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3><b>截止日期:</b></h3></font>
						<font style="font-size:20px;position:relative;left:1em;">${course_timeVO.deadline}</font><br>
						<h3><b>上課日期:</b></h3></font>
						<font style="font-size:20px;position:relative;left:1em;">${course_timeVO.crs_date} ${course_timeVO.crs_timeShow}</font>
						<br><br><font style="font-size:22px;"><b>價錢:</b></font>
						<font color="#D60000" style="font-size:30px;left:1em;">$${course_timeVO.price}</font><br>
						<span style="visibility:hidden;"> 66</span>
					</div>
				</div>
			</div>
		</div><!-- row end -->
		<div class="row" style="margin-top:1em;">	
				
			<center>
			<div class="col-xs-12 col-sm-12" style="background-color:white;margin-top:3em;border:2px #ccc solid;border-radius:6px;padding: 0px;">
				<div class="col-xs-12 col-sm-2">
					<p style="border-right:2px #ccc solid;margin-top:1em;">
					<span style="position:relative;right:1em;">
						<i class="glyphicon glyphicon-star-empty" style="font-size:16px;"></i>
						<font color="orange" style="font-size:16px;font-weight:bold;">
							<c:out value="${eva.crsAvg == 0?null:eva.crsAvg}" default="尚無評價"  />
						</font><br>
						<font style="font-size:18px;">課程評價</font>
					</span>						
					</p>
				</div>
				<div class="col-xs-12 col-sm-2" >
					<p style="border-right:2px #ccc solid;margin-top:1em;">
					<span style="position:relative;right:1em;">
						<i class="glyphicon glyphicon-star-empty" style="font-size:16px;"></i> 
						<font color="orange" style="font-size:16px;font-weight:bold;">
							<c:out value="${eva.coaAvg == 0?null:eva.coaAvg}" default="尚無評價"  />
						</font><br>
						<font style="font-size:18px;">教練評價</font>
					</span>
					</p>
				</div>
				<div class="col-xs-12 col-sm-4">
					<p style="border-right:2px #ccc solid;margin-top:1em;">
					<span style="position:relative;right:1em;">
						<i class="glyphicon glyphicon-user" style="font-size:16px;"></i> <font color="orange" style="font-size:16px;font-weight:bold;">${course_timeVO.count}/${course_timeVO.limit}</font><br>
						<font style="font-size:18px;">開課條件(${course_timeVO.class_num}人)</font>
					</span>
					</p>
				</div>
				<c:choose>
					<c:when test="${user.mem_rank == 1}">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">教練,您好</font>
							</p>
						</div>
					</c:when>
					<c:when test="${course_timeVO.count == course_timeVO.limit}">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">人數已滿</font>
							</p>
						</div>
					</c:when>
					<c:when test="${signable}">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">已報名</font>
							</p>
						</div>
					</c:when>
					<c:when test="${noLoginNow == 0}">
						<div class="col-xs-12 col-sm-4" style="cursor: not-allowed;">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" color="gray">請先登入</font>
							</p>
						</div>
					</c:when>
					<c:otherwise>
						<div class="col-xs-12 col-sm-4" id="signUp" style="cursor: pointer;" onclick="signUpGo()">
							<p style="margin-top:1em;">
							<font style="font-size:30px;" id="signUpFont">馬上報名</font>
							</p>
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			</center>	
					
		</div><!-- row end -->
		<div class="row" style="margin-top:1em;">
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

						<li style="width:14em;" id="shitMap" onclick="initMap('${course_timeVO.placeVO.p_latlng}')">
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
							<div id="placedetail" class="tab-pane"><center>
								<font style="font-size:40px;" >教練並沒有選擇場地,嗚嗚嗚嗚</font><br>
								<img src="<%= request.getContextPath()%>/front_end/CourseDetails/images/don.jpg" width="900" height="600">
							</center></div>
						</c:if>
					</div>
				
			</div><!-- /span -->
			
		</div><!-- row end -->	
		
	</div>
	<!-- Footer -->
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
	<span style="display:none;">
		<c:forEach var="course_time" items="${calendarList}">
			<c:choose>
					<c:when test="${course_time.crs_time == 1}">
						<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name}','${course_time.crs_date}','label-success',8)">
					</c:when>
					<c:when test="${course_time.crs_time == 2}">
						<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name}','${course_time.crs_date}','label-danger',10)">
					</c:when>
					<c:when test="${course_time.crs_time == 3}">
						<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name}','${course_time.crs_date}','label-purple',14)">
					</c:when>
					<c:when test="${course_time.crs_time == 4}">
						<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name}','${course_time.crs_date}','label-yellow',16)">
					</c:when>
					<c:when test="${course_time.crs_time == 5}">
						<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name}','${course_time.crs_date}','label-pink',19)">
					</c:when>
					<c:otherwise>
						<input type="button" class="calendarbtn" onclick="addCalendar('${course_time.courseVO.crs_name}','${course_time.crs_date}','label-info',21)">
					</c:otherwise>
				</c:choose>
			
		</c:forEach>
	</span>
</body>
	<%@include file="/front_end/include/basicScript2.file" %>



		<script src="<%= request.getContextPath() %>/style/assets/js/typeahead-bs2.min.js"></script>

		<!-- page specific plugin scripts -->

		<script src="<%= request.getContextPath() %>/style/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
		<script src="<%= request.getContextPath() %>/style/assets/js/jquery.ui.touch-punch.min.js"></script>
		<script src="<%= request.getContextPath() %>/style/assets/js/fullcalendar.min.js"></script>
		<script src="<%= request.getContextPath() %>/style/assets/js/bootbox.min.js"></script>


		<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAp7jp6Eg1dQZxn6Fi6h4V34jLnbipVfVU&callback=initMap&language=zh-TW"
    async defer></script>

</html>
<script>
	var large = document.getElementById("large");
	var signUp = document.getElementById("signUp");
	
	function showLarge(e){
		large.src = e.target.src;
	}
	
	function signUpIn(){
		$("#signUpFont").css("font-weight","bold");
		$("#signUpFont").attr("color","orange");
	}
	
	function signUpOut(){
		$("#signUpFont").css("font-weight","normal");
		$("#signUpFont").attr("color","black");
	}
	
	function signUpGo(){
		
		swal({
			  title: "是否要報名課程?",
			  text: "Are you ready to sign up?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "不了!",
			  confirmButtonText: "我要報名!",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			
			function(isConfirm){
			  if (isConfirm) {
				 $.ajax({
					url : '<%= request.getContextPath() %>/SCM/StudentsCourseManager.do',
	 				data : {
	 					ct_no : '<%= ((Course_timeVO)pageContext.getAttribute("course_timeVO")).getCt_no() %>',	
	 					cl_date : '<%= ((Course_timeVO)pageContext.getAttribute("course_timeVO")).getCrs_date() %>',
	 					crs_time : <%= ((Course_timeVO)pageContext.getAttribute("course_timeVO")).getCrs_time() %>,
	 					action : 'signUp'
	 				},
	 				type : "POST",
	 				dataType : 'text',
	 				success : function(msg) {
	 					swal("報名成功!", "Your are already sign up the course.", "success");
	 					setTimeout(function(){ window.location.reload(); }, 1200);
	 				},

	 				error : function(xhr, ajaxOptions, thrownError) {
	 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
	 				}
	 			});
			  } else {
				swal("取消報名", "See you next time!", "error");
				setTimeout(function(){ swal.close(); }, 1200);
			  }
			});
	}
	
	function addCalendar(title,start,className,minTime) {
		var y = new Date(start);
		$('#calendar').fullCalendar('renderEvent',
			{
				title: title,
				start: new Date(y.getFullYear(),y.getMonth(),y.getDate(),minTime),
				className: className,
				defaultEventMinutes: 90,
				allDay: false
			},
			true // make the event "stick"
		);
		$(".fc-event-time").append("m  ");
	}

		


		

	

	window.onload = function(){
	  $("#shitMap").click(function(e){
		e.target.click();
		e.target.click();
		e.target.click();
	  });
	  
	  $(".calendarbtn").click();
	  var small = document.getElementsByClassName("small");
	  for(var i=0;i<small.length;i++){
	  	small[i].onmouseover = showLarge;
	  }
	  
	  signUp.onmouseover = signUpIn;
	  signUp.onmouseout = signUpOut;
	}
	
	jQuery(function($) {
		$('#external-events div.external-event').each(function() {

		// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
		// it doesn't need to have a start or end
		var eventObject = {
			title: $.trim($(this).text()) // use the element's text as the event title
		};

		// store the Event Object in the DOM element so we can get to it later
		$(this).data('eventObject', eventObject);

		// make the event draggable using jQuery UI
		$(this).draggable({
			zIndex: 999,
			revert: true,      // will cause the event to go back to its
			revertDuration: 0  //  original position after the drag
		});
		
	});




	/* initialize the calendar
	-----------------------------------------------------------------*/

	var date = new Date();
	var d = date.getDate();
	var m = date.getMonth();
	var y = date.getFullYear();

	
	var calendar = $('#calendar').fullCalendar({
		 buttonText: {
			prev: '<i class="icon-chevron-left"></i>',
			next: '<i class="icon-chevron-right"></i>'
		},
	
		header: {
			left: 'prev,next today',
			center: 'title',
			right: 'month,agendaWeek,agendaDay'
		},
		
		
	});


})

function initMap(lat) {
    	var latlng = lat;
    	// map
    	var mapOptions = {
            	center: new google.maps.LatLng(latlng.split(",")[0],latlng.split(",")[1]),
            	panControl: true, //要不要出現可以上下左右移動的面板
            	zoomControl: true, //要不要出現可以放大縮小的面板
            	mapTypeControl: true, //切換地圖檢視類型的面板
            	scaleControl: true, //比例尺資訊
            	streetViewControl: true,  //顯示街景服務的面板
            	overviewMapControl: true, //總覽圖的面板，在右下有個開合的圖示
            	zoom: 15 //縮放的大小，0-18，0最小，18最大
        };
        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
		
     	// marker
     	var image = '/BA102G4/style/images/mapicon.png';
     	var beachMarker = new google.maps.Marker({
            position: new google.maps.LatLng(latlng.split(",")[0],latlng.split(",")[1]),
            map: map,
            icon: image, //圖示
            title: '我在這裡!!!', //title
            animation: google.maps.Animation.BOUNCE //動畫
        });
     	//跳動動畫
        beachMarker.addListener('click', function() {
        	map.setZoom(18);
        	map.setCenter(beachMarker.getPosition());
        });
        
        map.addListener('center_changed', function() {
            // 3 seconds after the center of the map has changed, pan back to the
            // marker.
        	window.setTimeout(function() {
        		map.panTo(beachMarker.getPosition());
        	}, 5000);
        });
    }
</script>
<style>
.fc-header-title > h2{
	font-size:30px;
}

.fc-day-header{
	font-size:16px;
}

.fc-event-hori{
	border:2px solid;
	border-radius:8px;
}

.fc-event-title{
	font-size:16px;
}

.fc-event-time{
	font-size:16px;
}

.fc-today{
	background-color:#FFC559;
}

.fc-day-number{
	font-size:18px;
}
</style>
