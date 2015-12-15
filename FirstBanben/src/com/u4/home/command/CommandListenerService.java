package com.u4.home.command;

import java.io.IOException;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.u4.home.common.Appcontext;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.AsyncHttpResponseHandler;
import com.u4.home.http.RequestParams;

public class CommandListenerService extends Service implements onReceiveCommand {
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	private CommandListener commandListener;

	@Override
	public void onCreate() {
		Log.i("com.u4", "服务被创建了");
		commandListener = CommandListener.getInstance();
		commandListener.setReceiveCommand(CommandListenerService.this);
		if (!commandListener.isRunning())
			try {
				commandListener.open();
			} catch (IOException e) {
				e.printStackTrace();
			}// 先监听端口，然后连接

		super.onCreate();
	}

	/**
	 * 发送呼叫记录
	 * 
	 * @param ip
	 * @param type
	 */
	public static void addHistory(String ip, String type) {
		AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("c", "addhistory");
		params.put("type", type);
		params.put("ip", ip);
		asyncHttpClient.get(Appcontext.myurl, params,
				new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
			}
		});
	}

	@Override
	public void onSetReceiveCommand(TCPMessage msg) {
		int type = Integer.parseInt(msg.getType());
		String targetip = msg.getTime();
		String targetname = msg.getContent();
		Intent intent = null;

		Log.i("Call Message Get", "type=" + type + ", targetip=" + targetip + ", targetname=" + targetname + ", busy=" + Appcontext.busy);

		switch (type) {
		case 1: // 对方请求通话
			if (targetip.equals(Appcontext.busy)) {
				return;
			} else if (Appcontext.busy.length() == 0) {
				Appcontext.busy = targetip;
				intent = new Intent();
				intent.setAction("maincalluser");
				intent.putExtra("targetip", targetip);
				intent.putExtra("targetname", targetname);
			} else {
				CommandSender commandSender = new CommandSender();
				TCPMessage tcpmsg = new TCPMessage();
				tcpmsg.setType("5");
				tcpmsg.setIp(targetip);
				commandSender.sendVideoChatCommand(tcpmsg);

				addHistory(targetip, "3");
			}
			break;
		case 2: // 对方同意通话
			Appcontext.busy = targetip;
			intent = new Intent();
			intent.setAction("pagecalluser");
			intent.putExtra("type", "2");
			break;
		case 3: // 对方结束通话：挂断、未接听
		case 4: // 对方拒绝通话
		case 5: // 对方忙
			Appcontext.busy = "";
			intent = new Intent();
			intent.setAction("pagecalluser");
			intent.putExtra("type", type + "");
			break;
		default:
			break;
		}

		if (intent != null) {
			sendBroadcast(intent);
		}
	}
}
