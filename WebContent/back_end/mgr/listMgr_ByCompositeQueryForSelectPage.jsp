<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- 萬用複合查詢-可由客戶端select_page.jsp隨意增減任何想查詢的欄位 --%>
<%-- 此頁只作為複合查詢時之結果練習，可視需要再增加分頁、送出修改、刪除之功能--%>

<jsp:useBean id="listMgr_ByCompositeQuery" scope="request" type="java.util.List" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn-en">
<head>
<title>健貨後台管理系統</title>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				員工帳號管理 <small>
				<i class="icon-double-angle-right"></i><a href="/BA102G4/back_end/mgr/select_page.jsp"> 員工總覽  </a>
				<i class="icon-double-angle-right"></i> 查詢結果 
				</small>
			</h1>
		</div>

	
		<div class="row">
		<center><h2>員工帳號管理</h2></center>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center" style="vertical-align:middle;">管理員編號</th>
								<th class="center" style="vertical-align:middle;">照片</th>
								<th class="center" style="vertical-align:middle;">帳號</th>
								<th class="center" style="vertical-align:middle;">職務</th>
								<th class="center" style="vertical-align:middle;">姓名</th>
								<th class="center" style="vertical-align:middle;">信箱</th>
								<th class="center" style="vertical-align:middle;">狀態</th>
								<th class="center" style="vertical-align:middle;">修改</th>
							</tr>
						</thead>	
						
						<tbody>
					<%@ include file="pages/page1_ByCompositeQuery.file"%>
					<c:forEach var="managerVO" items="${listMgr_ByCompositeQuery}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr align='center' valign='middle' ${(managerVO.mgr_no==param.mgr_no) ? 'bgcolor=#CCCCFF':''}>
									<!--將修改的那一筆加入對比色而已-->
								<td class="center" style="vertical-align:middle;">${managerVO.mgr_no}</td>
								<td class="center" style="vertical-align:middle;"><img src="<%=request.getContextPath()%>/tools/DBGifReader?mgr_no=${managerVO.mgr_no}"
										style="width: 100px; height: 100px;"></td>
								<td class="center" style="vertical-align:middle;">${managerVO.mgr_id}</td>
									<%-- 			<td>${managerVO.mgr_pwd}</td> --%>
								<td class="center" style="vertical-align:middle;">${(managerVO.mgr_job==0)?'管理員':'系統管理員'}</td>
								<td class="center" style="vertical-align:middle;">${managerVO.mgr_name}</td>
								<td class="center" style="vertical-align:middle;">${managerVO.mgr_email}</td>
								<td class="center" style="vertical-align:middle;">${(managerVO.mgr_status==0)?'在職中':'離職'}</td>
								<td class="center" style="vertical-align:middle;">
										<!-- 送過去的網頁是用PART處理，所以這裡FORM記得用enctype='multipart/form-data' -->
									<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl"
										enctype='multipart/form-data'>
<!-- 										<input type="submit" value="修改" class="btn btn-inverse">  -->
										<button type="submit" class="btn btn-primary  btn-sm "><i class="icon-edit "></i>修改</button>
										<input type="hidden" name="mem_no" value="${managerVO.mem_no}"> 
										<input type="hidden" name="mgr_no" value="${managerVO.mgr_no}">
										<input type="hidden" name="action" value="getOne_For_Update">
									</FORM>
								</td>
								</tr>
							</c:forEach>
	<%@ include file="pages/page2_ByCompositeQuery.file"%>
							</tbody>
					</table>
</div>
</div>
</div>
</div>

	</body>
</html>
