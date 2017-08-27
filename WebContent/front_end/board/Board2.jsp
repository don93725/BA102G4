<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


				
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
									     <ul class="list-group pre-scrollable" style=' vertical-align:middle;max-height:88%; height:88%;'>
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
														
														<div class="col-xs-12 col-sm-8 cmt" >
															<span class='a' style='padding:50px; margin-top:50px; padding-right: 0px;'>${comment.bd_cmt_ctx }</span>
															<input type='text' class='b' value='${comment.bd_cmt_ctx }' style='display:none;' onfocus='this.value = this.value;'/>
															<span class='c' ><a href="#" style='margin-left: 3px' onclick='addPhotoCmtLikes.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }");'><span class='cmtLikes'>${(comment.ifClick)?'收回讚':'讚'}</span><span style='margin-left: 5px;'>${(comment.cmt_likes>0)? comment.cmt_likes:''  }</span></a></span>
												
														</div>
														<div class="col-xs-12 col-sm-2 cmt">
														<c:if test="${user.mem_no==comment.mem_no.mem_no }">
														<a href='#' onclick='editPhotoCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }","${comment.mem_no.mem_no}");' style='color:black'>
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