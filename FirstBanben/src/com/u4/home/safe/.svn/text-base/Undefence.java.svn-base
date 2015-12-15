package com.u4.home.safe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.FrameLayout;

import com.u4.home.R;
import com.u4.home.common.Base;

public class Undefence extends Base {
	private Button btn_undefance;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_1);
		findId();
	}

	public void findId() {
		inflater = LayoutInflater.from(context);

		// add main
		View view_main = inflater.inflate(R.layout.undefence, null);
		FrameLayout inc_main = (FrameLayout) findViewById(R.id.inc_main);
		inc_main.addView(view_main, new LayoutParams(inc_main.getLayoutParams().width, inc_main.getLayoutParams().height));

		btn_undefance = (Button) findViewById(R.id.btn_undefence);
		btn_undefance.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

}
