<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.fitkw.model.*"%>
<%
FitkwVO fitkwVO = (FitkwVO) request.getAttribute("fitkwVO");
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn-en">
<head>

<title>健貨後台管理系統</title>
	<%@include file="/back_end/include/basic_ace_script.file" %>

</head>
<body>
<%@include file="/back_end/include/navbar.file" %>
<%@include file="/back_end/include/sliderBar_breadCrumb.file" %>

	<div class="page-content">
		<div class="page-header">
			<h1>
				頁面資訊維護  
				<small>
					<i class="icon-double-angle-right"></i> <a href='${pageContext.request.contextPath }/back_end/fitkw/selectFitkw_page.jsp'>健身知識</a> 
					<i class="icon-double-angle-right"></i> 新增健身知識
				</small>
			</h1>
		</div>
		<!-- /.page-header -->
		<div class='container'>
	<div class='row'>
	<center><h2>新增健身知識</h2><button onclick='append();'></button></center>
		<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>

<FORM METHOD="post" ACTION="fitkw.do" name="form1" enctype="multipart/form-data">
<table class='table' align="center">
<tbody>
	<tr>
		<td style="vertical-align:middle;" align="center" >知識標題:</td>
		<td><input class='form-control' type="TEXT" id='fik_title' name="fik_title" size="45"
			value="<%= (fitkwVO==null)? "" : fitkwVO.getFik_title()%>" /></td>
	</tr>
	
	<jsp:useBean id="fitkwSvc" scope="page" class="com.fitkw.model.FitkwService" />
	<tr>
		<td style="vertical-align:middle;" align="center" >知識主題:<font color=red><b></b></font></td>
		<td><select class='form-control' size="1" name="fik_type">
			<option value="基礎知識">基礎知識</option>
            <option value="增肌知識">增肌知識</option>
            <option value="跑步知識">跑步知識</option>
            <option value="有氧知識">有氧知識</option>
            <option value="其他知識">其他知識</option>
          
		</select></td>
	</tr>
	
	<tr>
		<td style="vertical-align:middle;" align="center" >知識內文:</td>
<!-- 		<td><input type="TEXT" name="fik_ctx" size="45" -->
<%-- 			value="<%= (fitkwVO==null)? "" : fitkwVO.getFik_ctx()%>" /></td> --%>
			
			<td><textarea class='form-control' id='fik_ctx' name= "fik_ctx" rows="20" cols="40" style="resize:none;"></textarea></td>
			
	</tr>
	
	<tr>
		<td style="vertical-align:middle;" align="center" >知識圖片:</td>
		<td><img id='pic'>
		<br><input class='btn btn-default' type='button' onclick='upload();' value='上傳圖片'><input id='file' type="file" name="fik_photo" style='display:none;';/></td>
	</tr>

<tr align='center'><td colspan='2'>
<input type="hidden" name="action" value="insert">
<input class='btn btn-primary' type="submit" value="送出新增">&nbsp&nbsp&nbsp
<input class='btn btn-danger' type="reset" value="重置表格"></td>
</tbody>
</table>
</FORM>
	</div>
</div>



		<%@include file="/back_end/include/ace_setting_footer.file"%>
</body>
<script type="text/javascript">
function append(){
	$('#fik_title').val('運動完喝「含糖」豆漿 長肌肉效果更好！');
	$('#fik_ctx').val('增肌飲食大公開  豆漿選含糖更理想至於補充的份量，會依據每個人的體重和訓練強度而有所不同，以下是比較簡單的參考組合，不妨挑選一個自己愛吃的飲食種類吧！'+
			'\r\n(1)組合一/含糖豆漿或巧克力牛奶 :這兩種飲料都含有碳水化合物與蛋白質，而且人體對液體的吸收速度較快，'+
			'可以比較快速補充運動後流失的能量和水分。需要特別提醒的是，如果運動是以增加肌肉為目標，楊哲雄營養師建議，'+
			'喝「含糖」豆漿會比無糖豆漿更好。無糖豆漿雖然富含蛋白質可以幫助肌肉生長，'+
			'但是誘發胰島素分泌的碳水化合物含量較不足，而胰島素是體內合成作用的激素，能促進肌肉修補與生成。'+
			'因此，長肌肉不是光有蛋白質就足夠，還需要適度的碳水化合物。'+
			'\r\n(2)組合二/新鮮水果＋優格：水果富含碳水化合物，優格含有蛋白質，兩者結合又有酸酸甜甜的口感，'+
			'很適合運動後犒賞自己、補充營養。需要提醒的是，盡量選擇天然、新鮮的水果，比較健康。'+
			'另外，吃了水果優格後還要記得額外補充水分。'+
			'\r\n(3)組合三/茶碗蒸＋純果汁喜歡吃熱食的人，可以吃碗熱呼呼的茶碗蒸，裡面含有雞蛋的豐富蛋白質，'+
			'而碳水化合物的部分可以用一杯300c.c.的100%純果汁來補足。'+
			'長肌肉不只蛋白質  碳水化合物不可少，大部分的健身、運動族群都已補充了充足的蛋白質，'+
			'但卻經常忽略碳水化合物的重要性。雖然大量攝取蛋白質可以增加肌肉，但效果並不是最好的。'+
			'若把身體生長肌肉比喻成建造大樓，只吃蛋白質就好像工地裡備有充足的建材，'+
			'卻缺乏足夠的工人搬運材料、動手築牆，大樓建造的效率自然就會比較低。'+
			'天然食物補營養，長肌肉也顧健康，進階者分次補充蛋白質  增肌效果更好喔！');
}
window.onload = init;
function init(){
	Preview.file_change();
}
function upload(){
	$('#file').trigger('click');
}

Preview = new function() {
	var fileInput = $('#file');
	this.file_change = function() {
		$('#file').on('change', function() {
			
			show(this);
		});
	}
	var show = function(input) {
		if (input.files && input.files[0]) {
			each_img(input.files);
		}
	}			
	var each_img = function(files) {
		$.each(files,function(index, file) {
				if (file.type.match('image')) {
					var reader = new FileReader();				
					reader.onload = function() {
						$('#pic').prop('src',reader.result);
						$('#pic').css('display',"block");
						$('#pic').css('height',"200px");
					}
					if (file) {
						reader.readAsDataURL(file);
					}
				}
			});
	}

}
</script>
</html>
