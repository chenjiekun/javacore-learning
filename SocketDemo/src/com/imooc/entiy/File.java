package com.imooc.entiy;

import java.io.Serializable;

public class File implements Serializable{


	/**
	 * 自动生成的serialVersionUID 作用是保持版本的兼容性，即在版本升级时反序列化仍然保持对象的唯一性。
	 */
	private static final long serialVersionUID = -2979690031671728894L;
	
	private int fid;
	private String fname;
	private byte[] fcontent;
	
	public File(){
		
	}

	public File(int fid, String fname, byte[] fcontent) {
		super();
		this.fid = fid;
		this.fname = fname;
		this.fcontent = fcontent;
	}

	public int getFid() {
		return fid;
	}

	public void setFid(int fid) {
		this.fid = fid;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public byte[] getFcontent() {
		return fcontent;
	}

	public void setFcontent(byte[] fcontent) {
		this.fcontent = fcontent;
	}
	
	
}
