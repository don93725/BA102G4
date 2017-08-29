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
    			<h2>歡迎來到健貨 - Gym Home</h2>
    		</div></center>
    	</div>
        <br>
        <!-- 找XX -->
        <div class="row">
            <div class="col-md-4">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4><center><i class="icon-group"></i> 找教練?</center></h4>
                    </div>
                    <div class="panel-body f_coach">
					<p>擁有私人教練的好處：
<p>1. 增加訓練動機，避免因恆心不夠而中途放棄之現象。
2. 安全的訓練指導，減少因不當使用而造成的傷害。
3. 正確又具效果的訓練課程，專為個人量身訂做。
4. 針對特殊需求訓練，不論是傷害復健或是增強運動能力。
5. 自信的增加來自於健美勻稱的身材！良好的運動訓練計畫除了可促進健康外，最主要是可以獲得令人稱羨的完美身材。
不論您是要增重、強壯、減重、瘦身、復健、增強運動表現，您的專屬私人教練都會為您量身訂做一個屬於您個人的完美健身計畫！</p>
                    </div>
                    <div class="panel-body">
                    	<center><a href="<%=request.getContextPath()%>/front_end/browse/find_coaches.jsp"><span class="btn btn-warning btn-lg aa" data-rel="tooltip" data-placement="bottom">立刻預定教練</span></a></center>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-info">
                    <div class="panel-heading">
                        <h4><center><i class="icon-home"></i> 找場地?</center></h4>
                    </div>
                    <div class="panel-body f_coach">
                        <p>健貨致力於推廣社區運動發展，打造微型精緻專業機能健身中心。
為數極多的健身房業者中，分成為健身空間及運動教室，設有多功能教室/飛輪專屬教室，並引進世界前三大高級運動器材，包括重量訓練、心肺訓練、功能性訓練等各項器材。
相信健貨服務團隊由專業認證師資及健身教練組成，提供透明健全的會員制度，規劃數十種時下潮流課程，讓您享受健康玩課的樂趣。我們誠摯歡迎每位喜歡運動或是想要運動的人，一起加入我們。
不論您是要增重、強壯、減重、瘦身、復健、增強運動表現，我們的場地都會為您量身提供一個屬於您個人的完美健身場地！ </p>
					</div>
					<div class="panel-body">
                        <center><a href="<%=request.getContextPath()%>/CPM/CoachesPlaceManager.do?action=placeDetailList"><span class="btn btn-info btn-lg aa" data-rel="tooltip" data-placement="bottom">立刻預定場地</span></a></center>
                    </div>
                </div>
            </div>
            <div class="col-md-4">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4><center><i class="icon-calendar"></i> 找課程?</center></h4>
                    </div>
                    <div class="panel-body f_coach">
                        <p>我們誠摯歡迎每位喜歡運動或是想要運動的人，一起加入我們。 經由我們認真仔細有耐心的指導，推動你/妳們朝美麗、提升、強壯、健康的目標前進。健貨希望利用本身的運動專業知識、豐富的教學經驗，以及充滿熱忱的心，讓更多人能在舒適、乾淨和安全的環境下，享受運動給予的種種好處。 我們提供個人運動指導課程和小班制團體課程。
-個人運動指導課程：針對不同學員的身體情況，給予個別化的運動計畫，讓你/妳更有效率達成運動目標。服務項目包含一對一和一對二指導、體重控制、曲線雕塑、特殊族群運動指導、體能訓練、復健運動訓練等。
-小班制團體課程：小班制確實控管上課人數，讓教練更能注意到學員們的運動狀況，提升運動效率，避免運動傷害的產生。課程包含有氧舞蹈、瑜珈、皮拉提斯、滾筒按摩與訓練、體適能拳擊、TRX懸吊式訓練、循環訓練等。另提供公司行號企業配合與自由教練場地租借。</p>
					</div>
					<div class="panel-body">
					<center><a href="<%=request.getContextPath()%>/CCM/CourseManager.do?action=crsDetailList"><span class="btn btn-success btn-lg aa" data-rel="tooltip" data-placement="bottom"><i class="icon-hand-o-up"></i>立刻預定課程</span></a></center>
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
						
						<li style="display:flex;float:right;">
							<a href="<%= request.getContextPath()%>/AnnewShowCtrl">
								<right>
								<span>
									看更多&nbsp<i class="icon-arrow-right"></i>
								</span>
								</right>				
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
							<a href='<%= request.getContextPath()%>/FitkwCtrl?action=getOne_For_Display&fik_no=${fitkw.fik_no}'>
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
						<li style="display:flex;float:right;">
							<a href='<%= request.getContextPath()%>/front_end/fitkw/knowledges.jsp'>
								<span>
									看更多&nbsp<i class="icon-arrow-right"></i>
								</span>						
							</a>
						</li>
					</ul>
            	</div>
            </div>
            <!-- 健身知識 結束-->

            <div class="col-xs-12 col-md-4"  >
            	<!--廣告圖-->
<!--                 <img class="img-responsive" src="http://ochappy-co.cocolog-nifty.com/photos/uncategorized/2015/03/01/pic_006.jpg" style="width: 100%;"> -->
<%-- 					<%@ include file="/front_end/adapply/sliderAD.jsp"%> --%>
					<%@ include file="/front_end/adapply/FanPage.jsp" %>

				</div>
        </div>
</div><!-- page container3結束 -->
</div><!-- 最新消息+健身知識 結束 -->
<br>

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
<!-- 控制字數顯示 -->
<script>
$(function(){
	var len = 150; // 超過50個字以"..."取代
    $(".f_coach").each(function(){
        if($(this).text().length>len){
            $(this).attr("title",$(this).text());
            var text=$(this).text().substring(0,len-1)+" ...";
            $(this).text(text);
        }
    });
});
</script>
</html>
<div id="backTop"></div>
