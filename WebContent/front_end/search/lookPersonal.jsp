<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="com.album.service.*" %>
<%@ page import="com.board.service.*" %>
<%@ page import="com.friends.model.*" %>
<%@ page import="com.comments.model.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>���f - GymHome</title>
		<%@include file="/front_end/include/basicScript.file" %>
	<!-- ���ʵe -->
	<script src="dist/sweetalert-dev.js"></script>
  	<link rel="stylesheet" href="dist/sweetalert.css">

</head>
<body>

    <!-- �����C -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
	<%  	
	String paramMem_no = request.getParameter("mem_no");
	if(paramMem_no!=null){
		int albumNumber = new AlbumsService().getAlbumNumForOther(paramMem_no);
		pageContext.setAttribute("albumNumber", albumNumber);
		boolean friendShip = false;
		if(user!=null){
			friendShip = new FriendsService().checkFriendShip(paramMem_no,user.getMem_no());			
		}
		int boardNumber = 0;
		if(friendShip){
			boardNumber = new Message_boardService().getFriendsBoardNumByCondition(paramMem_no,"bd_prvt=0 or bd_prvt=1");		
		}else{
			boardNumber = new Message_boardService().getFriendsBoardNumByCondition(paramMem_no,"bd_prvt=1");			
		}
		pageContext.setAttribute("boardNumber", boardNumber);
		int friendNumber = new FriendsService().getFriendNum(paramMem_no);
		pageContext.setAttribute("friendNumber", friendNumber);
		int personCmtNumber = new Board_cmtService().getPersonCmtNumber(paramMem_no);
		pageContext.setAttribute("personCmtNumber", personCmtNumber);		
	}
%>
	<!-- ���D+�ѥ]shit -->
    <!-- Page Content -->
    <div class="container">   
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">
            <div class="col-lg-12">
                <h1>${requestScope.user.mem_nickname }���a
                    <small>${user.mem_nickname }'s Home</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="/BA102G4/front_end/index.jsp">����</a>
                     </li>
                <li class="active">�ӤH�Ŷ�</li>
                <li class="active">${requestScope.user.mem_nickname }���a</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>


<!-- �����������̮ɡA��� -->                    
<c:if test="${requestScope.user.mem_rank == '0'}">  
	<%@include file="/front_end/include/onlyForStuP.file" %>
</c:if><!-- �����������̮ɪ� Content���� -->


<!-- �������нm�ɡA��� -->                    
<c:if test="${requestScope.user.mem_rank == '1'}">  
	<%@include file="/front_end/include/onlyForCoaP.file" %>
</c:if><!-- �������нm�ɪ� Content���� -->


<!-- �����������з~�̮ɡA��� -->                    
<c:if test="${requestScope.user.mem_rank == '2'}">
	<%@include file="/front_end/include/onlyForGymP.file" %>
</c:if><!-- �����������з~�̮ɪ� Content���� -->
 
        <hr>
</div>
 
<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

<!-- �̩��h -->
	<%@include file="/front_end/include/floor.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>

<!-- googleMap Api -->
<%@include file="/front_end/include/googleMap.file" %>

</html>
