package com.ffbao.ui;

import com.ffbao.activity.R;
import com.ffbao.util.ExitActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
/** 
 * 
 * @FileName:BottomMenuActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:BottomMenuActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 添加报备操作界面
 */
public class BottomMenuActivity extends BaseActivity implements OnClickListener {
	private Button tongxun, zhijie, pass;
	private LinearLayout ll;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ffb_activity_bottom_menu);
		ExitActivity.addActivity(this);
		tongxun = (Button) findViewById(R.id.add_input1);
		zhijie = (Button) findViewById(R.id.add_input2);
		pass = (Button) findViewById(R.id.add_pass);
		ll = (LinearLayout) findViewById(R.id.pop_layout);
		tongxun.setOnClickListener(this);
		zhijie.setOnClickListener(this);
		pass.setOnClickListener(this);
		
		overridePendingTransition(0, 0);
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.add_input1:
			startActivity(new Intent(getApplicationContext(),
					ContactAddActivity.class));
			break;
		case R.id.add_input2:
			startActivity(new Intent(getApplicationContext(),
					CustomAddActivity.class));
			break;
		case R.id.add_pass:
			finish();
			break;
		}
		finish();
	}

}
