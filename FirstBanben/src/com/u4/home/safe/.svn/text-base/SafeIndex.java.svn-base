package com.u4.home.safe;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.u4.home.R;
import com.u4.home.common.Base;

@SuppressLint("HandlerLeak")
public class SafeIndex extends Base {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.safe_main;
		findId();
	}

	public void findId() {
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.menu_grid, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		// set menu content
		int menu_name[] = { R.string.defence_now, R.string.defence_setting, R.string.undefence_setting, R.string.defence_zone_setting, R.string.warning_list };
		int menu_icon[] = { R.drawable.bnt_ljbf_03, R.drawable.bnt_bfsz_05, R.drawable.bnt_cfsz_07, R.drawable.bnt_fqsz_15, R.drawable.bnt_bjjl_16 };
		Class<?> menu_jump[] = { DefenceNow.class, DefenceZone.class, UndefenceZone.class, ZoneList.class, WarningList.class };

		// set menu view
		GridView gvMenu = (GridView) findViewById(R.id.gv_menu);
		gvMenu.setAdapter(new MenuAdapter(menu_name, menu_icon, menu_jump));
	}

}
