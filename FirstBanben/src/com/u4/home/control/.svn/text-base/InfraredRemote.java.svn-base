package com.u4.home.control;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

public class InfraredRemote extends Base implements OnClickListener {
	private Button btn_0, btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7;
	private TextView tv_tip;
	private Boolean study = false;
	private int type_id = 4;
	private String name, cmds, code;
	private int unit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.control_infrared;

		Intent intent = getIntent();
		name = intent.getStringExtra("name");
		cmds = intent.getStringExtra("cmds");
		code = intent.getStringExtra("code");
		unit = Integer.parseInt(intent.getStringExtra("unit"));
		showLog("name : " + name + ", cmds : " + cmds + ", code : " + code + ", unit : " + unit);

		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		Appcontext.handlerUSB = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
//				showToast("handlerUSB=" + msg);
				String[] res = parseHex(msg.toString());
				showToast("0001".equals(res[4]) ? R.string.action_success : R.string.action_failed);

			}
		};

		btn_0.setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				study = !study;
				tv_tip.setText(study ? R.string.control_key_study : R.string.control_key_normal);
				String data = Appcontext.serviceUSB.createData(type_id, code, "", study ? 2 : 4);
				showLog("data : " + data);
				if (Appcontext.serviceUSB != null && data.length() == 22)
					Appcontext.serviceUSB.sendData(data);
				return true;
			}
		});
	}

	public void findId() {
		int layout = 0;
		switch (unit) {
		case 1: // TV
			layout = R.layout.equipment_remote_tv;
			break;
		case 2: // AIR
			layout = R.layout.equipment_remote_air;
			break;
		default:
			break;
		}

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = LayoutInflater.from(context).inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = LayoutInflater.from(context).inflate(layout, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		tv_name.setText(name);

		tv_tip = (TextView) findViewById(R.id.tv_tip);

		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);

		if (btn_0 != null)
			btn_0.setOnClickListener(this);
		if (btn_1 != null)
			btn_1.setOnClickListener(this);
		if (btn_2 != null)
			btn_2.setOnClickListener(this);
		if (btn_3 != null)
			btn_3.setOnClickListener(this);
		if (btn_4 != null)
			btn_4.setOnClickListener(this);
		if (btn_5 != null)
			btn_5.setOnClickListener(this);
		if (btn_6 != null)
			btn_6.setOnClickListener(this);
		if (btn_7 != null)
			btn_7.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String tmp[] = cmds.split(",");
		String tag = (String) v.getTag();
		int idx = Integer.parseInt(tag);
		showLog("tag= " + tag);

		if (idx < tmp.length) {
			int cmd = study ? 3 : 1;
			String temp = Integer.toHexString(Integer.parseInt(tmp[idx]));
			String data = Appcontext.serviceUSB.createData(type_id, code, "", cmd, temp);
			if (Appcontext.serviceUSB != null && data.length() == 22)
				Appcontext.serviceUSB.sendData(data);
			showLog("type_id : " + type_id + ", code : " + code + ", cmd : " + cmd + ", temp : " + temp + "\ndata : " + data);
			showToast("指令 " + data);
		}
	}

}
