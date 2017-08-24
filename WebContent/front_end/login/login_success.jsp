<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
	<head>
		<meta http-equiv="refresh" content="3; <%= request.getContextPath()%>/front_end/index.jsp">
		<title>���f - GymHome</title>
		<%@include file="/front_end/include/registerStyle.file" %>
		
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
									<span class="red">���f</span>
									<span class="white">�|���n�J�t��</span>
								</h1>
								<h4 class="blue">&copy; Gym Home</h4>
							</div>
							
							<div class="space-6"></div>

							<div class="position-relative">
							
							<!--�������-->
								<div id="signup-box-select" class="signup-box visible widget-box no-border">
								<center>
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">	
												<b><p>${user.mem_nickname}&nbsp�w��^��</p></b>
												�����N�b&nbsp<span id="divNum">3</span>&nbsp������...
											</h4>
											
											<div>
											<a href="<%= request.getContextPath() %>/front_end/index.jsp">	
												<button type="button" class="width-50 pull-center btn btn-md btn-info">
													�ߧY��^����
													<i class="icon-arrow-right"></i>
												</button>
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
		
		<!-- �˼ƭp�� -->
		<script type="text/javascript">
			var num = 3;
			var timerId;
			function countDown(){
				num--;
			if(num == 0){
				clearInterval(timerId);
			}
				document.getElementById("divNum").innerHTML = num;
			}

			function init(){
				timerId = setInterval(countDown , 1000);
			}
			window.onload = init;
			</script>	
</body>
</html>
							