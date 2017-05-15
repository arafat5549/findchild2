<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 包含jsp文件 侧边栏导航  公告和网站链接元素由jslib.jsp生成  --%>    
    <nav class="navbar-default navbar-static-side" role="navigation" style="margin-top:30px;">
		 <div class="sidebar-collapse">
		 	<ul class="nav" id="mysideMenu"> <!-- id="side-menu" -->
		 	   <li class="nav-header" style="text-align:center;">
		 	   		<c:if test="${not empty msg }">
		 	          <div class="alert alert-danger">${msg }</div>
		 	        </c:if>
		 	        
		 	        <c:if test="${empty sessionScope.session_user }">
			 	        <form role="form" id="loginform" action="${context }/user" method="post">
			 	            
		                    <div class="form-group">
		                    	<input type="text" placeholder="用户名" class="form-control" name="username" value="${session_user.username }">
		                    </div>
		                    
		                    <div class="form-group">
	                    		<input type="password" placeholder="密码" class="form-control" name="password" value="${session_user.password }">
	                    	</div>
	                    	
	                    	
	                    	 <div>
	                    	    <!--  type="submit" name="method" value="register"-->
	                         	<button class="btn btn-sm btn-primary m-t-n-xs" type="submit" name="method" value="login"><strong>登录</strong></button>
	                         	<a class="btn btn-sm btn-primary m-t-n-xs" href="${context }/user?method=register"><strong>注册</strong></a>
	                    	</div>
			 	        </form> 
		 	        </c:if>
		 	        
		 	         <c:if test="${not empty sessionScope.session_user }">
		 	            <div class="dropdown profile-element"> 
                        <span>
                        <a href="${context }">
                            <img alt="image" class="img-circle" src="Public/img/borrow_log_600x600.png" width="80px;"  height="80px;" />
                        </a>
                        </span>
                        <span class="clear">
                        <span class="block m-t-xs" style = "color:#fff;">
                        <strong class="font-bold">${session_user.username }</strong>
                        <strong class="font-bold"><a href="${context }/user?method=logout">退出</a></strong>
                        <c:if test="${sessionScope.session_user.type>=2 }">
                        	<strong class="font-bold"><a href="${context }/admin">后台管理</a></strong>
                        </c:if>
                        
                        </span> 
                        </span>
                        </div>
		 	         </c:if>
		 	   </li>
		 	   
		 	   
		 	   
		 	   <!--
				<li class="active">
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">系统公告</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level in">
                            <li class="active">
                                <a class="J_menuItem active" href="plugin_jqueryValidate.html" data-index="0">添加借款</a>
                            </li>
                            <li class="active">
                                <a class="J_menuItem active" href="__MODULE__/Borrow" data-index="1">借款列表</a>
                            </li>
                        </ul>
                 </li>  
		 	    
		 	    <li class="active">
                        <a href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">系统公告</span>
                           
                        </a>
                        <ul class="nav nav-second-level in">
                            <li class="active">
                                <a class="J_menuItem active" href="plugin_jqueryValidate.html" data-index="0">添加借款</a>
                            </li>
                            <li class="active">
                                <a class="J_menuItem active" href="__MODULE__/Borrow" data-index="1">借款列表</a>
                            </li>
                        </ul>
                 </li>  
		 	       -->          
		 	</ul>
		 </div>
	</nav>
	<!-- 侧边菜单-结束 -->
	
	