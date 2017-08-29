<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fitkw.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO");
%>

<html lang="zh-cn-en">

<head>
	<title><%= fitkwVO.getFik_title()%></title>
		<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>

    <!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>

    <!-- Page Content -->
      <div class="container">

        <!-- Page Heading/Breadcrumbs -->
            <div class="breadcrumbs" id="breadcrumbs">
            	<div class="col-lg-12">
                	<h1>健身知識
                    	<small>Knowledge</small>
                	</h1>
            	</div>

                <ul class="breadcrumb">
                	<li>
                		<i class="icon-home home-icon"></i>
                			<a href="${pageContext.request.contextPath }">首頁</a>
                    </li>
                    <li class="active"><a href="${pageContext.request.contextPath }/front_end/fitkw/knowledges.jsp">健身知識列表</a></li>
                    <li class="active">健身知識</li>
                </ul>
            </div>
        <!-- /.row -->

        <!-- Content Row -->
        <div class="row">
				<div class="col-lg-12" style="padding-top:5px;" align="right">
                	<i class="fa fa-clock-o"></i>日期： <%= fitkwVO.getUpd_date()%>
                </div>
                
				<div class="col-lg-12" align="center">
               		<h1><b><%= fitkwVO.getFik_title()%></b></h1>
                </div>

                <hr>
				
				<div class="col-lg-12" align="center" style="padding-top:10px;padding-bottom:20px;">
                	<img class="img-responsive" src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}' alt="" style="width:80%;">
				</div>
				
                <hr>

                <div class="col-lg-12" style="padding-left:50px;padding-right:50px;">
                	<%= fitkwVO.getFik_ctx()%>
                </div>

                <hr>
                
				<div class="col-lg-12" align="center" style="padding-top:10px;">
					<a href="${pageContext.request.contextPath }/front_end/fitkw/knowledges.jsp">回上頁</a>
				</div>
                <!-- Blog Comments -->

    </div>
    </div>

        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
