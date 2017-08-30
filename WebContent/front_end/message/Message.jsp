<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="com.members.model.*" %>

	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-ch-en">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
		<!--[if lt IE 9]>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/html5shiv/3.7.3/html5shiv.min.js"></script>
			<script src="https://cdnjs.cloudflare.com/ajax/libs/respond.js/1.4.2/respond.min.js"></script>
		<![endif]-->
		<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">

<style type="text/css">
	.profilePic{
		width: 80%;
		height: auto;
		margin: 10px;
	}
	.msgPic{
		width: 80%;
		height: auto;
		margin: 10px;
		margin-top: 0px; 
	}
	.msgTime{
		color: lightgray;
	}
	.msgInput{
		padding: 0px;
	}
	.msgPanel{
		height: 3000px;
	}
	a:visited {
		color: #ffffff;
	}





#container {
	background-color: black;
	position: absolute;
	min-height: 100%	;
	width: 100%;
	display:none;
	top:0;
	margin: 0px auto;
	z-index:20;
	
	align:center;
	-webkit-perspective: 1000;
}

#card {
	-webkit-transition-property: rotation;
	-webkit-transition-duration: 2s;
	-webkit-transform-style: preserve-3d;
}

#local {
	position: absolute;
	width: 60%;
	left:20%;
	-webkit-transform: scale(-1, 1);
	-webkit-backface-visibility: hidden;
}

#remote {
	position: absolute;
	width: 60%;
	left:20%;
	-webkit-transform: rotateY(180deg);
	-webkit-backface-visibility: hidden;
}

#mini {
	position: absolute;
	height: 30%;
	width: 30%;
	bottom: 0%;
	right: 0%;
	-webkit-transform: scale(-1, 1);
	opacity: 1.0;
}

#localVideo {
	opacity: 0;
	-webkit-transition-property: opacity;
	-webkit-transition-duration: 2s;
}

#remoteVideo {
	opacity: 0;
	-webkit-transition-property: opacity;
	-webkit-transition-duration: 2s;
}

#miniVideo {
	opacity: 0;
	-webkit-transition-property: opacity;
	-webkit-transition-duration: 2s;
}

#footer {
	spacing: 4px;
	position: absolute;
	bottom: 0;
	width: 100%;
	height: 28px;
	background-color: #3F3F3F;
	color: rgb(255, 255, 255);
	font-size: 13px;
	font-weight: bold;
	line-height: 28px;
	text-align: center;
}

#hangup {
	font-size: 13px;
	font-weight: bold;
	color: #FFFFFF;
	width: 128px;
	height: 30px;
	background-color: #808080;
	border-style: solid;
	border-color: #FFFFFF;
	margin: 2px;
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
		
		<div class="container" style='min-height: 100%;'>
			<div class="row">
				<div class="breadcrumbs" id="breadcrumbs">
		            <div class="col-lg-12">
		                <h1>${user.mem_nickname }的即時訊息
		                </h1>
		            </div>
		
		            <ul class="breadcrumb">
		                <li>
		                    <i class="icon-home home-icon"></i>
		                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a></li>
		                     </li>
		                <li class="active"> <a href="<%= request.getContextPath()%>/front_end/editPage/personal.jsp?action=basic">個人空間 </a></li>
		                <li class="active">即時訊息</li>		                
		            </ul><!-- .breadcrumb -->
		        </div></br>
		        <%@include file="/front_end/include/fakeTab.file" %>
				<div class="col-xs-4 col-sm-3">
					<div class="row">
						<div class="panel panel-default">
						
					  <!-- Default panel contents -->					  		 
					  		<ul class="nav nav-tabs" role="tablist" >
							    <li role="presentation" class="active" ><a href="#message" aria-controls="message" role="tab" data-toggle="tab">最新訊息</a></li>
							    <li role="presentation"><a href="#firendList" aria-controls="firendList" role="tab" data-toggle="tab">朋友列表</a></li>
						  	</ul>
						  
					  <!-- List group -->
					   <div class="tab-content pre-scrollable msgPanel">
					    <div role="tabpanel" class="tab-pane active" id="message">
					    	<ul class="list-group">
					    		<c:forEach var="newMsg" items="${lastestMsg }">
							    <li class="list-group-item hover" onclick='show("${pageContext.request.contextPath }","${(newMsg.post_no.mem_no==user.mem_no)? newMsg.rcv_no.mem_no :newMsg.post_no.mem_no}","${(newMsg.post_no.mem_no==user.mem_no)? newMsg.rcv_no.mem_nickname :newMsg.post_no.mem_nickname}");'>
								    <div class="row">								    	
								    	<div class="col-xs-12 col-sm-12">
								    		<div class="row">								    			
								    		<div class="col-xs-12 col-sm-12">
								    			${(newMsg.post_no.mem_no==user.mem_no)? newMsg.rcv_no.mem_nickname :newMsg.post_no.mem_nickname }
								    		</div>
								    		
								    	
									    <div class="col-xs-12 col-sm-10 col-sm-offset-2">
									    		<div class='row'>
									    		<div class="col-xs-12 col-sm-9">${newMsg.msg_ctx }</div>
									    		<div class="col-xs-12 col-sm-2">
									    		<c:if test="${newMsg.nr>0 }">
									    		<span class="badge" id='newMgrNum' style='background-color: red !important;'>${newMsg.nr }</span>
									    		</c:if></div>
									    		</div>
									    </div>
									    
									    <div class="col-xs-12 col-sm-8 col-sm-offset-4 msgTime">
									    	<fmt:setLocale value="en_US" />
											<fmt:formatDate value="${newMsg.send_time}"
												pattern="yyyy-MM-dd HH:mm" />
									    </div>
									    </div>
								    </div>
							    </li>
							    </c:forEach>							    
							  </ul>
					    </div>
					    <div role="tabpanel" class="tab-pane" id="firendList">
					    	<ul class="list-group">
					    		<c:forEach var='friend' items='${friendList }'>
							    <li class="list-group-item text-center fdList" onclick='show("${pageContext.request.contextPath }","${(user.mem_no!=friend.mem_no)? friend.mem_no:friend.fd_no }","${(user.mem_no!=friend.mem_no)? friend.mem_nickname:friend.fd_nickname }");'>${(user.mem_no!=friend.mem_no)? friend.mem_nickname:friend.fd_nickname }</li>
							    </c:forEach>
							  </ul>
					    </div>	
					   
					  </div>
							  


							</div>
						</div>
					</div>
				<div class="col-xs-8 col-sm-9">
					<div class="row">
						<div class="panel panel-default">
					  <!-- Default panel contents -->
					  		<div class="panel-heading" style='padding: 4px;'>
					  			<div class="row">
					  				<div class="col-xs-12 col-sm-10" id='msgName'>
							  			
							  		</div>
							  		<div class="col-xs-12 col-sm-2" style='padding: 0px;'>
							  			<button class="btn btn-default btn-xs" id='moreMsg' onclick='showMore("${pageContext.request.contextPath }");' >載入更多</button>
							  			<input type='hidden' id='thisPage'>
							  		</div>
					  			</div>	
					  		</div>
					  <!-- List group -->
							  <div class="panel-body msgContent msgPanel pre-scrollable" id='msgContent'>
							    <div class="col-xs-12 col-sm-12 ">
								    <div class="row" id='msgContent '>
								    </div>
							    </div>
							  </div>
							  <div class="panel-footer msgInput" style='display:none;background-color: transparent;'>
							    <div class="row">
							    	<div class="col-lg-12">
							    	<div class="col-xs-12 col-sm-10">
										<div class='row'>
											<div id='sendInput' class='form-group pre-scrollable' contenteditable='true' style='margin-bottom:0px;padding:5px;background-color: white;height:80px; width:100%;' ></div>
										</div>
							    	</div>
							    	<div class="col-xs-12 col-sm-1" style='margin-top:45px;'>
								      <div class='row'>
								      	<div class="input-group-btn">
								        <button type="button" onclick='trig.call(this);' class="btn btn-default dropdown-toggle" style='height:42px;width: 50px;' data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span class="caret"></span></button>
								        <ul class="dropdown-menu">
								          <li><a href="#" id='uplBtn'><span class="glyphicon glyphicon-folder-open"></span>&nbsp傳檔</a></li>
								          <li><a href="#" onclick='phoneCall();'><span class="glyphicon glyphicon-facetime-video"></span>&nbsp視訊</a></li>
								        </ul>
								      </div><!-- /btn-group -->
								      <div class="input-group-btn">
								        <button type="button" class="btn btn-primary" onclick="send(${user.mem_no});"><span class="glyphicon glyphicon-share-alt"></span>&nbspSend</button>
								        <input type='hidden' id='sendWho'>
								        <input type='file' id='uplInput' style='display:none;' mutiple>
								      </div>
								      </div>
							    	</div>
								  </div><!-- /.col-lg-6 -->
							    </div>
							  </div>
						</div>
					</div>
				</div>
			</div>
		</div>	
			<div id="container" class='container' align='center' ondblclick="enterFullScreen()">
		<div id="card">
			<div id="local">
				<video width="100%" height="100%" id="localVideo"
					autoplay="autoplay" />
			</div>
			<div id="remote">
				<video width="100%" height="100%" id="remoteVideo"
					autoplay="autoplay"> </video>
				<div id="mini">
					<video width="100%" height="100%" id="miniVideo"
						autoplay="autoplay" />
				</div>
			</div>
		</div>
		<div id="footer"></div>

	</div>
		<input type='hidden' id='rcv'>
		<input type='hidden' id='initiator'>
		<input type='hidden' id='pastUser_no' value='${user.mem_no }'>
		<input type='hidden' id='user_nickname' value='${user.mem_nickname }'>
		<input type='hidden' id='mem_rank' value='${user.mem_rank }'>
		<script src="https://code.jquery.com/jquery.js"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
		<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>	
		<script type="text/javascript">
		function trig(){
			if($(this).parent().hasClass('open')){
				$(this).parent().removeClass('open');
			}else{
				$(this).parent().addClass('open');
			}			
				
		}
		function show(path,post_no,mem_nickname){
			if(post_no=='0'){
				$('.msgInput').hide();	
			}else{
				$('.msgInput').show();				
			}
			$('#msgContent').empty();
			$('#sendWho').val(post_no);
			$('#thisPage').val(1);
			$('#msgName').text(mem_nickname);
			$('#moreMsg').removeClass('disabled').removeAttr('disabled');
			$('#sendInput').empty();
			var negNum = parseInt($('#newMgrNum').text(),10);
			var oriNrNum = parseInt($('#nrNum').text(),10);
			if(oriNrNum-negNum>0){
				$('#nrNum').text(oriNrNum-negNum);				
			}else{
				$('#nrNum').hide();
			}
			
			load(path,post_no);
			refresh();
		}
		function refresh(){
			$.ajax({
				type : "POST",
				url : webCtx + "/message/MessageCtrl",
				dataType : 'text',
				data: {
					"action" : "refresh"
				},
				success : function(msg) {					
					if (msg.length != 0) {
						$('#message').empty();
						$('#message').append(msg);
						$.each($('.hover'),function(){
							$(this).hover(function(){
								$(this).addClass('active');
							},function(){
								$(this).removeClass('active');
							})
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
		function showMore(path){
			var num = parseInt($('#thisPage').val(),10);
			if(num+1<=$('#oneNum').val()){
				$('#thisPage').val(num+1);
				load(path,$('#sendWho').val(),$('#thisPage').val());
			}
			
		}
		function send(user_no){
			var rcv_no = $('#sendWho').val();
			var msg_ctx = $('#sendInput').html();
			$('#sendInput').empty();
			
		}
		function load(path,post_no,thisPage){
			if(thisPage==undefined){
				thisPage = 1;
			}
			$.ajax({
				type : "POST",
				url : path + "/message/MessageCtrl",
				dataType : 'text',
				data: {
					"action" : "getOne",					
					"post_no":post_no,
					"thisPage": thisPage
				},
				success : function(msg) {					
					
					if (msg.length != 0) {
						$('#msgContent').prepend(msg);
						if($('#thisPage').val()==1){
							var $div = $('#msgContent');  
							$div.scrollTop($div[0].scrollHeight); 
						}
						if($('#oneNum').val()==$('#thisPage').val()){
							$('#moreMsg').addClass('disabled').attr('disabled',"");
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
	    var webCtx ;
	    $(function(){
	    	$('#msgTab').addClass('active');
	    	var path = window.location.pathname;
		    webCtx = path.substring(0, path.indexOf('/', 1));
	    })
		$(function(){
			connect($('#pastUser_no').val());
			Preview.file_change();
			$("#sendInput").keypress(function(e){
				  code = (e.keyCode ? e.keyCode : e.which);
				  if (code == 13)
				  {
				      send();
				  }
			});
			var path = window.location.pathname;
		    webCtx = path.substring(0, path.indexOf('/', 1));
			$('#uplBtn').click(function(){
				$('#uplInput').trigger('click');
			});
			$.each($('.fdList'),function(){
				$(this).hover(function(){
					$(this).addClass('active');
				},function(){
					$(this).removeClass('active');
				})
			});
			$.each($('.hover'),function(){
				$(this).hover(function(){
					$(this).addClass('active');
				},function(){
					$(this).removeClass('active');
				})
			});
		})
		Preview = new function() {
			var fileInput = $('#uplInput');
			this.file_change = function() {
				$('#uplInput').on('change', function() {
					show(this);
				});
			}
			var show = function(input) {
				if (input.files && input.files[0]) {
					each_img(input.files);
				}
			}			
			var each_img = function(files) {
				$.each(files,function(index, file) {
						if (file.type.match('image')) {
							var reader = new FileReader();
							var img = new Image();
							img.onload = function() {
								
								var pic ="<img style='width:200px;' height=100  src='"
										+ img.src
										+ "'>";
								$("#sendInput").append(pic);								

							};
							reader.onload = function() {
								img.src = reader.result;
							}
							if (file) {
								reader.readAsDataURL(file);
							}
						}
						if (file.type.match('video')) {
							film = null;
							var reader = new FileReader();
							reader.onload = function() {
								var video = "<video style='height:50px; width:20%;' controls='conrtols'><source src="+
										reader.result+" type='video/mp4'></video>";
								$("#sendInput").append(video);							
								

							}
							if (file) {
								reader.readAsDataURL(file);
							}
							return;
						}
					});
			}

		}
	    var MyPoint = "/MyWebSocketServer/";
	    var host = window.location.host;
	    var path = window.location.pathname;
	    var webCtx = path.substring(0, path.indexOf('/', 1));
	    var endPointURL = "wss://" + window.location.host + webCtx + MyPoint;
		var statusOutput = document.getElementById("statusOutput");
		var webSocket;
		
		function connect(user_no) {
			// 建立 websocket 物件
			webSocket = new WebSocket(endPointURL+user_no);			
			webSocket.onopen = function(event){
				
			};

			webSocket.onmessage = function(event) {
				console.log(event.data);
				var type = JSON.parse(event.data).type;
		        var obj = JSON.parse(event.data).obj;
		        var user_no = $('#pastUser_no').val();
		        var text = '<div class="col-xs-12 col-sm-12">';
		        var rcv_no = $('#sendWho').val();
		        if(type.trim()=='text'){
			        if(rcv_no==obj.post_no.mem_no){		        	
				        if(obj.post_no.mem_no!=user_no ){
				        	text += '<div class="col-xs-12 col-sm-2"><div class="row"><img class="img-circle msgPic" title="'+obj.post_no.mem_nickname+
				        	'" src="'+webCtx+'/util/OutputPic?mem_no='+obj.post_no.mem_no+'&mem_rank='+obj.post_no.mem_rank+'"></div></div><div class="col-xs-12 col-sm-8">'+
					        '<div class="row well">'+obj.msg_ctx +'</div></div><div class="col-xs-12 col-sm-2 text-left">'+obj.day+"<br>"+obj.date+
					        ' </div>';
				        }
				        $.ajax({
							type : "POST",
							url : webCtx + "/message/MessageCtrl",
							dataType : 'text',
							data: {
								"action" : "clear",
								"rcv_no" : user_no,
								"post_no": obj.post_no.mem_no
							},
							success : function(msg) {
							},
							error : function(xhr, ajaxOptions, thrownError) {							
							}
	
						});
			        }
			        if(obj.post_no.mem_no==user_no ){
			        	text += '<div class="col-xs-12 col-sm-2 text-right">'+obj.day+"<br>"+obj.date+
			        	'</div><div class="col-xs-12 col-sm-8 "><div class="row well">'+obj.msg_ctx+
			         	'</div></div><div class="col-xs-12 col-sm-2"><div class="row"><img class="img-circle msgPic" '+
			         	' title="'+obj.post_no.mem_nickname+'" src="'+webCtx+'/util/OutputPic?mem_no='+obj.post_no.mem_no+
			         	'&mem_rank='+obj.post_no.mem_rank+'"></div></div></div>';
					}		
			        $('#msgContent').append(text);
			        refresh();		        	
		        }
		        if(type.trim()=='phone'){
		        	console.log('收到信息123 : ' + message);					
					if (isRTCPeerConnection)
						processSignalingMessage(obj);//建立视频连接
					else
						processSignalingMessage00(obj);
		        }
		        if(type.trim()=='call'){
		        	var nickname = obj.nickname;
		        	var rcv_no = obj.rcv_no;
		        	var mem_rank = obj.mem_rank;
		        	swal({
		        		  title: nickname+"來電耶",
		        		  text: "你要接嗎？",
		        		  imageUrl: webCtx+'/util/OutputPic?mem_no='+rcv_no+"&mem_rank="+mem_rank,
		        		  showCancelButton: true,
		        		  confirmButtonColor: "#DD6B55",
		        		  confirmButtonText: "接聽",
		        		  cancelButtonText: "拒接",
		        		  closeOnConfirm: false,
		        		  closeOnCancel: false
		        		},
		        		function(isConfirm){
		        		  if (isConfirm) {
		        			    $('#rcv').val(obj.rcv_no);
		  						$('#initiator').val("1");
		  						initialize();
		  						swal.close();
		        		  } else {
		        			  var message = {"type":"stopCall","rcv_no" : obj.rcv_no};
		        				webSocket.send(JSON.stringify(message));		        		       
		        				swal.close();
		        		        
		        		  }
		        		});
		        		
					
		        }
		        if(type.trim()=='stopCall'){
		        	stopCall();
		        }
		        if(type.trim()=='stopWait'){
		        	var nickname = obj.nickname;		        
		        	swal({
	        		  title: "錯過"+nickname+"的來電",
	        		  text: "你一定是故意的吧 ，我不會跟其他人講，哈哈",
	        		  imageUrl: webCtx+'/front_end/message/images/secret.jpg',
					  confirmButtonText: "對阿我故意的",
	        		  closeOnConfirm: true
	        		});
		        }
		         
		        
		        	
			};

			webSocket.onclose = function(event) {
				console.log('123');
			};
		}		
		
		var inputUserName = $('#sendInput');
		inputUserName.focus();
		
		function send() {
		    var inputMessage = $('#sendInput');
		    var message = inputMessage.html();
		    
		    if (message === ""){
		        alert ("訊息請勿空白!");
		        inputMessage.focus();	
		    }else{
		    	var user_no =$('#pastUser_no').val();
		    	var rcv_no = $('#sendWho').val();
				var msg_ctx = $('#sendInput').html();
		        var jsonObj = {"type":"textMsg","post_no" : user_no,"rcv_no": rcv_no, "msg_ctx" : msg_ctx};
		        webSocket.send(JSON.stringify(jsonObj));
		        $('#sendInput').empty();
		        inputMessage.focus();
		    }
		}

		
		function disconnect () {
			webSocket.close();			
		}

		var localVideo;
		var miniVideo;
		var remoteVideo;
		var localStream;
		var remoteStream;
		var channel;
		var channelReady = true;
		var pc;
		var initiator;
		var started = false;
		var rcv;
		var isRTCPeerConnection = true;
		var mediaConstraints = {
			audio:true,
			video:true
		};
		var isVideoMuted = false;
		var isAudioMuted = false;
		function stopCall(){
			$('#container').css('display','none');
			started = false;
			isRTCPeerConnection = true;
			isAudioMuted = false;
			isVideoMuted = false;
			if(pc!=null)
			pc.close();
			pc = null;
			if(localVideo!=undefined)
			localVideo.style.opacity = 0;
			localVideo.src = "";
			miniVideo.src = "";
			remoteVideo.src = ""
			if(localStream!=undefined)
			var track = localStream.getTracks()[0];
			track.stop();
			if(remoteStream!=undefined)
			track = remoteStream.getTracks()[0];
			track.stop();
			localStream = null;
			remoteStream = null;
			
			remoteVideo.style.opacity = 0;
			miniVideo.style.opacity = 0;
			swal({
        		  title:"已結束通話",
        		  text: "可以開始期待下一次通話",
        		  imageUrl: webCtx+'/front_end/message/images/phone.png',		  		        		
				  timer: 1000,
				  showConfirmButton: false
        		});
			
			
		}
		function phoneCall(){
			$('#rcv').val($('#sendWho').val());
			var temp = $('#sendWho').val();
			$('#initaitor').val(0);
			initialize();
			var self_no = $('#pastUser_no').val();
			var user_nickname = $('#user_nickname').val();
			var mem_rank = $('#mem_rank').val();
			message = {"type":"phoneCall","rcv_no":temp,"post_no":self_no,"nickname":user_nickname,"mem_rank":mem_rank};
			webSocket.send(JSON.stringify(message));
			swal({
      		  title: "正在撥號..",
      		  text: "要有耐心，他一定會接的...吧。",
      		  imageUrl: webCtx+'/front_end/message/images/ring.gif',
      		  confirmButtonColor: "#DD6B55",
      		  confirmButtonText: "不等了",
      		  closeOnConfirm: false,
      		},
      		function(){
      			stopCall();
      			var user_nickname = $('#user_nickname').val();
      			var message = {"type":"stopWait","rcv_no" : temp,"user_nickname":user_nickname};
				webSocket.send(JSON.stringify(message));
				swal({
	        		  title: "哭哭，"+user_nickname+"沒接",
	        		  text: "他可能在忙拉，你不要難過",
	        		  imageUrl: webCtx+'/front_end/message/images/crying.png',		  		        		
					  timer: 1000,
					  showConfirmButton: false
	        	});
      		});
			
		}
		function offerfail(){
			console.log("offerfailed");
			
		}
		
		function answerfail(){
			console.log("answerfail");
		}
		
		function initialize() {
			$('#container').css('display','block');
			card = document.getElementById("card");
			localVideo = document.getElementById("localVideo");
			miniVideo = document.getElementById("miniVideo");
			remoteVideo = document.getElementById("remoteVideo");
			initiator = $('#initiator').val();
			rcv = $('#rcv').val();
			resetStatus();
			getUserMedia();
			
		}
		
		function getUserMedia() {
			try {
				navigator.webkitGetUserMedia({
					'audio' : true,
					'video' : true
				}, onUserMediaSuccess, onUserMediaError);
				console.log("Requested access to local media with new syntax.");
			} catch (e) {
				try {
					navigator.webkitGetUserMedia("video,audio",
							onUserMediaSuccess, onUserMediaError);
					console
							.log("Requested access to local media with old syntax.");
				} catch (e) {
					alert("webkitGetUserMedia() failed. Is the MediaStream flag enabled in about:flags?"+ e.message);
					console.log("webkitGetUserMedia failed with exception: "
							+ e.message);
				}
			}
		}
		
		function onUserMediaSuccess(stream) {
			console.log("User has granted access to local media.");
			var url = URL.createObjectURL(stream);
			localVideo.style.opacity = 1;
			localVideo.src = url;
			localStream = stream;
			// Caller creates PeerConnection.
			if (parseInt(initiator,10))
				maybeStart();
		}
		
		function maybeStart() {
			if (!started && localStream && channelReady) {
				setStatus("<input type=\"button\" id=\"hangup\" value=\"離開\" onclick=\"onHangup()\" />");
				console.log("Creating PeerConnection.");
				createPeerConnection();
				console.log("Adding local stream.");
				pc.addStream(localStream);
				started = true;
				// Caller initiates offer to peer.
				if (parseInt(initiator,10))
					doCall();
			}
		}

		function doCall() {
			console.log("Sending offer to peer.");
			if (isRTCPeerConnection) {
				console.log("offer is RTCPeerConnection");
				pc.createOffer(setLocalAndSendMessage, offerfail, mediaConstraints);
			} else {
				console.log("offer not RTCPeerConnection");
				var offer = pc.createOffer(mediaConstraints);
				pc.setLocalDescription(pc.SDP_OFFER, offer);
				sendMessage({
					type : 'offer',
					sdp : offer.toSdp()
				});
				pc.startIce();
			}
		}

		function setLocalAndSendMessage(sessionDescription) {
			console.log("seting local and send message");
			pc.setLocalDescription(sessionDescription);
			sendMessage(sessionDescription);
		}

		function sendMessage(message) {
			
			message = {"type":"msg","rcv_no":rcv,"data":message};
			console.log('發出信息 : ' + JSON.stringify(message));
			
			webSocket.send(JSON.stringify(message));
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

		function resetStatus() {
			setStatus("");
			
		}

		function createPeerConnection() {
			var pc_config = {
				"iceServers" : [ {
					"url" : "stun:stun.l.google.com:19302"
				} ]
			};
			try {
				pc = new webkitRTCPeerConnection(pc_config);
				pc.onicecandidate = onIceCandidate;
				console.log("Created webkitRTCPeerConnnection with config \""
						+ JSON.stringify(pc_config) + "\".");
			} catch (e) {
				try {
					var stun_server = "";
					if (pc_config.iceServers.length !== 0) {
						stun_server = pc_config.iceServers[0].url.replace(
								'stun:', 'STUN ');
					}
					pc = new webkitPeerConnection00(stun_server,
							onIceCandidate00);
					isRTCPeerConnection = false;
					console
							.log("Created webkitPeerConnnection00 with config \""
									+ stun_server + "\".");
				} catch (e) {
					console.log("Failed to create PeerConnection, exception: "
							+ e.message);
					alert("Cannot create PeerConnection object; Is the 'PeerConnection' flag enabled in about:flags?");
					return;
				}
			}

			pc.onconnecting = onSessionConnecting;
			pc.onopen = onSessionOpened;
			pc.onaddstream = onRemoteStreamAdded;
			pc.onremovestream = onRemoteStreamRemoved;
		}

		function setStatus(state) {
			footer.innerHTML = state;
		}

		function doAnswer() {
			console.log("Sending answer to peer.");
			if (isRTCPeerConnection) {
				console.log("answer is RTCPeerConnection");
				pc.createAnswer(setLocalAndSendMessage,answerfail);
				//这里有问题，要看看createanswer这个要怎么用。
				//pc.createAnswer(setLocalAndSendMessage, answerfail, mediaConstraints);
				//pc.createAnswer(function(){console.log("success")},function(){console.log("fail")},mediaConstraints);
				//pc.createAnswer().then(pc.setLocalDescription(sessionDescription)).then(sendMessage(sessionDescription));
			} else {
				console.log("answer not RTCPeerConnection");
				var offer = pc.remoteDescription;
				var answer = pc.createAnswer(offer.toSdp(), mediaConstraints);
				pc.setLocalDescription(pc.SDP_ANSWER, answer);
				sendMessage({
					type : 'answer',
					sdp : answer.toSdp()
				});
				pc.startIce();
			}
		}

		function processSignalingMessage00(message) {
			console.log(message);			

			var msg = JSON.parse(message).msg;
			
			// if (msg.type === 'offer') should not happen here.
			if (msg.type === 'answer' && started) {
				pc.setRemoteDescription(pc.SDP_ANSWER, new SessionDescription(
						msg.sdp));
			} else if (msg.type === 'candidate' && started) {
				var candidate = new IceCandidate(msg.label, msg.candidate);
				pc.processIceMessage(candidate);
			} 
		}


		
		function processSignalingMessage(message) {
			var msg = message;

			if (msg.type === 'offer') {
				// Callee creates PeerConnection
				if (!initiator && !started)
					maybeStart();

				// We only know JSEP version after createPeerConnection().
				if (isRTCPeerConnection)
					pc.setRemoteDescription(new RTCSessionDescription(msg));
				else
					pc.setRemoteDescription(pc.SDP_OFFER,
							new SessionDescription(msg.sdp));

				doAnswer();
			} else if (msg.type === 'answer' && started) {
				pc.setRemoteDescription(new RTCSessionDescription(msg));
			} else if (msg.type === 'candidate' && started) {
				var candidate = new RTCIceCandidate({
					sdpMLineIndex : msg.label,
					candidate : msg.candidate
				});
				pc.addIceCandidate(candidate);
			}
		}
		


		function onUserMediaError(error) {
			console.log("Failed to get access to local media. Error code was "
					+ error.code);
			alert("Failed to get access to local media. Error code was "
					+ error.code + ".");
		}

		function onIceCandidate(event) {
			if (event.candidate) {
				sendMessage({
					type : 'candidate',
					label : event.candidate.sdpMLineIndex,
					id : event.candidate.sdpMid,
					candidate : event.candidate.candidate
				});
			} else {
				console.log("End of candidates.");
			}
		}

		function onIceCandidate00(candidate, moreToFollow) {
			if (candidate) {
				sendMessage({
					type : 'candidate',
					label : candidate.label,
					candidate : candidate.toSdp()
				});
			}

			if (!moreToFollow) {
				console.log("End of candidates.");
			}
		}

		function onSessionConnecting(message) {
			console.log("Session connecting.");
		}
		function onSessionOpened(message) {
			console.log("Session opened.");
		}

		function onRemoteStreamAdded(event) {
			console.log("Remote stream added.");
			var url = URL.createObjectURL(event.stream);
			miniVideo.src = localVideo.src;
			remoteVideo.src = url;
			remoteStream = event.stream;
			waitForRemoteVideo();
			if(swal!=null)
			swal.close();
		}
		function onRemoteStreamRemoved(event) {
			console.log("Remote stream removed.");
		}

		function onHangup() {
			var rcv = $('#rcv').val();
			var message = {"type":"stopCall","rcv_no" : rcv};
			webSocket.send(JSON.stringify(message));
			stopCall();
			transitionToDone();

		}
		window.onbeforeunload = function() {
			var rcv = $('#rcv').val();
			var message = {"type":"stopCall","rcv_no" : rcv};
			webSocket.send(JSON.stringify(message));
		}



		function waitForRemoteVideo() {
			if (remoteStream.videoTracks!=null && remoteStream.videoTracks.length === 0
					|| remoteVideo.currentTime > 0) {
				transitionToActive();
			} else {
				setTimeout(waitForRemoteVideo, 100);
			}
		}
		function transitionToActive() {
			remoteVideo.style.opacity = 1;
			card.style.webkitTransform = "rotateY(180deg)";
			setTimeout(function() {
				localVideo.src = "";
			}, 500);
			setTimeout(function() {
				miniVideo.style.opacity = 1;
			}, 1000);
			setStatus("<input type=\"button\" id=\"hangup\" value=\"Hang up\" onclick=\"onHangup()\" />");
		}
		function transitionToWaiting() {
			card.style.webkitTransform = "rotateY(0deg)";
			setTimeout(function() {
				localVideo.src = miniVideo.src;
				miniVideo.src = "";
				remoteVideo.src = ""
			}, 500);
			miniVideo.style.opacity = 0;
			remoteVideo.style.opacity = 0;
			resetStatus();
		}
		function transitionToDone() {
			localVideo.style.opacity = 0;
			remoteVideo.style.opacity = 0;
			miniVideo.style.opacity = 0;
		}
		function enterFullScreen() {
			container.webkitRequestFullScreen();
		}

		function toggleVideoMute() {
			if (localStream.videoTracks.length === 0) {
				console.log("No local video available.");
				return;
			}

			if (isVideoMuted) {
				for (i = 0; i < localStream.videoTracks.length; i++) {
					localStream.videoTracks[i].enabled = true;
				}
				console.log("Video unmuted.");
			} else {
				for (i = 0; i < localStream.videoTracks.length; i++) {
					localStream.videoTracks[i].enabled = false;
				}
				console.log("Video muted.");
			}

			isVideoMuted = !isVideoMuted;
		}

		function toggleAudioMute() {
			if (localStream.audioTracks.length === 0) {
				console.log("No local audio available.");
				return;
			}

			if (isAudioMuted) {
				for (i = 0; i < localStream.audioTracks.length; i++) {
					localStream.audioTracks[i].enabled = true;
				}
				console.log("Audio unmuted.");
			} else {
				for (i = 0; i < localStream.audioTracks.length; i++) {
					localStream.audioTracks[i].enabled = false;
				}
				console.log("Audio muted.");
			}

			isAudioMuted = !isAudioMuted;
		}


		// Send BYE on refreshing(or leaving) a demo page
		// to ensure the room is cleaned for next session.


		// Ctrl-D: toggle audio mute; Ctrl-E: toggle video mute.
		// On Mac, Command key is instead of Ctrl.
		// Return false to screen out original Chrome shortcuts.
		document.onkeydown = function() {
			if (navigator.appVersion.indexOf("Mac") != -1) {
				if (event.metaKey && event.keyCode === 68) {
					toggleAudioMute();
					return false;
				}
				if (event.metaKey && event.keyCode === 69) {
					toggleVideoMute();
					return false;
				}
			} else {
				if (event.ctrlKey && event.keyCode === 68) {
					toggleAudioMute();
					return false;
				}
				if (event.ctrlKey && event.keyCode === 69) {
					toggleVideoMute();
					return false;
				}
			}
		}
	</script>
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>