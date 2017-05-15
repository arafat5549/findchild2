<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page-wrapper" style="margin-top:40px;">
<div class="wrapper wrapper-content animated fadeInRight">    

<div class="row">
  <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>${title }</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="xunqin_table">
                        <thead>
                            <tr>
                                  <th>留言类型</th>
                                  <th>标题</th>
                                  <th>内容</th>
                                  <th>留言人</th>
                                  <th>留言时间</th>
                                  <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                </div>
            </div>
  </div>
</div>   
 
<!-- 评论区域  
<div class="row">
                <div class="col-sm-12">
                    <div class="wrapper wrapper-content animated fadeInRight">
                        <div class="ibox-content forum-post-container">
                            
                            <c:forEach items="${ comments}" var="item">
                                <div class="media">
                                     <a class="forum-avatar" href="#">
		                                  <img src="img/a1.jpg" class="img-circle" alt="image">
		                                  <div class="author-info">
		                                        <strong>用户名称:</strong> 542<br/>
		                                        <strong>帖子数:</strong> 542<br/>
		                                        <strong>注册时间:</strong>xApril 11.2015<br/>
		                                  </div>
                                     </a>
                                     
                                     <div class="media-body">
                                         <h4 class="media-heading">${item.title }</h4>
                                         ${item.content }
                                     </div>
                                </div>
                            </c:forEach>
                            
                            
                          </div>

                            </div>
                        </div>
</div>
-->   
<!-- 添加评论 -->
<div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>添加评论</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content no-padding">
							<form class="form-horizontal" role="form" method="post" action="${context }/message">
							    <div class="form-group">
							       <input type="hidden" name="xid" value="${param.xid }">
							       <input type="hidden" name="method" value="add">
							       <input type="hidden" id="content" name="content" value="">
							     </div>
							   <div class="form-group">
			                         <label class="col-sm-2 control-label">标题</label>
			                         <div class="col-md-8">
			                            <input type="text" class="form-control" name="title" id="title">
			                        </div>
			                    </div>
			                    
			                    <div class="form-group">
			                        <label class="col-sm-2 control-label">留言类型</label>
			                        <div class="col-sm-4">
			                            <select class="form-control m-b" name="type">
			                                <c:forEach items="${messageTypes}" var="k">
			                                	<option value="${k.type}">${k.title}</option>
			                                </c:forEach>
			                            </select>
			                        </div>
			                    </div>
			                    
								 <div class="click2edit summernote wrapper p-md" style="display: block;" >
	                               
	                             </div>
	                             
                                 <div class="form-group">
			                        <div class="col-sm-4 col-sm-offset-2">
			                            <button class="btn btn-primary" type="submit" >添加</button>
			                        </div>
                    			</div>
							</form>
                            
  
                        </div>
                    </div>
                </div>
</div> 


</div>
</div>