<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>

    <title>���f - GymHome</title>
	<%@include file="/front_end/include/basicScript.file" %>
	<%@include file="/front_end/include/registerStyle.file" %>

</head>

<body>

    <!-- �����C -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
	
	<!-- ���D+�ѥ]shit -->
    <!-- Page Content -->
    <div class="container">   
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            
            <div class="col-lg-12">
                <h1>${user.mem_nickname }���a
                    <small>${user.mem_nickname }'s Home</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="/BA102G4/front_end/index.jsp">����</a>
                     </li>
                <li class="active">�ӤH�Ŷ�</li>
                <li class="active">�򥻸��</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>

<!-- ���ܤj�Y�K�O�c -->
	<%@include file="/front_end/include/changeHeadPic.file" %>
<!-- ���ܤj�Y�K�O�c���� -->

<!-- �����������̮ɡA��� -->                    
<c:if test="${user.mem_rank == '0'}">  
		<%@include file="/front_end/include/personal_stu.file" %>
</c:if><!-- �����������̮ɪ� Content���� -->

<!-- �������нm�ɡA��� -->                    
<c:if test="${user.mem_rank == '1'}">  
        <%@include file="/front_end/include/personal_coa.file" %>
</c:if><!-- �������нm�ɪ� Content���� -->

<!-- �����������з~�̮ɡA��� -->                    
<c:if test="${user.mem_rank == '2'}">
        <%@include file="/front_end/include/personal_gym.file" %>
</c:if><!-- �����������з~�̮ɪ� Content���� -->

    <hr>

    </div>
      
	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<%@include file="/front_end/include/registerJS.file" %>
	<!-- �ק���s -->
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
            	panControl: true, //�n���n�X�{�i�H�W�U���k���ʪ����O
            	zoomControl: true, //�n���n�X�{�i�H��j�Y�p�����O
            	mapTypeControl: true, //�����a���˵����������O
            	scaleControl: true, //��Ҥظ�T
            	streetViewControl: true,  //��ܵ󴺪A�Ȫ����O
            	overviewMapControl: true, //�`���Ϫ����O�A�b�k�U���Ӷ}�X���ϥ�
            	zoom: 15 //�Y�񪺤j�p�A0-18�A0�̤p�A18�̤j
        };
        var map = new google.maps.Map(document.getElementById('map'), mapOptions);
		
     	// marker
     	var image = '/BA102G4/style/images/mapicon.png';
     	var beachMarker = new google.maps.Marker({
            position: new google.maps.LatLng(latlng.split(",")[0],latlng.split(",")[1]),
            map: map,
            icon: image, //�ϥ�
            title: '�ڦb�o��!!!', //title
            animation: google.maps.Animation.BOUNCE //�ʵe
        });
     	//���ʰʵe
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
	<!-- �򥻸�ƭק� -->
	<%@include file="/front_end/include/changeBasicData.file" %>
</html>