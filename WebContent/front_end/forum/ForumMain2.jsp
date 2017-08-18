<%@ page language="java"
	import="java.util.List,java.util.ArrayList,com.forum.domain.Forums"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/forum/css/forum.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/front_end/forum/css/ArticleDisplay.css">

<script type="Text/JavaScript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script>
<script type="text/javascript"
	src='${pageContext.request.contextPath}/front_end/forum/js/ForumApply.js'></script>
</head>
<body>

	<c:if test="${not empty user }">
		<div>
			<a class='inline' href="#inline_content">申請板塊</a>
		</div>
	</c:if>
	<div>
		<div class='rank'>
			<table border='1'>
				<tr>
					<td>名次</td>
					<td>板塊名稱</td>
					<td>月點擊/總點擊</td>
				</tr>
				<c:forEach var='rankList' items="${rankList}" varStatus="loop">
					<tr>
						<td><c:out value="${loop.count}" /></td>
						<td><a
							href='${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=<c:out value='${rankList.forum_no}'/>'><c:out
									value='${rankList.forum_name }' /></a></td>
						<td align="center"><c:out value="${rankList.forum_views }" />/<c:out
								value="${rankList.forum_mviews }" /></td>
					</tr>
				</c:forEach>
			</table>
		</div>
		<div class='rank'>
			<table border='1'>
				<tr>
					<td>名次</td>
					<td>文章名稱</td>
					<td>月點擊/總點擊</td>
				</tr>

				<c:forEach var="rankArticles" items="${articlesRankList}"
					varStatus="loop2">
					<tr>
						<td><c:out value="${loop2.count}" /></td>
						<td><a
							href='${pageContext.request.contextPath}/forum/ArticleShowCtrl?forum_no=<c:out value="${rankArticles.forum_no}"/>&art_no=<c:out value="${rankArticles.art_no}"/>'><c:out
									value="${rankArticles.art_name }" /></a></td>
						<td><c:out value="${rankArticles.art_views }" />/<c:out
								value="${rankArticles.art_mviews }" /></td>
					</tr>
				</c:forEach>
				<c:if test="${empty articlesRankList}">
					<tr rowspan='5'>
						<td colspan='3'>目前尚無排行</td>
					</tr>
				</c:if>
			</table>
		</div>
	</div>
	<div>
		<table border='1'>
			<tr>
				<td>名次</td>
				<td>最新文章</td>
				<td>發表時間</td>
			</tr>

			<c:forEach var="newestRankList" items="${newestRankList}"
				varStatus="loop3">
				<tr>
					<td><c:out value="${loop3.count}" /></td>
					<td><a
						href='${pageContext.request.contextPath}/forum/ArticleShowCtrl?forum_no=<c:out value="${newestRankList.forum_no}"/>&art_no=<c:out value="${newestRankList.art_no}"/>'><c:out
								value="${newestRankList.art_name }" /></a></td>
					<td><fmt:setLocale value="en_US" /> <fmt:formatDate
							value="${newestRankList.art_add_date}" pattern="yyyy-MM-d HH:mm" /></td>
				</tr>
			</c:forEach>
			<c:if test="${empty newestRankList}">
				<tr rowspan='5'>
					<td colspan='3'>目前尚最新文章</td>
				</tr>
			</c:if>
		</table>
	</div>
	</div>
	<div class='rank'>
		<table border=1>
			<tr>
				<th>編號</th>
				<th>論壇</th>
				<th>文章數</th>
				<th>介紹</th>
				<th>點擊次數</th>
			</tr>


			<c:forEach var='f' items='${ forums}'>
				<tr>
					<td>${f.forum_no }</td>
					<td><a
						href='${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${f.forum_no }'>${f.forum_name }</a></td>
					<td><c:out value="${countArticles[f.forum_no ] }" default="0" /></td>
					<td>${f.forum_desc }</td>
					<td>${f.forum_views }</td>
				</tr>
			</c:forEach>


		</table>
		</div>
		<div id='inline' style="display: none">
			<div id='inline_content'
				style='padding: 10px; background: #fff; font-size: 30px;'>
				<h1>版塊申請</h1>
				<form
					action="#"
					method="post">
					<table>
						<tr>
							<td><label>版塊名字</label></td>
							<td><input type="text" id='forum_name' name='forum_name'></td>
						</tr>
						<tr>
							<td valign="top"><label>文章類型</label></td>
							<td><c:forEach begin="0" end="4" varStatus="loop">
									<input type="text" name='art_type_name' style="display: none;">
									<input type="button" class='xbtn' value='X'
										style="display: none;">
								</c:forEach> <input type="button" id='btn' value='+'></td>

						</tr>

						<tr>
							<td><label>版塊敘述</label></td>
							<td><textarea rows="5" cols="20" id='forum_desc'
									name='forum_desc'></textarea></td>
						</tr>
						<tr>
							<td><label>申請原因</label></td>
							<td><textarea rows="5" cols="20" id='forum_note'
									name='forum_note'></textarea></td>
						</tr>
						<tr align='center'>
							<td colspan="2"><input id='test' type="button" name=""
								value="送出"
								onclick="create('${pageContext.request.contextPath}','${user.mem_no }');">
								<input type="reset" name="" value="重填"></td>
						</tr>
					</table>
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