package com.example.gongcheng;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;

import com.app.MyApplication;
import com.gongyong.Config;
import com.work.Work5;

public class Twenty_three_persionActivity extends Activity{
	TextView textView10;
	public static TextView textView20;
	//时间设置
	int i;
	String str;
	//把时间放到的地方
	TextView textView1;
	TextView textView2;
	TextView textView3;
	TextView textView4;
	//选择时间
	TextView textView5;
	TextView textView6;
	TextView textView7;
	TextView textView8;
	//后退按钮
	ImageButton button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//把activity放到容器中
		MyApplication.getInstance().addActivity(this);
		setContentView(R.layout.twenty_three_persion);
		textView10=(TextView)findViewById(R.id.chongfu_persion);
		textView20=(TextView)findViewById(R.id.xingqixuanze_persion);
		textView1=(TextView)findViewById(R.id.shangwushijiankaishi_persion);
		textView2=(TextView)findViewById(R.id.shangwushijianjieshu_persion);
		textView3=(TextView)findViewById(R.id.xiawushijiankaishi_persion);
		textView4=(TextView)findViewById(R.id.xiawushijianjieshu_persion);
		textView5=(TextView)findViewById(R.id.tjswks_persion);
		textView6=(TextView)findViewById(R.id.tjswksjs_persion);
		textView7=(TextView)findViewById(R.id.tjxwks_persion);
		textView8=(TextView)findViewById(R.id.tjxwjs_persion);
		Twenty_two_persionActivity.workpe=new Work5();
		Twenty_two_persionActivity.workpe.setOnoff(true);
		
		button=(ImageButton)findViewById(R.id.shijianhoutui_persion);
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Twenty_two_persionActivity.workpe.setDate(textView20.getText().toString().trim());
				Intent intent = new Intent(Twenty_three_persionActivity.this,Twenty_two_persionActivity.class);
				Twenty_three_persionActivity.this.startActivity(intent);
				
			}
		});
		textView10.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent intent=new Intent(Twenty_three_persionActivity.this,Twenty_four_persionActivity.class);
				Twenty_three_persionActivity.this.startActivity(intent);
			}
		});
		// textView20.setText(Config.sp.getString("date", ""));
		textView5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=1;
				showDialog(1);
			}
		});
		textView6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=2;
				showDialog(1);
			}
		});
		textView7.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=3;
				showDialog(1);
			}
		});
		textView8.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				i=4;
				showDialog(1);
			}
		});
	}
	TimePickerDialog.OnTimeSetListener onTimeSetListener=new OnTimeSetListener() {
		@Override
		public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
			// TODO Auto-generated method stub
			if(hourOfDay<10&&minute<10){
				str="0"+hourOfDay+":0"+minute;
			}
			else if (hourOfDay<10) {
				str="0"+hourOfDay+":"+minute;
			}
			else if (minute<10) {
				str=hourOfDay+":0"+minute;
			}
			else {
				str=hourOfDay+":"+minute;
			}

			if(i==1){
				textView1.setText(str);
				Twenty_two_persionActivity.workpe.setMorningst(textView1.getText().toString().trim());
			}
			if(i==2){
				textView2.setText(str);
				Twenty_two_persionActivity.workpe.setMorningsp(textView2.getText().toString().trim());
			}
			if(i==3){
				textView3.setText(str);
				Twenty_two_persionActivity.workpe.setAfternoonst(textView3.getText().toString().trim());

			}
			if(i==4){
				textView4.setText(str);
				Twenty_two_persionActivity.workpe.setAfternoonsp(textView4.getText().toString().trim());
			}
		}
	};
	@Override
	protected Dialog onCreateDialog(int id) {
		// TODO Auto-generated method stub
		switch (id) {
		case 1:
			return new TimePickerDialog(Twenty_three_persionActivity.this, onTimeSetListener, 15, 23,true);
		default:
			break;
		}
		return super.onCreateDialog(id);
	}
}
