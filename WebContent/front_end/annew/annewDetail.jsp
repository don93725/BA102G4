<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="zh-cn-en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<head>
	<title>健貨 - GymHome</title>
		<%@include file="/front_end/include/basicScript.file" %>
</head>
<%@include file="/front_end/include/basicScript.file" %>
 
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
                <h1 class="page-header">公告消息
                    <small>Announce</small>
                </h1>
            </div>

            <ol class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="${pageContext.request.contextPath }">首頁</a>
                     </li>
                <li class="active"><a href="${pageContext.request.contextPath }/AnnewShowCtrl">公告消息</a></li>
                <li class="active">公告詳情</li>
            </ol><!-- .breadcrumb -->
        </div>
        <!-- Page Heading/Breadcrumbs -->    

        <!-- Content Row -->
        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">
				<hr>
				
              	<p><i class="fa fa-clock-o"></i>日期：  ${annewVo.ann_date }</p>
				<hr>
				
                <!-- Preview Image -->
                <img class="img-responsive" src="${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annewVo.ann_no }" alt="">

                <hr>

                <!-- Post Content -->
               
                <p class="lead">${annewVo.ann_title }</p>
                <p >${annewVo.ann_ctx }</p>

                <hr>

                

    </div>
    </div></div>
        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
