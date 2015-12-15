package com.u4.home.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.call.HistoryList;
import com.u4.home.call.MessageList;
import com.u4.home.call.NoticeList;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

/**
 * 新消息页面
 * 
 * @author Administrator
 * 
 */
public class Newmessage extends Base implements OnClickListener {
	private LinearLayout ll_new_history, ll_new_message, ll_new_notice;
	private FrameLayout fl_new_history, fl_new_message, fl_new_notice;
	private TextView tv_new_history, tv_new_message, tv_new_notice;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.newmessage;
		findId();
	}

	public void findId() {
		inflater = LayoutInflater.from(context);
		
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.newmessage, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		fl_new_history = (FrameLayout) findViewById(R.id.fl_new_history);
		fl_new_message = (FrameLayout) findViewById(R.id.fl_new_message);
		fl_new_notice = (FrameLayout) findViewById(R.id.fl_new_notice);

		tv_new_history = (TextView) findViewById(R.id.tv_new_history);
		tv_new_message = (TextView) findViewById(R.id.tv_new_message);
		tv_new_notice = (TextView) findViewById(R.id.tv_new_notice);

		ll_new_history = (LinearLayout) findViewById(R.id.ll_new_history);
		ll_new_history.setOnClickListener(this);

		ll_new_message = (LinearLayout) findViewById(R.id.ll_new_message);
		ll_new_message.setOnClickListener(this);

		ll_new_notice = (LinearLayout) findViewById(R.id.ll_new_notice);
		ll_new_notice.setOnClickListener(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (Appcontext.newHistory > 0) {
			tv_new_history.setText(Appcontext.newHistory < 10 ? Appcontext.newHistory + "" : "N");
			fl_new_history.setVisibility(View.VISIBLE);
		}
		if (Appcontext.newMessage > 0) {
			tv_new_message.setText(Appcontext.newMessage < 10 ? Appcontext.newMessage + "" : "N");
			fl_new_message.setVisibility(View.VISIBLE);
		}
		if (Appcontext.newNotic > 0) {
			tv_new_notice.setText(Appcontext.newNotic < 10 ? Appcontext.newNotic + "" : "N");
			fl_new_notice.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.ll_new_history:
			intent = new Intent(context, HistoryList.class);
			break;
		case R.id.ll_new_message:
			intent = new Intent(context, MessageList.class);
			break;
		case R.id.ll_new_notice:
			intent = new Intent(context, NoticeList.class);
			break;
		default:
			break;
		}
		if (intent != null)
			startActivity(intent);
	}
}
