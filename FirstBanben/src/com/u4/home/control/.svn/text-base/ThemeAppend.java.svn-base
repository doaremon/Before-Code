package com.u4.home.control;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.db.Equipment_DB;
import com.u4.home.db.Theme_DB;

public class ThemeAppend extends Base {
	private ListView lv_list;
	private JSONArray data = new JSONArray();
	private Equipment_DB db;
	private XitongDialog dialog;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.control_theme_add;
		db = new Equipment_DB(context);
		dialog = new XitongDialog(context);
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		db.open();
		getData();
		db.close();

		lv_list.setAdapter(new Myadapter());
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// middle
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.theme_append, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));
		lv_list = (ListView) findViewById(R.id.lv_list);

		TextView btn_save = (TextView) findViewById(R.id.btn_save);
		btn_save.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = null;
				try {
					name = data.getJSONObject(0).getString("content");
				} catch (JSONException e) {
					e.printStackTrace();
				}
				if (name != null && !"".equals(name)) {
					Theme_DB dbTheme = new Theme_DB(context);
					dbTheme.open();
					dbTheme.themeAdd(name, saveData());
					dbTheme.close();
				}
				finish();
			}
		});
	}

	private void getData() {
		try {
			JSONArray list, type;
			data.put(createItem("item_input", "情景名称", "点击输入", "", 0, "", "", 0));
			type = db.typeList();
			for (int i = 0; i < type.length(); i++) {
				data.put(createItem("group", type.getJSONObject(i).getString("name"), "", "", 0, "", "", 0));
				list = db.equipmentList(type.getJSONObject(i).getInt("id"));
				if (list.length() > 0) {
					for (int j = 0; j < list.length(); j++) {
						data.put(createItem("item_switch", list.getJSONObject(j).getString("name"), "", "", type.getJSONObject(i).getInt("id"), list.getJSONObject(j).getString("code"), list.getJSONObject(j).getString("unit"), 0));
					}
				} else {
					data.put(createItem("item_empty", "暂无设备", "", "", 0, "", "", 0));
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private JSONObject createItem(String mode, String title, String subtitle, String content, int type, String code, String unit, int status) {
		JSONObject item = new JSONObject();
		try {
			item.put("mode", mode);
			item.put("title", title);
			item.put("subtitle", subtitle);
			item.put("content", content);
			item.put("type", type);
			item.put("code", code);
			item.put("unit", unit);
			item.put("status", status);
		} catch (JSONException e) {
			e.printStackTrace();
			return null;
		}
		return item;
	}

	private String saveData() {
		JSONArray save = new JSONArray();
		JSONObject item;
		try {
			String[] names = { "type", "code", "unit", "status" };
			for (int i = 0; i < data.length(); i++) {
				item = data.getJSONObject(i);
				if ("item_switch".equals(item.getString("mode"))) {
					save.put(new JSONObject(item, names).toString());
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return save.toString();
	}

	public class Myadapter extends BaseAdapter {
		@Override
		public int getCount() {
			return data.length();
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
			try {
				JSONObject item = data.getJSONObject(position);
				if ("group".equals(item.getString("mode"))) {
					convertView = inflater.inflate(R.layout.theme_adapter_group, null);
					TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
					tv_title.setText(item.getString("title"));
				} else {
					convertView = inflater.inflate(R.layout.theme_adapter_item, null);
					TextView tv_title = (TextView) convertView.findViewById(R.id.tv_title);
					TextView tv_subtitle = (TextView) convertView.findViewById(R.id.tv_subtitle);
					TextView tv_content = (TextView) convertView.findViewById(R.id.tv_content);
					ImageView iv_switch = (ImageView) convertView.findViewById(R.id.iv_switch);

					tv_title.setText(item.getString("title"));

					if (!"".equals(item.getString("subtitle"))) {
						tv_subtitle.setText(item.getString("subtitle"));
						tv_subtitle.setVisibility(View.VISIBLE);
					}

					if ("item_input".equals(item.getString("mode"))) {
						tv_content.setText(item.getString("content"));
						tv_content.setVisibility(View.VISIBLE);
						convertView.setTag(position);
						convertView.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								dialog.show("theme");
								dialog.btn_ok.setTag((Integer) v.getTag());
								dialog.btn_ok.setOnClickListener(new OnClickListener() {
									@Override
									public void onClick(View v) {
										dialog.dismiss();
										int position = (Integer) v.getTag();
										try {
											JSONObject item = data.getJSONObject(position);
											data.put(position, createItem(item.getString("mode"), item.getString("title"), item.getString("subtitle"), dialog.et_theme_name.getText().toString().trim(), 0, "", "", 0));
											notifyDataSetChanged();
										} catch (JSONException e) {
											e.printStackTrace();
										}
									}
								});
							}
						});
					} else if ("item_switch".equals(item.getString("mode"))) {
						iv_switch.setTag(position);
						iv_switch.setImageResource(item.getInt("status") == 1 ? R.drawable.btn_switch_on : R.drawable.btn_switch_off);
						iv_switch.setVisibility(View.VISIBLE);
						iv_switch.setOnClickListener(new OnClickListener() {
							@Override
							public void onClick(View v) {
								int position = (Integer) v.getTag();
								try {
									JSONObject item = data.getJSONObject(position);
									data.put(position, createItem(item.getString("mode"), item.getString("title"), item.getString("subtitle"), item.getString("content"), item.getInt("type"), item.getString("code"), item.getString("unit"), item.getInt("status") == 1 ? 0 : 1));
									notifyDataSetChanged();
								} catch (JSONException e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}
			return convertView;
		}

	}

}
