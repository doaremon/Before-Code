package com.u4.home.media;

import java.io.File;
import java.util.ArrayList;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

public class Photo extends Base {
	private String photoPath = "/photo";
	private ArrayList<Object> photoList;
	private GridView gv_list;
	private PhotoAdapter adapter = new PhotoAdapter();
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.dmt_tupianshow;
		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		photoList = new ArrayList<Object>();
		if (!Appcontext.basepath.equals("")) {
			getFiles(Appcontext.basepath + photoPath);
		}
		gv_list.setAdapter(adapter);
	}

	private void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.photo, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		gv_list = (GridView) findViewById(R.id.gv_list);
		gv_list.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(context, PhotoPlayer.class);
				intent.putExtra("position", position);
				startActivity(intent);
			}
		});
	}

	private void getFiles(String path) {
		File file = new File(path);
		if (!file.exists())
			file.mkdir();
		File[] files = file.listFiles();
		for (File filenames : files) {
			String filename = filenames.getName();
			if (filename.endsWith(".jpg")) {
				photoList.add(path + "/" + filename);
			}
		}
	}

	public class PhotoAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			return photoList.size();
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
			ImageView imageView;
			if (convertView == null) {
				imageView = new ImageView(context);
				imageView.setLayoutParams(new GridView.LayoutParams(160, 120));// 设置ImageView对象布局
				imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);// 设置刻度的类型
			} else {
				imageView = (ImageView) convertView;
			}

			String fn = photoList.get(position).toString();

			// Bitmap bm =
			// BitmapFactory.decodeFile(photoList.get(position).toString());
			Bitmap bm = tryGetBitmap(fn, -1, 160 * 120);
			imageView.setImageBitmap(bm);
			return imageView;
		}
	}

	private int computeSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		int initialSize = computeInitialSampleSize(options, minSideLength, maxNumOfPixels);

		int roundedSize;
		if (initialSize <= 8) {
			roundedSize = 1;
			while (roundedSize < initialSize) {
				roundedSize <<= 1;
			}
		} else {
			roundedSize = (initialSize + 7) / 8 * 8;
		}

		return roundedSize;
	}

	private int computeInitialSampleSize(BitmapFactory.Options options, int minSideLength, int maxNumOfPixels) {
		double w = options.outWidth;
		double h = options.outHeight;

		// 上下限范围
		int lowerBound = (maxNumOfPixels == -1) ? 1 : (int) Math.ceil(Math.sqrt(w * h / maxNumOfPixels));
		int upperBound = (minSideLength == -1) ? 128 : (int) Math.min(Math.floor(w / minSideLength), Math.floor(h / minSideLength));

		if (upperBound < lowerBound) {
			// return the larger one when there is no overlapping zone.
			return lowerBound;
		}

		if ((maxNumOfPixels == -1) && (minSideLength == -1)) {
			return 1;
		} else if (minSideLength == -1) {
			return lowerBound;
		} else {
			return upperBound;
		}
	}

	private Bitmap tryGetBitmap(String imgFile, int minSideLength, int maxNumOfPixels) {
		if (imgFile == null || imgFile.length() == 0)
			return null;

		try {
			//FileDescriptor fd = new FileInputStream(imgFile).getFD();
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(imgFile, options);
			//BitmapFactory.decodeFileDescriptor(fd, null, options);

			options.inSampleSize = computeSampleSize(options, minSideLength, maxNumOfPixels);
			try {
				// 这里一定要将其设置回false，因为之前我们将其设置成了true
				// 设置inJustDecodeBounds为true后，decodeFile并不分配空间，即，BitmapFactory解码出来的Bitmap为Null,但可计算出原始图片的长度和宽度
				options.inJustDecodeBounds = false;

				Bitmap bmp = BitmapFactory.decodeFile(imgFile, options);
				return bmp == null ? null : bmp;
			} catch (OutOfMemoryError err) {
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
