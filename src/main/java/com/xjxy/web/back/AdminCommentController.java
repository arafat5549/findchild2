package com.xjxy.web.back;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import com.xjxy.model.Comment;
import com.xjxy.model.User;
import com.xjxy.model.Xunqin;
import com.xjxy.service.CommentService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;

@SuppressWarnings("serial")
public class AdminCommentController extends HttpServlet{
	CommentService commentService = new CommentService();
	public static final String VIEW_PATH = "/WEB-INF/views/admin/comment/";

	private void listAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		req.setAttribute("title", "评论管理");
		
		int xId = NumberUtils.toInt(req.getParameter("xid"));
		List<Comment> lists = commentService.findCommentsByXId(xId);
		req.setAttribute("comments", lists);
		
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
	private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/admin/comment?method=list");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("method");
		if("list".equals(method)){ 
			listAction(req,resp);
			//req.setAttribute("title", "公告管理");
			//req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
		}
		else if("ajaxquery".equals(method)){
			
			
			int xid = NumberUtils.toInt(req.getParameter("xid"));
			if(xid>0){
				List<Comment> lists = commentService.findCommentsByXId(xid); 
				String json = JsonMapper.getInstance().toJson(lists);
				resp.getWriter().write(json);
			}
			else{
				List<Comment> lists = commentService.findAll();
				String json = JsonMapper.getInstance().toJson(lists);
				resp.getWriter().write(json);
			}
			
			
		}
		else if("del".equals(method)){
			boolean f1 = Globals.CheckLoginAdmin(req);
			if(!f1)
			{
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.PEMISSION_DENIED);
				listAction(req,resp);
				return;
			}
			int id = Integer.parseInt(req.getParameter("id"));
			boolean flag = commentService.delete(id);
			if(flag){
				listRedirectAction(req, resp);
			}
			else{
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_DELETE_RESULT_ERROR);
				listAction(req,resp);
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		boolean f1 = Globals.CheckLoginAdmin(req);
		if(!f1)
		{
			req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.PEMISSION_DENIED);
			listAction(req,resp);
			return;
		}
		
		User admin = Globals.getLoginUser(req);
		String method = req.getParameter("method");
		if("add".equals(method))
		{
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			Comment record = new Comment();
			record.setTitle(title);
			record.setContent(content); 
			record.setUserId(admin.getId());
			boolean flag = commentService.save(record);
			if(flag){
				listRedirectAction(req, resp);
			}
			else{
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_INSERT_RESULT_ERROR);
				listAction(req,resp);
			}
		}
		else if("upd".equals(method)){
			int id = Integer.parseInt(req.getParameter("id"));
			Comment record = commentService.findById(id);
			if(record == null){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.INVALID_COMMENT);
				listAction(req,resp);
				return;
			}
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			record.setTitle(title);
			record.setContent(content);
			boolean flag = commentService.update(record);
			if(flag){
				listRedirectAction(req, resp);
			}
			else{
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_UPDATE_RESULT_ERROR);
				listAction(req,resp);
			}
		}
	}
}
