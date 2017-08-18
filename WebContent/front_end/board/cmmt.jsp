<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:forEach var="comment" items="${photo.comments }" varStatus="cmt">
<li class="list-group-item comments key_photo_${photo.photo_no}_<fmt:formatNumber type="number" value="${(cmt.index-cmt.index%5)/5 }" />" ${(cmt.count>5)? 'style="display:none"':'' }>
<div class="row">
	<div class="container">
		<a href="#">
			<div class="col-xs-12 col-sm-1">

				<img
					src="${pageContext.request.contextPath}/util/OutputPic?mem_no=${comment.mem_no.mem_no}&mem_rank=${comment.mem_no.mem_rank}"
					class="img-circle cmt_mem_pic" title="${comment.mem_no.mem_nickname }" style='z-index: 10;'>

			</div>
		</a>
		<div class="col-xs-12 col-sm-8 cmt" >
			<span class='a' style='padding:30px; padding-right: 0px;'>${comment.bd_cmt_ctx }</span>
			<input type='text' class='b' value='${comment.bd_cmt_ctx }' style='display:none;' onfocus='this.value = this.value;'/>
			<span class='c' ><a href="#" style='margin-left: 3px' onclick='addPhotoCmtLikes.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }");'><span class='cmtLikes'>${(comment.ifClick)?'收回讚':'讚'}</span><span style='margin-left: 5px;'>${(comment.cmt_likes>0)? comment.cmt_likes:''  }</span></a></span>

		</div>
		<div class="col-xs-12 col-sm-3 cmt">
		<a href='#' onclick='editPhotoCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }");' style='color:black'>
			<span class='glyphicon glyphicon-pencil'></span></a>
			&nbsp&nbsp&nbsp&nbsp&nbsp
		<a href='#' onclick='delPhotoCmmt.call(this,event,"${pageContext.request.contextPath}","${comment.bd_cmt_no }","${comment.mem_no.mem_no}");' style='color:black'>
			<span class='glyphicon glyphicon-remove'></span></a>
		</div>
		
	</div>
</div>	
</li>
</c:forEach>