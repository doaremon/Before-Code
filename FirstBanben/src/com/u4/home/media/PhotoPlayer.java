package com.u4.home.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

/**
 * 图片播放进入页面
 * 
 * @author Administrator
 * 
 */
@SuppressLint("HandlerLeak")
public class PhotoPlayer extends Base {
	private String photoPath = "/photo";
	private ArrayList<Object> photoList;
	private float touchDownX;
	private int position;
	private ImageView iv_photo, iv_close, iv_play;
	private LinearLayout ll_control_bar;
	private Timer timer = null;
	private Handler handler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			position++;
			if (position == photoList.size())
				position = 0;
			showPhoto();
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo_player);
		position = getIntent().getExtras().getInt("position");
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		photoList = new ArrayList<Object>();
		if (!Appcontext.basepath.equals("")) {
			getFiles(Appcontext.basepath + photoPath);
		}
		showPhoto();
	}

	private void findId() {
		iv_photo = (ImageView) findViewById(R.id.iv_photo);
		iv_close = (ImageView) findViewById(R.id.iv_close);
		iv_play = (ImageView) findViewById(R.id.iv_play);
		ll_control_bar = (LinearLayout) findViewById(R.id.ll_control_bar);

		iv_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (timer == null) {
					startTimer();
					iv_play.setImageResource(R.drawable.icon_player_pause);
				} else {
					stopTimer();
					iv_play.setImageResource(R.drawable.icon_player_plays);
				}
				ll_control_bar.setVisibility(View.GONE);
			}
		});

		iv_close.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				stopTimer();
				finish();
			}
		});
	}

	private void getFiles(String path) {
		File file = new File(path);
		File[] files = file.listFiles();
		for (File filenames : files) {
			String filename = filenames.getName();
			if (filename.endsWith(".jpg")) {
				photoList.add(path + "/" + filename);
			}
		}
	}

	/**
	 * 显示图片
	 */
	private void showPhoto() {
		if (photoList.size() == 0)
			return;
		String fn = photoList.get(position).toString();

		Bitmap bitmap = BitmapFactory.decodeFile(fn);
		Bitmap copybitmap = Bitmap.createBitmap(bitmap.getWidth() / 2, bitmap.getHeight() / 2, bitmap.getConfig());
		Canvas canvas = new Canvas(copybitmap);
		Matrix matrix = new Matrix();
		matrix.setScale(0.5f, 0.5f);
		canvas.drawBitmap(bitmap, matrix, new Paint());
		iv_photo.setImageBitmap(copybitmap);

	}

	/**
	 * 启动计时器
	 */
	private void startTimer() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				handler.sendMessage(new Message());
			}
		}, 1000 * 3, 1000 * 3);
	}

	/**
	 * 停止计时器
	 */
	private void stopTimer() {
		if (timer == null)
			return;
		timer.cancel();
		timer = null;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			touchDownX = event.getX();
			break;
		case MotionEvent.ACTION_UP:
			float move = event.getX() - touchDownX;
			if (Math.abs(move) == 0) { // click
				ll_control_bar.setVisibility(ll_control_bar.getVisibility() == View.GONE ? View.VISIBLE : View.GONE);
			} else { // move
				position += move > 0 ? 1 : -1;
				if (position == photoList.size())
					position = 0;
				if (position < 0)
					position = photoList.size() - 1;
				showPhoto();
			}
			break;
		}
		return true;
	}

}
