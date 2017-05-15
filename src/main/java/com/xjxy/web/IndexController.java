package com.xjxy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.News;
import com.xjxy.model.Xunqin;
import com.xjxy.service.AnnounceService;
import com.xjxy.service.NewsService;
import com.xjxy.service.XunqinService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.ImageUtils;
import com.xjxy.utils.Globals.UserType;

/**
 * 主页
 * @author wyy
 * 2017年4月19日
 *
 */
public class IndexController extends HttpServlet{
	
	    //AnnounceService announceService = new AnnounceService();
		XunqinService xunqinService = new XunqinService();
	    NewsService newsService = new NewsService();
	    //所有页面要放到WEB-INF底下
		public static final String VIEW_PATH = "/WEB-INF/views/";
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			
			//req.setAttribute("announces", announceService.findAll());
			
			
			String method = req.getParameter("method");
			if("getimg".equals(method)){
				getImage(req, resp);
			}
			else{
				//跳转到主页
				List<News> newsList =newsService.findAll();
				req.setAttribute("newsList", newsList);
				
				List<Xunqin> xunqinList = xunqinService.findAll();
				req.setAttribute("xunqinList", xunqinList);
				
				
				
				req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
			}
		}
		
		void getImage(HttpServletRequest req, HttpServletResponse resp) throws IOException{
			//resp.getWriter().write(buf);
			ImageUtils.createImage(req, resp);
		}
}
