<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty searchResult}">
<%  	StudentsService studentsSV = new StudentsService();
		List<StudentsVO> list = studentsSV.getAll();
		pageContext.setAttribute("list",list);
%>
</c:if>
<c:if test="${not empty searchResult}">
<%		List<StudentsVO> searchResult = (List<StudentsVO>)request.getAttribute("searchResult");
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
                <h1>找學員
                    <small>Students</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">找學員</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        
        <div class="alert alert-block alert-success">
        	<p><strong>把健身帶入生活，處處都是健身房</strong><br>
			<p>因為<strong>關心、分享</strong>的概念，希望將有助益大眾的健身訊息，更廣為被運用與散布，於是他也開始嘗試將健身活動帶到戶外，有些學員就曾跟著他去公園做過健身訓練。<br>
			<p>他將公園常見的器材或是坡度地形設計成可供訓練的方式，把健身融入日常生活環境中。甚至還從去年開始籌畫、將日常中隨身可得的工具，設計成健身動作，透過圖解的簡單的健身工具書，掃把、長柄傘等都一一變成健身器材了，<strong>畢竟不是人人都有時間、金錢去健身房，但好的健身動作，卻能幫助很多的人，不應只有在健身房裡被運用。</strong></p>
        </div>
        
            	<form action="<%= request.getContextPath() %>/StudentsServlet" method="post" id="search_stu">
                <div class="row">
                	
                	<div class="col-lg-2">
                            <input type="search" placeholder="輸入學員姓名" name="search_Name" style="height:42px;">
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
                        <input type="button" class="btn btn-success" value="查看結果" style="width:163px;" onclick="tick.call(this);">
                        <input type="hidden" name="action" value="search_stu">
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
<c:forEach var="studentsVO" items="${list}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=0&mem_no=${studentsVO.stu_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${studentsVO.stu_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=0&mem_no=${studentsVO.stu_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${studentsVO.stu_name }
						<p style="color:#3c763d;font-size:14px;"><strong>學員</strong></p>
						</h3>
						</p>
						
					</center>
				</div>	
			</div>
</c:forEach>
		</div>
</c:if>

<c:if test="${not empty searchResult && empty errorMsgs}">
		<div class="row">
<c:forEach var="studentsVO" items="${searchResult}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=0&mem_no=${studentsVO.stu_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${studentsVO.stu_into }</span>
											<P></P>
										<span>深入了解我&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=0&mem_no=${studentsVO.stu_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
					<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${studentsVO.stu_name }
						<p style="color:#3c763d;font-size:14px;"><strong>學員</strong></p>
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
<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<%@include file="/front_end/include/onlyForFS.file" %>
</html>