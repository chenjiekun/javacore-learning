package com.imooc.socket;
 
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream; 
import java.net.Socket;
import java.util.Scanner;

import com.imooc.entiy.File;
import com.imooc.entiy.User;
import com.imooc.util.CommandTransfer;

public class SocketClient {
	private  static Scanner in=new Scanner(System.in); 
	private static CommandTransfer commandTransfer;
	private static Socket socket=null;
	//与服务器端通讯程序块,发送对象
	private static void sendData(CommandTransfer commandTransfer){
		try {
			//1.创建客户端Socket，指定服务器地址和端口
			socket=new Socket("localhost", 8089);
			//2.获取输出流，向服务器端发送信息
			ObjectOutputStream oos=new ObjectOutputStream(socket.getOutputStream()); 
			//打印向客户端通信的对象
			//System.out.println(commandTransfer);
			//3、将 对象传递给服务端 
			oos.writeObject(commandTransfer); 
			oos.flush();						 
			socket.shutdownOutput();			//上面打开过字节输出流
			        						 
		} catch (Exception e) {
			e.printStackTrace();	
		}
	}
	//与服务器端通信程序块，接收对象
	public  CommandTransfer receiveData(){
		ObjectInputStream ois=null;
		CommandTransfer commandTransfer=null;
		try {
			//socket=new Socket("localhost", 8123);
			ois=new ObjectInputStream(socket.getInputStream());
			commandTransfer=(CommandTransfer)ois.readObject();
			
			ois.close(); 
		} catch (Exception e) {
			e.printStackTrace(); 
		}   
		return commandTransfer;
	} 
	
	//用户登陆程序块
	public void login(){
		User user=new User(); 
		System.out.println("请输入用户名：");
		user.setUserName(in.nextLine());  
		System.out.println("请输入密码：");
		user.setPassword(in.nextLine());
		commandTransfer=new CommandTransfer();
		commandTransfer.setData(user);
		commandTransfer.setCmd("login");
		sendData(commandTransfer);                     //将输入的user传给服务器校验
	} 
	
	//用户注册程序块
	public void enroll(){
		User user=new User(); 
		while(true){
			System.out.println("请输入用户名：");
			user.setUserName(in.nextLine()); 
			System.out.println("请输入密码：");
			user.setPassword(in.nextLine());
			
			System.out.println("请再次确认密码：");
			String temp=in.nextLine(); 
			if(user.getPassword().equals(temp)){
				commandTransfer=new CommandTransfer();	 
				commandTransfer.setData(user);
				commandTransfer.setCmd("enroll");
				sendData(commandTransfer); 
				break;
			}else{
				System.out.println("两次输入的密码不一致！");
			}
		}
		
	} 
	
	//文件传递程序块   TODO
	public void upLoad(){  
		while(true){
			
			System.out.println("请输入上传文件的绝对路径（例如：e:/dog.jpg）");
			File file=new File();           //new 一个File对象
			String path=in.nextLine();       //读取本地文件路径
			
			InputStream is=null;
			BufferedInputStream bis=null; 
			
			try {
				is=new FileInputStream(path);         
				bis=new BufferedInputStream(is);   //创建一个内部缓冲区数组并将其存储在 bis 中。    
				
				byte[] 	buffer=new byte[is.available()];  //可以不受阻塞地从此输入流读取（或跳过）的估计字节数,
															//即建立is.available()大的一个字节缓冲区
				bis.read(buffer);                    	  //读取buferr字节数组大小的字节进来，存在buffer中	
				String fname= path.substring(path.lastIndexOf("/")+1);
				file.setFname(fname);                   //获取文件名
				file.setFcontent(buffer);                //保存文件内容
				
				//将文件对象包装到commandTransfer中
				commandTransfer=new CommandTransfer();	 
				commandTransfer.setData(file);
				commandTransfer.setCmd("upLoad");
				sendData(commandTransfer); 
				break;
				
			} catch (FileNotFoundException e) {
				System.out.println("文件不存在！");
				continue;
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
					try {
						if(is!=null) 
							is.close();
						if(bis!=null)
							bis.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
			}
		}
		 
		
		
	}
	

	
		 
}
