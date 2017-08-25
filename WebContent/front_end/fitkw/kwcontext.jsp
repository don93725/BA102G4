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
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">健身知識
                    <small><a href="#">Knowledge</a>
                    </small>
                </h1>
                <ol class="breadcrumb">
                    <li><a href="index.html">首頁</a>
                    </li>
                    <li class="active"><a href="${pageContext.request.contextPath }/front_end/fitkw/knowledges.jsp">健身知識列表</a></li>
                    <li class="active">健身知識</li>
                </ol>
            </div>
        </div>
        <!-- /.row -->

        <!-- Content Row -->
        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

                <!-- Blog Post -->

                <hr>

                <!-- Date/Time -->
                <p><i class="fa fa-clock-o"></i>日期： <%= fitkwVO.getUpd_date()%></p>

                <hr>

                <!-- Preview Image -->
                <img class="img-responsive" src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}' alt="" style="width:80%;">

                <hr>

                <!-- Post Content -->
                <p class="lead"><%= fitkwVO.getFik_title()%></p>
                <p><%= fitkwVO.getFik_ctx()%></p>
                

                <hr>

                <!-- Blog Comments -->

              

        </div>
        <!-- /.row -->
        <!-- Footer -->        

    </div>
        <hr>

        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
