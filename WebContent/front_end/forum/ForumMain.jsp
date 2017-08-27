<%@ page language="java"
	import="java.util.List,java.util.ArrayList,com.forum.domain.Forums"
	contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-ch-en">
<head>
<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <title>健貨 - GymHome</title>
<link rel="stylesheet"
			href="${pageContext.request.contextPath}/front_end/forum/css/colorbox.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/forum/css/forum.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/forum/css/ArticleDisplay.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">

<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>
<div class="container" style='min-height: 100%'>
			<div class="row">
				<div class="col-xs-12 col-sm-12">
				  <div class="breadcrumbs" id="breadcrumbs">
            
			            <div class="col-lg-12">
			                <h1>討論版
			                </h1>
			            </div>
			
			            <ul class="breadcrumb">
			                <li>
			                    <i class="icon-home home-icon"></i>
			                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a></li>
			                     </li>
			                <li class="active">討論版</li>
			            </ul><!-- .breadcrumb -->
			        </div><br></div>
				
				<div class="col-xs-12 col-sm-12">
	
	<c:if test="${not empty user }">
		<div class="col-xs-12 col-sm-12">
						<a class='inline' href="#inline_content"><button class='btn btn-lg btn-white'>申請板塊</button></a>
		</div>
	</c:if>
	
		<div class="col-xs-12 col-sm-12">
			<p></p>
			<div class="panel panel-default">
						<table class="table table-hover">
							<thead>
								<tr class='active well'><th colspan='5' style="text-align:center;color:;font-size:30px;background-color:#FFFFBB">官方版塊</th></tr>
								<tr>
									<th style="text-align:center;">編號</th>
										<th style="text-align:center;">板塊名稱</th>
										<th style="text-align:center;">文章數</th>
										<th>介紹</th>
										<th style="text-align:center;">點擊次數</th>
								</tr>
							</thead>
							<tbody>
									<c:forEach var='f' items='${offcialforums}' varStatus="loop">
							<tr>
								<td style="text-align:center;">${loop.count }</td>
								<td style="text-align:center;"><a
									href='${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${f.forum_no }'>${f.forum_name }</a></td>
								<td style="text-align:center;"><c:out value="${countOfficialArticles[f.forum_no ] }" default="0" /></td>
								<td>${f.forum_desc }</td>
								<td style="text-align:center;">${f.forum_views }</td>
							</tr>
						</c:forEach>
							</tbody>
						
						<thead>
							<tr class='active well'><th colspan='5' style="text-align:center;color:;font-size:30px;background-color:#EEFFBB">會員板塊</th></tr>
							<tr>
								<th style="text-align:center;">編號</th>
									<th style="text-align:center;">板塊名稱</th>
									<th style="text-align:center;">文章數</th>
									<th>介紹</th>
									<th style="text-align:center;">點擊次數</th>
							</tr>
						</thead>
						<tbody>
									<c:forEach var='f' items='${ forums}' varStatus="loop">
						<tr>
							<td style="text-align:center;">${loop.count }</td>
							<td style="text-align:center;"><a
								href='${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${f.forum_no }'>${f.forum_name }</a></td>
							<td style="text-align:center;"><c:out value="${countArticles[f.forum_no ] }" default="0" /></td>
							<td>${f.forum_desc }</td>
							<td style="text-align:center;">${f.forum_views }</td>
						</tr>
					</c:forEach>
						</tbody>
					</table>
					</div>
				</div>

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
						<caption><h1>版塊申請</h1></caption>						
						<tbody>
							<tr>
							<td><label>版塊名字</label></td>
							<td><input type="text" class='form-control' id='forum_name' name='forum_name'></td>
						</tr>
						<tr>
							<td valign="top"><label>文章類型</label></td>
							<td><c:forEach begin="0" end="4" varStatus="loop">									
									<input type="text" name="art_type_name" style="display: none;width:60%;">
									<button type="button" class='xbtn btn btn-danger'
										style="display: none;"><span class='glyphicon glyphicon-remove'></span></button>
									
								</c:forEach> <button type="button" class='btn btn-info' id='btn'><span class='glyphicon glyphicon-plus'></span></button></td>

						</tr>

						<tr>
							<td><label>版塊敘述</label></td>
							<td><textarea style='resize: none;' class='form-control' rows="5" cols="20" id='forum_desc'
									name='forum_desc'></textarea></td>
						</tr>
						<tr>
							<td><label>申請原因</label></td>
							<td><textarea style='resize: none;' class='form-control' rows="5" cols="20" id='forum_note'
									name='forum_note'></textarea></td>
						</tr>
						<tr align='center'>
							<td colspan="2"><input id='test' class='btn btn-lg btn-primary' type="button" name=""
								value="送出"
								onclick="create('${pageContext.request.contextPath}','${user.mem_no }');">
								<input class='btn btn-default btn-lg' type="reset" name="" value="重填"></td>
						</tr>
						</tbody>
					</table>
				
				</form>

			</div>
		</div>
		
		
		
	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
<script src="https://code.jquery.com/jquery.js"></script>
<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>	
<script	src="${pageContext.request.contextPath}/front_end/forum/js/jquery.colorbox2.js"></script>		
<script type="text/javascript" src='${pageContext.request.contextPath}/front_end/forum/js/ForumApply.js'></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
</html>