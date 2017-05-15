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
                    <!--  
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >编号</button> </span>
                                    <input type="text" class="form-control" id="id" placeholder="精确">
                            </div>
                    </div>
                    <div class="col-sm-3">
                            <div class="input-group m-b"><span class="input-group-btn">
                                    <button type="button" class="btn btn-primary" >名称</button> </span>
                                    <input type="text" class="form-control" id="name" placeholder="模糊">
                            </div>
                    </div>
                    <div class="col-sm-6" id="export-btn">
                            <a data-type="xls" data-table="category_table" href="javascript:;" type="button" class="btn btn-danger btn-sm">导出xls</a>
                    </div>
                    -->
                </div>
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover dataTables-example" id="category_table">
                        <thead>
                            <tr>
                                  <th></th>
                                  <th>编号</th>
                                  <th>名称</th>
                                  <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                                <c:forEach items="${newsList }" var="news">
							       <tr>
							             <td></td>
							             <td><a class="btn btn-lg btn-link" href="${context}/news?method=detail&id=${news.id}">${news.title }</a></td>
							             <td>${news.source }</td>
							             <td><fmt:formatDate value="${news.createTime }" type="both"/></td>
							       </tr>
							    </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
	</div>
</div>


</div>
</div>