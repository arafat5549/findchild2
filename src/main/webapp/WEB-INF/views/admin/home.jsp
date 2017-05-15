<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/Public/csslib.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>后台主页</title>

</head>
<body>
   

<div id="wrapper">    

	<%@ include file="/Public/include/back/layout.jsp" %>
	<c:if test="${empty sessionScope.session_user }">
		<%@ include file="/Public/include/back/admin/loginPage.jsp" %>
	</c:if>
	
	
 	<%--@ include file="/Public/include/back/index/homePage.jsp" --%>
    
</div>
  
  
 <%@ include file="/Public/jslib.jsp" %>
</body>
</html>