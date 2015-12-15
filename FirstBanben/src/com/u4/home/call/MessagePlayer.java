package com.u4.home.call;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

public class MessagePlayer extends Base implements SurfaceHolder.Callback {
	private SurfaceView surfaceView;
	private SurfaceHolder surfaceHolder;
	private MediaPlayer mediaPlayer = null;
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
	private String id, url;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.message_player);

		Intent intent = getIntent();
		id = intent.getStringExtra("id");
		url = intent.getStringExtra("url");

		surfaceView = (SurfaceView) findViewById(R.id.sv);
		surfaceHolder = surfaceView.getHolder();
		surfaceHolder.addCallback(this);
	}

	@Override
	protected void onResume() {
		super.onResume();

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				finish();
			}
		});
		mediaPlayer.setOnErrorListener(new OnErrorListener() {
			@Override
			public boolean onError(MediaPlayer mp, int what, int extra) {
				return false;
			}
		});
		mediaPlayer.setOnPreparedListener(new OnPreparedListener() {
			@Override
			public void onPrepared(MediaPlayer mp) {
				mediaPlayer.setLooping(false);
				mediaPlayer.start();
			}
		});

		serverGetMessage(id);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer != null) {
			if (mediaPlayer.isPlaying())
				mediaPlayer.stop();
			mediaPlayer.release();
		}
	}

	/**
	 * 获取视频留言文件
	 * 
	 * @param id
	 */
	public void serverGetMessage(String id) {
		RequestParams params = new RequestParams();
		params.put("c", "getmessage");
		params.put("id", id);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				showLog(response.toString());
				try {
					if (1 != response.getInt("status")) {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void playMessage(String url) {
		try {
			if (mediaPlayer == null)
				return;
			mediaPlayer.reset();
			mediaPlayer.setDataSource(url);
			mediaPlayer.prepareAsync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (mediaPlayer == null)
			return;
		surfaceHolder = holder;
		mediaPlayer.setDisplay(surfaceHolder);
		playMessage(url);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
	}
}
