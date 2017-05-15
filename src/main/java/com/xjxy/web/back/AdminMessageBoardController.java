package com.xjxy.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.MessageBoard;
import com.xjxy.model.User;
import com.xjxy.service.MessageBoardService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.MessageType;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;

@SuppressWarnings("serial")
public class AdminMessageBoardController extends HttpServlet{
	MessageBoardService messageBoardService = new MessageBoardService();
	public static final String VIEW_PATH = "/WEB-INF/views/admin/messageBoard/";

	private void listAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		req.setAttribute("title", "留言管理");
		
		List<MessageType> messageTypes = Globals.findMessageTypes();
		req.setAttribute("messageTypes", messageTypes);
		
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
	private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/admin/message?method=list");
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
			List<MessageBoard> lists = messageBoardService.findAll();
			String json = JsonMapper.getInstance().toJson(lists);
			resp.getWriter().write(json);
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
			boolean flag = messageBoardService.delete(id);
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
		//System.out.println(method);
		if("add".equals(method))
		{
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int type = Integer.parseInt(req.getParameter("type"));
			MessageBoard record = new MessageBoard();
			record.setTitle(title);
			record.setContent(content); 
			record.setType(type); 
			record.setUserId(admin.getId());
			boolean flag = messageBoardService.save(record);
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
			MessageBoard record = messageBoardService.findById(id);
			if(record == null){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.INVALID_MESSAGEBOARD);
				listAction(req,resp);
				return;
			}
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int type = Integer.parseInt(req.getParameter("type"));
			record.setTitle(title);
			record.setContent(content);
			record.setType(type);
			boolean flag = messageBoardService.update(record);
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
