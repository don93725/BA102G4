<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
	<head>
		<title>健貨 - GymHome</title>
		<%@include file="/front_end/include/registerStyle.file" %>	
	</head>

	<body class="login-layout">
		<div class="main-container">
			<div class="main-content">
				<div class="row">
					<div class="col-sm-10 col-sm-offset-1">
						<div class="login-container">
							<div class="center">
								<h1>
									<i class="icon-leaf green"></i>
									<span class="red">健貨</span>
									<span class="white">會員註冊系統</span>
								</h1>
								<h4 class="blue">&copy; Company Name</h4>
							</div>
							
							<div class="space-6"></div>

							<div class="position-relative">
							
							<!--教練註冊-->
								<div id="signup-box-coaches" class="signup-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header orange lighter bigger">
												<i class="icon-group orange"></i>
												教練註冊
											</h4>
											<input type="button" id="smallButton" onclick="magic()" value="神奇小按鈕">&nbsp<i class="icon-arrow-left"></i>&nbsp點我快速新增資料
											<div class="space-6"></div>
											<p style="color: red"><b>*為必填欄位</b></p>
											<form method="post" action="<%= request.getContextPath()%>/CoachesServlet" enctype="multipart/form-data">
											<p>*姓名:&nbsp<b style="color: red">${errorMsgs.coa_name}</b></p>
												<fieldset>			
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[(\u4e00-\u9fa5)(a-zA-Z)]{2,6}$" title="請輸入2-6個大小寫中英文" required minlength="2" maxlength="6"
															class="form-control" id="coa_name" name="coa_name" placeholder="請輸入真實姓名，以便日後資料核對用"
															value="${param.coa_name}"/>
															<i class="icon-user"></i>
														
														</span>									
													</label>

											<p>*暱稱:&nbsp<b style="color: red">${errorMsgs.mem_nickname}</b></p>
												<fieldset>			
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[(\u4e00-\u9fa5)(a-zA-Z0-9)]{1,15}$" title="請輸入1-15個大小寫中英文數字" required minlength="1" maxlength="15"
															class="form-control" id="mem_nickname" name="mem_nickname" placeholder="網站顯示之暱稱，請輸入1-15個大小寫中英文數字"
															value="${param.mem_nickname}"/>
															<i class="icon-user"></i>
														</span>
													</label>
											
											<p>*帳號:&nbsp<b style="color: red">${errorMsgs.mem_acc}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[(a-zA-Z0-9_)]{6,20}$" title="請輸入6-20個大小寫英數字(含_)，請勿輸入特殊符號" required  minlength="6" maxlength="20"
															class="form-control" id="mem_acc" name="mem_acc" placeholder="請輸入6-20個大小寫英數字(含_)"
															value="${param.mem_acc}"/>
															<i class="icon-user"></i>
														</span>
													</label>

											<p>*密碼:&nbsp<b style="color: red">${errorMsgs.coa_psw}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password"
															pattern="^[(a-zA-Z0-9_)]{6,20}$" title="請輸入6-20個大小寫英數字(含_)，請勿輸入特殊符號" required minlength="6" maxlength="20"
															class="form-control" id="coa_psw" name="coa_psw" placeholder="請輸入6-20個大小寫英數字(含_)"
															value="${param.coa_psw}"/>
															<i class="icon-lock"></i>
														</span>
													</label>

											<p>*確認密碼:&nbsp<b style="color: red">${errorMsgs.coa_psw_ck}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="password" required minlength="6" maxlength="20"
															class="form-control" id="coa_psw_ck" name="coa_psw_ck" placeholder="請再次輸入密碼"
															value="${param.coa_psw_ck}"/>
															<i class="icon-retweet"></i>
														</span>
													</label>

											<p>*性別:&nbsp<b style="color: red">${errorMsgs.coa_sex}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right" value="${param.coa_sex}">
														<input type="radio" id="coa_sex" name="coa_sex" value="1" required>男生&nbsp&nbsp&nbsp
														<input type="radio" name="coa_sex" value="2" required>女生
														</span>
													</label>

											<p>*身分證號:&nbsp<b style="color: red">${errorMsgs.coa_id}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="text"
															pattern="^[A-Z]{1}[1-2]{1}[0-9]{8}$" title="請輸入首字為大寫英文的合格身分證號" required minlength="10" maxlength="10"
															class="form-control" id="coa_id" name="coa_id" placeholder="首字請輸入大寫英文字"
															value="${param.coa_id}" />
															<i class="icon-user"></i>
														</span>
													</label>

											<p>*信箱:&nbsp<b style="color: red">${errorMsgs.coa_mail}</b></p>
													<label class="block clearfix">
														<span class="block input-icon input-icon-right">
															<input type="email"
															pattern="^[_a-z0-9-]+([.][_a-z0-9-]+)*@[a-z0-9-]+([.][a-z0-9-]+)*$" title="ex.abcdefg@gmail.com" required maxlength="50"
															class="form-control" id="coa_mail" name="coa_mail" placeholder="請輸入您的電子郵件"
															value="${param.coa_mail}"/>
															<i class="icon-envelope"></i>
														</span>
													</label>

											<p>*自我介紹:&nbsp<b style="color: red">${errorMsgs.coa_into}</b></p>
															<textarea rows="5"
															required  minlength="1" maxlength="500"
															class="form-control limited" id="form-field-9" name="coa_into" placeholder="輸入完整的自我介紹有助於您的曝光率"
															>${param.coa_into}</textarea>
											<p></p>	

											<p>*頭像:&nbsp<b style="color: red">${errorMsgs.coa_pic}</b></p>
												<input type="button" class="btn btn-primary btn-xs" value="我要上傳圖片" id="updatePic" onclick="fileUpdate();">
												<input type="button" class="btn btn-primary btn-xs" value="我要拍張照片" id="takePic" onclick="openCam();">
	
	<p></p>
		<div class="cropped" id="head_img" style="display:none"></div>
		<input type="hidden" name="cropped_pic" id="cropped">
	<p></p>
	
	<div id="picPanel1" style="display:none">
	<p></p>
	<div class="imageBox">
			<div class="thumbBox"></div>
  		</div>
  		<div class="action"> 
			<div class="new-contentarea tc"> 
				<a href="javascript:void(0)" class="upload-img">
					<label for="upload-file">上傳圖片</label>
	  			</a>
	  			<input type="file" name="upload-file" id="upload-file" accept="iamge/*"/>
			</div>
				<input type="button" id="btnZoomIn" class="btn btn-default-a btn-xs" value="+"  >
				<input type="button" id="btnZoomOut" class="btn btn-default-a btn-xs" value="-" >
				<input type="button" id="btnCrop"  class="btn btn-default-a btn-xs" value="鎖定">
  		</div>
  	</div>
  	
	<!-- 自拍 -->
	<center>
	<div id="picPanel2" style="display:none">
	<div id="my_camera"></div>
	<!--自拍按鈕列表 -->
		<p></p>
		<div id="pre_take_buttons">
			<input type=button value="拍照  &gt; &gt;" onClick="preview_snapshot()" class="btn btn-default-a btn-xs">
		</div>
	</div>
	</center>
	<p></p>
												<p></p>
												<div>
													<label class="block">
														<input type="checkbox"
														required
														class="ace" id="coa_term"/>
														<span class="lbl">
															我接受
															<a href="#">使用者條款</a>
														</span>
													</label>
												</div>

													<div class="space-24"></div>
												
													<div class="clearfix">
														<button type="reset" class="width-30 pull-left btn btn-sm">
															<i class="icon-refresh"></i>
															重新填寫
														</button>
														
														<input type="hidden" name="action" value="insert">
														<button type="submit" class="width-65 pull-right btn btn-sm btn-warning">
															送出
															<i class="icon-arrow-right icon-on-right"></i>
														</button>
													</div>
												</fieldset>
											</form>

										</div>
										<div class="toolbar center">
											<a href="<%= request.getContextPath()%>/front_end/login.jsp" class="back-to-login-link">
												<i class="icon-arrow-left"></i>
												返回登入
											</a>
										</div>
									</div><!-- /widget-body -->
								</div><!-- /signup-box -->
								
							</div><!-- /position-relative -->
						</div>
					</div><!-- /.col -->
				</div><!-- /.row -->
			</div>
		</div><!-- /.main-container -->


		<!-- 神奇小按鈕 -->
		<script type="text/javascript">
			function magic(){
				document.getElementById("coa_name").value = "花沐嵐";
				document.getElementById("mem_nickname").value = "父不詳的妻子";
				document.getElementById("mem_acc").value = "coa111";
				document.getElementById("coa_psw").value = "coa111";
				document.getElementById("coa_psw_ck").value = "coa111";
				document.getElementById("coa_sex").checked = true ;
				document.getElementById("coa_id").value = "A123456789";
				document.getElementById("coa_mail").value = "gymhome9453@gmail.com";
				document.getElementById("form-field-9").value = "專長：有氧。想說的話：逆境中盛開的花最珍貴，也最美麗。";
				document.getElementById("coa_term").checked = true ;
			}
		</script>
		<%@include file="/front_end/include/registerJS.file" %>
		<%@include file="/front_end/include/picture.file" %>
</body>
</html>
							