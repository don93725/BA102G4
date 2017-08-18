<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
	<title>這是標題</title>
		<%@include file="include/basicScript.file" %>
</head>


<body>

    <!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            	<img class="logo" src="<%= request.getContextPath() %>/style/images/logo.gif">
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li class="navbar_icon">
                        <a href="index.jsp">首頁</a>
                    </li>
                    <input type="hidden" id="rankColor" value="${user.mem_rank }">

                     <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">服務介紹 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="about.jsp">關於我們</a>
                            </li>
                            <li>
                                <a href="#">公告消息</a>
                            </li>
                            <li>
                                <a href="knowledges.jsp">健身知識</a>
                            </li>
                            <li>
                                <a href="find_coaches.jsp">找教練</a>
                            </li>
                            <li>
                                <a href="find_pleaces.jsp">找場地</a>
                            </li>
                            <li>
                                <a href="find_courses.jsp">找課程</a>
                            </li>
                        </ul>
                    </li>
                            
                    <li class="navbar_icon">
                        <a href="#">討論專區</a>
                    </li>
                    <li class="navbar_icon">
                        <a href="#">直播專區</a>
                    </li>
                   
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">客服專區 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li class="navbar_icon">
                                <a href="faq.jsp">常見問題</a>
                            </li>
                            <li class="navbar_icon">
                                <a href="contact.jsp">聯絡我們</a>
                            </li>
                        </ul>
                    </li>
     
<!-- 身分為訪客時，顯示 -->                    
<c:if test="${empty user.mem_rank }">
					<!-- 個人空間 -->
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">會員專區 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="login.jsp">請先登入會員!</a>
                            </li>
                        </ul>
                    </li>
                </ul>
				
				<!-- -登入註冊 -->
                <ul class="nav navbar-nav navbar-right">
                    <li class="navbar_icon"><a href="login.jsp"><span class="glyphicon glyphicon-user"></span> 註冊</a></li>
                    <li class="navbar_icon"><a href="login.jsp"><span class="glyphicon glyphicon-log-in"></span> 登入</a></li>
                </ul>
</c:if>

<!-- 身分為健身者時，顯示 -->                    
<c:if test="${user.mem_rank == '0'}">
					<!-- 個人空間 -->
					<li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">個人空間 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="personal.jsp">基本資料</a>
                            </li>
                            <li>
                                <a href="#">相簿</a>
                            </li>
                            <li>
                                <a href="#">行事曆</a>
                            </li>
                            <li>
                                <a href="#">我的收藏</a>
                            </li>
                            <li>
                                <a href="#">訊息</a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- 課程管理 -->
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">課程管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">選課記錄查詢</a>
                            </li>
                            <li>
                                <a href="#">請假申請</a>
                            </li>
                            <li>
                                <a href="#">課程回饋</a>
                            </li>
                            <li>
                                <a href="#">課程檢舉</a>
                            </li>
                            <li>
                                <a href="#">評價教練</a>
                            </li>
                        </ul>
                    </li>
                </ul>
				
				<!-- 登出 -->
                <ul class="nav navbar-nav navbar-right">
                    <li class="navbar_icon">
                    	<a href="#">
                    		<span class="glyphicon glyphicon-user"></span>
                    	 	${user.mem_nickname}&nbsp學員，你好
                    	</a>
                    </li>
                    <li class="navbar_icon">
                    		<form method="post" action="<%= request.getContextPath() %>/StudentsServlet">
                    			<span class="glyphicon glyphicon-log-out"></span>
                    			<input type="hidden" name="action" value="logout">
                    			<input type="submit" value="登出">
                    		</form>
                    </li>
                </ul>
</c:if>

<!-- 身分為教練時，顯示 -->                    
<c:if test="${user.mem_rank == '1'}" var="identity"> 
                    <!-- 個人空間 -->
					<li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">個人空間 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="personal.jsp">基本資料</a>
                            </li>
                            <li>
                                <a href="#">相簿</a>
                            </li>
                            <li>
                                <a href="#">行事曆</a>
                            </li>
                            <li>
                                <a href="#">我的收藏</a>
                            </li>
                            <li>
                                <a href="#">留言板</a>
                            </li>
                            <li>
                                <a href="#">訊息</a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- 課程管理 -->
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">課程管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">課程上/下架</a>
                            </li>
                            <li>
                                <a href="#">課程紀錄查詢</a>
                            </li>
                            <li>
                                <a href="#">課程報表查詢</a>
                            </li>
                            <li>
                                <a href="#">課程Q&A</a>
                            </li>
                            <li>
                                <a href="#">開課通知</a>
                            </li>
                            <li>
                                <a href="#">檢舉學員</a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- 直播管理 -->
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">直播管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">直播上/下架</a>
                            </li>
                            <li>
                                <a href="#">直播管理</a>
                            </li>
                            <li>
                                <a href="#">直播報表查詢</a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- 廣告申請 -->
                    <li>
                        <a href="#">廣告申請</a>
                    </li>
                    
                    <!-- 場地租借 -->
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">場地租借 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">付訂金</a>
                            </li>
                            <li>
                                <a href="#">付尾款</a>
                            </li>
                            <li>
                                <a href="#">檢舉場地</a>
                            </li>
                        </ul>
                    </li>
                </ul>

                <!-- 登出 -->
                <ul class="nav navbar-nav navbar-right">
                    <li class="navbar_icon">
                    	<a href="#">
                    		<span class="glyphicon glyphicon-user"></span>
                    	 	${user.mem_nickname}&nbsp教練，你好
                    	</a>
                    </li>
                    <li class="navbar_icon">
                    		<form method="post" action="<%= request.getContextPath() %>/CoachesServlet">
                    			<span class="glyphicon glyphicon-log-out"></span>
                    			<input type="hidden" name="action" value="logout">
                    			<input type="submit" value="登出">
                    		</form>
                    </li>
                </ul>
</c:if>

<!-- 身分為健身房業者時，顯示 -->                    
<c:if test="${user.mem_rank == '2'}" var="identity"> 
					<!-- 個人空間 -->
					<li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">個人空間 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="personal.jsp">基本資料</a>
                            </li>
                            <li>
                                <a href="#">相簿</a>
                            </li>
                            <li>
                                <a href="#">行事曆</a>
                            </li>
                            <li>
                                <a href="#">留言板</a>
                            </li>
                            <li>
                                <a href="#">訊息</a>
                            </li>
                        </ul>
                    </li>
                    
                    <!-- 廣告申請 -->
                    <li>
                        <a href="#">廣告申請</a>
                    </li>
                    
                    <!-- 租借管理 -->
                    <li class="dropdown navbar_icon">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">租借管理 <b class="caret"></b></a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="#">申請者管理</a>
                            </li>
                            <li>
                                <a href="#">場地上/下架</a>
                            </li>
                            <li>
                                <a href="#">租借紀錄查詢</a>
                            </li>
                            <li>
                                <a href="#">租借報表查詢</a>
                            </li>
                            <li>
                                <a href="#">檢舉教練</a>
                            </li>
                        </ul>
                    </li>
                </ul>
				
				<!-- 登出 -->
                <ul class="nav navbar-nav navbar-right">
                    <li class="navbar_icon">
                    	<a href="#">
                    		<span class="glyphicon glyphicon-user"></span>
                    	 	${user.mem_nickname}&nbsp健身房業者，你好
                    	</a>
                    </li>
                    <li class="navbar_icon">
                    		<form method="post" action="<%= request.getContextPath() %>/GymsServlet">
                    			<span class="glyphicon glyphicon-log-out"></span>
                    			<input type="hidden" name="action" value="logout">
                    			<input type="submit" value="登出">
                    		</form>
                    </li>
                </ul>
</c:if>

            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

    <!-- Page Content -->
    <div class="container">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            <script type="text/javascript">
                try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
            </script>
            
            <div class="col-lg-12">
                <h1>找課程
                    <small>Courses</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="#">首頁</a>
                     </li>
                <li class="active">服務介紹</li>
                <li class="active">找課程</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        

            <div class="col-lg-12">
                <div class="row">

                    <div class="col-lg-2">
                            <select class="form-control" id="form-field-select-1">
                                <option value="">1.選擇地區</option>
                                <option value="">台北</option>
                                <option value="">台中</option>
                                <option value="">高雄</option>                   
                            </select>
                    </div>

                    <div class="col-lg-2">
                            <select class="form-control" id="">
                                <option value="">2.選擇分區</option>
                                <option value="">萬華區</option>
                                <option value="">中山區</option>
                                <option value="">信義區</option>                
                            </select>
                    </div>

                    <div class="col-lg-2">
                            <select class="form-control" id="">
                                <option value="">3.選擇種類</option>
                                <option value="">重訓</option>
                                <option value="">有氧</option>
                                <option value="">瑜珈</option>                
                            </select>
                    </div>

                    <div class="col-lg-2">
                            <select class="form-control" id="">
                                <option value="">4.選擇時段</option>
                                <option value="">上午</option>
                                <option value="">下午</option>
                                <option value="">晚上</option>                
                            </select>
                    </div>

                    <div class="col-lg-2">
                        <button class="btn btn-info">查看結果</button>
                    </div>

                    <div class="col-lg-2">
                        <button class="btn btn-default">重置條件</button>
                    </div>

                </div>
            </div>

            
            <br><br><br>
            <!--table開始-->
            <div class="col-lg-12">
                <div class="row">
                    <div class="col-xs-12 col-sm-12">
                        <div class="table-responsive">
                            <table id="sample-table-1" class="table table-striped">
                                <thead class="aaa">
                                    <tr>
                                        <th class="center">圖片</th>
                                        <th>場館</th>
                                        <th>課程名稱&時段</th>
                                        <th>價格</th>
                                        <th>預定按鈕</th>
                                     </tr>
                                </thead>

                                <tbody>

                                    <tr>
                                        <td class="center"><span class="label label-xs label-warning arrowed-in">公告</span></td>
                                        <td><a href="#">健身工廠XX場</a></td>
                                        <td>開心樂活，17健身</td>
                                        <td>$4,500</td>
                                        <td><button class="btn btn-inverse">預定</button></td>
                                    </tr>

                                    <tr>
                                        <td class="center"><span class="label label-xs label-success arrowed-in">公告</span></td>
                                        <td><a href="#">健身工廠XX場</a></td>
                                        <td>李商隱/無題</td>
                                        <td>$900</td>
                                        <td><button class="btn btn-inverse">預定</button></td>
                                    </tr>

                                 </tbody>
                                
                            </table>
                        </div><!-- /.table-responsive -->
                    </div><!-- /col-sm-12 -->
                </div><!-- /row -->
            </div>
            <!--table結束-->
        

        <hr>

        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Your Website 2014</p>
                </div>
            </div>
        </footer>

    </div>
    <!-- /.container -->

</body>

<!--按鈕產生對話框-->
<script type="text/javascript">
    jQuery(function($) {
        $('[data-rel=tooltip]').tooltip();
    });
</script>

<!--navbar淡入-->
<script type="text/javascript">
	$(document).ready(function() {
		$("li.navbar_icon").fadeIn(1000);
	});
</script>

<!--下拉式選單滑動 -->
<script type="text/javascript">
$(document).ready(function() {
	$("li.navbar_icon").on('click', function () {
		$.each($("li.navbar_icon"),function(){
			$(this).children("ul.dropdown-menu")
			.slideUp('fast');
		});
		$(this).children("ul.dropdown-menu")
		.slideToggle('fast');
	});
});

</script>

<!-- 教練排行榜特效 未完成 
<scriput type="text/javascript">
$(document).ready(function() {
	$(window).scroll(function() {
		$('div.item-container').slideDown('slow');
	});
});
</scriput>
-->

<script>
function init(){
	<!-- 身分為健身者時，navbar顏色 -->
	if(document.getElementById("rankColor").value == 0){  	
		$("nav.navbar").css("background-color", "#87B87F");
	}
	<!-- 身分為教練時，navbar顏色 -->
	if(document.getElementById("rankColor").value == 1){
		$("nav.navbar").css("background-color", "#FFB752");
	}
	<!-- 身分為健身房業者時，navbar顏色 -->
	if(document.getElementById("rankColor").value == 2){
		$("nav.navbar").css("background-color", "#6FB3E0");
	}
	if(document.getElementById("rankColor").value == ''){
		$("nav.navbar").css("background-color", "#303030");
	}
}
	window.onload = init();
</script>

</html>