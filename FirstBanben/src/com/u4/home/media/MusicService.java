package com.u4.home.media;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

	private String filepath = "", filename = "";
	private MediaPlayer mediaPlayer = null;
	private boolean end = true;

	@Override
	public IBinder onBind(Intent intent) {
		return new MusicBinder();
	}

	public class MusicBinder extends Binder {
		public MusicService getService() {
			return MusicService.this;
		}
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Log.i("Service", "onCreate : Mp3Service");

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				end = true;
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
				end = false;
			}
		});
	}

	public void play(String path) {
		if (mediaPlayer == null || path.equals(""))
			return;

		filepath = path;
		filename = getFilename(path);

		try {
			mediaPlayer.reset();
			mediaPlayer.setDataSource(filepath);
			mediaPlayer.prepareAsync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void play() {
		if (mediaPlayer == null)
			return;
		if (!mediaPlayer.isPlaying()) {
			mediaPlayer.start();
			end = false;
		}
	}

	public void stop() {
		if (mediaPlayer == null)
			return;
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
	}

	public void pause() {
		if (mediaPlayer == null)
			return;
		if (mediaPlayer.isPlaying())
			mediaPlayer.pause();
	}

	public boolean status() {
		return mediaPlayer.isPlaying();
	}

	public int currentPosition() {
		if (mediaPlayer == null || filepath.equals("") || end)
			return 0;
		return mediaPlayer.getCurrentPosition();
	}

	public String currentFilename() {
		return filename;
	}

	private String getFilename(String path) {
		int start = path.lastIndexOf("/");
		int end = path.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return path.substring(start + 1, end);
		}
		return null;
	}

}
