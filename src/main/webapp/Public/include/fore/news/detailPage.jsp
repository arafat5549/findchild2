<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div id="page-wrapper" style="margin-top:40px;">
<div class="wrapper wrapper-content animated fadeInRight">    
    
<div class="row"> <!-- 每一个class的row是一个大框 -->
    <div class="col-sm-12">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
                <h5>${news.title }</h5>
                <div class="ibox-tools">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                </div>
            </div>
            <div class="ibox-content">
                <form class="form-horizontal">
                     
                    <div class="form-group">
                         <label class="col-sm-2 control-label">标题</label>
                         <div class="col-md-4">
                            <input  disabled="disabled" type="text" class="form-control" value="${news.title }">
                        </div>
                    </div>
                    
                    <div class="form-group"> 
                        <label class="col-sm-2 control-label">内容</label>
                        <div class="col-sm-8">
					   		 <textarea  disabled="disabled" class="form-control" rows="25" >${news.content }</textarea> 
					    </div>
					 </div> 
					 
					 <div class="form-group">
                         <label class="col-sm-2 control-label">发布时间</label>
                         <div class="col-md-4 input-group date">
                             <input disabled="disabled" type="text" class="form-control"  value="<fmt:formatDate value="${news.createTime }" type="both"/>">
                     	</div>
                    </div>
                    
                  </form>
            </div>
        </div>
	</div>
</div>


</div>
</div>