package com.imooc.entiy;

import java.io.Serializable;

public class User implements Serializable{

	/**
	 * 自动生成的serialVersionUID 作用是保持版本的兼容性，即在版本升级时反序列化仍然保持对象的唯一性。
	 */
	private static final long serialVersionUID = -4359765262784260271L;
	
	private String id;
	private String userName;
	private String password;
	
	public User(){
		
	}
	public User(String id, String userName, String password) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
