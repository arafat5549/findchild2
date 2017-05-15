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
                                    <button type="button" class="btn btn-primary" >用户名</button> </span>
                                    <input type="text" class="form-control" id="s_username" placeholder="输入按回车">
                            </div>
                            
                    </div>
                    
                    
                             <div class="col-sm-3">
                            	<div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >用户类型</button> </span>
                                    <!-- <input type="text" class="form-control" id="s_losttype" placeholder="模糊"> -->
                                    <select class="form-control m-b" id="s_usertype">
                                        <option value="">--</option>
		                                <c:forEach items="${userTypes}" var="k">
		                                	<option value="${k.type}">${k.title}</option>
		                                </c:forEach>
		                            </select>
                            	</div>
                    		</div>
                    		
                    		<div class="col-sm-3">
                            	<div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >用户状态</button> </span>
                                    <!-- <input type="text" class="form-control" id="s_losttype" placeholder="模糊"> -->
                                    <select class="form-control m-b" id="s_userstatus">
                                        <option value="">--</option>
		                                <c:forEach items="${userStatuss}" var="k">
		                                	<option value="${k.type}">${k.title}</option>
		                                </c:forEach>
		                            </select>
                            	</div>
                    		</div>
                    
                  </div>
             </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="admin_table">
                        <thead>
                            <tr>
                                  <th>编号</th>
                                  <th>名称</th>
                                  <th>密码</th>
                                  <th>用户类型</th>
                                  <th>用户状态</th>
                                  <th>籍贯</th>
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

