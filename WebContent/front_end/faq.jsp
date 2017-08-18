<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
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
                <h1>常見問題
                    <small>FAQ</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="#">首頁</a>
                     </li>
                <li class="active">客服專區</li>
                <li class="active">常見問題</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>

        <!-- Content Row -->
        <div class="row">
            <div class="col-lg-12">
                <ul id="myTab" class="nav nav-tabs nav-justified">
                    <li class="active"><a href="#service-one" data-toggle="tab"><i class="icon-check"></i> 一般</a>
                    </li>
                    <li class=""><a href="#service-two" data-toggle="tab"><i class="icon-check"></i> 註冊</a>
                    </li>
                    <li class=""><a href="#service-three" data-toggle="tab"><i class="icon-check"></i> 儲值</a>
                    </li>
                    <li class=""><a href="#service-four" data-toggle="tab"><i class="icon-check"></i> 其他</a>
                    </li>
                </ul>


                <div id="myTabContent" class="tab-content aaaaaa">
                    <div class="tab-pane fade active in" id="service-one">
                    <p></p>
                        <div class="panel-group" id="accordion">
                            
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">問題一?</a>
                                    </h4>
                                </div>

                                <div id="collapseOne" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        不知道
                                    </div>
                                </div>
                            </div>
                            <!-- /.panel -->


                    <div class="tab-pane fade" id="service-two">
                        
                        <p></p>
                        <div class="panel-group" id="accordion">
                            
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <h4 class="panel-title">
                                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion" href="#collapseOne">不知道一?</a>
                                    </h4>
                                </div>

                                <div id="collapseOne" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        不知道
                                    </div>
                                </div>
                            </div>
                            <!-- /.panel -->

                        </div>
                        <!-- /.panel-group -->
                    </div>
                </div>              
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->


        <!-- Pager -->
        <div class="row">
            <div class="text-center">
                        <ul class="pagination">
                          <li><a href="#">&laquo;</a></li>
                          <li class="active"><a href="#">1</a></li>
                          <li><a href="#">2</a></li>
                          <li><a href="#">3</a></li>
                          <li><a href="#">4</a></li>
                          <li><a href="#">5</a></li>
                          <li><a href="#">&raquo;</a></li>
                        </ul>
                    </div>
        </div>
        <!-- /.row -->

        <!-- Call to Action Section -->
        <div class="well">
            <div class="row">
                <div class="col-md-4"></div>
                <div class="col-md-4">
                    <center><h3>問題依然無法解決?</h3>
                    <a class="btn btn-lg btn-default" href="contact.html">聯絡我們</a></center>
                </div>
                <div class="col-md-4"></div>
            </div>
        </div>

        <hr>

        <!-- Footer -->
	<%@include file="include/footer.file" %>

</body>
	<%@include file="include/basicScript2.file" %>
</html>
