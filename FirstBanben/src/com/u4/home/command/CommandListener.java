package com.u4.home.command;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.alibaba.fastjson.JSONException;


public class CommandListener extends TCPListener{
	
//	public void createServer() throws IOException{
//		// 建立服务端socket服务，并监听一个端口
//		 ServerSocket ss=new ServerSocket(7000);
//		// 通过accept方法获取连接过来的客户端对象
//		 Socket accept = ss.accept();
//		 //获取客户端发送过来的数据，那么要使用客户端对象的读取流来读取数据
//		 InputStream is = accept.getInputStream();
//		 byte[] bytes=new byte[1024];
//		 is.read(bytes);
//		 String json=new String(bytes,0,bytes.length);
//		 try {
//			org.json.JSONArray array=new org.json.JSONArray(json);
//			String type=array.getString(0);
//			String content=array.getString(1);
//			String ip=array.getString(2);
//			String time=array.getString(3);
//			TCPMessage msg=new TCPMessage();
//			msg.setContent(content);
//			msg.setIp(ip);
//			msg.setTime(time);
//			msg.setType(type);
//			if("视频通话".equals(msg.getContent())){
//				Intent intent=new Intent();
//			}
//		} catch (JSONException e) {
//			e.printStackTrace();
//		}
//
//		 accept.close();
//	}
	public static final int THREAD_COUNT=1;//线程数
	
	private int port=7000;
	//用来加载图片
	private ExecutorService executors=Executors.newFixedThreadPool(THREAD_COUNT);
	
	private onReceiveCommand receiveCommand;
	
	boolean isReceived;//刚进来默认是正在接收数据的
	
	private static CommandListener instance;
	
	private CommandListener(){}
	
	public static CommandListener getInstance(){
		return instance==null?instance=new CommandListener():instance;
	}
	
	@Override
	void init() {
		setPort(port);
	}
	
	public void onReceiveData(final Socket socket) throws IOException{
		connectionReceive(socket);
	}
	
	private void connectionReceive(final Socket socket){
		executors.execute(new Runnable() {
			@Override
			public void run() {
				try {
					System.out.println("lllj使用客户端对象的读取流来读取数据");
					 //获取客户端发送过来的数据，那么要使用客户端对象的读取流来读取数据
					 InputStream is = socket.getInputStream();
					 byte[] bytes=new byte[1024];
					 int len=0;
					 String json="";
					 while((len=is.read(bytes))!=-1){
						   json=new String(bytes,0,len);
					 }
					 System.out.println(json);
					 try {
						 
						org.json.JSONObject array=new org.json.JSONObject(json);
						String type=array.getString("type");
						String content=array.getString("content");
						String ip=array.getString("ip");
						String time=array.getString("time");
						TCPMessage msg=new TCPMessage();
						msg.setContent(content);
						msg.setIp(ip);
						msg.setTime(time);
						msg.setType(type);
						receiveCommand.onSetReceiveCommand(msg);
						
					} catch (JSONException e) {
						e.printStackTrace();
					} catch (org.json.JSONException e) {
						e.printStackTrace();
					}
//					Bitmap bitmap = BitmapFactory.decodeStream(socket.getInputStream());
//					receiveCommand.onReceiveCommand(bitmap);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	public onReceiveCommand getReceiveCommand() {
		return receiveCommand;
	}

	public void setReceiveCommand(onReceiveCommand receiveCommand) {
		this.receiveCommand = receiveCommand;
	}

	@Override
	public void noticeReceiveError(IOException e) {
		
	}


	@Override
	public void noticeSendFileError(IOException e) {
		
	}
	
	@Override
	public void close() throws IOException {
		super.close();
		isReceived=false;
		executors.shutdownNow();
		instance=null;
	}

}
