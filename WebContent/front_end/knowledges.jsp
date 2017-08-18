<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
	<title>這是標題</title>
		<%@include file="include/basicScript.file" %>
</head>


<body>

    <!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="include/front_navbar.file" %>
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
            <div class="col-md-1 text-center">
                <p><i class="fa fa-camera fa-4x"></i>
                </p>
                <p>2017-07-20</p>
            </div>
            <div class="col-md-5">
                <a href="blog-post.html">
                    <img class="img-responsive img-hover" src="http://img1.voc.com.cn/UpLoadFile/2017/06/01/201706011740554338.jpeg" alt="">
                </a>
            </div>
            <div class="col-md-6">
                <h3>
                    <a href="blog-post.html">第一篇文章，裡面有照片 600*300</a>
                </h3>
                <p>by <a href="#">後台管理員</a>
                </p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                <a class="btn btn-primary" href="blog-post.html">看更多 <i class="fa fa-angle-right"></i></a>
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Blog Post Row -->
        <div class="row aaaaaa">
            <div class="col-md-1 text-center">
                <p><i class="fa fa-music fa-4x"></i>
                </p>
                <p>2017-07-20</p>
            </div>
            <div class="col-md-5">
                <a href="blog-post.html">
                    <img class="img-responsive img-hover" src="http://img1.voc.com.cn/UpLoadFile/2017/06/01/201706011740568569.jpeg" alt="">
                </a>
            </div>
            <div class="col-md-6">
                <h3>
                    <a href="blog-post.html">第二篇文章，裡面有影片</a>
                </h3>
                <p>by <a href="#">後台管理員</a>
                </p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                <a class="btn btn-primary" href="blog-post.html">看更多 <i class="fa fa-angle-right"></i></a>
            </div>
        </div>
        <!-- /.row -->

        <hr>

        <!-- Blog Post Row -->
        <div class="row aaaaaa">
            <div class="col-md-1 text-center">
                <p><i class="fa fa-flag fa-4x"></i>
                </p>
                <p>2017-07-20</p>
            </div>
            <div class="col-md-5">
                <a href="blog-post.html">
                    <img class="img-responsive img-hover" src="http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=http://mmbiz.qpic.cn/mmbiz_jpg/8SnruvhTreCFICiaCutlp1Z3xoxm65IGG7NqgvazzqQCQ9ooVAeictNvBPRagVfPSxvyvQl60yDhB4JDwz7icibyicQ/0" alt="">
                </a>
            </div>
            <div class="col-md-6">
                <h3>
                    <a href="blog-post.html">第三篇文章，裡面只有文章</a>
                </h3>
                <p>by <a href="#">後台管理員</a>
                </p>
                <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur.</p>
                <a class="btn btn-primary" href="blog-post.html">看更多 <i class="fa fa-angle-right"></i></a>
            </div>
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
	<%@include file="include/footer.file" %>

</body>
	<%@include file="include/basicScript2.file" %>
</html>
