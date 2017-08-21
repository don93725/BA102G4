<%@ page contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.adapply.model.*"%>
<%@ page import="java.util.*"%>



<!DOCTYPE html>
<html>
<head>
<title>AD_Apply</title>


<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>


<script src="<%=request.getContextPath() %>/style/assets/js/jquery-1.10.2.min.js"></script>
<script src="<%=request.getContextPath() %>/style/assets/js/jquery.colorbox.js"></script>

<link rel="stylesheet" href="<%=request.getContextPath()%>/style/assets/css/colorbox.css"/>

<%-- <script src="<%=request.getContextPath() %>/style/dist/sweetalert.min.js"></script> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/style/dist/sweetalert.css"> --%>

<!-- <script type="Text/JavaScript" -->
<!-- 	src="http://ajax.googleapis.com/ajax/libs/jquery/1.6/jquery.min.js"></script> -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</head>

<body>
    
起始日期：<input type="text" id="start" />
結束日期：<input type="text" id="end" />
<script type="text/javascript">
$(function(){

	  var today = new Date();
	  var tomorrow = new Date(today.getTime() + 24 * 60 * 60 * 1000);

	  $('#end').datepicker();
	  $('#start').datepicker({
	    //minDate: 0, //從今天後日期才可選
	    minDate: tomorrow, //從明天日期才可選
	    onSelect: function (dat, inst) {
	      $('#end').datepicker('option', 'minDate', dat);
	    }
	  });
	});


	$.datepicker.setDefaults({ dateFormat: 'yy-mm-dd' }); //全局設置日期格式
</script>

	<div style='display:none'>
		<div id='inline_content'>
			<div class="demo-container">
        		<div class="card-wrapper">
        		</div>

        		<div class="form-container active" id="aabbcc">
               	 	卡號:<input placeholder="Card number" type="tel" name="number"><br>
               		名字:<input placeholder="Full name" type="text" name="name"><br>
               		年分:<input placeholder="MM/YY" type="tel" name="expiry"><br>
                	CVC:<input placeholder="CVC" type="number" name="cvc">
       		 	</div>
   			 </div>
		</div>
	</div>
	
	
	<button>Show lightbox</button>
	
	
<div class="container">
	<div class="row">
		<div  class="col-xs-12 col-sm-6 col-sm-offset-2">
			<form class="form-horizontal" method="post" action="<%=request.getContextPath()%>/adapply/AD_ApplyCtrl" name="from1" enctype="multipart/form-data">
				
				
				
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

				<br> 
		
				<div class="form-group">
					<label class="col-xs-12 col-sm-3 control-label"></label>
					<div class="col-xs-12 col-sm-9">
<!-- 						<input type="hidden" name="action" value="insert">  -->
						<a class='inline' href="#inline_content"><button class="btn btn-primary">繳費</button></a>
<!-- 						<input type="submit" value="新增" id="payMoney"> -->
					</div>
				</div>
		
		
			</form>
		</div>
	</div>
</div>



	<div class="tab-content page" style="background-color:white;">
		<div class="col-md-12"><a class='inline' href="#inline_content"><button class="btn btn-primary">繳費</button></a></div>
	</div>
	
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
	

</body>
	<script>
	$(document).ready(function(){
 $('button').on('click', function(){
  $.colorbox({
   html : '<h1>Hello lightbox!!!</h1>', //在燈箱中要顯示的html字段
   width : 700, //燈箱中間區塊的寬度
   height : 600, //燈箱中間區塊的高度
   onClosed : function(){ //當燈箱關閉時的callback funtion
    alert('Lightbox is closed');
   }
  });
 });
})();
	

	</script>
</html>