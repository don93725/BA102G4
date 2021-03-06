<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<!--<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content=""> -->

 <title>健貨 - GymHome</title>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.play.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.dom.js"></script>
  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/libraries/p5.sound.js"></script>

  <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/front_end/game/p5-js-play/sketch.js"></script>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	<div id = "bgCanvas" style="position:absolute;z-index:-1;"></div>  
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>

    <!-- Page Content -->
    <div class="container" style="background-color:transparent;min-height: 100%;">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs" style="background-color:transparent;">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            
            <div class="col-lg-12">
                <h1>找場地
                    <small>Places</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%=request.getContextPath()%>/front_end/CCM/index.jsp">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">找場地</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        

            <div class="col-lg-12">
                <div class="row" style="background-color:transparent;">
				<form action="<%=request.getContextPath()%>/CPM/CoachesPlaceManager.do" method="post">
                    <div class="col-lg-2">
                            <input type="text" name="p_name" id="p_name" placeholder="輸入名稱" style="height:42px;">
                    </div>

                    <div class="col-lg-2">
                            <input type="text" name="p_add" id="p_add" placeholder="輸入地址" style="height:42px;">
                    </div>

                    <div class="col-lg-2">
                            <select class="form-control" name="p_cap" id="p_cap" style="height:42px;">
                                <option value="null">選擇人數</option>
                                <option name="" value="0-20">0-20</option>
								<option name="" value="20-40">20-40</option>
								<option name="" value="40-100">40-100</option>
                            </select>
                    </div>
		
                    
                    <div class="col-lg-1">
                    	<input type="button" class="btn btn-info" id="sendSelectbtn"  value="送出條件">
                        <button class="btn btn-info" id="sendSelect" style="display:none;">送出條件</button>
                    </div>

                    <div class="col-lg-1">
                    	<input type="button" class="btn btn-warning" id="readAll"  value="查全部">
                        <input type="reset" class="btn btn-default" id="sendSelectReset" value="重置" style="display:none";>
                        <input type="hidden" name="action" value="placeSelectList">
                    </div>
				</form>
                </div>
            </div>
			
			<br><br><br>
            <!--table開始-->
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-xs-12 col-sm-12">
                        <div class="table-responsive">
                            <table id="sample-table-1" class="table table-hover" style="background-color:transparent;text-align:center;">
                                <thead class="aaa">
                                    <tr>
                                        <th class="center" style="vertical-align:middle;background-color:#CCEEFF;">容納人數</th>
                                        <th class="center" style="vertical-align:middle;background-color:#CCEEFF;">場館名稱</th>
                                        <th class="center" style="vertical-align:middle;background-color:#CCEEFF;">地址</th>
                                        <th class="center" style="vertical-align:middle;background-color:#CCEEFF;">訂金/尾款</th>
                                        <th class="center" style="vertical-align:middle;background-color:#CCEEFF;">預定按鈕</th>
                                     </tr>
                                </thead>

                                <tbody>
									<c:forEach var="place_timeVO" items="${plist}">
	                                    <tr style="font-weight:bold;">
	                                        <td class="center" style="vertical-align:middle;" align="center;"><span class="label label-xs label-info arrowed-in">${place_timeVO.placeVO.p_cap}人</span></td>
	                                        <td style="vertical-align:middle;" align="center;">${place_timeVO.placeVO.p_name}</a></td>
	                                        <td style="vertical-align:middle;" align="center;">${place_timeVO.placeVO.p_add}</a></td>
	                                        <td style="vertical-align:middle;color:#CC0000;" align="center;">$${place_timeVO.pbu_price} / $${place_timeVO.pau_price}</td>
	                                        <td style="vertical-align:middle;" align="center;"><a href="<%= request.getContextPath() %>/PlaceInfoServlet?p_no=${place_timeVO.pt_no}&action=lookPlaceInfoByP"><button class="btn btn-inverse">預訂/觀看詳情</button></a></td>
	                                    </tr>
									</c:forEach>
                                 </tbody>
                                
                            </table>
                        </div><!-- /.table-responsive -->
                    </div><!-- /col-sm-12 -->
                </div><!-- /row -->
            </div>
            <!--table結束-->
        

        <hr>

        <!-- Footer -->


    </div>
    <!-- /.container -->


  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>



</html>
<script>

window.onload = function(){
	$("#sendSelectbtn").click(sendSelect);
	$("#readAll").click(readAll);
}

$(function(){
    $('#rp_date').datepicker({
                dateFormat: 'yy-mm-dd'
    });
});

function sendSelect(){
	if($("#p_name").val() == '' && $("#p_add").val() == '' && $("select[name='p_cap']").val() == 'null'){
		swal("未選擇條件", "Please choose at least one condition!", "error");
	}else{
		$("#sendSelect").click();
	}
}
function readAll(){
	$("#sendSelectReset").click();
	$("#sendSelect").click();
}


</script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/CCM/dist/sweetalert-dev.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/front_end/CCM/dist/sweetalert.min.js"></script>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front_end/CCM/dist/sweetalert.css" />
<script type="text/javascript"
	src="http://code.jquery.com/jquery-1.9.1.js"></script>
<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/themes/smoothness/jquery-ui.css" />
<script type="text/javascript" src="https://code.jquery.com/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>
