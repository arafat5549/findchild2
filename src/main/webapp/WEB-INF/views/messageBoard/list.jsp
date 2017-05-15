<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/Public/csslib.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>留言板</title>


</head>
<body>
   
<%@ include file="/Public/include/fore/top.jsp" %>

<div id="wrapper">    

	<%@ include file="/Public/include/fore/sidemenu.jsp" %>
	
	<%@ include file="/Public/include/fore/messageBoard/listPage.jsp" %>
   
</div>
  
 <%@ include file="/Public/jslib.jsp" %>
 
 
    <script>
        $(document).ready(function(){
            $('.summernote').summernote();
            
            $("form").submit(function(e){
            	 var aHTML = $('.click2edit').code();
            	 $("input#content").attr("value",aHTML);//把内容带过去
           });
       });
    </script>
 
 
 
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
	
	//DataTable表格插件
    var xunqin_table =  $("#xunqin_table").DataTable({
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
                 url: '${context}/message?method=ajaxquery',
                 dataSrc: '' // data:{分类列表}，page：1
           },
           /**/
          //data: productConfig,
          //"columnDefs": [ {
          //"targets": 8,
          //"orderable": false
          //} ],
          "columns": [
                    { data: "typeName"},
                    { data: "title"},
                    { data: "content"}, 
                    { data: "user.username"},
                    { data: "createTime"},
                    
                    {
                    	"orderable":      false,
                    	"sWidth": "50px", 
                    	"data": "user.id", 
                    	//"data2": "id", 
                    	"mRender": 
                    	function(data, type, full) { 
                    		var userid = Number('${sessionScope.session_user.id}');
                    		//console.log(data,type,full);
                    		if(data === userid)
                        	  return '<a href ="${context}/message?method=del&id='+full.id+'">删除</a>';
                        	else
                        	  return '';	  
                        } 
                    }
                    /**/
          ],
          "order": [ 4, 'desc' ],                         //按第几列排序
          "lengthMenu": [ [ 5, 20, 100], [ 5, 20, 100] ] //每页显示多少条
    });
 });

 
</script>

</body>
</html>