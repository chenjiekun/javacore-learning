package com.imooc.service;
 

import com.imooc.dao.UserDao;
import com.imooc.entiy.User;

public class UserService {
	private UserDao userDao=new UserDao();
	
	//注册
	public int regi(User user){
		return userDao.register(user);
	}
	//登陆校验 
	public boolean vetifyUser(User user){
		String pw =userDao.validate(user.getUserName());
		if(user.getPassword().equals(pw)){  //可能为null的pw放equals（里面）
			return true;                   //数据库中有该条数据，且用户输入的密码用户名校验正确
		}else{
			return false;
		}
	}
}
