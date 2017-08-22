<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.members.model.*"%>

<%@ include file="default.file"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<!--     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content=""> -->


<script>
function leave(ct_no,stu_acc){
	swal({
		  title: "請假單!",
		  text: "請假原因:",
		  type: "input",
		  showCancelButton: true,

		  closeOnConfirm: false,
		  animation: "slide-from-top",
		  inputPlaceholder: "Write something",
		  confirmButtonColor: "#d9534f"
		},
		function(inputValue){
		  if (inputValue === false) return false;
		  
		  if (inputValue === "") {
		    swal.showInputError("請填寫請假原因,謝謝!");
		    return false
		  }
		  $.ajax({
				url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
 				data : {
 					ct_no : ct_no,	
 					stu_acc : stu_acc,
 					reason : inputValue,
 					action : 'leaveCourse'
 				},
 				type : "POST",
 				dataType : 'text',

 				success : function(msg) {
 					swal("請假成功!", "請假原因:" + inputValue , "success");
 					setTimeout(function(){ dropdown(4); }, 1500);
 				},

 				error : function(xhr, ajaxOptions, thrownError) {
 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
 				}
 			});
		});
}

function report(ct_no,stu_acc){
	swal({
		  title: "檢舉課程!",
		  text: "檢舉原因:",
		  type: "input",
		  showCancelButton: true,
		  closeOnConfirm: false,
		  animation: "slide-from-top",
		  inputPlaceholder: "Write something",
		  confirmButtonColor: "#d9534f"
		},
		function(inputValue){
		  if (inputValue === false) return false;
		  
		  if (inputValue === "") {
		    swal.showInputError("請填寫檢舉原因,謝謝!");
		    return false
		  }
		  $.ajax({
				url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
 				data : {
 					ct_no : ct_no,	
 					stu_acc : stu_acc,
 					report_ct : inputValue,
 					action : 'reportCourse'
 				},
 				type : "POST",
 				dataType : 'text',

 				success : function(msg) {
 					swal("檢舉成功!", "檢舉原因:" + inputValue , "success");
 					setTimeout(function(){ dropdown(5); }, 1500);
 				},

 				error : function(xhr, ajaxOptions, thrownError) {
 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
 				}
 			});
		});
}

</script>
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body onmousemove="mouseXY()">
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>



	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<div class="col-lg-12">
				<h1>
					課程管理 <small>CoursesManagement</small>
				</h1>
			</div>

			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首頁</a></li>
				<li class="active">課程專區</li>
				<li class="active">${which}</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		<br>

		<!-- Content Row -->
		<div class="row">
			<div class="col-lg-12">

				<div class="tabbable">
					<!-- 課程那一欄 -->
					<ul class="nav nav-tabs" id="myTab">

						<!-- 課程管理 -->
						<li  id="manager">
							<a href="<%=request.getContextPath()%>/SCM/StudentsCourseManager.do?action=courseList" id="dropdown1"><i
								class="green glyphicon glyphicon-pencil" style="font-size: 16px"></i>
								<font style='font-size: 16px; font-weight: bold;'> 選課列表 </font></a>
						</li>

						<!-- 課程紀錄 -->
						<li class="" id="record"><a href="<%=request.getContextPath()%>/SCM/StudentsCourseManager.do?action=courseOpen" id="dropdown2"> <i
								class="green  glyphicon glyphicon-home" style="font-size: 16px"></i>
								<font style='font-size: 16px; font-weight: bold;'>開課列表</font>
						</a>
						</li>

						<!-- 課程課程報表紀錄 -->
						<li id="report"><a href="<%=request.getContextPath()%>/SCM/StudentsCourseManager.do?action=courseRecord"><i
								class="green glyphicon glyphicon-book" style="font-size: 16px"></i>
								<font style='font-size: 16px; font-weight: bold;'> 課程紀錄</font> </a>
							</li>

						<li id="showCalendar"><a href="<%=request.getContextPath()%>/SCM/StudentsCourseManager.do?action=calendar"><i
								class="green glyphicon glyphicon-calendar"
								style="font-size: 16px"></i> <font
								style='font-size: 16px; font-weight: bold;'> 行事曆</font> </a>
						</li>
					</ul>


					<!-- 內容全在這裡 -->
					<div class="tab-content page">
						<jsp:include page="${page}" flush="true" />
					</div>
				</div>
				<!-- 內容全在這裡 結束 -->


			</div>
			<!-- col-lg-12 -->
			<div class="vspace-xs-6"></div>
		</div>
		<!-- /row -->




	</div>
	<!-- /.container -->
	<button id="changeActive" onclick="chageActive(${active})"
		style="display: none;"></button>
	<div id="showMouse"
		style="border:2px #ccc solid;border-radius:10px;display:none;position:absolute;background-color:gray;width:500px;height:300px; z-index:9999;background-image:url('<%=request.getContextPath()%>/front_end/SCM/images/blockbg.jpg');"></div>

	<div id="showMouseLeave"
		style="border:2px #ccc solid;border-radius:10px;display:none;position:absolute;background-color:gray;width:400px;height:200px; z-index:9999;background-image:url('<%=request.getContextPath()%>/front_end/SCM/images/blockbg.jpg');"></div>


  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>

</html>

<!-- page specific plugin scripts -->


<script type="text/javascript">
document.onkeydown = function(){
	        var keycode = event.which || event.keyCode;
	        if(keycode == 37){
	            $("#goleft").click();
	        }else if (keycode == 39){
				$("#goright").click();
			}
	    }
	
window.onload =  function () {
	$("#changeActive").trigger('click');
	$(".paybtn").click();	
	$(".calendarbtn").click();
}


function QueryString(name) {
	var AllVars = window.location.search.substring(1);
	var Vars = AllVars.split("&");
	for (i = 0; i < Vars.length; i++)
	{
		var Var = Vars[i].split("=");
		if (Var[0] == name) return Var[1];
	}
	return "";
}
function mouseXY(){
	tempX = event.clientX + document.body.scrollLeft;
	tempY = event.clientY + document.body.scrollTop;
	bodyY = document.body.scrollHeight;
	if(tempY<(bodyY/2)){
		$("#showMouse").css("left",tempX);
		$("#showMouse").css("top",tempY);	
		$("#showMouseLeave").css("left",tempX);
		$("#showMouseLeave").css("top",tempY);	
	}else{
		$("#showMouse").css("left",tempX);
		$("#showMouse").css("top",tempY-300);	
		$("#showMouseLeave").css("left",tempX);
		$("#showMouseLeave").css("top",tempY-300);
	}
}

function showBlock(ct_no){
	$.ajax({
			url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
			data : {
				ct_no : ct_no,	
				action : 'showBlock'
			},
			type : "POST",
			dataType : 'text',

			success : function(msg) {
				console.log(msg);
				$("#showMouse").html(msg);
			},

			error : function(xhr, ajaxOptions, thrownError) {
				sweetAlert("Oops...", "請檢查網路狀態!", "error");
			}
		});
	$("#showMouse").css("display","block");
}

function showLeaveBlock(ct_no,stu_acc){
	$.ajax({
			url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
			data : {
				ct_no : ct_no,	
				stu_acc : stu_acc,
				action : 'showLeaveBlock'
			},
			type : "POST",
			dataType : 'text',

			success : function(msg) {
				$("#showMouseLeave").html(msg);
			},

			error : function(xhr, ajaxOptions, thrownError) {
				sweetAlert("Oops...", "請檢查網路狀態!", "error");
			}
		});
	$("#showMouseLeave").css("display","block");
}

function showReportBlock(ct_no,stu_acc){
	$.ajax({
			url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
			data : {
				ct_no : ct_no,	
				stu_acc : stu_acc,
				action : 'showReportBlock'
			},
			type : "POST",
			dataType : 'text',

			success : function(msg) {
				$("#showMouseLeave").html(msg);
			},

			error : function(xhr, ajaxOptions, thrownError) {
				sweetAlert("Oops...", "請檢查網路狀態!", "error");
			}
		});
	$("#showMouseLeave").css("display","block");
}

function hideBlock(){
	$("#showMouse").css("display","none");
}

function hideLeaveBlock(){
	$("#showMouseLeave").css("display","none");
}

function payment(c){
	var pay = "#pay" + c;
	$(pay).click();
}

function payStatus(c){
	var payStatus = "#payStatus" + c;
	var pay = ".pay" + c;
	if($(payStatus).attr("color") == 'blue'){
		$(pay).attr("disabled",true);
	}else{
		$(pay).attr("disabled",false);
	}
}

function chageActive(num) {
		if (num == '2'){
			$("#record").addClass("active");
		}else if(num == '3'){
			$("#report").addClass("active");
	    }else if(num == '4'){
			$("#showCalendar").addClass("active");
	    }else{
			$("#manager").addClass("active");
		}
}

function dropdown(num) {
	var btn = "#dropdown" + num;
	$(btn).click();
}

function deleteCourse(ct_no,stu_acc){
	

	swal({
		  title: "是否要退選課程?",
		  text: "You will not be able to recover this!",
		  type: "warning",
		  showCancelButton: true,
		  confirmButtonColor: "#DD6B55",
		  cancelButtonText: "不了!",
		  confirmButtonText: "確認退選!",
		  closeOnConfirm: false,
		  closeOnCancel: false
		},
		
		function(isConfirm){
		  if (isConfirm) {
			 $.ajax({
　				url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
 				data : {
 					ct_no : ct_no,	
 					stu_acc : stu_acc,
 					action : 'deleteCourse'
 				},
 				type : "POST",
 				dataType : 'text',

 				success : function(msg) {
 					swal("退選成功!", "Your are already retire the course.", "success");
 					setTimeout(function(){ location.reload(); }, 1200);
 				},

 				error : function(xhr, ajaxOptions, thrownError) {
 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
 				}
 			});
		  } else {
				swal.close();
		  }
		});
	}
	
	
	function calendarChange(){
		$(".fc-event-time").append("m  ");
	}
	
function addCalendar(title, start, className, minTime) {
	var y = new Date(start);
	$('#calendar').fullCalendar(
			'renderEvent',
			{
				title : title,
				start : new Date(y.getFullYear(), y.getMonth(),
						y.getDate(), minTime),
				className : className,
				defaultEventMinutes : 90,
				allDay : false
			}, true // make the event "stick"
	);
	$(".fc-event-time").append("m  ");
}

	jQuery(function($) {

		$('#external-events div.external-event').each(function() {

			// create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
			// it doesn't need to have a start or end
			var eventObject = {
				title : $.trim($(this).text())
			// use the element's text as the event title
			};

			// store the Event Object in the DOM element so we can get to it later
			$(this).data('eventObject', eventObject);

			// make the event draggable using jQuery UI

		});

		/* initialize the calendar
		 -----------------------------------------------------------------*/

		var date = new Date();
		var d = date.getDate();
		var m = date.getMonth();
		var y = date.getFullYear();

		var calendar = $('#calendar')
				.fullCalendar(
						{
							buttonText : {
								prev : '<i class="icon-chevron-left"></i>',
								next : '<i class="icon-chevron-right"></i>'
							},

							header : {
								left : 'prev,next today',
								center : 'title',
								right : 'month,agendaWeek,agendaDay'
							},

							eventClick : function(calEvent, jsEvent, view) {
								if(calEvent.title.match('加退選') != null){
									
									if(('' + (calEvent.start.getMonth() + 1)).length == 1){
										var month = "0" + (calEvent.start.getMonth() + 1);
									}else{
										var month = (calEvent.start.getMonth() + 1);
									}
									
									var cl_date = calEvent.start.getFullYear() + "-" + month + "-" + calEvent.start.getDate();
									if(calEvent.className[0] == 'label-success'){
										var crs_time = 1;
									}else if(calEvent.className[0] == 'label-danger'){
										var crs_time = 2;
									}else if(calEvent.className[0] == 'label-purple'){
										var crs_time = 3;
									}else if(calEvent.className[0] == 'label-yellow'){
										var crs_time = 4;
									}else if(calEvent.className[0] == 'label-pink'){
										var crs_time = 5;
									}else{
										var crs_time = 6;
									}
									console.log(cl_date);
									console.log(crs_time);

									swal({
										  title: "是否要退選課程?",
										  text: "You will not be able to recover this!",
										  type: "warning",
										  showCancelButton: true,
										  confirmButtonColor: "#DD6B55",
										  cancelButtonText: "不了!",
										  confirmButtonText: "確認退選!",
										  closeOnConfirm: false,
										  closeOnCancel: false
										},
										
										function(isConfirm){
										  if (isConfirm) {
												$.ajax({
									　				url : '<%=request.getContextPath()%>/SCM/StudentsCourseManager.do',
									 				data : {
									 					cl_date : cl_date,	
									 					crs_time : crs_time,
									 					stu_acc : '<%= ((MembersVO) session.getAttribute("user")).getMem_acc() %>',
									 					action : 'deleteCalendarCourse'
									 				},
									 				type : "POST",
									 				dataType : 'text',

									 				success : function(msg) {
									 					swal("退選成功!", "Your are already retire the course.", "success");
									 					setTimeout(function(){ location.reload(); }, 1200);
									 				},

									 				error : function(xhr, ajaxOptions, thrownError) {
									 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
									 				}
									 			});
										  } else {
											swal.close();
										  }
										});
							
								}
							}

						});

	})
</script>
<style>
.modal-content{
 	position:relative;
 	right:62em;
}
</style>