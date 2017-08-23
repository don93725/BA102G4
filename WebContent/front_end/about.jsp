<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.manager.model.*" %>
<%@ page import="com.platinf.model.*" %>
<% ManagerService managerSV = new ManagerService();
   List<ManagerVO> mgrList = managerSV.getAll(); 
   pageContext.setAttribute("mgrList",mgrList);
   PlatinfService platinfSV = new PlatinfService();
   PlatinfVO plain = platinfSV.getOnePlatinf("1");
   pageContext.setAttribute("plain",plain);
   %>
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
                <h1>關於我們
                    <small>About us</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="${pageContext.request.contextPath }/front_end/index.jsp">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">關於我們</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>

        <!-- Intro Content -->
        <div class="row">
            <div class="col-md-6">
                <img class="img-responsive" src="${pageContext.request.contextPath }/util/OutputPic?pin_no=1" alt="">
            </div>
            <div class="col-md-6">
            	<div class='row'>
            	<div class="col-md-12">
            	<div class="col-md-7">
            	<h2>健貨 - GymHome</h2>
            	</div>
            	<div class="col-md-5">
            	<p></p>
            	<p><small>更新時間 ： ${plain.upd_date2}</small></p>
            	</div>
            	</div>
            	<div class="col-md-12">
            	<h4>電話： ${plain.cp_no}</h4><p></p>
            	<h4>地址： ${plain.com_address}</h4><p></p><p></p>
            	<h4>信箱： ${plain.cs_email}</h4><p></p><p></p>
            	<h4>介紹： ${plain.pr_policy}</h4><p></p><p></p>
            	</div>
            	</div>
            </div>
        </div>
        <!-- /.row -->

        <!-- Team Members -->
        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">我們的團隊</h2>
            </div>
            <c:forEach var="mgr" items="${mgrList }">
            <div class="col-md-4 text-center">
                <div class="thumbnail">
                    <img class="img-responsive" src="${pageContext.request.contextPath }/mgr/DBGifReader?mgr_no=${mgr.mgr_no}" alt="">
                    <div class="caption">
                        <h3>${mgr.mgr_name }<br>
                            <small>${(mgr.mgr_job==0)? "管理員":"系統管理員" }</small>
                        </h3>
                        <p>${mgr.mgr_int }</p>
                        
                    </div>
                </div>
            </div>
            </c:forEach>
        </div>
        <!-- /.row -->
		</div>
        <hr>

        <!-- Footer -->
        <%@include file="include/footer.file" %>

    </div>
    <!-- /.container -->



</body>

	<%@include file="include/basicScript2.file" %>

</html>
