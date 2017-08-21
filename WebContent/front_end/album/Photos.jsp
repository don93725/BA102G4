<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="zh-ch-en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/album/css/jquery.fancybox.css">
<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">
<style type="text/css">
.shareContent img{
	width: 50%;
	height: 300px;
}
.cmt {
	height: 60px;
	padding-top: 20px;
	padding-left: 0px;
}

.cmt_mem_pic {
	height: 60px;
	width: 60px;
	margin-top: 2px;
	margin-bottom: 2px;
}

.cmtInfo {
	margin-top: 10px;
}

.cmtTime {
	color: gray;
}
div.album img {
	width: 100%;
	
}

div.addAlbum {
	position:relative;
	background-color: gray;
	height: 340px;	
	width: 860px;
	z-index: 0;
	
}

.test {
	height: 200px;
}

.imgDiv .delete {
	position: absolute;
	top: 0px;
	right: 0px;
	width: 50px;
	height: 50px;
	display: none;
	
}
#realShareBtn{
position: fixed;
top:0;
left:-138%;
width:350px;
height:150px;
size: 50px;
font-size:50px;
}
.progressCr{
	position: fixed;
	height: 340px;
	width:91%;
	background-color:gray;
	-webkit-filter:blur(80px);
	opacity :0.8;
	z-index: 1;
	display: none;
}
.progress {
	position: fixed;
	top: 40%;
	width:95%;
	z-index: 2;
	display: none;
	
}
.pht_cmt{
	position : fixed;
	z-index: 99999;
}
#pht_cmt{
	position : fixed;
	top:50%;
	width:27%;
	margin-right:30px;
	z-index: 1000000;
	color
}
#hidden-content-b {
  /* Custom styling */
  max-width: 50%;
  width: 50%;
  height: auto;
  border-radius: 4px;

  /* Custom transition - slide from top*/
  transform: translateY(-50px);
  transition: all .33s;
}
#hidden-content-a {
  /* Custom styling */
  max-width: 60%;
  width: 50%;
  height: auto;
  border-radius: 4px;
  /* Custom transition - slide from top*/
  transform: translateY(-50px);
  transition: all .33s;
}

.fancybox-slide--current #hidden-content-a {
  transform: translateY(0);
}
#cmt-container {
  position: fixed;
  top: 0;
  right: 0;
  width: 30%;
  height: 100%;
  z-index: 99991;
  background-color:blue;
  -webkit-tap-highlight-color: transparent;
  -webkit-backface-visibility: hidden;
  backface-visibility: hidden;
  display:none;
  -webkit-transform: translateZ(0);
  transform: translateZ(0); 
 }
#cmt-container .list-group-item{
	width:100%;
	
}
.alert-dismissable {
  padding-right: 35px;
  position: fixed;
  top:25%;
  left:0;
  right:0;
  margin-left:auto;
  margin-right:auto;
  width: 30%;
  font-size:50px;
  text-align: center;
  z-index: 1000000;
}
.alert-dismissable .close {
  position: relative;
  top: -5px;
  right: -21px;
  font-size:50px;
  color: inherit;
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

<body>
	<div class="container" style='min-height: 100%'>
				   <div class="breadcrumbs" id="breadcrumbs">            
			            <div class="col-lg-12">
			                <h1>相簿 <small>${album.al_name }</small></h1>
			            </div>
			
			            <ul class="breadcrumb">
			                <li>
			                    <i class="icon-home home-icon"></i>
			                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a></li>
			                     </li>
			                       <c:if test='${empty param.mem_rank}'>
	                	<li class="active"> <a href="<%= request.getContextPath()%>/front_end/editPage/personal.jsp?action=basic">個人空間 </a></li>
	                     </c:if>
	                     <c:if test='${not empty param.mem_rank}'>
	                	<li class="active"> <a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=${param.mem_rank}&mem_no=${param.mem_no}&action=lookPersonal">個人空間 </a></li>
	                     </c:if>
			                <li class="active"> <a href="<%= request.getContextPath()%>/album/AlbumsShowCtrl?mem_no=${album.mem_no.mem_no }">相簿</a></li>
			                <li class="active">${album.al_name }</li>
			            </ul><!-- .breadcrumb -->
			        </div>
			        <br>
			<%@include file="/front_end/include/fakeTab.file" %>
			<div class="col-xs-12 col-sm-12">
			<div class='row'>
			<div class="panel panel-default">
				<c:if test="${(not empty user&&param.mem_no==user.mem_no)&& album.al_board!=1 }">
				  <div class="panel-heading">
				 	<div class="row">
					<div class="col-xs-12 col-sm-8">
					<input type="button" class="btn btn-default btn-lg" id='allCheck' value="全選">
					<input type="button" class="btn btn-default btn-lg" id='choosePic' value="選取">
					<button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
					  新增照片
					</button>
					<input type="button" id='editPic' style="display: none;" class="btn btn-primary btn-lg" onclick="editPic()" value="編輯相片" >
					<input type="button" id='deletePic' style="display: none;" class="btn btn-danger btn-lg" onclick="return deletePic('${pageContext.request.contextPath}','${param.mem_no }','${param.al_no }','${thisPage }');" value="刪除照片" >
					</div>
					<div class="col-xs-12 col-sm-4 text-right" style="vertical-align: middle;">
				<button type="button" class="btn btn-default btn-lg" aria-label="Left Align" onclick="four();">
				<span class="glyphicon glyphicon-th-large"></span></button>
				<button type="button" class="btn btn-default btn-lg" aria-label="Left Align" onclick="six();">
				<span class="glyphicon glyphicon-th"></span>
				</div>
				</div>
				  </div>
				  </c:if>
				  	<div class="panel-body">				    
				 
				 
				<c:forEach var="photo" items="${photos }" varStatus="loop"> 
				<div class="col-xs-12 col-sm-2 album" style='padding: 2px;'>
				<div class="list-group">				
				<figure>
					<a href="${pageContext.request.contextPath}/util/OutputPic?photo_no=${photo.photo_no }&type=big" class='try' data-fancybox="group" >
					
					<div class="list-group-item">			
						<div class="row">						 						
				
							
							<img style="height:200px; width:100%" src="${pageContext.request.contextPath}/util/OutputPic?photo_no=${photo.photo_no }">									
						</div>						
					</div>
					
					</a>	
					</figure>							
					<div class="list-group-item text-center" style="padding-left: 0px ;height: 50px; ">								
						<div><input type="checkbox" name="photo_no" value='${photo.photo_no }' hidden><span><c:out value="${photo.photo_desc}" /></span></div>						
						<div style='display:none;'>
							<div class='col-lg-8' style='padding-right: 0px;'>
							<input type='text' class='form-control' name='photo_desc' >
							</div >
							<span class='input-group-btn' style='margin-left: 0px;' >
								<input type='button' class='btn btn-group-btn' onclick='update_photo_desc.call(this,"${pageContext.request.contextPath}","${param.mem_no }","${photo.al_no }","${photo.photo_no }","${thisPage }");' value='確認'>
							</span>
						</div>						
					</div>		
								

				</div>	
			</div>			
			<div id='picCmt${photo.photo_no}' style='display:none;'>
						<div class="panel panel-default" style="font-size: 20px; max-height: 103%; height: 103%;">
							  <div class="panel-heading" >留言 <button id='realShareBtn' class='btn btn-primary btn-lg' onclick='sharePhoto("${pageContext.request.contextPath}","${album.al_no}","${album.mem_no.mem_no}","${album.mem_no.mem_rank}","${album.mem_no.mem_nickname}","<fmt:setLocale value="en_US" /><fmt:formatDate value="${photo.ul_Date}" pattern="yyyy-MM-dd HH:mm" />","${photo.photo_no}");'>拜託分享我嘛</button>  </div>
							     <ul class="list-group pre-scrollable" style=' vertical-align:middle;max-height:88%; height:88%;'>
								    <c:if test="${empty photo.comments }">
								    	<li class="list-group-item text-center" style='height: 100%; font-size: 20px;'>							    		
								    	<div>目前尚無留言</div>
								    	</li>
								    	</c:if>
								    <c:forEach var="comment" items="${photo.comments }" varStatus="cmt">
								    
								    	
										<li class="list-group-item comments photo_key_${photo.photo_no}_<fmt:formatNumber type="number" value="${(cmt.index-cmt.index%8)/8 }" />" ${(cmt.count>8)? 'style="display:none"':'' }>
										<div class="row">
												
												<div class="col-xs-12 col-sm-2">
													<a href="${pageContext.request.contextPath}/PersonalPageCtrl?mem_no=${comment.mem_no.mem_no}&mem_rank=${comment.mem_no.mem_rank}">
													<img
														src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${comment.mem_no.mem_no}&mem_rank=${comment.mem_no.mem_rank}"
														class="img-circle cmt_mem_pic" title="${comment.mem_no.mem_nickname }" style='z-index: 10;'>
													</a>
												</div>
												
												<div class="col-xs-12 col-sm-8 cmt" >
													<span class='a' style='padding:50px; margin-top:50px; padding-right: 0px;'>${comment.bd_cmt_ctx }</span>
													<input type='text' class='b' value='${comment.bd_cmt_ctx }' style='display:none;' onfocus='this.value = this.value;'/>
													<span class='c' ><a href="#" style='margin-left: 3px' onclick='addPhotoCmtLikes.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }");'><span class='cmtLikes'>${(comment.ifClick)?'收回讚':'讚'}</span><span style='margin-left: 5px;'>${(comment.cmt_likes>0)? comment.cmt_likes:''  }</span></a></span>
										
												</div>
												<div class="col-xs-12 col-sm-2 cmt">
												<c:if test="${user.mem_no==comment.mem_no.mem_no }">
												<a href='#' onclick='editPhotoCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }");' style='color:black'>
													<span class='glyphicon glyphicon-pencil'></span></a>
													&nbsp&nbsp&nbsp&nbsp&nbsp
												<a href='#' onclick='delPhotoCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }","${comment.mem_no.mem_no}");' style='color:black'>
													<span class='glyphicon glyphicon-remove'></span></a>
												</c:if>
												
											</div>
										</div>	
										</li>
										</c:forEach>
										<c:if test="${not empty photo.comments&& fn:length(photo.comments)>7}">			
										<li class="list-group-item text-center" style='height: 60px;'><a href="#"
											onclick="showMore.call(this,event,'${photo.photo_no}','${fn:length(photo.comments)}');">顯示更多</a>
											<input type='hidden' id='count${photo.photo_no}' value=1>
											</li>
										</c:if>
																	    
								  </ul>
								  <div>
								 		 <li class="list-group-item">
											<div class="input-group">
												<input type="text" class="form-control" placeholder="${(not empty user)? "留些什麼吧":"請先登入會員" }" ${(not empty user)?"":"disabled"}>
												<span class="input-group-btn">
													<button class="btn btn-default ${(not empty user)?"":"disabled"}" ${(not empty user)?"":"disabled"} type="button" onclick='sendPhotoComments.call(this,"${pageContext.request.contextPath}","${user.mem_no }","${photo.photo_no}");'>送出</button>
												</span>
											</div>
										</li>
								  </div>
							</div>
							</div>
				</c:forEach>		
		

				<!-- Button trigger modal -->											
				<div class="col-xs-12 col-sm-12 text-center">
				
	<jsp:include page="/front_end/comm/ChangePage.jsp"></jsp:include>
				 
  				</div>
					</div>

				</div>
				</div>
				</div>
				</div>
				


<!-- Modal -->
<div class="modal fade bs-example-modal-lg" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" >
  <div class="modal-dialog modal-lg" role="document">
    <div class="modal-content ">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
        
		<h4 class="modal-title" id="myModalLabel">新增照片</h4>
        <div class="col-xs-12 col-sm-2 col-sm-push-10">
        
         
        </div>
      </div>
      <div class="modal-body">
 		<div class='progressCr' >

</div>  
<div class="progress text-center">
<div id="upload_progress" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100">
</div>
</div>   	
        	<div class="inline">
					<div class="inline_content " >
						
						<form action="" method="get" class="album_form" >
						<input type="file" class="photo" id='photo' accept='image/*' style="display: none;" name="photo" multiple>
						<div class="col-xs-12 col-sm-12 pre-scrollable addAlbum" ondragover="javascript: dragHandler(event);" ondrop="javascript: drop_image(event);" id="drop_image" >

						</div>
			
						
						

						<div class="text-center">
						<input type='button' class="btn btn-primary btn-lg" id='upload' value="上傳照片">
							<button type="reset" class="btn btn-default btn-lg" id='picReset'>清除所有</button>
						</div>
					</form>
					</div>
				</div>
      </div>
      <div class="modal-footer">

      	<button type="button" class="btn btn-default" data-dismiss="modal">關閉</button>
        <button type="button" onclick="uploadPhotos('${pageContext.request.contextPath}','${param.mem_no }','${param.al_no }','${thisPage }');" class="btn btn-primary">送出</button>
        </div>
        
      </div>
    </div>
  </div>
</div>

			
			
			
				
		</div>



<!-- 分享 -->
<a data-fancybox data-src="#hidden-content-b" href="javascript:;" id='shareBtn' style='display:none;'>share</a>
 <div style="display: none;" id="hidden-content-b">
<div class="modal-content" id='shareContainer' style='width:100%;'>
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">分享相片至動態</h4>
      </div>
      <div class="modal-body">
        <textarea class="form-control" id='shareText' rows="3" style="resize: none;">
        	
        </textarea>
      </div>
      <div class="modal-footer">
      
      <a href="#" id='shareLink' target="_blank" >
        <div class="panel panel-default">
		  <div class="panel-heading text-left">
		 		<div class="row">
									<div class="col-xs-12 col-sm-1">

										<img id='sharePic'
											src=""
											class="img-circle cmt_mem_pic">

									</div>
									<div class="col-xs-12 col-sm-11">
										<div class="col-xs-12 col-sm-12 cmtInfo" id='shareNickname'>
											名字
										</div>
										<div class="col-xs-12 col-sm-12 cmtInfo cmtTime" id='shareTime'>
											時間									

										</div>

									</div>
									</div>
		  </div>
		  <div class="panel-body shareContent">
		  		
		    	<div class="col-xs-12 col-sm-12 text-left">
		    		<img  id='shareIMG' src="">
		    	</div>
		    	
		  
		  </div>
		</div>
      </a>
      </div>
      <div class="modal-footer">
      	<div class="col-xs-12 col-sm-2 col-sm-offset-9" style="padding-left: 80px;" >
      		<div class="dropup">
					<button class="btn btn-default btn-lg dropdown-toggle" type="button"
						id="dropdownMenu2" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true" value="0">
						隱私 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu2">
						<li><a href="#"
							onclick="chooseCmmtPrvt.call(this,'0');">朋友&nbsp</a></li>
						<li><a href="#"
							onclick="chooseCmmtPrvt.call(this,'1');">公開&nbsp</a></li>
						<li><a href="#"
							onclick="chooseCmmtPrvt.call(this,'2');">本人&nbsp</a></li>
					</ul>
				</div>
      	</div>
      	<div class="col-xs-12 col-sm-1" style="padding-left: 0px;">
        <button type="button" class="btn btn-primary btn-lg" onclick='shareSubmit("${pageContext.request.contextPath}","${param.mem_no }");'>分享</button></div>
      	
      </div>
    </div>
  </div>
  
  
  
  <!-- 123 -->
     <a data-fancybox data-src="#hidden-content-a" href="javascript:;" id='shareBtn' class="btn">Open demo</a>
  <div style="display: none;" id="hidden-content-a">
		<div class="modal-content" >
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">檢舉照片</h4>
      </div>
      <div class="modal-body">
       <label class="col-sm-2 control-label">檢舉類型</label>
      <select class="form-control">
			<option value="0">涉及歧視</option>				
			<option value="1">含十八禁</option>
			<option value="2">人身攻擊</option>
			<option value="3">政治問題</option>
			<option value="4">其他</option>
	 </select>
	 	<label class="col-sm-2 control-label">檢舉原因</label>
        <textarea class="form-control" rows="3" style="resize: none;">
        	
        </textarea>
      </div>

      <div class="modal-footer">

        <button type="button" class="btn btn-primary btn-lg">送出</button></div>
      
    </div>
  </div>

<!-- 分享 end -->

<!-- test -->

	<script src="https://code.jquery.com/jquery.js"></script>
	<script src='${pageContext.request.contextPath}/front_end/album/js/jquery.ajax-progress.js'></script>	
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="${pageContext.request.contextPath}/front_end/album/js/jquery.fancybox.js"></script>
	<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>	
	<script type="text/javascript">
	function sharePhoto(path,al_no,mem_no,mem_rank,mem_nickname,ul_date,photo_no){
		$('#sharePic').attr('src',path+'/util/OutputPic?mem_no='+mem_no+"&mem_rank="+mem_rank);
		$('#shareIMG').attr('src',path+'/util/OutputPic?photo_no='+photo_no);
		$('#shareNickname').text(mem_nickname+" 發佈的相片");
		$('#shareTime').text(ul_date);
		$('#shareLink').attr('href',path+'/album/PhotosShowCtrl?al_no='+al_no+"&mem_no="+mem_no);
		$('#shareBtn').trigger('click');
		
		
	}
	function shareSubmit(path,mem_no){
		var content = $('#shareContainer').find('.modal-footer').html();
		var bd_msg_ctx = $('#shareText').val().trim();
		var bd_prvt = $('#dropdownMenu2').val();
		$.ajax({
			type : "POST",
			url : path + "/board/BoardActionCtrl?action=ref_board&mem_no="+mem_no,
			dataType : 'text',
			data: {
				bd_ref_ctx : content,
				bd_type:"4",
				bd_msg_ctx: bd_msg_ctx,
				bd_prvt: bd_prvt
				
			},
			success : function(msg) {
				if (msg.length == 0) {
					swal({
					  title: "成功",
					  text: "已成功分享動態",
					  timer: 1000,
					  type: "success",
					  showConfirmButton: false
					},function(){
						swal.close();
						$('.fancybox-close-small').click();	
					});
					
				} else {						
					$.each(JSON.parse(msg),function(v,i){
						swal({
							  title: "輸入錯誤",
							  text: i,
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
						});					

					});

					
					
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				swal({
					  title: "輸入錯誤",
					  text: "請再嘗試試看看",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
			}

		});
	}
	function chooseCmmtPrvt(bd_prvt) {
		$(this).parents('ul').prev().html(
				$(this).text() + '<span class="caret"></span>');
		$(this).parents('ul').prev().val(bd_prvt);

	}
	function addPhotoCmtLikes(event,path,bd_cmt_no,mem_no){
		event.preventDefault();
		var span = $(this).find('.cmtLikes');
		var action ;
		if(span.text()=='讚'){
			action = 'addCmtLikes';
		}else{
			action = 'negativeCmtLikes';
			
		}
		$.ajax({
			type : "POST",
			url : path + "/all/CommentsCtrl?action="+action+"&mem_no="+mem_no,
			dataType : 'text',
			data: "cmt_type=1&bd_cmt_no="+bd_cmt_no,
			success : function(msg) {
				if (msg.length == 0) {
					var num = parseInt(span.next().text(),10);
					if(num==undefined||num==null||span.next().text().length==0){
						num=0;
					}
					
					if(span.text()=='讚'){
						span.text('收回讚');
						span.next().text(num+1);
					}else{
						span.text('讚');	
						if(num-1>0){
							span.next().text(num-1);
							
						}else{
							span.next().text('');
						}
					}
					
				} else {						
					$.each(JSON.parse(msg),function(v,i){
						swal({
						  title: "輸入錯誤",
						  text: i,
						  timer: 1000,
						  type: "error",
						  showConfirmButton: false
						});
					});
					
					
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				swal({
					  title: "輸入錯誤",
					  text: "請再嘗試看看",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
			}

		});
		
	}
	function delPhotoCmmt(event,path,bd_cmt_no,mem_no){
		event.preventDefault();
		var _self = $(this).parents('.comments');
		var _CommtNum = $(this).parents('.list-group').prev().find('.badage');
		if(confirm('你確定要很獨裁的刪除此筆留言嗎？')){
			$.ajax({
				type : "POST",
				url : path + "/all/CommentsCtrl?action=delete&mem_no="+mem_no,
				dataType : 'text',
				data: "cmt_type=1&bd_cmt_no="+bd_cmt_no,
				success : function(msg) {
					if (msg.length == 0) {
						_self.remove();
						var num = parseInt(_CommtNum.text(),10);
						if(num-1!=0){
							_CommtNum.text(num-1);							
						}else{
							_CommtNum.text('');
						}
					} else {						
						$.each(JSON.parse(msg),function(v,i){
							swal({
							  title: "輸入錯誤",
							  text: i,
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
							});
						});
						
						
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					swal({
						  title: "輸入錯誤",
						  text: "請再嘗試看看	",
						  timer: 1000,
						  type: "error",
						  showConfirmButton: false
						});
				}

			});
		}
	}
	function editPhotoCmmt(event,path,bd_cmt_no,mem_no){
		event.preventDefault();
		var val = $(this).parent().prev().children().text();
		var clazz = $(this).children().attr('class');
		var content = $(this).parent().prev();
		if(clazz=='glyphicon glyphicon-pencil'){
			$(this).children().removeClass();
			$(this).children().addClass('glyphicon glyphicon-ok');
			$(this).children().css("color","green");
			content.find('.a').css("display", "none");
			content.find('.b').css("display", "");
			content.find('.b').focus();
			
		}else{
			submitEditCmt(path,mem_no,bd_cmt_no,content.find('.b').val(),$(this).children(),content);
			
		}
	}
	function submitEditCmt(path,mem_no,bd_cmt_no,val,ch,content){
		var result;
		$.ajax({
			type : "POST",
			url : path + "/all/CommentsCtrl?action=update&mem_no="+mem_no,
			dataType : 'text',
			data: "bd_cmt_no="+bd_cmt_no+"&bd_cmt_ctx="+val,
			success : function(msg) {
				if (msg.length == 0) {
					ch.removeClass();
					ch.addClass('glyphicon glyphicon-pencil');
					ch.css("color","black");
					content.find('.a').css("display", "");
					content.find('.b').css("display","none");
					content.find('.a').text(content.find('.b').val());
					swal({
						  title: "成功",
						  text: "已成功編輯留言",
						  timer: 1000,
						  type: "success",
						  showConfirmButton: false
					});
				} else {						
					$.each(JSON.parse(msg),function(v,i){
						swal({
						  title: "輸入錯誤",
						  text: i,
						  timer: 1000,
						  type: "error",
						  showConfirmButton: false
						});
					});
					result = false;
					
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {

				swal({
					  title: "輸入錯誤",
					  text: "請再次嘗試看看",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
				result = false;
			}

		});
		return result;
	}
	function showMore(event,bd_msg_no,size){
		event.preventDefault();
		var cmt = $(this).parent().parent().children(".comments");
		var count = parseInt( $(this).next().val(),10);
		if(count+1>(size-size%5)/5){
			$(this).hide();
		}
			$(".photo_key_"+bd_msg_no+"_"+count).css('display','block');
			
			$(this).next().val(count+1);
		
	}
	function sendPhotoComments(path, mem_no, photo_no){
		var val = $(this).parent().prev().val();	
		$.ajax({
			type : "POST",
			url : path + "/all/CommentsCtrl?action=insert&mem_no="+mem_no,
			dataType : 'text',
			data: "cmt_type=1&org_no="+photo_no+"&bd_cmt_ctx="+val,
			success : function(msg) {
				if (msg.length == 0) {
					swal({
						  title: "成功",
						  text: "已成功發布動態",
						  timer: 1000,
						  type: "success",
						  showConfirmButton: false
					},function(){
						location.reload();
					});
					
				} else {						
					$.each(JSON.parse(msg),function(v,i){
						swal({
						  title: "輸入錯誤",
						  text: i,
						  timer: 1000,
						  type: "error",
						  showConfirmButton: false
						});
					});
					
					
				}
			},
			error : function(xhr, ajaxOptions, thrownError) {
				swal({
					  title: "輸入錯誤",
					  text: "請再嘗試看看",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
			}

		});
	}
	function report(){
		$('#rptBtn').trigger('click');
	}
	function makePhotoCmmt(){
		$(this).prev().val();
	}

	function four(){
		$('.album').removeClass('col-sm-1');
		$('.album').addClass('col-sm-2');	
		$('.album img').css("height","200px");

	}
	function six(){
		$('.album').removeClass('col-sm-2');
		$('.album').addClass('col-sm-1');
		$('.album img').css("height","120px");
	}
	function uploadPhotos(path,mem_no,al_no,thisPage){
		var num=0;
		var data = new FormData();
		var upload_progress = $('#upload_progress');
		$('.progress').css('display','block');
		$('.progressCr').css('display','block');
		upload_progress.html('') ; 
    	upload_progress.css("width",'') ;                 
    	upload_progress.attr('aria-valuenow', '') ;
		$.each(fileList,function(){
	
			data.append("image",$(this)[0]);
			data.append("photo_desc",$("input[name=photo_desc][id="+$(this)[1]+"]").val());

		});
		$.ajax({
            type: "POST",
            url: path+"/album/PhotosActionCtrl?action=insert&mem_no="+mem_no+"&al_no="+al_no,
            dataType:'text',
            contentType: false,
            processData: false, //不做任何處理，只上傳原始資料
            data: data,
            progress: function(e) {
                //make sure we can compute the length
                if(e.lengthComputable) {
                	var intComplete = (e.loaded / e.total) * 100 | 0 ;                    
                	upload_progress.html(intComplete + '%') ; // 控制進度條的顯示數字，例如65%
                	upload_progress.css("width",intComplete + '%') ; // 控制進度條的長度                        
                	upload_progress.attr('aria-valuenow', intComplete) ;
                }
                //this usually happens when Content-Length isn't set
                else {
                    console.warn('Content Length not reported!');
                }
            },
            success: function(msg){
            	
				if(msg.length==0){	
					upload_progress.html(100 + '%') ; // 控制進度條的顯示數字，例如65%
                	upload_progress.css("width",100 + '%') ; // 控制進度條的長度                        
                	upload_progress.attr('aria-valuenow', 100) ;
	        		
	        		$('#picReset').trigger('click');
	        			
	        		$('.progress').css('display','none');
	        		$('.progressCr').css('display','none');
	        		swal({
						  title: "成功",
						  text: "已成功上傳照片",
						  timer: 1000,
						  type: "success",
						  showConfirmButton: false
					},function(){
	                	location.href ="PhotosShowCtrl?mem_no="+mem_no+"&al_no="+al_no+"&thisPage=1";						
					});
                	
             	}else{
             		$.each(JSON.parse(msg),function(v,i){
    					swal({
    					  title: "輸入錯誤",
    					  text: i,
    					  timer: 1000,
    					  type: "error",
    					  showConfirmButton: false
    					});
    				});
             	}
            },

             error:function(xhr, ajaxOptions, thrownError){ 
            	 swal({
					  title: "輸入錯誤",
					  text: "請再嘗試看看",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
            }
           
        });
// 		xhr = new XMLHttpRequest();
//         xhr.upload.onprogress = function(e) {
//            // it will never come inside here
//            alert('ha');
//            if(e.lengthComputable) {
//                //calculate the percentage loaded
//                var pct = (e.loaded / e.total) * 100;

//                //log percentage loaded
//                console.log(pct);
//            }
//            //this usually happens when Content-Length isn't set
//            else {
//                console.warn('Content Length not reported!');
//            }
//         }
//         xhr.open("POST", path+"/album/PhotosActionCtrl?al_no="+al_no , true);
//         xhr.send(data);

//         xhr.onreadystatechange = function(){
//             if(xhr.readyState === 4 && xhr.status === 200){
//                 console.log(xhr.responseText);              
//             }
//         }		
		
		


		
	}
	function dragHandler(e){
	    e.preventDefault() ; //防止瀏覽器執行預設動作

	}
	function drop_image(e){
	    e.preventDefault() ; //防止瀏覽器執行預設動作

	    var files  = e.dataTransfer.files ; //擷取拖曳的檔案
	    for(var i =0 ; i < files.length ; i++){
	    	$.each(files, function(index,file){
	    		if (!file.type.match('image')){
	    			alert('這又不是圖片..');
	    			return;
	    		}
	    		
				var reader  = new FileReader();
				
				//var src = URL.createObjectURL(file);
				var ph = new Image();
				var rate ;
				var width =0; 
				reader.onload = function(){
					ph.src = reader.result;
					fileList[count] = [file,count];
					ph.onload = function(){
					var pic="<div class='col-xs-12 col-sm-3 pic'>"+
					"<div class='list-group'><div class='list-group-item imgDiv'>"+
					"		<img src='"+webCtx+"/front_end/album/images/cross1.png' id='"+count+"' class='delete' onclick='Preview.removePic.call(this);'/>"+
					"<div align='center'><img alumb='true' class='pics' style='width:100%;' height=100  src='"+ph.src+"'>"+"</div></div><div class='list-group-item'><div class='input-group col-lg-20' style='padding-right: 0px;' >"+	
					"<span class='input-group-addon' style='paddin:0px;margin-left: 0px;' >描述"+
					"</span><input type='text' class='form-control' id='"+(count-1)+"' name='photo_desc' >"+
					"</div ></div></div>";


					root.find(".addAlbum").append(pic);
					$(".imgDiv").mouseenter(function () {
    			 	$(this).find(".delete").show();

        				});   
            
    				$(".imgDiv").mouseleave(function () {
        				$(this).find(".delete").hide();	            				
    				});		

					}
					
				}
				if(file){
					reader.readAsDataURL(file);
				}
					

					});
				}
	    }		
	$(function (){	
					$('#albumTab').addClass('active');
										
					$('#dropdownMenu2').click(function(){
						$(this).parent().addClass('open');
					});
					$('#choosePic').click(function(){
						
							$('input[name=photo_no]').toggle();
							$('#editPic').toggle();
							$('#deletePic').toggle();																		
					})
					$('#allCheck').click(function(){						
						if(flag==1){
							$('input[name=photo_no]').prop('checked',true);
							flag=0;
						}else{
							$('input[name=photo_no]').prop('checked',false);	
							flag=1;	
						}
					
					})
						

				})

				function editPic(){

					if($('input[name=album_no]:checked').length>1){
						alert('請不要選擇多個');
						return false;
					}else if($('input[name=photo_no]:checked').length==0){
						alert('請至少選擇一個');
						return false;
					}else{
						
						$.each($('input[name=photo_no]:checked'),function(){
							var val= $(this).next('span').text();
							$(this).parent().hide();
	 						$(this).parent().next('div').show();
	 						$(this).parent().next('div').find('input[type=text]').val(val);
	 						
						});
// 						$('input[name=photo_no]:checked').parent().hide();
// 						$('input[name=photo_no]:checked').parent().next('div').show();
// 						$('input[name=photo_no]:checked').parent().next('div').find('input[type=text]').val(val);
					}
				}
				function update_photo_desc(path,mem_no,al_no,photo_no,thisPage){
					var val = $(this).parent().prev().find('input[type=text]').val();
					var self = $(this);
					 $.ajax({
			                url: path+"/album/PhotosActionCtrl?mem_no="+mem_no+"&al_no="+al_no+"&photo_no="+photo_no+"&thisPage="+thisPage,
			                data: {	
			                	action:"update",			             
			                	photo_desc:val
			                },
			                
			                type:"POST",
			                dataType:'text',
			                success: function(msg){
			                    if(msg.length==0){
			                    	swal({
										  title: "修改成功",
										  text: "已成功修改照片描述。",
										  timer: 1000,
										  type: "success",
										  showConfirmButton: false
									});
			                    	self.parent().parent().prev().children('span').text(val);	
			                    	
			                    }else{
			                    	//報錯啊
			                    	$.each(JSON.parse(msg),function(v,i){
			        					swal({
			        					  title: "輸入錯誤",
			        					  text: i,
			        					  timer: 1000,
			        					  type: "error",
			        					  showConfirmButton: false
			        					});
			        				});
			                    }
			                },

			                 error:function(xhr, ajaxOptions, thrownError){ 
		     					swal({
		     					  title: "發生錯誤",
		     					  text: "請稍後再嘗試。",
		     					  timer: 1000,
		     					  type: "error",
		     					  showConfirmButton: false
		     					});
			                 }
			            });
					
					$(this).parent().parent().prev().show();
					$(this).parent().parent().hide();                    			                    		
                    	

				}
				function deletePic(path,mem_no,al_no,thisPage){
					if($('input[name=photo_no]:checked').length==0){
						swal({
							  title: "請至少選擇一張",
							  text: "刪除照片至少要選擇一張才行。",
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
							});
						return false;
					}else{
							var photo_no = "";
							$.each($('input[name=photo_no]:checked') ,function(){
								photo_no = photo_no + "photo_no=" + $(this).val() +"&";
							});
							photo_no = photo_no.substring(0,photo_no.length-1);
							swal({
								  title: "確定要刪除照片?",
							 	  text: "刪除後將無法回復，請三思再做此舉。",
								  type: "warning",
								  showCancelButton: true,
								  confirmButtonColor: "#DD6B55",
								  cancelButtonText: "算了",
								  confirmButtonText: "是的",
								  closeOnConfirm: false
							},
							function(){
								$.ajax({
					                url: path+"/album/PhotosActionCtrl?mem_no="+mem_no+"&al_no="+al_no+"&thisPage="+thisPage,
					                data:   "action=delete&"+photo_no,				                
					                type:"POST",
					                dataType:'text',
					                success: function(msg){
					                    if(msg.length==0){	
					                    	swal({
												  title: "刪除成功",
												  text: "已成功刪除照片",
												  timer: 1000,
												  type: "success",
												  showConfirmButton: false
											},function(){
						                    	location.href =path + "/album/PhotosShowCtrl?mem_no="+mem_no+"&al_no="+al_no+"&thisPage="+thisPage;
											});
					                    }else{
					                    	//報錯啊
					                    	$.each(JSON.parse(msg),function(v,i){
					        					swal({
					        					  title: "輸入錯誤",
					        					  text: i,
					        					  timer: 1000,
					        					  type: "error",
					        					  showConfirmButton: false
					        					});
					        				});
					                    }
					                },

					                 error:function(xhr, ajaxOptions, thrownError){ 
					                	 swal({
					   					  title: "錯誤",
					   					  text: "請稍後再次嘗試看看。",
					   					  timer: 1000,
					   					  type: "error",
					   					  showConfirmButton: false
					   					});
					                 }
					            });						
							
							});
					}
				}


			var Preview = new function(){
				root = $('.album_form');
				count = 0;
				var picReset = $('#picReset');
				this.change_file = function(){

					root.on('change',".photo", function(){
						show(this);
						count = 0;
					});
				}
				var show = function(input){
					if(input.files && input.files[0]){
						each_img(input.files);
					}
				}
				this.removePic = function (){
					var id = $(this).attr('id');
					$(this).parent().parent().parent().remove();
					delete fileList[id];
				}

				var each_img = function(files){
					$.each(files, function(index,file){
						if (!file.type.match('image')){
							swal({
								  title: "上傳錯誤",
								  text: "這並不是圖片。",
								  timer: 1000,
								  type: "error",
								  showConfirmButton: false
							});
			    			return;
			    		}
						var reader  = new FileReader();
						
						//var src = URL.createObjectURL(file);
						var ph = new Image();
						var rate ;
						var width =0; 
						reader.onload = function(){
							ph.src = reader.result;
							ph.onload = function(){
							fileList[count] = [file,count];
							var pic="<div class='col-xs-12 col-sm-3 pic'>"+
							"<div class='list-group'><div class='list-group-item imgDiv'>"+
							"		<img src='"+webCtx+"/front_end/album/images/cross1.png' id='"+(count++)+"' class='delete' onclick='Preview.removePic.call(this);'/>"+
							"<div align='center'><img alumb='true' class='pics' style='width:100%;' height=100  src='"+ph.src+"'>"+"</div></div><div class='list-group-item'><div class='input-group col-lg-20' style='padding-right: 0px;' >"+	
							"<span class='input-group-addon' style='paddin:0px;margin-left: 0px;' >描述"+
							"</span><input type='text' class='form-control' id='"+(count-1)+"' name='photo_desc' >"+
							"</div ></div></div>";
   							
							root.find(".addAlbum").append(pic);
							$(".imgDiv").mouseenter(function () {
            			 	$(this).find(".delete").show();

		        				});   
		            
	        				$(".imgDiv").mouseleave(function () {
	            				$(this).find(".delete").hide();	            				
	        				});		

						}
							
							}
							if(file){
								reader.readAsDataURL(file);
							}
						
							});
				}

				var clean = function(){
					root.find(".addAlbum").empty();
					fileList=[];
				}
				picReset.click(function(){
					clean();
				});
				var reset = function(){

					root.find('.addAlbum').val(null);
				}
			}
			

			$(function(){
				
// 				$( '[data-fancybox=group]' ).fancybox({
					 
// 					clickSlide : false,				    
// 						caption : function( instance, item ) {
// 						  return $(this).find('figcaption').html();
// 				 }
// 				});
				$('#upload').click(function(){
					$('#photo').trigger('click');
				});
				Preview.change_file();
				var path = window.location.pathname;
			    webCtx = path.substring(0, path.indexOf('/', 1));
			})
			
			var root,count,webCtx;
			var fileList = [];
			
			
		</script>
 	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>