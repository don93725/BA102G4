<%@ page language="java"
	import="java.util.*,com.forum.domain.*,java.text.SimpleDateFormat"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<script type="Text/JavaScript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/forum/css/ArticleDisplay.css">
<script type="text/javascript"
	src='${pageContext.request.contextPath}/front_end/forum/js/ForumApply2.js'></script>
<title>Insert title here</title>
</head>

<body>
	>
	<a href="${pageContext.request.contextPath}/forum/ForumCtrl">討論大廳</a>>${forum_name }
	<br>
	<c:if test="${! empty user }">
		<a
			href='${pageContext.request.contextPath}/forum/ArticlesActionCtrl?action=goCreatePage&forum_no=${param.forum_no }'>新增文章</a>
	</c:if>
	<c:if test="${mem_no==user.mem_no&&!empty user.mem_no }">
		<a id='trig' href="#" onclick="editForum('${pageContext.request.contextPath}','${param.forum_no }')">編輯論壇</a>
		<a class='inline' href="#inline_content" style='display:none;'></a>
		<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no}">管理檢舉</a>
	</c:if>
	<div>
		<table border='1'>
			<tr>
				<td><a
					href="${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${param.forum_no }">全部</a></td>

				<c:forEach var="art_types" items="${art_types}" varStatus="loop">
					<td><a
						href="${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${param.forum_no }&art_type_no=${art_types.art_type_no}"><c:out
								value="${art_types.art_type_name }" /></a></td>
				</c:forEach>
			</tr>
		</table>
	</div>
	<div>
		<table border=1>
			<tr>
				<th>文章類型</th>
				<th>文章標題</th>
				<th>發文者</th>
				<th>發文時間</th>
				<th>瀏覽次數</th>
			</tr>
			<c:forEach var="art" items="${articles }">
				<tr>
					<td>${art.art_type}</td>
					<td><a
						href="${pageContext.request.contextPath}/forum/ArticleShowCtrl?forum_no=${param.forum_no }&art_no=${art.art_no}">${art.art_name}</a></td>
					<td><a
						href='${pageContext.request.contextPath}/forum/PersonalPageCtrl?mem_no=${art.mem_no.mem_no}'>${art.mem_no.mem_nickname}</a></td>
					<td><fmt:setLocale value="en_US" />
						<fmt:formatDate value="${art.art_add_date}"
							pattern="yyyy-MM-d HH:mm" /></td>
					<td align="right">${art.art_views}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty articles}">
				<tr>
					<td colspan='5'>目前尚無文章</td>
				</tr>
			</c:if>

		</table>


		<div id='inline' style="display: none">
			<div id='inline_content'
				style='padding: 10px; background: #fff; font-size: 30px;'>
				<h1>版塊申請</h1>
				<form
					action="${pageContext.request.contextPath}/forum/ForumActionCtrl?action=update&forum_no=${param.forum_no }"
					method="post">
					<table>
						<tr>
							<td><label>版塊名字</label></td>
							<td><input type="text" id='forum_name' name='forum_name' disabled >
							</td>
						</tr>
						<tr>
							<td valign="top"><label>文章類型</label></td>
							
							<td><c:forEach begin="0" end="4" varStatus="loop">
									<input type="text" name='art_type_name' style="display: none;" 	>
									<input type="button" class='xbtn' value='X' style="display: none;">
								</c:forEach> <input type="button" id='btn' value='+'></td>

						</tr>

						<tr>
							<td><label>版塊敘述</label></td>
							<td><textarea rows="5" cols="20" id='forum_desc' name='forum_desc'></textarea></td>
						</tr>
						<tr align='center'>
							<td colspan="2"><input type="submit"  value="送出"><input
								type="reset" name="" value="重填"></td>
						</tr>
					</table>
				</form>
				<a
					href='${pageContext.request.contextPath}/forum/${(empty forums)?"ForumCtrl":"ForumShowCtrl?forum_no="}${(empty forums)?"":forums.forum_no}'>返回板塊區</a>
				</form>

			</div>
		</div>
		<div id='tips'></div>
		<script
			src="${pageContext.request.contextPath}/front_end/forum/js/jquery.colorbox2.js"></script>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath}/front_end/forum/css/colorbox.css" />
</body>
</html>