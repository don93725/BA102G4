<%@ page language="java"
	import="java.util.*,com.forum.domain.*,java.text.SimpleDateFormat"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-ch-en">
<head>
<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/forum/css/ArticleDisplay.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>
	<div class="container" style='min-height: 100%;'>
			<div class="row">
					<div class="breadcrumbs" id="breadcrumbs">
			            <div class="col-lg-12">
			                <h1>${forum_name }
			                </h1>
			            </div>
	
	           			<ul class="breadcrumb">
			              	<li>
				            	<i class="icon-home home-icon"></i>
				            	<a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a>
				            </li>
				            <li class="active"><a  href="${pageContext.request.contextPath}/forum/ForumCtrl">討論大廳</a></li>
				            <li class="active">${forum_name }</li>
			          	</ul><!-- .breadcrumb -->
	        		</div>
					<br>
				<div class="col-xs-12 col-sm-12">					
					<c:if test="${! empty user }">
						<a
							href='${pageContext.request.contextPath}/forum/ArticlesActionCtrl?action=goCreatePage&forum_no=${param.forum_no }'><button class='btn btn-info' style="font-size:16px;">新增文章</button></a>
					</c:if>
					<c:if test="${(mem_no==user.mem_no&&!empty user.mem_no)||(!empty user &&mem_no=='0'&&user.mem_rank==3) }">
						<a id='trig' href="#" onclick="editForum('${pageContext.request.contextPath}','${param.forum_no }')"><button class='btn btn-primary'>編輯板塊</button></a>
						<a class='inline' href="#inline_content" style='display:none;'></a>
						<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no}"><button class='btn btn-primary'>管理檢舉</button></a>
					</c:if>
				</div>
				<div class="col-xs-12 col-sm-12" style='margin-bottom: 10px; margin-top: 10px;  '>
					<a
					href="${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${param.forum_no }"><span class='label label-default label-lg' >全部</span></a>
					
				<c:forEach var="art_types" items="${art_types}" varStatus="loop">
					<a href="${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${param.forum_no }&art_type_no=${art_types.art_type_no}">
					<span class='label label-default label-lg' ><c:out value="${art_types.art_type_name }" /></span></a>
				</c:forEach>
				</div>				
				<div class="col-xs-12 col-sm-12">
				<div class="panel panel-default">
					  <!-- Default panel contents -->
					  
					  <!-- Table -->
					  <table class="table table-hover" style="text-align:center;">
						
						<thead>
							<tr class="active">
								<th class="center" style="vertical-align:middle;" align="center;">文章類型</th>
								<th class="center" style="vertical-align:middle;" align="center;">文章標題</th>
								<th class="center" style="vertical-align:middle;" align="center;">發文者</th>
								<th class="center" style="vertical-align:middle;" align="center;">發文時間</th>
								<th class="center" style="vertical-align:middle;" align="center;">瀏覽次數</th>
								<th class="center" style="vertical-align:middle;" align="center;">最後留言</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="art" items="${articles }">
								<tr valign="middle">
									<td style="vertical-align:middle;" align="center;">${art.art_type}</td>
									<td style="vertical-align:middle;" align="center;"><a
										href="${pageContext.request.contextPath}/forum/ArticleShowCtrl?forum_no=${param.forum_no }&art_no=${art.art_no}">${art.art_name}</a></td>
									<td style="vertical-align:middle;" align="center;"><a
										href='${pageContext.request.contextPath}/forum/PersonalPageCtrl?mem_no=${art.mem_no.mem_no}&mem_rank=${art.mem_no.mem_rank}&action=lookPersonal'>${art.mem_no.mem_nickname}</a></td>
									<td style="vertical-align:middle;" align="center;"><fmt:setLocale value="en_US" />
										<fmt:formatDate value="${art.art_add_date}"
											pattern="yyyy-MM-d HH:mm" /></td>
									<td style="vertical-align:middle;" align="center;">${art.art_views}</td>
									<td style="vertical-align:middle;" align="center;"><c:if test="${empty art.newestCmmt }">尚無人留言</c:if><a
										href='${pageContext.request.contextPath}/forum/PersonalPageCtrl?mem_no=${art.newestCmmt.mem_no.mem_no}'>${art.newestCmmt.mem_no.mem_nickname }</a><br>
									<span style='color: gray;'><fmt:formatDate value="${art.newestCmmt.art_cmt_time }"
											pattern="yyyy-MM-d HH:mm" /></span>											
											</td>
								</tr>
							</c:forEach>
							<c:if test="${empty articles}">
								<tr class='text-center'>
									<td colspan='6'>目前尚無文章</td>
								</tr>
							</c:if>
						</tbody>
					</table>
					</div>
					<center>
						<jsp:include page="/front_end/forum/ChangePage.jsp"/>
					</center>
				</div>
			</div>
		</div>
	






		<div id='inline' style="display: none">
			<div id='inline_content'
				style='padding: 10px; background: #fff; font-size: 30px;'>				
				<form
					action="#"
					method="post">
					<table class="table table-hover">
						
						<thead>
							<caption><h1>版塊編輯</h1></caption>							
						</thead>
						<tbody>
							<tr>
								<td><label>版塊名字</label></td>
								<td id='forum_name'></td>
							</tr>
							<tr>
							<td valign="top"><label>文章類型</label></td>
							
							<td>
								<c:forEach begin="0" end="4" varStatus="loop">
									<input type="text" name='art_type_name' style="display: none;" 	>
									<button type="button" class='xbtn btn-lg btn btn-danger' style="display: none;"><span class='glyphicon glyphicon-remove'></span></button>
								</c:forEach> <button type="button" class='btn-lg btn btn-info' id='btn'><span class='glyphicon glyphicon-plus'></span></button></td>
							</tr>
	
							<tr>
								<td><label>版塊敘述</label></td>
								<td><textarea rows="5" cols="20" class='form-control' style='resize: none;' id='forum_desc' name='forum_desc'></textarea></td>
							</tr>
							<tr align='center'>
								<td colspan="2"><input class='btn btn-lg btn-primary' type="button" onclick='update("${pageContext.request.contextPath}","${param.forum_no }");' value="送出">
								<input class='btn btn-default btn-lg' type="reset" name="" value="重填"></td>
							</tr>
						</tbody>			
					</table>
				</form>
				

			</div>
		</div>
		
		
		
		<script src="https://code.jquery.com/jquery.js"></script>		
		<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>	
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script
			src="${pageContext.request.contextPath}/front_end/forum/js/jquery.colorbox2.js"></script>
		
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
		<script type="text/javascript" src='${pageContext.request.contextPath}/front_end/forum/js/ForumApply2.js'></script>
</html>