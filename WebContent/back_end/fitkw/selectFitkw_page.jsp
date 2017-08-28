<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
				頁面資訊維護  <small><i class="icon-double-angle-right"></i> 健身知識</small>
			</h1>
		</div>
		
	<!-- /.page-header -->
	<div class='container'>
	<div class='row'>
	<center><h2>健身知識</h2></center>
	<p></p>
	
	<div class='col-sm-12'>
		<a href='listAllFitkw.jsp'><button class='btn btn-warning btn-sm'><i class="icon-eye-open bigger-120"></i> 知識總覽</button></a>
  		<a href='addFitkw.jsp'><button class='btn btn-success btn-sm'><i class="icon-plus bigger-120"></i> 新增知識</button></a>
  	</div>

  	
  	
  			<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">

	<div class='col-sm-12'>
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
	</div>

</c:if>


	<div class='col-sm-12'>
    <FORM METHOD="post" ACTION="fitkw.do" >
        <label for="exampleInputEmail1"><img src="images/flower.gif"><b>輸入標題<img src="images/flower.gif">:</b></label>
    		<div class="input-group">
    			<input class='form-control' type="text" name="fik_title">
		  		<div class="input-group-btn">
		   		 <input class='btn btn-default form-control btn-primary' type="submit" value="送出">
        			<input type="hidden" name="action" value="getPart_By_Title">
		  		</div>
			</div>
    </FORM>
    </div>


 
    <div class='col-sm-12'>
    <FORM METHOD="post" ACTION="fitkw.do" >
       <label for="exampleInputEmail1"><img src="images/flower.gif"><b>輸入日期<img src="images/flower.gif">:</b></label>
       <div class="input-group">
       <input class='form-control' type="date" name="upd_date">
		  <div class="input-group-btn">
		    <input class='btn btn-default form-control btn-primary' type="submit" value="送出">
        	<input type="hidden" name="action" value="getPart_By_Date">
		  </div>
		</div>
    </FORM>
    </div>



  <jsp:useBean id="fitkwSvc" scope="page" class="com.fitkw.model.FitkwService" />
  	<div class='col-sm-12'>
     <FORM METHOD="post" ACTION="fitkw.do" >
       <label for="exampleInputEmail1"><img src="images/flower.gif"><b>選擇主題<img src="images/flower.gif">:</b></label>
        <div class="input-group">
        <select class='form-control' size="1" name="fik_type">
          <option value="基礎知識">基礎知識</option>
          <option value="增肌知識">增肌知識</option>
          <option value="跑步知識">跑步知識</option>
          <option value="有氧知識">有氧知識</option>
          <option value="其他知識">其他知識</option>
       </select>
		  <div class="input-group-btn">
		    <input class='btn btn-default form-control btn-primary' type="submit" value="送出">
      		 <input type="hidden" name="action" value="getPart_By_Type">
		  </div>
		</div>
    </FORM>
    </div>
 

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
