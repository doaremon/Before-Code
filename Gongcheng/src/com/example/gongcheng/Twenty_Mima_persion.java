package com.example.gongcheng;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.app.MyApplication;
import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;
//修改密码
public class Twenty_Mima_persion extends Activity{
	EditText editText1;
	EditText editText2;
	EditText editText3;
	String aa;
	String bb;
	String cc;
    ImageButton imageButton;
	Button button;
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(Twenty_Mima_persion.this, "修改密码成功", Toast.LENGTH_LONG).show();
		}};
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
			super.onCreate(savedInstanceState);
			setContentView(R.layout.twenty_mima_pe);
			MyApplication.getInstance().addActivity(this);
			editText1=(EditText)findViewById(R.id.shurujiumima_pe);
			editText2=(EditText)findViewById(R.id.shuruxinmima_pe);
			editText3=(EditText)findViewById(R.id.zaicishuruxinmima_pe);
			button=(Button)findViewById(R.id.xiugaimimaqueren_pe);
			//后退按钮
			imageButton=(ImageButton)findViewById(R.id.xiugaimimabutton_pe);
			imageButton.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
				}
			});


			button.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					aa=editText1.getText().toString();
					bb=editText2.getText().toString();
					cc=editText3.getText().toString();
					Log.i("ccc", aa+"==="+bb+"-----"+cc);
					String name=Map.Updpassword(aa, bb, "");
					String url="http://222.88.48.186:8090/Ghyy/mobile/UpdatePassword";
					if(cc.equals(bb)){
						ShowRun showRun1=new ShowRun(name, url, handler);
						Thread thread=new Thread(showRun1);
						thread.start();
					}
					else{
						Toast.makeText(Twenty_Mima_persion.this, "您两次输入的密码不同，请重新输入！", Toast.LENGTH_LONG).show();
					}
					

				}
			});
		}

}
