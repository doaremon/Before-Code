package com.u4.home.common;

import android.app.AlertDialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import com.u4.home.R;

public class XitongDialog {
	private AlertDialog dialog;
	private View view;

	public Button btn_ok, btn_cancel;
	public EditText et_mobile, et_server_ip, et_server_port, et_password, et_zonename, et_undefended, et_equipment_name, et_equipment_code, et_theme_name, et_infrared_name, et_infrared_code;
	public LinearLayout ll_mobile, ll_volume, ll_server, ll_brightness, ll_password, ll_zone, ll_undefended, ll_equipment, ll_infrared, ll_theme, ll_defence, ll_delete;
	public SeekBar sb_volume_talk, sb_volume_beep, sb_brightness;
	public Spinner so_zonetype, so_equipment_unit, so_infrared_unit;
	public ListView lv_list;
	public TextView tv_defence_countdown;

	public XitongDialog(Context context) {
		dialog = new AlertDialog.Builder(context).create();
		dialog.setCanceledOnTouchOutside(false);
		view = LayoutInflater.from(context).inflate(R.layout.dialog, null);

		btn_ok = (Button) view.findViewById(R.id.btn_ok);
		btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
		btn_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		et_mobile = (EditText) view.findViewById(R.id.et_mobile);
		et_server_ip = (EditText) view.findViewById(R.id.et_server_ip);
		et_server_port = (EditText) view.findViewById(R.id.et_server_port);
		et_password = (EditText) view.findViewById(R.id.et_password);
		et_zonename = (EditText) view.findViewById(R.id.et_zonename);
		et_undefended = (EditText) view.findViewById(R.id.et_undefended);
		et_equipment_name = (EditText) view.findViewById(R.id.et_equipment_name);
		et_equipment_code = (EditText) view.findViewById(R.id.et_equipment_code);
		et_theme_name = (EditText) view.findViewById(R.id.et_theme_name);
		et_infrared_name = (EditText) view.findViewById(R.id.et_infrared_name);
		et_infrared_code = (EditText) view.findViewById(R.id.et_infrared_code);

		sb_volume_talk = (SeekBar) view.findViewById(R.id.sb_volume_talk);
		sb_volume_beep = (SeekBar) view.findViewById(R.id.sb_volume_beep);
		sb_brightness = (SeekBar) view.findViewById(R.id.sb_brightness);

		so_zonetype = (Spinner) view.findViewById(R.id.so_zonetype);
		so_equipment_unit = (Spinner) view.findViewById(R.id.so_equipment_unit);
		so_infrared_unit = (Spinner) view.findViewById(R.id.so_infrared_unit);

		lv_list = (ListView) view.findViewById(R.id.lv_list);

		tv_defence_countdown = (TextView) view.findViewById(R.id.tv_defence_countdown);

		ll_mobile = (LinearLayout) view.findViewById(R.id.ll_mobile);
		ll_volume = (LinearLayout) view.findViewById(R.id.ll_volume);
		ll_brightness = (LinearLayout) view.findViewById(R.id.ll_brightness);
		ll_server = (LinearLayout) view.findViewById(R.id.ll_server);
		ll_password = (LinearLayout) view.findViewById(R.id.ll_password);
		ll_zone = (LinearLayout) view.findViewById(R.id.ll_zone);
		ll_undefended = (LinearLayout) view.findViewById(R.id.ll_undefended);
		ll_equipment = (LinearLayout) view.findViewById(R.id.ll_equipment);
		ll_infrared = (LinearLayout) view.findViewById(R.id.ll_infrared);
		ll_theme = (LinearLayout) view.findViewById(R.id.ll_theme);
		ll_defence = (LinearLayout) view.findViewById(R.id.ll_defence);
		ll_delete = (LinearLayout) view.findViewById(R.id.ll_delete);
	}

	public void show(String type) {
		// for control
		ll_equipment.setVisibility("equipment".equals(type) ? View.VISIBLE : View.GONE);
		ll_infrared.setVisibility("infrared".equals(type) ? View.VISIBLE : View.GONE);
		ll_theme.setVisibility("theme".equals(type) ? View.VISIBLE : View.GONE);
		// for safe
		ll_zone.setVisibility("zone".equals(type) ? View.VISIBLE : View.GONE);
		ll_undefended.setVisibility("undefended".equals(type) ? View.VISIBLE : View.GONE);
		// for config
		ll_password.setVisibility("password".equals(type) ? View.VISIBLE : View.GONE);
		ll_mobile.setVisibility("mobile".equals(type) ? View.VISIBLE : View.GONE);
		ll_volume.setVisibility("volume".equals(type) ? View.VISIBLE : View.GONE);
		lv_list.setVisibility("list".equals(type) ? View.VISIBLE : View.GONE);
		ll_brightness.setVisibility("brightness".equals(type) ? View.VISIBLE : View.GONE);
		ll_server.setVisibility("server".equals(type) ? View.VISIBLE : View.GONE);
		// confirm
		ll_defence.setVisibility("defence".equals(type) ? View.VISIBLE : View.GONE);
		ll_delete.setVisibility("delete".equals(type) ? View.VISIBLE : View.GONE);

		dialog.setView(view, 0, 0, 0, 0);
		dialog.getWindow().setGravity(Gravity.CENTER);
		dialog.show();
	}

	public void dismiss() {
		dialog.dismiss();
	}

}
