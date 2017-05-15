package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.AnnounceMapper;
import com.xjxy.dao.UserMapper;
import com.xjxy.model.Announce;
import com.xjxy.model.User;
import com.xjxy.utils.MyBatisUtil;

/**
 * 业务层
 * 调用DAO的方法
 * @author wyy
 * 2017年4月19日
 *
 */
public class AnnounceService {
	
	public Announce findById(int id){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AnnounceMapper mapper = sqlSession.getMapper(AnnounceMapper.class);
			Announce obj =  mapper.selectByPrimaryKey(id);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public List<Announce> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AnnounceMapper mapper = sqlSession.getMapper(AnnounceMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Announce> lists= mapper.selectList("SELECT * FROM sys_announce");
		    for (Announce announce : lists) {
		    	User user = userMapper.selectByPrimaryKey(announce.getUserId());
		    	announce.setUser(user); 
			}
			return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean save(Announce record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AnnounceMapper mapper = sqlSession.getMapper(AnnounceMapper.class);
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
			AnnounceMapper mapper = sqlSession.getMapper(AnnounceMapper.class);
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
	
	public boolean update(Announce record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			AnnounceMapper mapper = sqlSession.getMapper(AnnounceMapper.class);
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
