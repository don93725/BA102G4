<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
	
	<head>
		<title>login</title>

		<!-- basic styles -->
		<link href="<%= request.getContextPath()%>/style/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%= request.getContextPath()%>/style/assets/css/font-awesome.min.css" />
  
		<!--[if IE 7]>
		  <link rel="stylesheet" href="assets/css/font-awesome-ie7.min.css" />
		<![endif]-->

		<!-- page specific plugin styles -->

		<!-- fonts -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400,300" />

		<!-- ace styles -->
		<link rel="stylesheet" href="<%= request.getContextPath()%>/style/assets/css/ace.min.css" />
		<link rel="stylesheet" href="<%= request.getContextPath()%>/style/assets/css/ace-rtl.min.css" />

		<!--[if lte IE 8]>
		  <link rel="stylesheet" href="<%= request.getContextPath()%>/style/assets/css/ace-ie.min.css" />
		<![endif]-->

		<!-- inline styles related to this page -->

		<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->

		<!--[if lt IE 9]>
		<script src="<%= request.getContextPath()%>/style/assets/js/html5shiv.js"></script>
		<script src="<%= request.getContextPath()%>/style/assets/js/respond.min.js"></script>
		<![endif]-->
	</head>
	<style>
		a.btn i{
			vertical-align : middle;
		}
	</style>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">���f</span>
									<span class="white">�|���n�J�t��</span>
								</h1>
								<h4 class="blue">&copy; Company Name</h4>
							</div>

							<div class="space-6"></div>

							<div class="position-relative">
								<!--�n�J�e��-->
								<div id="login-box" class="login-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header blue lighter bigger">
												<i class="icon-coffee green"></i>
												�п�J�z���b���B�K�X
											</h4>

											<div class="space-6"></div>

											<form method="post" action="<%= request.getContextPath()%>/MembersServlet">
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text" class="form-control" name="account" placeholder="�п�J�b��" />
															<i class="icon-user"></i>
														</span>
													</label>

													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" class="form-control" name="password" placeholder="�п�J�K�X" />
															<i class="icon-lock"></i>
														</span>
													</label>

													<div class="space"></div>

													<div class="clearfix">
														<label class="inline">
															<input type="checkbox" class="ace" />
															<span class="lbl"> �O���</span>
														</label>

														<input type="hidden" name="action" value="login">
														<button type="submit" class="width-35 pull-right btn btn-sm btn-primary">
															<i class="icon-key"></i>
															�n�J
														</button>
													</div>

													<div class="space-4"></div>
												</fieldset>
											</form>

											<div class="social-or-login center">
												<span class="bigger-110">�ΥΥH�U�b���n�J</span>
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
													�ѰO�K�X?
												</a>
											</div>

											<div>
												<a href="#" onclick="show_box('signup-box-select'); return false;" class="user-signup-link"">
													�ڷQ�����|��
													<i class="icon-arrow-right"></i>
												</a>
											</div>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /login-box -->
								
								<!--�ѰO�K�X-->
								<div id="forgot-box" class="forgot-box widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">
												<i class="icon-key"></i>
												��^�K�X
											</h4>

											<div class="space-6"></div>
											<p>
												��J�z���q�l�l����I���H���s��
											</p>

											<form>
												<fieldset>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email" class="form-control" placeholder="Email" />
															<i class="icon-envelope"></i>
														</span>
													</label>

													<div class="clearfix">
														<button type="button" class="width-35 pull-right btn btn-sm btn-danger">
															<i class="icon-lightbulb"></i>
															�e�X
														</button>
													</div>
												</fieldset>
											</form>
										</div><!-- /widget-main -->

										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												��^�n�J
												<i class="icon-arrow-right"></i>
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /forgot-box -->

								<!--�������-->
								<div id="signup-box-select" class="signup-box widget-box no-border">
								<center>
									<div class="widget-body">
										<div class="widget-main">
											<div>
											<a href="students_register.jsp">
												<button type="button" class="width-50 pull-center btn btn-md btn-success">
													�ڬO������
													<i class="icon-arrow-right"></i>
												
												</button>
											</a>
											</div>
											<br>
											<div>
											<a href="coaches_register.jsp">
												<button type="button" class="width-50 pull-middle btn btn-md btn-warning">
													�ڬO�нm
														<i class="icon-arrow-right"></i>
												</button>
											</a>
											</div>
											<br>
											<div>
											<a href="gyms_register.jsp">	
												<button type="button" class="width-50 pull-center btn btn-md btn-info">
													�ڬO�����з~��
													<i class="icon-arrow-right"></i>
												</button>
											</a>
											</div>
										</div>
										<div class="toolbar center">
											<a href="#" onclick="show_box('login-box'); return false;" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												��^�n�J
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

		<!-- basic scripts -->

		<!--[if !IE]> -->

		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.0.3/jquery.min.js"></script>

		<!-- <![endif]-->

		<!--[if IE]>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<![endif]-->

		<!--[if !IE]> -->

		<script type="text/javascript">
			window.jQuery || document.write("<script src='<%= request.getContextPath()%>/style/assets/js/jquery-2.0.3.min.js'>"+"<"+"/script>");
		</script>

		<!-- <![endif]-->

		<!--[if IE]>
<script type="text/javascript">
 window.jQuery || document.write("<script src='<%= request.getContextPath()%>/style/assets/js/jquery-1.10.2.min.js'>"+"<"+"/script>");
</script>
<![endif]-->
		
		<script src="<%= request.getContextPath()%>/style/assets/js/jquery.inputlimiter.1.3.1.min.js"></script>
		<script type="text/javascript">
			if("ontouchend" in document) document.write("<script src='<%= request.getContextPath()%>/style/assets/js/jquery.mobile.custom.min.js'>"+"<"+"/script>");
		</script>
		
		<!-- inline scripts related to this page -->

		<script type="text/javascript">
			//����r��
			$(document).ready(function() {
				$('textarea.limited').inputlimiter({
					remText: '�ثe�Ѿl %n �Ӧr...',
					limitText: '�̦h : %n �r'
				});
			});

			//������U���୶��
			function show_box(id) {
			 jQuery('.widget-box.visible').removeClass('visible');
			 jQuery('#'+id).addClass('visible');
			};

		</script>
	
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
