package com.u4.home.command;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.call.Myviewpage;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.AsyncHttpResponseHandler;
import com.u4.home.http.RequestParams;

/**
 * 透明的页面，这个是为了显示dialog的时候用的
 * 
 * @author Administrator
 * 
 */
@SuppressLint("HandlerLeak")
public class TranslucentActivity extends Base {
	private AlertDialog dialog;
	private View dialogView;
	private MediaPlayer mediaPlayer = null;
	private Timer timer = null;
	private int times = 10;
	private Handler handler;
	private int type = 0; // 1主叫,2被叫
	private String myname, targetip, targetname;
	private PageuserBroadcast broadcast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.translucent);

		Intent intent = getIntent();
		type = Integer.parseInt(intent.getStringExtra("type"));
		myname = intent.getStringExtra("myname");
		targetip = intent.getStringExtra("targetip");
		targetname = intent.getStringExtra("targetname");

		showToast("type:" + type + ", targetip : " + targetip + ", targetname:" + targetname);

		dialogView = LayoutInflater.from(context).inflate(R.layout.dialogshow, null);
		TextView tv_dialog_content = (TextView) dialogView.findViewById(R.id.tv_dialog_content);
		Button btn_ok = (Button) dialogView.findViewById(R.id.btn_ok);
		Button btn_cancel = (Button) dialogView.findViewById(R.id.btn_cancel);

		String target = targetname;
		if ("manager".equals(target)) {
			target = "管理中心";
		} else if ("door".equals(target)) {
			target = "单元门";
			btn_ok.setText(R.string.call_view);
		}

		dialog = new AlertDialog.Builder(context).create();
		dialog.setView(dialogView, 0, 0, 0, 0);
		dialog.getWindow().setGravity(Gravity.CENTER);
		dialog.show();

		broadcast = new PageuserBroadcast();
		this.registerReceiver(broadcast, new IntentFilter("pagecalluser"));

		String dialogContent = "";
		if (type == 1) { // 主叫
			dialogContent = "正在呼叫 " + target;
			btn_ok.setVisibility(View.GONE);
			btn_cancel.setText(R.string.call_hangup);
			callMainer();
		} else { // 被叫
			dialogContent = "来自 " + target + " 的呼叫";

			handler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					super.handleMessage(msg);
					addHistory(targetip, "3");
					callHangup();
					destroy();
				}
			};

			timer = new Timer();
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					handler.sendMessage(new Message());
				}
			}, 1000 * times, 1000);
			startRing(Appcontext.preferences.getString("conf_ring_call", ""));
		}

		tv_dialog_content.setText(dialogContent);

		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (type == 1) {
					callHangup();
				} else {
					callRefuse();
				}
				destroy();
			}
		});

		btn_ok.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				callAccept();
				destroy();
			}
		});
	}

	/**
	 * 被叫方接听
	 */
	private void callAccept() {
		addHistory(targetip, "2");

		Intent intent2 = new Intent(context, Myviewpage.class);
		intent2.putExtra("type", "2"); // 1主叫,2被叫
		intent2.putExtra("targetip", targetip);
		intent2.putExtra("targetname", targetname);
		startActivity(intent2);
	}

	/**
	 * 被叫放拒绝
	 */
	private void callRefuse() {
		addHistory(targetip, "2");

		CommandSender commandSender = new CommandSender();
		TCPMessage msg = new TCPMessage();
		msg.setType("4");
		msg.setIp(targetip);
		commandSender.sendVideoChatCommand(msg);

		Appcontext.busy = "";
	}

	/**
	 * 主叫方呼出
	 */
	private void callMainer() {
		addHistory(targetip, "1");

		CommandSender commandSender = new CommandSender();
		TCPMessage msg = new TCPMessage();
		msg.setType("1");
		msg.setContent(myname);
		msg.setIp(targetip);
		msg.setTime(Appcontext.mainInstance.getLocalIp());
		commandSender.sendVideoChatCommand(msg);

		Appcontext.busy = targetip;
	}

	/**
	 * 主叫方挂断
	 */
	private void callHangup() {
		CommandSender commandSender = new CommandSender();
		TCPMessage msg = new TCPMessage();
		msg.setType("3");
		msg.setIp(targetip);
		commandSender.sendVideoChatCommand(msg);

		Appcontext.busy = "";
	}

	/**
	 * 播放铃声
	 * 
	 * @param path
	 */
	public void startRing(String path) {
		if (path.equals(""))
			return;
		try {
			if (mediaPlayer == null) {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
					}
				});
				mediaPlayer.setOnErrorListener(new OnErrorListener() {
					@Override
					public boolean onError(MediaPlayer mp, int what, int extra) {
						return false;
					}
				});
				mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
					@Override
					public void onPrepared(MediaPlayer mp) {
						mediaPlayer.setLooping(true);
						mediaPlayer.start();
					}
				});
			}
			mediaPlayer.reset();
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepareAsync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 停止铃声
	 */
	public void stopRing() {
		if (mediaPlayer == null)
			return;
		mediaPlayer.stop();
		mediaPlayer.release();
		mediaPlayer = null;
	}

	/**
	 * 停止计时器
	 */
	private void stopTimer() {
		if (timer == null)
			return;
		timer.cancel();
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
		asyncHttpClient.get(Appcontext.myurl, params, new AsyncHttpResponseHandler() {
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				System.out.println("这个是addhistory的返回值为=" + content);
			}
		});
	}

	/**
	 * 广播接收者
	 * 
	 * @author Administrator
	 * 
	 */
	class PageuserBroadcast extends BroadcastReceiver {
		@Override
		public void onReceive(Context context, Intent intent) {
			int type = Integer.parseInt(intent.getStringExtra("type"));
			System.out.println("广播接收到的broadcast 的type 是： " + type);
			switch (type) {
			case 2:
				showToast("通话开始:对方接受");
				Intent intent2 = new Intent(context, Myviewpage.class);
				intent2.putExtra("type", "1"); // 1主叫,2被叫
				intent2.putExtra("targetip", targetip);
				intent2.putExtra("targetname", targetname);
				startActivity(intent2);
				destroy();
				break;
			case 3:
				showToast("通话结束:对方挂断");
				destroy();
				break;
			case 4:
				showToast("通话结束:对方拒绝");
				destroy();
				break;
			case 5:
				showToast("通话结束:对方忙");
				destroy();
				break;
			default:
				break;
			}

		}

	}

	private void destroy() {
		unregisterReceiver(broadcast);
		stopTimer();
		stopRing();
		dialog.dismiss();
		finish();
	}
}
