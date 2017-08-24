<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fitkw.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    FitkwService fitkwSvc = new FitkwService();
    List<FitkwVO> list = fitkwSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<html lang="zh-cn-en">

<head>
	<title>這是標題</title>
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
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            
            <div class="col-lg-12">
                <h1>健身知識
                    <small>Knowledge</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="index.html">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">健身知識</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>

    <!-- Page Content -->
    <div class="container">




        <!-- Blog Post Row -->
        <div class="row aaaaaa">
  <c:forEach var="fitkwVO" items="${list}">
            <div class="col-md-1 text-center" style='margin-top: 5px;border: 1px black;'>
                <p><i class="fa fa-camera fa-4x"></i>
                </p>
                <p>${fitkwVO.upd_date}</p>
            </div>
            <div class="col-md-5">
                <a href="blog-post.html">
                    <img class="img-responsive img-hover" src="http://img1.voc.com.cn/UpLoadFile/2017/06/01/201706011740554338.jpeg" alt="">
                </a>
            </div>
            <div class="col-md-6">
                <h3>
                    <a href="blog-post.html">${fitkwVO.fik_title}</a>
                </h3>
                <p>by <a href="#">後台管理員</a>
                </p>
                <p>${fitkwVO.fik_ctx }</p>
                <a class="btn btn-primary" href="blog-post.html">看更多 <i class="fa fa-angle-right"></i></a>
            </div>
   </c:forEach>
        </div>
        <!-- /.row -->



        <hr>

        <!-- Pager -->
        <div class="row">
            <div class="text-center">
						<ul class="pagination">
						  <li><a href="#">&laquo;</a></li>
						  <li><a href="#">1</a></li>
						  <li><a href="#">2</a></li>
						  <li class="active"><a href="#">3</a></li>
						  <li><a href="#">4</a></li>
						  <li><a href="#">5</a></li>
						  <li><a href="#">&raquo;</a></li>
						</ul>
					</div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
