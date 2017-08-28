<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.annew.model.*"%>
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
				<i class="icon-double-angle-right"></i><a href="selectAnnew_page.jsp"> 公告管理 </a>
				<i class="icon-double-angle-right"></i> 日期蒐尋
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
	<center><h2>蒐尋結果</h2></center>
		<table class='table' style="text-align:center;">
	<thead>
	<tr>
		
		<th class="center" style="vertical-align:middle;">刊登日期</th>
		<th class="center" style="vertical-align:middle;">更新日期</th>
		<th class="center" style="vertical-align:middle;">公告標題</th>
		<th class="center" style="vertical-align:middle;">公告內文</th>
		<th class="center" style="vertical-align:middle;">公告圖片</th>
<!-- 		<th>附件檔案</th> -->
		<th class="center" style="vertical-align:middle;"></th>
		<th class="center" style="vertical-align:middle;"></th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="annew" items='${annewVO}' >
	<!-- for( AnnewVO annew : annewVO{
	  
	} -->
	<tr align='center' valign='middle'>
		
		<td class="center" style="vertical-align:middle;">${annew.ann_date}</td>
		<td class="center" style="vertical-align:middle;">${annew.upd_date}</td>
		<td class="center" style="vertical-align:middle;">${annew.ann_title}</td>
		<td class="center" style="vertical-align:middle;">${annew.ann_ctx}</td>
		<td class="center" style="vertical-align:middle;"><img height=80 src='${pageContext.request.contextPath }/g1/PhotoOutputA?ann_no=${annew.ann_no}'/></td>
<%-- 		<td>${annew.att_no}</td> --%>
		
		<td class="center" style="vertical-align:middle;">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/annew/annew.do">
			     <button class="btn btn-primary btn-sm">修改</button>
			     <input type="hidden" name="ann_no" value="${annew.ann_no}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
		</td>
		<td class="center" style="vertical-align:middle;">
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back_end/annew/annew.do">
			    <button class="btn btn-danger btn-sm">刪除</button>
			    <input type="hidden" name="ann_no" value="${annew.ann_no}">
			    <input type="hidden" name="action"value="delete"></FORM>
		</td>
		
	</tr>
	</c:forEach>
	</tbody>
</table>
	</div>
</div>



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

<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>
