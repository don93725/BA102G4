<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>常見問題管理</title></head>
<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#1AFF19;">
  <tr bgcolor='yellow' align='center' valign='middle' height='20'>
    <td><h3>常見問題管理</h3></td>
  </tr>
</table>

<p></p>

<h3>[問題查詢]:</h3>
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

<ul>
  <li><a href='listAllQueans.jsp'><input type="submit" value="問題總覽" style="width:80px;height:40px;font-size:15px;"></a> </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="queans.do" >
       <img src="images/flower.gif"><b>輸入標題<img src="images/flower.gif">:</b>
        <input type="text" name="que_title" style="background-color:#FFDEDE;">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getPart_By_Title">
    </FORM>
  </li>

 <li>
    <FORM METHOD="post" ACTION="queans.do" >
       <img src="images/flower.gif"><b>輸入日期<img src="images/flower.gif">:</b>
        <input type="date" name="upd_date" style="background-color:#D6D6FF;">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getPart_By_Date">
    </FORM>
  </li>

  <jsp:useBean id="queansSvc" scope="page" class="com.queans.model.QueansService" />
   
  <li>
     <FORM METHOD="post" ACTION="queans.do" >
       <img src="images/flower.gif"><b>選擇類別<img src="images/flower.gif">:</b>
       <select size="1" name="que_type" style="background-color:#8CFFFF;">
         
         <option value="註冊">註冊</option>
         <option value="直播">直播</option>
         <option value="報名">報名</option>
         
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getPart_By_Type">
    </FORM>
  </li>
  

</ul>


<h3>[新增問題]:</h3>

<ul>
  <li><a href='addQueans.jsp'><input type="submit" value="新增" style="width:80px;height:40px;font-size:15px;"></a></li>
</ul>

</body>

</html>
