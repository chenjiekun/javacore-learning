package com.imooc.socket;

import java.util.Scanner;

 
import com.imooc.util.CommandTransfer;

public class StartClient {
	private  static Scanner in=new Scanner(System.in); 
	private static  SocketClient socketClient=new SocketClient();
	
	public static void main(String[] args) {
	 
		System.out.println("**************欢迎使用imooc文件上传器*************");
		System.out.println("1、登陆");
		System.out.println("2、注册");
		System.out.println("3、退出");
		System.out.println("*********************************************");
	 
		int count=0;
		int temp;
		do{ 
			System.out.println("请选择："); 
			temp=in.nextInt();
			if(1==temp){           			 //如果输入为1，执行登陆程序块
				doLogin(); 
			}else if(2==temp){           //如果输入为2，执行注册程序块
				doRegister();
			}else if(3==temp){
				System.exit(0);
			}else{
				System.out.println("输入有误，请重新输入！");
			}
			count++;
			if(count>=3){
				System.out.println("您登陆的次数已满三次，请下次在登录！");
				System.exit(0);
				break;
			}
		}while(temp!=1||temp!=2||temp!=3);
		
	} 
	//当选择1、登陆时的逻辑
	private static void doLogin(){
		while(true){                 //当登陆成功时退出
			socketClient.login(); 
			CommandTransfer commandTransfer=socketClient.receiveData();   		//调用执行程序返回的结果       
			System.out.println(commandTransfer.getResult());      				//打印出登陆结果！
			System.out.println("*********************************************");
			if(commandTransfer.isFlag()){                                       //如果登陆成功，转到上传文件程序
				 fileUpLoad(); 
			} 
		}  	
	}
	//当选择2、注册时的程序逻辑
	private static void doRegister(){
		while(true){
			socketClient.enroll();
			CommandTransfer commandTransfer=socketClient.receiveData();
			System.out.println(commandTransfer.getResult()); 
			System.out.println("*********************************************");
			if(commandTransfer.isFlag()){                                      //如果注册成功，跳转到登陆页面
				doLogin();
				break;
			}
			
		}
	}
	//上传文件程序，供调用
	private static void  fileUpLoad(){
		CommandTransfer ctLoad=null;
		while(true){          //当文件上传成功后退出
			socketClient.upLoad();
			ctLoad=socketClient.receiveData(); 
			System.out.println(ctLoad.getResult());
			if(ctLoad.isFlag()){
				System.exit(0);
				break;
			} 
		}
		//return ctLoad; 
	}
	
}
