<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
                <div class="modal inmodal" id="myModal" tabindex="-1" role="dialog" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content animated bounceInRight">

                           <div class="modal-header">
                                 <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
                                 </button>
                                <!--<i class="fa fa-laptop modal-icon"></i>-->
                                <h4 class="modal-title">Modal 标题</h4>
                                 
                            </div>
                            
                            <div class="modal-body">
                                <p id="modalBodyMsg">
                                    <strong>粗体信息</strong> 
                                    信息
                                </p>

                                <form id="modalFrom">
                                    <div class="form-group">
                                    	<input type="email" placeholder="邮件" class="form-control">
                                    </div>
                                </form>
                            </div>

                            <div class="modal-footer">
                                <button type="button" class="btn btn-white" data-dismiss="modal">Close</button>
                                <button type="button" class="btn btn-primary">Save changes</button>
                            </div>

                        </div>
                    </div>
                </div>