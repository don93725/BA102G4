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
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            
            <div class="col-lg-12">
                <h1>公告消息
                    <small>Knowledge</small>
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
    <div class="container" style='min-height: 100%;'>
    	<%@ include file="page1.file" %> 
		<c:forEach var='annew' items='${annewList }' begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <!-- Blog Post Row -->
        <div class="row aaaaaa">
            <div class="col-md-1 text-center" style='margin-top:10px;'>
                
                <p>${annew.ann_date }</p>
            </div>
            <div class="col-md-5">
                <a href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no}">
                    <img style='height:200px;' class="img-responsive img-hover" src="${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annew.ann_no}" alt="">
                </a>
            </div>
            <div class="col-md-6">
                <h3>
                    <a href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no}">${annew.ann_title }</a>
                </h3>
                <p>by 後台管理員
                </p>
                <p>${annew.ann_ctx }</p>
                <a class="btn btn-primary" href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no}">看更多 <i class="fa fa-angle-right"></i></a>
            </div>
        </div>
        <!-- /.row -->
		</c:forEach>
        </div>
    	<%@ include file="page2.file" %> 
		</div>
        <hr>

        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
