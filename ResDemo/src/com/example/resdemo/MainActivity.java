package com.example.resdemo;

import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;

import org.apache.commons.android.binary.Base64;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		set();

	}
	public void set(){
		 byte[] modulusBytes = Base64.decodeBase64("123");
         byte[] publicExponentBytes = Base64.decodeBase64("123");
         RSAPublicKey pubKey = RSAUtils.getPublicKey(new BigInteger(modulusBytes).toString(), new BigInteger(publicExponentBytes).toString());
         String mi = RSAUtils.encrypt(pubKey, "123");
         Toast.makeText(MainActivity.this, "加密后-"+mi, 0).show();
         Log.i("chenghao", "加密后-"+mi);
       
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
