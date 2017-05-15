<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- 包含jsp文件 头部导航和Modal(公告弹出页与登录框弹出页)  --%>    
	<!-- Fixed navbar -->
    <nav class="navbar navbar-collapse navbar-fixed-top">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <a class="navbar-brand" href="${context }">寻子寻亲系统</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${context }">主页</a></li>
            <!--  <li><a href="#">新闻中心</a></li>-->
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">信息中心 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${context }/news?method=list&type=1">寻子信息</a></li>
                <li><a href="${context }/news?method=list&type=2">寻亲信息</a></li>
                <li><a href="${context }/news?method=list&type=3">政策法规</a></li>
              </ul>
            </li>
             
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">寻子寻亲 <span class="caret"></span></a>
              <ul class="dropdown-menu">
                <li><a href="${context }/xunqin?method=add">登记</a></li>
                <li><a href="${context }/xunqin?method=list">查询</a></li>
                <li><a href="${context }/xunqin?method=list&userid=${sessionScope.session_user.id}">我的登记</a></li>
              </ul>
            </li>
            
            <li><a href="${context }/solution?method=list">成功案例</a></li>
            <li><a href="${context }/message?method=list"">留言板</a></li>
		</ul>
		</div><!--/.nav-collapse -->
      </div>
    </nav>

<div>
	${msg}
</div>

<div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">

                           <div class="modal-header">
                                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                 </button>
                                <!--<i class="fa fa-laptop modal-icon"></i>-->
                                <h4 class="modal-title" id="modalTitleMsg">Modal 标题</h4>
                                 
                            </div>
                            
                            <div class="modal-body">
                                <p id="modalBodyMsg">
                                    <strong>粗体信息</strong> 
                                    信息
                                </p>
								
								<!--  
                                <form id="modalFrom">
                                    <div class="form-group">
                                    	<input type="email" placeholder="邮件" class="form-control">
                                    </div>
                                </form>
                                -->
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                            </div>

                        </div>
                    </div>
                </div>