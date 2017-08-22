<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>
  	<div class="container" style='min-height: 100%;'>
			   <div class="breadcrumbs" id="breadcrumbs">
            
	            <div class="col-lg-12">
	                <h1>好友列表 </h1>
	            </div>
	
	            <ul class="breadcrumb">
	                <li>
	                    <i class="icon-home home-icon"></i>
	                        <a href="/BA102G4/front_end/index.jsp">首頁</a></li>
	                     </li>
	                     <c:if test='${empty param.mem_rank}'>
	                	<li class="active"> <a href="<%= request.getContextPath()%>/front_end/editPage/personal.jsp?action=basic">個人空間 </a></li>
	                     </c:if>
	                     <c:if test='${not empty param.mem_rank}'>
	                	<li class="active"> <a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=${param.mem_rank}&mem_no=${param.mem_no}&action=lookPersonal">個人空間 </a></li>
	                     </c:if>
	                	<li class="active">好友列表</li>
	            </ul><!-- .breadcrumb -->
	        </div>
	        <br>
		<%@include file="/front_end/include/fakeTab.file" %>
			
		<div class="row">
			<div class="col-xs-12 col-sm-12" >
					<ul class="list-group" style='margin-left: 0px;'>
			<c:if test="${empty friendPageList }">
			<li class="list-group-item text-center">目前尚未加入好友</li>
			</c:if>
			<c:forEach var="friend" items="${friendPageList }">
					<li class="list-group-item">
							<div class="row">
							<c:set var="mem_no" value="${(friend.mem_no==param.mem_no)? friend.fd_no:friend.mem_no}"/>							
							<c:set var="mem_nickname" value="${(friend.mem_no==param.mem_no)? friend.fd_nickname:friend.mem_nickname}"/>							
							<c:set var="mem_rank" value="${(friend.mem_no==param.mem_no)? friend.fd_rank:friend.mem_rank}"/>							
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=${mem_rank }&mem_no=${mem_no}&action=lookPersonal">						
							<div class="col-xs-12 col-sm-3 " >
							<img src='${pageContext.request.contextPath }/util/OutputPic?mem_no=${mem_no}&mem_rank=${mem_rank}'  class="img-thumbnail" title='${mem_nickname }'>
							</div>
							</a>
							<div class="col-xs-12 col-sm-5" >
							${mem_nickname }<p></p>
							<c:if test="${mem_rank==0 }">學員</c:if>
							<c:if test="${mem_rank==1 }">教練</c:if>
							<c:if test="${mem_rank==2 }">健身房</c:if>
							<c:if test="${mem_rank==3 }">管理員</c:if>
							<p></p>
							<fmt:setLocale value="en_US" />
							<fmt:formatDate value="${friend.fd_date}" pattern="yyyy-MM-dd" />成為好友
							</div>
							<div class="col-xs-12 col-sm-3" >
							<p></p>
							<p></p>
							<c:if test="${param.mem_no==user.mem_no }">
							<button class='btn btn-danger btn-lg' onclick='delFriend("${mem_no}","${pageContext.request.contextPath }")'>刪除好友</button>
							</c:if>
							<p></p>
							<p></p>
							</div>
							</div>
					</li>
			</c:forEach>
					</ul>
			</div>
			</div>
			</div>
  	
  	
  	
  	
  	
  	
  	
  	
  	
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
<script type="text/javascript">
$(function(){
	$('#friend').addClass('active');
})
function delFriend(mem_no,path){
	 $.ajax({
         url: path+"/friends/FirendCtrl",
         data: {
         	action:"delete",
         	"mem_no":mem_no
         } ,
         type:"POST",
         dataType:'text',
         success: function(msg){
             if(msg.trim().length==0){
             	swal({
							  title: "刪除成功",
							  text: "已成功刪除好友",
							  timer: 1000,
							  type: "success",
							  showConfirmButton: false
					},function(){
						location.reload();
					});
             }else{	
					swal({
					  title: "刪除錯誤",
					  text: "請稍後再試",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
             }
         },
			error:function(xhr, ajaxOptions, thrownError){ 
	                swal({
					  title: "添加錯誤",
					  text: "請稍後再試",
					  timer: 1000,
					  type: "error",
					  showConfirmButton: false
					});
          }
  });
}
</script>  	
</html>