package com.xjxy.utils;

/**
 * 业务异常基类，所有业务异常都必须继承于此异常 定义异常时，需要先确定异常所属模块。 例如：无效用户可以定义为 [10010001]
 * 前四位数为系统模块编号，后4位为错误代码 ,唯一。
 * 
 * @author wang
 *
 */
public enum ResultEnum {

	// 数据库想操作异常
	DB_INSERT_RESULT_ERROR(99990001, "数据库插入异常"),
	DB_UPDATE_RESULT_ERROR(99990002, "数据库更新异常"),
	DB_SELECTONE_IS_NULL(99990003,"数据库查询异常"),
	DB_DELETE_RESULT_ERROR(99990004, "数据库异常删除"),

	// 系统异常
	INNER_ERROR(99980001, "系统错误"), 
	TOKEN_IS_ILLICIT(99980002, "Token验证非法"), 
	SESSION_IS_OUT_TIME(99980003, "会话超时"),

	// 用户相关异常
	INVALID_USER(10000001, "无效用户"),
	// 公告相关异常
	INVALID_ANNOUNCE(10010001, "无效公告"),
	// 网站链接相关异常
	INVALID_LINK(10020001, "无效网站链接"),
	// 解决方案相关异常
	INVALID_SOLUTION(10030001, "无效解决方案"),
	// 新闻相关异常
	INVALID_NEWS(10040001, "无效新闻"),
	// 留言板相关异常
	INVALID_MESSAGEBOARD(10050001, "无效留言板"),
	// 评论相关异常
	INVALID_COMMENT(10050001, "无效评论"),
	
	//权限相关
	PEMISSION_DENIED(88880001,"权限不足无法操作");
	;
   
	@Override
	public String toString(){
		return msg;
	}
	private int state;

	private String msg;
 
	ResultEnum(int state, String msg) {
		this.state = state;
		this.msg = msg;
	}

	public int getState() {
		return state;
	}

	public String getMsg() {
		return msg;
	}

	public static ResultEnum stateOf(int index) {
		for (ResultEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
    
	
}
