<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/Public/csslib.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>${title }</title>

</head>
<body>

<!-- 弹出框modal -->
<div class="modal inmodal" id="userModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">

                           <div class="modal-header">
                                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                 </button>
                                <!--<i class="fa fa-laptop modal-icon"></i>-->
                                <h4 class="modal-title" id="modalTitleMsg">Modal 标题</h4>
                                 
                            </div>
                            
                            <div class="modal-body">
								 
                                <form class="form-horizontal" role="form" id="modalFrom" method="post" action="${context }/admin/user">
                                	
                                	<div class="form-group">
                                	   <input type="hidden" name="method" value="upd">
                                	   <input type="hidden" name="id" id="id" value="">
                                	</div>
                                	<div class="form-group">
                                	    <label class="col-sm-2 control-label">编号</label>
                                    	 <div class="col-md-8">
                                    	 <input type="text" placeholder="编号" class="form-control" name="userid" id="userid" disabled="true">
                                        </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">用户名</label>
                                    	 <div class="col-md-8">
                                    	 <input type="text" placeholder="用户名" class="form-control" name="username" id="username">
                                    	 </div>
                                    </div>
                                    
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">密码</label>
                                    	 <div class="col-md-8">
                                    	 <input type="text" placeholder="密码" class="form-control" name="password" id="password">
                                    	 </div>
                                    </div>
                                    
                                     <div class="form-group">
                                         <label class="col-sm-2 control-label">籍贯</label>
                                    	 <div class="col-md-8">
                                    	    <!--<input type="text" placeholder="用户类型" class="form-control" id="type">-->
                                    	    <select class="form-control m-b" name="birthplace" id="birthplace">
				                                <c:forEach items="${birthplaces}" var="k">
				                                	<option value="${k.title}">${k.title}</option>
				                                </c:forEach>
				                            </select>
                                    	 </div>
                                    </div>
                                    
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">用户类型</label>
                                    	 <div class="col-md-8">
                                    	    <!--<input type="text" placeholder="用户类型" class="form-control" id="type">-->
                                    	    <select class="form-control m-b" name="type" id="type">
				                                <c:forEach items="${userTypes}" var="k">
				                                	<option value="${k.type}">${k.title}</option>
				                                </c:forEach>
				                            </select>
                                    	 </div>
                                    </div>
                                    
                                    <div class="form-group">
                                         <label class="col-sm-2 control-label">用户状态</label>
                                    	 <div class="col-md-8">
                                    	    <!--  <input type="text" placeholder="用户状态" class="form-control" id="status">-->
                                    	    <select class="form-control m-b" name="status" id="status">
				                                <c:forEach items="${userStatuss}" var="k">
				                                	<option value="${k.type}">${k.title}</option>
				                                </c:forEach>
				                            </select>
                                    	 </div>
                                    </div>
                                    
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">创建时间</label>
                                    	 <div class="col-md-8">
                                    	 <input type="text" placeholder="创建时间" class="form-control" id="createTime" disabled="true">
                                    	 </div>
                                    </div>
                         			
                         			<div class="form-group">
				                        <div class="col-sm-12 col-sm-offset-4">
				                            <button class="btn btn-primary" type="submit">修改</button>
				                        </div>
				                    </div>          
                                </form>
                               
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                            </div>

                        </div>
                    </div>
</div>   


<div id="wrapper">    

	<%@ include file="/Public/include/back/layout.jsp" %>
	<c:if test="${empty sessionScope.session_user }">
		<%@ include file="/Public/include/back/admin/loginPage.jsp" %>
	</c:if>
	
	<c:if test="${not empty sessionScope.session_user }">
		<%@ include file="/Public/include/back/user/listPage.jsp" %>
	</c:if>
 	

 	
</div>
  
 <%@ include file="/Public/jslib.jsp" %>
 <script>
 $(function(){
	  
	//DataTable表格插件
    var admin_table =  $("#admin_table").DataTable({
    	  //"paging": false, // 禁止分页
          "language": {//I18n国际化
                "decimal":        "",
                "emptyTable":     "没有数据",
                "info":           "从 第_START_ 到 _END_条 /共 _TOTAL_ 条数据",
                "infoEmpty":      "从 0到 0 /共 0条数据",
                "infoFiltered":   "(从 _MAX_ 条数据中检索)",
                "infoPostFix":    "",
                "thousands":      ",",
                "lengthMenu":     "每页显示 _MENU_ 条记录",
                "loadingRecords": "加载中...",
                "processing":     "搜索中...",
                "search":         "搜索:",
                "zeroRecords":    "没有检索到数据",
                "paginate": {
                    "first":      "首页",
                    "last":       "尾页",
                    "next":       "后一页",
                    "previous":   "前一页"
                },
          },
          // 设置相关排列 - col-sm bootstrap来做响应式编程
          "dom": 't<".col-sm-4"l><".col-sm-4"i><".col-sm-4"p>',
          "orderMulti": true, //可以多行排序
          "processing": true, //进度条
          //"serverSide": true,  //服务端#分页 
           ajax: {
                 url: '${context}/admin/user?method=ajaxquery',
                 dataSrc: '' // data:{分类列表}，page：1
           },
           /**/
          //data: productConfig,
          //"columnDefs": [ {
          //"targets": 8,
          //"orderable": false
          //} ],
          "columns": [
                    { data: "id"},
                    { data: "username"},
                    { data: "password"},
                    { data: "typeName"},
                    { data: "statusName"},
                    { data: "birthplace"},
                    { data: "createTime"},
                    {
                    	"orderable":      false,
                    	"sWidth": "120px", 
                    	"data": "id", 
                    	"mRender": 
                    	function(data, type, full) {   
                    		
                    		var usertype = ('${sessionScope.session_user.type}');
                    		var dtype = (full.type);
                    		//href="${context}/admin/user?method=upd&id='+data+'"
                    		var delNode = '<a href="${context}/admin/user?method=del&id='+data+'"><button class="btn btn-primary btn-sm">删除</button></a>'
                    		var updNode = '<a class="updUser" id="'+data+'" data-toggle="modal" data-target="#userModal" ><button class="btn btn-primary btn-sm">修改</button></a>';
                    		
                    		var dataNode = '<span id="dataNode'+data+'" style="display:none;">'+full+'</span>';
                    		if(usertype < dtype)
                    		{
                    			delNode = '<strong>删除</strong>';
                    			updNode = '<strong>修改</strong>';
                    			//return delNode +" / "+ updNode + ;  
                    		}
                    		$(".updUser#"+data).click(function(){
                    			//alert($(this).attr("id"));
                    			$("#modalTitleMsg").html("修改用户");
                    			
                    			$("#modalFrom #id").val(full.id);
                    			$("#modalFrom #userid").val(full.id);
                    			$("#modalFrom #username").val(full.username);
                    			$("#modalFrom #password").val(full.password);
                    			$("#modalFrom #type").val(full.type);
                    			$("#modalFrom #birthplace").val(full.birthplace);
                    			//$("#type option").attr("selected", false);
                    			//$("#type option[value="+full.type+"]").attr("selected", true);
                    			//$("#status option").attr("selected", false);
                    			//$("#status option[value="+full.status+"]").attr("selected", true);
                    			$("#modalFrom #status").val(full.status);
                    			$("#modalFrom #createTime").val(full.createTime);
                    			 
                    		});
                    		//else
                        	return delNode +" / " + updNode + dataNode;  
                        } 
                    }
                    /**/
          ],
          "order": [ 0, 'asc' ],                         //按第几列排序
          "lengthMenu": [ [ 20, 100], [20, 100] ] //每页显示多少条
    }); 
	//
    $('#s_username').on( 'change', function () {
		 admin_table
     .columns( 1 )
     .search( this.value )
     .draw();
	 });
	
    $('#s_usertype').on( 'change', function () {
		 var value = $(this).find("option:selected").text();
		 if(this.value == ""){
			 admin_table.columns( 3 )
	         .search("").draw(); 
		 }
		 else{
			 admin_table
	         .columns( 3 )
	         .search(value)
	         .draw();
		 }
	 });
    
    $('#s_userstatus').on( 'change', function () {
		 var value = $(this).find("option:selected").text();
		 if(this.value == ""){
			 admin_table.columns( 4 )
	         .search("").draw(); 
		 }
		 else{
			 admin_table
	         .columns( 4 )
	         .search(value)
	         .draw();
		 }
	 });
	
	
 });
 </script>
</body>
</html>