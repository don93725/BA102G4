<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
 					    	<ul class="list-group">
					    		<c:forEach var="newMsg" items="${lastestMsg }">
							    <li class="list-group-item hover" onclick='show("${pageContext.request.contextPath }","${(newMsg.post_no.mem_no==user.mem_no)? newMsg.rcv_no.mem_no :newMsg.post_no.mem_no}","${(newMsg.post_no.mem_no==user.mem_no)? newMsg.rcv_no.mem_nickname :newMsg.post_no.mem_nickname}");'>
								    <div class="row">								    	
								    	<div class="col-xs-12 col-sm-12">
								    		<div class="row">								    			
								    		<div class="col-xs-12 col-sm-12">
								    			${(newMsg.post_no.mem_no==user.mem_no)? newMsg.rcv_no.mem_nickname :newMsg.post_no.mem_nickname }
								    		</div>
								    		
								    	
									    <div class="col-xs-10 col-xs-offset-2  col-sm-10 col-sm-offset-2">
									    		<div class='row'>
									    		<div class="col-xs-12 col-sm-9">${newMsg.msg_ctx }</div>
									    		<div class="col-xs-12 col-sm-2">
									    		<c:if test="${newMsg.nr>0 }">
									    		<span class="badge" style='background-color: red;'>${newMsg.nr }</span>
									    		</c:if></div>
									    		</div>
									    </div>
									    <div class="col-xs-8 col-xs-offset-4 col-sm-8 col-sm-offset-4 msgTime">
									    	<fmt:setLocale value="en_US" />
											<fmt:formatDate value="${newMsg.send_time}"
												pattern="yyyy-MM-dd HH:mm" />
									    </div>
									    </div>
								    </div>
							    </li>
							    </c:forEach>							    
							  </ul>
	