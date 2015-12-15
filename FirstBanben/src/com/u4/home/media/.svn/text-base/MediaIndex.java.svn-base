package com.u4.home.media;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.u4.home.R;
import com.u4.home.common.Base;

public class MediaIndex extends Base {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.media_main;
		findId();
	}

	private void findId() {
		// set header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// set main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.menu_grid, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		// set menu content
		int menu_name[] = { R.string.media_music, R.string.media_video, R.string.media_photo };
		int menu_icon[] = { R.drawable.bnt_yib_03, R.drawable.bnt_shii_05, R.drawable.bnt_tup_07 };
		Class<?> menu_jump[] = { Music.class, Video.class, Photo.class };

		// set menu view
		GridView gvMenu = (GridView) findViewById(R.id.gv_menu);
		gvMenu.setAdapter(new MenuAdapter(menu_name, menu_icon, menu_jump));
	}

}
