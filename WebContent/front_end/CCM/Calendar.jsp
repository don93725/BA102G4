<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.members.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/fullcalendar.css" />
<link href="<%= request.getContextPath() %>/style/css/bootstrap.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/font-awesome.min.css" />
<!-- page specific plugin styles -->
<!-- Custom Fonts -->
<link
	href="<%= request.getContextPath() %>/style/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<!-- jQuery -->
<!--訪客navbar動畫-1-->
<script src="<%= request.getContextPath() %>/style/js/jquery.js"></script>

<!-- Script to Activate the Carousel -->
<!-- Bootstrap Core JavaScript -->
<!--訪客navbar動畫-2-->
<script src="<%= request.getContextPath() %>/style/js/bootstrap.min.js"></script>
<!-- ace settings handler -->
<!--button樣式-->
<link rel="stylesheet"
	href="<%= request.getContextPath() %>/style/assets/css/ace.min.css" />
</head>
<body>
	<div class="row">
		<div class="col-xs-12">
			<!-- PAGE CONTENT BEGINS -->

			<div class="row">
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
										<i class="icon-move"></i> 08:00-09:30
									</div>

									<div class="external-event label-danger"
										data-class="label-danger">
										<i class="icon-move"></i> 10:00-11:30
									</div>

									<div class="external-event label-purple"
										data-class="label-purple">
										<i class="icon-move"></i> 13:00-14:30
									</div>

									<div class="external-event label-yellow"
										data-class="label-yellow">
										<i class="icon-move"></i> 15:00-16:30
									</div>

									<div class="external-event label-pink" data-class="label-pink">
										<i class="icon-move"></i> 18:00-19:30
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> 20:00-21:30
									</div>

								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>
<script type="text/javascript">
			window.jQuery || document.write("<script src='<%= request.getContextPath() %>/style/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>


<script
	src="<%=request.getContextPath()%>/style/assets/js/typeahead-bs2.min.js"></script>

<!-- page specific plugin scripts -->

<script
	src="<%=request.getContextPath()%>/style/assets/js/jquery-ui-1.10.3.custom.min.js"></script>
<script
	src="<%=request.getContextPath()%>/style/assets/js/jquery.ui.touch-punch.min.js"></script>
<script
	src="<%=request.getContextPath()%>/style/assets/js/fullcalendar.min.js"></script>
<script
	src="<%=request.getContextPath()%>/style/assets/js/bootbox.min.js"></script>

<!-- ace scripts -->

<script
	src="<%=request.getContextPath()%>/style/assets/js/ace-elements.min.js"></script>
<script>
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
			$(this).draggable({
				zIndex : 999,
				revert : true, // will cause the event to go back to its
				revertDuration : 0
			//  original position after the drag
			});

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
								if(('' + (calEvent.start.getMonth() + 1)).length == 1){
									var month = "0" + (calEvent.start.getMonth() + 1);
								}else{
									var month = (calEvent.start.getMonth() + 1);
								}
								
								var crs_date = calEvent.start.getFullYear() + "-" + month + "-" + calEvent.start.getDate();
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
								　				url : '<%=request.getContextPath()%>/CCM/CourseManager.do',
								 				data : {
								 					crs_date : crs_date,	
								 					crs_time : crs_time,
								 					c_acc : '<%= ((MembersVO) session.getAttribute("user")).getMem_acc() %>',
								 					action : 'deleteCalendarCourse'
								 				},
								 				type : "POST",
								 				dataType : 'text',

								 				success : function(msg) {
								 					swal("退選成功!", "Your are already retire the course.", "success");
								 					setTimeout(function(){ dropdown(6); }, 1200);
								 				},

								 				error : function(xhr, ajaxOptions, thrownError) {
								 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
								 				}
								 			});
									  } else {
										swal("取消退選", "Your content is safe :)", "error");
										setTimeout(function(){ swal.close(); }, 1200);
									  }
									});
							}

						});

	})
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