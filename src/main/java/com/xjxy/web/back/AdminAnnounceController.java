package com.xjxy.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.Announce;
import com.xjxy.model.User;
import com.xjxy.service.AnnounceService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;

@SuppressWarnings("serial")
public class AdminAnnounceController extends HttpServlet{

	AnnounceService announceService = new AnnounceService();
	public static final String VIEW_PATH = "/WEB-INF/views/admin/announce/";

	private void listAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		req.setAttribute("title", "公告管理");
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
	private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/admin/announce?method=list");
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
			List<Announce> lists = announceService.findAll();
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
			boolean flag = announceService.delete(id);
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
			Announce record = new Announce();
			record.setTitle(title);
			record.setContent(content); 
			record.setUserId(admin.getId());
			boolean flag = announceService.save(record);
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
			Announce record = announceService.findById(id);
			if(record == null){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.INVALID_ANNOUNCE);
				listAction(req,resp);
				return;
			}
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			record.setTitle(title);
			record.setContent(content);
			boolean flag = announceService.update(record);
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
