<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<jsp:useBean id="CountSvc" scope="page" class="com.index.model.CountService"/> 	
		
		
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
<title>健貨後台管理系統</title>
<!-- fonts -->

<%@include file="/back_end/include/basic_ace_script.file"%>
</head>
<body>
	<%@include file="/back_end/include/navbar.file"%>
	<%@include file="/back_end/include/sliderBar_breadCrumb.file"%>
	<div class="page-content">

		<div class="page-header">
			<h1>首頁</h1>
		</div>
		<!-- /.page-header -->

		<div class="row">
			<div class="col-xs-12">
				<!-- PAGE CONTENT BEGINS -->

				<div class="alert alert-block alert-success">
					<button type="button" class="close" data-dismiss="alert">
						<i class="icon-remove"></i>
					</button>

					<i class="icon-ok green"></i> 歡迎使用 <strong class="green">
						健貨後台管理系統 <small>(v1.2)</small>
					</strong> ,爆肝吧!少年少女們 QAQ~
				</div>

				<div class="row">
					<div class="space-6"></div>

					<div class="col-sm-12 infobox-container">
						<div class="infobox infobox-green ">
							<div class="infobox-icon">
								<i class="icon-user"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("students")}</span>
								<div class="infobox-content">健身者</div>
							</div>
						</div>

						<div class="infobox infobox-orange2  ">
							<div class="infobox-icon">
								<i class="icon-linux"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("coaches")}</span>
								<div class="infobox-content">教練</div>
							</div>

							
						</div>

						<div class="infobox infobox-blue  ">
							<div class="infobox-icon">
								<i class="icon-beaker"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("gyms")}</span>
								<div class="infobox-content">健身房</div>
							</div>
						</div>

						<div class="infobox infobox-orange  ">
							<div class="infobox-icon">
								<i class="icon-github-alt"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("manager")}</span>
								<div class="infobox-content">員工</div>
							</div>

							
						</div>

						<div class="space-6"></div>


					</div>

					<div class="vspace-sm"></div>

					<div class="col-sm-5">
						
						
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->

			

				<div class="hr hr32 hr-dotted"></div>

				<div class="row">
					<div class="col-sm-6">
						<div class="widget-box transparent" id="recent-box">
							<div class="widget-header">
								<h4 class="lighter smaller">
									<i class="icon-rss orange"></i> 最近
								</h4>

								<div class="widget-toolbar no-border">
									<ul class="nav nav-tabs" id="recent-tab">
										<li><a data-toggle="tab" href="#member-tab">會員</a></li>

										<li><a data-toggle="tab" href="#comment-tab">評論</a></li>
									</ul>
								</div>
							</div>

							<div class="widget-body">
								<div class="widget-main padding-4">
									<div class="tab-content padding-8 overflow-visible">

										<div id="member-tab" class="tab-pane">
											<div class="clearfix">
												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Bob Doe's avatar" src="assets/avatars/user.jpg" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Bob Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">20
																min</span>
														</div>

														<div>
															<span class="label label-warning label-sm">pending</span>

															<div class="inline position-relative">
																<button
																	class="btn btn-minier bigger btn-yellow btn-no-border dropdown-toggle"
																	data-toggle="dropdown">
																	<i class="icon-angle-down icon-only bigger-120"></i>
																</button>

																<ul
																	class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
																	<li><a href="#" class="tooltip-success"
																		data-rel="tooltip" title="Approve"> <span
																			class="green"> <i class="icon-ok bigger-110"></i>
																		</span>
																	</a></li>

																	<li><a href="#" class="tooltip-warning"
																		data-rel="tooltip" title="Reject"> <span
																			class="orange"> <i
																				class="icon-remove bigger-110"></i>
																		</span>
																	</a></li>

																	<li><a href="#" class="tooltip-error"
																		data-rel="tooltip" title="Delete"> <span
																			class="red"> <i class="icon-trash bigger-110"></i>
																		</span>
																	</a></li>
																</ul>
															</div>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Joe Doe's avatar"
															src="assets/avatars/avatar2.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Joe Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">1
																hour</span>
														</div>

														<div>
															<span class="label label-warning label-sm">pending</span>

															<div class="inline position-relative">
																<button
																	class="btn btn-minier bigger btn-yellow btn-no-border dropdown-toggle"
																	data-toggle="dropdown">
																	<i class="icon-angle-down icon-only bigger-120"></i>
																</button>

																<ul
																	class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
																	<li><a href="#" class="tooltip-success"
																		data-rel="tooltip" title="Approve"> <span
																			class="green"> <i class="icon-ok bigger-110"></i>
																		</span>
																	</a></li>

																	<li><a href="#" class="tooltip-warning"
																		data-rel="tooltip" title="Reject"> <span
																			class="orange"> <i
																				class="icon-remove bigger-110"></i>
																		</span>
																	</a></li>

																	<li><a href="#" class="tooltip-error"
																		data-rel="tooltip" title="Delete"> <span
																			class="red"> <i class="icon-trash bigger-110"></i>
																		</span>
																	</a></li>
																</ul>
															</div>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Jim Doe's avatar"
															src="assets/avatars/avatar.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Jim Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">2
																hour</span>
														</div>

														<div>
															<span class="label label-warning label-sm">pending</span>

															<div class="inline position-relative">
																<button
																	class="btn btn-minier bigger btn-yellow btn-no-border dropdown-toggle"
																	data-toggle="dropdown">
																	<i class="icon-angle-down icon-only bigger-120"></i>
																</button>

																<ul
																	class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
																	<li><a href="#" class="tooltip-success"
																		data-rel="tooltip" title="Approve"> <span
																			class="green"> <i class="icon-ok bigger-110"></i>
																		</span>
																	</a></li>

																	<li><a href="#" class="tooltip-warning"
																		data-rel="tooltip" title="Reject"> <span
																			class="orange"> <i
																				class="icon-remove bigger-110"></i>
																		</span>
																	</a></li>

																	<li><a href="#" class="tooltip-error"
																		data-rel="tooltip" title="Delete"> <span
																			class="red"> <i class="icon-trash bigger-110"></i>
																		</span>
																	</a></li>
																</ul>
															</div>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Alex Doe's avatar"
															src="assets/avatars/avatar5.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Alex Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">3
																hour</span>
														</div>

														<div>
															<span class="label label-danger label-sm">blocked</span>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Bob Doe's avatar"
															src="assets/avatars/avatar2.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Bob Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">6
																hour</span>
														</div>

														<div>
															<span class="label label-success label-sm arrowed-in">approved</span>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Susan's avatar" src="assets/avatars/avatar3.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Susan</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">yesterday</span>
														</div>

														<div>
															<span class="label label-success label-sm arrowed-in">approved</span>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Phil Doe's avatar"
															src="assets/avatars/avatar4.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Phil Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">2
																days ago</span>
														</div>

														<div>
															<span
																class="label label-info label-sm arrowed-in arrowed-in-right">online</span>
														</div>
													</div>
												</div>

												<div class="itemdiv memberdiv">
													<div class="user">
														<img alt="Alexa Doe's avatar"
															src="assets/avatars/avatar1.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Alexa Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">3天以前</span>
														</div>

														<div>
															<span class="label label-success label-sm arrowed-in">approved</span>
														</div>
													</div>
												</div>
											</div>

											<div class="center">
												<i class="icon-group icon-2x green"></i> &nbsp; <a href="#">
													查看所有會員 &nbsp; <i class="icon-arrow-right"></i>
												</a>
											</div>

											<div class="hr hr-double hr8"></div>
										</div>
										<!-- member-tab -->

										<div id="comment-tab" class="tab-pane">
											<div class="comments">
												<div class="itemdiv commentdiv">
													<div class="user">
														<img alt="Bob Doe's Avatar"
															src="assets/avatars/avatar.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Bob Doe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="green">6
																min</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i> Lorem ipsum dolor sit
															amet, consectetur adipiscing elit. Quisque commodo massa
															sed ipsum porttitor facilisis &hellip;
														</div>
													</div>

													<div class="tools">
														<div class="inline position-relative">
															<button
																class="btn btn-minier bigger btn-yellow dropdown-toggle"
																data-toggle="dropdown">
																<i class="icon-angle-down icon-only bigger-120"></i>
															</button>

															<ul
																class="dropdown-menu dropdown-only-icon dropdown-yellow pull-right dropdown-caret dropdown-close">
																<li><a href="#" class="tooltip-success"
																	data-rel="tooltip" title="Approve"> <span
																		class="green"> <i class="icon-ok bigger-110"></i>
																	</span>
																</a></li>

																<li><a href="#" class="tooltip-warning"
																	data-rel="tooltip" title="Reject"> <span
																		class="orange"> <i
																			class="icon-remove bigger-110"></i>
																	</span>
																</a></li>

																<li><a href="#" class="tooltip-error"
																	data-rel="tooltip" title="Delete"> <span
																		class="red"> <i class="icon-trash bigger-110"></i>
																	</span>
																</a></li>
															</ul>
														</div>
													</div>
												</div>

												<div class="itemdiv commentdiv">
													<div class="user">
														<img alt="Jennifer's Avatar"
															src="assets/avatars/avatar1.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Jennifer</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="blue">15
																min</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i> Lorem ipsum dolor sit
															amet, consectetur adipiscing elit. Quisque commodo massa
															sed ipsum porttitor facilisis &hellip;
														</div>
													</div>

													<div class="tools">
														<div class="action-buttons bigger-125">
															<a href="#"> <i class="icon-pencil blue"></i>
															</a> <a href="#"> <i class="icon-trash red"></i>
															</a>
														</div>
													</div>
												</div>

												<div class="itemdiv commentdiv">
													<div class="user">
														<img alt="Joe's Avatar" src="assets/avatars/avatar2.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Joe</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="orange">22
																min</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i> Lorem ipsum dolor sit
															amet, consectetur adipiscing elit. Quisque commodo massa
															sed ipsum porttitor facilisis &hellip;
														</div>
													</div>

													<div class="tools">
														<div class="action-buttons bigger-125">
															<a href="#"> <i class="icon-pencil blue"></i>
															</a> <a href="#"> <i class="icon-trash red"></i>
															</a>
														</div>
													</div>
												</div>

												<div class="itemdiv commentdiv">
													<div class="user">
														<img alt="Rita's Avatar" src="assets/avatars/avatar3.png" />
													</div>

													<div class="body">
														<div class="name">
															<a href="#">Rita</a>
														</div>

														<div class="time">
															<i class="icon-time"></i> <span class="red">50 min</span>
														</div>

														<div class="text">
															<i class="icon-quote-left"></i> Lorem ipsum dolor sit
															amet, consectetur adipiscing elit. Quisque commodo massa
															sed ipsum porttitor facilisis &hellip;
														</div>
													</div>

													<div class="tools">
														<div class="action-buttons bigger-125">
															<a href="#"> <i class="icon-pencil blue"></i>
															</a> <a href="#"> <i class="icon-trash red"></i>
															</a>
														</div>
													</div>
												</div>
											</div>

											<div class="hr hr8"></div>

											<div class="center">
												<i class="icon-comments-alt icon-2x green"></i> &nbsp; <a
													href="#"> See all comments &nbsp; <i
													class="icon-arrow-right"></i>
												</a>
											</div>

											<div class="hr hr-double hr8"></div>
										</div>
									</div>
								</div>
								<!-- /widget-main -->
							</div>
							<!-- /widget-body -->
						</div>
						<!-- /widget-box -->
					</div>
					<!-- /span -->

					<div class="col-sm-6">
						<div class="widget-box ">
							<div class="widget-header">
								<h4 class="lighter smaller">
									<i class="icon-comment blue"></i> 訊息
								</h4>
							</div>

							<div class="widget-body">
								<div class="widget-main no-padding">
									<div class="dialogs">
										<div class="itemdiv dialogdiv">
											<div class="user">
												<img alt="Alexa's Avatar" src="assets/avatars/avatar1.png" />
											</div>

											<div class="body">
												<div class="time">
													<i class="icon-time"></i> <span class="green">4秒前</span>
												</div>

												<div class="name">
													<a href="#">Alexa</a>
												</div>
												<div class="text">大家好啊</div>

												<div class="tools">
													<a href="#" class="btn btn-minier btn-info"> <i
														class="icon-only icon-share-alt"></i>
													</a>
												</div>
											</div>
										</div>

										<div class="itemdiv dialogdiv">
											<div class="user">
												<img alt="John's Avatar" src="assets/avatars/avatar.png" />
											</div>

											<div class="body">
												<div class="time">
													<i class="icon-time"></i> <span class="blue">38秒以前</span>
												</div>

												<div class="name">
													<a href="#">John</a>
												</div>
												<div class="text">框架很好用嘛</div>

												<div class="tools">
													<a href="#" class="btn btn-minier btn-info"> <i
														class="icon-only icon-share-alt"></i>
													</a>
												</div>
											</div>
										</div>

										<div class="itemdiv dialogdiv">
											<div class="user">
												<img alt="Bob's Avatar" src="assets/avatars/user.jpg" />
											</div>

											<div class="body">
												<div class="time">
													<i class="icon-time"></i> <span class="orange">2分鐘以前</span>
												</div>

												<div class="name">
													<a href="#">Bob</a> <span
														class="label label-info arrowed arrowed-in-right">admin</span>
												</div>
												<div class="text">大家好</div>

												<div class="tools">
													<a href="#" class="btn btn-minier btn-info"> <i
														class="icon-only icon-share-alt"></i>
													</a>
												</div>
											</div>
										</div>

										<div class="itemdiv dialogdiv">
											<div class="user">
												<img alt="Jim's Avatar" src="assets/avatars/avatar4.png" />
											</div>

											<div class="body">
												<div class="time">
													<i class="icon-time"></i> <span class="grey">3分鐘以前</span>
												</div>

												<div class="name">
													<a href="#">Jim</a>
												</div>
												<div class="text">大家多注意BUG</div>

												<div class="tools">
													<a href="#" class="btn btn-minier btn-info"> <i
														class="icon-only icon-share-alt"></i>
													</a>
												</div>
											</div>
										</div>

										<div class="itemdiv dialogdiv">
											<div class="user">
												<img alt="Alexa's Avatar" src="assets/avatars/avatar1.png" />
											</div>

											<div class="body">
												<div class="time">
													<i class="icon-time"></i> <span class="green">4分鐘以前</span>
												</div>

												<div class="name">
													<a href="#">Alexa</a>
												</div>
												<div class="text">繼續支持健貨後台系統</div>

												<div class="tools">
													<a href="#" class="btn btn-minier btn-info"> <i
														class="icon-only icon-share-alt"></i>
													</a>
												</div>
											</div>
										</div>
									</div>

									<form>
										<div class="form-actions">
											<div class="input-group">
												<input placeholder="Type your message here ..." type="text"
													class="form-control" name="message" /> <span
													class="input-group-btn">
													<button class="btn btn-sm btn-info no-radius" type="button">
														<i class="icon-share-alt"></i> 發送
													</button>
												</span>
											</div>
										</div>
									</form>
								</div>
								<!-- /widget-main -->
							</div>
							<!-- /widget-body -->
						</div>
						<!-- /widget-box -->
					</div>
					<!-- /span -->
				</div>
				<!-- /row -->

				<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>