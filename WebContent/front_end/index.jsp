<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="com.don.util.*" %>
<%@ page import="com.annew.model.*" %>
<%@ page import="com.fitkw.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%  CoachesService coachesSV = new CoachesService();
	List<CoachesVO> coachRank = coachesSV.getRankList();
	pageContext.setAttribute("coachRank", coachRank);
	IndexFiller idxFill = new IndexFiller();
	List<AnnewVO> annewList = idxFill.getNewAnnew();
	pageContext.setAttribute("annewList", annewList);
	List<FitkwVO> fitkwList = idxFill.getNewFitkw();
	pageContext.setAttribute("fitkwList", fitkwList);
	
	%>
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

    <!-- Header Carousel -->
    <header id="myCarousel" class="carousel slide">
		<%@include file="/front_end/include/indexHeader.file" %>
	</header>
	
	<br>
	
    <!-- Page Content -->
    <div class="container">
    	<div class="row">
    		<center><div class="col-lg-12">
    			<h2>歡迎來到健貨-Gym Home ~</h2>
    		</div></center>
    	</div>
        <br>
        <!-- 找XX -->
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4><center><i class="icon-star icon-lg"></i>找教練?${user.mem_no}${user.mem_acc}${user.mem_rank}${user.mem_nickname}</center></h4>
                    </div>
                    <div class="panel-body">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla aspernatur in alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus eveniet incidunt dicta nostrum quod?</p>
                        <center><a href="find_coaches.jsp"><span class="btn btn-success btn-lg tooltip-success" data-rel="tooltip" data-placement="bottom" title="訊息">立刻預定教練</span></a></center>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4><center><i class="icon-bell-alt"></i> 找場地?</center></h4>
                    </div>
                    <div class="panel-body">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla aspernatur in alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus eveniet incidunt dicta nostrum quod?</p>
                        <center><a href="find_pleaces.jsp"><span class="btn btn-warning btn-lg tooltip-warning" data-rel="tooltip" data-placement="bottom" title="訊息">立刻預定場地</span></a></center>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><center><i class="icon-bell-alt"></i> 找課程?</center></h4>
                    </div>
                    <div class="panel-body">
                        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Itaque, optio corporis quae nulla aspernatur in alias at numquam rerum ea excepturi expedita tenetur assumenda voluptatibus eveniet incidunt dicta nostrum quod?</p>
                        <center><a href="<%=request.getContextPath()%>/CCM/CourseManager.do?action=crsDetailList"><span class="btn btn-info btn-lg tooltip-info" data-rel="tooltip" data-placement="bottom" title="訊息"><i class="icon-hand-o-up"></i>立刻預定課程</span></a></center>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.row -->
	</div>


<!-- 教練排行榜 -->
<div class="full_width" style="background-image: url('/BA102G4/style/images/ranking_img.jpg');background-attachment: fixed;">
<!-- Page2 Content -->
<div class="container">
<br>
	<div class="row">
            <div class="col-xs-12 col-md-12" style="color:white;">
                <h2 class="page-header"><center>教練熱門排行榜<small style="color:white;">Ranking700*450</small></center></h2>
            </div>
            
            <!-- 教練1 -->
            <div class="col-md-4 col-md-4 col-sm-6">
				<div class="wrap-col">
					<div class="item-container">
							<div class="title">
								<h2>本周第一名教練</h2>
							</div>
						<div class="item-caption yellow">
							<c:if test="${ fn:length(coachRank)>0 }">
								<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachRank[0].coa_no}&action=lookPersonal">
							</c:if>
         					<c:if test="${fn:length(coachRank)<1 }">
								<a href="#">
							</c:if>
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<c:if test="${ fn:length(coachRank)>0 }">
											<h4>${coachRank[0].coa_name }</h4></br>
											<span>${coachRank[0].coa_into }</span>
										</c:if>
            							<c:if test="${fn:length(coachRank)<1}">
											<h4>目前從缺</h4></br>										
										</c:if>
									</div>
								</div>
							</a>
						</div>
							<c:if test="${ fn:length(coachRank)>0 }">
								<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachRank[0].coa_no}" alt=""/>
							</c:if>
         				<c:if test="${ fn:length(coachRank)<1 }">
							<img  src="http://down.epchina.com/portal/201509/08/154457pee2rumj27f145bu.jpg" alt=""/>
						</c:if>
					</div>
				</div>
			</div>
			
			<!-- 教練2 -->
			<div class="col-md-4 col-md-4 col-sm-6">
				<div class="wrap-col">
					<div class="item-container ranking_coa">
							<div class="title">
								<h2>本周第二名教練</h2>
							</div>
						<div class="item-caption yellow">
							<c:if test="${ fn:length(coachRank)>1 }">
								<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachRank[1].coa_no}&action=lookPersonal">
							</c:if>
         					<c:if test="${ fn:length(coachRank)<2 }">
								<a href="#">
							</c:if>
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
									<c:if test="${ fn:length(coachRank)>1 }">
											<h4>${coachRank[1].coa_name }</h4></br>
											<span>${coachRank[1].coa_into }</span>
										</c:if>
            							<c:if test="${fn:length(coachRank)<2 }">
											<h4>目前從缺</h4></br>										
										</c:if>
										</div>
								</div>
							</a>
						</div>
						<c:if test="${ fn:length(coachRank)>1 }">
								<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachRank[1].coa_no}" alt=""/>
							</c:if>
         					<c:if test="${ fn:length(coachRank)<2 }">
												<img  src="http://down.epchina.com/portal/201509/08/154457pee2rumj27f145bu.jpg" alt=""/>
						</c:if>
					</div>
				</div>
			</div>
			
			<!-- 教練3 -->
			<div class="col-md-4 col-md-4 col-sm-6">
				<div class="wrap-col">
					<div class="item-container ranking_coa">
							<div class="title">
								<h2>本周第三名教練</h2>
							</div>
						<div class="item-caption yellow">
							<c:if test="${ fn:length(coachRank)>2 }">
								<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachRank[2].coa_no}&action=lookPersonal">
							</c:if>
         					<c:if test="${ fn:length(coachRank)<3 }">
								<a href="#">
							</c:if>
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
            							<c:if test="${ fn:length(coachRank)>2 }">
											<h4>${coachRank[2].coa_name }</h4></br>
											<span>${coachRank[2].coa_into }</span>
										</c:if>
            							<c:if test="${ fn:length(coachRank)<3 }">
											<h4>目前從缺</h4></br>										
										</c:if>
									</div>
								</div>
							</a>
						</div>
							
							<c:if test="${ fn:length(coachRank)>2 }">
								<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachRank[2].coa_no}" alt=""/>
							</c:if>
         					<c:if test="${ fn:length(coachRank)<3 }">
								<img  src="http://down.epchina.com/portal/201509/08/154457pee2rumj27f145bu.jpg" alt=""/>
							</c:if>
					</div>
				</div>
			</div>
	</div>
</div>
<!-- container2結束 -->
</div>
<!-- 教練排行榜結束 -->

<!-- 最新消息+健身知識 -->
<div>
<!-- Page3 Content -->
<div class="container">
<br>

        <div class="row">
        	<!-- 最新消息 -->
			<div class="col-xs-12 col-md-4">
            	<div class="panel panel-default" style="margin-top: 0px;padding-top: 0px;">
                    <div class="panel-heading" style="padding-top: 0px;padding-bottom: 0px;background-color:white;">
            			<h3>最新消息<small>News</small></h3>      	  	
            	  	</div>
            	  	<p></p>
            		<ul style="list-style:none; margin-left: 15px;">
					<c:forEach var="annew" items="${annewList }">
						<li style="margin-left: 0px; display: flex;">
							<a href="${pageContext.request.contextPath }/AnnewShowCtrl?ann_no=${annew.ann_no }">
								<span class="label label-lg label-primary arrowed-in">
									公告
								</span>
								<span>
									${annew.ann_date2 }
								</span>
								<span class="title">
									${annew.ann_title }
								</span>
							</a>
						</li>
						<p></p>
						</c:forEach>
						
						<li style="margin-left: 0px; display: flex;">
							<a href="<%= request.getContextPath()%>/AnnewShowCtrl">
								<span>
									看更多&nbsp<i class="icon-arrow-right"></i>
								</span>						
							</a>
						</li>
					</ul>
				</div>	
            </div>
            <!-- 最新消息 結束-->
        
        	<!-- 健身知識 --> 
            <div class="col-xs-12 col-md-4">
            	<div class="panel panel-default" style="margin-top: 0px;padding-top: 0px;">
                    <div class="panel-heading" style="padding-top: 0px;padding-bottom: 0px;background-color:white;">
            			<h3>健身知識<small>knowledge</small></h3>
            	  	</div>
            	  	<p></p>
            		<ul style="list-style:none;margin-left:15px;">
					<c:forEach var='fitkw' items="${fitkwList}">
						<li style="margin-left: 0px; display: flex;">
							<a>
								<span class="label label-lg label-pink arrowed-right">
									${fitkw.fik_type }
								</span>
								<span>
									${fitkw.upd_date2 }
								</span>
								<span class="title">
									${fitkw.fik_title }
								</span>
							</a>
						</li>
						<p></p>
						</c:forEach>
						<li style="margin-left: 0px; display: flex;">
							<a>
								<span>
									看更多&nbsp<i class="icon-arrow-right"></i>
								</span>						
							</a>
						</li>
					</ul>
            	</div>
            </div>
            <!-- 健身知識 結束-->
             
            <div class="col-xs-12 col-md-4">
            	<!--廣告圖-->
                <img class="img-responsive" src="http://ochappy-co.cocolog-nifty.com/photos/uncategorized/2015/03/01/pic_006.jpg" style="width: 100%;">
            </div>
        </div>
</div><!-- page container3結束 -->
</div><!-- 最新消息+健身知識 結束 -->


<!-- 討論區 -->
<div class="full_width" style="background-image: url('/BA102G4/style/images/ranking_img.jpg');background-attachment: fixed;">
<!-- page container4 -->
<div class="container">
<p></p>
        <!-- 討論區 -->
        <%@ include file="/front_end/include/forum.file" %>
		<!-- /.row -->   
<br>
</div>
<!-- /.container3 -->
</div>
<!-- 討論區結束 -->

	<!-- 回頂端、Footer、最底部 -->
	<%@include file="/front_end/include/footer.file" %>
	
</body>
	<!--navbar淡入、下拉式選單滑動、navbar變色、物件淡出效果、回到最頂端、控制字數顯示 -->
	<%@include file="/front_end/include/basicScript2.file" %>
</html>
<div id="backTop"></div>
