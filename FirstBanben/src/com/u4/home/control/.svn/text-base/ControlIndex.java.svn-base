package com.u4.home.control;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.GridView;

import com.u4.home.R;
import com.u4.home.common.Base;

public class ControlIndex extends Base {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_2);
		res_top_finish = R.string.control_main;
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
		int menu_name[] = { R.string.control_light, R.string.control_switch, R.string.control_curtain, R.string.control_infrared, R.string.control_theme };
		int menu_icon[] = { R.drawable.bnt_de_03, R.drawable.bnt_ka_05_a, R.drawable.bnt_ch_07, R.drawable.bnt_remote, R.drawable.bnt_yy_15 };
		Class<?> menu_jump[] = { Light.class, Switch.class, Curtain.class, Infrared.class, Theme.class };

		// set menu view
		GridView gvMenu = (GridView) findViewById(R.id.gv_menu);
		gvMenu.setAdapter(new MenuAdapter(menu_name, menu_icon, menu_jump));
	}

}
