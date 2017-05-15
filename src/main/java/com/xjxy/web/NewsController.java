package com.xjxy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import com.xjxy.model.News;
import com.xjxy.service.NewsService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.NewsType;

public class NewsController extends HttpServlet{

	NewsService newsService = new NewsService();
	public static final String VIEW_PATH = "/WEB-INF/views/news/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){
			int typeId = Integer.parseInt(req.getParameter("type"));
			List<News>  lists = newsService.findNewsByType(typeId);
			req.setAttribute("title", Globals.getNewsType(typeId));
			req.setAttribute("newsList", lists);
			req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
		}
		else if("detail".equals(method)){
			int newId = NumberUtils.toInt(req.getParameter("id"));
			News news = newsService.findById(newId);
			req.setAttribute("news", news); 
			req.getRequestDispatcher(VIEW_PATH+"detail.jsp").forward(req, resp);
		}
	}
}
