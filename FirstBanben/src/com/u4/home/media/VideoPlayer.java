package com.u4.home.media;

import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import com.u4.home.R;
import com.u4.home.common.Base;

@SuppressLint("HandlerLeak")
public class VideoPlayer extends Base {
	private ImageButton btn_pause, btn_play;
	private LinearLayout ll_bar;
	private SeekBar sb_progress;

	private SurfaceView sv_video;
	private SurfaceHolder surfaceHolder;
	private MediaPlayer mediaPlayer;

	private Timer videoTimer = null;
	private Handler videoHandler = null;

	private String filename = "";
	private boolean end = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.video_player);

		filename = getIntent().getStringExtra("path");

		findId();

		videoHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					sb_progress.setProgress(mediaPlayer.getCurrentPosition());
				}
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();

		mediaPlayer = new MediaPlayer();
		mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

		mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
			@Override
			public void onCompletion(MediaPlayer mp) {
				end = true;
				btn_play.setVisibility(View.VISIBLE);
				btn_pause.setVisibility(View.GONE);
				ll_bar.setVisibility(View.VISIBLE);
				sb_progress.setProgress(0);
				mediaPlayer.seekTo(0);
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
				btn_play.setVisibility(View.GONE);
				btn_pause.setVisibility(View.VISIBLE);
				ll_bar.setVisibility(View.GONE);
				sb_progress.setMax(mediaPlayer.getDuration());
			}
		});

		startTimer();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();

		mediaPlayer.release();
		stopTimer();
	}

	private void findId() {
		ll_bar = (LinearLayout) findViewById(R.id.ll_bar);
		btn_play = (ImageButton) findViewById(R.id.btn_play);
		btn_pause = (ImageButton) findViewById(R.id.btn_pause);

		sv_video = (SurfaceView) findViewById(R.id.sv_video);
		surfaceHolder = sv_video.getHolder();
		surfaceHolder.addCallback(new Callback() {
			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					mediaPlayer.stop();
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				if (mediaPlayer != null) {
					surfaceHolder = holder;
					mediaPlayer.setDisplay(surfaceHolder);
					play(null);
				}
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
			}
		});

		sb_progress = (SeekBar) findViewById(R.id.sb_progress);
		sb_progress.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				if (fromUser) {
					if (mediaPlayer != null && mediaPlayer.isPlaying()) {
						mediaPlayer.seekTo(progress);
					}
				}
			}
		});
	}

	private void startTimer() {
		if (videoTimer != null)
			return;
		videoTimer = new Timer();
		videoTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (mediaPlayer != null && mediaPlayer.isPlaying()) {
					videoHandler.sendMessage(new Message());
				}
			}
		}, 0, 1000 * 1);
	}

	private void stopTimer() {
		if (videoTimer == null)
			return;
		videoTimer.cancel();
		videoTimer = null;
	}

	public void bar(View view) {
		ll_bar.setVisibility(ll_bar.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
	}

	public void play(View view) {
		if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
			if (end) {
				try {
					mediaPlayer.reset();
					mediaPlayer.setDataSource(filename);
					mediaPlayer.prepareAsync();
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				mediaPlayer.start();
			}
		}
	}

	public void pause(View view) {
		if (mediaPlayer != null && mediaPlayer.isPlaying()) {
			mediaPlayer.pause();
			btn_pause.setImageResource(R.drawable.icon_player_plays);
			return;
		}
		if (mediaPlayer != null) {
			mediaPlayer.start();
			btn_pause.setImageResource(R.drawable.icon_players_pauses);
			return;
		}
	}

	public void close(View view) {
		finish();
	}
}
