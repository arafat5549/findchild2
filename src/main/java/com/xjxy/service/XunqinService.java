package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.XunqinMapper;
import com.xjxy.dao.UserMapper;
import com.xjxy.dao.XunqinMapper;
import com.xjxy.model.Xunqin;
import com.xjxy.model.User;
import com.xjxy.model.Xunqin;
import com.xjxy.utils.Globals;
import com.xjxy.utils.MyBatisUtil;



public class XunqinService {
	
	public List<Xunqin> findByUserId(int userid){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			XunqinMapper mapper = sqlSession.getMapper(XunqinMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Xunqin> lists= mapper.selectList("SELECT * FROM sys_xunqin WHERE user_id="+userid);
		    for (Xunqin xunqin : lists) {
		    	xunqin.setLogtypeName(Globals.getLogType(xunqin.getLogtype()));
		    	xunqin.setLosttypeName(Globals.getLostType(xunqin.getLosttype()));
		    	xunqin.setStatusName(Globals.getXunqinStatus(xunqin.getStatus()));
		    	
		    	User user = userMapper.selectByPrimaryKey(xunqin.getUserId());
		    	xunqin.setUser(user); 
			}
	    	return lists;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public Xunqin findById(int xid){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			XunqinMapper mapper = sqlSession.getMapper(XunqinMapper.class);
			Xunqin xunqin =  mapper.selectByPrimaryKey(xid);
			xunqin.setLogtypeName(Globals.getLogType(xunqin.getLogtype()));
	    	xunqin.setLosttypeName(Globals.getLostType(xunqin.getLosttype()));
	    	xunqin.setStatusName(Globals.getXunqinStatus(xunqin.getStatus()));
	    	return xunqin;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	
	
	public List<Xunqin> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			XunqinMapper mapper = sqlSession.getMapper(XunqinMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Xunqin> lists= mapper.selectList("SELECT * FROM sys_xunqin");
		    for (Xunqin xunqin : lists) {
		    	xunqin.setLogtypeName(Globals.getLogType(xunqin.getLogtype()));
		    	xunqin.setLosttypeName(Globals.getLostType(xunqin.getLosttype()));
		    	xunqin.setStatusName(Globals.getXunqinStatus(xunqin.getStatus()));
		    	
		    	User user = userMapper.selectByPrimaryKey(xunqin.getUserId());
		    	xunqin.setUser(user); 
			}
		    return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean save(Xunqin record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			XunqinMapper mapper = sqlSession.getMapper(XunqinMapper.class);
			record.setCreateTime(new Date());
			int f = mapper.insertSelective(record);
			sqlSession.commit();
			sqlSession.clearCache();
			return f > 0;
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
			XunqinMapper mapper = sqlSession.getMapper(XunqinMapper.class);
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
	
	public boolean update(Xunqin record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			XunqinMapper mapper = sqlSession.getMapper(XunqinMapper.class);
			int count = mapper.updateByPrimaryKeySelective(record);
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
	
}
