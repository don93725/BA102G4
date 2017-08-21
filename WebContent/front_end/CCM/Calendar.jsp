<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
							<h4>Draggable events</h4>
						</div>

						<div class="widget-body">
							<div class="widget-main no-padding">
								<div id="external-events">
									<div class="external-event label-grey" data-class="label-grey">
										<i class="icon-move"></i> My Event 1
									</div>

									<div class="external-event label-success"
										data-class="label-success">
										<i class="icon-move"></i> My Event 2
									</div>

									<div class="external-event label-danger"
										data-class="label-danger">
										<i class="icon-move"></i> My Event 3
									</div>

									<div class="external-event label-purple"
										data-class="label-purple">
										<i class="icon-move"></i> My Event 4
									</div>

									<div class="external-event label-yellow"
										data-class="label-yellow">
										<i class="icon-move"></i> My Event 5
									</div>

									<div class="external-event label-pink" data-class="label-pink">
										<i class="icon-move"></i> My Event 6
									</div>

									<div class="external-event label-info" data-class="label-info">
										<i class="icon-move"></i> My Event 7
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

<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%= request.getContextPath() %>
	/style/assets/js/jquery.mobile.custom.min.js'>"
						+ "<"+"/script>");
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
							events : [ {
								title : 'All Day Event',
								start : new Date(y, m, 1),
								className : 'label-important'
							}, {
								title : 'Long Event',
								start : new Date(y, m, d - 5),
								end : new Date(y, m, d - 2),
								className : 'label-success'
							}, {
								title : 'Some Event',
								start : new Date(y, m, d - 3, 16, 0),
								allDay : false
							} ],
							editable : true,
							droppable : true, // this allows things to be dropped onto the calendar !!!
							drop : function(date, allDay) { // this function is called when something is dropped

								// retrieve the dropped element's stored Event Object
								var originalEventObject = $(this).data(
										'eventObject');
								var $extraEventClass = $(this).attr(
										'data-class');

								// we need to copy it, so that multiple events don't have a reference to the same object
								var copiedEventObject = $.extend({},
										originalEventObject);

								// assign it the date that was reported
								copiedEventObject.start = date;
								copiedEventObject.allDay = allDay;
								if ($extraEventClass)
									copiedEventObject['className'] = [ $extraEventClass ];

								// render the event on the calendar
								// the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
								$('#calendar').fullCalendar('renderEvent',
										copiedEventObject, true);

								// is the "remove after drop" checkbox checked?
								if ($('#drop-remove').is(':checked')) {
									// if so, remove the element from the "Draggable Events" list
									$(this).remove();
								}

							},
							selectable : true,
							selectHelper : true,
							select : function(start, end, allDay) {

								bootbox.prompt("New Event Title:", function(
										title) {
									if (title !== null) {
										calendar.fullCalendar('renderEvent', {
											title : title,
											start : start,
											end : end,
											allDay : allDay
										}, true // make the event "stick"
										);
									}
								});

								calendar.fullCalendar('unselect');

							},
							eventClick : function(calEvent, jsEvent, view) {

								var form = $("<form class='form-inline'><label>Change event name &nbsp;</label></form>");
								form
										.append("<input class='middle' autocomplete=off type=text value='" + calEvent.title + "' /> ");
								form
										.append("<button type='submit' class='btn btn-sm btn-success'><i class='icon-ok'></i> Save</button>");

								var div = bootbox
										.dialog({
											message : form,

											buttons : {
												"delete" : {
													"label" : "<i class='icon-trash'></i> Delete Event",
													"className" : "btn-sm btn-danger",
													"callback" : function() {
														calendar
																.fullCalendar(
																		'removeEvents',
																		function(
																				ev) {
																			return (ev._id == calEvent._id);
																		})
													}
												},
												"close" : {
													"label" : "<i class='icon-remove'></i> Close",
													"className" : "btn-sm"
												}
											}

										});

								form.on('submit', function() {
									calEvent.title = form.find(
											"input[type=text]").val();
									calendar.fullCalendar('updateEvent',
											calEvent);
									div.modal("hide");
									return false;
								});

								//console.log(calEvent.id);
								//console.log(jsEvent);
								//console.log(view);

								// change the border color just for fun
								//$(this).css('border-color', 'red');

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