<%@ page language="java" import='com.forum.domain.*,java.util.*' contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-ch-en">
<head>
<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/forum/css/ArticleDisplay.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/forum/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">

<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>

<div class="container">
			<div class="row">				
				<div class="col-xs-12 col-sm-12">
				<div class="panel panel-default">
					  


		

<c:if test="${param.action=='goCreatePage'}">

<form action="${pageContext.request.contextPath}/forum/ArticlesActionCtrl" method='post' id='articleForm'>
 <table class="table" style="text-align:center;">
<thead>
	<caption><center><h1>發表文章</h1></center></caption>	
</thead>
<tbody>
<tr>
	<td style="vertical-align:middle;" align="center;">標題</td>
	<td style='width:15%;'>
		<select class='form-control' name='art_type_name'>		
		<c:forEach var='art_types' items="${art_types}" varStatus="loop2">
		<option value='${art_types.art_type_name}'>${art_types.art_type_name}</option>		
		</c:forEach>		
		</select></td>
	<td><input type='text' class='form-control' name='art_name' id='art_name' ><input type='hidden' name='forum_no' value='${param.forum_no }'><input type='hidden' name='action' id='artAction' value='create'></td>
</tr>
<tr>
<td style="vertical-align:middle;" align="center;">內文</td><td colspan='2'>
<div contentEditable="true" id='ctx' name='content' style='width:100%; height:500px;'></div>
<textarea id="my-textarea" name='art_ctx' style="display:none"></textarea>     
<input type="file" id='file' name='file' style="display:none"/> 
</td></tr>
<tr><td style='text-align: center' colspan='3'><input class='btn btn-lg btn-primary' type='button' name="getHtml" value='送出'/>
<button class='btn btn-lg btn-default' onclick='back.call(this,event,"${pageContext.request.contextPath}","${param.forum_no }");'>返回</button></td></tr>
</tbody>
</table>
</form>
</c:if>



<c:if test="${param.action=='goUpdatePage'}">
<form action="${pageContext.request.contextPath}/forum/ArticlesActionCtrl" method='post' id='articleForm'>
<table class="table">
<thead>
	<caption><h1>編輯文章</h1></caption>	
</thead>
<tbody>
<tr>
	<td style='width:15%;'>標題</td>
	<td style='width:15%;'>
		<select class='form-control' name='art_type_name'>
		<c:forEach begin="0" end="${fn:length(art_types)-1}" varStatus="loop">
		<c:set var="type" value="${art_types[loop.count-1].art_type_name}"/>
		<option value='<c:out value='${type }'/>' <c:if test='${type==articles.art_type}'>selected </c:if>><c:out value='${art_types[loop.count-1].art_type_name }'/></option>
		</c:forEach>		
		</select></td>
	<td><input type='text' id='art_name' class='form-control' name='art_name' value='${articles.art_name }' ><input type='hidden' name='forum_no' value='${param.forum_no }'><input type='hidden' name='art_no' value='${param.art_no }'>
	<input type='hidden' name='action' id='artAction' value='update'></td></tr>
<tr><td>內文</td>
<td colspan='2'>
<div contentEditable="true" id='ctx' name='content' style='width:100%; height:500px;'>${articles.art_ctx}</div>
<textarea id="my-textarea" name='art_ctx' style="display:none"></textarea> 
<input type="file" id='file' name='file' style="display:none"/> 
</td></tr>
<tr><td  style='text-align: center' colspan='3'><input type='button' class='btn btn-lg btn-primary' name="getHtml" value='更新'>
<button class='btn btn-lg btn-default' onclick='back2.call(this,event,"${pageContext.request.contextPath}","${param.forum_no }")'>返回</button></td></tr>
</tbody>
</table>
</form>
</c:if>


	</div>
					
	</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>	
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/front_end/forum/js/kindeditor/kindeditor-all2.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/front_end/forum/js/kindeditor/lang/zh-TW.js"></script>
<script type="Text/JavaScript" src="${pageContext.request.contextPath}/front_end/forum/js/ArticlesMaker.js"></script>
<script type="text/javascript">
function back(event,path,forum_no){
	event.preventDefault();
	location.href= path+"/forum/ForumShowCtrl?forum_no="+forum_no;
}
function back2(event,path,forum_no){
	event.preventDefault();
	location.href= path+"/forum/ForumShowCtrl?forum_no="+forum_no;
}
</script>
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>