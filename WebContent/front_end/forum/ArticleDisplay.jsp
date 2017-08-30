<%@ page language="java" import="com.forum.domain.*,java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="zh-ch-en">
<head>

<meta content="Expires" content="-1">
<meta content="Catch-Control" content="no-cache">
<meta content="Pragma" content="no-cache">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/album/css/jquery.fancybox.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/forum/js/kindeditor/themes/default/default.css" />
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">
<style type="text/css">
.shareContent {
	margin-top: 2em;
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
</style>
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>


<div class="container" style="min-height: 100%;">
	<div class="row">
		  <div class="breadcrumbs" id="breadcrumbs">
            
            <div class="col-lg-12">
                <h1>文章
                    <small>${articles.art_name }</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a></li>
                     </li>
                <li class="active"><a href="${pageContext.request.contextPath}/forum/ForumCtrl">討論大廳</a></li>
                <li class="active"><a href="${pageContext.request.contextPath}/forum/ForumShowCtrl?forum_no=${param.forum_no }">${forum_name }</a></li>
                <li class="active">${articles.art_name }</li>
            </ul><!-- .breadcrumb -->
        </div>
		<div class="col-xs-12 col-sm-12" >
		<c:if test="${thisPage==1}">

		    	<div class="row">
		    		
		    	<div class="col-xs-12 col-sm-12" style="padding-left: 0px;">						    		
					  <div class="panel panel-default" style="margin-bottom: 0px;">
						  <table class="table table-bordered">
						  <tbody>
						  		<tr>	
									<td rowspan='3' width='100' style="vertical-align:middle;text-align:center;" align="center;"><img width=80 src='${pageContext.request.contextPath}/util/OutputPic?mem_no=${articles.mem_no.mem_no}&mem_rank=${articles.mem_no.mem_rank}'><br>
									<a href='${pageContext.request.contextPath}/forum/PersonalPageCtrl?mem_no=${articles.mem_no.mem_no}&mem_rank=${articles.mem_no.mem_rank}&action=lookPersonal'>${articles.mem_no.mem_nickname}</a><br>
									<small><c:if test="${articles.mem_no.mem_rank=='0'}">學員</c:if><c:if test="${articles.mem_no.mem_rank=='1'}">教練</c:if><c:if test="${articles.mem_no.mem_rank=='2'}">健身房</c:if>
									<c:if test="${not empty user }"></small>
									<br>
									<br>
									<button class='btn btn-primary' onclick='sharePhoto.call(this,event,"${pageContext.request.contextPath}","${articles.forum_no}","${articles.art_type}","${articles.art_name}","${articles.art_no}","${articles.mem_no.mem_no}","${articles.mem_no.mem_rank}","${articles.mem_no.mem_nickname}","${articles.art_add_date}");'>分享</button>
									</c:if>
									</td>
									<td colspan='2' style="background-color:#DDDDDD;">標題：&nbsp${articles.art_name}</td>
								</tr>
								<tr><td id='shareArt_ctx' valign="top">${articles.art_ctx}</td></tr>
								<tr><td style="text-align:right;">
									<c:if test="${(articles.mem_no.mem_no==user.mem_no)||user.mem_rank=='3'||user.mem_rank=='4'}">
									<a href="${pageContext.request.contextPath}/forum/ArticlesActionCtrl?action=goUpdatePage&forum_no=${param.forum_no }&art_no=${articles.art_no}"><button class='btn btn-info'><span class='glyphicon glyphicon-pencil'></span>&nbsp編輯</button></a>&nbsp
									<a href="${pageContext.request.contextPath}/forum/ArticlesActionCtrl?action=delete&forum_no=${param.forum_no }&art_no=${articles.art_no}" onclick="return del.call(this);"><button class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp刪除</button></a>&nbsp
									</c:if>
									<c:if test="${articles.mem_no.mem_no!=user.mem_no&&! empty user}">									
									<a class='inline' href="#inline_content"><button class='btn btn-warning'><span class='glyphicon glyphicon-exclamation-sign'></span>&nbsp檢舉</button></a>	</c:if>
									</td></tr>	
						  </tbody>
						  </table>
					
		    		</div>	
		  					
		</c:if>
						    		
<c:forEach var='comments' items='${article_comments }'>
				<div class="panel panel-default" style="margin-top:10px; margin-bottom: 0px;">
						<table class="table table-bordered">
							<tbody>
								<tr>	
									<td rowspan='3' width='100' style='text-align: center' valign="top"><img width=80 src='${pageContext.request.contextPath}/util/OutputPic?mem_no=${comments.mem_no.mem_no}&mem_rank=${comments.mem_no.mem_rank}'>
									<br>
									<a href='${pageContext.request.contextPath}/forum/PersonalPageCtrl?mem_no=${comments.mem_no.mem_no}&mem_rank=${comments.mem_no.mem_rank}&action=lookPersonal'>${comments.mem_no.mem_nickname}</a><br>
								<c:if test="${comments.mem_no.mem_rank=='0'}">學員</c:if><c:if test="${comments.mem_no.mem_rank=='1'}">教練</c:if><c:if test="${comments.mem_no.mem_rank=='2'}">健身房</c:if></td>
								
								<td valign="top">${comments.art_cmt_ctx }</td>
								</tr>
								<c:if test="${(articles.mem_no==user.mem_no)||user.mem_rank=='3'||user.mem_rank=='4'}">
								<tr>
								<td>
										<a href="${pageContext.request.contextPath}/forum/ArtCmtActionCtrl?action=delete&forum_no=${param.forum_no }&art_no=${articles.art_no}&art_cmt_no=${comments.art_cmt_no}" onclick="return delCmt.call(this);"><button class='btn btn-danger'><span class='glyphicon glyphicon-trash'></span>&nbsp刪除</button></a>
								</td>
								</tr>
								</c:if>
							</tbody>
						</table>
					</div>
</c:forEach>

<div class="col-xs-12 col-sm-12 text-center">	
<jsp:include page="/front_end/forum/ChangePage.jsp"/>
</div>
<c:if test="${! empty user }">
<div class="col-xs-12 col-sm-12" style="padding-left: 0px;">	
	<div class="panel panel-default" style="margin-top:10px; margin-bottom: 0px; padding: 0px;">
		<form id='cmmtForm' action="${pageContext.request.contextPath}/forum/ArtCmtActionCtrl?forum_no=${param.forum_no}&art_no=${articles.art_no}&action=create" method="post" enctype='multipart/form-data'>
				<table class="table table-bordered" style=" margin-bottom: 0px;">
				<tbody>
					<tr>
						<td rowspan='2' width='100' align="center"">留言</td>
						<td align="top" style='padding: 0px;'>
						<div contentEditable="true" id='ctx' name='content' style='width:100%; height:250px;;'></div>
						<textarea id="my-textarea" name='art_cmt_ctx' style="display:none"></textarea>    
						<input type="file" id='file' name='file' style="display:none"/>
						</td>	
					</tr>
					<tr><td  align="center" ><input class='btn btn-primary btn-lg' type='button' name="getHtml"  value='送出'><input class='btn btn-default btn-lg' type='reset' value='重填'></td></tr>
				</tbody>
			</table>
		</form>	
	</div>
</div>
</c:if>
<c:if test="${ empty user }">
<div class="col-xs-12 col-sm-12" style="padding-left: 0px;">	
	<div class="panel panel-default" style="margin-top:10px; margin-bottom: 0px;">
		<table class="table table-bordered">
			<tbody>
				<tr>
					<td width='100' align="center">留言</td>
					<td width='600' height='100' align="center"><a href="${pageContext.request.contextPath}/LoginCtrl">請先登入</a></td>	
				</tr>
			</tbody>
		</table>
	</div>
</div>
</c:if>

				</div>
			</div>
		</div>
	</div>
</div>



<div style="display: none">
			<div id='inline_content' style='padding:10px; background:#fff;font-size: 30px;'>
			<form action="#" method="post">
			<table class="table">
						
						<thead><caption>文章檢舉</caption></thead>
						<tbody>
						<tr><td>
						<label >類型</label>
						<select name="rpt_type" id='rpt_type' style='height:50px;font-size: 30px;border-radius:10px;' >
							<option value="0">涉及歧視</option>				
							<option value="1">含十八禁</option>
							<option value="2">人身攻擊</option>
							<option value="3">政治問題</option>
							<option value="4">其他</option>
						</select>
						<label >檢舉原因</label>
						<input type="text" style='font-size: 30px;border-radius:10px; width:55%;' name="rpt_ctx" id='rpt_ctx' maxlength="80">
						</td></tr>
						<tr class='text-center'><td>
						<input type='button' class='btn btn-primary btn-lg' value="送出" onclick="report.call(this,event,'${pageContext.request.contextPath}','${param.art_no }');">
						<input type='reset' class='btn btn-default btn-lg' value="重填">
						</td></tr>
						</tbody>
						</table>
			
			</form>
			</div>
		</div>
	<!-- 分享 -->
<a data-fancybox data-src="#hidden-content-b" style='display:none;' href="javascript:;" id='shareBtn' >share</a>
 <div  id="hidden-content-b" style='display:none;'>
<div class="modal-content" id='shareContainer' style='width:100%;'>
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">分享此文章至個人動態<button onclick='appendCmt();'></button></h4>
      </div>
      <div class="modal-body">
        <textarea class="form-control" id='shareText' rows="3" style="resize: none;"></textarea>
      </div>
      <div class="modal-footer">
      
      <a href="#" class='shareLink' target="_blank" >
        <div class="panel panel-default">
		  <div class="panel-heading text-left">
		 		<div class="row">
									<div class="col-xs-12 col-sm-1">

										<img src=""
											class="img-circle cmt_mem_pic sharePic">

									</div>
									<div class="col-xs-12 col-sm-11">
										<div class="col-xs-12 col-sm-12 cmtInfo shareTitle">
											標題
										</div>
										<div class="col-xs-12 col-sm-12 cmtInfo shareNickname" >
											名字
										</div>
										
										<div class="col-xs-12 col-sm-12 cmtInfo cmtTime shareTime">
											時間
										</div>
										

									</div>
									</div>
		  </div>
		  <div class="panel-body shareContent">
		  		<div class="col-xs-12 col-sm-12 text-left bd_msg_ctx">
		  		內文
		  		</div>		    	
		  
		  </div>
		</div>
      </a>
      </div>
      <div class="modal-footer">
      	<div class="col-xs-12 col-sm-2 col-sm-offset-9" style="padding-left: 50px;" >
      		<div class="dropup">
					<button class="btn btn-default btn-lg dropdown-toggle" type="button"
						id="dropdownMenu3" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true" value="0" onclick='addOpen.call(this,event);'>
						隱私 <span class="caret"></span>
					</button>
					<ul class="dropdown-menu" aria-labelledby="dropdownMenu3">
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
        <button type="button" class="btn btn-primary btn-lg" onclick='shareSubmit("${pageContext.request.contextPath}");'>分享</button></div>
      	
      </div>
    </div>
  </div>
  
  
<!-- 分享 end -->
  
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/front_end/forum/js/jquery.colorbox.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/front_end/forum/js/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/front_end/forum/js/kindeditor/lang/zh-TW.js"></script>
<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>	
<script
		src="${pageContext.request.contextPath}/front_end/album/js/jquery.fancybox.js"></script>
<script type="Text/JavaScript" src="${pageContext.request.contextPath}/front_end/forum/js/ArticleDisplay.js"></script>
<script type="text/javascript">
function appendCmt(){
	$('#shareText').val('這真是篇好文 請大家多多支持！！！');
}
function addOpen(event){
	event.preventDefault();
	if($(this).parent().hasClass('open')){
		$(this).parent().removeClass('open');
	}else{
		$(this).parent().addClass('open');
	}
}
function sharePhoto(event,path,forum_no,art_type,art_name,art_no,mem_no,mem_rank,mem_nickname,art_add_date){
	event.preventDefault();
	var art_ctx = $('#shareArt_ctx').html();
	$('.sharePic:last').attr('src',path+'/util/OutputPic?mem_no='+mem_no+"&mem_rank="+mem_rank);
	//補內文
	$('.bd_msg_ctx:last').html(art_ctx);	
	$('.shareNickname:last').text(mem_nickname+" 發佈的文章");
	$('.shareTime:last').text(art_add_date);
	$('.shareTitle:last').text("標題 ：["+art_type+"]"+art_name);
	$('.shareLink:last').prop('href',path+'/forum/ArticleShowCtrl?forum_no='+forum_no+'&art_no='+art_no);
	$('#shareBtn').trigger('click');
	
	
}
function shareSubmit(path,mem_no){
	var content = $('#shareContainer').find('.modal-footer').html();
	var bd_msg_ctx = $('#shareText').val().trim();
	var bd_prvt = $('#dropdownMenu3').val();
	
	$.ajax({
		type : "POST",
		url : path + "/board/BoardActionCtrl?action=ref_board",
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
					  text: "已成功發布動態",
					  timer: 1000,
					  type: "success",
					  showConfirmButton: false
					},function(){
						$('.fancybox-close-small').click();	
						swal.close();
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
				  title: "發生錯誤",
				  text: "請再嘗試看看",
				  timer: 1000,
				  type: "error",
				  showConfirmButton: false
			});
		}

	});
}
function del(){
	var res = $(this).prop('href');
		swal({
			  title: "確定要刪除文章？",
			  text: "刪除後將無法回復，請三思而後行",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "刪掉",
			  cancelButtonText: "算了",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			function(isConfirm){
			  if (isConfirm) {
				  location.href= res;
			  } else {
				  swal.close();
			  }
			});
	return false;
}
function delCmt(){
	var res = $(this).prop('href');
		swal({
			  title: "確定要刪除留言？",
			  text: "刪除後將無法回復，請三思而後行",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  confirmButtonText: "刪掉",
			  cancelButtonText: "算了",
			  closeOnConfirm: false,
			  closeOnCancel: false
			},
			function(isConfirm){
			  if (isConfirm) {
				  location.href= res;
			  } else {
				  swal.close();
			  }
			});
	return false;
}
</script>

  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>