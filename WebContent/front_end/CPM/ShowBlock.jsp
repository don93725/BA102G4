<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	
	<div class="row">
		<div class="col-sm-6">		
		<div id="carousel-id" class="carousel slide" data-ride="carousel" style="border:2px #ccc solid;border-radius:10px;margin:1em;">
		<font style="font-size:14px;font-weight:bold;margin-left:3em;"><i class="glyphicon glyphicon-chevron-left"></i>鍵盤左右觀看<i class="glyphicon glyphicon-chevron-right"></i></font>
        <div class="carousel-inner">

	        	<% int c=0; %>
				<c:forEach var="place_PicVO" items="${place_PicList}">
				<c:if test="<%= c==0 %>">
		        	<div class="item active" >
			            <img src="${place_PicVO.p_base}" style="border-radius:10px;" width="200" height="200">
	
			        </div>
				</c:if>
				<c:if test="<%= c!=0 %>">
		        	<div class="item"  style="border:2px #ccc solid;border-radius:10px;margin:1em;">
			            <img src="${place_PicVO.p_base}" style="border-radius:10px;" width="200" height="200">
	
			        </div>
				</c:if>
				<%c++; %>
				</c:forEach>

        </div >

        <a class="left carousel-control" href="#carousel-id" data-slide="prev" ><span class="" id="goleft"></span></a>
		<a class="right carousel-control" href="#carousel-id" data-slide="next" ><span class="" id="goright"></span></a>

		</div>
		</div>
		<div class="col-sm-6" style="position:reletive;right:2em;">
		<h3 style="font-weight:bold;">場地詳情:</h3>
		&nbsp;&nbsp;&nbsp;&nbsp;<font style="font-size:16px;font-weight:bold;">${place_timeVO.placeVO.p_into}</font>
		</div>
		<div style=" position:absolute;left:1em;bottom:1em;" ><font style="font-size:20px;font-weight:bold;">聯絡E-mail:</font> <font style="font-size:18px;font-weight:bold;">${place_timeVO.gymsVO.gym_mail}</font></div>
		<img alt="" src="<%= request.getContextPath()%>/front_end/CourseDetails/images/label.png" style=" position:absolute;right:0;bottom:0;" width="80" height="80">
	</div>
	<script>
		
	
	</script>

	