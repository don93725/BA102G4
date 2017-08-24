<%@ page contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.members.model.*"%>		
									
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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

<title>修改管理者</title>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>

	<div class="page-content">
		<div class="page-header">
			<h1>
				員工帳號管理 <small> <i class="icon-double-angle-right"></i>
					員工資料修改 - update_mgr_input.jsp
				</small>
			</h1>
		</div>


		<div>
			<br>
		</div>
		<c:if test="${not empty errorMsgs}">
			<font color='red'>錯誤:
				<ul>
					<c:forEach var="errorMessage" items="${errorMsgs}">
						<li>${errorMessage.value}</li>
					</c:forEach>
				</ul>
			</font>
		</c:if>


		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-sm-offset-2">
					<form method="post"
						action="<%=request.getContextPath()%>/mgr/MgrCtrl" name="form1"
						enctype="multipart/form-data" class="form-horizontal">

						<div class="form-group">
							<label for="mgr_pic" class="col-xs-12 col-sm-3 control-label">
								照片 </label>

							<div id="kv-avatar-errors-2" class="center-block"
								style="width: 800px; display: none"></div>

							<div class="col-xs-12 col-sm-9">
								<div class="kv-avatar center-block text-center"
									style="width: 200px">
									<input id="avatar-2" name="mgr_pic" type="file"
										class="file-loading">
								</div>
							</div>
						</div>

						<div class="form-group">
							<label for="mgr_no" class="col-xs-12 col-sm-3 control-label">
								員工編號: </label>
							<div class="col-xs-12 col-sm-9">
								<input type="TEXT" id="mgr_no" class="form-control"
									disabled="true" value="${managerVO.mgr_no}" />
							</div>
						</div>

						<div class="form-group">
							<label for="mgr_id" class="col-xs-12 col-sm-3 control-label">
								帳號 </label>
							<div class="col-xs-12 col-sm-9">
								<input type="TEXT" id="mgr_id" class="form-control"
									disabled="true" value="${managerVO.mgr_id}" /> <input
									type="hidden" name="mgr_id" id="mgr_id" class="form-control"
									value="${managerVO.mgr_id}" />
							</div>
						</div>

						<div class="form-group">
							<label for="mgr_pwd" class="col-xs-12 col-sm-3 control-label">
								密碼 </label>
							<div class="col-xs-12 col-sm-9">
								<input type="password" name="mgr_pwd" id="mgr_pwd"
									class="form-control" value="${managerVO.mgr_pwd}" />
							</div>
						</div>

						<div class="form-group">
							<label for="mgr_name" class="col-xs-12 col-sm-3 control-label">
								姓名 </label>
							<div class="col-xs-12 col-sm-9">
								<input type="TEXT" name="mgr_name" id="mgr_name"
									class="form-control" value="${managerVO.mgr_name}" />
							</div>
						</div>

						<div class="form-group">
							<label for="mem_nickname"
								class="col-xs-12 col-sm-3 control-label"> 暱稱 </label>
							<div class="col-xs-12 col-sm-9">
								<input type="TEXT" name="mem_nickname" id="mem_nickname"
									class="form-control" value="${membersVO.mem_nickname}" />
							</div>
						</div>


						<div class="form-group">
							<label for="mgr_email" class="col-xs-12 col-sm-3 control-label">
								信箱 </label>
							<div class="col-xs-12 col-sm-9">
								<input type="email" name="mgr_email" id="mgr_email"
									class="form-control" value="${managerVO.mgr_email}" />
							</div>
						</div>
						
						

						<div class="form-group">
							<label for="mgr_job" class="col-xs-12 col-sm-3 control-label">
								職位 </label>
							<div class="col-xs-12 col-sm-9">
								<select size="1" name="mgr_job" class="form-control">
									<option value="0" ${(managerVO.mgr_job==0)?'selected':''}>管理者</option>
									<option value="1" ${(managerVO.mgr_job==1)?'selected':''}>系統管理者</option>
								</select>
							</div>
						</div>

						<jsp:useBean id="funSvc" scope="page"
							class="com.function.model.FunctionService" />
						<jsp:useBean id="autSvc" scope="page"
							class="com.authority.model.AuthorityService" />
						<div class="form-group">
							<label for="f_no" class="col-xs-12 col-sm-3 control-label">
								權限 </label>
							<div class="col-xs-12 col-sm-9" data-role="fieldcontain">
								<c:forEach var="functionVO" items="${funSvc.all}">
									<fieldset data-role="controlgroup" data-type="horizontal">
										<div class="checkbox">
											<label> <input type="checkbox" class='f_no' id="f_no"
												name="f_no" value="${functionVO.f_no}"
												${functionVO.f_no==autSvc.getOneFun(managerVO.mgr_no,functionVO.f_no).f_no?'checked':''}>
												${functionVO.fname}
											</label>
										</div>
									</fieldset>

								</c:forEach>
							</div>
						</div>
						<br>
						<div class="form-group">
							<label for="mgr_int" class="col-xs-12 col-sm-3 control-label">
								自我介紹
							</label>
							<div class="col-xs-12 col-sm-9">
								<textarea id='mgr_int' name="mgr_int" class="form-control" style='resize:none;' rows=4>${managerVO.mgr_int }</textarea>
							</div>
						</div>
						<div class="form-group">
							<label for="mgr_status" class="col-xs-12 col-sm-3 control-label">
								狀態: </label>
							<div class="col-xs-12 col-sm-9">
								<select size="1" name="mgr_status" class="form-control">
									<option value="0" ${(managerVO.mgr_status==0)?'selected':''}>上班</option>
									<option value="1" ${(managerVO.mgr_status==1)?'selected':''}>離職</option>
								</select>
							</div>
						</div>

						<br>
						<div class="form-group">
							<div class="col-xs-12 col-sm-3"></div>

							<div class="col-xs-12 col-sm-9">
								<input type="hidden" name="action" value="update"> <input
									type="hidden" name="mgr_no" value="${managerVO.mgr_no}">
								<input type="hidden" name="mem_no" value="${membersVO.mem_no}">
								<input type="submit" value="送出修改"
									class="btn btn-success btn-lg btn-block" id="picCheck">
							</div>
						</div>
				</div>
			</div>
		</div>

		</form>

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
//     defaultPreviewContent: '<img alt="Your Avatar" style="width:160px" src="../style/images/default_avatar_male.jpg" >',
   defaultPreviewContent: '<img src="<%=request.getContextPath()%>/tools/DBGifReader?mgr_no=${managerVO.mgr_no}" alt="Your Avatar" style="width:160px" id="ppic" >',

	layoutTemplates: {main2: '{preview}  {remove} {browse}'},    
							//  上面的 ' +  btnCust + '
    allowedFileExtensions: ["jpg", "png", "gif"," "]
});
</script>
<script>
console.log($('#ppic').attr("src"));
var ppic = ($('#ppic').attr("src"));
console.log(ppic);
var ppicaddress ='<%=request.getContextPath()%>/tools/DBGifReader?mgr_no=${managerVO.mgr_no}' ;
console.log(ppicaddress);
console.log(ppicaddress == ppic);


	

// $('#picCheck').click(function(){
// 	if( ppic == ppicaddress  ){
// 		$('#ppic').attr("src","");
// 	}
// });


</script>

<script type="text/javascript">
// $(function(){	
// 	$('.f_no:eq(0)').attr('checked','');
// 	$('.f_no:eq(1)').attr('checked','');
// 	$('.f_no:eq(2)').attr('checked','');
// 	$('.f_no:eq(3)').attr('checked','');
// 	$('.f_no:eq(5)').attr('checked','');
// 	$('.f_no:eq(6)').attr('checked','');
// 	$('.f_no:eq(7)').attr('checked','');
// 	$('.f_no:eq(8)').attr('checked','');
// 	$('.f_no:eq(9)').attr('checked','');
// 	$('#a').change(function(){
// 		if($(this).val()==0){
// 			$('.f_no:eq(4)').removeAttr('checked');			
// 		}else{
// 			$('.f_no:eq(4)').attr('checked','');
// 		}

// 	});
// })

</script>

<%@include file="/back_end/include/ace_setting_footer.file"%>
		
</body>
</html>