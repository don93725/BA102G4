<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${empty searchResult}">
<%  	StudentsService studentsSV = new StudentsService();
		List<StudentsVO> list = studentsSV.getAll();
		pageContext.setAttribute("list",list);
%>
</c:if>
<c:if test="${not empty searchResult}">
<%		List<StudentsVO> searchResult = (List<StudentsVO>)request.getAttribute("searchResult");
%>
</c:if>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">

<head>
    <title>���f - GymHome</title>
    <%@include file="/front_end/include/basicScript.file" %>
	<!-- ����r�Ʊ��� -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	
<style>
	div.item-caption{
		margin-top: 0;
		padding-top: 0;
	}
	div.item-caption-inner1{
		 margin-top: 0;
		 padding-top: 0;
	}
	div.item-container{
		border-radius:100px;
	}
</style>

</head>


<body>

    <!-- �����C -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<%@include file="/front_end/include/front_navbar.file" %>
    </nav>
    

    <!-- Page Content -->
    <div class="container" style="min-height: 100%">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">    
            <div class="col-lg-12">
                <h1>��ǭ�
                    <small>Students</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">����</a>
                     </li>
                <li class="active">�A�Ȥ���</li>
                <li class="active">��ǭ�</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        
        <div class="alert alert-block alert-success">
        	<p><strong>�ⰷ���a�J�ͬ��A�B�B���O������</strong><br>
			<p>�]��<strong>���ߡB����</strong>�������A�Ʊ�N���U�q�j���������T���A��s���Q�B�λP�����A��O�L�]�}�l���ձN�������ʱa���~�A���Ǿǭ��N����ۥL�h���鰵�L�����V�m�C<br>
			<p>�L�N����`���������άO�Y�צa�γ]�p���i�ѰV�m���覡�A�ⰷ���ĤJ��`�ͬ����Ҥ��C�Ʀ��ٱq�h�~�}�l�w�e�B�N��`���H���i�o���u��A�]�p�������ʧ@�A�z�L�ϸѪ�²�檺�����u��ѡA����B���`�ʵ����@�@�ܦ����������F�A<strong>�������O�H�H�����ɶ��B�����h�����СA���n�������ʧ@�A�o�����U�ܦh���H�A�����u���b�����и̳Q�B�ΡC</strong></p>
        </div>
        
            	<form action="<%= request.getContextPath() %>/StudentsServlet" method="post" id="search_stu">
                <div class="row">
                	
                	<div class="col-lg-2">
                            <input type="search" placeholder="��J�ǭ��m�W" name="search_Name" style="height:42px;">
                    </div>
                    
                    <div class="col-lg-2">
                    	<select class="form-control" id="form-field-select-1" name="search_Type" style="height:42px;">
							<option value="0">�����d������</option>
                        	<option value="A">����</option>
                        	<option value="B">��������</option>
                        	<option value="C">�R�ʦ���</option>
                        	<option value="D">��������</option>
							<option value="E">��¦���V</option>
							<option value="F">�i�����V</option>
							<option value="G">�֩Դ���</option>
							<option value="H">TRX�٤O�J��</option>
							<option value="O">��L</option>
                    	</select>
                    </div>

                    <div class="col-lg-2">
                        <input type="button" class="btn btn-success" value="�d�ݵ��G" style="width:163px;" onclick="tick.call(this);">
                        <input type="hidden" name="action" value="search_stu">
                    </div>

                    <div class="col-lg-2">
                        <input type="reset" class="btn btn-default" style="width:163px;" value="���m����">
                    </div>
                </div>
                </form>
<p></p>          
<div class="row bigHead">
<c:if test="${not empty errorMsgs}">
	<div class="row">
		<div class="col-md-4 pic">
                <img src="<%= request.getContextPath() %>/style/images/noResult.png">
        </div>
		<div class="col-md-6 word" style="font-size:32px;">
			<div style="text-align:left;">
				<b>�ګܩ�p ,�d�L���G<br><p></p>
				I am SORRY ,no results found<br><p></p>
				Ƹ������ƹƨ ,���Gư����Ư����ƿ��</b>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${empty searchResult && empty errorMsgs}">
		<div class="row"  style="margin-bottom:20px;margin-top:20px;text-align:center;">
<c:forEach var="studentsVO" items="${list}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=0&mem_no=${studentsVO.stu_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${studentsVO.stu_into }</span>
											<P></P>
										<span>�`�J�F�ѧ�&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=0&mem_no=${studentsVO.stu_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${studentsVO.stu_name }
						<p style="color:#3c763d;font-size:14px;"><strong>�ǭ�</strong></p>
						</h3>
						</p>
						
					</center>
				</div>	
			</div>
</c:forEach>
		</div>
</c:if>

<c:if test="${not empty searchResult && empty errorMsgs}">
		<div class="row">
<c:forEach var="studentsVO" items="${searchResult}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=0&mem_no=${studentsVO.stu_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${studentsVO.stu_into }</span>
											<P></P>
										<span>�`�J�F�ѧ�&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=0&mem_no=${studentsVO.stu_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
					<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${studentsVO.stu_name }
						<p style="color:#3c763d;font-size:14px;"><strong>�ǭ�</strong></p>
						</h3>
						</p>
						
					</center>
				</div>	
			</div>
</c:forEach>
		</div>
</c:if>
</div>
	
      
</div>
<!-- /.container3 -->
</div>

<!-- Footer -->
	<%@include file="/front_end/include/footer.file" %>
<!-- �̩��h -->
	<%@include file="/front_end/include/floor.file" %>

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<%@include file="/front_end/include/onlyForFS.file" %>
</html>