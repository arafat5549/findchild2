package com.xjxy.web;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.math.NumberUtils;

import com.xjxy.model.User;
import com.xjxy.model.Xunqin;
import com.xjxy.service.XunqinService;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.BirthPlace;
import com.xjxy.utils.Globals.LogType;
import com.xjxy.utils.Globals.LostType;
import com.xjxy.utils.Globals.XunqinStatus;
import com.xjxy.utils.JsonMapper;
import com.xjxy.utils.ResultEnum;


@SuppressWarnings("serial")
public class XunqinController extends HttpServlet{
	   
	    XunqinService xunqinService = new XunqinService();
	    //所有页面要放到WEB-INF底下
		public static final String VIEW_PATH = "/WEB-INF/views/xunqin/";
		
		private void listAction(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException{
			
			req.setAttribute("title", "寻子寻亲");
			
			List<LostType> lostTypes = Globals.findLostTypes();
			req.setAttribute("lostTypes", lostTypes);
			List<LogType> logtypes = Globals.findLogTypes();
			req.setAttribute("logTypes", logtypes);
			List<XunqinStatus> statuss = Globals.findXunqinStatuss();
			req.setAttribute("statuss", statuss);
			List<BirthPlace> birthplaces = Globals.findBirthPlaces();
			req.setAttribute("birthplaces", birthplaces);
			
			req.getRequestDispatcher(VIEW_PATH+"list.jsp").forward(req, resp);
		}
		
		private void listRedirectAction(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException{
			resp.sendRedirect(req.getContextPath()+"/xunqin?method=list");
		}
		
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException {
			String method = req.getParameter("method");
			if("add".equals(method)){
				req.setAttribute("title", "添加寻子寻亲");
				
				List<LogType> logtypes = Globals.findLogTypes();
				req.setAttribute("logTypes", logtypes);
				List<LostType> lostTypes = Globals.findLostTypes();
				req.setAttribute("lostTypes", lostTypes);
				List<XunqinStatus> statuss = Globals.findXunqinStatuss();
				req.setAttribute("statuss", statuss);
				List<BirthPlace> birthplaces = Globals.findBirthPlaces();
				req.setAttribute("birthplaces", birthplaces);
				
				req.getRequestDispatcher(VIEW_PATH+"add.jsp").forward(req, resp);
			}
			else if("list".equals(method)){
				listAction(req, resp);
			}
			else if("ajaxquery".equals(method)){
				String xidStr = req.getParameter("xid");
				int userid = NumberUtils.toInt(req.getParameter("userid"));
				
				if(xidStr!=null&&!"".equals(xidStr)){
					int xid = Integer.parseInt(xidStr);
					Xunqin x = xunqinService.findById(xid);
					List<Xunqin> lists = new ArrayList<Xunqin>();
					lists.add(x); 
					String json = JsonMapper.getInstance().toJson(lists);
					resp.getWriter().write(json);
				} 
				else{
					if(userid<=0){
						List<Xunqin> lists = xunqinService.findAll();
						String json = JsonMapper.getInstance().toJson(lists);
						resp.getWriter().write(json);
					}
					else{
						List<Xunqin> lists = xunqinService.findByUserId(userid);
						String json = JsonMapper.getInstance().toJson(lists);
						resp.getWriter().write(json);
					}
					
				}
				
			}
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			boolean f1 = Globals.CheckLoginUser(req);
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
					xunqin.setStatus(XunqinStatus.XunqinStatus_WAIT_FIND.getType());
					
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
		}
		
		//上传图片
		public static String uploadImage(Xunqin xunqin,HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
			
			String method = "";
			// 创建工厂
			DiskFileItemFactory dfif = new DiskFileItemFactory();
			// 使用工厂创建解析器对象
			ServletFileUpload fileUpload = new ServletFileUpload(dfif);
			try { 
				// 使用解析器对象解析request，得到FileItem列表
				List<FileItem> list = fileUpload.parseRequest(request);
				
				// 遍历所有表单项
				for(FileItem fileItem : list) {
					// 如果当前表单项为普通表单项
					if(fileItem.isFormField()) {
						
						// 获取当前表单项的字段名称
						String fieldName = fileItem.getFieldName();
						//System.out.println(fieldName+","+fileItem.getString("UTF-8"));
						String value = fileItem.getString("UTF-8");
						// 如果当前表单项的字段名为username
						if(fieldName.equals("method")){
							method = value;
						}
						else if(fieldName.equals("id")) {
							xunqin.setId(NumberUtils.toInt(value));
						}
						else if(fieldName.equals("logname")) {
							xunqin.setLogname(value);
						}
						else if(fieldName.equals("birthplace")){
							xunqin.setBirthplace(value);
						}
						else if(fieldName.equals("age")){
							xunqin.setAge(NumberUtils.toInt(value));
						}
						else if(fieldName.equals("logtype")){
							xunqin.setLogtype(NumberUtils.toInt(value));
						}
						else if(fieldName.equals("losttype")){
							xunqin.setLosttype(NumberUtils.toInt(value));
						}
						else if(fieldName.equals("losttime")){
							if(value!=null && !"".equals(value)){
								System.out.println(value);
								SimpleDateFormat format = new SimpleDateFormat(Globals.DATE_FORMAT);
								Date date = format.parse(value);
								xunqin.setLosttime(date);
							}
						}
						else if(fieldName.equals("note")){
							xunqin.setNote(value);
						}
						else if(fieldName.equals("contact")){
							xunqin.setContact(value);
						}
						else if(fieldName.equals("mobile")){
							xunqin.setMobile(value);
						}
						else if(fieldName.equals("status")){
							xunqin.setStatus(NumberUtils.toInt(value));
						}
					}  
					else{  //如果当前表单项不是普通表单项，说明就是文件字段
						String name = fileItem.getName();//获取上传文件的名称
						// 如果上传的文件名称为空，即没有指定上传文件
						if(name == null || name.isEmpty()) {
							continue;
						}
						String fileName = new Date().getTime() + ".jpg";
						// 获取真实路径，对应${项目目录}/uploads，当然，这个目录必须存在
						String savepath = request.getServletContext().getRealPath("/uploads");
						System.out.println("savepath:"+savepath);
						if (!new File(savepath).exists()) {//如果上传路径不存在 先创建文件夹
			                new File(savepath).mkdirs();
			            } 
						 String serverFile = savepath + "/" + fileName;
			            if (!new File(serverFile).exists()) {
			                new File(serverFile).createNewFile();
			            }
						
						// 通过uploads目录和文件名称来创建File对象
						File file = new File(serverFile);
						// 把上传文件保存到指定位置
						fileItem.write(file);
						
						xunqin.setAvatarurl("/uploads/" + fileName);
						
						// 打印上传文件的名称
						//response.getWriter().print("上传文件名：" + name + "<br/>");
						// 打印上传文件的大小
						//response.getWriter().print("上传文件大小：" + fileItem.getSize() + "<br/>");
						// 打印上传文件的类型
						//response.getWriter().print("上传文件类型：" + fileItem.getContentType() + "<br/>");
					}
				}
				
				return method;
			} catch (Exception e) {
				throw new ServletException(e);
			} 

		}
}
