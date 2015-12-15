package com.u4.home.call;

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
import com.u4.home.command.TranslucentActivity;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.AsyncHttpResponseHandler;
import com.u4.home.http.RequestParams;
/**
 * 这个是拨号界面
 * @author Administrator
 *
 */
public class Calluser extends Base implements OnClickListener{
	private String myip, targetip;
	private EditText myedittext_num;
	private ImageView imageView_del;
	private Button call_user_ok,but_1,but_2,but_3,but_4,but_5,but_6,but_7,but_8,but_9,but_0,but_dong,but_men,but_wuye;
	private StringBuffer buffer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.frame_2);
		findid();
		cliner();
	}
	public void findid(){
		res_top_finish=R.string.call_main;

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = LayoutInflater.from(context).inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = LayoutInflater.from(context).inflate(R.layout.duijiangtongxin, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		buffer=new StringBuffer();
		myedittext_num=(EditText)findViewById(R.id.myedittext_num);
		imageView_del=(ImageView)findViewById(R.id.imageView_del);
		but_1=(Button)findViewById(R.id.but_1);
		but_2=(Button)findViewById(R.id.but_2);
		but_3=(Button)findViewById(R.id.but_3);
		but_4=(Button)findViewById(R.id.but_4);
		but_5=(Button)findViewById(R.id.but_5);
		but_6=(Button)findViewById(R.id.but_6);
		but_7=(Button)findViewById(R.id.but_7);
		but_8=(Button)findViewById(R.id.but_8);
		but_9=(Button)findViewById(R.id.but_9);
		but_0=(Button)findViewById(R.id.but_0);
		but_men=(Button)findViewById(R.id.but_men);
		but_dong=(Button)findViewById(R.id.but_dong);
		but_wuye=(Button)findViewById(R.id.but_wuye);
		call_user_ok=(Button)findViewById(R.id.call_user_ok);

		myip=Appcontext.mainInstance.getLocalIp();
		showLog("我自己的ip是"+myip);
		showToast("我自己的ip是"+myip);

	}
	public void cliner(){
		imageView_del.setOnClickListener(this);
		but_1.setOnClickListener(this);
		but_2.setOnClickListener(this);
		but_3.setOnClickListener(this);
		but_4.setOnClickListener(this);
		but_5.setOnClickListener(this);
		but_6.setOnClickListener(this);
		but_7.setOnClickListener(this);
		but_8.setOnClickListener(this);
		but_9.setOnClickListener(this);
		but_0.setOnClickListener(this);
		but_men.setOnClickListener(this);
		but_dong.setOnClickListener(this);
		but_wuye.setOnClickListener(this);
		call_user_ok.setOnClickListener(this);
	}

	public void callRoom(){
		AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
		RequestParams params=new RequestParams();
		params.put("c", "getip");
		params.put("room", buffer.toString());
		asyncHttpClient.get(Appcontext.myurl, params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				showLog("获取对方ip的返回值"+content);
				try {
					JSONObject object = new JSONObject(content);
					if(object.getInt("status") == 1){
						JSONObject object1 = new JSONObject(object.getString("data"));	
						targetip = object1.getString("ip");
						showLog("targetip : "+targetip);
						Intent intent=new Intent(context, TranslucentActivity.class);
						intent.putExtra("type", "1"); //1主叫,2被叫
						intent.putExtra("myname", Appcontext.roomNumber);
						intent.putExtra("targetip", targetip);
						intent.putExtra("targetname", buffer.toString());
						startActivity(intent);
                        showLog("主叫方已经发送请求");
					}else{
						showToast(object.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
	public void callManager(){
		AsyncHttpClient asyncHttpClient=new AsyncHttpClient();
		RequestParams params=new RequestParams();
		params.put("c", "getmanageip");
		asyncHttpClient.get(Appcontext.myurl, params, new AsyncHttpResponseHandler(){
			@Override
			public void onSuccess(String content) {
				super.onSuccess(content);
				try {
					JSONObject object = new JSONObject(content);
					if(object.getInt("status") == 1){
						JSONObject object1 = new JSONObject(object.getString("data"));	
						targetip = object1.getString("ip");
						showLog("targetip : "+targetip);
						Intent intent=new Intent(context, TranslucentActivity.class);
						intent.putExtra("type", "1"); //1主叫,2被叫
						intent.putExtra("myname", Appcontext.roomNumber.replace("栋", "d").replace("门", "m"));
						intent.putExtra("targetip", targetip);
						intent.putExtra("targetname", "管理中心");
						startActivity(intent);
						showLog("主叫方已经发送请求");
					}else{
						showToast(object.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.but_1:
		case R.id.but_2:
		case R.id.but_3:
		case R.id.but_4:
		case R.id.but_5:
		case R.id.but_6:
		case R.id.but_7:
		case R.id.but_8:
		case R.id.but_9:
		case R.id.but_0:
		case R.id.but_dong:
		case R.id.but_men:
			buffer.append(v.getTag());
			myedittext_num.setText(buffer.toString());
			break;
		case R.id.call_user_ok:
			callRoom();
			break;
		case R.id.but_wuye:
			callManager();
			break;
		case R.id.imageView_del:
			buffer.delete(0, buffer.length());
			myedittext_num.setText("");
			break;
		default:
			break;
		}
      
	}
}
