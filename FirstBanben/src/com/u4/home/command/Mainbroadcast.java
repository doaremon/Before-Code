package com.u4.home.command;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Mainbroadcast extends BroadcastReceiver{
	@Override
	public void onReceive(final Context context, Intent intent) {
		String targetip = intent.getStringExtra("targetip");
		String targetname = intent.getStringExtra("targetname");

		Intent intent2;
		intent2 = new Intent(context, TranslucentActivity.class);
		intent2.putExtra("type", "2"); //1主叫,2被叫
		intent2.putExtra("targetip", targetip);
		intent2.putExtra("targetname", targetname);
		intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent2);			

	}
}