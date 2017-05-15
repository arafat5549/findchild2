package com.xjxy.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.math.NumberUtils;

import com.xjxy.model.User;
import com.xjxy.service.UserService;
import com.xjxy.utils.CookieUtils;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.BirthPlace;
import com.xjxy.utils.Globals.UserType;

public class UserController extends HttpServlet{
	public static final String VIEW_PATH = "/WEB-INF/views/user/";
	private UserService userService = new UserService();
	//
	private static String session_remember_username = "c_username";
	private static String session_remember_password = "c_password";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("login".equals(method)){
			//从cookie里获取登录名和密码
			String username = CookieUtils.getCookie(req, session_remember_username);
			String password = CookieUtils.getCookie(req, session_remember_password);
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			req.setAttribute("session_user", user);
			
			req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);
		}
		else if("register".equals(method)){
			List<UserType> userTypes = Globals.findLoginUserTypes();
			req.setAttribute("userTypes", userTypes);
			List<BirthPlace> birthplaces = Globals.findBirthPlaces();
			req.setAttribute("birthplaces", birthplaces);
			
			req.getRequestDispatcher(VIEW_PATH+"register.jsp").forward(req, resp);
		}
		else if("logout".equals(method)){
			req.getSession().setAttribute(Globals.SESSION_USER, null);
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String method = req.getParameter("method");
		if("login".equals(method)){
			//1.获取参数
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			User user = new User();
			user.setUsername(username);
			user.setPassword(password);
			System.out.println("-------------");
			
			
			String error = userService.login(user);
			if(error!=null && !"".equals(error)){
				req.setAttribute("msg", error);
				req.getRequestDispatcher(VIEW_PATH+"login.jsp").forward(req, resp);
			}
			else{
				//登录为存cookie
				CookieUtils.setCookie(resp, session_remember_username, username);
				CookieUtils.setCookie(resp, session_remember_password, password);
				
				req.setAttribute("msg", "登录成功");
				User exist = userService.findByName(username);
				req.getSession().setAttribute(Globals.SESSION_USER, exist);
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				//req.getRequestDispatcher("index.jsp").forward(req, resp);
			}
		}
		else if("register".equals(method)){
			doRegister(req, resp);
		}
		        
	}
	
	private void doRegister(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException{
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		int type = NumberUtils.toInt(req.getParameter("type"),Globals.UserType.UserType_NORMAL.getType());
		String birthplace = req.getParameter("birthplace");
		
		//映射成JavaBean
		User user =new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setType(type); 
		user.setBirthplace(birthplace);
		String error = userService.register(user);
		if(error!=null && !"".equals(error)){
			req.setAttribute("msg", error);
			req.getRequestDispatcher(VIEW_PATH+"register.jsp").forward(req, resp);
		}
		else{
			req.setAttribute("msg", "注册成功");
			//req.setAttribute("user", user);
			//req.getRequestDispatcher(VIEW_PATH+"/login.jsp").forward(req, resp);
			req.getSession().setAttribute(Globals.SESSION_USER, userService.findByName(username));
			resp.sendRedirect(req.getContextPath()+"/index.jsp");
		}
		
	}
}
