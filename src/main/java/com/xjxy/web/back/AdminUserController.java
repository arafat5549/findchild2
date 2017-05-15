package com.xjxy.web.back;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xjxy.model.User;
import com.xjxy.service.UserService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.BirthPlace;
import com.xjxy.utils.Globals.UserStatus;
import com.xjxy.utils.Globals.UserType;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;


@SuppressWarnings("serial")
public class AdminUserController extends HttpServlet{
	
	UserService userService = new UserService();
	//所有页面要放到WEB-INF底下
	public static final String VIEW_PATH = "/WEB-INF/views/admin/user/";
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		User admin = Globals.getLoginUser(req);
		
		String method = req.getParameter("method");
		if("list".equals(method)){ 
			req.setAttribute("title", "用户管理");
			
			List<UserType> userTypes = Globals.findUserTypes();
			req.setAttribute("userTypes", userTypes);
			
			List<UserStatus> userStatuss = Globals.findUserStatuss();
			req.setAttribute("userStatuss", userStatuss);
			List<BirthPlace> birthplaces = Globals.findBirthPlaces();
			req.setAttribute("birthplaces", birthplaces);
			
			req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
		}
		else if("ajaxquery".equals(method)){
			//Ajax传json数据
			List<User> lists = userService.findAll();
			String json = JsonMapper.getInstance().toJson(lists);
			resp.getWriter().write(json);
			//传递List列表
			//req.setAttribute("lists", lists);
		}
		else if("del".equals(method)){
			int id = Integer.parseInt(req.getParameter("id"));
			User target = userService.findById(id);
			boolean f1 = Globals.CheckPrivilegeOnUser(admin,target);
			
			if(!f1){
				req.setAttribute("msg", "权限不够无法操作");
				
				resp.sendRedirect(req.getContextPath()+"/admin/user?method=list");
				//req.setAttribute("title", "用户管理");
				//req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
			}
			else{
				boolean flag = userService.delete(id);
				if(flag){
					resp.sendRedirect(req.getContextPath()+"/admin/user?method=list");
				}
				else{
					req.setAttribute("msg", "无法删除");
					
					resp.sendRedirect(req.getContextPath()+"/admin/user?method=list");
					//req.setAttribute("title", "用户管理");
					//req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
				}
			}
			
		}
		//else if("upd".equals(method)){}
	}
	//
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		User admin = Globals.getLoginUser(req);
		String method = req.getParameter("method");
		if("upd".equals(method)){
			
			int id = Integer.parseInt(req.getParameter("id"));
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			int type = Integer.parseInt(req.getParameter("type"));
			int status = Integer.parseInt(req.getParameter("status"));
			String birthplace = req.getParameter("birthplace");
//			User target = new User();
//			target.setUsername(username);
//			target.setPassword(password);
//			target.setStatus(status);
//			target.setType(type);
//			System.out.println(req.getParameter("userid"));
//			System.out.println(req.getParameter("id"));
//			System.out.println(target);
			
			User target = userService.findById(id);
			boolean f1 = Globals.CheckPrivilegeOnUser(admin,target);
			if(!f1){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_UPDATE_RESULT_ERROR);
				resp.sendRedirect(req.getContextPath()+"/admin/user?method=list");
			}
			else{
				target.setUsername(username);
				target.setPassword(password);
				target.setStatus(status);
				target.setType(type);
				target.setBirthplace(birthplace);
				boolean flag = userService.update(target);
				if(flag){
					resp.sendRedirect(req.getContextPath()+"/admin/user?method=list");
				}
				else{
					req.setAttribute("msg", "无法更新");
					resp.sendRedirect(req.getContextPath()+"/admin/user?method=list");
				}
			}
		}
	}
}
