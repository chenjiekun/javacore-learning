package com.imooc.dao;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;

import com.imooc.entiy.File;
import com.imooc.util.DBUtil;

public class FileDao {
	//将文件保存到数据库
	private String sql="insert into photo values(null,?,?)";
	private static  Connection conn;
	private static PreparedStatement call;
	
	public int update(File file){
			int line=0;
		try {
			conn=DBUtil.getconnection();
			call=conn.prepareStatement(sql); 
			
			//写入sql语句的参数
			call.setString(1, file.getFname());
			//将字节数组转化成输入流，供给setBlob;
			InputStream is=new ByteArrayInputStream(file.getFcontent());
			call.setBlob(2, is);
			 
			//执行调用
			line=call.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			DBUtil.release(conn, call, null);
		}
	
		return line;
	}		
		
		
}
