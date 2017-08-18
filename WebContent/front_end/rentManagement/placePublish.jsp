<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>健貨 - GymHome</title>
		<%@include file="/front_end/include/basicScript.file" %>
</head>
<!-- 網頁路徑 -->
<script>
	var webCtx ;
	$(function(){
 		var path = window.location.pathname;
 		webCtx = path.substring(0, path.indexOf('/', 1));
	})
</script>

<!-- 燈箱 -->
<script>
$(document).ready(function(){
	$(".inline").colorbox({inline:true, width:"50%"});
});
</script>

<body>

	<!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
	
	<!-- Page Content -->
	<div class="container">

	<!-- 新增場地視窗 -->
	<div style='display:none'>
		<div id='inline_content' style='padding:0px; background:#fff;'>
			<h1>新增場地</h1>
			<ul id="error">
				<c:forEach var="message" items="${errorMsgs}">
					<li>${errorMsgs}</li>
				</c:forEach>
			</ul>
			<input type="button" value="神奇小按鈕" onclick="magicPlace();">
			<form method="post" action="<%=request.getContextPath()%>/PlaceServlet">
				場地名稱:<br>
				<input type="text" pattern="^[(\u4e00-\u9fa5)(a-zA-Z)]{1,18}$" title="只能是中、英文字母 ,且長度必需在1到18之間" name="p_name" id="p_name" size="85" minlength="1" maxlength="18" required><br>
				可容納人數：<br>
				<input type="text" pattern="^([1-9][0-9]){1,2}$" title="只能輸入1-99" name="p_cap" id="p_cap" minlength="1" maxlength="2" required><br>
				場地地址：<br>
				<input type="text" name="p_add" id="p_add" pattern="^[(\u4e00-\u9fa5)(a-zA-Z)(0-9)(-)]{1,50}$" title="只能是大小寫英數字，且長度必需在1到50之間" size="85" minlength="1" maxlength="50" required>
				<input type="hidden" id="gym_add" value="${gym.gym_add}">
				<input type="button" value="地址同健身房位置" onclick="addAddr();"><br>
				場地介紹:<br>
				<textarea name="p_into" id="p_into" title="" rows="5" cols="85" class="text" resize="none"
					id="" minlength="1" maxlength="500" required></textarea><br>
				<input type="button" class="btn btn-primary" value="新增" onclick="send();">
				<input type="reset" class="btn btn-default" name="" value="重新填寫" id="cancel">
				<input type="hidden" name="action" value="insert">
			</form>
		</div>
	</div>
	<!-- 本頁面專屬js -->
	<script>
		function addAddr(){
			document.getElementById("p_add").value = document.getElementById("gym_add").value;
		}
		
		function magicPlace() {
			document.getElementById("p_name").value = "格鬥八角鐵籠";
			document.getElementById("p_cap").value = "10";
			document.getElementById("p_add").value = "台北市大安區敦化南路一段232巷6號B1";
			document.getElementById("p_into").value = "可做為MMA之訓練場地，並提供賽事之舉辦與租借。";
		}

		function send() {		
			swal({
				title: "確定新增?",
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
							p_name : $("#p_name").val(),
							p_cap : $("#p_cap").val(),
							p_add : $("#p_add").val(),
							p_into : $("#p_into").val(),
							action : 'insert'
						},
						type : "POST",
						dataType : 'text',

						success : function(msg) {
							swal("新增成功!", "資料已新增成功", "success");
							$(".inline").colorbox.close();
							setTimeout("location.reload()",1000);
						},

						error : function(xhr, ajaxOptions, thrownError) {						
							swal("失敗", "請修正以下錯誤", "error");
						}
					});
				}
			});
		}
	</script>		
		
		
		
		
		
		
		<!-- Page Heading/Breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<div class="col-lg-12">
				<h1>
					租借管理 <small>$#^</small>
				</h1>
			</div>

			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首頁</a></li>
				<li class="active">租借管理</li>
				<li class="active">@$#</li>
			</ul>
			<!-- .breadcrumb -->
		</div>
		<br>
			
		<!-- Content Row -->
		<div class="row">
			<div class="col-lg-12">	
    
			<div class="tabbable">
					<!-- tab那一欄 -->
					<ul class="nav nav-tabs" id="myTab">
						<!-- 申請者管理 -->
						<li id = ""><a data-toggle="tab" href="#profile" onclick=""> 申請者管理 </a>
						</li>
						
						<!-- 上/下架管理 -->
						<li class="dropdown" id = ""><a data-toggle="dropdown"
							class="dropdown-toggle" href="#"> 上/下架管理 &nbsp;<i
								class="icon-caret-down bigger-110 width-auto"></i></a> <!-- 課程管理細項 -->
							<ul class="dropdown-menu dropdown-info">
								<li>
								<a data-toggle="tab" href="#" onclick="dropdown(1)">場地列表<span class="badge badge-danger">4</span></a>
									<form action="<%= request.getContextPath() %>/PlaceServlet" method="post" style="display: none">
										<input type="submit" class="btn btn-default" id="dropdown1" role="button" value="場地列表">
										<input type="hidden" name="action" value="placeList">
									</form>
								</li>
								
								<li><a data-toggle="tab" href="" onclick="">上架中</a>
								</li>
								<li><a data-toggle="tab" href="" onclick="">下架中</a></li>
								</li>
								<li><a data-toggle="tab" href="" onclick="">正在使用中</a></li>
							</ul>
						</li>

						<!-- 租借紀錄查詢 -->
						<li class="" id = ""><a data-toggle="tab" href="" onclick=""> <i
								class="green icon-home bigger-110"></i>租借紀錄查詢</a>
						</li>

						<!-- 租借報表查詢 -->
						<li id = ""><a data-toggle="tab" href="#profile" onclick=""> 租借報表查詢 </a>
						</li>
						
						<!-- 檢舉教練 -->
						<li id = ""><a data-toggle="tab" href="#profile" onclick=""> 檢舉教練 </a>
						</li>
					</ul>


					<!-- 內容全在這裡 -->
					<div class="tab-content page" style="background-color:white;">
						<div class="col-md-12"><a class='inline' href="#inline_content"><button class="btn btn-primary">新增場地</button></a></div>
						<jsp:include page="/front_end/rentManagement/include/placeList.jsp"/>
					</div>
				</div>
				<!-- 內容全在這裡 結束 -->


			</div>
			<!-- col-lg-12 -->
			<div class="vspace-xs-6"></div>
		</div>
		<!-- /row -->
</div>
	
			
	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<script>
		function dropdown(num) {
			var btn = "#dropdown" + num;
			$(btn).click();
		}
	</script>
</html>
