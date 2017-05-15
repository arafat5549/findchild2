package com.xjxy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.Comment;
import com.xjxy.model.MessageBoard;
import com.xjxy.model.User;
import com.xjxy.service.MessageBoardService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.MessageType;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.Globals.LostType;

public class MessageBoardController extends HttpServlet{
	
	
	
	MessageBoardService messageBoardService = new MessageBoardService();
	public static final String VIEW_PATH = "/WEB-INF/views/messageBoard/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = Globals.getLoginUser(req);
		
		String method = req.getParameter("method");
		if("list".equals(method)){
			req.setAttribute("title", "留言板");
			
			List<MessageType> messageTypes = Globals.findMessageTypes();
			req.setAttribute("messageTypes", messageTypes);
			
			req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
		}
		else if("ajaxquery".equals(method)){
			List<MessageBoard> lists = messageBoardService.findAll();
			String json = JsonMapper.getInstance().toJson(lists);
			resp.getWriter().write(json);
		}
		else if("del".equals(method)){
			int id = Integer.parseInt(req.getParameter("id"));
			MessageBoard m = messageBoardService.findById(id);
			
			if(m!=null && user!=null && m.getUserId().intValue() == user.getId().intValue()){
				messageBoardService.delete(id);
				resp.sendRedirect(req.getContextPath()+"/message?method=list");
			}
			else{
				req.setAttribute("msg", "无法删除");
				req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
			//messageBoardService.delete
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User user = Globals.getLoginUser(req);
		
		String method = req.getParameter("method");
		if("add".equals(method)){
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int type = Integer.parseInt(req.getParameter("type"));
			int userId = user!=null ? user.getId() : 0;
			
			MessageBoard c = new MessageBoard();
			c.setContent(content);  
			c.setTitle(title);
			c.setType(type);
			c.setUserId(userId);
			messageBoardService.save(c);
			 
			resp.sendRedirect(req.getContextPath()+"/message?method=list");
		}
	}
}
