<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ include file="/Public/taglib.jsp" %>

<%--@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"--%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<%@ include file="/Public/csslib.jsp" %>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>解决方案</title>


</head>
<body>
   
<%@ include file="/Public/include/fore/top.jsp" %>

<div id="wrapper">    

	<%@ include file="/Public/include/fore/sidemenu.jsp" %>
	
	<%@ include file="/Public/include/fore/solution/listPage.jsp" %>

</div>
  
 <%@ include file="/Public/jslib.jsp" %>
 <script>
 $(function(){
	  
	 //
	//DataTable表格插件
    var solution_table =  $("#solution_table").DataTable({
    	 
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
                 url: '${context}/solution?method=ajaxquery',
                 dataSrc: '' // data:{分类列表}，page：1
           },
           /**/
          //data: productConfig,
          //"columnDefs": [ {
          //"targets": 8,
          //"orderable": false
          //} ],
          "columns": [
					{ data: "logtypeName"},
                    { data: "title"},
                    { data: "content"},
                    { data: "createTime"},
                    { data: "user.username"}
          ],
          "order": [ 1, 'asc' ],                         //按第几列排序
          "lengthMenu": [ [ 5, 20, 100], [ 5, 20, 100] ] //每页显示多少条
    });
	 /*
	 //显示备注信息
	 $('#solution_table tbody').on('click', 'td.details-control', function () {
		
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
	 */
    //自定义查询过滤
	 $('#s_title').on( 'change', function () {
		 solution_table
        .columns( 1 )
        .search( this.value )
        .draw();
	 });
	 
	 $('#s_content').on( 'change', function () {
		 solution_table
        .columns( 2 )
        .search( this.value )
        .draw();
	 });
	 
	 $('#s_logtype').on( 'change', function () {
		 var value = $(this).find("option:selected").text();
		 if(this.value == ""){
			 solution_table.columns( 0 ).search("").draw(); 
		 }
		 else{
			 solution_table.columns( 0 )
	         .search(value)
	         .draw();
		 }
 	 });
	 
 });
 </script>

</body>
</html>