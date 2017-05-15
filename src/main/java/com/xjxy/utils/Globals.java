package com.xjxy.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.xjxy.model.User;

/**
 * 全局常量
 * @author wyy
 * 2017年4月18日
 *
 */
public class Globals {
	//session域中用户的key值
	public static final String SESSION_USER = "session_user";
	//全局错误信息的key值
	public static final String ERROR_MESSAGE = "msg";
	//YYYY/MM/dd-hh:mm:ss
	public static final String DATE_FORMAT = "yyyy/MM/dd";
	
	
	public static List<XunqinStatus> findXunqinStatuss(){
		List<XunqinStatus> lists = new ArrayList<XunqinStatus>();
		lists.add(XunqinStatus.XunqinStatus_WAIT_FIND);
		lists.add(XunqinStatus.XunqinStatus_SUCESS);
		return lists;
	}
	public static String getXunqinStatus(int type){
		switch(type){
		    case 1: return XunqinStatus.XunqinStatus_WAIT_FIND.title;
		    case 2: return XunqinStatus.XunqinStatus_SUCESS.title;
		}
		return "";
	}
	/**
	 * 寻亲状态
	 */
	public enum XunqinStatus{
		XunqinStatus_WAIT_FIND("查找中",1),
		XunqinStatus_SUCESS("成功",2);
		
		private String title;
		private int type;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		XunqinStatus(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	
	public static List<NewsType> findNewsTypes(){
		List<NewsType> lists = new ArrayList<NewsType>();
		lists.add(NewsType.NEWS_XUNZI);
		lists.add(NewsType.NEWS_XUNQIN);
		lists.add(NewsType.NEWS_ZHENGCE);
		return lists;
	}
	public static String getNewsType(int type){
		switch(type){
		    case 1: return NewsType.NEWS_XUNZI.title;
		    case 2: return NewsType.NEWS_XUNQIN.title;
		    case 3: return NewsType.NEWS_ZHENGCE.title;
		}
		return "";
	}
	/**
	 * 新闻类型
	 * @author wyy
	 * 2017年4月18日
	 *
	 */
	public enum NewsType{
		NEWS_XUNZI("寻子新闻",1),
		NEWS_XUNQIN("寻亲新闻",2),
		NEWS_ZHENGCE("政策法规",3);
		
		private String title;
		private int type;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		NewsType(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	
	public static List<LogType> findLogTypes(){
		List<LogType> lists = new ArrayList<LogType>();
		lists.add(LogType.LogType_CHILD);
		lists.add(LogType.LogType_PARENT);
		lists.add(LogType.LogType_HOMELESS);
		//lists.add(LogType.LogType_VOLUNTEER);
		lists.add(LogType.LogType_WELFARE_HOUSE);
		lists.add(LogType.LogType_OTHER);
		return lists;
	}
	public static String getLogType(Integer key){
		switch (key) {
		case 1:
			return LogType.LogType_CHILD.getTitle();
		case 2:
			return LogType.LogType_PARENT.getTitle();
		case 3:
			return LogType.LogType_HOMELESS.getTitle();
		case 4:
			return LogType.LogType_VOLUNTEER.getTitle();
		case 5:
			return LogType.LogType_WELFARE_HOUSE.getTitle();
		case 6:
			return LogType.LogType_OTHER.getTitle();
		default:
			break;
		}
		return "";
	}
	/**
	 * 登记类型
	 */
	public enum LogType{
		LogType_CHILD("寻找孩子",1),
		LogType_PARENT("寻找父母",2),
		LogType_HOMELESS("流浪儿童",3),
		LogType_VOLUNTEER("志愿者",4),
		LogType_WELFARE_HOUSE("福利院",5),
		LogType_OTHER("其他寻人",6)
		;
		
		private String title;
		private int type;
		
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}


		LogType(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	
	public static String getLostType(Integer key){
		switch (key) {
		case 1:
			return LostType.LostType_LOST.getTitle();
		case 2:
			return LostType.LostType_KIDNAP.getTitle();
		case 3:
			return LostType.LostType_ABANDON.getTitle();
		case 4:
			return LostType.LostType_UNKOWN.getTitle();
		default:
			break;
		}
		return "";
	}
	public static List<LostType> findLostTypes(){
		List<LostType> lists = new ArrayList<LostType>();
		lists.add(LostType.LostType_LOST);
		lists.add(LostType.LostType_KIDNAP);
		lists.add(LostType.LostType_ABANDON);
		lists.add(LostType.LostType_UNKOWN);
		return lists;
	}
	/**
	 * 走失类型
	 */
	public enum LostType{
		LostType_LOST("走失",1),
		LostType_KIDNAP("诱拐",2),
		LostType_ABANDON("遗弃",3),
		LostType_UNKOWN("情况不名",4)
		;
		
		private String title;
		private int type;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		LostType(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	
	
	public static List<MessageType> findMessageTypes(){
		List<MessageType> lists = new ArrayList<MessageType>();
		lists.add(MessageType.MessageType_ADVICE);
		lists.add(MessageType.MessageType_BUG);
		lists.add(MessageType.MessageType_OTHER);
		return lists;
	}
	public static String getMessageType(Integer key){
		switch (key) {
		case 1:
			return MessageType.MessageType_ADVICE.getTitle();
		case 2:
			return MessageType.MessageType_BUG.getTitle();
		case 3:
			return MessageType.MessageType_OTHER.getTitle();
		default:
			break;
		}
		return "";
	}
	/**
	 * 留言类型
	 */
	public enum MessageType{
		MessageType_ADVICE("意见建议",1),
		MessageType_BUG("bug修复",2),
		MessageType_OTHER("其他",3)
		;
		
		private String title;
		private int type;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		MessageType(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	public static List<UserType> findLoginUserTypes(){
		List<UserType> lists = new ArrayList<UserType>();
		lists.add(UserType.UserType_NORMAL);
		lists.add(UserType.UserType_ADMIN);
		return lists;
	}
	//
	public static List<UserType> findUserTypes(){
		List<UserType> lists = new ArrayList<UserType>();
		lists.add(UserType.UserType_NORMAL);
		lists.add(UserType.UserType_ADMIN);
		lists.add(UserType.UserType_SUPERADMIN);
		return lists;
	}
	public static String getUserType(Integer key){
		switch (key) {
		case 1:
			return UserType.UserType_NORMAL.getTitle();
		case 2:
			return UserType.UserType_ADMIN.getTitle();
		case 3:
			return UserType.UserType_SUPERADMIN.getTitle();
		default:
			break;
		}
		return "";
	}
	/**
	 * 用户类型
	 */
	public enum UserType{
		UserType_NORMAL("普通用户",1),
		UserType_ADMIN("普通管理员",2),
		UserType_SUPERADMIN("超级管理员",3)
		;
		
		private String title;
		private int type;
		
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		UserType(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	//
	public static List<UserStatus> findUserStatuss(){
		List<UserStatus> lists = new ArrayList<UserStatus>();
		lists.add(UserStatus.UserStatus_NORMAL);
		lists.add(UserStatus.UserStatus_DISABLED);
		return lists;
	}
	public static String getUserStatus(Integer key){
		switch (key) {
		case 0:
			return UserStatus.UserStatus_NORMAL.getTitle();
		case 1:
			return UserStatus.UserStatus_DISABLED.getTitle();
		default:
			break;
		}
		return "";
	}
	/**
	 * 用户状态
	 */
	public enum UserStatus{
		UserStatus_NORMAL("激活",0),
		UserStatus_DISABLED("冻结",1);
		
		private String title;
		private int type;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		UserStatus(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	
	/***
	 * 籍贯
	 */
	public static List<BirthPlace> findBirthPlaces(){
		List<BirthPlace> lists = new ArrayList<BirthPlace>();
		lists.add(BirthPlace.BirthPlace_FUJIAN);
		lists.add(BirthPlace.BirthPlace_GUANGZHOU);
		lists.add(BirthPlace.BirthPlace_BEIJING);
		return lists;
	}
	
	public enum BirthPlace{
		BirthPlace_FUJIAN("福建",0),
		BirthPlace_GUANGZHOU("广州",1),
		BirthPlace_BEIJING("北京",2);
		
		private String title;
		private int type;
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		public int getType() {
			return type;
		}
		public void setType(int type) {
			this.type = type;
		}
		BirthPlace(String title,int type){
			this.type = type;
			this.title = title;
		}
	}
	//参数验证
	public static boolean validate(String msg,String regex){
		if(StringUtils.isEmpty(msg) || StringUtils.isEmpty(regex)) return false;
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(msg);
		return m.matches();
	}
	
	public static User getLoginUser(HttpServletRequest req){
		return (User)req.getSession().getAttribute(SESSION_USER);
	}
	//检测用户登录
	public static boolean CheckLoginUser(HttpServletRequest req){
		User user =  (User)req.getSession().getAttribute(SESSION_USER);
		return user != null;
	}
	//检测管理员登录
	public static boolean CheckLoginAdmin(HttpServletRequest req){
		User user =  (User)req.getSession().getAttribute(SESSION_USER);
		return user != null && user.getType().intValue() >= UserType.UserType_ADMIN.getType();
	}
	/**
	 * 检测Admin#操作用户实体权限 - 简单规则：高级可以操作低级
	 */
	public static boolean CheckPrivilegeOnUser(User admin,User target){
		if(admin!=null && target!=null){
			return admin.getType().intValue() >= UserType.UserType_ADMIN.getType() &&
					admin.getType().intValue() >= target.getType().intValue();
		}
		return false;
	}
	
}
