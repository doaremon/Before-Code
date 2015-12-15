package com.u4.home.call;

import java.io.File;
import java.io.IOException;

import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

public class MessageRecorder extends Base implements OnClickListener {
	private File videoFile;
	private SurfaceView sv_camera;
	private ImageView iv_start, iv_stop, iv_close;
	private MediaRecorder mediaRecorder = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_recorder);
		findId();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mediaRecorder.release();
		mediaRecorder = null;
	}

	private void findId() {
		iv_start = (ImageView) findViewById(R.id.iv_start);
		iv_stop = (ImageView) findViewById(R.id.iv_stop);
		iv_close = (ImageView) findViewById(R.id.iv_close);
		sv_camera = (SurfaceView) findViewById(R.id.sv_camera);

		sv_camera.getHolder().setFixedSize(320, 240);

		iv_start.setOnClickListener(this);
		iv_stop.setOnClickListener(this);
		iv_close.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (!Appcontext.hasSDcard()) {
			Toast.makeText(this, R.string.sdcard_not_inuse, Toast.LENGTH_SHORT).show();
			return;
		}
		switch (v.getId()) {
		case R.id.iv_start:
			try {
				if (mediaRecorder == null) {
					iv_start.setVisibility(View.GONE);
					iv_stop.setVisibility(View.VISIBLE);
					videoFile = new File(Environment.getExternalStorageDirectory(), System.currentTimeMillis() + ".3gp");
					mediaRecorder = new MediaRecorder();
					mediaRecorder.reset();
					mediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
					mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
					mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
					mediaRecorder.setVideoSize(320, 240);
					mediaRecorder.setVideoFrameRate(3);
					mediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
					mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
					mediaRecorder.setPreviewDisplay(sv_camera.getHolder().getSurface());
					mediaRecorder.setOutputFile(videoFile.getAbsolutePath());
					mediaRecorder.prepare();
					mediaRecorder.start();
				}
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		case R.id.iv_stop:
			if (mediaRecorder != null) {
				iv_start.setVisibility(View.VISIBLE);
				iv_stop.setVisibility(View.GONE);
				mediaRecorder.stop();
				Intent intent = new Intent();
				intent.putExtra("file", videoFile.getAbsolutePath());
				setResult(0, intent);
				finish();
			}
			break;
		case R.id.iv_close:
			finish();
			break;
		default:
			break;
		}
	}
}
