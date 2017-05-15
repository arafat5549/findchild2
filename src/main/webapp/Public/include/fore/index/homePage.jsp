<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 头部导航-开始 -->
	<div id="page-wrapper" style="margin-top:40px;">
	<div class="wrapper wrapper-content animated fadeInRight">
		<div class="row">
		
			<div class="col-sm-12">
			    <div class="ibox float-e-margins">
			        <div class="ibox-title">
			            <h5 class="label label-success">系统简介</h5>
			        </div>
			       
			        <div class="ibox-content">
			           <div class="contact-box">
	                    <div class="col-sm-4">
	                        <div class="text-center">
	                            <img alt="image" class="m-t-xs img-responsive" src="${contextStatic }/img/picture_newsNotice.gif">
	                        </div>
	                    </div>
	                    <div class="col-sm-8">
	                    <!--    <c:forEach items="${newsList }" var="item">
	                        	<h3><strong><a class="btn btn-link" href="${context }/news?method=detail&id=${item.id}">${item.title }</a></strong></h3>
	                        </c:forEach>--> 
	                                                                      本系统主要是为了给寻找亲人的朋友提供一个登记平台，通过这个平台的登记，浏览过本系统的用户，
	                                                                      可以对登记进行评论，提供相应的信息，有问题也可以给系统后台留言。
	                                                                      在本系统的寻子寻亲登记必须是真实有效的，如发现有虚假信息则会冻结该用户，追究责任，大家互相监督！
	                    </div>
	                    <div class="clearfix"></div>
	     				</div>
			        </div>
			    </div>
			</div>
		</div>
		
        
		 <!-- 主题部分-->
		<div class="row">
		
			<div class="col-sm-12">
			    <div class="ibox float-e-margins">
			        <div class="ibox-title">
			            <h5 class="label label-success">信息中心</h5>
			        </div>
			       
			        <div class="ibox-content">
			           <div class="contact-box">
	                    <div class="col-sm-4">
	                        <div class="text-center">
	                            <img alt="image" class="m-t-xs img-responsive" src="${contextStatic }/img/picture_newsNotice.gif">
	                        </div>
	                    </div>
	                    <div class="col-sm-8">
	                        <c:forEach items="${newsList}" var="item">
	                        	<h3><strong><a class="btn btn-link" href="${context}/news?method=detail&id=${item.id}">${item.title }</a></strong></h3>
	                        </c:forEach>
	                    </div>
	                    <div class="clearfix"></div>
	     				</div>
			        </div>
			    </div>
			</div>
		</div>
		
		<!--  -->
		<div class="row">
				
				<c:forEach items="${xunqinList }" var="item">
				   <div class="col-md-3">
				     <div class="ibox">
                        <div class="ibox-content product-box">
                        	<div class="test-imitation">
                                <img width="128px" height="128px" src="${context }/${item.avatarurl}" alt="${context }/Public/img/a1.jpg" onerror='javascript:this.src="${context }/Public/img/a1.jpg"'>
                            </div> 
                            <c:if test="${item.status == 1 }">
                               <span class="label label-warning">${item.statusName }</span>
                            </c:if>
                            
                            <c:if test="${item.status == 2 }">
                               <span class="label label-success">${item.statusName }</span>
                            </c:if>
                            
                            <div class="product-desc">
                            	<span class="product-price">
                                    ${item.losttypeName } - ${item.logtypeName }
                                </span>
                              
                                <div class="text-muted">联系人:${item.contact }</div>
                                <div class="text-muted">电话:${item.mobile }</div>
                                
                                <a href="${context }/comment?method=list&xid=${item.id}" class="product-name"> ${item.logname }[${item.birthplace }]</a>
                            
                            	<div class="small m-t-xs">
                                    ${item.note }
                                </div>
                                <div class="m-t text-righ">
                                    <a href="${context }/comment?method=list&xid=${item.id}" class="btn btn-xs btn-outline btn-primary">查看 <i class="fa fa-long-arrow-right"></i> </a>
                                </div>
                            </div>
                        </div>
                     </div>
                   </div>
   				 </c:forEach>
				<!--  
				<div class="col-md-3">
                    <div class="ibox">
                        <div class="ibox-content product-box">

                            <div class="product-imitation">
                                [ INFO ]
                            </div>
                            <div class="product-desc">
                                <span class="product-price">
                                    $10
                                </span>
                                <small class="text-muted">Category</small>
                                <a href="#" class="product-name"> Product</a>



                                <div class="small m-t-xs">
                                    Many desktop publishing packages and web page editors now.
                                </div>
                                <div class="m-t text-righ">

                                    <a href="#" class="btn btn-xs btn-outline btn-primary">Info <i class="fa fa-long-arrow-right"></i> </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
				-->

            </div>	
			
			
			
</div>
</div>
 