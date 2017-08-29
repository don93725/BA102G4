<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="BIG5"%>
<%@ page import="com.members.model.*" %>
<%@ page import="com.coaches.model.*" %>
<%@ page import="com.students.model.*" %>
<%@ page import="com.gyms.model.*" %>
<%@ page import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%  	CoachesService coachesSV = new CoachesService();
		List<CoachesVO> list = coachesSV.getAll();
		pageContext.setAttribute("list",list);
%>

<c:if test="${not empty searchResult}">
<%		List<CoachesVO> searchResult = (List<CoachesVO>)request.getAttribute("searchResult");
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
	div.img_title{
		text-align: center;
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
    <div class="container" style="min-height: 100%;margin: 0 auto -170px;">
        
        <!-- Page Heading/Breadcrumbs -->
        <div class="breadcrumbs" id="breadcrumbs">    
            <div class="col-lg-12">
                <h1>��нm
                    <small>Coaches</small>
                </h1>
            </div>

            <ul class="breadcrumb">
                <li>
                    <i class="icon-home home-icon"></i>
                        <a href="<%= request.getContextPath()%>/front_end/index.jsp">����</a>
                     </li>
                <li class="active">�A�Ȥ���</li>
                <li class="active">��нm</li>
            </ul><!-- .breadcrumb -->
        </div>
        <br>
        
        <div class="alert alert-block alert-warning">
        	<p><strong>�֦��p�H�нm���n�B�G</strong><br>
			<p>1. �W�[�V�m�ʾ��A�קK�]��ߤ����Ӥ��~��󤧲{�H�C<br>
			2. �w�����V�m���ɡA��֦]����ϥΦӳy�����ˮ`�C<br>
			3. ���T�S��ĪG���V�m�ҵ{�A�M���ӤH�q���q���C<br>
			4. �w��S��ݨD�V�m�A���׬O�ˮ`�_���άO�W�j�B�ʯ�O�C<br>
			5. �۫H���W�[�Ӧ۩󰷬��ú٪������I�}�n���B�ʰV�m�p�e���F�i�P�i���d�~�A�̥D�n�O�i�H��o�O�H�ٸr�����������C<br>
			<p>���ױz�O�n<strong>�W���B�j���B��B�G���B�_���B�W�j�B�ʪ�{</strong>�A�z���M�ݨp�H�нm���|���z�q���q���@���ݩ�z<strong>�ӤH�����������p�e�I</strong></p>
        </div>
            	<form action="<%= request.getContextPath() %>/CoachesServlet" method="post" id="search_coa">
                <div class="row">
                	
                	<div class="col-lg-2">
                            <input type="search" placeholder="��J�нm�m�W" name="search_Name" style="height:42px;">
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
                        <input type="button" class="btn btn-warning" value="�d�ݵ��G"  style="width:163px;" onclick="tick.call(this);">
                        <input type="hidden" name="action" value="search_coa">
                    </div>

                    <div class="col-lg-2">
                        <input type="reset" class="btn btn-default" value="���m����" style="width:163px;">
                    </div>
                </div>
                </form>
<p></p>                      
<div class="row bigHead">
<c:if test="${not empty errorMsgs}">
	<div class="row">
		<div class="col-md-6 pic" style="text-align:center;">
                <img style="height:400px;" src="<%= request.getContextPath() %>/style/images/noResult.png">
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
<c:forEach var="coachesVO" items="${list}">
			 <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachesVO.coa_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${coachesVO.coa_into }</span>
											<P></P>
										<span>�`�J�F�ѧ�&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachesVO.coa_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<center>
						<p>
						<h3 style="margin-top:5px;padding-bottom:0px;">
						${coachesVO.coa_name }
						<p style="color:#8a6d3b;font-size:14px;"><strong>�нm</strong></p>
						</h3>
						</p>
					</center>
				</div>
			</div>
</c:forEach>
		</div>	
</c:if>

<c:if test="${not empty searchResult && empty errorMsgs}">
		<div class="row"  style="margin-bottom:20px;margin-top:20px;text-align:center;">
<c:forEach var="coachesVO" items="${searchResult}">
            <div class="col-md-3 col-sm-4 animated bounceIn" style="margin-bottom:40px;text-align:center;">
					<div class="item-container"  style="margin-bottom:0px;">
						<div class="item-caption black">
							<a href="<%= request.getContextPath() %>/MembersServlet?mem_rank=1&mem_no=${coachesVO.coa_no}&action=lookPersonal" target="_blank">
								<div class="item-caption-inner">
									<div class="item-caption-inner1">
										<span class="into">${coachesVO.coa_into }</span>
											<P></P>
										<span>�`�J�F�ѧ�&nbsp<i class="icon-arrow-right icon-on-right"></i></span>
									</div>
								</div>
							</a>
						</div>
							<img  src="<%= request.getContextPath() %>/XiangZhiPic?mem_rank=1&mem_no=${coachesVO.coa_no}" style="margin-top:4px;border-radius:100px;box-shadow:0px 0px 12px #7E7E7E;" />
					</div>
				<div class="img_title" style="margin-top:0px;text-align:center;">
					<p>
					<h3 style="margin-top:5px;padding-bottom:0px;">
					${coachesVO.coa_name }
					<p style="color:#8a6d3b;font-size:14px;"><strong>�нm</strong></p>
					</h3>
					</p>
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

</body>
	<%@include file="/front_end/include/basicScript2.file" %>
	<%@include file="/front_end/include/onlyForFC.file" %>
</html>
