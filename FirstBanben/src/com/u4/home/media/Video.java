package com.u4.home.media;

import java.io.File;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

/**
 * 视屏播放
 * 
 * @author Administrator
 * 
 */
public class Video extends Base {
	private String videoPath = "/video";
	private ArrayList<Object> videoList;
	private ListView lv_list;
	private VideoAdapter adapter = new VideoAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.media_video;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		// 获取文件列表
		videoList = new ArrayList<Object>();
		if (!Appcontext.basepath.equals("")) {
			getFiles(Appcontext.basepath + videoPath);
		}
		lv_list.setAdapter(adapter);
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.video, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		lv_list = (ListView) findViewById(R.id.shipin_list);
		lv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, VideoPlayer.class);
				intent.putExtra("path", Appcontext.basepath + videoPath + "/" + videoList.get(position).toString());
				startActivity(intent);
			}
		});
	}

	public class VideoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return videoList.size() == 0 ? 1 : videoList.size();
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
			if (videoList.size() == 0) {
				convertView = inflater.inflate(R.layout.list_row_empty, null);
			} else {
				convertView = inflater.inflate(R.layout.video_adapter, null);
				TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
				tv_name.setText(videoList.get(position).toString());
			}
			return convertView;
		}
	}

	/**
	 * 获取MP4后缀的文件列表
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
			if (filename.endsWith(".mp4")) {
				videoList.add(filename);
			}
		}
	}

}
