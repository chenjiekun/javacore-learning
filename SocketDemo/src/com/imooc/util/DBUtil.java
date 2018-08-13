package com.imooc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtil {
	private static String driver="com.mysql.jdbc.Driver";
	private static String  url="jdbc:mysql://127.0.0.1:3306/register?useUnicode=true&characterEncoding=utf8";
	private static String user="root";
	private static String password="123456";
	
	//注册数据库驱动
	static{
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	// 获取数据库连接
	public static Connection getconnection(){
		try {
			return DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	//释放数据库资源
	public static void release(Connection conn,Statement st,ResultSet rs){
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(st!=null){
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
}
