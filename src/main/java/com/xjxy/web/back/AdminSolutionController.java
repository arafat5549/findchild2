package com.xjxy.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import com.xjxy.model.Solution;
import com.xjxy.model.User;
import com.xjxy.service.SolutionService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;
import com.xjxy.utils.Globals.LogType;


@SuppressWarnings("serial")
public class AdminSolutionController extends HttpServlet{

	SolutionService solutionService = new SolutionService();
	public static final String VIEW_PATH = "/WEB-INF/views/admin/solution/";
	
	private void listAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		req.setAttribute("title", "解决方案管理");
		List<LogType> logtypes = Globals.findLogTypes();
		req.setAttribute("logTypes", logtypes);  
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
	private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/admin/solution?method=list");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){ 
			listAction(req, resp);
		}
		else if("ajaxquery".equals(method)){
			List<Solution> lists = solutionService.findAll();
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
			boolean flag = solutionService.delete(id);
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
			Solution record = new Solution();
			record.setTitle(title);
			record.setContent(content); 
			record.setUserId(admin.getId());
			boolean flag = solutionService.save(record);
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
			Solution record = solutionService.findById(id);
			if(record == null){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.INVALID_SOLUTION);
				listAction(req,resp);
				return;
			}
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			int logtype = NumberUtils.toInt(req.getParameter("logtype"));
			record.setTitle(title);
			record.setContent(content);
			record.setLogtype(logtype); 
			boolean flag = solutionService.update(record);
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
