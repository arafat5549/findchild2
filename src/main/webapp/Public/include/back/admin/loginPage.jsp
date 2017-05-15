<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
    
<div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <h3>欢迎来到寻子寻亲后台管理系统</h3>
            
            <form class="m-t" role="form" action="${context }/admin" method="post">
                <div class="form-group">
                   <input type="hidden" name="method" value="login">
                </div>
                 
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="用户名" name="username">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="密码" name="password">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">登录</button>
                <!--
                <a href="#"><small>忘记密码?</small></a>
                <p class="text-muted text-center"><small>Do not have an account?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">Create an account</a>
                -->
            </form>
            <p class="m-t"> <small>寻子寻亲后台管理系统基于Bootstrap3 &copy; 2017</small> </p>
        </div>
    </div>

