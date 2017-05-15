<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

	<!-- 侧边菜单-开始 -->
	<nav class="navbar-default navbar-static-side" role="navigation">
		 <div class="sidebar-collapse">
		 	<ul class="nav" id="side-menu">
		 	   <li class="nav-header" style="text-align:center;">
		 	   		<div class="dropdown profile-element"> <span>
                        <a href="#">
                            <img alt="image" class="img-circle" src="${contextStatic }/img/borrow_log_600x600.png" width="80px;"  height="80px;" />
                        </a>
                        </span>
                        <span class="clear">
                        <span class="block m-t-xs" style = "color:#fff;">
                        <strong class="font-bold">${sessionScope.session_user.username}</strong>
                        </span> 
                        </span>
                   </div>
		 	   </li>
		 	   
		 	  <c:if test="${sessionScope.session_user.type >2 }">
		
		 	   <li>
                    <a href="#">
                        <i class="fa fa-diamond"></i>
                        <span class="nav-label">公告管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/announce?method=list" data-index="0">公告列表</a>
                            </li>
                        </ul>
                </li>
                
                <li>
                    <a href="#">
                        <i class="fa fa-diamond"></i>
                        <span class="nav-label">网站链接管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/link?method=list" data-index="0">网站链接列表</a>
                            </li>
                        </ul>
                </li>
                
                <li>
                    <a href="#">
                        <i class="fa fa-diamond"></i>
                        <span class="nav-label">成功案例管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/solution?method=list" data-index="0">成功案例列表</a>
                            </li>
                        </ul>
                </li>
                
 				<li>
                    <a href="#">
                        <i class="fa fa-diamond"></i>
                        <span class="nav-label">信息中心管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/news?method=list" data-index="0">信息列表</a>
                            </li>
                        </ul>
                </li>
                
                 <li>
                    <a href="#">
                        <i class="fa fa-diamond"></i>
                        <span class="nav-label">留言板管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/message?method=list" data-index="0">留言板列表</a>
                            </li>
                        </ul>
                </li>
                
				<li>
                    <a href="#">
                        <i class="fa fa-user"></i>
                        <span class="nav-label">用户管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/user?method=list" data-index="0">用户列表</a>
                            </li>
                        </ul>
                </li>  
                </c:if>
                <li>
                    <a href="#">
                        <i class="fa fa-newspaper-o"></i>
                        <span class="nav-label">寻子寻亲管理</span>
                        <span class="fa arrow"></span>
                    </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${context }/admin/xunqin?method=list" data-index="0">寻子寻亲列表</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${context }/admin/comment?method=list" data-index="0">信息提供列表</a>
                            </li>
                        </ul>
                </li>              
		 	</ul>
		 </div>
	</nav>
	<!-- 侧边菜单-结束 -->

    <!-- 头部导航-开始 -->
    <div id="page-wrapper" class="gray-bg">
		<div class="row border-bottom">
			<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">

				<div class="navbar-header">
                    <a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i class="fa fa-bars"></i> </a>
				</div>	
					<ul class="nav navbar-top-links navbar-left">
                       <li>
                           <a class="m-r-lg text-muted welcome-message">欢迎来到寻子寻亲后台管理系统</a>
                      </li>
                   </ul>

                    <ul class="nav navbar-top-links navbar-right">
                        <span class="m-r-sm text-muted welcome-message">欢迎你,</span>
                        <li class="dropdown">
                                <a class="dropdown-toggle count-info" data-toggle="dropdown" href="javascript:void(0)">
                                       <strong><span class="text-success">#${sessionScope.session_user.username}</span></strong>
                                </a>
                                <ul class="dropdown-menu dropdown-messages" style="width: 120px;">
                                    <li>
                                        <strong><a href="${context }/admin?method=userInfo">个人信息</a></strong>
                                    </li>
                                     <li>
                                        <strong><a href="${context }/admin?method=logout">退出登陆</a></strong>
                                    </li>
                                </ul>
                          </li>
                    </ul>
			</nav>
		</div>
    <!-- 头部导航-结束 -->
    <div style="padding:10px;"></div>
    
    <!-- 正文部分 -->
<c:if test="${not empty msg }">
	<div class="alert alert-danger">
	  ${msg }
	</div>
</c:if>