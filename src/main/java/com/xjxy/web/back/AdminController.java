package com.xjxy.web.back;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.User;
import com.xjxy.service.UserService;
import com.xjxy.utils.Globals;

/**
 * 后台页面
 * @author wyy
 * 2017年4月19日
 *
 */

@SuppressWarnings("serial")
public class AdminController extends HttpServlet{
	
	UserService userService = new UserService();
	//所有页面要放到WEB-INF底下
	public static final String VIEW_PATH = "/WEB-INF/views/admin/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("method");
		if("login".equals(method)){
			req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);
		}
		else if("logout".equals(method)){
			req.getSession().setAttribute(Globals.SESSION_USER, null);
			req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
		}
		else
		{
			req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("login".equals(method)){
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			
			//映射成JavaBean 
			User user =new User();
			user.setUsername(username);
			user.setPassword(password);
			String error = userService.loginAdmin(user);
			//System.out.println(error);
			if(error!=null && !"".equals(error)){
				req.setAttribute("msg", error);
				req.getRequestDispatcher(VIEW_PATH+"home.jsp").forward(req, resp);
			}
			else{
				req.setAttribute("msg", "注册成功");
				User exist = userService.findByName(username);
				req.getSession().setAttribute(Globals.SESSION_USER, exist);
				resp.sendRedirect(req.getContextPath()+"/admin");
			}
		}
	}
}
