package com.u4.home.media;

import java.io.File;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

/**
 * Mp3页面
 * 
 * @author Administrator
 * 
 */
public class Music extends Base {
	private String musicPath = "/music";
	private ArrayList<Object> musicList;

	private TextView tv_name, tv_time;
	private ImageView iv_play;
	private ListView lv_list;

	private LayoutInflater inflater;

	private Timer musicTimer = null;
	private Handler musicHandler = null;
	private MusicService serverMusic = null;
	private MusicServiceConnection musicServiceConnection;
	private MusicAdapter adapter = new MusicAdapter();

	@SuppressLint("HandlerLeak")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.media_music;

		findId();

		musicHandler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				tv_name.setText(serverMusic.currentFilename());
				tv_time.setText(toTime(serverMusic.currentPosition()));
				iv_play.setImageResource(serverMusic.status() ? R.drawable.icon_pause_blue : R.drawable.icon_play_blue);
			}
		};
	}

	@Override
	protected void onResume() {
		super.onResume();

		// 绑定服务
		Intent intent = new Intent(context, MusicService.class);
		startService(intent);
		musicServiceConnection = new MusicServiceConnection();
		bindService(intent, musicServiceConnection, BIND_AUTO_CREATE);

		startTimer();

		// 获取文件列表
		musicList = new ArrayList<Object>();
		if (!Appcontext.basepath.equals("")) {
			getFiles(Appcontext.basepath + musicPath);
		}
		lv_list.setAdapter(adapter);
	}

	@Override
	protected void onStop() {
		super.onStop();

		stopTimer();
		unbindService(musicServiceConnection);
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.music, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		tv_name = (TextView) findViewById(R.id.tv_name);
		tv_time = (TextView) findViewById(R.id.tv_time);

		iv_play = (ImageView) findViewById(R.id.iv_play);
		iv_play.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (serverMusic.status()) {
					serverMusic.pause();
				} else {
					serverMusic.play();
				}
			}
		});

		lv_list = (ListView) findViewById(R.id.lv_list);
		lv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				serverMusic.play(musicList.get(position).toString());
			}
		});
	}

	private void startTimer() {
		if (musicTimer != null)
			return;
		musicTimer = new Timer();
		musicTimer.schedule(new TimerTask() {
			@Override
			public void run() {
				if (serverMusic != null) {
					musicHandler.sendMessage(new Message());
				}
			}
		}, 0, 1000 * 1);
	}

	private void stopTimer() {
		if (musicTimer == null)
			return;
		musicTimer.cancel();
		musicTimer = null;
	}

	/**
	 * 音乐服务连接
	 * 
	 * @author Administrator
	 * 
	 */
	public class MusicServiceConnection implements ServiceConnection {
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			serverMusic = ((MusicService.MusicBinder) service).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			showLog("onServiceDisconnected : " + name.toString());
		}
	}

	public class MusicAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return musicList.size() == 0 ? 1 : musicList.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (musicList.size() == 0) {
				convertView = inflater.inflate(R.layout.list_row_empty, null);
			} else {
				convertView = inflater.inflate(R.layout.music_adapter, null);
				TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				TextView tv_duration = (TextView) convertView.findViewById(R.id.tv_duration);
				
				String fn = musicList.get(position).toString();
				tv_name.setText(getFilename(fn));
				tv_duration.setText(getInfos(fn));
			}
			return convertView;
		}
	}

	/**
	 * 获取MP3后缀的文件列表
	 * 
	 * @param path
	 */
	private void getFiles(String path) {
		File file = new File(path);
		if (!file.exists())
			file.mkdir();
		File[] files = file.listFiles();
		for (File filenames : files) {
			String filename = filenames.getName();
			if (filename.endsWith(".mp3")) {
				musicList.add(path + "/" + filename);
			}
		}
	}

	private String getInfos(String path) {
		int time = 0;
		String[] projection = { MediaStore.Audio.Media.DURATION };
		String selection = MediaStore.Audio.Media.DATA + " = ?";
		String[] selectionArgs = { path };
		ContentResolver resolver = getContentResolver();
		Cursor cursor = resolver.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, projection, selection, selectionArgs, null);
		if (cursor.moveToFirst()) {
			time = (int) cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
		}
		cursor.close();
		return toTime(time);
	}

	/**
	 * 将毫秒数转换为时间格式
	 * 
	 * @param time
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	private String toTime(int time) {
		time /= 1000;
		int second = time % 60;
		int minute = time / 60;
		int hour = minute / 60;
		minute %= 60;
		return hour > 0 ? String.format("%02d:%02d:%02d", hour, minute, second) : String.format("%02d:%02d", minute, second);
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
