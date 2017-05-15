package com.xjxy.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.Solution;
import com.xjxy.service.SolutionService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.Globals.LogType;

public class SolutionController extends HttpServlet{
	
	SolutionService solutionService = new SolutionService();
    //所有页面要放到WEB-INF底下
	public static final String VIEW_PATH = "/WEB-INF/views/solution/";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){
			req.setAttribute("title", "解决方案");
			List<LogType> logtypes = Globals.findLogTypes();
			req.setAttribute("logTypes", logtypes);  
			req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
		}
		else if("ajaxquery".equals(method)){
			List<Solution> lists = solutionService.findAll();//new ArrayList<Solution>();
			String json = JsonMapper.getInstance().toJson(lists);
			resp.getWriter().write(json);
		}
	}
}
