<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.fitkw.model.*" %>
<%@ page import="java.util.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    FitkwService fitkwSvc = new FitkwService();
    List<FitkwVO> list = fitkwSvc.getFrontAll();
    pageContext.setAttribute("list",list);
%>

<html lang="zh-cn-en">

<head>
	<title>健身知識列表</title>
		<%@include file="/front_end/include/basicScript.file" %>
</head>


<body>

    <!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>

    <!-- Page Content -->
    <div class="container" style="min-height: 100%">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            <div class="col-lg-12">
                <h1>健身知識列表
                    <small>Knowledge List</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="${pageContext.request.contextPath }">首頁</a>
                     </li>
                <li class="active">健身知識列表</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>

    <!-- Page Content -->
    <div class="container">


<%@ include file="page1.file" %>
<c:forEach var="fitkwVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
        <!-- Blog Post Row -->
        <div class="row aaaaaa">
  
            <div class="col-md-5" style="text-align:center;" align="center">
            	<a href="${pageContext.request.contextPath }/FitkwCtrl?action=getOne_For_Display&fik_no=${fitkwVO.fik_no}">
<!--             	<img class="img-responsive img-hover" src="http://img1.voc.com.cn/UpLoadFile/2017/06/01/201706011740554338.jpeg" alt=""> -->
            		<img class="img-responsive img-hover" src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}' alt="" style="height:256px;display:block; margin:auto;">
                </a>
            </div>
            <div class="col-md-7" style="vertical-align:middle;" align="center;">
                    <span class="label label-xlg label-warning">${fitkwVO.fik_type}</span><h3 style=""><a href="${pageContext.request.contextPath }/FitkwCtrl?action=getOne_For_Display&fik_no=${fitkwVO.fik_no}">${fitkwVO.fik_title}</a></h3>
                
                	<div class="col-lg-6" style="padding-left:0px;margin-top:-5px;">by 後台管理員</div> <div class="col-lg-6" align="right" style="margin-top:-5px;">日期: ${fitkwVO.upd_date}</div><br>
                	<br>
                <div style="margin-top:-10px;">${fitkwVO.fik_ctx }</div>
                <br>
                <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath }/FitkwCtrl?action=getOne_For_Display&fik_no=${fitkwVO.fik_no}">看更多 <i class="icon icon-arrow-right"></i></a>
            </div>
        </div>
        <hr style="border:0;height:1px;background-image: -webkit-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);background-image: -moz-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);background-image: -ms-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);background-image: -o-linear-gradient(left, #f0f0f0, #8c8b8b, #f0f0f0);">
        <!-- /.row -->
   </c:forEach>

        <!-- Pager -->
        <div class="row">
            <div class="text-center">
						<ul class="pagination">
						<%@ include file="page2.file" %>
<!-- 						  <li><a href="#">&laquo;</a></li> -->
<!-- 						  <li><a href="#">1</a></li> -->
<!-- 						  <li><a href="#">2</a></li> -->
<!-- 						  <li class="active"><a href="#">3</a></li> -->
<!-- 						  <li><a href="#">4</a></li> -->
<!-- 						  <li><a href="#">5</a></li> -->
<!-- 						  <li><a href="#">&raquo;</a></li> -->
						</ul>
			</div>
        </div>
        <!-- /.row -->
	</div>
</div>

        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
