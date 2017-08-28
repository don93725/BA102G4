<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.annew.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    AnnewService annewSvc = new AnnewService();
    List<AnnewVO> list = annewSvc.getAll();
    pageContext.setAttribute("list",list);
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<title>健貨後台管理系統</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>

	<div class="page-content">
		<div class="page-header">
			<h1>
				頁面資訊維護 <small>
				<i class="icon-double-angle-right"></i><a href="selectAnnew_page.jsp"> 公告管理 </a>
				<i class="icon-double-angle-right"></i> 公告總覽
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
	<center><h2>公告總覽</h2></center>
		<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

	<table class='table' style="text-align:center;">
		<thead>
		<tr align='center' valign='middle'>
			<th class="center" style="vertical-align:middle;">刊登日期</th>
			<th class="center" style="vertical-align:middle;">更新日期</th>
			<th class="center" style="vertical-align:middle;">公告標題</th>
			<th class="center" style="vertical-align:middle;">公告內文</th>
			<th class="center" style="vertical-align:middle;">公告圖片</th>
	<!-- 		<th>附件檔案</th> -->
			<th class="center" style="vertical-align:middle;">修改</th>
			<th class="center" style="vertical-align:middle;">刪除</th>
		</tr>
		</thead>
		<tbody>
		<%@ include file="page1.file" %> 
		<c:forEach var="annewVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
			<tr align='center' valign='middle'>
				
				<td style="vertical-align:middle;" align="center" >${annewVO.ann_date}</td>
				<td style="vertical-align:middle;" align="center" >${annewVO.upd_date}</td>
				<td style="vertical-align:middle;" align="center" >${annewVO.ann_title}</td>
				<td style="vertical-align:middle;" align="center" >${annewVO.ann_ctx}</td>
				<td><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annewVO.ann_no}'/></td>
	<%-- 			<td>${annewVO.att_no}</td> --%>
				
				<td style="vertical-align:middle;" align="center" >
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/annew/annew.do">
				     <input class='btn btn-warning btn-sm' type="submit" value="修改">
				     <input type="hidden" name="ann_no" value="${annewVO.ann_no}">
				     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
				</td>
				<td style="vertical-align:middle;" align="center" >
				  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/annew/annew.do">
				    <input class='btn btn-danger btn-sm' type="submit" value="刪除">
				    <input type="hidden" name="ann_no" value="${annewVO.ann_no}">
				    <input type="hidden" name="action"value="delete"></FORM>
				</td>
			</tr>
		</c:forEach>
	<%@ include file="page2.file" %>
		</tbody>
	</table>
	</div>
</div>

</body>
<script type="text/javascript">
window.onload = init;
function init(){
	Preview.file_change();
}
function upload(){
	$('#file').trigger('click');
}


<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>





