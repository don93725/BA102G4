<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>

    <title>健貨 - GymHome</title>
	<%@include file="/front_end/include/basicScript.file" %>
	<%@include file="/front_end/include/registerStyle.file" %>

</head>


<body>

    <!-- 導覽列 -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
	
	<!-- 標題+麵包shit -->
    <!-- Page Content -->
    <div class="container">   
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            
            <div class="col-lg-12">
                <h1>${user.mem_nickname }的家
                    <small>${user.mem_nickname }'s Home</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="/BA102G4/front_end/index.jsp">首頁</a>
                     </li>
                <li class="active">個人空間</li>
                <li class="active">基本資料</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>


<!-- 身分為健身者時，顯示 -->                    
<c:if test="${user.mem_rank == '0'}">  
		<%@include file="/front_end/include/personal_stu.file" %>
</c:if><!-- 身分為健身者時的 Content結束 -->


<!-- 身分為教練時，顯示 -->                    
<c:if test="${user.mem_rank == '1'}">  
        <%@include file="/front_end/include/personal_coa.file" %>
</c:if><!-- 身分為教練時的 Content結束 -->


<!-- 身分為健身房業者時，顯示 -->                    
<c:if test="${user.mem_rank == '2'}">
        <%@include file="/front_end/include/personal_gym.file" %>
</c:if><!-- 身分為健身房業者時的 Content結束 -->

    <hr>
        
    </div>
      
	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>
	
	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<%@include file="/front_end/include/registerJS.file" %>
	<!-- 修改按鈕 -->
	<%@include file="/front_end/include/modiButton.file" %>
	<c:if test="${user.mem_rank == '2'}">
<!-- googleMap Api -->
<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyAp7jp6Eg1dQZxn6Fi6h4V34jLnbipVfVU&callback=initMap&language=zh-TW"
    async defer></script>
<script>
    function initMap() {
    	var latlng = document.getElementById("gym_latlng").value;
    	// map
    	var mapOptions = {
            	center: new google.maps.LatLng(latlng.split(",")[0],latlng.split(",")[1]),
            	panControl: true, //要不要出現可以上下左右移動的面板
            	zoomControl: true, //要不要出現可以放大縮小的面板
            	mapTypeControl: true, //切換地圖檢視類型的面板
            	scaleControl: true, //比例尺資訊
            	streetViewControl: true,  //顯示街景服務的面板
            	overviewMapControl: true, //總覽圖的面板，在右下有個開合的圖示
            	zoom: 15 //縮放的大小，0-18，0最小，18最大
        };
        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
		
     	// marker
     	var image = '/BA102G4/style/images/mapicon.png';
     	var beachMarker = new google.maps.Marker({
            position: new google.maps.LatLng(latlng.split(",")[0],latlng.split(",")[1]),
            map: map,
            icon: image, //圖示
            title: '我在這裡!!!', //title
            animation: google.maps.Animation.BOUNCE //動畫
        });
     	//跳動動畫
        beachMarker.addListener('click', function() {
        	map.setZoom(18);
        	map.setCenter(beachMarker.getPosition());
        });
        
        map.addListener('center_changed', function() {
            // 3 seconds after the center of the map has changed, pan back to the
            // marker.
        	window.setTimeout(function() {
        		map.panTo(beachMarker.getPosition());
        	}, 5000);
        });
    }
</script>
</c:if>
<!-- 動畫 -->
<script>
	function tick(){
	/* 身分為健身者時，loading為 */
	if(document.getElementById("rankColor").value == 0){  	
		var self = $(this).parents("#form_stu");
		swal({
			title: "修改中",
			text: "請稍後",
			imageUrl: "/BA102G4/style/images/p2.gif",
			timer: 2500,
			showConfirmButton: false
		},function (){
			self.submit();
		});
	}
	/* 身分為教練時，loading為 */
	if(document.getElementById("rankColor").value == 1){
		var self = $(this).parents("#form_coa");
		swal({
			title: "修改中",
			text: "請稍後",
			imageUrl: "/BA102G4/style/images/p3.gif",
			timer: 2500,
			showConfirmButton: false
		},function (){
			self.submit();
		});
	}
	/* 身分為健身房業者時，loading為 */
	if(document.getElementById("rankColor").value == 2){
		var self = $(this).parents("#form_gym");
		swal({
			title: "修改中",
			text: "請稍後",
			imageUrl: "/BA102G4/style/images/p4.gif",
			timer: 2500,
			showConfirmButton: false
		},function (){
			self.submit();
		});
	}
	}
</script>
</html>
