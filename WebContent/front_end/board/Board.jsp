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

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/front_end/album/css/jquery.fancybox.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">

<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
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

.cmtInfo {
	margin-top: 10px;
}

.cmtTime {
	color: gray;
}

.cmtPrvt {
	margin-left: 8px;
}

.updatTime {
	color: gray;
}

.row-fluid {
	padding-left: 10px;
	padding-right: 30px;
	overflow: auto;
	white-space: nowrap;
}

.row-fluid .col-sm-2 {
	display: inline-block;
	float: none;
	padding: 5px;
}

.row-fluid .col-sm-2 img {
	border: 2px height: 150px;
	width: 100%;
}

.imgDiv img.delete {
	position: absolute;
	top: 0px;
	right: 0px;
	width: 30px;
	height: 30px;
	display: none;
}

.showBorder {
	border: 5px dotted yellow;
	background-image:
		url('http://gb.cri.cn/mmsource/images/2015/05/12/24/6068039622117627944.jpg');
	background-size: cover;
	background-position: 0% 55%;
	background-repeat: no-repeat;
	opacity: 0.5;
}

.boardEdit {
	margin-top: 15px;
}

.carousel-inner.onebyone-carosel {
	margin: auto;
	width: 90%;
}

.onebyone-carosel .active.left {
	left: -33.33%;
}

.onebyone-carosel .active.right {
	left: 33.33%;
}

.onebyone-carosel .next {
	left: 33.33%;
}

.onebyone-carosel .prev {
	left: -33.33%;
}

.pht_cmt {
	position: fixed;
	z-index: 99999;
}

#pht_cmt {
	position: fixed;
	top: 50%;
	width: 27%;
	margin-right: 30px;
	background-color: white;
	z-index: 1000000;
	border-top-color: white;
}
.pic img.delete {
	position: absolute;
	top: 0%;
	right: 5%;
	width: 90%;
	height: 90%;
	display:none;
}
.filmDel img.check {
	position: absolute;
	top: 2.5%;
	right: 5%;
	width: 90%;
	height: 95%;
	display:none;
}
.filmDel img.delete {
	position: absolute;
	top: -5%;
	right: 0%;
	width: 100%;
	height: 110%;
	display:none;
}
.pic img.check {
	position: absolute;
	top: 5%;
	right: 10%;
	width: 80%;
	height: 80%;
	display:none;
}
.addPic{
	margin-left:5px;
	width: 50px;
	height: 50px;
}
.picAndFilm{
	display:none;
}
.picAndFilm .showAddFilmCtrl {
	display:none;
}
.picAndFilm .showAddPicCtrl {
	display:none;
}
.progressCr{
	position: absolute;
	height: 235px;
	width:97%;
	background-color:gray;
	-webkit-filter:blur(80px);
	opacity :0.8;
	z-index: 1;
	display:none;
}
.progress {
	position: absolute;
	top: 115px;
	width:95%;
	z-index: 2;
	display:none;
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



</style>
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body ondragover="javascript: dragHandler(event);"
	ondragleave="javascript: leave_image(event);">
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>

	<div class="container">
			   <div class="breadcrumbs" id="breadcrumbs">
            
	            <div class="col-lg-12">
	                <h1>${(empty param.friend)?"個人動態":"好友動態" }
	                </h1>
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
	                	<li class="active">${(empty param.friend)?"個人動態":"好友動態" }</li>
	            </ul><!-- .breadcrumb -->
	        </div>
	        <br>
		<%@include file="/front_end/include/fakeTab.file" %>
		<div class="row">

			<div class="col-xs-12 col-sm-12" id="boardContainer">
				<c:if test="${!(empty param.friend && user.mem_no != param.mem_no)&& (not empty user)}">
						<div class="panel panel-default opcityDiv" id='showBorder'
					ondrop="javascript: drop_image(event);">
					<div class="panel-heading">
						<h3 class="panel-title">新增動態</h3>
					</div>
					<div class="panel-body">
					 		<div class='progressCr' >
</div>
<div class="progress text-center">
<div id="upload_progress" class="progress-bar progress-bar-striped active" role="progressbar" aria-valuenow="" aria-valuemin="0" aria-valuemax="100">
</div>
</div>  
  
						<textarea class="form-control scrollable" id='bd_msg_ctx'
							style="resize: none; background-color: transparent;" rows="8"
							placeholder="${user.mem_nickname }，在想些什麼呢？"></textarea>	
							<div class="error alert alert-danger" role='alert' style='display:none;' id='alert_bd_msg_ctx'></div>					
					</div>
					<div class="row-fluid" id='media'></div>
					<div class="row-fluid" id='filmContainer'></div>
					<div class="panel panel-default"
						style="margin-bottom: 0px; background-color: transparent;">
						<div class="row">
							<div class="col-xs-12 col-sm-8">
								<div class="panel-heading">
									<button class="btn btn-default" id='uploadTrigger'>
										<span class="glyphicon glyphicon-picture"> 新增照片</span>
									</button>
									<input type="file" style="display: none;" id="uploadPhoto"
										multiple>
									<button class="btn btn-default" id='uploadFilmTrigger'>
										<span class="glyphicon glyphicon-facetime-video"> 新增影片</span>
									</button>
									<input type="file" style="display: none;" id="uploadFilm">
								</div>
							</div>


							<div class="col-xs-12 col-sm-2">
								<div class="row" style="padding-left: 100px;">
									<div class="panel-heading">
										<div class="dropdown">
											<button class="btn btn-default dropdown-toggle" type="button"
												id="dropdownMenu1" data-toggle="dropdown"
												aria-haspopup="true" aria-expanded="true" value="0" onclick='addOpen.call(this);'>
												隱私設定 <span class="caret"></span>
											</button>
											<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
												<li><a href="#"
													onclick="chooseCmmtPrvt.call(this,'0');">朋友&nbsp</a></li>
												<li><a href="#"
													onclick="chooseCmmtPrvt.call(this,'1');">公開&nbsp</a></li>
												<li><a href="#"
													onclick="chooseCmmtPrvt.call(this,'2');">本人&nbsp</a></li>
											</ul>
										</div>

									</div>

								</div>

							</div>



							<div class="col-xs-12 col-sm-1" style="margin-left: 35px;">

								<div class="row">

									<div class="panel-heading" style="padding-left:">

										<button class="btn btn-primary"
											onclick="submit.call(this,'${pageContext.request.contextPath}','${param.mem_no }','${param.friend }');">
											&nbsp&nbsp&nbsp送出&nbsp&nbsp&nbsp</button>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				</c:if>
				<c:if test="${empty message_board }">
					<li class="list-group-item text-center">目前尚未有動態消息</li>
				</c:if>
				<c:forEach var="message_board" items="${message_board}" varStatus="number">
					<div class="panel panel-default panel<fmt:formatNumber type="number" value="${number.count/8 }" maxFractionDigits="0" var="num" />${num}">
						<div class="panel-heading">
							<h3 class="panel-title">
								<div class="row">
									<div class="col-xs-12 col-sm-1">

										<img
											src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${message_board.mem_no.mem_no }&mem_rank=${message_board.mem_no.mem_rank }"
											class="img-circle cmt_mem_pic" title='${message_board.mem_no.mem_nickname }'>

									</div>
									<div class="col-xs-12 col-sm-9">
										<div class="col-xs-12 col-sm-12 cmtInfo">
											<a href="#">${message_board.mem_no.mem_nickname }</a>
										</div>
										<div class="col-xs-12 col-sm-12 cmtInfo cmtTime">
											<fmt:setLocale value="en_US" />
											<fmt:formatDate value="${message_board.bd_msg_time}"
												pattern="yyyy-MM-dd HH:mm" />
											<div class="btn-group cmtPrvt">
												<div data-toggle="dropdown" aria-haspopup="true"
													aria-expanded="false">
													<img
														src="${pageContext.request.contextPath}/front_end/board/images/cmmtPrvt${message_board.bd_prvt }.png"  <c:if test="${message_board.mem_no.mem_no==user.mem_no }">onclick='addOpen2.call(this);'</c:if>>
													<c:if test="${message_board.mem_no.mem_no==user.mem_no }">
														<span class="caret"></span>
												</div>

												<ul class="dropdown-menu" id='cmtPrvt'>
													<li><a href="#">隱私設定</a></li>
													<li role="separator" class="divider"></li>
													<li><a href="#" onclick="setCmmtPrvt.call(this,'${pageContext.request.contextPath}','${message_board.mem_no.mem_no }','${message_board.bd_msg_no }','0');">朋友</a></li>
													<li><a href="#" onclick="setCmmtPrvt.call(this,'${pageContext.request.contextPath}','${message_board.mem_no.mem_no }','${message_board.bd_msg_no }','1');">公開</a></li>
													<li><a href="#" onclick="setCmmtPrvt.call(this,'${pageContext.request.contextPath}','${message_board.mem_no.mem_no }','${message_board.bd_msg_no }','2');">本人</a></li>
												</ul>

											</div>

										</div>


									</div>
									<div class="col-xs-12 col-sm-2 boardEdit">
										<button class="btn btn-info" onclick='edit.call(this,"${pageContext.request.contextPath}","${message_board.bd_msg_no }","${message_board.mem_no.mem_no }","${message_board.bd_type }");'>
											<span class="glyphicon glyphicon-pencil"></span>
										</button>
										<button class="btn btn-danger"
											onclick="deleteBoard.call(this,'${pageContext.request.contextPath}','${user.mem_no }','${message_board.bd_msg_no }');">
											<span class="glyphicon glyphicon-trash"></span>
										</button>
										</c:if>
									</div>
								</div>

							</h3>
						</div>
						<div class="panel-body">
							<div id='bd_msg_ctx${message_board.bd_msg_no }' class='content'>${message_board.bd_msg_ctx}</div>
						</div>
						<c:if
							test="${message_board.bd_type==1 || message_board.bd_type==3 }">
							<div class="panel-body">
								<!-- 如果有照片  -->
								<div class="well">			
								<div <c:if	test="${list!= null || fn:length(message_board.photos) > 3}">id="myCarousel"</c:if> class="carousel fdi-Carousel slide">	
										<!-- Carousel items -->
										<div class="carousel fdi-Carousel slide"
											id="eventCarousel${message_board.bd_msg_no }"											
											data-interval="0">
											<div class="carousel-inner onebyone-carosel">
												<c:forEach var="bd_photo"
													items="${message_board.photos }" varStatus="loop">
													
													<div class="item ${(loop.index==0)? 'active':''  }">
														<div class="col-sm-4 pic">
															<figure>
															
																<a id='${bd_photo.photo_no}'
																	href="${pageContext.request.contextPath}/util/OutputPic?photo_no=${bd_photo.photo_no}&type=big"
																	data-fancybox="group${message_board.bd_msg_no }" class='aLink'> 
																	<img src='${pageContext.request.contextPath}/front_end/board/images/cancel.png' class='check checkGroup${bd_photo.photo_no}'/>
																	<img src='${pageContext.request.contextPath}/front_end/board/images/select.png' class='delete delete${message_board.bd_msg_no } delGroup${message_board.bd_msg_no }' onclick="del('${bd_photo.photo_no}');"/>
																	
																	<img
																	style='height: 250px; width: 100%;'
																	src="${pageContext.request.contextPath}/util/OutputPic?photo_no=${bd_photo.photo_no}"
																	class="img-responsive center-block">
																	
																	<div class="text-center">${loop.count }/${fn:length(message_board.photos) }</div>
																	
																</a>
															</figure>
															<input type="checkbox" id='del${bd_photo.photo_no }' name='delPhoto_no${message_board.bd_msg_no }' value='${bd_photo.photo_no }' hidden/>
														</div>
													</div>
			
												</c:forEach>

											</div>
											<c:if
												test="${list!= null || fn:length(message_board.photos) > 3}">
												<a class="left carousel-control"
													href="#eventCarousel${message_board.bd_msg_no }"
													data-slide="prev"></a>
												<a class="right carousel-control"
													href="#eventCarousel${message_board.bd_msg_no }"
													data-slide="next"></a>
											</c:if>

										</div>
										<!--/carousel-inner-->
									</div>
									<!--/myCarousel-->
								</div>
								<!--/well-->

							</div>
							<!--/panel-body  -->
								<!-- 相片留言  -->
								<c:forEach var="bd_photo" 
													items="${message_board.photos }" varStatus="loop">
							<c:forEach var="comment" items="bd_photo.comments">
						<div id='picCmt${bd_photo.photo_no}' style='display:none;'>
						<div class="panel panel-default" style='font-size: 20px;max-height:103%; height:103%;'>
									  <div class="panel-heading" >留言</div>
									     <ul class="list-group pre-scrollable" style=' vertical-align:middle;max-height:82%; height:82%;'>
										    <c:if test="${empty bd_photo.comments }">
										    	<li class="list-group-item text-center" style='height: 100%; font-size: 20px;'>							    		
										    	<div>目前尚無留言</div>
										    	</li>
										    	</c:if>
										    <c:forEach var="comment" items="${bd_photo.comments }" varStatus="cmt">
										    
										    	
												<li class="list-group-item comments photo_key_${bd_photo.photo_no}_<fmt:formatNumber type="number" value="${(cmt.index-cmt.index%8)/8 }" />" ${(cmt.count>8)? 'style="display:none"':'' }>
												<div class="row">
														
														<div class="col-xs-12 col-sm-2">
															<a href="${pageContext.request.contextPath}/PersonalPageCtrl?mem_no=${comment.mem_no.mem_no}&mem_rank=${comment.mem_no.mem_rank}">
															<img
																src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${comment.mem_no.mem_no}&mem_rank=${comment.mem_no.mem_rank}"
																class="img-circle cmt_mem_pic" title="${comment.mem_no.mem_nickname }" style='z-index: 10;'>
															</a>
														</div>
														
														<div class="col-xs-12 col-sm-7 cmt" >
															<span class='a' style='padding:50px; margin-top:50px; padding-right: 0px;'>${comment.bd_cmt_ctx }</span>
															<input type='text' class='b' value='${comment.bd_cmt_ctx }' style='display:none;' onfocus='this.value = this.value;'/>
															<span class='c' ><a href="#" style='margin-left: 3px' onclick='addPhotoCmtLikes.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }");'><span class='cmtLikes'>${(comment.ifClick)?'收回讚':'讚'}</span><span style='margin-left: 5px;'>${(comment.cmt_likes>0)? comment.cmt_likes:''  }</span></a></span>
												
														</div>
														<div class="col-xs-12 col-sm-3 cmt">
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
															<button class="btn btn-default ${(not empty user)?"":"disabled"}" ${(not empty user)?"":"disabled"} type="button" onclick='sendPhotoComments.call(this,"${pageContext.request.contextPath}","${user.mem_no }","${bd_photo.photo_no}");'>送出</button>
														</span>
													</div>
												</li>
										  </div>
									</div>
									</div>
												</c:forEach>
												
												
							</c:forEach>
												<!-- ----------- -->
						</c:if>
						
						<!-- 如果有影片  -->
						<c:if
							test="${message_board.bd_type==2 || message_board.bd_type==3 }">
							<div class="panel-body text-center">
								<div class="well">
									<div class="row">
										<div class="col-xs-12 col-sm-12 filmDel">
										<img src='${pageContext.request.contextPath}/front_end/board/images/cancel.png' class='check' id='checkFilm${message_board.bd_msg_no }'/>
										<img src='${pageContext.request.contextPath}/front_end/board/images/select.png' class='delete delete${message_board.bd_msg_no }' id='deleteFilm${message_board.bd_msg_no }' onclick="delFilm('${message_board.bd_msg_no }');"/>
											<video controls="controls">
												<source
													src="${pageContext.request.contextPath}/util/OutputPic?bd_msg_no=${message_board.bd_msg_no}">
											</video>
										</div>
									</div>
								</div>
								<!--well -->
								<input type="checkbox" id='delFilm${message_board.bd_msg_no }' name='delFilm' hidden/>
							</div>
							
							<!--panel-body -->
						</c:if>
						<c:if
							test="${message_board.bd_type==2 || message_board.bd_type==3 ||message_board.bd_type==0 ||message_board.bd_type==1}">
						<div class='panel panel-default picAndFilm'>
						<div class="panel-heading text-center">
						<h4>新增圖影專區
								<img src='${pageContext.request.contextPath}/front_end/board/images/plus.png' class='addPic' onclick="addPic('${message_board.bd_msg_no}','${message_board.bd_type }');"/>
						
									<input style='display:none' type="file" class='addPicInput' id="addPicInput${message_board.bd_msg_no}_${message_board.bd_type }" multiple ></h4></div>
						<div class="panel-body">
								
							
							<div class="panel-body text-center showAddPicCtrl">
							<div class="well">
									<div class="row">
								<div class="col-xs-12 col-sm-12">
									<div class="row row-fluid" id='addBdPic${message_board.bd_msg_no}'>									
								</div>
								<button class="btn btn-danger" onclick='BdPreview.removeAllPic.call(this);'>
								清除所有新增圖片
									</button>
									</div>
									</div>
									</div>
									</div>
							<div class="panel-body text-center showAddFilmCtrl">
								<div class="well">
									<div class="row">
									
									<div class="col-xs-12 col-sm-12 text-center">
									<div class="row" id='addBdFilm${message_board.bd_msg_no}'>
									</div>
									</div>
									</div></div></div>
									
							
								</div>
							</div></c:if>
						<!-- 相片分享 -->	
						<c:if test="${message_board.bd_type=='4' }">
							<div class="panel-body">
							${message_board.bd_ref_ctx}
							</div>
						</c:if>
						
							
						<!-- 相片分享end -->		
						<div class="panel-body updatTime">
						<div class="col-xs-12 col-sm-4">
						<span id='likes${message_board.bd_msg_no}'>${message_board.bd_likes}</span>&nbsp個讚
						</div>
							<div class="col-xs-12 col-sm-4 col-sm-offset-4">
								<fmt:setLocale value="en_US" />
								<fmt:formatDate value="${message_board.bd_upd_time}"
									pattern="最後更新於 yyyy-MM-dd HH:mm" />
									
							</div>
								
						</div>
						<div class="panel panel-default" style="margin-bottom: 0px;">
						
							<div class="panel-heading">
							
								<ul class="nav nav-pills">
									<li role="presentation" ${(message_board.ifClick)? 'class="disabled"':''}>
									<a href="#"
										<c:if test='${!message_board.ifClick }' >onclick="addLikes.call(this,event,'${pageContext.request.contextPath}','${message_board.mem_no.mem_no }','${message_board.bd_msg_no}')"</c:if>
									 	<c:if test='${!message_board.ifClick }' >onclick="return false;"</c:if>>
									 <span
											class="glyphicon glyphicon-thumbs-up">&nbsp讚</span>
									</a></li>
									<li role="presentation"><a href="#" onclick="return showCmmt.call(this,event,'${message_board.bd_msg_no }');">
										<c:if test="${not empty message_board.comments }"> <span class="badage">${fn:length(message_board.comments)}</span></c:if>
											<span class="glyphicon glyphicon-comment">&nbsp留言</span>
									</a></li>
									<li role="presentation" ${(not empty user)?"":"class='disabled'"} ${(not empty user)?"":"disabled"}>
									<c:if test='${empty user}'><a href="#" onclick="return false;"></c:if>
										<c:if test='${!empty user}'><a href="#" onclick='sharePhoto.call(this,event,"${pageContext.request.contextPath}","${message_board.bd_msg_no}","${message_board.mem_no.mem_no}","${message_board.mem_no.mem_rank}","${message_board.mem_no.mem_nickname}","<fmt:setLocale value="en_US" /><fmt:formatDate value="${message_board.bd_msg_time}" pattern="yyyy-MM-dd HH:mm" />","${message_board.bd_type}","${message_board.photos[0].photo_no},${message_board.photos[1].photo_no},${message_board.photos[2].photo_no}");'></c:if> 
										<span class="glyphicon glyphicon-share-alt">&nbsp分享</span>
									</a></li>
								</ul>
							</div>
						</div>

						<ul id='commt${message_board.bd_msg_no }' class="list-group" style="display: none;">
						<c:forEach var="comment" items="${message_board.comments }" varStatus="cmt">
						
						<li class="list-group-item comments key_${message_board.bd_msg_no}_<fmt:formatNumber type="number" value="${(cmt.index-cmt.index%5)/5 }" />" ${(cmt.count>5)? 'style="display:none"':'' }>
							<div class="row">
								<div class="container">
									<a href="#">
										<div class="col-xs-12 col-sm-1">

											<img
												src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${comment.mem_no.mem_no}&mem_rank=${comment.mem_no.mem_rank}"
												class="img-circle cmt_mem_pic" title="${comment.mem_no.mem_nickname }" style='z-index: 10;'>

										</div>
									</a>
									<div class="col-xs-12 col-sm-8 cmt" >
										<span class='a' style='padding:30px; padding-right: 0px;'>${comment.bd_cmt_ctx }</span>
										<input type='text' class='b' value='${comment.bd_cmt_ctx }' style='display:none;' onfocus='this.value = this.value;'/>
										<span class='c' ><a href="#" style='margin-left: 3px' onclick='addCmtLikes.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }","${comment.mem_no.mem_no }");'><span class='cmtLikes'>${(comment.ifClick)?'收回讚':'讚'}</span><span style='margin-left: 5px;'>${(comment.cmt_likes>0)? comment.cmt_likes:''  }</span></a></span>

									</div>
									<div class="col-xs-12 col-sm-3 cmt">
									<a href='#' onclick='editCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }","${comment.mem_no.mem_no}");' style='color:black'>
										<span class='glyphicon glyphicon-pencil'></span></a>
										&nbsp&nbsp&nbsp&nbsp&nbsp
									<a href='#' onclick='delCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }","${comment.mem_no.mem_no}");' style='color:black'>
										<span class='glyphicon glyphicon-remove'></span></a>
									</div>
								</div>
							</div>	
							</li>
							</c:forEach>
							<c:if test="${not empty message_board.comments&& fn:length(message_board.comments)>5}">			
							<li class="list-group-item"><a href="#"
								onclick="showMore.call(this,event,'${message_board.bd_msg_no}','${fn:length(message_board.comments)}');">顯示更多</a>
								<input type='hidden' id='count${message_board.bd_msg_no}' value=1>
								</li>
							</c:if>
							<li class="list-group-item">
								<div class="input-group">
									<input type="text" class="form-control" placeholder="${(not empty user)? "留些什麼吧":"請先登入會員" }" ${(not empty user)?"":"disabled"}>
									<span class="input-group-btn">
										<button class="btn btn-default ${(not empty user)?"":"disabled"}" ${(not empty user)?"":"disabled"} type="button" onclick='sendComments.call(this,"${pageContext.request.contextPath}","${user.mem_no }","${message_board.bd_msg_no}");'>送出</button>
									</span>
								</div>
							</li>
						</ul>
					</div>
				</c:forEach>


				<div id='loader' style='display:none;' class='col-xs-12 col-sm-7 col-sm-offset-5'><img style='margin-left:30px;width:50px;' src='${pageContext.request.contextPath}/front_end/comm/image/loader.gif'></div>
			</div>
		</div>
	</div>
	
	<!-- 分享 -->
<a data-fancybox data-src="#hidden-content-b" style='display:none;' href="javascript:;" id='shareBtn' >share</a>
 <div  id="hidden-content-b" style='display:none;'>
<div class="modal-content" id='shareContainer' style='width:100%;'>
      <div class="modal-header">
        <h4 class="modal-title" id="myModalLabel">分享此動態至個人動態</h4>
      </div>
      <div class="modal-body">
        <textarea class="form-control" id='shareText' rows="3" style="resize: none;">
        	
        </textarea>
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
		    	<div class="col-xs-12 col-sm-12 text-left shareMedia">
		    		
		    	</div>
		    	
		  
		  </div>
		</div>
      </a>
      </div>
      <div class="modal-footer">
      	<div class="col-xs-12 col-sm-2 col-sm-offset-9" style="padding-left: 0px;" >
      		<div class="dropup">
					<button class="btn btn-default btn-lg dropdown-toggle" type="button"
						id="dropdownMenu3" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="true" value="0">
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
  
     <a data-fancybox data-src="#hidden-content-a" href="javascript:;" id='shareBtn2' class="btn" style='display:none;'>Open demo</a>
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
	
	
  <!-- 123 -->
	
	
	<input type='text' style='display:none;' id='thisPage' value='${thisPage }'>
	<input type='text' id='allPageCount' style='display:none;' value='${allPageCount }'>
	
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src='${pageContext.request.contextPath}/front_end/album/js/jquery.ajax-progress.js'></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/front_end/album/js/jquery.fancybox.js"></script>
	<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>
	<script type="text/javascript">
	function addOpen(){
		if($(this).parent().hasClass('open')){
			$(this).parent().removeClass('open');
		}else{
			$(this).parent().addClass('open');
		}
	}
	function addOpen2(){
		if($(this).parent().parent().hasClass('open')){
			$(this).parent().parent().removeClass('open');
		}else{
			$(this).parent().parent().addClass('open');
		}
	}
	function sharePhoto(event,path,bd_msg_no,mem_no,mem_rank,mem_nickname,bd_msg_time,bd_type,photo_no){
		event.preventDefault();
		$('.sharePic:last').attr('src',path+'/util/OutputPic?mem_no='+mem_no+"&mem_rank="+mem_rank);
		$('.bd_msg_ctx:last').text($(this).parents('.panel').find('.content').text().trim());
		var media ="";
		
		if(bd_type=='2'||bd_type=='3'){
			media +="<div class='col-xs-12 col-sm-12'>"+
				"<video controls='controls'>"+
			"<source src='"+path+"/util/OutputPic?bd_msg_no="+bd_msg_no+"'></video></div>";
		}
		if(bd_type=='1'||bd_type=='3'){
			//補多張格式 要加foreach
			
			var temp = photo_no.split(",");
			for(var i =0 ; i< temp.length ; i++){
				if(temp[i].length!=0){					
					media +="<div class='col-xs-12 col-sm-4'>"+
					"<img style='height:250;width:100%'  src='"+path+"/util/OutputPic?photo_no="+temp[i]+"'></div>";					
				}
			}
		}
		if(bd_type=='4'){
			media = "<div class='col-xs-12 col-sm-12 well'>此為分享內容，點擊此後即可觀看詳細內容</div>";
		}
		$('.shareMedia:last').empty();
		$('.shareMedia:last').append(media);		
		$('.shareNickname:last').text(mem_nickname+" 發佈的動態");
		$('.shareTime:last').text(bd_msg_time);
		
		$('.shareLink:last').prop('href',path+'/board/BoardShowCtrl?mem_no='+mem_no+'&bd_msg_no='+bd_msg_no);
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
					  title: "發生錯誤",
					  text: "請再嘗試看看",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
				});
			}

		});
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
					  title: "發生錯誤",
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
		swal({
			  title: "確定要刪除此筆留言嗎？?",
			  text: "此舉有點獨裁，你要確定你仍想這樣做哦。",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "算了",
			  confirmButtonText: "是的",
			  closeOnConfirm: false
			},
		function(){
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
						swal({
							  title: "成功",
							  text: "你這獨裁者，已成功刪除留言。",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
						});
					} else {						
						swal({
							  title: "刪除失敗",
							  text: "請在嘗試看看。",
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
						});
						
						
					}
				},
				error : function(xhr, ajaxOptions, thrownError) {
					swal({
						  title: "出了點錯",
						  text: "應該是網路問題。",
						  timer: 1000,
						  type: "error",
						  showConfirmButton: false
						});
				}

			});
		});
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
			submitPhotoEditCmt(path,mem_no,bd_cmt_no,content.find('.b').val(),$(this).children(),content);
			
		}
	}
	function submitPhotoEditCmt(path,mem_no,bd_cmt_no,val,ch,content){
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
				} else {						
					
					result = false;
					
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
					location.reload();
				} else {						
					
					
					
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
	function addCmtLikes(event,path,bd_cmt_no,mem_no){
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
			data: "cmt_type=0&bd_cmt_no="+bd_cmt_no,
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
	function delCmmt(event,path,bd_cmt_no,mem_no){
		event.preventDefault();
		var _self = $(this).parents('.comments');
		var _CommtNum = $(this).parents('.list-group').prev().find('.badage');
		swal({
			  title: "確定要刪除此筆留言?",
			  text: "此舉有點獨裁，你要確定你仍想這樣做哦。",
			  type: "warning",
			  showCancelButton: true,
			  confirmButtonColor: "#DD6B55",
			  cancelButtonText: "算了",
			  confirmButtonText: "是的",
			  closeOnConfirm: false
			},
		function(){
			$.ajax({
				type : "POST",
				url : path + "/all/CommentsCtrl?action=delete&mem_no="+mem_no,
				dataType : 'text',
				data: "cmt_type=0&bd_cmt_no="+bd_cmt_no,
				success : function(msg) {
					if (msg.length == 0) {
						_self.remove();
						var num = parseInt(_CommtNum.text(),10);
						if(num-1!=0){
							_CommtNum.text(num-1);							
						}else{
							_CommtNum.text('');
						}
						swal({
							  title: "成功",
							  text: "你這獨裁者，已成功刪除留言。",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
						});
					} else {						
						swal({
							  title: "刪除失敗",
							  text: "請在嘗試看看。",
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
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
		});
	}
	function editCmmt(event,path,bd_cmt_no,mem_no){
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
			if(submitEditCmt(path,mem_no,bd_cmt_no,content.find('.b').val())){
				$(this).children().removeClass();
				$(this).children().addClass('glyphicon glyphicon-pencil');
				$(this).children().css("color","black");
				content.find('.a').css("display", "");
				content.find('.b').css("display","none");
				content.find('.a').text(content.find('.b').val());				
			}
		}
	}
	function submitEditCmt(path,mem_no,bd_cmt_no,val){
		var res = $.ajax({
			type : "POST",
			url : path + "/all/CommentsCtrl?action=update&mem_no="+mem_no,
			dataType : 'text',
			data: "bd_cmt_no="+bd_cmt_no+"&bd_cmt_ctx="+val,
			success : function(msg) {
				if (msg.length == 0) {
					return true;
				} else {								
					return false;					
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
				return false;
			}

		});
		return res;
	}
	function showMore(event,bd_msg_no,size){
		event.preventDefault();
		var cmt = $(this).parent().parent().children(".comments");
		var count = parseInt( $(this).next().val(),10);
		if(count+1>(size-size%5)/5){
			$(this).hide();
		}
			$(".key_"+bd_msg_no+"_"+count).css('display','block');
			
			$(this).next().val(count+1);
		
	}
	function sendComments(path, mem_no, bd_msg_no){
		var val = $(this).parent().prev().val();	
		$.ajax({
			type : "POST",
			url : path + "/all/CommentsCtrl?action=insert&mem_no="+mem_no,
			dataType : 'text',
			data: "cmt_type=0&org_no="+bd_msg_no+"&bd_cmt_ctx="+val,
			success : function(msg) {
				if (msg.length == 0) {
					location.reload();
				} else {						
					
					
					
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
	function share(e){
		e.preventDefault();
		document.location.reload();
	}
	function addLikes(e,path,mem_no,bd_msg_no){
		var likesBtn = $(this);
		e.preventDefault();
		if(likesBtn.parent().attr('disabled')!='disabled'){
			$.ajax({
				type : "POST",
				url : path + "/board/BoardActionCtrl?action=addLikes&mem_no="+mem_no+"&bd_msg_no="+bd_msg_no,
				dataType : 'text',
				contentType : false,
				success : function(msg) {

					if (msg.length == 0) {
						likesBtn.parent().addClass('disabled');
						likesBtn.parent().attr('disabled','');
						var likes = $('#likes'+bd_msg_no).text();
						$('#likes'+bd_msg_no).text(parseInt(likes,10)+1);
					} else {						
						swal({
							  title: "輸入錯誤",
							  text: "請稍後再嘗試。",
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
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
		
	}
	function addPic(bd_msg_no,bd_type){
		$('#addPicInput'+bd_msg_no+"_"+bd_type).trigger('click');
	}

	
	
	function del(photo_no){
		if($('.checkGroup'+photo_no).css('display')=='none'){
			$('.checkGroup'+photo_no).css('display',"block");
			$('#del'+photo_no).prop('checked','true');
		}else{
			$('.checkGroup'+photo_no).css('display',"none");
			$('#del'+photo_no).removeAttr('checked');
		}
		
	}
	function delFilm(bd_msg_no){
		if($('#checkFilm'+bd_msg_no).css('display')=='none'){
			$('#checkFilm'+bd_msg_no).css('display',"block");
			$('#delFilm'+bd_msg_no).prop('checked','true');
		}else{
			$('#checkFilm'+bd_msg_no).css('display',"none");
			$('#delFilm'+bd_msg_no).removeAttr('checked');
		}
		
	}
	var countBdPic = 0;
	var bdPicList =[];
	var bdFilm = null;
	var isEditOpen = false;
	BdPreview = new function() {
		var fileInput = $('.addPicInput');
		this.file_change = function() {
			$('.addPicInput').each(function(){
				$(this).on('change', function() {
					var len = $(this).attr('id').length;
					var tempStr = $(this).attr('id').substring(11,len).split("_");
					show(this,tempStr[0],tempStr[1]);					
				});
			});
		}
		var show = function(input,bd_msg_no,bd_type) {
			if (input.files && input.files[0]) {
				each_img(input.files,bd_msg_no,bd_type);
			}
		}
		this.removePic = function(bd_msg_no) {
			var id = $(this).attr('id');
			$(this).parent().parent().parent().remove();
			delete bdPicList[id];
			var res = false;
			for(var count in bdPicList){
				res = true;
				break;	
			}
			if(!res){
				$('#addBdPic'+bd_msg_no).parents('.showAddPicCtrl').css('display','none');					
			}
		}
		this.removeFilm = function(bd_msg_no,bd_type) {
			$(this).parent().parent().parent().remove();
			bdFilm = null;
			$('#addBdFilm'+bd_msg_no).parents('.showAddFilmCtrl').css('display','none');
			if(bd_type==2||bd_type==3){
				if($('#checkFilm'+bd_msg_no).css('display')=='none'){
					
				}else{
					$('#checkFilm'+bd_msg_no).css('display',"none");
					$('#delFilm'+bd_msg_no).removeAttr('checked');
				}
			}
			
		}
		this.removeAllPic = function(){
			$(this).prev().empty();
			bdPicList =[];
			$(this).parents('.showAddPicCtrl').css('display','none');
			
			
		}
		var each_img = function(files,bd_msg_no,bd_type) {
			$.each(
					files,
					function(index, file) {
						if (file.type.match('video')) {
							var reader = new FileReader();
							bdFilm = null;
							reader.onload = function() {
								var video = "<div class='col-xs-12 col-sm-12'>"
										+ "<div class='list-group text-center'><div class='list-group-item imgDiv'>"
										+ "<img class='delete' src='"+webCtx+"/front_end/board/images/cross1.png' "
										+ "onclick='BdPreview.removeFilm.call(this,"+bd_msg_no+","+bd_type+");'/>"
										+ "<video controls='conrtols'><source src="+
										reader.result+" type='video/mp4'></video></div></div></div>";
								$('#addBdFilm'+bd_msg_no).append(video);
								$(".imgDiv").mouseenter(function() {
									$(this).find(".delete").show();

								});

								$(".imgDiv").mouseleave(function() {
									$(this).find(".delete").hide();
								});
								bdFilm = file;
								if(bdFilm!=null){
									$('#addBdFilm'+bd_msg_no).parents('.showAddFilmCtrl').css('display','block');
									if(bd_type==2||bd_type==3){
										if($('#checkFilm'+bd_msg_no).css('display')=='none'){
											$('#checkFilm'+bd_msg_no).css('display',"block");
											$('#delFilm'+bd_msg_no).prop('checked','true');
										}else{
											
										}
									}
								}
							}
							if (file) {
								reader.readAsDataURL(file);
							}
							return;
						}
						if (file.type.match('image')) {
							var reader = new FileReader();
							var img = new Image();
							img.onload = function() {
								bdPicList[countBdPic] = [file,countBdPic];
								if(bdPicList.length>0){
									$('#addBdPic'+bd_msg_no).parents('.showAddPicCtrl').css('display','block');
								}
								var pic = "<div class='col-xs-12 col-sm-2 pic'>"
										+ "<div class='list-group'><div class='list-group-item imgDiv'>"
										+ "<img src='"+webCtx+"/front_end/board/images/cross1.png' id='"
										+ (countBdPic++)
										+ "' class='delete' onclick='BdPreview.removePic.call(this,\""+bd_msg_no+"\");'/>"
										+ "<div align='center'><img alumb='true' class='pics' style='width:100%;' height=100  src='"
										+ img.src
										+ "'>"
										+ "</div></div></div>";
								$("#addBdPic"+bd_msg_no).append(pic);
								$(".imgDiv").mouseenter(function() {
									$(this).find(".delete").show();

								});

								$(".imgDiv").mouseleave(function() {
									$(this).find(".delete").hide();
								});

							};
							reader.onload = function() {
								img.src = reader.result;
							}
							if (file) {
								reader.readAsDataURL(file);
							}
							return;
						}
						swal({
							  title: "格式不符",
							  text: "請換檔案再嘗試看看",
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
						});
					});
					
					
		}

	}
	
		$(document).ready(function() {		
							var fdKey = QueryString('friend');
							if(fdKey!=null&&fdKey.trim().length!=0){
								$('#FdBdTab').addClass('active');
							}else{
								$('#BdTab').addClass('active');				
							}
							$('#myCarousel').carousel({
								interval : 5000
							});
							$('.fdi-Carousel .item')
									.each(
											function() {
												var sibNum = $(this).siblings().length;
												var next = $(this).next();
												if (!next.length) {
													next = $(this).siblings(
															':first');
												}
												next.children(':first-child')
														.clone().appendTo(
																$(this));
												var num = $(this).children(':last-child').children().children().attr('id');
												if(sibNum>1){
													
													$(this)	.children(':last-child')
															.children()
															.children()
															.prop("href", "#")
															.removeAttr("data-fancybox")
															.click(function(event) {
																		event.preventDefault();
																		next.children(':first-child')
																			.children()
																			.children()
																			.trigger('click');
																	});
												}

												if (sibNum > 1) {
													if (next.next().length > 0) {
														next.next()
															.children(':first-child')
															.clone()
															.appendTo($(this));
														$(this).children(':last-child')
																.children()
																.children()
																.prop("href","#")
																.removeAttr("data-fancybox")
																.click(function(event) {
																			event.preventDefault();
																			next.next()
																				.children(':first-child')
																				.children()
																				.children()
																				.trigger('click');
																		});
													} else {
														$(this).siblings(':first')
																.children(':first-child')
																.clone()
																.appendTo($(this));
														$(this).children(':last-child')
																.children()
																.children()
																.prop("href","#")
																.removeAttr("data-fancybox")
																.click(function(event) {
																			event.preventDefault();
																			$(this).siblings(':first')
																					.children(':first-child')
																					.children()
																					.children()
																					.trigger('click');
																		});
													}
						 						}

											});

							$('[data-fancybox]').fancybox({

								clickSlide : false
							});
							$('#uploadTrigger').click(function() {
								$('#uploadPhoto').trigger('click');
							})
							$('#uploadFilmTrigger').click(function() {
								$('#uploadFilm').trigger('click');
							})
							thisPage = $('#thisPage').val();
							allPageCount = $('#allPageCount').val();	
							var path = window.location.pathname;
						    webCtx = path.substring(0, path.indexOf('/', 1));
							BdPreview.file_change();
							Preview.file_change();
							ShowFilm.file_change();
							$(window).scroll(function(){
								  var window_height = $( window ).height();								  
								  var window_scrollTop = $(window).scrollTop();								 
								  var document_height = $( document ).height();
								  
								  if(!ifLoad&&(parseInt(thisPage,10)+1<=allPageCount)){									  
									  if(window_height + window_scrollTop > (document_height-100)){
										  ifLoad=true;
										  loadContent();
										     
										}
									 
								  }
								   

								});
						});
		var thisPage,allPageCount ;	
		var webCtx;
		var ifLoad = false;
		function loadContent(){
			var mem_no = QueryString("mem_no");
			var friend = QueryString("friend");
			var path = '';
			
			ifLoad=true;
			thisPage = parseInt(thisPage,10)+1;	
			if(friend.length!=0){
				path = webCtx+"/board/BoardShowCtrl?type=json&friend=1&mem_no="+mem_no+"&thisPage="+(thisPage);				
			}else{
				path = webCtx+"/board/BoardShowCtrl?type=json&mem_no="+mem_no+"&thisPage="+(thisPage);
			}
			$('#loader').css('display',"block");
			$.ajax({
				type : "POST",
				url : path,
				dataType : 'text',
				contentType : false,
				success : function(msg) {
					if (msg.length != 0) {
						ifLoad=false;
						$('#loader').before(msg);
						reload();
						$('#loader').css('display',"none");
					} else {
						//報錯啊
						swal({
							  title: "載入失敗",
							  text: "請再嘗試看看",
							  timer: 1000,
							  type: "error",
							  showConfirmButton: false
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
		function deleteBoard(path ,mem_no, bd_msg_no) {
			
			swal({
				  title: "確定要刪除此筆動態?",
			 	  text: "將會把照片及內文，確定不會捨不得，仍要執意刪除哦。",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  cancelButtonText: "算了",
				  confirmButtonText: "是的",
				  closeOnConfirm: false
			},
			function(){
				$.ajax({
					type : "POST",
					url : path + "/board/BoardActionCtrl?action=delete&mem_no="+mem_no+"&bd_msg_no="+bd_msg_no,
					dataType : 'text',
					contentType : false,
					processData : false, //不做任何處理，只上傳原始資料					
					success : function(msg) {
	
						if (msg.length == 0) {
							
							swal({
							  title: "成功",
							  text: "已成功刪除動態",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
							},function(){
								location.reload();
							});
							
						} else {
							//報錯啊
							swal({
								  title: "發生錯誤",
								  text: "請再嘗試看看",
								  timer: 1000,
								  type: "error",
								  showConfirmButton: false
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
			});
		}
		
		function edit(path,bd_msg_no,mem_no,bd_type) {
			var btn = $(this);
			var btnSpan = $(this).children();
			var spanClass = $(this).children().attr('class');
			var createArea = $(this).parents(".panel-default").children('.picAndFilm');
			var content = $(this).parents(".panel-default").find(".content");
			var originText = content.text();
			if (spanClass == "glyphicon glyphicon-pencil") {
				if(!isEditOpen){					
					$('.aLink').each(function() {					
						var a = $(this).attr('href',"#").removeAttr("data-fancybox").unbind('click').click(function(event){
							event.preventDefault();
						});
					});
					$('.delete'+bd_msg_no).each(function(){
						$(this).css('display','block');
					});
					createArea.css('display','block');
					btn.removeClass('btn-info');
					btn.addClass('btn-success');
					btnSpan.removeClass(spanClass);
					btnSpan.addClass('glyphicon glyphicon-ok');
					content.attr("contenteditable", "true");
					content.focus();
					isEditOpen=true;
					setCursorToEnd(content.get(0));
					
				}
			} else {
				if(isEditOpen){						
					swal({
						  title: "確定要完成編輯?",
					 	  text: "此舉會改動貼問內容，很危險低，請確定要不要繼續。",
						  type: "warning",
						  showCancelButton: true,
						  confirmButtonColor: "#DD6B55",
						  cancelButtonText: "算了",
						  confirmButtonText: "是的",
						  closeOnConfirm: false
					},
					function(){
						if(submitBd(path,bd_msg_no,mem_no,bd_type)){
							$('.fdi-Carousel .item').each(function() {
								var id = $(this).children(':first-child').children().children().attr('id');
								$(this).children(':first-child').children().children().attr('href',webCtx+"/util/OutputPic?photo_no="+id+"&type=big");
								$(this).children(':first-child').children().children().attr("data-fancybox","group"+id);
								$('[data-fancybox]').fancybox({
			
									clickSlide : false
								});
								var it = $(this).children(':first-child');
								var next  = $(this).next();
								var next2 = $(this).next().next();
								
								if(next2.length==0&&next.length==0){
									next = $(this).siblings(':first').children(':first-child').children().children();
									next2 =  $(this).siblings(':first').next().children(':first-child').children().children();
									it.next().children().children().click(function(){
										next.trigger('click');
									})
									it.next().next().children().children().click(function(){
										next2.trigger('click');
									})
									
								}else if(next2.length==0){
									next = next.children(':first-child').children().children();
									next2 = $(this).siblings(':first').children(':first-child').children().children();
									it.next().children().children().click(function(){
										next.trigger('click');
									})
									it.next().next().children().children().click(function(){
										next2.trigger('click');
									})
									
								}else{
									next = next.children(':first-child').children().children();
									next2 = next2.children(':first-child').children().children();
									it.next().children().children().click(function(){
										next.trigger('click');
									})
									it.next().next().children().children().click(function(){
										next2.trigger('click');
									})
								}
							});
							$('.delete'+bd_msg_no).each(function(){
								$(this).css('display','none');
							});
							createArea.css('display','none');
							btn.removeClass('btn-success');
							btn.addClass('btn-info');
							btnSpan.removeClass(spanClass);
							btnSpan.addClass('glyphicon glyphicon-pencil');
							content.attr("contenteditable", "false");
							isEditOpen=false;
						}
					});
				}
			}
			

		}
		function setCursorToEnd(ele) {
			
			var range = document.createRange();
			var sel = window.getSelection();
			range.setStart(ele, 1);
			range.collapse(true);
			sel.removeAllRanges();
			sel.addRange(range);
			ele.focus();
		}
		function submitBd(path,bd_msg_no,mem_no,bd_type){
			var data = new FormData();
			var bd_msg_ctx = $('#bd_msg_ctx'+bd_msg_no).text();
			data.append("bd_msg_ctx",bd_msg_ctx);
			$.each($('input[name=delPhoto_no'+bd_msg_no+"]:checked"),function(){
				data.append('delPhoto_no',$(this).val());
			});		
			var ifDelFilm =$('input[name=delFilm]:checked').length;
			
			$.each(bdPicList, function() {
				data.append("image", $(this)[0]);
			});
			var delStat;
			if(bdFilm!=null&&bdFilm.length!=0){
				delStat=1;
				data.append("film", bdFilm);				
			}else if(ifDelFilm==1){
				delStat=4;
			}else if(ifDelFilm==0&&bd_type==2){
				delStat=2;
			}else if(ifDelFilm==0&&bd_type==3){
				delStat=3;
			}else if(ifDelFilm==0&&bd_type==0){
				delStat=0;
			}else if(ifDelFilm==0&&bd_type==1){
				delStat=0;
			}else if(ifDelFilm==0&&bd_type==4){
				delStat=5;
			}else{
				alert('怎麼可能有意外');
			}
			$.ajax({
				type : "POST",
				url : path+"/board/BoardActionCtrl?action=update&bd_msg_no="+bd_msg_no+"&mem_no="+mem_no+"&delStat="+delStat,
				dataType : 'text',
				contentType : false,
				processData : false, //不做任何處理，只上傳原始資料	
				data : data,
				success : function(msg) {

					if (msg.length == 0) {						
						
						// 							upload_progress.html(100 + '%') ; // 控制進度條的顯示數字，例如65%
						// 		                	upload_progress.css("width",100 + '%') ; // 控制進度條的長度                        
						// 		                	upload_progress.attr('aria-valuenow', 100) ;

						swal({
							  title: "成功",
							  text: "已成功修改動態",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
						},function(){
							location.reload();
						});
						// 			        		$('.progress').css('display','none');
						// 			        		$('.progressCr').css('display','none');
// 						location.href =path+"/board/BoardShowCtrl?mem_no="+mem_no+"&thisPage=1";
						
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
		function submit(path,mem_no, friend) {
			$('#alert_bd_msg_ctx').css('display','none');
			var bd_prvt = $('#dropdownMenu1').val();
			var bd_msg_ctx = $('#bd_msg_ctx').val();
			var num = 0;
			var data = new FormData();
			var bd_type = 0;
			if (!fileList.length == 0 && film != undefined) {
				bd_type = 3;
			} else if (film != undefined) {
				bd_type = 2;
			} else if (!fileList.length == 0) {
				bd_type = 1;
			}
			$.each(fileList, function() {

				data.append("image", $(this)[0]);

			});
			data.append("film", film);
			data.append("bd_msg_ctx", bd_msg_ctx);
			data.append("bd_prvt", bd_prvt);

			var upload_progress = $('#upload_progress');			
			upload_progress.html('') ; 
	    	upload_progress.css("width",'') ;                 
	    	upload_progress.attr('aria-valuenow', '') ;
	    	if(fileList.length!=0||film!=null){
	    		$('.progress').css('display','block');
    			$('.progressCr').css('display','block');
	    	}
	    	
			$.ajax({
				type : "POST",
				url : path + "/board/BoardActionCtrl?action=insert&bd_type="
				+ bd_type ,
				dataType : 'text',
				contentType : false,
				processData : false, //不做任何處理，只上傳原始資料
				data : data,
	            progress: function(e) {	            	
	                //make sure we can compute the length
	                if(e.lengthComputable) {
	                	var intComplete = (e.loaded / e.total) * 100 | 0 ;  
	                	console.log(intComplete);
	                	upload_progress.html(intComplete + '%') ; // 控制進度條的顯示數字，例如65%
	                	upload_progress.css("width",intComplete + '%') ; // 控制進度條的長度                        
	                	upload_progress.attr('aria-valuenow', intComplete) ;
	                }
	                //this usually happens when Content-Length isn't set
	                else {
	                    console.warn('Content Length not reported!');
	                }
	            },
				success : function(msg) {

					if (msg.trim().length == 0) {
						upload_progress.html(100 + '%') ; // 控制進度條的顯示數字，例如65%
	                	upload_progress.css("width",100 + '%') ; // 控制進度條的長度                        
	                	upload_progress.attr('aria-valuenow', 100) ;
						$('#picReset').trigger('click');									
		        		$('.progress').css('display','none');
		        		$('.progressCr').css('display','none');
		        		swal({
							  title: "成功",
							  text: "已成功發布動態",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
							},function(){
								if(friend.length!=0){									
									location.href =path+"/board/BoardShowCtrl?friend=1&thisPage=1";
								}else{
									location.href =path+"/board/BoardShowCtrl?mem_no="+mem_no+"&thisPage=1";
									
								}
									
							});
						
						
					} else {
						var errorMsg = $.each(JSON.parse(msg),function(v,i){							
							$('#alert_'+v).css('display',"block");
							$('#alert_'+v).text(i);		
							
						})
						$('.progress').css('display','none');
		        		$('.progressCr').css('display','none');
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
		function setCmmtPrvt(path,mem_no,bd_msg_no,bd_prvt) {
			var self = $(this);			
			swal({
				  title: "確定要更改隱私設定嗎？",
				  text: "此舉將會改動隱私權，觀看者範圍將會改變。",
				  type: "warning",
				  showCancelButton: true,
				  confirmButtonColor: "#DD6B55",
				  confirmButtonText: "是的",
				  cancelButtonText:"算了",
				  closeOnConfirm: false
				},function(){
				
				var option = self.parents('ul').prev().find('img');
				$.ajax({
					url: path+"/board/BoardActionCtrl?action=setPrvt&mem_no="+mem_no+"&bd_msg_no="+bd_msg_no+"&bd_prvt="+bd_prvt,
					type: "POST",
					dataType: "text",
					success: function(msg){
						option.attr('src',webCtx+'/front_end/board/images/cmmtPrvt' + bd_prvt + '.png');
						swal({
							  title: "成功",
							  text: "已成功變更隱私",
							  timer: 500,
							  type: "success",
							  showConfirmButton: false
						});
					},
					error: function(xhr, ajaxOptions, thrownError){ 
						swal({
							  title: "更新失敗",
							  text: "",
							  timer: 500,
							  type: "error",
							  showConfirmButton: false
							});
				    }

				});
			});
		}
		function chooseCmmtPrvt(bd_prvt) {
			$(this).parents('ul').prev().html(
					$(this).text() + '<span class="caret"></span>');
			$(this).parents('ul').prev().val(bd_prvt);
			/*$.ajax({
				url: path+"/board/MessageBoardCtrl?bd_msg_no="+bd_msg_no+"&bd_prvt="+bd_prvt;
				type: "POST",
				dataType: "text",
				success: function(msg){

				},
				error: function(xhr, ajaxOptions, thrownError){ 
			            alert('更新失敗');
			    }

			});*/

		}
		function showCmmt(event,bd_msg_no) {
			event.preventDefault();
			$('#commt' + bd_msg_no).toggle();
		}

		var film;
		var fileList = [];
		var count = 0;
		Preview = new function() {
			var fileInput = $('#uploadPhoto');
			this.file_change = function() {
				$('#uploadPhoto').on('change', function() {
					show(this);
				});
			}
			var show = function(input) {
				if (input.files && input.files[0]) {
					each_img(input.files);
				}
			}
			this.removePic = function() {
				var id = $(this).attr('id');
				$(this).parent().parent().parent().remove();
				delete fileList[id];
			}
			var each_img = function(files) {
				$
						.each(
								files,
								function(index, file) {
									if (!file.type.match('image')) {
										swal({
											  title: "這並不是圖片",
											  text: "請換檔案再嘗試看看",
											  timer: 1000,
											  type: "error",
											  showConfirmButton: false
										});
										return;
									}
									var reader = new FileReader();
									var img = new Image();
									img.onload = function() {
										fileList[count] = [file,count];
										var pic = "<div class='col-xs-12 col-sm-2 pic'>"
												+ "<div class='list-group'><div class='list-group-item imgDiv'>"
												+ "<img src='"+webCtx+"/front_end/board/images/cross1.png' id='"
												+ (count++)
												+ "' class='delete' onclick='Preview.removePic.call(this);'/>"
												+ "<div align='center'><img alumb='true' class='pics' style='width:100%;' height=100  src='"
												+ img.src
												+ "'>"
												+ "</div></div></div></div>";
										$("#media").append(pic);
										$(".imgDiv").mouseenter(function() {
											$(this).find(".delete").show();

										});

										$(".imgDiv").mouseleave(function() {
											$(this).find(".delete").hide();
										});

									};
									reader.onload = function() {
										img.src = reader.result;
									}
									if (file) {
										reader.readAsDataURL(file);
									}
								});
			}

		}


		ShowFilm = new function() {
			var fileInput = $('#uploadFilm');
			this.file_change = function() {

				$('#uploadFilm').on('change', function() {
					show(this);
				});
			}
			var show = function(input) {
				if (input.files && input.files[0]) {
					each_img(input.files);
				}
			}
			this.removeFilm = function() {
				$(this).parent().parent().parent().remove();
				film = null;
			}
			var each_img = function(files) {
				$
						.each(
								files,
								function(index, file) {
									if (!file.type.match('video')) {
										swal({
											  title: "這並不是影片",
											  text: "請換檔案再嘗試看看",
											  timer: 1000,
											  type: "error",
											  showConfirmButton: false
										});
										return;
									}
									var reader = new FileReader();
									if (file.type.match('video')) {
										film = null;

										reader.onload = function() {
											var video = "<div class='col-xs-12 col-sm-12'>"
													+ "<div class='list-group text-center'><div class='list-group-item imgDiv'>"
													+ "<img class='delete' src='"+webCtx+"/front_end/board/images/cross1.png' "
													+ "onclick='ShowFilm.removeFilm.call(this);'/>"
													+ "<video style='height:250px; width:100%;' controls='conrtols'><source src="+
						reader.result+" type='video/mp4'></video></div></div></div>";
											$("#filmContainer").append(video);
											$(".imgDiv").mouseenter(function() {
												$(this).find(".delete").show();

											});

											$(".imgDiv").mouseleave(function() {
												$(this).find(".delete").hide();
											});
											film = file;

										}
										if (file) {
											reader.readAsDataURL(file);
										}
										return;
									}
								});

			}

		}
		function dragHandler(e) {
			e.preventDefault(); //防止瀏覽器執行預設動作
			$('#showBorder').addClass('showBorder');
		}
		leave_image
		function leave_image(e) {
			e.preventDefault(); //防止瀏覽器執行預設動作
			$('#showBorder').removeClass('showBorder');
		}
		function drop_image(e) {
			e.preventDefault(); //防止瀏覽器執行預設動作
			$('#showBorder').removeClass('showBorder');
			var files = e.dataTransfer.files; //擷取拖曳的檔案
			for (var i = 0; i < files.length; i++) {

				$
						.each(
								files,
								function(index, file) {
									if (file.type.match('video')) {
										var reader = new FileReader();
										film = null;
										reader.onload = function() {
											var video = "<div class='col-xs-12 col-sm-12'>"
													+ "<div class='list-group text-center'><div class='list-group-item imgDiv'>"
													+ "<img class='delete' src='"+webCtx+"/front_end/board/images/cross1.png' "
													+ "onclick='ShowFilm.removeFilm.call(this);'/>"
													+ "<video controls='conrtols'><source src="+
						reader.result+" type='video/mp4'></video></div></div></div>";
											$("#filmContainer").append(video);
											$(".imgDiv").mouseenter(function() {
												$(this).find(".delete").show();

											});

											$(".imgDiv").mouseleave(function() {
												$(this).find(".delete").hide();
											});
											film = file;

										}
										if (file) {
											reader.readAsDataURL(file);
										}
										return;
									}
									if (file.type.match('image')) {
										var reader = new FileReader();
										var img = new Image();
										img.onload = function() {
											fileList[count] = [file,count];
											var pic = "<div class='col-xs-12 col-sm-2 pic'>"
													+ "<div class='list-group'><div class='list-group-item imgDiv'>"
													+ "<img src='"+webCtx+"/front_end/board/images/cross1.png' id='"
													+ (count++)
													+ "' class='delete' onclick='Preview.removePic.call(this);'/>"
													+ "<div align='center'><img alumb='true' class='pics' style='width:100%;' height=100  src='"
													+ img.src
													+ "'>"
													+ "</div></div></div>";
											$("#media").append(pic);
											$(".imgDiv").mouseenter(function() {
												$(this).find(".delete").show();

											});

											$(".imgDiv").mouseleave(function() {
												$(this).find(".delete").hide();
											});

										};
										reader.onload = function() {
											img.src = reader.result;
										}
										if (file) {
											reader.readAsDataURL(file);
										}
										return;
									}
									swal({
										  title: "這啥格式",
										  text: "請換檔案再嘗試看看",
										  timer: 1000,
										  type: "error",
										  showConfirmButton: false
									});
								});
			}
		}
		function reload(){
			var fdKey = QueryString('friend');
			if(fdKey!=null&&fdKey.trim().length!=0){
				$('#FdBdTab').addClass('active');
			}else{
				$('#BdTab').addClass('active');				
			}
			$('#myCarousel').carousel({
				interval : 5000
			});
			$('.fdi-Carousel .item').each(function(){
				var img = $(this).children(':first-child');
				img.next().remove();
				img.next().remove();
			})	
			$('.fdi-Carousel .item').each(function() {
								var sibNum = $(this).siblings().length;
								var next = $(this).next();
								if (!next.length) {
									next = $(this).siblings(':first');
								}
								next.children(':first-child').clone().appendTo($(this));
								var num = $(this).children(':last-child').children().children().attr('id');
								if (sibNum > 1) {
									$(this)	.children(':last-child')
											.children()
											.children()
											.prop("href", "#")
											.removeAttr("data-fancybox")
											.click(function(event) {
														event.preventDefault();
														next.children(':first-child')
															.children()
															.children()
															.trigger('click');
													});									
								}
								if (sibNum > 1) {
									if (next.next().length > 0) {
										next.next()
											.children(':first-child')
											.clone()
											.appendTo($(this));
										$(this).children(':last-child')
												.children()
												.children()
												.prop("href","#")
												.removeAttr("data-fancybox")
												.click(function(event) {
															event.preventDefault();
															next.next()
																.children(':first-child')
																.children()
																.children()
																.trigger('click');
														});
									} else {
										$(this).siblings(':first')
												.children(':first-child')
												.clone()
												.appendTo($(this));
										$(this).children(':last-child')
												.children()
												.children()
												.prop("href","#")
												.removeAttr("data-fancybox")
												.click(function(event) {
															event.preventDefault();
															$(this).siblings(':first')
																	.children(':first-child')
																	.children()
																	.children()
																	.trigger('click');
														});
									}
		 						}

							});
	
			$('[data-fancybox]').fancybox({

				clickSlide : false,
				caption : function(instance, item) {
					return $(this).find('figcaption').html();
				}
			});
			BdPreview.file_change();
			Preview.file_change();
			ShowFilm.file_change();

		}
		
	</script>
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>