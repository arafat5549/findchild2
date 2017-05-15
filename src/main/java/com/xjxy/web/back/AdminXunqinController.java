package com.xjxy.web.back;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.xjxy.model.User;
import com.xjxy.model.Xunqin;
import com.xjxy.service.XunqinService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.BirthPlace;
import com.xjxy.utils.Globals.LogType;
import com.xjxy.utils.Globals.LostType;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;
import com.xjxy.web.XunqinController;

@SuppressWarnings("serial")
public class AdminXunqinController extends HttpServlet{

	XunqinService xunqinService = new XunqinService();
	public static final String VIEW_PATH = "/WEB-INF/views/admin/xunqin/";
	
	private void listAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		req.setAttribute("title", "寻子寻亲管理");
		List<LogType> logtypes = Globals.findLogTypes();
		req.setAttribute("logTypes", logtypes);
		List<LostType> lostTypes = Globals.findLostTypes();
		req.setAttribute("lostTypes", lostTypes);
		List<BirthPlace> birthplaces = Globals.findBirthPlaces();
		req.setAttribute("birthplaces", birthplaces);
		req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
	}
	private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		resp.sendRedirect(req.getContextPath()+"/admin/xunqin?method=list");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String method = req.getParameter("method");
		if("list".equals(method)){ 
			listAction(req, resp);
		}
		else if("ajaxquery".equals(method)){
			User admin = Globals.getLoginUser(req);
			//过滤籍贯 自己的帖子和籍贯相关
			if(admin!=null && admin.getType() == Globals.UserType.UserType_ADMIN.getType()){
				//admin.getBirthplace()
				List<Xunqin> mylist = new ArrayList<Xunqin>();
				List<Xunqin> lists = xunqinService.findAll();
				for (Xunqin xunqin : lists) {
					boolean f1 = xunqin.getUserId() == admin.getId();
					boolean f2 = admin.getBirthplace()!=null && admin.getBirthplace().equals(xunqin.getBirthplace());
					if(f1 || f2){
						mylist.add(xunqin);
					}
				}
				String json = JsonMapper.getInstance().toJson(mylist);
				resp.getWriter().write(json);
			}
			else{
				List<Xunqin> lists = xunqinService.findAll();
				String json = JsonMapper.getInstance().toJson(lists);
				resp.getWriter().write(json);
			}
			
			
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
			boolean flag = xunqinService.delete(id);
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
		boolean f2 = ServletFileUpload.isMultipartContent(req);
		if(f2){
			Xunqin xunqin = new Xunqin();
			String method = XunqinController.uploadImage(xunqin,req,resp);
			xunqin.setUserId(admin.getId());
			if("add".equals(method))
			{
				boolean flag = xunqinService.save(xunqin);
				if(flag){
					listRedirectAction(req, resp);
				}
				else{
					req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_INSERT_RESULT_ERROR);
					listAction(req,resp);
				}
			}
			else if("upd".equals(method)){
				Xunqin record = xunqinService.findById(xunqin.getId());
				if(xunqin.getAvatarurl()==null){
					xunqin.setAvatarurl(record.getAvatarurl());
				}
				if(xunqin.getCreateTime()==null){
					xunqin.setCreateTime(record.getCreateTime());
				}
				
				try {
					BeanUtils.copyProperties(record, xunqin);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
				
				boolean flag = xunqinService.update(xunqin);
				if(flag){
					listRedirectAction(req, resp);
				}
				else{
					req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_UPDATE_RESULT_ERROR);
					listAction(req,resp);
				}
			}
			
		}
		else{
		}
		
		
		/*
		String method = req.getParameter("method");
		if("add".equals(method))
		{
			boolean flag = xunqinService.save(record);
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
			Xunqin record = xunqinService.findById(id);
			if(record == null){
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.INVALID_SOLUTION);
				listAction(req,resp);
				return;
			}
			String title = req.getParameter("title");
			String content = req.getParameter("content");
			record.setTitle(title);
			record.setContent(content);
			boolean flag = xunqinService.update(record);
			if(flag){
				listRedirectAction(req, resp);
			}
			else{
				req.setAttribute(Globals.ERROR_MESSAGE, ResultEnum.DB_UPDATE_RESULT_ERROR);
				listAction(req,resp);
			}
		}
		*/
	}
}
