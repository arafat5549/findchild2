<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/Public/csslib.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>添加寻子寻亲</title>


</head>
<body>
   
<%@ include file="/Public/include/fore/top.jsp" %>

<div id="wrapper">    

	<%@ include file="/Public/include/fore/sidemenu.jsp" %>
	
	<%@ include file="/Public/include/fore/xunqin/addPage.jsp" %>

</div>
  
 <%@ include file="/Public/jslib.jsp" %>
 
<!-- 时间选择插件 laydate --> 
    <script>
        laydate({elem:"#losttime",event:"focus", format: 'YYYY/MM/DD-hh:mm:ss',istime:true});
    </script>
    
<!-- 参数验证框架 -->    
<script>

  $(function(){
	  
             $("#myform").validate({ //参数验证规则
                 rules: {
                	 logname: {
                         required: true,
                         minlength: 2
                     },
                     birthplace: {
                         required: true
                     },
                     losttime:{
                    	 required: true
                     },
                     age: {
                         required: true,
                         number: true
                     },
                     mobile:{
                    	 required: true
                     }
                     
                 }
                 
             });
  });
 </script>   
</body>
</html>