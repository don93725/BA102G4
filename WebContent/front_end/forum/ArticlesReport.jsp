<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-ch-en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/front_end/forum/css/ArticleDisplay.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/front_end/comm/css/sweetalert.css">

<title>健貨 - GymHome</title>
<%@include file="/front_end/include/basicScript.file" %>
</head>

<body>
	
	<!-- 導覽列 -->
 <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
<%@include file="/front_end/include/front_navbar.file" %>
  </nav>
<div class="container" style="min-height: 100%;">
	<div class="row">
		  <div class="breadcrumbs" id="breadcrumbs">
            
            <div class="col-lg-12">
                <h1>文章檢舉管理
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">首頁</a></li>
                     </li>
                <li class="active"><a href="${pageContext.request.contextPath}/forum/ForumShowCtrl?&forum_no=${param.forum_no }">${forum_name }</a></li>
                <li class="active">管理檢舉</li>
            </ul><!-- .breadcrumb -->
        </div>
		
		<div class="col-xs-12 col-sm-12">

<div class="col-xs-12 col-sm-12">

<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no }"><span class='label label-default' >全部</span></a>
<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no }&rpt_type=0"><span class='label label-default' >涉及歧視</span></a>
<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no }&rpt_type=1"><span class='label label-default' >含十八禁</span></a>
<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no }&rpt_type=2"><span class='label label-default' >人身攻擊</span></a>
<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no }&rpt_type=3"><span class='label label-default' >政治問題</span></a>
<a href="${pageContext.request.contextPath}/forum/ArticlesReportActionCtrl?action=select&forum_no=${param.forum_no }&rpt_type=4"><span class='label label-default' >其他</span></a>
</div>

<div class="col-xs-12 col-sm-12">
				<div class="panel panel-default">
	<table class="table table-hover">
		<thead>
			<tr class='active'>
				<th>被檢舉文章</th>
				<th>檢舉人</th>
				<th>檢舉類型</th>
				<th>檢舉原因</th>
				<th>檢舉時間</th>
				<th>處理</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach var="article_report" items='${article_report }' varStatus="loop">
		<tr class="art_rpt">
			<td><a target="_blank" href="${pageContext.request.contextPath}/forum/ArticleShowCtrl?forum_no=${article_report.art_no.forum_no }&art_no=${article_report.art_no.art_no}">${article_report.art_no.art_name}</a></td>
			<td><a target="_blank" href='${pageContext.request.contextPath}/forum/PersonalPageCtrl?mem_no=${article_report.rpt_mem_no.mem_no}&mem_rank=${article_report.rpt_mem_no.mem_rank}&action=lookPersonal'>${article_report.rpt_mem_no.mem_nickname}</a></td>
			<td>
			<c:if test="${article_report.rpt_type==0}">涉及歧視</c:if>
			<c:if test="${article_report.rpt_type==1}">含十八禁</c:if>
			<c:if test="${article_report.rpt_type==2}">人身攻擊</c:if>
			<c:if test="${article_report.rpt_type==3}">政治問題</c:if>
			<c:if test="${article_report.rpt_type==4}">其他</c:if>					
			</td>
			<td>${article_report.rpt_ctx}</td>
			<td><fmt:setLocale value="en_US"/><fmt:formatDate value="${article_report.rpt_time}" pattern="yyyy-MM-d HH:mm"/></td>
			<td>
			<button class='btn btn-danger delRpt' value='${article_report.art_no.art_no }'>刪除</button>
			<button class='btn btn-default fininshRpt' value='${article_report.art_rpt_no }'>忽略</button>
			</td>
		</tr>
		<tr style="display: none;">
			<td colspan="6" >${article_report.art_no.art_ctx}</td>			
		</tr>
		</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	<jsp:include page="/front_end/forum/ChangePage.jsp"/>
		</div>
	</div>
</div>
<script src="https://code.jquery.com/jquery.js"></script>
<script src='${pageContext.request.contextPath}/front_end/comm/js/sweetalert.min.js'></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/front_end/forum/js/ArticlesReport.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>

	<!-- 最底層 -->
	<%@include file="/front_end/include/floor.file" %>
	
</body>
	<%@include file="/front_end/include/basicScript2.file" %>
</html>