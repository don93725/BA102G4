<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.course.model.*"%>
<%@ page import="com.members.model.*"%>
<%@ page import="com.course_picture.model.*"%>
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
<html lang="zh-cn-en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>CourseList</title>
</head>
<body>
	<div id="dropdown1" class="tab-pane in active">
		<div class="row">
			<div class="col-xs-12 col-sm-12">
				<div class="table-responsive">
					<table id="sample-table-1" class="table table-striped table-hover" style="table-layout:fixed;word-break:break-all;word-wrap:break-word;text-align:center;">
						<thead class="aaa">
							<tr>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">課程名稱</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">類別</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"width:400px;">內容介紹</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">修改</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">刪除</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;">上下架</th>
								<th class="center" style="vertical-align:middle;background-color:#FFDDAA;" align="center;"><a class='inline' href="#add_content"
									onclick="newCrs()">新增課程</a></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="courseVO" items="${crslist}">

								<tr>
									<span class="hiddenCategory<%=count%>" style="display: none;">${courseVO.category}</span>
									<span class="hiddenCrs_no<%=count%>" style="display: none;">${courseVO.crs_no}</span>
									<td style="vertical-align:middle;" align="center;"><span class="changeCrs_name<%=count%>">${courseVO.crs_name}</span></td>
									<td style="vertical-align:middle;" align="center;"><span class="changeCategory<%=count%>">${courseVO.categoryChange}</span></td>
									<td style="word-wrap:break-word;vertical-align:middle;" align="center;"><span class="changeDetails<%=count%>">${courseVO.details}</span></td>
									<!-- 故意擺兩個 -->
									<td style="vertical-align:middle;" align="center;"><input type="button" class="btn btn-primary"
										id="update<%=count%>" onclick="changeDisabled(1,<%=count%>)"
										value="我要修改">
										<button class="btn btn-warning" id="updateCommit<%=count%>"
											onclick="changeDisabled(2,<%=count%>)" style="display: none;">確認修改</button></td>
									<td style="vertical-align:middle;" align="center;"><button class="btn btn-danger" onclick = "deleteCrs(<%=count%>)">刪除</button>
									<FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/CCM/CourseManager.do" style="display: none;">
											<button class="btn btn-danger" id="deleteCrs<%=count%>">刪除</button>
											<input type="hidden" name="crs_no" value="${courseVO.crs_no}">
											<input type="hidden" name="action" value="courseDelete">
										</FORM></td>
									<td style="vertical-align:middle;" align="center;"><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/CCM/CourseManager.do" >
											<input type="submit" class="btn btn-success" value="上架">
											<input type="hidden" name="crs_no" value="${courseVO.crs_no}"><input
												type="hidden" name="crs_name" value="${courseVO.crs_name}"><input
												type="hidden" name=details value="${courseVO.details}"><input
												type="hidden" name=category value="${courseVO.category}"><input
												type="hidden" name="action" value="coursePublish">
										</FORM></td>
									<td style="vertical-align:middle;" align="center;"><a class='inline' href="#updatePic" onclick="updatePic(<%=count%>)"><input style="display: none;" type="submit" class="btn btn-warning changePic<%=count%>"  value="修改圖片"></a></td>
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
