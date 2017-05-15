<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page-wrapper" >
<div class="wrapper wrapper-content animated fadeInRight"> 
    
<div class="middle-box text-center loginscreen   animated fadeInDown">
        <div>
            <h3>注册页面</h3>
            <form class="m-t" role="form" action="${context }/user" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" name="password">
                </div>
                
                <div class="form-group">
	                  <select class="form-control m-b" name="type" id="type">
                                <c:forEach items="${userTypes}" var="k">
                                	<option value="${k.type}">${k.title}</option>
                                </c:forEach>
                      </select>
	            </div>
                 
                <div class="form-group">
                         <select class="form-control m-b" name="birthplace" id="birthplace">
                                <c:forEach items="${birthplaces}" var="k">
                                	<option value="${k.title}">${k.title}</option>
                                </c:forEach>
                            </select>
                </div>
                
                <button type="submit" class="btn btn-primary block full-width m-b"
                name="method" value="register">注册账号</button>
                <!--  
                <p class="text-muted text-center"><small>已经拥有账户?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="login.html">登录</a>
           		-->
            </form>
            <p class="m-t"> <small>后台管理 on Bootstrap 3 &copy; 2017</small> </p>
        </div>
    </div>

 </div>
 </div>