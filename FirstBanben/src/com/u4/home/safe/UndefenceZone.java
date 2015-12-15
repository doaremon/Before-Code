package com.u4.home.safe;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.db.Zone_DB;
import com.u4.home.db.Zone_Shitilei;

public class UndefenceZone extends Base {
	private Zone_DB db;
	private List<Zone_Shitilei> list = new ArrayList<Zone_Shitilei>();
	private ListView lv_list;
	private ImageView iv_switch_password;
	private RelativeLayout rl_password;
	private TextView tv_password;
	private UndefendedAdapter adapter = new UndefendedAdapter();
	private XitongDialog dialog;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.undefence_setting;

		db = new Zone_DB(context);
		dialog = new XitongDialog(context);

		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		db.open();
		Zone_Shitilei[] zone_Shitilei = db.queryAllData();
		for (int c = 0; c < zone_Shitilei.length; c++) {
			list.add(zone_Shitilei[c]);
		}
		db.close();

		lv_list.setAdapter(adapter);
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.undefence_zone, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		iv_switch_password = (ImageView) findViewById(R.id.iv_switch_password);
		iv_switch_password.setImageResource(Appcontext.conf_undefence_mode == 0 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
		iv_switch_password.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Appcontext.conf_undefence_mode = Appcontext.conf_undefence_mode == 1 ? 0 : 1;
				iv_switch_password.setImageResource(Appcontext.conf_undefence_mode == 0 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
				rl_password.setVisibility(Appcontext.conf_undefence_mode == 1 ? View.VISIBLE : View.GONE);
				Appcontext.mainInstance.setConfig("conf_undefence_mode", Appcontext.conf_undefence_mode);
			}
		});

		rl_password = (RelativeLayout) findViewById(R.id.rl_password);
		rl_password.setVisibility(Appcontext.conf_undefence_mode == 1 ? View.VISIBLE : View.GONE);
		rl_password.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.show("undefended");
				dialog.et_undefended.setText("");
				dialog.btn_ok.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						dialog.dismiss();
						Appcontext.conf_undefence_password = dialog.et_undefended.getText().toString().trim();
						tv_password.setText(Appcontext.conf_undefence_password);
						Appcontext.mainInstance.setConfig("conf_undefence_password", Appcontext.conf_undefence_password);
					}
				});
			}
		});

		tv_password = (TextView) findViewById(R.id.tv_password);
		tv_password.setText(Appcontext.conf_undefence_password);

		lv_list = (ListView) findViewById(R.id.lv_list);
	}

	private class UndefendedAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return list.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(final int position, View convertView, ViewGroup parent) {
			convertView = inflater.inflate(R.layout.undefence_zone_adapter, null);
			TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
			TextView tv_subtitle = (TextView) convertView.findViewById(R.id.tv_subtitle);
			ImageView iv_switch_zone = (ImageView) convertView.findViewById(R.id.iv_switch);
			tv_title.setText(list.get(position).id + "# " + list.get(position).name);
			tv_subtitle.setText(list.get(position).type);
			iv_switch_zone.setImageResource(list.get(position).undefended == 1 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
			iv_switch_zone.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					list.get(position).undefended = list.get(position).undefended == 1 ? 0 : 1;
					db.open();
					db.updateUndefended(list.get(position).id, list.get(position).undefended);
					db.close();
					adapter.notifyDataSetChanged();
				}
			});
			return convertView;
		}
	}
}
