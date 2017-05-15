<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page-wrapper" style="margin-top:40px;">
<div class="wrapper wrapper-content animated fadeInRight">    
    
<div class="row"> <!-- 每一个class的row是一个大框 -->
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
                                    <button type="button" class="btn btn-primary" >内容</button> </span>
                                    <input type="text" class="form-control" id="s_content" placeholder="输入按回车">
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
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="solution_table">
                        <thead>
                            <tr>
                                  <th>登记类型</th>
                                  <th>标题</th>
                                  <th>内容</th>
                                  <th>发布时间</th>
                                  <th>发布人</th>
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


</div>
</div>