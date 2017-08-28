<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*" %>
<%@ page import="com.manager.model.*" %>
<%@ page import="com.platinf.model.*" %>
<% ManagerService managerSV = new ManagerService();
   List<ManagerVO> mgrList = managerSV.getAll(); 
   pageContext.setAttribute("mgrList",mgrList);
   PlatinfService platinfSV = new PlatinfService();
   PlatinfVO plain = platinfSV.getOnePlatinf("1");
   pageContext.setAttribute("plain",plain);
   %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<title>���f��x�޲z�t��</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>
	<div class="page-content">
		<div class="page-header">
			<h1>
				������T���@ <small> <i class="icon-double-angle-right"></i>
					����ڭ�
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		</div>
		<div class='container'>
		<div class="row">
           
            <div class="col-md-12">
            	<div class='row'>
            	
            	<div class="col-md-12">
            	</div>
            	<FORM METHOD="post" ACTION="platinf.do" name="form1" enctype='multipart/form-data'>
            	<table class='table' align="center">
            	<tr><td style="vertical-align:middle;" align="center" >
            		�Ϥ�
            	</td><td style="vertical-align:middle;" align="center" >
                <img style='height:300px;' id='pic' class="img-responsive" src="${pageContext.request.contextPath }/util/OutputPic?pin_no=1" alt="">
                
                <input type="button" class='btn btn-dafault' onclick='upload_Pic();' value='�W�ǹϤ�' /></td>
                <input type="file" id='file' name="pin_photo" style='display:none;'/>
           		 </tr>
            	<div class="col-md-12">
            	<tr><td style="vertical-align:middle;" align="center" >
            	<label for="basic-url">�q��</label></td><td>
				  <input type="text" class="form-control" name="cp_no" aria-describedby="basic-addon3" value='${plain.cp_no}'></td>
				</tr>
            	<tr><td style="vertical-align:middle;" align="center" >
            	<label for="basic-url">�a�}</label></td><td>
				  <input type="text" class="form-control" name="com_address" aria-describedby="basic-addon3" value='${plain.com_address}'></td>
            	</tr>
            	<tr><td style="vertical-align:middle;" align="center" >
            	<label for="basic-url">�H�c</label></td><td>
				  <input type="text" class="form-control" name="cs_email" aria-describedby="basic-addon3" value=' ${plain.cs_email}'></td>
            	</tr>
            	<tr><td style="vertical-align:middle;" align="center" >
            	<label for="basic-url">����</label></td><td>
				  <textarea style='resize:none;' class="form-control" name="pr_policy" aria-describedby="basic-addon3" rows="7">${plain.pr_policy}</textarea></td>
            	</tr>
            	<tr align='center'><td colspan='2' align="center" >
            	<input type="hidden" name="action" value="update">
				<input type="hidden" name="pin_no" value="1">
				<input type="submit" class='btn btn-primary' value="�T�{�ק�" >&nbsp&nbsp&nbsp<input type="reset" class='btn btn-danger' value="������" ></td></tr>
				</table>
				</FORM>
				</div>
            </div>
        </div>
        <!-- /.row -->
        </div>


		<%@include file="/back_end/include/ace_setting_footer.file"%>


</body>

	<%@include file="/front_end/include/basicScript2.file" %>
<script type="text/javascript">
window.onload= init;
function upload_Pic(){
	$('#file').trigger('click');	
}
function init(){
	Preview.file_change();
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
						$('#pic').css('height','300px');
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

