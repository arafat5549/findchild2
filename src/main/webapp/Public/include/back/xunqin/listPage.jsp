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
                                    <button type="button" class="btn btn-primary" >登记名称</button> </span>
                                    <input type="text" class="form-control" id="s_logname" placeholder="输入按回车">
                            </div>
                    </div>
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >走失性质</button> </span>
                                    <!-- <input type="text" class="form-control" id="s_losttype" placeholder="模糊"> -->
                                    <select class="form-control m-b" id="s_losttype">
                                        <option value="">--</option>
		                                <c:forEach items="${lostTypes}" var="k">
		                                	<option value="${k.type}">${k.title}</option>
		                                </c:forEach>
		                            </select>
                            </div>
                    </div>
                    
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >登记性质</button> </span>
                                    <select class="form-control m-b"  id="s_logtype">
                                        <option value="">--</option>
		                                <c:forEach items="${logTypes}" var="k">
		                                	<option value="${k.type}">${k.title}</option>
		                                </c:forEach>
		                            </select>
                            </div>
                    </div>
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="admin_table">
                        <thead>
                            <tr>
                                  <th>编号</th>
                                  <th>登记类型</th>
                                  <th>登记姓名</th>
                                  <th>图片</th>
                                  <th>籍贯</th>
                                  <th>年龄</th>
                                  <th>走失性质</th>
                                  <th>走失时间</th>
                                  <th>发布人</th>
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
            	 <form class="form-horizontal" role="form" id="myform" action="${context }/admin/xunqin" method="post" enctype="multipart/form-data">
                     <div class="form-group">
                    	<input type="hidden" class="form-control" name="method" value="add">
                     </div>
                     
                    <div class="form-group">
                         <label class="col-sm-2 control-label">登记姓名</label>
                         <div class="col-md-4">
                            <input type="text" class="form-control" name="logname" id="logname">
                        </div>
                        
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">籍贯</label>
                         <div class="col-md-4">
                            <!-- <input type="text" class="form-control" name="birthplace" id="birthplace"> -->
                            <select class="form-control m-b" name="birthplace" id="birthplace">
                                <c:forEach items="${birthplaces}" var="k">
                                	<option value="${k.title}">${k.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">年龄</label>
                         <div class="col-md-4">
                            <input type="text" class="form-control" name="age">
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">走失时间</label>
                         <div class="col-md-4 input-group date">
                             <input type="text" class="form-control" name="losttime" id="losttime2">
                             <span class="input-group-addon"><i class="fa fa-calendar"></i></span>
                     	</div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">登记类型</label>
                        <div class="col-sm-4">
                            <select class="form-control m-b" name="logtype">
                                <c:forEach items="${logTypes}" var="k">
                                	<option value="${k.type}">${k.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                   
                   <div class="form-group">
                        <label class="col-sm-2 control-label">走失类型</label>
                        <div class="col-sm-4">
                            <select class="form-control m-b" name="losttype">
                                <c:forEach items="${lostTypes}" var="k">
                                	<option value="${k.type}">${k.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label class="col-sm-2 control-label">上传图片</label>
                        <div class="col-sm-4">
	                         <input type="file" name="avatarurl" >
                        </div>
                    </div>
                    
                    
                    <div class="form-group"> 
                        <label class="col-sm-2 control-label">简介说明</label>
                        <div class="col-sm-8">
					   		 <textarea class="form-control" rows="5" placeholder="不超过250个字节" name="note"></textarea> 
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
