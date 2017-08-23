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
	div.img_title{
		text-align: center;
		background-color: #FFFFBB;
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
    <div class="container">
        
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
                        <a href="#">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">找健身房</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
            	<form action="<%= request.getContextPath() %>/GymsServlet" method="post" id="search_gym">
                <div class="row">
                	
                	<div class="col-lg-2">
                            <input type="search" placeholder="輸入健身房姓名" name="search_Name">
                    </div>
                    
<!--                     <div class="col-lg-2"> -->
<!--                     	<select class="form-control" id="form-field-select-1" name="search_Type"> -->
<!-- 							<option value="0">不限查詢類型</option> -->
<!--                         	<option value="A">瑜珈</option> -->
<!--                         	<option value="B">飛輪有氧</option> -->
<!--                         	<option value="C">舞動有氧</option> -->
<!--                         	<option value="D">拳擊有氧</option> -->
<!-- 							<option value="E">基礎重訓</option> -->
<!-- 							<option value="F">進階重訓</option> -->
<!-- 							<option value="G">皮拉提斯</option> -->
<!-- 							<option value="H">TRX肌力雕塑</option> -->
<!-- 							<option value="O">其他</option> -->
<!--                     	</select> -->
<!--                     </div> -->

                    <div class="col-lg-2">
                        <input type="button" class="btn btn-info" value="查看結果" onclick="tick.call(this);">
                        <input type="hidden" name="action" value="search_gym">
                    </div>

                    <div class="col-lg-2">
                        <input type="reset" class="btn btn-default" value="重置條件">
                    </div>
                </div>
                </form>
                               
<div class="row bigHead">
<c:if test="${not empty errorMsgs}">
	<div class="row">
		<div class="col-md-4 pic">
                <img src="<%= request.getContextPath() %>/style/images/noResult.png">
        </div>
		<div class="col-md-8 word">
			<b>我很抱歉 ,查無結果<br><p></p>
			I am SORRY ,no results found<br><p></p>
			ごめんなさい ,結果が見つかりません</b>
		</div>
	</div>
</c:if>

<c:if test="${empty searchResult && empty errorMsgs}">
		<div class="row">
<c:forEach var="gymsVO" items="${list}">
			<div class="col-md-2 col-sm-4">
				<div class="img_title">
					<center>
						教練
						<h3 style="margin-top:5px;">
						${gymsVO.gym_name }
						</h3>
					</center>
				</div>	
					<div class="item-container">
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
			</div>
</c:forEach>
		</div>	
</c:if>

<c:if test="${not empty searchResult && empty errorMsgs}">
		<div class="row">
<c:forEach var="gymsVO" items="${searchResult}">
            <div class="col-md-2 col-sm-4">
				<div class="img_title">
					<center>
						教練
						<h3 style="margin-top:5px;">
						${gymsVO.gym_name }
						</h3>
					</center>
				</div>	
					<div class="item-container">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${gymsVO.gym_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${gymsVO.gym_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${gymsVO.gym_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
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
