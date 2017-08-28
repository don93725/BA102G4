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

		<a class='inline' href="#insert_content">
			<button class="btn btn-white">
				<i class="icon-plus"></i>新增場地
			</button>
		</a><i class="arrow-left"></i>
		<p></p>
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped" style="text-align:center;">
						<thead class="aaa">
							<tr>
								<th class="center">場地名稱</th>
								<th class="center">人數</th>
								<th class="center">場地地址</th>
								<th class="center">場地介紹</th>
								<th class="center"></th>
								<th class="center"></th>

							</tr>
						</thead>
						<tbody>
							<c:forEach var="placeVO" items="${placeList}">
								<tr>
									<input type="hidden" value="${placeVO.p_no}">
									<td style="vertical-align:middle;">${placeVO.p_name}</td>	
									<td style="vertical-align:middle;">${placeVO.p_cap}</td>
									<td style="vertical-align:middle;">${placeVO.p_add}</td>
									<td style="vertical-align:middle;" class="p_into_limited">${placeVO.p_into}</td>
									<input type="hidden" class="p_status" value="${placeVO.p_status}">
									
									
									<!-- 下架的場地 -->
									<c:if test="${placeVO.p_status == '0'}">
									<td>
										<div class="btn-group"> 
  											<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" style="border-width:5px;">
    											編輯<span class="caret"></span>
  											</button>
  											<ul class="dropdown-menu" style="min-width:50px;">
    											<li>
   													<a class='inline' href="#update_content" onclick="show('${placeVO.p_name}','${placeVO.p_cap}','${placeVO.p_add}','${placeVO.p_into}','${placeVO.p_no}')">
   													<i class="icon-edit"></i> 更新場地	
    												</a>
    											</li>
    											<li>
    												<a class='inline' href="#insertPic_content" onclick="showPicWindow('${placeVO.p_no}')" value="新增圖片">
    												<i class="icon-camera"></i> 新增圖片
    												</a>
    											</li>
    											<li>
    												<a href="#" onclick="deletePlace('${placeVO.p_no}', '${placeVO.p_status}')">
    												&nbsp<i class="icon-map-marker"></i> 刪除場地
    												</a>
    											</li>
  											</ul>
										</div>	
									</td>
									<td>
										<a class='inline' href="#publish_content" onclick="showPublish('${placeVO.p_name}','${placeVO.p_cap}','${placeVO.p_add}','${placeVO.p_into}','${placeVO.p_no}')">
											<input type="button" class="btn btn-info" value="我要上架">
										</a>
									</td>
									<td>
									</td>
									</c:if>
									<!-- 下架的場地 結束 -->
									
									<!-- 上架中的場地 -->
									<c:if test="${placeVO.p_status == '1'}">
									<td>
										<div class="btn-group"> 
  											<button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false" disabled="disabled" style="border-width:5px;">
    											編輯 <span class="caret"></span>
  											</button>
  											<ul class="dropdown-menu" style="min-width:50px;">
    											<li>
   													<a href="#" onclick="useOrUp();">
   														<i class="icon-edit"></i> 更新場地	
    												</a>
    											</li>
    											<li>
    												<a href="#" onclick="useOrUp();">
    													<i class="icon-edit"></i> 新增圖片
    												</a>
    											</li>
    											<li>
    												<a href="#" onclick="useOrUp();">
    													<i class="icon-edit"></i> 刪除場地
    												</a>
    											</li>
  											</ul>
										</div>	
									</td>
									<td>
										<input type="button" class="btn btn-primary" value="我要下架" onclick="unPublish(${placeVO.p_no})">
									</td>

									</c:if>
									<!-- 上架中的場地 結束 -->
									
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

	<!-- 刪除效果、已上架按鈕控制 -->
	<script>
		function deletePlace(no, p_status) {
			swal({
				title: "確定刪除?",
				text: "注意：上架後的場地無法進行 編輯 與 刪除",
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
							p_status : p_status,
							action : 'delete'
						},
						type : "POST",
						dataType : 'text',

						success : function(msg) {
							console.log(msg);
							if(msg.length == 0) {
								swal({
									title : "刪除成功!",
									text : "資料已刪除成功",
									type : "success",
									showConfirmButton : false});
								$(".inline").colorbox.close();
								setTimeout("location.reload()",1000);
							}else {
								swal("失敗", "已上架或使用中的場地無法刪除，請先下架或等待使用結束", "error");
							}	
						},

						error : function(xhr, ajaxOptions, thrownError) {
							console.log(thrownError);
							swal("失敗", "已上架或使用中的場地無法刪除，請先下架或等待使用結束", "error");
						}
					});
				}
			});
		}
		
		function useOrUp(){
			swal({
				title : "錯誤",
				text : "已上架或使用中的場地無法使用此功能",
				type : "error",
				showConfirmButton : true,
				timer : 2000});
		}
		function unPublish(no){
			swal({
				title : "場地下架",
				text : "下架後的場地無法被看見",
				type: "info",
				showCancelButton: true,
				confirmButtonColor: "#F8BB86",
				cancelButtonText: "取消",
				confirmButtonText: "確定",
				closeOnConfirm: true
			},function(response){
				if(response){
					$.ajax({
						url : webCtx + '/Place_PublishServlet',
						data : {
							p_no : no,
							action : 'unPublish'
						},
						
						type : "POST",
						dataType : 'text',

						success : function(msg) {
							if(msg.length == 0) {
								swal({
									title : "下架成功",
									text : "場地已下架",
									type : "success",
									timer : "1500",
								});
								$(".inline").colorbox.close();
								setTimeout("location.reload()",1000);
							}
						},

						error : function(xhr, ajaxOptions, thrownError) {						
							swal("失敗", "HelloWorld", "error");
						}
					});
				}
			});
		}
	</script>
<!-- 控制字數顯示 -->
<script>
$(function(){
	var len = 20; // 超過50個字以"..."取代
    $(".p_into_limited").each(function(){
        if($(this).text().length>len){
            $(this).attr("title",$(this).text());
            var text=$(this).text().substring(0,len-1)+" ...";
            $(this).text(text);
        }
    });
});
</script>
</html>