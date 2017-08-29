<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="BIG5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>���f - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- �����C -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>
  	<div class="container" style="min-height:100%;">
			   <div class="breadcrumbs" id="breadcrumbs">
            
	            <div class="col-lg-12">
	                <h1>${personalCmts.mem_nickname }���d���O</h1>
	            </div>
	
	            <ul class="breadcrumb">
	                <li>
	                    <i class="icon-home home-icon"></i>
	                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">����</a></li>
	                     </li>
	                     <c:if test='${empty param.mem_rank}'>
	                	<li class="active"> <a href="<%= request.getContextPath()%>/front_end/editPage/personal.jsp?action=basic">�ӤH�Ŷ� </a></li>
	                     </c:if>
	                     <c:if test='${not empty param.mem_rank}'>
	                	<li class="active"> <a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=${param.mem_rank}&mem_no=${param.mem_no}&action=lookPersonal">�ӤH�Ŷ� </a></li>
	                     </c:if>
	                	<li class="active">�d����</li>
	            </ul><!-- .breadcrumb -->
	        </div>
	        <br>
		<%@include file="/front_end/include/fakeTab.file" %>
		
<div class="col-xs-12 col-sm-12">
		<div class="row">
  
	<%@include file="/front_end/include/person_comments.file" %>
		</div>
		</div>
		</div>


 	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- �̩��h -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<script type="text/javascript">
		
	</script>
</html>