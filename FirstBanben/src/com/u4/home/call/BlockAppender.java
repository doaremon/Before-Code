package com.u4.home.call;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

/**
 * 列表添加
 * 
 * @author Administrator
 */
public class BlockAppender extends Base implements OnClickListener {
	private int type;
	private EditText et_room;
	private ImageView iv_del;
	private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_build, btn_unit, btn_add;
	private StringBuffer buffer = new StringBuffer();
	private LayoutInflater inflater;
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.call_setting;
		type = getIntent().getExtras().getInt("type");
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
		View view_middle = inflater.inflate(R.layout.block_append, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		et_room = (EditText) findViewById(R.id.et_room);
		iv_del = (ImageView) findViewById(R.id.iv_del);
		btn_1 = (Button) findViewById(R.id.btn_1);
		btn_2 = (Button) findViewById(R.id.btn_2);
		btn_3 = (Button) findViewById(R.id.btn_3);
		btn_4 = (Button) findViewById(R.id.btn_4);
		btn_5 = (Button) findViewById(R.id.btn_5);
		btn_6 = (Button) findViewById(R.id.btn_6);
		btn_7 = (Button) findViewById(R.id.btn_7);
		btn_8 = (Button) findViewById(R.id.btn_8);
		btn_9 = (Button) findViewById(R.id.btn_9);
		btn_0 = (Button) findViewById(R.id.btn_0);
		btn_build = (Button) findViewById(R.id.btn_build);
		btn_unit = (Button) findViewById(R.id.btn_unit);
		btn_add = (Button) findViewById(R.id.btn_add);
		btn_add.setText(type == 1 ? R.string.block_list_add : R.string.allow_list_add);

		iv_del.setOnClickListener(this);
		btn_1.setOnClickListener(this);
		btn_2.setOnClickListener(this);
		btn_3.setOnClickListener(this);
		btn_4.setOnClickListener(this);
		btn_5.setOnClickListener(this);
		btn_6.setOnClickListener(this);
		btn_7.setOnClickListener(this);
		btn_8.setOnClickListener(this);
		btn_9.setOnClickListener(this);
		btn_0.setOnClickListener(this);
		btn_unit.setOnClickListener(this);
		btn_build.setOnClickListener(this);
		btn_add.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_1:
		case R.id.btn_2:
		case R.id.btn_3:
		case R.id.btn_4:
		case R.id.btn_5:
		case R.id.btn_6:
		case R.id.btn_7:
		case R.id.btn_8:
		case R.id.btn_9:
		case R.id.btn_0:
		case R.id.btn_build:
		case R.id.btn_unit:
			buffer.append(v.getTag().toString());
			et_room.setText(buffer.toString());
			break;
		case R.id.iv_del:
			buffer.delete(0, buffer.length());
			et_room.setText("");
			break;
		case R.id.btn_add:
			serverAddBlock(Integer.toString(type), buffer.toString());
			break;
		default:
			break;
		}
	}

	/**
	 * 新增屏蔽名单
	 */
	private void serverAddBlock(String type, String room) {
		RequestParams params = new RequestParams();
		params.put("c", "addblock");
		params.put("type", type);
		params.put("room", room);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 != response.getInt("status")) {
						showToast(response.getString("message"));
					}
					finish();
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
