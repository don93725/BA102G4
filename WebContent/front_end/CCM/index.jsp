<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ include file="default.file"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>

<!--     <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content=""> -->


<style type="text/css">
.second {
	margin-top: 2em;
}

.first {
	margin-top: 2em;
}

.list {
	padding: 0;
	position: relative;
	left: 5em;
}

#error {
	color: red;
}

#dropDIV {
	text-align: center;
	width: 650px;
	height: 150px;
	margin: 1em;
	border: dashed 4px skyblue;
	background-image: url("");
}

#dropChangeDIV {
	text-align: center;
	width: 850px;
	height: 150px;
	margin: 1em;
	border: dashed 4px skyblue;
	background-image: url("");
}
</style>
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>



	<!--第一個colorbox -->
	<div style="display: none;">
		<div id='add_content' style="margin: 1em;">
			<h1 class="center">新增課程</h1>
			<font color='red'><span id="errorMsg"></span></font><br> 課程名稱:<input
				type="text" name="crs_name" id="crs_name"> 類別: <select
				name="category" id="category">
				<option name="" value="A" selected>瑜珈</option>
				<option name="" value="B">飛輪有氧</option>
				<option name="" value="C">舞動有氧</option>
				<option name="" value="D">拳擊有氧</option>
				<option name="" value="E">基礎重訓</option>
				<option name="" value="F">進階重訓</option>
				<option name="" value="G">皮拉提斯</option>
				<option name="" value="H">TRX肌力雕塑</option>
				<option name="" value="O">其他</option>
			</select><br> 課程內容簡述:<br>
			<textarea name="details" rows="4" cols="30" class="text" id="details"></textarea>
			<br>
			<br> <img src='' id="img0" width="100px" heigth="100px"
				style="margin: 1em;"><input type='hidden' name='crs_pic_base0'
				id="base0" value=''> <img src='' id="img1" width="100px"
				heigth="100px" style="margin: 1em;"><input type='hidden'
				name='crs_pic_base1' id="base1" value=''> <img src=''
				id="img2" width="100px" heigth="100px" style="margin: 1em;"><input
				type='hidden' name='crs_pic_base2' id="base2" value=''> <img
				src='' id="img3" width="100px" heigth="100px" style="margin: 1em;"><input
				type='hidden' name='crs_pic_base3' id="base3" value=''> <img
				src='' id="img4" width="100px" heigth="100px" style="margin: 1em;"><input
				type='hidden' name='crs_pic_base4' id="base4" value=''> <input
				type='hidden' id="count_pic" name="crs_pic_count" value='0'>

			<br>
			<br> <input type="button" name="" class="btn btn-primary"
				value="送出" id="send"><input type="button" name=""
				class="btn btn-default" value="取消" id="cancel"> <input
				type="hidden" name="action" value="courseInsert"> <br>
			<h3>新增圖片</h3>
			<br>
			<button onclick="addPic()" class="btn btn-primary">確認新增</button>
			<button onclick="clearPic()" class="btn btn-default">清除</button>
			(須先加入才能新增，最多五張)
			<div id="dropDIV" ondragover="dragoverHandler(event)"
				ondrop="dropHandler(event)" onclick="updateFile()">
				課程圖片 拖曳圖片到此處上傳,最多五張
				<div id="up_progress">
					<div id="imgDIV" style="margin-bottom: 30px;"></div>
				</div>
			</div>
			<form style="display: none;">
				<input type="file" id="clickimg"> <input type="reset"
					id="resetFileImg">
			</form>
		</div>
	</div>

	<!--第二個colorbox -->
	<div style="display: none;">
		<div id='updatePic' style="margin: 1em;">
			<h1 class="center">修改圖片</h1>
			<br> <span id="showChangePic"></span><input type="button"
				name="" class="btn btn-warning" value="確定刪除" id="sendDeletePic"
				disabled="disabled"><br>
			<br> <img src='' id="imgChange0" width="100px" heigth="100px"
				style="margin: 1em;"><input type='hidden'
				name='crs_pic_Change0' id="baseChange0" value=''> <img
				src='' id="imgChange1" width="100px" heigth="100px"
				style="margin: 1em;"><input type='hidden'
				name='crs_pic_Change1' id="baseChange1" value=''> <img
				src='' id="imgChange2" width="100px" heigth="100px"
				style="margin: 1em;"><input type='hidden'
				name='crs_pic_Change2' id="baseChange2" value=''> <img
				src='' id="imgChange3" width="100px" heigth="100px"
				style="margin: 1em;"><input type='hidden'
				name='crs_pic_Change3' id="baseChange3" value=''> <img
				src='' id="imgChange4" width="100px" heigth="100px"
				style="margin: 1em;"><input type='hidden'
				name='crs_pic_Change4' id="baseChange4" value=''> <input
				type='hidden' id="crs_pic_countChange" name="crs_pic_countChange"
				value='0'><br> <input type="button" name=""
				class="btn btn-primary" value="送出新增" id="sendChangePic" onclick="">
			<input type="button" name="" class="btn btn-default" value="取消"
				onclick="cancelPic()"> <br>
			<h3>新增圖片</h3>
			<br>
			<button onclick="addChangePic()" class="btn btn-primary">確認新增</button>
			<button onclick="clearPic()" class="btn btn-default">清除</button>
			(須先加入才能新增，最多五張)
			<div id="dropChangeDIV" ondragover="dragoverChangeHandler(event)"
				ondrop="dropChangeHandler(event)" onclick="updateChangeFile()">
				拖曳圖片到此處上傳,最多五張
				<div id="up_progress">
					<div id="imgChangeDIV" style="margin-bottom: 30px;"></div>
				</div>
			</div>
			<form style="display: none;">
				<input type="file" id="clickimgChange"> <input type="reset"
					id="resetChangeFileImg">
			</form>
		</div>
	</div>


	<!-- Page Content -->
	<div class="container">

		<!-- Page Heading/Breadcrumbs -->
		<div class="breadcrumbs" id="breadcrumbs">
			<script type="text/javascript">
				try {
					ace.settings.check('breadcrumbs', 'fixed')
				} catch (e) {
				}
			</script>

			<div class="col-lg-12">
				<h1>
					課程管理 <small>CoursesManagement</small>
				</h1>
			</div>

			<ul class="breadcrumb">
				<li><i class="icon-home home-icon"></i> <a href="#">首頁</a></li>
				<li class="active">會員專區</li>
				<li class="active">課程管理</li>
				<form action="<%=request.getContextPath()%>/CCM/CourseManager.do" method="post">
					<input type="submit" value="查課程">
					<input type="hidden" name="action" value="crsDetailList">
				</form>
			</ul>
			<!-- .breadcrumb -->
		</div>
		<br>

		<!-- Content Row -->
		<div class="row">
			<div class="col-lg-12">

				<div class="tabbable">
					<!-- 課程那一欄 -->
					<ul class="nav nav-tabs" id="myTab">

						<!-- 課程管理 -->
						<li class="dropdown" id="manager"><a data-toggle="dropdown"
							class="dropdown-toggle" id="manager1" href="#"><i class="green glyphicon glyphicon-pencil"></i>
							 課程管理 &nbsp;<i
								class="icon-caret-down bigger-110 width-auto"></i>
						</a> <!-- 課程管理細項 -->
							<ul class="dropdown-menu dropdown-info">
								<li><a data-toggle="tab" href="" onclick="dropdown(1)">課程列表<span
										class="badge badge-danger">4</span></a>
									<form method="post"
										action="<%=request.getContextPath()%>/CCM/CourseManager.do"
										style="display: none">
										<input type="submit" id="dropdown1" role="button"
											value="課程的管理"> <input type="hidden" name="action"
											value="courseList">
									</form></li>
								<li><a data-toggle="tab" href="" onclick="dropdown(2)">上架中</a>
									<form method="post"
										action="<%=request.getContextPath()%>/CCM/CourseManager.do"
										style="display: none">
										<input type="submit" class="btn btn-default" id="dropdown2"
											role="button" value="上架中課程"> <input type="hidden"
											name="action" value="coursePublishList">
									</form></li>
								<li><a data-toggle="tab" href="" onclick="dropdown(3)">已開課</a></li>
							</ul>
							<form method="post"
								action="<%=request.getContextPath()%>/CCM/CourseManager.do"
								style="display: none">
								<input type="submit" class="btn btn-default" id="dropdown3"
									role="button" value="開課中課程"> <input type="hidden"
									name="action" value="courseOpenList">
							</form></li>

						<!-- 課程紀錄 -->
						<li class="" id="record"><a data-toggle="tab" id="record1" href=""
							onclick="dropdown(4)"> <i class="green icon-home bigger-110"></i>課程紀錄
						</a>
							<form method="post"
								action="<%=request.getContextPath()%>/CCM/CourseManager.do"
								style="display: none">
								<input type="submit" class="btn btn-default" id="dropdown4"
									role="button" value="課程紀錄"> <input type="hidden"
									name="action" value="courseRecord">
							</form></li>

						<!-- 課程課程報表紀錄 -->
						<li id="report"><a data-toggle="tab" id="report1" href="#profile"
							onclick="dropdown(5)"><i class="green glyphicon glyphicon-book"></i> 課程報表紀錄 </a>
							<form method="post"
								action="<%=request.getContextPath()%>/CCM/CourseManager.do"
								style="display: none">
								<input type="submit" class="btn btn-default" id="dropdown5"
									role="button" value="課程報表紀錄"> <input type="hidden"
									name="action" value="courseReport">
							</form></li>
					</ul>


					<!-- 內容全在這裡 -->
					<div class="tab-content page">
						<jsp:include page="${page}" flush="true" />
					</div>
				</div>
				<!-- 內容全在這裡 結束 -->


			</div>
			<!-- col-lg-12 -->
			<div class="vspace-xs-6"></div>
		</div>
		<!-- /row -->

		<hr>



	</div>
	<!-- /.container -->
	<button id="changeActive" onclick="chageActive(${active})"
		style="display: none;"></button>
	<div id="deletePicAppend" style="display: none;">
		<span id="deletePicCount">
	</div>



 	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>

	<%@include file="/front_end/include/basicScript2.file" %>


</html>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/CCM/dist/sweetalert-dev.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/CCM/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/CCM/dist/sweetalert.css" />
<script type="text/javascript">
	$("#cancel").click(cancel);
	$("#send").click(send);
	$("#sendChangePic").click(sendChangePic);
	$("#sendDeletePic").click(sendDeletePic);
	$(function(){
		var liBtn = QueryString("liBtn").trim();
		$('#'+liBtn).trigger('click');
	})
	function QueryString(name) {
		var AllVars = window.location.search.substring(1);
		var Vars = AllVars.split("&");
		for (i = 0; i < Vars.length; i++)
		{
			var Var = Vars[i].split("=");
			if (Var[0] == name) return Var[1];
		}
		return "";
	}
	window.onload =  function () {
		$("#changeActive").trigger('click');
		document.getElementById("clickimg").addEventListener('change',updateHandler,false);
		document.getElementById("clickimgChange").addEventListener('change',updateChangeHandler,false);
	}

	function updateFile(){
		$("#clickimg").click();
	}
	
	function updateChangeFile(){
		$("#clickimgChange").click();
	}
	
    function dragoverHandler(evt) {
        evt.preventDefault();
        if($("#imgDIV > img").size() == 5){
    		$("#dropDIV").css("background-color","firebrick");
    	}else{
    		$("#dropDIV").css("background-color","gray");
    	} 
    }
    
    function dragoverChangeHandler(evt) {
        evt.preventDefault();
        if($("#imgChangeDIV > img").size() == 5){
    		$("#dropChangeDIV").css("background-color","firebrick");
    	}else{
    		$("#dropChangeDIV").css("background-color","gray");
    	} 
    }
    
    function dropHandler(evt) {//evt 為 DragEvent 物件
        evt.preventDefault();
        $("#dropDIV").css("background-color","white");
        var files = evt.dataTransfer.files;
    
        for (var i in files) {
            if (files[i].type == 'image/jpeg' || 'image/png') {
                //將圖片在頁面預覽
                var fr = new FileReader();
                fr.onload = openfile;
                fr.readAsDataURL(files[i]);
            }else{
    			alert("只能傳圖片");
    		}
        }
    }
    
    function dropChangeHandler(evt) {//evt 為 DragEvent 物件
        evt.preventDefault();
        $("#dropChangeDIV").css("background-color","white");
        var files = evt.dataTransfer.files;
    
        for (var i in files) {
            if (files[i].type == 'image/jpeg' || 'image/png') {
                //將圖片在頁面預覽
                var fr = new FileReader();
                fr.onload = openChangefile;
                fr.readAsDataURL(files[i]);
            }else{
    			alert("只能傳圖片");
    		}
        }
    }

    function updateHandler(){
    	if (this.files[0].type == 'image/jpeg' || 'image/png'){
        	var file = this.files[0];
        	var fr = new FileReader();
        	fr.onload = openfile;
            fr.readAsDataURL(file);
            $("#resetFileImg").click();
    	}else{
    		alert("只能傳圖片");
    	}
    }
    
    function updateChangeHandler(){
    	if (this.files[0].type == 'image/jpeg' || 'image/png'){
        	var file = this.files[0];
        	var fr = new FileReader();
        	fr.onload = openChangefile;
            fr.readAsDataURL(file);
            $("#resetChangeFileImg").click();
    	}else{
    		alert("只能傳圖片");
    	}
    }

    function openfile(evt) {
    	if($("#imgDIV > img").size() == 5){
    		swal("最多只能新增五張圖片", "At most five pictures are accepted", "error");
    		return;
    	}
        var img = evt.target.result;
        var imgx = document.createElement('img');
        imgx.style.margin = "10px";
        imgx.style.width = "100px";
        imgx.style.height = "100px";
        imgx.src = img;
        imgx.className  = "aa";
        document.getElementById('imgDIV').appendChild(imgx);
    }   
    
    function openChangefile(evt) {
    	if($("#imgChangeDIV > img").size() == 5){
    		swal("最多只能新增五張圖片", "At most five pictures are accepted", "error");
    		return;
    	}
        var img = evt.target.result;
        var imgx = document.createElement('img');
        imgx.style.margin = "10px";
        imgx.style.width = "100px";
        imgx.style.height = "100px";
        imgx.src = img;
        imgx.className  = "bb";
        document.getElementById('imgChangeDIV').appendChild(imgx);
    }   

	
	function chageActive(num) {
		if (num == '2'){
			$("#record").addClass("active");
		}else if(num == '3'){
			$("#report").addClass("active");
	    }else{
			$("#manager").addClass("active");
		}
	}
	
	function newCrs() {
		$(".inline").colorbox({
			inline : true,
			width : "48%"
		});
	}
	
	function updatePic(c) {
		var crs = "#hiddenPic" + c;
		console.log($(crs).html());
		$("#showChangePic").html($(crs).html());
		$(".inline").colorbox({
			inline : true,
			width : "60%"
		});
	}
	
	function cancel() {
		$(".inline").colorbox.close();
	}

	function cancelPic() {
		$(".inline").colorbox.close();
		for(var i=0;i<5;i++){
			var id = "#imgChange" + i;
			var base = "#baseChange" + i;
			$(id).attr('src','');
			$(base).val('');
		}
		$("#sendDeletePic").attr("disabled",true);
	}
	

	function send() {
		var crs_name = $('#crs_name').val();
		var category = $("#category").val();
		var details = $("#details").val();
		var crs_pic_count = $("#count_pic").val();
		var crs_pic_base0 = $("#base0").val();
		var crs_pic_base1 = $("#base1").val();
		var crs_pic_base2 = $("#base2").val();
		var crs_pic_base3 = $("#base3").val();
		var crs_pic_base4 = $("#base4").val();
		
		
		swal({
			  title: "確定新增課程?",
			  text: "Are you sure to add the course?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "不了!",
			  confirmButtonText: "確認新增!",
			  closeOnConfirm: false,
			  closeOnCancel: false
		
			},
			function(isConfirm){
			  if (isConfirm) {

				  
				  $.ajax({
						url : '<%= request.getContextPath()%>/CCM/CourseManager.do',
						data : {
							crs_name : crs_name,
							category : category,
							details : details,
							crs_pic_count : crs_pic_count,
							crs_pic_base0 : crs_pic_base0,
							crs_pic_base1 : crs_pic_base1,
							crs_pic_base2 : crs_pic_base2,
							crs_pic_base3 : crs_pic_base3,
							crs_pic_base4 : crs_pic_base4,
							action : 'courseInsert'
						},
						type : "POST",
						dataType : 'text',

						success : function(msg) {
							if(msg.trim() == 'success'){
								$(".inline").colorbox.close();
								swal("新增成功!", "Your course has been added.", "success");
								setTimeout(function(){ dropdown(1); }, 1000);
							}else{
								sweetAlert(msg + "!","","error");
							}
						},

						error : function(xhr, ajaxOptions, thrownError) {
							sweetAlert("Oops...", "請檢查網路狀態!", "error");
						}
					});
				  } else {
				    swal("取消新增", "You stop this action", "error");
				  }
			});
	}
	
	function sendChangePic() {
		if(parseInt($("#showChangePic img").length) + parseInt($("#crs_pic_countChange").val()) > 5){
			swal("總圖片不可超過5張", "Your images are over the limit", "error");
			return;
		}
		
		var crs_no = $("#showChangePic div").html();
		var crs_pic_count = $("#crs_pic_countChange").val();
		var crs_pic_base0 = $("#baseChange0").val();
		var crs_pic_base1 = $("#baseChange1").val();
		var crs_pic_base2 = $("#baseChange2").val();
		var crs_pic_base3 = $("#baseChange3").val();
		var crs_pic_base4 = $("#baseChange4").val();
		
		console.log(crs_pic_count);
		swal({
			  title: "確定新增圖片?",
			  text: "Are you sure to add the image?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "不了!",
			  confirmButtonText: "確認新增!",
			  closeOnConfirm: false,
			  closeOnCancel: false
		
			},
			function(isConfirm){
			  if (isConfirm) {
	  
				  $.ajax({
						url : '<%= request.getContextPath()%>/CCM/CourseManager.do',
						data : {
							crs_no : crs_no,
							crs_pic_count : crs_pic_count,
							crs_pic_base0 : crs_pic_base0,
							crs_pic_base1 : crs_pic_base1,
							crs_pic_base2 : crs_pic_base2,
							crs_pic_base3 : crs_pic_base3,
							crs_pic_base4 : crs_pic_base4,
							action : 'course_picInsert'
						},
						type : "POST",
						dataType : 'text',

						success : function(msg) {
							swal({
								  title: "Please wait...",
								  text: "正在傳送圖片",
								  timer: 1000,
								  showConfirmButton: false
								});
 	 	 					setTimeout(function(){ dropdown(1); }, 1000);
						},

						error : function(xhr, ajaxOptions, thrownError) {
							sweetAlert("Oops...", "請檢查網路狀態!", "error");
						}
					});				
				  	swal("新增成功!", "Your course has been added.", "success");
				  } else {
				    swal("取消新增", "You stop this action", "error");
					setTimeout(function(){ dropdown(1); }, 1000);
				  }
			});
		
			
	}
	
	function sendDeletePic() {
		console.log(parseInt($("#showChangePic img").length));
		var num = parseInt($("#showChangePic img").length);
		for(var i=0;i<num;i++){
			var status ="#showChangePic #picStatus" + i;
			if($(status).css("opacity") == '0.5'){
				$("#deletePicAppend").append("<div id=dp" + i + ">" + $(status).attr("class") + "</div>");
				$("#deletePicCount").val(i+1);
			}
		}
		
		var crs_pic_no0 = $("#dp0").html();
		var crs_pic_no1 = $("#dp1").html();
		var crs_pic_no2 = $("#dp2").html();
		var crs_pic_no3 = $("#dp3").html();
		var crs_pic_no4 = $("#dp4").html();
		var deletePicCount = $("#deletePicCount").val();
		swal({
			  title: "確定刪除圖片?",
			  text: "Are you sure to delete the image?",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "不了!",
			  confirmButtonText: "確認新增!",
			  closeOnConfirm: false,
			  closeOnCancel: false
		
			},
			function(isConfirm){
			  if (isConfirm) {

				  
				  $.ajax({
		 				url : '<%= request.getContextPath()%>/CCM/CourseManager.do',
		 				data : {
		 					crs_pic_no0 : crs_pic_no0,
		 					crs_pic_no1 : crs_pic_no1,
		 					crs_pic_no2 : crs_pic_no2,
		 					crs_pic_no3 : crs_pic_no3,
		 					crs_pic_no4 : crs_pic_no4,
		 					deletePicCount : deletePicCount,
		 					action : 'course_picDelete'
		 				},
		 				type : "POST",
		 				dataType : 'text',

		 				success : function(msg) {
		 					swal("刪除成功!", "Your course has been added.", "success");
 	 	 					setTimeout(function(){ swal.close(); 
 	 	 					for(var i=0;i<num;i++){
 	 	 						var status ="#showChangePic #picStatus" + i;
 	 	 						if($(status).css("opacity") == '0.5'){
 	 	 							$(status).remove();
 	 	 					}
 	 	 					$("#sendDeletePic").attr("disabled",true);}}, 1500);
		 				},

		 				error : function(xhr, ajaxOptions, thrownError) {
		 					alert("您老請求失敗啦~請檢查網路兒");
		 				}
		 			});		
				  } else {
				    swal("取消刪除", "You have stoped this action", "error");
					setTimeout(function(){ dropdown(1); }, 1000);
				  }
			});
		
		$("#deletePicAppend div").remove();


	}

	function dropdown(num) {
		var btn = "#dropdown" + num;
		$(btn).click();
	}
	
	function slide(count) {
		var slide = ".selectStu" + count;
		$(slide).slideToggle();
	}
	
	function clearPic(){
		$("#imgDIV").html("");
		$("#imgChangeDIV").html("");
	}
	
	function addPic(){
		for(var i=0;i<5;i++){
			var id = "#img" + i;
			var base = "#base" + i;
			$(id).attr('src','');
			$(base).val('');
		}
		var aa = document.getElementsByClassName("aa");
		var count = aa.length;
		if(count>5){
			count = 5;
		}
		for(var i=0;i<count;i++){
			var id = "#img" + i;
			var base = "#base" + i;
			$(id).attr('src',aa[i].src);
			$(base).val(aa[i].src);
		}
		$("#count_pic").val(aa.length);
		clearPic();
		$("#resetFileImg").click();
	}
	
	function addChangePic(){
		for(var i=0;i<5;i++){
			var id = "#imgChange" + i;
			var base = "#baseChange" + i;
			$(id).attr('src','');
			$(base).val('');
		}
		var bb = document.getElementsByClassName("bb");
		var count = bb.length;
		if(count>5){
			count = 5;
		}
		for(var i=0;i<count;i++){
			var id = "#imgChange" + i;
			var base = "#baseChange" + i;
			$(id).attr('src',bb[i].src);
			$(base).val(bb[i].src);
		}
		$("#crs_pic_countChange").val(bb.length);
		clearPic();
		$("#resetFileImg").click();
	}
	
	function dpic(count){
 		var status ="#showChangePic #picStatus" + count;
		if($(status).css("opacity") == '1'){
			$(status).css("opacity","0.5");
		}else{
			$(status).css("opacity","1");
		}
		var cc = 0;
		for(var i=0;i<parseInt($("#showChangePic img").length);i++){
			var pic = "#showChangePic #picStatus" + i;
			if($(pic).css("opacity") == '0.5'){
				cc++;
			}
		}
		
		if(cc > 0){
			$("#sendDeletePic").attr("disabled",false);
		}else{
			$("#sendDeletePic").attr("disabled",true);
		}
	}
	
	
	function deleteCrs(){
		swal({
			  title: "確定刪除課程?",
			  text: "You will not be able to recover this!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "不了!",
			  confirmButtonText: "確認刪除!",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			
			function(isConfirm){
			  if (isConfirm) {
	 			swal("刪除成功!", "Your course has been deleted.", "success");
	 			setTimeout(function(){ $("#deleteCrs").click(); }, 1000);

			  } else {
				swal("取消刪除", "Your course is safe :)", "error");
				setTimeout(function(){ swal.close(); }, 1500);
			  }
			});
	}
	
	function deleteCrstime(){
		swal({
			  title: "確定下架課程?",
			  text: "You will not be able to recover this!",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "不了!",
			  confirmButtonText: "確認下架!",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			
			function(isConfirm){
			  if (isConfirm) {
	 			swal("下架成功!", "Your course has been deleted.", "success");
	 			setTimeout(function(){ $("#deleteCrstime").click(); }, 1000);

			  } else {
				swal("取消下架", "Your course is safe :)", "error");
				setTimeout(function(){ swal.close(); }, 1500);
			  }
			});
	}
	
	
	function changeDisabled(value,count){
		
		var update = "update" + count;
		var updateCommit = "updateCommit" + count;
		var crsrow = ".changeCrs_name" + count;
		var drow = ".changeDetails" + count;
		var crow = ".changeCategory" + count;
		var cidrow = "#changeCategory" + count;
		var cdtrow = ".changeCrs_date_time" + count;
		var cprow = ".changePrice" + count;
		var dlrow = ".changeDeadline" + count;
		var pnrow = ".changeP_name" + count;
		var hctrow = ".hiddenCategory" + count;
		var hcrow = ".hiddenCrs_no" + count;
		var hprow = ".hiddenP_no" + count;
		var hctrow = ".hiddenCrs_time" + count;
		var pl = ".placeList" + count;
		var cpic = "changePic" + count;

　		if(value == '1'){
　			document.getElementById(update).style.display='none'; 
　			document.getElementById(updateCommit).style.display='block'; 
　			var cval = $(hctrow).html();
　			var select = "#changeCategory" + count + " option[value=" + cval + "]";
　			$(crsrow).html('<input type="text" id="changeCrs_name" value="' + $(crsrow).html() + '">');
　			$(drow).html('<input type="text" id="changeDetails" value="' + $(drow).html() + '">');
　			$(crow).html('<select name="category" id="changeCategory' + count + '"><option name="" value="A">瑜珈 </option><option name="" value="B">飛輪有氧</option><option name="" value="C">舞動有氧</option><option name="" value="D">拳擊有氧</option><option name="" value="E">基礎重訓</option><option name="" value="F">進階重訓</option><option name="" value="G">皮拉提斯</option><option name="" value="H">TRX肌力雕塑</option> <option name="" value="O">其他</option></select>');
　			$(select).attr('selected','selected');
　			for(var i=0;i<document.getElementsByClassName(cpic).length;i++){
	　			document.getElementsByClassName(cpic)[i].style.display='block'; 	
　			}
　		}else if(value == '2'){
　			document.getElementById(update).style.display='block'; 
　			document.getElementById(updateCommit).style.display='none'; 
　			var cids = cidrow + " :selected";
　			var changeCrs_name =  $("#changeCrs_name").val();
　			var changeDetails = $("#changeDetails").val();
　			var cidrow = $(cidrow).val();
　			
 			
　			swal({
　				  title: "是否要確認修改?",
 				  text: "You will not be able to recover this!",
 				  type: "warning",
 				  showCancelButton: true,
 				  confirmButtonColor: "#DD6B55",
 				  cancelButtonText: "不了!",
 				  confirmButtonText: "確認修改!",
 				  closeOnConfirm: false,
 				  closeOnCancel: false
 				},
 				function(isConfirm){
				  if (isConfirm) {
					  $.ajax({
		　	　				url : '<%= request.getContextPath()%>/CCM/CourseManager.do',
		 	 				data : {
		 	 					crs_no : $(hcrow).html(),	
		 	 					crs_name : changeCrs_name,
		 	 					details : changeDetails,
		 	 					category : cidrow,
		 	 					action : 'courseUpdate'
		 	 				},
		 	 				type : "POST",
		 	 				dataType : 'text',

		 	 				success : function(msg) {
		 	 					swal("修改成功!", "Your content has been changed.", "success");
		 	 					setTimeout(function(){ swal.close(); }, 1500);
		 	 				},

		 	 				error : function(xhr, ajaxOptions, thrownError) {
		 	 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
		 	 				}
		 	 			}); 
	 				  } else {
	 				    swal("取消修改", "Your content is safe :)", "error");
　						setTimeout(function(){ dropdown(1); }, 1000);
	 				  }
	 			});
　			
　			$(crsrow).html($("#changeCrs_name").val());
　			$(drow).html($("#changeDetails").val());
　			$(crow).html($(cids).text());
　			for(var i=0;i<document.getElementsByClassName(cpic).length;i++){
	　			document.getElementsByClassName(cpic)[i].style.display='none'; 	
　			}
　		}else if(value == '3'){
　			document.getElementById(update).style.display='none'; 
　			document.getElementById(updateCommit).style.display='block'; 
			var cts = $(hctrow).html();
　			var ctselect = "#changeCrs_time" + count + " option[value=" + cts + "]";
　			var pselect = $(hprow).html();
　			var select = "#place" + count + " option[value='" + pselect + "']";
　			var cd = $(cdtrow).html().substring(0,$(cdtrow).html().indexOf('-',10));
　			$(dlrow).html('<input type="text" id="changeDeadline' + count + '" value="' + $(dlrow).html() + '">');
　			$(cdtrow).html('<input type="text" id="changeCrs_date' + count + '" value="' + cd + '"> <select name="crs_time" id="changeCrs_time' + count + '"><option value="1">早上-1</option><option value="2">早上-2</option><option value="3">下午-1</option><option value="4">下午-2</option><option value="5">晚上-1</option><option value="6">晚上-2</option> </select>');
　			$(cprow).html('<input type="text" id="changePrice' + count + '" value="' + $(cprow).html() + '">');
　			$(pnrow).html($(pl).html().replace('class','id'));
　			$(ctselect).attr('selected','selected');
　			$(select).attr('selected','selected');
　			addDatepickerIndex(count);
　		}else{
　			document.getElementById(update).style.display='block'; 
　			document.getElementById(updateCommit).style.display='none'; 
　			var hctrow = ".hiddenCt_no" + count;
　			var cdd = "#changeDeadline" + count;
　			var ps = "#place" + count + " :selected";
　			var cts = "#changeCrs_time" + count + " :selected";
　			var ctd = "#changeCrs_date" + count;
　			var cp = "#changePrice" + count;
　			var ct_no = $(hctrow).html();	
			var	deadline = $(cdd).val();
			var crs_date = $(ctd).val();
			var	crs_time = $(cts).val();
			var	price = $(cp).val();
            var p_no = $(ps).val();
　			swal({
　				  title: "是否要確認修改?",
 				  text: "You will not be able to recover this!",
 				  type: "warning",
 				  showCancelButton: true,
 				  confirmButtonColor: "#DD6B55",
 				  cancelButtonText: "不了!",
 				  confirmButtonText: "確認修改!",
 				  closeOnConfirm: false,
 				  closeOnCancel: false
 				},
 				
 				function(isConfirm){
 				  if (isConfirm) {
 					 $.ajax({
 	　	　				url : '<%= request.getContextPath()%>/CCM/CourseManager.do',
 	 	 				data : {
 	 	 					ct_no : ct_no,	
 	 	 					deadline : deadline,
 	 	 					crs_date : crs_date,
 	 	 					crs_time : crs_time,
 	 	 					price : price,
 	 	                    p_no : p_no,
 	 	 					action : 'course_timeUpdate'
 	 	 				},
 	 	 				type : "POST",
 	 	 				dataType : 'text',

 	 	 				success : function(msg) {
 	 	 					swal("修改成功!", "Your content has been changed.", "success");
 	 	 					setTimeout(function(){ swal.close(); }, 1500);
 	 	 				},

 	 	 				error : function(xhr, ajaxOptions, thrownError) {
 	 	 					sweetAlert("Oops...", "請檢查網路狀態!", "error");
 	 	 				}
 	 	 			});
 				  } else {
 					swal("取消修改", "Your content is safe :)", "error");
　					setTimeout(function(){ dropdown(2); }, 1000);
 				  }
 				});

　	
　			$(dlrow).html($(cdd).val());
　			$(cdtrow).html($(ctd).val() + '-' + $(cts).text());
　			$(cprow).html($(cp).val());
　			$(pnrow).html($(ps).text());
　		}
　		
	}
	
	function addDatepickerIndex(c){
		var end = '#changeCrs_date' + c;
		var start = '#changeDeadline' + c;
		$(end).datepicker();
		  $(start).datepicker({
		    minDate: +3,
		    onSelect: function (dat, inst) {
		      $(end).datepicker('option', 'minDate', dat);
		    }
		  });
		$.datepicker.setDefaults({ dateFormat: 'yy-mm-dd' }); 
	}

	
	
</script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/CCM/JS/jquery.colorbox.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/CCM/CSS/colorbox.css" />
<style type="text/css">
#cboxContent, #cboxLoadedContent {
	border: 0.5px;
}

.btn {
	border-radius:6px;
}
</style>

