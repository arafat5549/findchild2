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
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="xunqin_table">
                        <thead>
                            <tr>
                                  <th></th>
                                  <th>登记类型</th>
                                  <th>登记姓名</th>
                                  <th>图片</th>
                                  <th>籍贯</th>
                                  <th>年龄</th>
                                  <th>走失性质</th>
                                  <th>走失时间</th>
                                  
                                  <th>联系人</th>
                                  <th>联系电话</th>
                                  <th>当前状态</th>
                                  
                                  <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                                 <%-- 
                                <c:forEach items="${lists }" var="item">
							       <tr>
							             <td class="details-control"></td>
							             <td>${item.logtype }</td>
							             <td>${item.logname }</td>
							              <td><img class="img-responsive" width="150px" height="100px" src="${context }/${item.avatarurl }" ></td>
							             <td>${item.birthplace }</td>
							             <td>${item.age }</td>
							             <td>${item.losttype }</td>
							             <td><fmt:formatDate value="${item.createTime }" type="both"/></td>
							       </tr>
							    </c:forEach>
							    --%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
	</div>
</div>


</div>
</div>