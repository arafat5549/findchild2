package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.UserMapper;
import com.xjxy.model.User;
import com.xjxy.utils.Globals;
import com.xjxy.utils.Globals.UserType;
import com.xjxy.utils.MyBatisUtil;

/**
 * 
 * @author wyy
 * 2017年4月17日
 *
 */
public class UserService {
	
	public User findById(int id){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			User obj =  mapper.selectByPrimaryKey(id);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public List<User> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		//System.out.println(sqlSession);
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			List<User>  lists = mapper.selectList("SELECT * FROM sys_user");
			for (User user : lists) {
				user.setTypeName(Globals.getUserType(user.getType()));
				user.setStatusName(Globals.getUserStatus(user.getStatus()));
			}
		    return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public int save(User record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			record.setCreateTime(new Date());
			int f = mapper.insertSelective(record);
			sqlSession.commit();
			sqlSession.clearCache();
			return f;
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}
	
	public boolean delete(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int count = mapper.deleteByPrimaryKey(id);
			sqlSession.commit();
			return count > 0;
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}
	
	public boolean update(User user) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			int count = mapper.updateByPrimaryKeySelective(user);
			sqlSession.commit();
			return count > 0;
		} 
		catch (Exception e) {
			sqlSession.rollback();
			e.printStackTrace();
			throw new RuntimeException(e.getCause());
		}
		finally {
			sqlSession.close();
		}
	}
    //------------------------------------------------------
	public User findByName(String username) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			UserMapper mapper = sqlSession.getMapper(UserMapper.class);
			return (User)mapper.selectUser("SELECT * FROM sys_user WHERE username='"+username+"'");
		} finally {
			sqlSession.close();
		}
	}
	
	
	
	public String loginAdmin(User user){
		User exist = findByName(user.getUsername());
		if(exist==null){
			return "该用户不存在";
		}
		if(exist.getType() < UserType.UserType_ADMIN.getType()){
			return "不是管理员无法登录";
		}
		if(exist.getStatus() == Globals.UserStatus.UserStatus_DISABLED.getType()){
			return "管理员已冻结无法登陆";
		}
		if(!exist.getPassword().equals(user.getPassword())){
			return "密码错误";
		}
		return "";
	}
	
	
	public String login(User user) {
		User exist = findByName(user.getUsername());
		if(exist==null){
			return "该用户不存在";
		}
		if(exist.getStatus() == Globals.UserStatus.UserStatus_DISABLED.getType()){
			return "用户已冻结无法登陆";
		}
		if(!exist.getPassword().equals(user.getPassword())){
			return "密码错误";
		}
		return "";
	}

	public String register(User user) {
		String username = user.getUsername();
    	String password = user.getPassword(); 
    	//0.参数的判定
    	//用户名必须为3-6位的中文字母数字下划线
    	String regex ="[\u4e00-\u9fa5a-zA-Z0-9_]{3,6}";
    	boolean f = Globals.validate(username, regex);
    	if(!f){
    		return "用户名必须为3-6位的中文字母数字下划线";
    	}
    	//密码长度为6-12位 以字母数字开头，并且不能带中文
    	regex ="[a-zA-Z0-9][^\u4e00-\u9fa5]{5,11}";
    	f = Globals.validate(password, regex);
    	if(!f){
    		return "密码长度为6-12位 以字母数字开头，并且不能带中文";
    	}
    	//1.判断有没有这个User对象
    	User exist = findByName(username);
    	if(exist!=null){
    		return "用户名已存在";
    	} 
    	//数据入库
    	f = save(user) > 0;
    	if(!f){
    		return "插入数据库错误";
    	}
    	return "";
	}
}
