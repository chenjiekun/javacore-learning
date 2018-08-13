package com.imooc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.imooc.entiy.User;
import com.imooc.util.DBUtil;

public class UserDao {
	private static String sql1="insert into users values(null,?,?)";
	private static String sql2="select password from users where username=?";
	private static Connection conn=null;
	private static PreparedStatement call=null;
	
	//1、注册
	public int register(User user){ 
		int line=0;
		try {
			conn=DBUtil.getconnection();
			call=conn.prepareStatement(sql1);
			//写入sql语句的参数
			call.setString(1, user.getUserName());
			call.setString(2, user.getPassword());
			//执行调用
			line= call.executeUpdate();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			DBUtil.release(conn, call, null);
		}
		return line;
	}
	
	//2、登录校验
	public String validate(String username){
		String password=null;
		ResultSet rs=null;
		try {
			conn=DBUtil.getconnection();
			call=conn.prepareStatement(sql2);
			//向sql2中传参数
			call.setString(1, username);
			 
			//执行查询
			rs=call.executeQuery();
			
			//取出返回值
			 while(rs.next()){
				 password =rs.getString("password");
			 } 
			System.out.println("输入查询的用户名："+username+"\t"+"从数据库中查询到的密码:"+password);
		} catch (Exception e) {
			e.printStackTrace();	
		}finally{
			DBUtil.release(conn, call, rs);
		} 
		return password;
	}
}
