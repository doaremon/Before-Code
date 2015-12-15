package com.u4.home.call;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.u4.home.R;
import com.u4.home.common.Base;

public class CallIndex extends Base {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.call_main;
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
		int menu_name[] = { R.string.call_video, R.string.message, R.string.history_list, R.string.notice_list, R.string.call_setting };
		int menu_icon[] = { R.drawable.bnt_yul_03, R.drawable.bnt_tongh_05, R.drawable.bnt_tongx_07, R.drawable.bnt_guangb_15, R.drawable.bnt_shez_16 };
		Class<?> menu_jump[] = { Calluser.class, Message.class, HistoryList.class, NoticeList.class, BlockList.class };

		// set menu view
		GridView gvMenu = (GridView) findViewById(R.id.gv_menu);
		gvMenu.setAdapter(new MenuAdapter(menu_name, menu_icon, menu_jump));
	}

}
