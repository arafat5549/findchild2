<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>    



<!-- 每一个class的row是一个大框 -->    
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
                <div class="row">
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >标题</button> </span>
                                    <input type="text" class="form-control" id="s_title" placeholder="输入按回车">
                            </div>
                    </div>
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >名称</button> </span>
                                    <input type="text" class="form-control" id="s_content" placeholder="输入按回车">
                            </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="admin_table">
                        <thead>
                            <tr>
                                  <th>编号</th>
                                  <th>标题</th>
                                  <th>内容</th>
                                  <th>新闻类型</th>
                                  <th>发布人</th>
                                  <th>创建时间</th>
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
</div>

<!-- 添加 -->
<!-- 每一个class的row是一个大框 -->    
<div class="row"> 
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>添加${title }</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            
            <div class="ibox-content">
            	 <form class="form-horizontal" role="form" id="myform" action="${context }/admin/message" method="post" >
                     <div class="form-group">
                    	<input type="hidden" class="form-control" name="method" value="add">
                     </div>
                     
                    <div class="form-group">
                         <label class="col-sm-2 control-label">标题</label>
                         <div class="col-md-8">
                            <input type="text" class="form-control" name="title" id="title">
                        </div>
                        
                    </div>
                    
                    
                    <div class="form-group"> 
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-8">
					   		 <textarea class="form-control" rows="5" placeholder="不超过250个字节" name="content" id="content"></textarea> 
					    </div>
					  </div> 
					  
					   <div class="form-group">
                          <label class="col-sm-2 control-label">留言类型</label>
                          <div class="col-md-4">
                                    	    <select class="form-control m-b" name="type" id="type">
				                                <c:forEach items="${messageTypes}" var="k">
				                                	<option value="${k.type}">${k.title}</option>
				                                </c:forEach>
				                            </select>
                          </div>
                        </div>
					  
					<div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" type="submit">添加</button>
                        </div>
                    </div>
                </form>
            </div>
         </div>
     </div>
</div>         
