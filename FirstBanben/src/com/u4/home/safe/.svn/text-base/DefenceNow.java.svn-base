package com.u4.home.safe;

import java.util.Timer;
import java.util.TimerTask;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;

@SuppressLint("HandlerLeak")
public class DefenceNow extends Base {
	private int time = 0;
	private Timer timer;
	private XitongDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.translucent);
	}

	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			dialog.tv_defence_countdown.setText(time + "");
			if (time < 1) {
				Intent intent = new Intent(context, Appcontext.conf_undefence_mode == 1 ? UndefencePassword.class : Undefence.class);
				startActivity(intent);
				finish();
			}
			time--;
		}
	};

	@Override
	protected void onResume() {
		super.onResume();

		dialog = new XitongDialog(context);
		dialog.show("defence");
		dialog.btn_ok.setVisibility(View.GONE);
		dialog.btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		time = 10;
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendMessage(new Message());
			}
		}, 0, 1000 * 1);
	}

	@Override
	protected void onStop() {
		super.onStop();

		timer.cancel();
		dialog.dismiss();
	}
}
