<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>這是標題</title>
		<%@include file="include/basicScript.file" %>
	<style type="text/css">
			.item img{
				padding-top: 5%;
				width: 100%;
			}
	</style>
</head>

<body>
	
	<!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="include/front_navbar.file" %>
    </nav>
	
	<br>
	
    <!-- Page Content -->
    <div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-4" style="background-color:blue;">
				<div class="item">
					<img src="https://api.fnkr.net/testimg/350x200/00CED1/FFF/?text=img+placeholder">
					<h3>文字A</h3>
					<p>1Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quisquam, asperiores.</p>
				</div>
			</div>			
			<div class="col-xs-12 col-sm-8" style="background-color:red;">
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3>文字B</h3>
						<p>2Lorem ipsum dolor sit amet, consectetur adipisicing elit. Hic, modi?</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3>文字C</h3>
						<p>3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum, veritatis!</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3>文字C</h3>
						<p>3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum, veritatis!</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3>文字C</h3>
						<p>3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum, veritatis!</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3>文字C</h3>
						<p>3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum, veritatis!</p>
					</div>
				</div>
				<div class="col-xs-12 col-sm-12">
					<div class="item">
						<h3>文字C</h3>
						<p>3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum, veritatis!</p>
					</div>
				</div>	
			</div>
			
		</div><!-- row end -->
		<p></p>
		<div class="row">	
				
			<center>
			<div class="col-xs-12 col-sm-12" style="background-color: yellow;">
				<div class="item">
					<h3>文字C</h3>
					<p>3Lorem ipsum dolor sit amet, consectetur adipisicing elit. Earum, veritatis!</p>
				</div>
			</div>
			</center>	
					
		</div><!-- row end -->
		<p></p>
		<div class="row">
			<div class="col-sm-12" style="padding-left:0px;padding-right:0px;">
				
					<ul class="nav nav-tabs" id="myTab">
						<li class="active">
							<a data-toggle="tab" href="#home">
								<i class="green icon-home bigger-110"></i>
								Home
							</a>
						</li>

						<li>
							<a data-toggle="tab" href="#profile">
								Messages
									<span class="badge badge-danger">4</span>
							</a>
						</li>

						<li class="dropdown">
							<a data-toggle="dropdown" class="dropdown-toggle" href="#">
								Dropdown &nbsp;
								<i class="icon-caret-down bigger-110 width-auto"></i>
							</a>

						<ul class="dropdown-menu dropdown-info">
							<li>
								<a data-toggle="tab" href="#dropdown1">@fat</a>
							</li>

							<li>
								<a data-toggle="tab" href="#dropdown2">@mdo</a>
							</li>
						</ul>
						</li>
					</ul>

					<div class="tab-content">
						<div id="home" class="tab-pane in active">
							<div>
								1 Raw denim you probably haven't heard of them jean shorts Austin.</p>
							</div>
							<p></p>
							<div>
								2 Raw denim you probably haven't heard of them jean shorts Austin.</p>
							</div>
						</div>
						<p></p>
						<div id="profile" class="tab-pane">
							<p>Food truck fixie locavore, accusamus mcsweeney's marfa nulla single-origin coffee squid.</p>
						</div>
						<div id="dropdown1" class="tab-pane">
							<p>Etsy mixtape wayfarers, ethical wes anderson tofu before they sold out mcsweeney's organic lomo retro fanny pack lo-fi farm-to-table readymade.</p>
						</div>
						<div id="dropdown2" class="tab-pane">
							<p>Trust fund seitan letterpress, keytar raw denim keffiyeh etsy art party before they sold out master cleanse gluten-free squid scenester freegan cosby sweater. Fanny pack portland seitan DIY, art party locavore wolf cliche high life echo park Austin.</p>
						</div>
					</div>
				
			</div><!-- /span -->
			
		</div><!-- row end -->	
		
	</div>

	<!-- Footer -->
	<%@include file="include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="include/floor.file" %>
	
</body>
	<%@include file="include/basicScript2.file" %>
	
</script>
</html>