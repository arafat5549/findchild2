package com.xjxy.service;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.xjxy.dao.MessageBoardMapper;
import com.xjxy.dao.UserMapper;
import com.xjxy.model.MessageBoard;
import com.xjxy.model.User;
import com.xjxy.utils.Globals;
import com.xjxy.utils.MyBatisUtil;

public class MessageBoardService {
	
	public MessageBoard findById(int xid){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MessageBoardMapper mapper = sqlSession.getMapper(MessageBoardMapper.class);
			MessageBoard obj =  mapper.selectByPrimaryKey(xid);
	    	return obj;
		} 
		finally {
			sqlSession.close();
		}
	}
	
	public List<MessageBoard> findAll()
	{
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MessageBoardMapper mapper = sqlSession.getMapper(MessageBoardMapper.class);
			UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
			List<MessageBoard> lists= mapper.selectList("SELECT * FROM sys_message_board");
		    for (MessageBoard obj : lists) {
		    	obj.setTypeName(Globals.getMessageType(obj.getType()));
		    	User user = userMapper.selectByPrimaryKey(obj.getUserId());
				obj.setUser(user);
			}
			return lists;
		} finally {
			sqlSession.close();
		}
	}
	
	public boolean save(MessageBoard record){
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MessageBoardMapper mapper = sqlSession.getMapper(MessageBoardMapper.class);
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
	//
	public boolean delete(int id) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MessageBoardMapper mapper = sqlSession.getMapper(MessageBoardMapper.class);
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
	//
	public boolean update(MessageBoard record) {
		SqlSession sqlSession = MyBatisUtil.getSqlSessionFactory().openSession();
		try {
			MessageBoardMapper mapper = sqlSession.getMapper(MessageBoardMapper.class);
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
