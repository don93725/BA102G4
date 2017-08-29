<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty searchResult}">
<%  	GymsService gymsSV = new GymsService();
		List<GymsVO> list = gymsSV.getAll();
		pageContext.setAttribute("list",list);
%>
</c:if>
<c:if test="${not empty searchResult}">
<%		List<GymsVO> searchResult = (List<GymsVO>)request.getAttribute("searchResult");
%>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>健貨 - GymHome</title>
    <%@include file="/front_end/include/basicScript.file" %>
	<!-- 限制字數控制 -->
	
<style>
	div.item-caption{
		margin-top: 0;
		padding-top: 0;
	}
	div.item-caption-inner1{
		 margin-top: 0;
		 padding-top: 0;
	}
	div.item-container{
		border-radius:100px;
	}
</style>

</head>


<body>

    <!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
    

    <!-- Page Content -->
    <div class="container" style="min-height: 100%;">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">    
            <div class="col-lg-12">
                <h1>找健身房
                    <small>Gyms</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">找健身房</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        
        <div class="alert alert-block alert-info">
        	<p>健貨致力於推廣社區運動發展，打造<strong>微型精緻專業機能健身中心。</strong><br>
			<p>為數極多的健身房業者中，分成為健身空間及運動教室，設有多功能教室/飛輪專屬教室，並引進世界前三大高級運動器材，包括重量訓練、心肺訓練、功能性訓練等各項器材。<br>
			<p>相信健貨服務團隊由專業認證師資及健身教練組成，提供透明健全的會員制度，規劃數十種時下潮流課程，讓您享受健康玩課的樂趣。我們誠摯歡迎每位喜歡運動或是想要運動的人，一起加入我們。<br>
			<p>不論您是要<strong>增重、強壯、減重、瘦身、復健、增強運動表現</strong>，我們的場地都會為您量身提供一個屬於您<strong>個人的完美健身場地！</strong></p>
        </div>
        
            	<form action="<%= request.getContextPath() %>/GymsServlet" method="post" id="search_gym">
                <div class="row">
                	
                	<div class="col-lg-2">
                            <input type="search" placeholder="輸入健身房姓名" name="search_Name" style="height:42px;">
                    </div>

                    <div class="col-lg-2">
                        <input type="button" class="btn btn-info" value="查看結果" style="width:163px;" onclick="tick.call(this);">
                        <input type="hidden" name="action" value="search_gym">
                    </div>

                    <div class="col-lg-2">
                        <input type="reset" class="btn btn-default" style="width:163px;" value="重置條件">
                    </div>
                </div>
                </form>

<p></p>                           
<div class="row bigHead">
<c:if test="${not empty errorMsgs}">
	<div class="row">
		<div class="col-md-4 pic">
                <img src="<%= request.getContextPath() %>/style/images/noResult.png">
        </div>
		<div class="col-md-6 word" style="font-size:32px;">
			<div style="text-align:left;">
				<b>我很抱歉 ,查無結果<br><p></p>
				I am SORRY ,no results found<br><p></p>
				ごめんなさい ,結果が見つかりません</b>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${empty searchResult && empty errorMsgs}">
		<div class="row"  style="margin-bottom:20px;margin-top:20px;text-align:center;">
<c:forEach var="gymsVO" items="${list}">
			 <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
				<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=2&mem_no=${gymsVO.gym_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${gymsVO.gym_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=2&mem_no=${gymsVO.gym_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${gymsVO.gym_name }
						<p style="color:#31708f;font-size:14px;"><strong>健身房</strong></p>
						</h3>
						</p>
						
					</center>
				</div>
			</div>
</c:forEach>
		</div>	
</c:if>

<c:if test="${not empty searchResult && empty errorMsgs}">
		<div class="row"  style="margin-bottom:20px;margin-top:20px;text-align:center;">
<c:forEach var="gymsVO" items="${searchResult}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
				<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=2&mem_no=${gymsVO.gym_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${gymsVO.gym_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=2&mem_no=${gymsVO.gym_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${gymsVO.gym_name }
						<p style="color:#31708f;font-size:14px;"><strong>健身房</strong></p>
						</h3>
						</p>
						
					</center>
				</div>
			</div>
</c:forEach>
		</div>
</c:if>
</div>
	
      
</div>
<!-- /.container3 -->
</div>

<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<%@include file="/front_end/include/onlyForFG.file" %>
</html>
