package com.imooc.socket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.imooc.entiy.File;
import com.imooc.entiy.User;
import com.imooc.service.FileService;
import com.imooc.service.UserService;
import com.imooc.util.CommandTransfer;

/*
	 * 服务器线程处理类
	 */
public class ServerThread extends Thread{
	private UserService userService=new UserService();
	private FileService fileService =new FileService(); 
	private Socket socket=null;
	public ServerThread(Socket socket){
		this.socket=socket;
	}
	//线程执行的操作，响应客户端的请求
	@Override
	public void run(){ 
		ObjectInputStream ois=null;
		ObjectOutputStream oos=null; 
		
		try {
			 ois=new ObjectInputStream(socket.getInputStream());
			//接收客户端发送的信息
			CommandTransfer commandTransfer=(CommandTransfer)ois.readObject(); 
			//System.out.println(commandTransfer);
			socket.shutdownInput();//关闭输入流
			
			// 用接收到的commandTransfer对象调用excute方法执行
			  commandTransfer=  excute(commandTransfer);
			//将返回的对象返回给客户端
			 oos=new ObjectOutputStream(socket.getOutputStream());
			 oos.writeObject(commandTransfer); 
			 
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally{                   //关闭资源
			 try {
				if(ois!=null)
					ois.close(); 
				if(oos!=null)
					oos.close();
				if(socket!=null)
					socket.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			} 
		} 
		
	}
	private   CommandTransfer excute(CommandTransfer commandTransfer){
		String cmd=commandTransfer.getCmd();
		if("login".equals(cmd)){
			//向服务端打印客户登陆信息 
			System.out.println("登陆用户："+((User)commandTransfer.getData()).getUserName()+"密码："+
					((User)commandTransfer.getData()).getPassword());
			 //调用userService验证用户密码是否正确
			 boolean vetifyPassword=userService.vetifyUser((User)commandTransfer.getData());
			 if(vetifyPassword){                         
				 commandTransfer.setFlag(true);
				 commandTransfer.setResult("登陆成功！");
			 }else{
				 commandTransfer.setFlag(false);
				 commandTransfer.setResult("登陆失败~！");
			 }
			
		 }else if("enroll".equals(cmd)){
			//向服务端打印用户注册信息 
			System.out.println("注册用户："+((User)commandTransfer.getData()).getUserName()+"密码："+
						((User)commandTransfer.getData()).getPassword());
			 //调用userService注册用户信息
			 int line=userService.regi((User)commandTransfer.getData());
			 if(line==1){
				 commandTransfer.setFlag(true);
				 commandTransfer.setResult("注册成功,请登录~！");
			 }else{
				 commandTransfer.setFlag(false);
				 commandTransfer.setResult("注册失败~！");
			 }
			 
		 }else if("upLoad".equals(cmd)){
			 int line =fileService.upLoad((File)commandTransfer.getData());
			 if(line>0){
				 commandTransfer.setFlag(true);
				 commandTransfer.setResult("文件上传成功，再见！");
			 }else{
				 commandTransfer.setFlag(false);
				 commandTransfer.setResult("文件上传失败！");
			 }
			 
		 }
		 //将登陆信息返回给客户端
		 return commandTransfer; 
	}
	
	
	
}
