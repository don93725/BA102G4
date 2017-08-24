<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<%@ page import="java.util.*"%>
<%@ page import="com.adapply.model.*" %>

<%
	AD_ApplyService ADSvc = new AD_ApplyService();
	Set<AD_ApplyVO> list = ADSvc.getStat(1);
	pageContext.setAttribute("list", list);
%>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- icon -->
<link rel="shortcut icon" href="/BA102G4/style/images/icon.ico" />

    <link href="/BA102G4/style/css/bootstrap.css" rel="stylesheet">
  
    <link href="/BA102G4/style/css/modern-business.css" rel="stylesheet">
  
    <link rel="stylesheet" href="/BA102G4/style/assets/css/font-awesome.min.css" />
  
    <script src="/BA102G4/style/js/jquery.js"></script>
    
    <script src="/BA102G4/style/js/bootstrap.min.js"></script>
   
    <link rel="stylesheet" href="/BA102G4/style/assets/css/ace.min.css" />  
	
	<link rel="stylesheet" href="/BA102G4/style/assets/css/sweetalert.css" />
	<script src="/BA102G4/style/assets/js/sweetalert.min.js"></script>  
    
    <script src="/BA102G4/style/assets/js/jquery.colorbox.js"></script>
    <link rel="stylesheet" href="/BA102G4/style/assets/css/colorbox.css" />
   
    <link rel="stylesheet" type="text/css" href="/BA102G4/style/assets/css/animate.css">

<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>Insert title here</title>
</head>
<body>

<!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
		
<!-- Indicators -->
        <ol class="carousel-indicators">
            <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
       <%
		for(int i=0;i<list.size();i++){
       %>
            <li data-target="#myCarousel" data-slide-to="<%=i+1%>"></li>
       <%
		}
       %>
     
        </ol>

       
        <div class="carousel-inner">
            <div class="item active">
                <div class="fill" style="background-image:url('/BA102G4/style/images/header3.png');"></div>
                <div class="carousel-caption">
                </div>
            </div>
       <c:forEach var="ad_ApplyVO" items="${list}" >
            <div class="item">
                <div class="fill" style="background-image:url('<%=request.getContextPath()%>/tools/DBGifReader?ad_no=${ad_ApplyVO.ad_no}');"></div>
                <div class="carousel-caption">
                </div>
            </div>
        </c:forEach>    
        </div>


        <a class="left carousel-control" href="#myCarousel" data-slide="prev">
            <span class="icon-prev"></span>
        </a>
        <a class="right carousel-control" href="#myCarousel" data-slide="next">
            <span class="icon-next"></span>
        </a>
        
        <!-- Script to Activate the Carousel -->
    	<script>
    	$('.carousel').carousel({
        	interval: 2500 //changes the speed
    	})
    	</script>
	</header>

	 



</body>
</html>