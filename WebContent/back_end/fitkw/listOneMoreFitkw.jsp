<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.fitkw.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

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
				<i class="icon-double-angle-right"></i> <a href="selectFitkw_page.jsp">健身知識 </a>
				<i class="icon-double-angle-right"></i> 搜尋結果
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
		<center><h2>搜尋結果</h2></center>
		<table class='table'>
	<thead>
	<tr>

		<th class="center" style="vertical-align:middle;">更新日期</th>
		<th class="center" style="vertical-align:middle;">知識主題</th>
		<th class="center" style="vertical-align:middle;">知識標題</th>
		<th class="center" style="vertical-align:middle;">知識內文</th>
		<th class="center" style="vertical-align:middle;">知識圖片</th>
		<th class="center" style="vertical-align:middle;">修改</th>
		<th class="center" style="vertical-align:middle;">刪除</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="fitkw" items='${fitkwVO}' >
	<tr align='center' valign='middle'>

		<td class="center" style="vertical-align:middle;" width="100">${fitkw.upd_date}</td>
		<td class="center" style="vertical-align:middle;" width="100">${fitkw.fik_type}</td>
		<td class="center" style="vertical-align:middle;" width="100">${fitkw.fik_title}</td>
		<td align="left">${fitkw.fik_ctx}</td>
		<td class="center" style="vertical-align:middle;"><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutput?fik_no=${fitkw.fik_no}'/></td>
		
		<td class="center" style="vertical-align:middle;">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			     <input class='btn btn-warning btn-sm' type="submit" value="修改">
			     <input type="hidden" name="fik_no" value="${fitkw.fik_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td class="center" style="vertical-align:middle;">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/fitkw/fitkw.do">
			    <input class='btn btn-danger btn-sm' type="submit" value="刪除">
			    <input type="hidden" name="ann_no" value="${fitkw.fik_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
		</td>
		
	</tr>
	</c:forEach>
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
