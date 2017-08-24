<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
	<head>
		<title>test</title>

		<!-- basic styles -->
		<link href="<%= request.getContextPath()%>/style/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="<%= request.getContextPath()%>/style/assets/css/font-awesome.min.css" />

		<!--[if IE 7]>
		  <link rel="stylesheet" href="<%= request.getContextPath()%>assets/css/font-awesome-ie7.min.css" />
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
								<h4 class="blue">&copy; Company Name</h4>
							</div>
							
							<div class="space-6"></div>

							<div class="position-relative">
							
							<!--身分選擇-->
								<div id="signup-box-select" class="signup-box visible widget-box no-border">
								<center>
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header red lighter bigger">	
												<b><p>帳號以停權，請聯絡管理員!</p></b>
											</h4>
											
											<div class="toolbar center">
											<a href="<%= request.getContextPath()%>/front_end/login/login.jsp" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												返回登入
											</a>
											</div>
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
	
	<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>
							