<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.manager.model.*" %>

<%
	ManagerService mgrSvc = new ManagerService();
	List<ManagerVO> list = mgrSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-cn-en">
<head>
</head>
<body>
	<div class="page-content">
		<div class="page-header">
			<h1>
				員工帳號管理 <small> <i class="icon-double-angle-right"></i>
					員工總覽 - listAllMgr
				</small>
			</h1>
		</div>

<!-- <b><font color=red>此頁練習採用 EL 的寫法取值:</font></b> -->
<!-- <table border='1' cellpadding='5' cellspacing='0' width='800'> -->
<!-- 	<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'> -->
<!-- 		<td> -->
<!-- 		<h3>所有員工資料 - ListAllMgr.jsp</h3> -->
<%-- 		<a href="<%=request.getContextPath()%>/back_end/index.jsp">回首頁</a> --%>
<!-- 		</td> -->
<!-- 	</tr> -->
<!-- </table> -->
<%-- <c:if test="${not empty errorMsgs}"> --%>
<!-- 	<font color='red'>請修正以下錯誤: -->
<!-- 	<ul> -->
<%-- 		<c:forEach var="message" items="${errorMsgs}"> --%>
<%-- 			<li>${message}</li> --%>
<%-- 		</c:forEach> --%>
<!-- 	</ul> -->
<!-- 	</font> -->

<%-- </c:if> --%>

				
					<!-- /.page-header -->

							<!-- PAGE CONTENT BEGINS -->

<!-- 								<div class="row"> -->
<!-- 							</div> -->
				
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
									<%@ include file="page1.file" %> 
										<c:forEach var="managerVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
										<tr>
											
											<td class="center">${managerVO.mgr_no}</td>
											
											<td class="center">
												<img src="<%=request.getContextPath()%>/tools/DBGifReader?mgr_no=${managerVO.mgr_no}" style="width:100px;height:100px;">
											</td>
											
											<td class="center">${managerVO.mgr_id}</td>
											<td class="center">${(managerVO.mgr_job==0)?'管理員':'系統管理員'}</td>
											<td class="center">${managerVO.mgr_name}</td>
											<td class="center">${managerVO.mgr_email}</td>
											<td class="center">${(managerVO.mgr_status==0)?'在職中':'離職'}</td>
											
											
											<td class="center">
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/mgr/MgrCtrl" enctype='multipart/form-data'>
			    								<button type="submit" class="btn btn-app btn-primary  btn-xs "><i class="icon-edit "></i>修改</button>
			     								<input type="hidden" name="mem_no" value="${managerVO.mem_no}">
			   								  	<input type="hidden" name="mgr_no" value="${managerVO.mgr_no}">
			   									<input type="hidden" name="action"	value="getOne_For_Update">
			   								</FORM>
											</td>
											
										</tr>
										
										</c:forEach>
											<%@ include file="page2.file" %>
									</tbody>
								</table>
							</div>
							<!-- /.table-responsive -->
						</div>
						<!-- /col-sm-12 -->
					</div>
					<!-- /row -->
	</div>
</body>
</html>