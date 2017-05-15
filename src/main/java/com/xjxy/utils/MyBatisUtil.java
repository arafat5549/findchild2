package com.xjxy.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * Mybatis ORM框架连接数据库
 * 配置文件为 mybatis-config.xml
 * jdbc配置文件为jdbc.properties
 * 
 * @author wang
 *
 */
public class MyBatisUtil
{
	private static final String DEFAULT_MYBATIS_CONFIG_FILE="mybatis-config.xml";
	private static SqlSessionFactory sqlSessionFactory;
	
	private static final Properties PROPERTIES = new Properties();
	
	static
	{
		try {
			InputStream is = DataSourceFactory.class.getResourceAsStream("/jdbc.properties");
			PROPERTIES.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	//封装后的Mybatis连接
	public static SqlSessionFactory getSqlSessionFactory()
	{
		if(sqlSessionFactory==null){
			//org.apache.ibatis.logging.LogFactory.useLog4JLogging();
			try
			{
				InputStream inputStream = Resources.getResourceAsStream(DEFAULT_MYBATIS_CONFIG_FILE);
				sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			} catch (IOException e)
			{
				throw new RuntimeException(e.getCause());
			}
		}
		return sqlSessionFactory;
	}
	
	//原始JDBC连接
	public static Connection getConnection() 
	{
		String driver = PROPERTIES.getProperty("jdbc.driverClassName");
		String url = PROPERTIES.getProperty("jdbc.url");
		String username = PROPERTIES.getProperty("jdbc.username");
		String password = PROPERTIES.getProperty("jdbc.password");
		Connection connection = null;
		try {
			Class.forName(driver);
			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		} 
		return connection;
	}
		
}
