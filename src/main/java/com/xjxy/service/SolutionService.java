package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.SolutionMapper;
import com.xjxy.dao.UserMapper;
import com.xjxy.model.Solution;
import com.xjxy.model.User;
import com.xjxy.utils.Globals;
import com.xjxy.utils.MyBatisUtil;

public class SolutionService {

	public Solution findById(int id){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			SolutionMapper mapper = sqlSession.getMapper(SolutionMapper.class);
			Solution obj =  mapper.selectByPrimaryKey(id);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public List<Solution> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			SolutionMapper mapper = sqlSession.getMapper(SolutionMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<Solution> lists= mapper.selectList("SELECT * FROM sys_solution");
			for (Solution obj : lists) { 
				obj.setLogtypeName(Globals.getLogType(obj.getLogtype()));
				User user = userMapper.selectByPrimaryKey(obj.getUserId());
				obj.setUser(user);
			}
		    return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean save(Solution record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			SolutionMapper mapper = sqlSession.getMapper(SolutionMapper.class);
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
			SolutionMapper mapper = sqlSession.getMapper(SolutionMapper.class);
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
	
	public boolean update(Solution record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			SolutionMapper mapper = sqlSession.getMapper(SolutionMapper.class);
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
