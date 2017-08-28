<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.members.model.*"%>		
									
<!DOCTYPE html>
<html lang="zh-cn-en">
<head>
	<%@include file="/back_end/include/fileInput/fileInput_head.file" %>
	<%@include file="/back_end/include/basic_ace_script.file" %>
<title>健貨後台管理系統</title>

<!-- the fileinput plugin initialization -->
<style>
.kv-avatar .krajee-default.file-preview-frame,.kv-avatar .krajee-default.file-preview-frame:hover {
    margin: 0;
    padding: 0;
    border: none;
    box-shadow: none;
    text-align: center;
}
.kv-avatar .file-input {
    display: table-cell;
    max-width: 220px;
}
.kv-reqd {
    color: red;
    font-family: monospace;
    font-weight: normal;
}
</style>

<title>新增管理者</title>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>

	<div class="page-content">
		<div class="page-header">
			<h1>
				員工帳號管理 <small> <i class="icon-double-angle-right"></i>
					新增員工
				</small>
			</h1>
		</div>
		


	<div>
		<br>
	</div>
<c:if test="${not empty errorMsgs}">
	<font color='red'>錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message.value}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>


<div class="container">
<div class="row">

<div  class="col-xs-12 col-sm-6 col-sm-offset-2">
<form method="post" action="<%=request.getContextPath()%>/mgr/MgrCtrl" name="form1" enctype="multipart/form-data" class="form-horizontal">
	
	<div class="form-group">
		<label for="mgr_pic" class="col-xs-12 col-sm-3 control-label">
			照片
		</label>
					
	<div id="kv-avatar-errors-2" class="center-block" style="width:800px;display:none"></div>
			
		<div class="col-xs-12 col-sm-9">
            <div class="kv-avatar center-block text-center" style="width:200px">
                <input id="avatar-2" name="mgr_pic" type="file" class="file-loading" required>
            </div>
       	</div>
	</div>
		
	<div class="form-group">
		<label for="mgr_name" class="col-xs-12 col-sm-3 control-label">
			姓名
		</label>
		<div class="col-xs-12 col-sm-9">
			<input type="TEXT" name="mgr_name" id="mgr_name" placeholder="姓名" class="form-control"
				value="${param.mgr_name}"  />	<div style="color:red">${errorMsgs.mgr_name}</div>
											
		</div>
	</div>

	<div class="form-group">	
		<label for="mem_nickname" class="col-xs-12 col-sm-3 control-label">
			暱稱
		</label>
		<div class="col-xs-12 col-sm-9">
			<input type="TEXT" name="mem_nickname" id="mem_nickname" placeholder="暱稱" class="form-control"
				value="${param.mem_ncikname}" /><div style="color:red">${errorMsgs.mem_nickname}</div>
		</div>
	</div>

	<div class="form-group">
		<label for="mgr_id" class="col-xs-12 col-sm-3 control-label">
			帳號
		</label>
		<div class="col-xs-12 col-sm-9">		
			<input type="TEXT" name="mgr_id" id="mgr_id" placeholder="帳號" class="form-control"
				 value="${param.mgr_id}" /><div style="color:red">${errorMsgs.mgr_id}</div>
		</div>
	</div>
				
	<div class="form-group">
		<label for="mgr_email" class="col-xs-12 col-sm-3 control-label">
			信箱
		</label>
		<div class="col-xs-12 col-sm-9">
			<input type="email" name="mgr_email" id="mgr_email" placeholder="信箱" class="form-control"
			 	value="${param.mgr_email}"/><div style="color:red">${errorMsgs.mgr_email}</div>
		</div>
	</div>
				
	<div class="form-group">
		<label for="mgr_job" class="col-xs-12 col-sm-3 control-label">
			職位
		</label>
		<div class="col-xs-12 col-sm-9">
			<select id='a' size="1" name="mgr_job" class="form-control">
				<option  value="0" selected >管理者</option>
				<option id='b' value="1" >系統管理者</option>
			</select>
		</div>
	</div>
					
<jsp:useBean id="funSvc" scope="page" class="com.function.model.FunctionService" />
	<div class="form-group">
		<label for="f_no" class="col-xs-12 col-sm-3 control-label">
			權限
		</label>
		<div class="col-xs-12 col-sm-9" data-role="fieldcontain">	
<c:forEach var="functionVO" items="${funSvc.all}">
			<fieldset data-role="controlgroup" data-type="horizontal">
			<div  class="checkbox" style="padding-left:0px;">
				<label>
					<input type="checkbox" class='f_no' id="f_no" name="f_no" value="${functionVO.f_no}"> 
					${functionVO.fname} 
				</label>
			</div>	
			</fieldset>
		
</c:forEach>
		<div  style="color:red">${errorMsgs.f_no}</div>
		</div>
	</div>	
	<br>
	<div class="form-group">
		<label for="mgr_int" class="col-xs-12 col-sm-3 control-label">
			自我介紹
		</label>
		<div class="col-xs-12 col-sm-9">
			<textarea id='mgr_int' name="mgr_int" class="form-control" style='resize:none;' rows=4></textarea>
		</div>
	</div>
	<br>
	<div class="form-group">
		<div class="col-xs-12 col-sm-3"></div>
		
		<div class="col-xs-12 col-sm-9">
			<input type="hidden" name="action" value="insert">
			<%-- <input type="hidden" name="mgr_no" value="${managerVO.mgr_no}"> --%>
			<input type="submit" value="送出新增" class="btn btn-success btn-lg btn-block" >
		</div>
	</div>

</div>
</div>
</div>
</form>




<script type="text/javascript">
$(function(){	
	$('.f_no:eq(0)').attr('checked','');
	$('.f_no:eq(1)').attr('checked','');
	$('.f_no:eq(2)').attr('checked','');
	$('.f_no:eq(4)').attr('checked','');
	$('.f_no:eq(5)').attr('checked','');
	$('.f_no:eq(6)').attr('checked','');
	$('.f_no:eq(7)').attr('checked','');
	$('#a').change(function(){
		if($(this).val()==0){
			$('.f_no:eq(3)').removeAttr('checked');			
		}else{
			$('.f_no:eq(3)').attr('checked','');
		}

	});
})

</script>

<script>

	
</script>
 
<!-- the fileinput plugin initialization -->
<script>
var btnCust = '<button type="button" class="btn btn-default" title="Add picture tags" ' + 
    'onclick="alert(\'Call your custom code here.\')">' +
    '<i class="glyphicon glyphicon-tag"></i>' +
    '</button>'; 
$("#avatar-2").fileinput({
	overwriteInitial: true,
	maxFileSize: 1500,
	showClose: false,
	showCaption: false,
	showBrowse: false,
	browseOnZoneClick: true,
	removeLabel: '',
    removeIcon: '<i class="glyphicon glyphicon-remove"></i>',
    removeTitle: 'Cancel or reset changes',
    elErrorContainer: '#kv-avatar-errors-2',
    msgErrorClass: 'alert alert-block alert-danger',
    defaultPreviewContent: '<img src="<%=request.getContextPath()%>/style/images/default_avatar_male.jpg" alt="Your Avatar" style="width:160px">',
    layoutTemplates: {main2: '{preview}  {remove} {browse}'},    
							//  上面的 ' +  btnCust + '
    allowedFileExtensions: ["jpg", "png", "gif"]
});
</script>
<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>