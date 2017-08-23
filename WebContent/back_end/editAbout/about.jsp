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
    <title>健貨 - GymHome</title>
	<%@include file="/front_end/include/basicScript.file" %>
</head>


<body>


    <!-- Page Content -->
    <div class="container">
         
        <!-- Intro Content -->
        <div class="row">
           
            <div class="col-md-12">
            	<div class='row'>
            	
            	<div class="col-md-12">
            	<h2>健貨 - 編輯關於我們</h2>
            	</div>
            	<FORM METHOD="post" ACTION="platinf.do" name="form1" enctype='multipart/form-data'>
            	 <div class="col-md-12">
                <img style='height:300px;' id='pic' class="img-responsive" src="${pageContext.request.contextPath }/util/OutputPic?pin_no=1" alt="">
                
                <input type="button" class='btn btn-primary' onclick='upload_Pic();' value='上傳圖片' />
                <input type="file" id='file' name="pin_photo" style='display:none;'/>
           		 </div>
            	<div class="col-md-12">
            	<h4>
            	<label for="basic-url">電話</label>
				<div class="input-group">
				  <input type="text" class="form-control" name="cp_no" aria-describedby="basic-addon3" value='${plain.cp_no}'>
				</div>
				</h4>
            	<h4>
            	<label for="basic-url">地址</label>
            	<div class="input-group">
				  <input type="text" class="form-control" name="com_address" aria-describedby="basic-addon3" value='${plain.com_address}'>
				</div>
            	</h4>
            	<h4>
            	<label for="basic-url">信箱</label>
            	<div class="input-group">
				  <input type="text" class="form-control" name="cs_email" aria-describedby="basic-addon3" value=' ${plain.cs_email}'>
				</div>            	
            	</h4>
            	<h4>
            	<label for="basic-url">介紹</label>
            	<div class="input-group">
				  <textarea class="form-control" name="pr_policy" aria-describedby="basic-addon3">${plain.pr_policy}</textarea>
				</div> 
            	</h4>
            	</div>
            	<input type="hidden" name="action" value="update">
				<input type="hidden" name="pin_no" value="1">
				<input type="submit" class='btn btn-primary' value="確認修改" >&nbsp<input type="reset" class='btn btn-danger' value="重填資料" ></FORM>
				</div>
            </div>
        </div>
        <!-- /.row -->
    </div>
    <!-- /.container -->



</body>

	<%@include file="/front_end/include/basicScript2.file" %>
<script type="text/javascript">
function upload_Pic(){
	$('#file').trigger('click');	
}
$(function(){
	Preview.file_change();
})
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
