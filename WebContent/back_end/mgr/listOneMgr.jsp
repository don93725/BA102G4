<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager.model.*"%>
<%@ page import="com.members.model.*"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
<title>健貨後台管理系統</title>
<%@include file="/back_end/include/basic_ace_script.file" %>
</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>


	<!-- 內容從這開始，要改最好只改這裡 -->
	<div class="page-content">
		<div class="page-header">
			<h1>
				員工帳號管理 <small> <i class="icon-double-angle-right"></i> 員工 -
					listOneMgr
				</small>
			</h1>
		</div>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl"
			name="form1" enctype='multipart/form-data'>
			<div class="row">
				<div class="col-md-12 col-md-offset-1 ">

					<div class="col-md-2">
						<div class="input-group">
							<label class="input-group-addon">員工編號</label> <input type="text"
								name="mgr_no" class="form-control" placeholder="請輸入編號">
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<label class="input-group-addon">員工姓名</label> <input type="text"
								name="mgr_name" class="form-control" placeholder="請輸入姓名">
						</div>
					</div>
					<div class="col-md-2">
						<div class="input-group">
							<label class="input-group-addon">職位</label> <select
								name="mgr_job">
								<option value="">請選擇
									<option value="0">管理員
											
								<option value="1">系統管理員
										
							</select>
									</div>
								</div>
								<div class="col-md-2">
									<div class="input-group">
										<label class="input-group-addon">狀態</label>
										 <select name="mgr_status">
											<option value="">請選擇
											
								<option value="0">帳號起用中
											
								<option value="1">帳號以停權
										
							</select>
									</div>
								</div>
								
								<div class="col-md-2">
									<input type="submit" class="btn btn-info  btn-group-lg"
							value="查詢">
									<input type="hidden" name="action"
							value="listMgr_ByCompositeQuery">
								</div>
								
							</div>
						</div>
					</FORM>


		<div class="row">
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">管理員編號</th>
								<th class="center">照片</th>
								<th class="center">帳號</th>
								<th class="center">職務</th>
								<th class="center">姓名</th>
								<th class="center">信箱</th>
								<th class="center">狀態</th>
								<th class="center">修改</th>
							</tr>
						</thead>

						<tbody>
								<tr>

									<td class="center">${managerVO.mgr_no}</td>

									<td class="center"><img
										src="<%=request.getContextPath()%>/tools/DBGifReader?mgr_no=${managerVO.mgr_no}"
										style="width: 100px; height: 100px;"></td>
									<td class="center">${managerVO.mgr_id}</td>
									<td class="center">${(managerVO.mgr_job==0)?'管理員':'系統管理員'}</td>
									<td class="center">${managerVO.mgr_name}</td>
									<td class="center">${managerVO.mgr_email}</td>
									<td class="center">${(managerVO.mgr_status==0)?'在職中':'離職'}</td>


									<td class="center">
										<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl" enctype='multipart/form-data'>
											<input type="submit" value="修改" class="btn btn-inverse">
											<input type="hidden" name="mem_no" value="${managerVO.mem_no}"> 
											<input type="hidden" name="mgr_no" value="${managerVO.mgr_no}"> 
											<input type="hidden" name="action" value="getOne_For_Update">
										</FORM>
									</td>

								</tr>

						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /col-sm-12 -->
		</div>
		<!-- /row -->

<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
</html>