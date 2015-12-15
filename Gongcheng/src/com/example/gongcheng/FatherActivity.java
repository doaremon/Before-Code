package com.example.gongcheng;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ProgressBar;

public class FatherActivity extends Activity{
	ProgressBar progressBar;
	private static ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.father);
		progressBar=(ProgressBar)findViewById(R.id.father_probar);

	}
	public static void showbar(Context context ,String str){
		if(dialog.isShowing()&&dialog !=null){
			dialog.cancel();

		}else {
			dialog=new ProgressDialog(context);
			dialog.setMessage(str);
			dialog.show();

		}

	}

}
