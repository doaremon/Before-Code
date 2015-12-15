package com.example.gongcheng;

import com.yonghuliuyan.Map;
import com.yonghuliuyan.ShowRun;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
//费用设置
public class Twenty_feiyong_persion extends Activity{
	EditText editText;
	Button button;
	ImageButton imageButton;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.twenty_feiyong_persion);
		editText=(EditText)findViewById(R.id.feiyongeditext_pe);
		button=(Button)findViewById(R.id.feiyongtijiaobutton_pe);
		imageButton=(ImageButton)findViewById(R.id.feiyongshezhibutton_pe);
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
				// TODO Auto-generated method stub
				String str=editText.getText().toString().trim();
				String url="http://222.88.48.186:8098/sims/medicationService.do?method=registrationServlet";
				String name=Map.feiyongshezhi();
				ShowRun run=new ShowRun(name, url, handler);
				Thread thread=new Thread(run);
				thread.start();
			}
		});
	}
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Toast.makeText(Twenty_feiyong_persion.this, "修改失败", Toast.LENGTH_LONG).show();
		}};
	
      
}
