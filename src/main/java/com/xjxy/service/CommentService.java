package com.xjxy.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.CommentMapper;
import com.xjxy.dao.UserMapper;
import com.xjxy.model.Comment;
import com.xjxy.model.User;
import com.xjxy.utils.MyBatisUtil;



public class CommentService {
	
	public List<Comment> findCommentsByXId(int xid){
		if(xid<=0){
			return new ArrayList<Comment>();
		}
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
			List<Comment> lists= mapper.selectList("SELECT * FROM sys_comment WHERE xunqin_id="+xid);
			
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			for (Comment comment : lists) {
		    	User user = userMapper.selectByPrimaryKey(comment.getUserId());
		    	comment.setUser(user);
			}
		    return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public Comment findById(int id){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
			Comment obj =  mapper.selectByPrimaryKey(id);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public List<Comment> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
			List<Comment> lists= mapper.selectList("SELECT * FROM sys_comment");
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			for (Comment comment : lists) {
		    	User user = userMapper.selectByPrimaryKey(comment.getUserId());
		    	comment.setUser(user);
			}
		    return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	
	//
	public boolean save(Comment record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
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
			CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
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
	
	public boolean update(Comment record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			CommentMapper mapper = sqlSession.getMapper(CommentMapper.class);
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
