package com.u4.home.command;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import android.util.Log;


public class CommandSender  {
	public void sendVideoChatCommand(final TCPMessage msg){
		Runnable runnable=new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				String strMsg="{ \"type\": \""+msg.getType()+"\", \"content\": \""+msg.getContent()+"\", \"ip\": \""+msg.getIp()+"\", \"time\": \""+msg.getTime()+"\" }";
				//创建客户端的socket服务。指定目的主机和端口
				Socket s;
				try {
					s = new Socket(msg.getIp(),7000);
					//为了发送数据，应该获取socket流中的输出流。
					OutputStream out=s.getOutputStream();
					out.write(strMsg.getBytes());
					s.close();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				Log.i("Call Message Put", strMsg);
				//{ "type": "1", "content": "视频通话", "ip": "192.168.1.90", "time": "195522552" }
			}
		};
		Thread thread = new Thread(runnable);
		thread.start();
	}
}
