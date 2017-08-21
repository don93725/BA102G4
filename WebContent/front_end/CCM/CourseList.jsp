<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.Course.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.Course_picture.model.*"%>
<%
	CourseService courseSVC = new CourseService();
	ArrayList<CourseVO> crslist = (ArrayList) courseSVC.getAll(((MembersVO) session.getAttribute("user")).getMem_acc());
	for (int i = 0; i < crslist.size(); i++) {
		crslist.get(i).setCategoryChange(
				(String) getServletContext().getAttribute(((CourseVO) crslist.get(i)).getCategory()));
	}
	pageContext.setAttribute("crslist", crslist);
%>
<%!
	int count = 0;
	int countPic = 0;
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>CourseList</title>
</head>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;">
						<thead class="aaa">
							<tr>
								<th class="center">課程名稱</th>
								<th>類別</th>
								<th style="width:400px;">內容介紹</th>
								<th>修改</th>
								<th>刪除</th>
								<th>上下架</th>
								<th><a class='inline' href="#add_content"
									onclick="newCrs()">新增課程</a></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="courseVO" items="${crslist}">

								<tr>
									<span class="hiddenCategory<%=count%>" style="display: none;">${courseVO.category}</span>
									<span class="hiddenCrs_no<%=count%>" style="display: none;">${courseVO.crs_no}</span>
									<td class="center"><span class="changeCrs_name<%=count%>">${courseVO.crs_name}</span></td>
									<td><span class="changeCategory<%=count%>">${courseVO.categoryChange}</span></td>
									<td style="word-wrap:break-word;"><span class="changeDetails<%=count%>">${courseVO.details}</span></td>
									<!-- 故意擺兩個 -->
									<td><input type="button" class="btn btn-primary"
										id="update<%=count%>" onclick="changeDisabled(1,<%=count%>)"
										value="我要修改">
										<button class="btn btn-warning" id="updateCommit<%=count%>"
											onclick="changeDisabled(2,<%=count%>)" style="display: none;">確認修改</button></td>
									<td><button class="btn btn-danger" onclick = "deleteCrs()">刪除</button>
									<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/CCM/CourseManager.do" style="display: none;">
											<button class="btn btn-danger" id="deleteCrs">刪除</button>
											<input type="hidden" name="crs_no" value="${courseVO.crs_no}">
											<input type="hidden" name="action" value="courseDelete">
										</FORM></td>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/CCM/CourseManager.do" >
											<input type="submit" class="btn btn-success" value="上架">
											<input type="hidden" name="crs_no" value="${courseVO.crs_no}"><input
												type="hidden" name="crs_name" value="${courseVO.crs_name}"><input
												type="hidden" name=details value="${courseVO.details}"><input
												type="hidden" name=category value="${courseVO.category}"><input
												type="hidden" name="action" value="coursePublish">
										</FORM></td>
									<td><a class='inline' href="#updatePic" onclick="updatePic(<%=count%>)"><input style="display: none;" type="submit" class="btn btn-warning changePic<%=count%>"  value="修改圖片"></a></td>
									<span id="hiddenPic<%=count%>" style="display: none;">
										<% countPic = 0;%>
										<c:forEach var="crs_picVO" items="${courseVO.picList}">
											<img src='${crs_picVO.crs_base}' width="120" height="120" class="${crs_picVO.crs_pic_no}" style="margin:1em;" id="picStatus<%=countPic%>" onclick="dpic(<%=countPic%>)">	
											<% countPic++;%>
										</c:forEach>
										<div style="display: none;">${courseVO.crs_no}</div>
									</span>
								</tr>
								<%
									count++;
								%>
							</c:forEach>
						</tbody>
					</table>
				</div>
				<!-- /.table-responsive -->
			</div>
			<!-- /col-sm-12 -->
		</div>
		<!-- /row -->
	</div>
	<!-- 課程管理 全部 結束 -->


</body>
</html>
