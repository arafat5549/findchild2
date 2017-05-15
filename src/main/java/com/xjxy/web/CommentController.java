package com.xjxy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.Comment;
import com.xjxy.model.User;
import com.xjxy.model.Xunqin;
import com.xjxy.service.CommentService;
import com.xjxy.service.XunqinService;
import com.xjxy.utils.Globals;

public class CommentController extends HttpServlet{
    
	public static final String VIEW_PATH = "/WEB-INF/views/comment/";
	CommentService commentService = new CommentService();
	XunqinService  xunqinService  = new XunqinService();
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		//System.out.println(method); 
		if("list".equals(method)){
			req.setAttribute("title", "寻子寻亲-评论");
			int xId = Integer.parseInt(req.getParameter("xid"));
			List<Comment> lists = commentService.findCommentsByXId(xId);
			req.setAttribute("comments", lists);
			req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);;
		}
		else if("add".equals(method)){
			int xId = Integer.parseInt(req.getParameter("xid"));
			Xunqin xunqin = xunqinService.findById(xId);
			if(xunqin ==null){
				req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
			else{
				String title = req.getParameter("title");
				String code = req.getParameter("code");
				Comment c = new Comment();
				c.setContent(code);
				c.setTitle(title);
			}
			
		}
	}
	
	//
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User user = Globals.getLoginUser(req);
		
		
		String method = req.getParameter("method");
		
		//System.out.println(method);
		if("add".equals(method)){
			int xId = Integer.parseInt(req.getParameter("xid"));
			Xunqin xunqin = xunqinService.findById(xId);
			
			//System.out.println(xId);
			if(xunqin ==null){
				req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
			else{
				//System.out.println(xunqin);
				String title = req.getParameter("title");
				String code = req.getParameter("content");
				System.out.println("content:"+code);
				Comment c = new Comment();
				c.setContent(code);  
				c.setTitle(title);
				c.setUserId(user.getId());
				c.setXunqinId(xId); 
				commentService.save(c);
				
				
				List<Comment> lists = commentService.findCommentsByXId(xId);
				req.setAttribute("comments", lists);
				req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
			
		}
	}
	
}
