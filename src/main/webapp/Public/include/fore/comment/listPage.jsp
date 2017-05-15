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
                                  <th>操作</th>
                            </tr>
                        </thead>
                        <tbody>
                                 <%-- 
                                <c:forEach items="${lists }" var="item">
							       <tr>
							             <td class="details-control"></td>
							             <td>${item.logtypeName }</td>
							             <td>${item.logname }</td>
							              <td><img class="img-responsive" width="150px" height="100px" src="${context }/${item.avatarurl }" ></td>
							             <td>${item.birthplace }</td>
							             <td>${item.age }</td>
							             <td>${item.losttypeName }</td>
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
 
<!-- 评论区域 -->    
<div class="row">
                <div class="col-sm-12">
                    <div class="wrapper wrapper-content animated fadeInRight">
                        <div class="ibox-content forum-post-container">
                            <!--  
                            <div class="forum-post-info">
                                <h4><span class="text-navy"><i class="fa fa-globe"></i> General discussion</span> - Announcements - <span class="text-muted">Free talks</span></h4>
                            </div>
                            
                            <div class="media">
                                 
                                <a class="forum-avatar" href="#">
                                    <img src="img/a1.jpg" class="img-circle" alt="image">
                                    <div class="author-info">
                                        <strong>帖子数:</strong> 542<br/>
                                        <strong>注册时间:</strong> April 11.2015<br/>
                                    </div>
                                </a>
                                <div class="media-body">
                                    <h4 class="media-heading">Title标题</h4>
                                    
                                    Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old.
                                    <br/><br/>
                                    Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of "de Finibus Bonorum et Malorum" (The Extremes of Good and Evil) by Cicero, written in 45 BC. This book is a treatise on the theory of ethics, very popular during the Renaissance. The first line of Lorem Ipsum, "Lorem ipsum dolor sit amet..", comes from a line in section 1.10.32.
                                    <br/><br/>
                                    - Mike Smith
                                    CEO, Zender Inc.
                                </div>
                                
                            </div>
                            -->
                            <c:forEach items="${ comments}" var="item">
                                <div class="media">
                                     <a class="forum-avatar" href="#">
		                                  <img src="${contextStatic }/img/profile_small.jpg" class="img-circle" alt="image">
		                                  <div class="author-info">
		                                        <strong>用户名称:</strong>${item.user.username }<br/>
		                                        <!--<strong>帖子数:</strong> 542<br/>  -->
		                                        <strong>注册时间:</strong><fmt:formatDate value="${item.user.createTime }" type="date"/><br/>
		                                  </div>
                                     </a>
                                     
                                     <div class="media-body">
                                         <h4 class="media-heading">${item.title }</h4>
                                         ${item.content }
                                         <br/><br/>
                                         <fmt:formatDate value="${item.createTime }" type="both"/>
                                     </div>
                                </div>
                            </c:forEach>
                            
                            
                          </div>

                            </div>
                        </div>
</div>

<!-- 添加评论 -->
<div class="row">
                <div class="col-sm-12">
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5>提供信息</h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content no-padding">
							<form class="form-horizontal" role="form" method="post" action="${context }/comment">
							    <div class="form-group">
							       <input type="hidden" name="xid" value="${param.xid }">
							       <input type="hidden" name="method" value="add">
							       <input type="hidden" id="content" name="content" value="">
							     </div>
							   <div class="form-group">
			                          <label class="col-sm-2 control-label">标题</label>
			                         <div class="col-md-8">
			                            <input type="text" class="form-control" name="title" id="title">
			                        </div>
			                        
			                    </div>
			                    
								 <div class="click2edit summernote wrapper p-md" style="display: block;" >
	                               
	                             </div>
	                             
                                 <div class="form-group">
			                        <div class="col-sm-4 col-sm-offset-2">
			                            <button class="btn btn-primary" type="submit" >添加</button>
			                        </div>
                    			</div>
							</form>
                            
  
                        </div>
                    </div>
                </div>
</div> 


</div>
</div>