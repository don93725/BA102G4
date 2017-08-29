<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
	<title>健貨 - GymHome</title>
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
                <h1>公告消息
                    <small>Announcements</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="index.html">首頁</a>
                     </li>
                <li class="active">公告消息</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>

    <!-- Page Content -->
    <div class="container" >
    	<%@ include file="page1.file" %> 
		
		<c:forEach var='annew' items='${annewList }' begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <!-- Blog Post Row -->
        <div class="row aaaaaa">
            
            <div class="col-md-5" style="text-align:center;" align="center">
            	<a href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no}">
            		<img style="height:256px;display:block; margin:auto;" class="img-responsive img-hover" src="${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annew.ann_no}" alt="" >
                </a>
            </div>
            
            <div class="col-md-7" style="vertical-align:middle;" align="center;">
				<div style="padding-bottom:30px;">
					<h3>
                    	<a href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no}">${annew.ann_title }</a>
                	</h3>
                </div>
                <div style="margin-left:0px;padding-bottom:20px;">日期: ${annew.ann_date }</div>  
                <div style="padding-left:0px;padding-bottom:20px;">by 後台管理員</div>
                <div style="padding-bottom:20px;">${annew.ann_ctx }</div>
               	<a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no}">看更多 <i class="fa fa-angle-right"></i></a>
            </div>   	
        </div>
        <!-- /.row -->
		<hr style="border:0;height:1px;background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);">		</c:forEach>
     
      </div>
    	<%@ include file="page2.file" %> 
	</div>

        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
