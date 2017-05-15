<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/Public/csslib.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>寻子寻亲</title>


</head>
<body>
   
<%@ include file="/Public/include/fore/top.jsp" %>

<!-- 弹出框modal -->
<div class="modal inmodal" id="updModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">
                           <!-- 
                           <div class="modal-header">
                                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                 </button>
                                <h4 class="modal-title" id="modalTitleMsg">Modal 标题</h4>
                                 
                            </div>
                             -->
                            <div class="modal-body">
                                 <!-- 
                                <p id="modalBodyMsg">
                                    <strong>粗体信息</strong> 
                                   	 信息
                                </p>
								 -->
								 
                                <form class="form-horizontal" role="form" id="modalForm" method="post" action="${context }/xunqin" enctype="multipart/form-data">
                                	
                                	<div class="form-group">
                                	   <input type="hidden" name="method" value="upd">
                                	   <input type="hidden" name="id" id="id" value="">
                                	</div>
                                	<div class="form-group">
                                	    <label class="col-sm-2 control-label">编号</label>
                                    	 <div class="col-md-8">
                                    	 <input type="text" placeholder="编号" class="form-control" name="showid" id="showid" disabled="true">
                                        </div>
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
                            <input type="text" class="form-control" name="age" id="age">
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">联系人</label>
                         <div class="col-md-4">
                            <input type="text" class="form-control" name="contact" id="contact">
                        </div>
                    </div>
                    
                    <div class="form-group">
                         <label class="col-sm-2 control-label">联系方式</label>
                         <div class="col-md-4">
                            <input type="text" class="form-control" name="mobile" id="mobile">
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label class="col-sm-2 control-label">当前状态</label>
                        <div class="col-sm-4">
                            <select class="form-control m-b" name="status" id="status">
                                <c:forEach items="${statuss}" var="k">
                                	<option value="${k.type}">${k.title}</option>
                                </c:forEach>
                            </select>
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
                            <select class="form-control m-b" name="logtype" id="logtype">
                                <c:forEach items="${logTypes}" var="k">
                                	<option value="${k.type}">${k.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                   
                   <div class="form-group">
                        <label class="col-sm-2 control-label">走失类型</label>
                        <div class="col-sm-4">
                            <select class="form-control m-b" name="losttype" id="losttype">
                                <c:forEach items="${lostTypes}" var="k">
                                	<option value="${k.type}">${k.title}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    
                     <div class="form-group">
                        <label class="col-sm-2 control-label">上传图片</label>
                        <div class="col-sm-4">
	                         <input type="file" name="avatarurl">
	                         <img class="img-responsive" width="150px" height="100px" src="" id="avatarurlImg">
                        </div>
                    </div>
                    
                    
                    <div class="form-group"> 
                        <label class="col-sm-2 control-label">简介说明</label>
                        <div class="col-sm-8">
					   		 <textarea class="form-control" rows="5" placeholder="不超过250个字节" name="note" id="note"></textarea> 
					    </div>
					  </div> 
                         			
                         			<div class="form-group">
				                        <div class="col-sm-6 col-sm-offset-4">
				                            <button class="btn btn-primary" type="submit">修改</button>
				                            <button class="btn btn-white" data-dismiss="modal">关闭</button>
				                        </div>
				                        
				                    </div>          
                                </form>
                               
                            </div>
 							<!--  
                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">关闭</button>
                            </div>
							-->
                        </div>
                    </div>
</div>   

<div id="wrapper">    

	<%@ include file="/Public/include/fore/sidemenu.jsp" %>
	
	<%@ include file="/Public/include/fore/xunqin/listPage.jsp" %>

</div>
  
 <%@ include file="/Public/jslib.jsp" %>
 
 <script type="text/javascript">
 
 function xunqin_table_info (data) {
	    return '<table class="table table-striped">'+
	    '<tr>'+
	        '<td>备注:</td>'+
	        '<td>'+data.note+'</td>'+
	    '</tr>'+
	    '</table>';
	};
 
 $(function(){
	  
	 //
	     //DataTable表格插件
    var xunqin_table =  $("#xunqin_table").DataTable({
    	 
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
                 url: '${context}/xunqin?method=ajaxquery&userid=${param.userid}',
                 dataSrc: '' // data:{分类列表}，page：1
           },
           /**/
          //data: productConfig,
          //"columnDefs": [ {
          //"targets": 8,
          //"orderable": false
          //} ],
          "columns": [
					{
					    "class":          'details-control',
					    "orderable":      false,
					    "data":           "id",
					    "defaultContent": '',
					    "mRender":
                    		function(data, type, full){
                    		return '';
                    	}
					},
                    { 
						data: "logtypeName"
                    },
                    { data: "logname"},
                    { 
                    	"orderable":      false,
                    	data: "avatarurl",
                    	"mRender":
                    		function(data, type, full){
                    		return '<img class="img-responsive" width="50px" height="50px" src="${context }/'+data+'" >';
                    	}
                    },
                    { data: "birthplace"},
                    { data: "age"},
                    { data: "losttypeName"},
                    { data: "losttime"},
                    { data: "contact"},
                    { data: "mobile"},
                    { 
                    	data: "statusName",
                    	"mRender": 
                    	function(data, type, full){
                    		var node ='<span class="label label-warning">'+data+'</span>';
                    		if(full.status==2){
                    			node ='<span class="label label-success">'+data+'</span>';
                    		}
                    		return node;
                    	}
                    	
                    		
                    },
                    /**/
                    {
                    	"orderable":      false,
                    	"sWidth": "100px", 
                    	"data": "id", 
                    	"mRender": 
                    	function(data, type, full) {  
                    		var addNode = '<a href ="${context}/comment?method=list&xid='+data+'"><button class="btn btn-primary btn-sm">评论</button></a>';
                        	
                    		var updNode = '';
                    		
                    		var userid = ('${sessionScope.session_user.id}');
                    		console.log(userid,full.user.id); 
                    		if(userid == full.user.id){
                    			updNode = '<a class="updNode" id="upd'+data+'" data-toggle="modal" data-target="#updModal" ><button class="btn btn-primary btn-sm">修改</button></a>';
                    		}
                    		
                    		$(".updNode#upd"+data).click(function(){
                    			//alert($(this).attr("id"));
                    			//$("#modalTitleMsg").html("修改寻子寻亲");
                    			
                    			$("#modalForm #id").val(full.id);
                    			$("#modalForm #showid").val(full.id);
                    			
                    			$("#modalForm #logtype").val(full.logtype);
                    			$("#modalForm #logname").val(full.logname);
                    			$("#modalForm #avatarurl").val(full.avatarurl);
                    			$("#modalForm #avatarurlImg").attr("src",'${context}/'+full.avatarurl);//val(full.avatarurl);
                    			$("#modalForm #birthplace").val(full.birthplace);
                    			$("#modalForm #age").val(full.age);
                    			$("#modalForm #losttime").val(full.losttime);
                    			$("#modalForm #losttype").val(full.losttype);
                    			$("#modalForm #note").val(full.note);
                    			$("#modalForm #createTime").val(full.createTime);
                    			
                    			$("#modalForm #contact").val(full.contact);
                    			$("#modalForm #mobile").val(full.mobile);
                    			$("#modalForm #status").val(full.status);
                    		});
                    		
                    		return addNode + "/" + updNode;  
                        } 
                    }
                   
          ],
          "order": [ 0, 'asc' ],                         //按第几列排序
          "lengthMenu": [ [  20, 100], [  20, 100] ] //每页显示多少条
    });
	 //显示备注信息
	 $('#xunqin_table tbody').on('click', 'td.details-control', function () {
		
		 var tr = $(this).closest('tr');
	     var row = xunqin_table.row( tr );
	     if ( row.child.isShown() ) {
	         // This row is already open - close it
	         row.child.hide();
	         tr.removeClass('shown');
	     }
	     else {
	         // Open this row
	         row.child( xunqin_table_info (row.data()) ).show();
	         tr.addClass('shown');
		}
	 }); 
	 
	 //自定义查询过滤
	 $('#s_logname').on( 'change', function () {
		 xunqin_table
         .columns( 2 )
         .search( this.value )
         .draw();
 	 });
	 
	 $('#s_losttype').on( 'change', function () {
		 var value = $(this).find("option:selected").text();
		 if(this.value == ""){
			 xunqin_table.columns( 6 )
	         .search("").draw(); 
		 }
		 else{ 
			 xunqin_table
	         .columns( 6 )
	         .search(value)
	         .draw();
		 }
 	 });
	 
	 $('#s_logtype').on( 'change', function () {
		 var value = $(this).find("option:selected").text();
		 if(this.value == ""){
			 xunqin_table.columns( 1 )
	         .search("").draw(); 
		 }
		 else{
			 xunqin_table
	         .columns( 1 )
	         .search(value)
	         .draw();
		 }
 	 });
	 
 });

 
</script>

</body>
</html>