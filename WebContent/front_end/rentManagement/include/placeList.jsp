<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.place.model.*"%>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>健貨 - GymHome</title>
<!-- 網頁路徑 -->
<script>
	var webCtx ;
	$(function(){
 		var path = window.location.pathname;
 		webCtx = path.substring(0, path.indexOf('/', 1));
	})
</script>

</head>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped">
						<thead class="aaa">
							<tr>
								<th class="center">場地名稱</th>
								<th>可容納人數</th>
								<th>場地地址</th>
								<th>場地介紹</th>
								<th>修改</th>
								<th>刪除</th>
								<th>上下架狀況</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="placeVO" items="${placeList}">
								<tr>
									<td class="center"><span class="">${placeVO.p_name}</span></td>
									<td><span class="">${placeVO.p_cap}</span></td>
									<td><span class="">${placeVO.p_add}</span></td>
									<td><span class="">${placeVO.p_into}</span></td>
									<td><input type="button" class="btn btn-primary btn-sm"
										id="" onclick=""
										value="我要修改">
									</td>
									<td><input type="button" class="btn btn-danger btn-sm"
										id="" onclick="deletePlace(${placeVO.p_no});" value="我要刪除"></td>
									<td><span class="p_status">${placeVO.p_status}</span></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /col-sm-12 -->
		</div>
		<!-- /row -->
	</div>
	<!-- 課程管理 全部 結束 -->
</body>
	<script>
		function deletePlace(no) {
			swal({
				title: "確定刪除?",
				type: "info",
				showCancelButton: true,
				confirmButtonColor: "#F8BB86",
				cancelButtonText: "取消",
				confirmButtonText: "確定",
				closeOnConfirm: false
			},function(response){
				if(response){
					$.ajax({
						url : webCtx + '/PlaceServlet',
						data : {
							p_no : no,
							action : 'delete'
						},
						type : "POST",
						dataType : 'text',

						success : function(msg) {
							swal("刪除成功!", "資料已刪除成功", "success");
							$(".inline").colorbox.close();
							setTimeout("location.reload()",1000);
						},

						error : function(xhr, ajaxOptions, thrownError) {						
							/*swal("失敗", "請修正以下錯誤", "error");*/
						}
					});
				}
			});
		}
	</script>
</html>