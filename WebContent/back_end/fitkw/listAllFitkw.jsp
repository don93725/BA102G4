<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.fitkw.model.*"%>
<%-- 此頁練習採用 EL 的寫法取值 --%>

<%
    FitkwService fitkwSvc = new FitkwService();
    List<FitkwVO> list = fitkwSvc.getAll();
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
			<a href="selectFitkw_page.jsp">首頁</a>
			<h1>
				健身知識總覽 <small> <i class="icon-double-angle-right"></i>
						Knowledge List
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
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

<table class='table'>
	<thead>
	<tr>
		<th>更新日期</th>
		<th>知識主題</th>
		<th>知識標題</th>
		<th>知識內文</th>
		<th>知識圖片</th>
		<th>修改</th>
		<th>刪除</th>
	</tr>
	</thead>
	<tbody>
	<%@ include file="page1.file" %> 
	<c:forEach var="fitkwVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
		<tr align='center' valign='middle'>

			<td width="100">${fitkwVO.upd_date}</td>
			<td width="100">${fitkwVO.fik_type}</td>
			<td align='left' width="100">${fitkwVO.fik_title}</td>
			<td align='left' valign='top'>${fitkwVO.fik_ctx}</td>
			<td width="100"><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkwVO.fik_no}'/></td>
			
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			     <input class='btn btn-warning' type="submit" value="修改">
			     <input type="hidden" name="fik_no" value="${fitkwVO.fik_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			    <input class='btn btn-danger' type="submit" value="刪除">
			    <input type="hidden" name="fik_no" value="${fitkwVO.fik_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
<%@ include file="page2.file" %>
	</tbody>
</table>
	</div>
</div>



		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
<script type="text/javascript">
window.onload = init;
function init(){
	Preview.file_change();
}
function upload(){
	$('#file').trigger('click');
}

Preview = new function() {
	var fileInput = $('#file');
	this.file_change = function() {
		$('#file').on('change', function() {
			
			show(this);
		});
	}
	var show = function(input) {
		if (input.files && input.files[0]) {
			each_img(input.files);
		}
	}			
	var each_img = function(files) {
		$.each(files,function(index, file) {
				if (file.type.match('image')) {
					var reader = new FileReader();				
					reader.onload = function() {
						$('#pic').prop('src',reader.result);
						$('#pic').css('display',"block");
						$('#pic').css('height',"200px");
					}
					if (file) {
						reader.readAsDataURL(file);
					}
				}
			});
	}

}
</script>
</html>
