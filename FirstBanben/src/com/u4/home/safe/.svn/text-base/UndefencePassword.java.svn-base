package com.u4.home.safe;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

public class UndefencePassword extends Base implements OnClickListener {
	private EditText et_undefance;
	private Button btn_1, btn_2, btn_3, btn_4, btn_5, btn_6, btn_7, btn_8, btn_9, btn_0, btn_undefance;
	private ImageButton btn_delete;
	private StringBuffer buffer = new StringBuffer();
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
		View view_main = inflater.inflate(R.layout.undefence_password, null);
		FrameLayout inc_main = (FrameLayout) findViewById(R.id.inc_main);
		inc_main.addView(view_main, new LayoutParams(inc_main.getLayoutParams().width, inc_main.getLayoutParams().height));

		et_undefance = (EditText) findViewById(R.id.et_undefance);
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
		btn_delete = (ImageButton) findViewById(R.id.btn_delete);
		btn_undefance = (Button) findViewById(R.id.btn_undefance);

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
		btn_delete.setOnClickListener(this);
		btn_undefance.setOnClickListener(this);
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
			buffer.append(v.getTag());
			et_undefance.setText(buffer.toString());
			break;
		case R.id.btn_delete:
			buffer.delete(0, buffer.length());
			et_undefance.setText("");
			break;
		case R.id.btn_undefance:
			if (buffer.toString().equals(Appcontext.conf_undefence_password)) {
				finish();
			} else {
				buffer.delete(0, buffer.length());
				et_undefance.setText("");
			}
			break;
		default:
			break;
		}
	}

}
