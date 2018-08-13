package com.imooc.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StartServer {
	public static void main(String[] args) {
		try {
			//1、创建一个服务器端socket 即ServerSocket,指定绑定的端口
			ServerSocket serverSocket=new ServerSocket(8089);
			Socket socket=null;
			//2、记录客户端的数量
			int count=0;
			System.out.println("***服务器即将启动，等待客户端的连接***");
			//循环监听等待客户端的连接
			while(true){
				//3、调用accept方法开始监听，等待客户端的连接
				socket=serverSocket.accept();
				//4、创建一个新的线程
				ServerThread serverThread =new ServerThread(socket);
				//启动线程
				serverThread.start();
				
				count++;//统计客户端的数量
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
