package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.NewsMapper;
import com.xjxy.dao.NewsMapper;
import com.xjxy.dao.UserMapper;
import com.xjxy.model.News;
import com.xjxy.model.News;
import com.xjxy.model.User;
import com.xjxy.utils.Globals;
import com.xjxy.utils.MyBatisUtil;

public class NewsService {

	public List<News> findNewsByType(int type)
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
			return mapper.selectList("SELECT * FROM sys_news WHERE type="+type);
		} finally {
			sqlSession.close();
		}
	}
	
	public News findById(int id){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
			News obj =  mapper.selectByPrimaryKey(id);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public List<News> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<News> lists= mapper.selectList("SELECT * FROM sys_news");
		    for (News news : lists) {
		    	news.setTypeName(Globals.getNewsType(news.getType()));
		    	User user = userMapper.selectByPrimaryKey(news.getUserId());
		    	news.setUser(user); 
			}
			return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean save(News record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
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
			NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
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
	
	public boolean update(News record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			NewsMapper mapper = sqlSession.getMapper(NewsMapper.class);
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
