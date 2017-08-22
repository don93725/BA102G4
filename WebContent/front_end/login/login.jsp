<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
	
	<head>
		<title>健貨 - GymHome</title>
		<%@include file="/front_end/include/basicScript.file" %>	
		<%@include file="/front_end/include/loginStyle.file" %>
	</head>


	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">健貨</span>
									<span class="white">會員登入系統</span>
								</h1>
								<h4 class="blue">&copy; Gym Home</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<!--登入畫面-->
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												請輸入您的帳號、密碼
											</h4>

											<div class="space-6"></div>

											<form method="post" action="<%= request.getContextPath()%>/MembersServlet">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="account" placeholder="請輸入帳號" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="password" placeholder="請輸入密碼" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> 記住我</span>
														</label>

														<input type="hidden" name="action" value="login">
														<button type="submit" class="width-35 pull-right btn btn-sm btn-inverse">
															<i class="icon-key"></i>
															登入
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">或用以下帳號登入</span>
											</div>

											<div class="social-login center">
												<a class="btn btn-primary">
													<i class="icon-facebook"></i>
												</a>

												<a class="btn btn-info">
													<i class="icon-twitter"></i>
												</a>

												<a class="btn btn-danger">
													<i class="icon-google-plus"></i>
												</a>
											</div>
										</div><!-- /widget-main -->

										<div class="toolbar clearfix">
											<div>
												<a href="#" onclick="show_box('forgot-box'); return false;" class="forgot-password-link">
													<i class="icon-arrow-left"></i>
													忘記密碼?
												</a>
											</div>

											<div>
												<a href="#" onclick="show_box('signup-box-select'); return false;" class="user-signup-link"">
													我想成為會員
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
								
								<!--忘記密碼-->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="icon-key"></i>
												找回密碼
											</h4>

											<div class="space-6"></div>
											<p>
												輸入您的電子郵件並點擊信內連結
											</p>

												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" id="mail" name="mail" class="form-control" placeholder="Email" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<input type="button" class="width-35 pull-right btn btn-sm btn-danger" value="送出" onclick="forgetPSW()">
													</div>
												</fieldset>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												返回登入
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<!--身分選擇-->
								<div id="signup-box-select" class="signup-box widget-box no-border">
								<center>
									<div class="widget-body">
										<div class="widget-main">
											<div>
											<a href="<%=request.getContextPath() %>/front_end/register/students_register.jsp">
												<button type="button" class="width-50 pull-center btn btn-md btn-success">
													我是健身者
													<i class="icon-arrow-right"></i>
												
												</button>
											</a>
											</div>
											<br>
											<div>
											<a href="<%=request.getContextPath() %>/front_end/register/coaches_register.jsp">
												<button type="button" class="width-50 pull-middle btn btn-md btn-warning">
													我是教練
														<i class="icon-arrow-right"></i>
												</button>
											</a>
											</div>
											<br>
											<div>
											<a href="<%=request.getContextPath() %>/front_end/register/gyms_register.jsp">	
												<button type="button" class="width-50 pull-center btn btn-md btn-info">
													我是健身房業者
													<i class="icon-arrow-right"></i>
												</button>
											</a>
											</div>
										</div>
										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												返回登入
											</a>
										</div>
									</div><!-- /widget-body -->
								<center>
								</div><!-- /signup-box -->

							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->

		<%@include file="/front_end/include/registerJS.file" %>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>
		<!-- 控制註冊選項 -->
		<script type="text/javascript">
			function show_box(id) {
			 $(".widget-box.visible").removeClass('visible');
			 $('#'+id).addClass('visible');
			}
		</script>
		<!-- 忘記密碼 -->
		<script>
		function forgetPSW(){
			swal({
				title : "寄信中",
				imageUrl: "/BA102G4/style/images/p2.gif",
				timer: 1500,
	  			showConfirmButton: false
				},function(){
					$.ajax({
						url : '/BA102G4/MembersServlet',
						data : {
							mail : $('#mail').val(),
							action : 'forgetPSW'
						},
						type : "POST",
						dataType : 'text',

						success : function(msg) {
						console.log(msg)
							if(msg.length == 0) {
								swal({
									title : "寄信成功",
									text : "請去收信",
									type : "success",
									showConfirmButton : true
								});
							}else {
								swal({
									title : "寄信失敗",
									text : "",
									type : "error"
									showConfirmButton : true
								});
							}						
						},
						error : function(xhr, ajaxOptions, thrownError) {
						}
					});
			});
		}
		</script>
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
