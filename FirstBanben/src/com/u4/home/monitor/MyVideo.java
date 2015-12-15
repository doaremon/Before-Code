package com.u4.home.monitor;

import android.os.Bundle;
import android.view.SurfaceView;

import com.u4.home.R;
import com.videoServices.VideoActivity;

public class MyVideo extends VideoActivity {
	private SurfaceView mSurface;  
	
	@Override   
	protected void onCreate(Bundle savedInstanceState) {   
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.myvideo);  
		mSurface = (SurfaceView) findViewById(R.id.surfaceView1);  
		
		this.setSurfaceHolder(mSurface);
		
		this.setmFrequency(8);
		this.setmAudioFormat("aac");
		//352.288
		this.setmVideoWidth(320);
		this.setmVideoHeight(240);
		this.setmAudioOnly(false);
		this.setmBitrate(250000);
		this.setmClientIp("192.168.1.189");
		//rtsp://192.168.1.189:554
		this.setmClientPort(554);
		this.setmVideoParam("/mpeg4cif");
		
		this.startReceiveService();
	}  

	
}
