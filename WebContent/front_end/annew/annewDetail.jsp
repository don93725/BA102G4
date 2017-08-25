<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<head>
	<title>���f - GymHome</title>
		<%@include file="/front_end/include/basicScript.file" %>
</head>

 
<body>

    <!-- �����C -->
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
                <h1>���i����
                    <small>Announce</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="index.html">����</a>
                     </li>
                <li class="active"><a href="${pageContext.request.contextPath }/AnnewShowCtrl">���i����</a></li>
                <li class="active">���i�Ա�</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        <!-- Page Heading/Breadcrumbs -->
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">${annewVo.ann_title }<br>
                    <small><i class="fa fa-clock-o"></i> Posted by �t�κ޲z�� on ${annewVo.ann_date }
                    </small>
                </h1>

            </div>
        </div>
        <!-- /.row -->

        <!-- Content Row -->
        <div class="row">

            <!-- Blog Post Content Column -->
            <div class="col-lg-8">

              

                <!-- Preview Image -->
                <img class="img-responsive" src="${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annewVo.ann_no }" alt="">

                <hr>

                <!-- Post Content -->
                <p class="lead">${annewVo.ann_ctx }</p>

                <hr>

                

    </div>
    </div></div>
        <!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
