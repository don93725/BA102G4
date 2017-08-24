<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>	
<jsp:useBean id="CountSvc" scope="page" class="com.index.model.CountService"/> 	

<html lang="zh-cn-en">


<head>
<title>���f��x�޲z�t��</title>
<!-- fonts -->

<%@include file="/back_end/include/basic_ace_script.file" %>
	

</head>
<body>
	<%@include file="/back_end/include/navbar.file" %>
	<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				����x <small> <i class="icon-double-angle-right"></i>
					
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
					<div class="alert alert-block alert-success">
					<button type="button" class="close" data-dismiss="alert">
						<i class="icon-remove"></i>
					</button>

					<i class="icon-ok green"></i> �w��ϥ� <strong class="green">
						���f��x�޲z�t�� <small>(v1.2)</small>
					</strong> 
				</div>

				<div class="row">
					<div class="space-6"></div>

					<div class="col-sm-12 infobox-container">
						<div class="infobox infobox-green ">
							<div class="infobox-icon">
								<i class="icon-user"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("students")}�H</span>
								<div class="infobox-content">������</div>
							</div>
						</div>

						<div class="infobox infobox-orange2  ">
							<div class="infobox-icon">
								<i class="icon-linux"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("coaches")}�H</span>
								<div class="infobox-content">�нm</div>
							</div>

							
						</div>

						<div class="infobox infobox-blue  ">
							<div class="infobox-icon">
								<i class="icon-beaker"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("gyms")}�H</span>
								<div class="infobox-content">������</div>
							</div>
						</div>

						<div class="infobox infobox-orange  ">
							<div class="infobox-icon">
								<i class="icon-github-alt"></i>
							</div>

							<div class="infobox-data">
								<span class="infobox-data-number">${CountSvc.getCount("manager")}�H</span>
								<div class="infobox-content">���u</div>
							</div>

							
						</div>

						<div class="space-6"></div>


					</div>

					<div class="vspace-sm"></div>

					<div class="col-sm-5">
						
						
					</div>
					<!-- /span -->
				</div>	
		
	<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>