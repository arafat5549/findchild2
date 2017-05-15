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
                <form class="form-horizontal" role="form" id="myform" action="${context }/xunqin" method="post" enctype="multipart/form-data">
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
                            <!--  <input type="text" class="form-control" name="birthplace" id="birthplace">-->
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
                         <label class="col-sm-2 control-label">联系人</label>
                         <div class="col-md-4">
                            <input type="text" class="form-control" name="contact">
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">联系电话</label>
                         <div class="col-md-4">
                            <input type="text" class="form-control" name="mobile">
                        </div>
                    </div>
                    
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">走失时间</label>
                         <div class="col-md-4 input-group date">
                             <input type="text" class="form-control" name="losttime" id="losttime">
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
	                         <input type="file" accept="image/*" name="avatarurl" >
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

</div>
</div>