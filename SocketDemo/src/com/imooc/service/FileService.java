package com.imooc.service;

import com.imooc.dao.FileDao;
import com.imooc.entiy.File;

public class FileService {
	private FileDao fileDao=new FileDao();
	//上传文件
	public int upLoad(File file){ 
		return fileDao.update(file);
	}
	
	
}
