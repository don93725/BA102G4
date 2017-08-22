<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adapply.model.*"%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<title>健貨 - GymHome</title>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- 		燈箱 -->
<script src="<%=request.getContextPath() %>/style/assets/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath() %>/style/assets/js/jquery.colorbox.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/colorbox.css"/>
		
<!-- datapicker用		 -->
<!-- <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css"> -->
<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script> -->
<!-- <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script> -->
		
	<link href="<%=request.getContextPath()%>/style/bootstrap-fileinput-master/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />	
	<link href="<%=request.getContextPath()%>/style/css/fileinput.min.css" media="all" rel="stylesheet" type="text/css" />
	<script src="<%=request.getContextPath()%>/style/bootstrap-fileinput-master/js/fileinput.min.js"></script>	
</head>
<body>
<div class="container">
	<div class="row col-xs-12 col-sm-4 col-sm-offset-3">
	<table border='1' cellpadding='5' cellspacing='0' width='400'>
		<tr bgcolor='#CCCCFF' align='center' valign='middle' height='20'>
			<td>
				<h3>廣告申請 - addADD.jsp</h3>
				<h3>${user.mem_nickname},你好!!</h3>
			</td>
		</tr>
	</table>
	</div>
	<div class="col-xs-12 col-sm-4"></div>
</div>
<br><br>

	
	<c:if test="${not empty errorMsgs}">
		<font color='red'>錯誤:
			<ul>
				<c:forEach var="message" items="${errorMsgs}">
					<li>${message.value}</li>
				</c:forEach>
			</ul>
		</font>
	</c:if>
	
	
<div class="container">
	<div class="row">
		<div  class="col-xs-12 col-sm-6 col-sm-offset-2">
			<form id="form1" class="form-horizontal" method="post" action="<%=request.getContextPath()%>/adapply/AD_ApplyCtrl" name="from1" enctype="multipart/form-data">
				
				<div class="form-group">
					<label for="ad_name" class="col-xs-12 col-sm-3 control-label">
						廣告名稱:
					</label>
					<div class="col-xs-12 col-sm-9">
						<input type="text" name="ad_name" id="ad_name"  
							value="${param.ad_name}" placeholder="請輸入廣告名稱" class="form-control">
							<font color='red'>${errorMsgs.ad_name}</font>
					</div>
				</div>
		
		<c:if test="${user.mem_rank==1}">
			<jsp:useBean id="adapplyCourseSvc" scope="page" class="com.course.model.CourseService" />
			<div class="form-group">
				<label  class="col-xs-12 col-sm-3 control-label">
				</label>
				<div class="col-xs-12 col-sm-9">
					<select  name="ad_url" class="form-control">
						<c:forEach var="courseVO" items="${adapplyCourseSvc.getAll(user.mem_acc)}">
							<option value="${courseVO.crs_no}">${courseVO.crs_no}--${courseVO.crs_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</c:if>
		
		<c:if test="${user.mem_rank==2}">
			<jsp:useBean id="adapplyPlaceSvc" scope="page" class="com.place.model.PlaceService" />
			<div class="form-group">
				<label for="ad_url" class="col-xs-12 col-sm-3 control-label">
					~~選擇廣告刊登: ${adapplyPlaceSvc.placeList(user.mem_acc,"1")}~~
				</label>
				<div class="col-xs-12 col-sm-9">
					<select  name="ad_url" class="form-control">
						<c:forEach var="placeVO" items="${adapplyPlaceSvc.placeList(user.mem_acc,'1')}">
							<option value="${placeVO.p_no}">${placeVO.p_no}--${placeVO.p_name}</option>
						</c:forEach>
					</select>
				</div>
			</div>
		</c:if>
		
			<div class="form-group">
				<c:set var="nowDate" scope="page" value="<%=new java.sql.Date(System.currentTimeMillis())%>" />
				<label for="date1" class="col-xs-12 col-sm-3 control-label">
					刊登日期:
				</label>
				<div class="col-xs-12 col-sm-9">
					<input type="date" name="ad_ondate" id="date1"  min='${nowDate}'
							value="${(adApplyVO==null)?nowDate:param.ad_ondate}" class="form-control">
							<font color='red'>${errorMsgs.ad_ondate}</font>
				</div>
			</div>
		
			<div class="form-group">
				<c:set var="twDate" scope="page" value="<%=new java.sql.Date(System.currentTimeMillis()+1*24*3600*1000)%>" />
				<label for="ad_offdate" class="col-xs-12 col-sm-3 control-label">
					截止日期:
				</label>
				<div class="col-xs-12 col-sm-9">
					<input type="date" name="ad_offdate" id="date2"  min='${twDate}'
							onChange="DateMoney()"
							value="${(adApplyVO==null)?twDate:param.ad_offdate}" class="form-control">
							<font color='red'>${errorMsgs.ad_offdate} </font>
				</div>
			</div>


    		
    		<div class="form-group">
        		<label class="col-xs-12 col-sm-3 control-label">
					應付金額:
				</label>
				<div class="col-xs-12 col-sm-9">
					<font id="Money" size="50" color='red'></font>
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-xs-12 col-sm-3 control-label">
					廣告敘述:
				</label>
				<div class="col-xs-12 col-sm-9">
					<textarea rows="5"  name="ad_ctx" class="form-control"
						value="${param.ad_ctx}">
					</textarea>
				</div>		
			</div>


<!-- 			<div class="form-group"> -->
<!-- 				<label class="col-xs-12 col-sm-3 control-label"> -->
<!-- 					廣告圖片: -->
<!-- 				</label> -->
<!-- 				<div class="col-xs-12 col-sm-9"> -->
<!-- 					<input type="file" name="ad_pt" size="45" -->
<%-- 						value="${param.ad_pt}" id="file" /> --%>
<%-- 							${errorMsgs.param.ad_pt} --%>
<!-- 					</br> -->
<!-- 					<img id="img" style="width: 150px; height: 150px;"> -->
<!-- 				</div> -->
<!-- 			</div> -->

  				<div class="">
    				<input type="file" name="image" class="projectfile" value="image.png">
 				</div>

			<br> 
		
			<div class="form-group">
				<label class="col-xs-12 col-sm-3 control-label"></label>
				
				<div class="col-xs-12 col-sm-9">
<%-- 					<input type="hidden" name="ad_url" value="${user.mem_rank}">  --%>
					<input type="hidden" name="action" value="insert"> 
					<a class='inline ' href="#inline_content"><button class="btn btn-primary">繳費</button></a>
<!-- 					<input type="submit" value="送出新增"> -->
				</div>
			</div>
		
		
			</form>
		</div>
	</div>
</div>
								<!-- 	信用卡付款 -->
	<div class="container">
	<div class="row">							
	<div style='display:none'>
		<div id='inline_content'>
			<div class="demo-container">
        		<div class="card-wrapper">
        		</div>

        	<div class="form-container active" id="aabbcc">
        		<div class="form-group">
        			<label class="col-xs-12 col-sm-3 col-sm-offset-2  control-label " align="center" >
        				Card Number:
        			</label>
        			<div class="col-xs-12 col-sm-5 ">
               	 		<input placeholder="Card number" type="tel" name="number"  
               	 			class="form-control"/>
               		</div>
               		<div class="col-xs-12 col-sm-2 "></div>
               	</div>	
               	
               	<div class="form-group">
               		<label class="col-xs-12 col-sm-3 col-sm-offset-2 control-label" align="center">
        				Full Name:
        			</label>
        			<div class="col-xs-12 col-sm-5">
               			<input placeholder="Full name" type="text" name="name" class="form-control"/>
               		</div>	
               		<div class="col-xs-12 col-sm-2"></div>
               	</div>
               	<div class="form-group">
               		<label class="col-xs-12 col-sm-3 col-sm-offset-2 control-label" align="center">
        				Expiry:
        			</label>
        			<div class="col-xs-12 col-sm-5">
               			<input placeholder="MM/YY" type="tel" name="expiry" class="form-control"/>
               		</div>
               		<div class="col-xs-12 col-sm-2"></div>
               	</div>
               	<div class="form-group">
               		<label class="col-xs-12 col-sm-3 col-sm-offset-2 control-label" align="center">
        				CVC:
        			</label>
               		<div class="col-xs-12 col-sm-5">
                		<input placeholder="CVC" type="number" name="cvc" class="form-control"/>
                	</div>
                	<div class="col-xs-12 col-sm-2"></div>
       		 	</div>
       		 	<div class="form-group">
       		 	<label class="col-xs-12 col-sm-3 col-sm-offset-3 control-label">
       		 		<input type="button" class="btn btn-warning btn-lg" value="取消" onclick="$('.inline').colorbox.close();" class="form-control">
       		 	</label>
       		 	<div class="col-xs-12 col-sm-3">
       		 		<input type="button" class="btn btn-primary btn-lg" value="付款" onclick="$('#form1').submit();" class="form-control">
       		 	</div>
       		 	</div>
   			 </div>
		</div>
			</div>
	</div>
	</div>
	</div>
	
<!-- 	付款在這出現 -->
<!-- 	<div class="tab-content page" style="background-color:white;"> -->
<!-- 		<div class="col-md-12"><a class='inline' href="#inline_content"><button class="btn btn-primary">繳費</button></a></div> -->
<!-- 	</div> -->
	
	 <script src="<%=request.getContextPath()%>/style/card-master/dist/card.js"></script>
	
	<script>
	new Card({
        form: document.querySelector('#aabbcc'),
        container: '.card-wrapper'
        
//         ,placeholders: {
//         	number: '**** **** **** ****',
//         	name: 'Arya Stark',
//         	expiry: '**/****',
//         	cvc: '***'
//        }
    });
	
$(document).ready(function(){
	$(".inline").colorbox({inline:true,width:"50%"});
});


</script>	
	
	
	<script type="text/javascript">
		console.log($("#date1").val())
		$(function() {

			$('#date1').change(function() {
				var date1 = $("#date1").val();
				var date2 = $("#date2").val();

				$('#date2').attr('min', date1);
				if (date2 < date1) {
					$('#date2').attr('value', date1);
				}

			});
		});

		$(function() {
			$("#file").change(function() {
				if (this.files && this.files[0]) {
					var reader = new FileReader();

					reader.onload = function(e) {
						$('#img').attr('src', e.target.result);
					}
					reader.readAsDataURL(this.files[0]);
				}
			});
		});

		function DateMoney() {
			//定義起始 年月日
			var StartDate = $("#date1").val();
			//定義結束 年月日
			var EndDate = $("#date2").val();

			console.log('相差 ' + (DateDifference(StartDate, EndDate)) + '天'
					+ '共' + (DateDifference(StartDate, EndDate)) * 500 + '元');
			var round = Math.round((DateDifference(StartDate, EndDate)) * 500)
			$('#Money').text(" $ " + round + "元");
		}
		// 算出日期與日期間的差距有幾天
		function DateDifference(StartDate, EndDate) {

			var myStartDate = new Date(StartDate);
			var myEndDate = new Date(EndDate);

			// 天數，86400000是24*60*60*1000，除以86400000就是有幾天
			return (myEndDate - myStartDate) / 86400000;

		}
		
		
	</script>
</body>
</html>