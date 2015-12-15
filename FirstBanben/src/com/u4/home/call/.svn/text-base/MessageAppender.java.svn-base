package com.u4.home.call;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
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
import com.u4.home.http.AsyncHttpResponseHandler;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

/**
 * 视频录制
 * 
 * @author Administrator
 * 
 */
public class MessageAppender extends Base implements OnClickListener {

	private String room = "";
	private EditText et_input;
	private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_build, btn_unit, btn_rec;
	private ImageView iv_delete;
	private StringBuffer buffer = new StringBuffer();
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	private LayoutInflater inflater;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.message_recorder;
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
		View view_middle = inflater.inflate(R.layout.message_append, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		et_input = (EditText) findViewById(R.id.et_input);
		iv_delete = (ImageView) findViewById(R.id.iv_delete);
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
		btn_unit = (Button) findViewById(R.id.btn_unit);
		btn_build = (Button) findViewById(R.id.btn_build);
		btn_rec = (Button) findViewById(R.id.btn_rec);

		iv_delete.setOnClickListener(this);
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
		btn_rec.setOnClickListener(this);
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
			buffer.append(v.getTag());
			et_input.setText(buffer.toString());
			break;
		case R.id.iv_delete:
			clearInput();
			break;
		case R.id.btn_rec:
			serverGetIp();
			break;
		default:
			break;
		}
	}
	
	private void clearInput() {
		buffer.delete(0, buffer.length());
		et_input.setText("");
	}

	/**
	 * 获取设备IP
	 */
	public void serverGetIp(){
		room = buffer.toString();
		RequestParams params = new RequestParams();
		params.put("c", "getip");
		params.put("room", room);
		asyncHttpClient.get(Appcontext.myurl, params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				showLog(content);
				try {
					JSONObject object = new JSONObject(content);
					if(1 == object.getInt("status")){
						Intent intent = new Intent(context, MessageRecorder.class);
						startActivityForResult(intent, 0);
					}else{
						showToast(object.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * 新增视频留言
	 */
	private void serverAddMessage(String room, final File file) {
		RequestParams params = new RequestParams();
		params.put("c", "addmessage");
		params.put("room", room);
		try {
			params.put("file", file);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}
		asyncHttpClient.post(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if(1 != response.getInt("status")){
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
				file.delete();
			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (data != null) {
			if (room.equals("")) {
				showToast("请先输入对方房号");
				return;
			}
			clearInput();
			
			String filename = data.getStringExtra("file");
			if (filename == null || filename.equals("")) {
				showToast("没有录制视频，请先录视频");
				return;
			}
			
			File file = new File(filename);
			showLog(file.getAbsolutePath());
			
			serverAddMessage(room, file);
		}
	}
}
