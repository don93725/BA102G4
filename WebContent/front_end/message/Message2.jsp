<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<c:if test="${not empty oneNum }">
<input type='hidden' id='oneNum' value='${oneNum }'>
</c:if>
<c:forEach var="msg" items="${oneMsg }">
<div class="col-xs-12 col-sm-12">
<c:if test="${msg.post_no.mem_no!=user.mem_no }">
<div class="col-xs-12 col-sm-2">
	<div class="row">
		<img class='img-circle msgPic' title='${msg.post_no.mem_nickname}' src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${msg.post_no.mem_no}&mem_rank=${msg.post_no.mem_rank}"">
	</div>
</div>
<div class="col-xs-12 col-sm-8">
 <div class="row well">
 	${msg.msg_ctx } 
 </div>
</div>	
<div class="col-xs-12 col-sm-2 text-left">
	<fmt:setLocale value="en_US" />
	<fmt:formatDate value="${msg.send_time}" pattern="yyyy-MM-dd" />
	<br>
	<fmt:formatDate value="${msg.send_time}" pattern="HH:mm" />
</div>
</c:if>		
<c:if test="${msg.post_no.mem_no==user.mem_no }">	
<div class="col-xs-12 col-sm-2 text-right">
	<fmt:setLocale value="en_US" />
	<fmt:formatDate value="${msg.send_time}" pattern="yyyy-MM-dd" />
	<br>
	<fmt:formatDate value="${msg.send_time}" pattern="HH:mm" />
</div>			    
<div class="col-xs-12 col-sm-8 ">
 <div class="row well">
	${msg.msg_ctx }
 </div>
</div>
<div class="col-xs-12 col-sm-2">
	<div class="row">
		<img class='img-circle msgPic' title='${msg.post_no.mem_nickname}' src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${msg.post_no.mem_no}&mem_rank=${msg.post_no.mem_rank}">
	</div>
</div>
</c:if>

</div>
</c:forEach>								

	