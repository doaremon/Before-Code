package com.u4.home.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.db.Theme_DB;

@SuppressLint("HandlerLeak")
public class Theme extends Base {
	private Theme_DB db;
	private ThemeAdapter adapter = new ThemeAdapter();
	private XitongDialog dialog;
	private LayoutInflater inflater;
	private JSONArray jsonData;

	private GridView gv_list;
	private int indexTheme = 0;
	public Thread thread = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.control_theme;
		db = new Theme_DB(context);
		dialog = new XitongDialog(context);
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		// set usb handler
		Appcontext.handlerUSB = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				showLog("返回的数据为" + msg.obj.toString());
			}
		};

		db.open();
		jsonData = db.themeList();
		db.close();

		gv_list.setAdapter(adapter);
		gv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				if (jsonData.length() == 0 || jsonData.length() == position) {
					Intent intent = new Intent(context, ThemeAppend.class);
					startActivity(intent);
				} else {
					indexTheme = position;
					thread = new Thread(new Runnable() {
						@Override
						public void run() {
							try {
								JSONObject item;
								JSONArray jsonTheme = new JSONArray(jsonData.getJSONObject(indexTheme).getString("equipment"));
								for (int i = 0; i < jsonTheme.length(); i++) {
									item = new JSONObject(jsonTheme.getString(i));
									String cmd = Appcontext.serviceUSB.createData(item.getInt("type"), item.getString("code"), item.getString("unit"), item.getInt("status"));
									if (Appcontext.serviceUSB != null && cmd.length() == 22)
										Appcontext.serviceUSB.sendData(cmd);
									Thread.sleep(200);
								}
							} catch (JSONException e) {
								e.printStackTrace();
							} catch (InterruptedException e) {
								e.printStackTrace();
							}
						}
					});
					thread.start();
				}
			}
		});
		gv_list.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
				if (jsonData.length() > position) {
					dialog.show("delete");
					dialog.btn_ok.setOnClickListener(new OnClickListener() {
						@Override
						public void onClick(View v) {
							try {
								dialog.dismiss();
								db.open();
								db.themeDel(jsonData.getJSONObject(position).getInt("id"));
								jsonData = db.themeList();
								db.close();
								adapter.notifyDataSetChanged();
							} catch (SQLiteException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					});
				}
				return false;
			}
		});
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.equipment_list, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		gv_list = (GridView) findViewById(R.id.gv_control);
	}

	public class ThemeAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return jsonData.length() + 1;
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
		public View getView(int position, View convertView, ViewGroup parent) {
			if (position == jsonData.length()) {
				convertView = inflater.inflate(R.layout.equipment_append, null);
			} else {
				convertView = inflater.inflate(R.layout.equipment_adapter, null);
				ImageView iv_icon = (ImageView) convertView.findViewById(R.id.iv_icon);
				TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				try {
					JSONObject item = jsonData.getJSONObject(position);
					tv_name.setText(item.getString("name"));
					iv_icon.setImageResource(R.drawable.icon_control_theme);
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
			return convertView;
		}
	}

}
