package com.xjxy.web;

import java.util.List;

import javax.servlet.http.HttpServlet;

import com.xjxy.model.Announce;
import com.xjxy.service.AnnounceService;
import com.xjxy.utils.JsonMapper;

/**
 * 公告web层
 * 
 * 控制参数传递和页面跳转
 * 
 * @author wyy
 * 2017年4月19日
 *
 */
public class AnnounceController extends HttpServlet{
	
	AnnounceService announceService = new AnnounceService();
	protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse resp) 
			throws javax.servlet.ServletException ,java.io.IOException {
		String method = req.getParameter("method");
		if("ajaxquery".equals(method)){//ajax请求
			List<Announce> lists = announceService.findAll();
			String json = JsonMapper.getInstance().toJson(lists);
			resp.getWriter().write(json);
		}
	};
	
	
}
