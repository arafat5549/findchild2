package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.AnnounceMapper;
import com.xjxy.dao.LinkMapper;
import com.xjxy.model.Announce;
import com.xjxy.model.Link;
import com.xjxy.utils.MyBatisUtil;

public class LinkService {
	
	public Link findById(int id){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			LinkMapper mapper = sqlSession.getMapper(LinkMapper.class);
			Link obj =  mapper.selectByPrimaryKey(id);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	
	public List<Link> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			LinkMapper mapper = sqlSession.getMapper(LinkMapper.class);
			List<Link> lists= mapper.selectList("SELECT * FROM sys_link");
		    return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean save(Link record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			LinkMapper mapper = sqlSession.getMapper(LinkMapper.class);
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
			LinkMapper mapper = sqlSession.getMapper(LinkMapper.class);
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
	
	public boolean update(Link record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			LinkMapper mapper = sqlSession.getMapper(LinkMapper.class);
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
