<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<jsp:useBean id="CountSvcIndex" scope="page" class="com.index.model.CountService"/> 	

<html lang="zh-cn-en">


<head>
<title>���f��x�޲z�t��</title>
<!-- fonts -->

<%@include file="/back_end/include/basic_ace_script.file" %>

 <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.dom.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.sound.js"></script>

  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/snake.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/sketch.js"></script>
</head>
<body>

	<%@include file="/back_end/include/navbar.file" %>
	<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content" style="background-color:transparent;">
		<div class="page-header">
						<div id = "bgCanvas" style="position:absolute;z-index:-1;"></div> 
			<h1>
				����
			</h1>
		</div>
		<!-- /.page-header -->
					<div class="alert alert-block alert-success" style="background-color:transparent;">
					<button type="button" class="close" data-dismiss="alert">
						<i class="icon-remove"></i>
					</button>

					<i class="icon-ok green"></i> �w��ϥ� <strong class="green">���f��x�޲z�t��</strong> 
				</div>

				<div class="row" style="background-color:transparent;">
					<div class="space-6"></div>

					<div class="col-sm-12 infobox-container" align="center">
					
					
						<div class="infobox infobox-green" style="background-color:transparent;">
							<div class="infobox-icon">
								<i class="icon-user"></i>
							</div>

							<div class="infobox-data" style="background-color:transparent;">
								<span class="infobox-data-number">${CountSvcIndex.getCount("students")}�H</span>
								<div class="infobox-content">������</div>
							</div>
						</div>


						<div class="infobox infobox-orange" style="background-color:transparent;">
							<div class="infobox-icon">
								<i class="icon-linux"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvcIndex.getCount("coaches")}�H</span>
								<div class="infobox-content">�нm</div>
							</div>
						</div>


						<div class="infobox infobox-blue" style="background-color:transparent;">
							<div class="infobox-icon">
								<i class="icon-beaker"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvcIndex.getCount("gyms")}�H</span>
								<div class="infobox-content">������</div>
							</div>
						</div>


						<div class="infobox infobox-pink" style="background-color:transparent;">
							<div class="infobox-icon">
								<i class="icon-github-alt"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvcIndex.getCount("manager")}�H</span>
								<div class="infobox-content">���u</div>
							</div>
						</div>
					</div>
					<div class="col-sm-12" align="center">
						<img src="<%= request.getContextPath()%>/style/images/logo_b.gif" style="width:300px;">
					</div>
				</div>		
			</div>
					
					
	
		
	<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>
<script>
window.onload = function(){
	
}
</script>