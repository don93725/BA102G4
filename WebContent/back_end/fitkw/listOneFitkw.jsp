<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.fitkw.model.*"%>
<%
FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO"); //FitkwServlet.java(Controller), 存入req的fitkwVO物件
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
			<a href="selectAnnew_page.jsp">首頁</a>
			<h1>
				健身知識資料 <small> <i class="icon-double-angle-right"></i>
						Knowledge Data
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
		<table class='table'>
	<thead>
	<tr>
		<th>知識編號</th>
		<th>更新日期</th>
		<th>知識主題</th>
		<th>知識標題</th>
		<th>知識內文</th>
		<th>知識圖片</th>
	</tr>
	</thead>
	<tbody>
	<tr align='center' valign='middle'>
		<td><%=fitkwVO.getFik_no()%></td>
		<td><%=fitkwVO.getUpd_date()%></td>
		<td><%=fitkwVO.getFik_type()%></td>
		<td><%=fitkwVO.getFik_title()%></td>
		<td><%=fitkwVO.getFik_ctx()%></td>
		<td><%=fitkwVO.getFik_photo()%></td>
		
	</tr>
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