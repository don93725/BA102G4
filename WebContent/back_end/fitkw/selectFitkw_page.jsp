<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>健身知識管理</title></head>
<body bgcolor='#E8FFFF'>

<table border='5' cellpadding='5' cellspacing='0' width='400' style="border-color:#1AFF19;">
  <tr bgcolor='yellow' align='center' valign='middle' height='20'>
    <td><h3>健身知識管理</h3></td>
  </tr>
</table>

<p></p>

<h3>[知識查詢]:</h3>
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
  <li><a href='listAllFitkw.jsp'><input type="submit" value="知識總覽" style="width:80px;height:40px;font-size:15px;"></a> </li> <br><br>
  
  <li>
    <FORM METHOD="post" ACTION="fitkw.do" >
        <img src="images/flower.gif"><b>輸入標題<img src="images/flower.gif">:</b>
        <input type="text" name="fik_title" style="background-color:#FFDEDE;">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getPart_By_Title">
    </FORM>
  </li>
  
  <li>
    <FORM METHOD="post" ACTION="fitkw.do" >
       <img src="images/flower.gif"><b>輸入日期<img src="images/flower.gif">:</b>
        <input type="date" name="upd_date" style="background-color:#D6D6FF;">
        <input type="submit" value="送出">
        <input type="hidden" name="action" value="getPart_By_Date">
    </FORM>
  </li>

  <jsp:useBean id="fitkwSvc" scope="page" class="com.fitkw.model.FitkwService" />
   
  <li>
     <FORM METHOD="post" ACTION="fitkw.do" >
       <img src="images/flower.gif"><b>選擇主題<img src="images/flower.gif">:</b>
       <select size="1" name="fik_type" style="background-color:#8CFFFF;">
         
          <option value="基礎知識">基礎知識</option>
          <option value="增肌知識">增肌知識</option>
          <option value="其他知識">其他知識</option>
           
            
       </select>
       <input type="submit" value="送出">
       <input type="hidden" name="action" value="getPart_By_Type">
    </FORM>
  </li>
  

</ul>


<h3>[新增知識]:</h3>

<ul>
  <li><a href='addFitkw.jsp'><input type="submit" value="新增" style="width:80px;height:40px;font-size:15px;"></a></li>
</ul>

</body>

</html>
