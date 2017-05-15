<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page-wrapper" style="margin-top:40px;">
<div class="wrapper wrapper-content animated fadeInRight">    
    
<div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <h3>欢迎来到寻子寻亲</h3>
            
            <form class="m-t" role="form" action="${context }/user" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" name="password">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b" name="method" value="login">登录</button>
                <!--
                <a href="#"><small>忘记密码?</small></a>
                <p class="text-muted text-center"><small>Do not have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
                -->
            </form>
            <p class="m-t"> <small>IN+后台管理系统基于Bootstrap3 &copy; 2017</small> </p>
        </div>
    </div>


</div>
</div>