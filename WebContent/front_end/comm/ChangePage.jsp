<%@ page language="java" contentType="text/html; charset=BIG5"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>Insert title here</title>
</head>
<body>


	<ul class="pagination">  
	<c:if test="${thisPage>1 }">	
		<li><a href="${pageContext.request.contextPath}/${queryStr}&thisPage=${thisPage-1}${not empty param.mem_rank? "&mem_rank=":""}${not empty param.mem_rank? param.mem_rank:""}">&laquo;</a></li>
	</c:if>
	<c:forEach begin="${(thisPage-3<1)? '1':thisPage-3 }" end="${thisPage+3 }" varStatus="loop">
	<c:if test="${loop.index<allPageCount+1 }">	
	<li <c:if test="${loop.index==thisPage }">class='active'</c:if>><a href="${pageContext.request.contextPath}/${queryStr}&thisPage=${loop.index}${not empty param.mem_rank? "&mem_rank=":""}${not empty param.mem_rank? param.mem_rank:""}">${loop.index}</a>
	</c:if>
	
	</c:forEach>	
<c:if test="${thisPage<allPageCount }">
	<li><a href="${pageContext.request.contextPath}/${queryStr}&thisPage=${thisPage+1}${not empty param.mem_rank? "&mem_rank=":""}${not empty param.mem_rank? param.mem_rank:""}">&raquo;</a></li>
	</c:if>	
		
		
	<ul class="pagination">  


</body>
</html>