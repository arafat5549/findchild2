package com.xjxy.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.Link;
import com.xjxy.service.LinkService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;

@SuppressWarnings("serial")
public class AdminLinkController extends HttpServlet{

	LinkService linkService = new LinkService();
	public static final String VIEW_PATH = "/WEB-INF/views/admin/link/";
	
	private void listAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		req.setAttribute("title", "网站链接管理");
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
	private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/admin/link?method=list");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){ 
			listAction(req,resp);
		}
		else if("ajaxquery".equals(method)){
			List<Link> lists = linkService.findAll();
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
			boolean flag = linkService.delete(id);
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
		
		String method = req.getParameter("method");
		if("add".equals(method))
		{
			String title = req.getParameter("title");
			String href = req.getParameter("href");
			Link record = new Link();
			record.setTitle(title);
			record.setHref(href); 
			boolean flag = linkService.save(record);
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
			Link record = linkService.findById(id);
			if(record == null){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.INVALID_LINK);
				listAction(req,resp);
				return;
			}
			String title = req.getParameter("title");
			String href = req.getParameter("href");
			record.setTitle(title);
			record.setHref(href);
			boolean flag = linkService.update(record);
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
