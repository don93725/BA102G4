<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%  	CoachesService coachesSV = new CoachesService();
		List<CoachesVO> list = coachesSV.getAll();
		pageContext.setAttribute("list",list);
%>

<c:if test="${not empty searchResult}">
<%		List<CoachesVO> searchResult = (List<CoachesVO>)request.getAttribute("searchResult");
%>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>健貨 - GymHome</title>
    <%@include file="/front_end/include/basicScript.file" %>
	<!-- 限制字數控制 -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
<style>
	div.item-caption{
		margin-top: 0;
		padding-top: 0;
	}
	div.item-caption-inner1{
		 margin-top: 0;
		 padding-top: 0;
	}
	div.img_title{
		text-align: center;
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
    <div class="container" style="min-height: 100%;margin: 0 auto -170px;">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">    
            <div class="col-lg-12">
                <h1>找教練
                    <small>Coaches</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">找教練</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        
        <div class="alert alert-block alert-warning">
        	<p><strong>擁有私人教練的好處：</strong><br>
			<p>1. 增加訓練動機，避免因恆心不夠而中途放棄之現象。<br>
			2. 安全的訓練指導，減少因不當使用而造成的傷害。<br>
			3. 正確又具效果的訓練課程，專為個人量身訂做。<br>
			4. 針對特殊需求訓練，不論是傷害復健或是增強運動能力。<br>
			5. 自信的增加來自於健美勻稱的身材！良好的運動訓練計畫除了可促進健康外，最主要是可以獲得令人稱羨的完美身材。<br>
			<p>不論您是要<strong>增重、強壯、減重、瘦身、復健、增強運動表現</strong>，您的專屬私人教練都會為您量身訂做一個屬於您<strong>個人的完美健身計畫！</strong></p>
        </div>
            	<form action="<%= request.getContextPath() %>/CoachesServlet" method="post" id="search_coa">
                <div class="row">
                	
                	<div class="col-lg-2">
                            <input type="search" placeholder="輸入教練姓名" name="search_Name" style="height:42px;">
                    </div>
                    
                    <div class="col-lg-2">
                    	<select class="form-control" id="form-field-select-1" name="search_Type" style="height:42px;">
							<option value="0">不限查詢類型</option>
                        	<option value="A">瑜珈</option>
                        	<option value="B">飛輪有氧</option>
                        	<option value="C">舞動有氧</option>
                        	<option value="D">拳擊有氧</option>
							<option value="E">基礎重訓</option>
							<option value="F">進階重訓</option>
							<option value="G">皮拉提斯</option>
							<option value="H">TRX肌力雕塑</option>
							<option value="O">其他</option>
                    	</select>
                    </div>

                    <div class="col-lg-2">
                        <input type="button" class="btn btn-warning" value="查看結果"  style="width:163px;" onclick="tick.call(this);">
                        <input type="hidden" name="action" value="search_coa">
                    </div>

                    <div class="col-lg-2">
                        <input type="reset" class="btn btn-default" value="重置條件" style="width:163px;">
                    </div>
                </div>
                </form>
<p></p>                      
<div class="row bigHead">
<c:if test="${not empty errorMsgs}">
	<div class="row">
		<div class="col-md-6 pic" style="text-align:center;">
                <img style="height:400px;" src="<%= request.getContextPath() %>/style/images/noResult.png">
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
<c:forEach var="coachesVO" items="${list}">
			 <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachesVO.coa_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${coachesVO.coa_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachesVO.coa_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${coachesVO.coa_name }
						<p style="color:#8a6d3b;font-size:14px;"><strong>教練</strong></p>
						</h3>
						</p>
						
					</center>
				</div>
			</div>
</c:forEach>
		</div>	
</c:if>

<c:if test="${not empty searchResult && empty errorMsgs}">
		<div class="row"  style="margin-bottom:20px;margin-top:20px;">
<c:forEach var="coachesVO" items="${searchResult}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;text-align:center;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachesVO.coa_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${coachesVO.coa_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachesVO.coa_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<p>
					<h3 style="margin-top:0px;padding-bottom:0px;">
					${coachesVO.coa_name }
					<p style="color:#8a6d3b;font-size:14px;"><strong>教練</strong></p>
					</h3>
					</p>
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
	<%@include file="/front_end/include/onlyForFC.file" %>
</html>
