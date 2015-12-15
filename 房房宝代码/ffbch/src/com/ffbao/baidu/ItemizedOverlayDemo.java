package com.ffbao.baidu;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.MKGeneralListener;
import com.baidu.mapapi.map.ItemizedOverlay;
import com.baidu.mapapi.map.MKEvent;
import com.baidu.mapapi.map.MKMapViewListener;
import com.baidu.mapapi.map.MapController;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.OverlayItem;
import com.baidu.mapapi.search.MKSearch;
import com.baidu.mapapi.search.MKSearchListener;
import com.baidu.platform.comapi.basestruct.GeoPoint;
import com.ffbao.activity.R;
import com.ffbao.util.Constant;
import com.ffbao.util.StringUtils;
import com.ffbao.util.ToastUtils;

public class ItemizedOverlayDemo extends Activity {
	private View pop;
	private TextView title;

	protected MapView mapView;
	protected ImageView mapBank;
	protected BMapManager manager;
	protected MapController controller;

	protected int latitude = 0;
	protected int longitude = 0;

	protected MKSearch search;
	protected MKSearchListener listener;
	private String cityid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		initManager();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ffb_activity_map_of_baidu);
		String latitude = getIntent().getStringExtra(Constant.latitude);
		String longitude = getIntent().getStringExtra(Constant.longitude);
		cityid = getIntent().getStringExtra(Constant.cityid);
		if (StringUtils.isNull(latitude) && StringUtils.isNull(longitude)) {
			this.latitude = (int) (Double.parseDouble(latitude) * 1E6);
			this.longitude = (int) (Double.parseDouble(longitude) * 1E6);
		} else {
			finish();
			ToastUtils.showErrorToast(this, "坐标没有进行维护");
		}
		init();
		initController();

		search();

		overlay();
		initPop();
	}

	private void search() {
		search = new MKSearch();
	}

	private void initController() {
		controller = mapView.getController();
		controller.setZoom(12);
		controller.enableClick(true);
	}

	private void init() {
		mapView = (MapView) findViewById(R.id.bmapView);
		mapBank = (ImageView) findViewById(R.id.map_bank);
		mapView.setBuiltInZoomControls(true);
		mapView.regMapViewListener(manager, new MKMapViewListener() {

			@Override
			public void onMapMoveFinish() {

			}

			@Override
			public void onMapAnimationFinish() {

			}

			@Override
			public void onGetCurrentMap(Bitmap arg0) {

			}

			@Override
			public void onClickMapPoi(MapPoi poi) {
				if (poi != null) {
					Toast.makeText(getApplicationContext(), poi.strText, 0)
							.show();
				}

			}
		});

		mapBank.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	private void initManager() {
		manager = new BMapManager(getApplicationContext());
		manager.init(Constant.BaiduKey, new MKGeneralListener() {

			@Override
			public void onGetPermissionState(int iError) {
				if (iError == MKEvent.ERROR_PERMISSION_DENIED) {
					Toast.makeText(getApplicationContext(), "验证失败", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onGetNetworkState(int iError) {
				if (iError == MKEvent.ERROR_NETWORK_CONNECT) {
					Toast.makeText(getApplicationContext(), "无网络", Toast.LENGTH_LONG).show();
				}
			}
		});

	}

	private void initPop() {
		pop = View.inflate(getApplicationContext(), R.layout.ffb_pop, null);
		title = (TextView) pop.findViewById(R.id.title);

		pop.setVisibility(View.INVISIBLE);
		LayoutParams params = new MapView.LayoutParams(
				MapView.LayoutParams.WRAP_CONTENT,
				MapView.LayoutParams.WRAP_CONTENT, null,
				MapView.LayoutParams.BOTTOM_CENTER);
		mapView.addView(pop, params);

	}

	private void overlay() {
		ItemizedOverlay<OverlayItem> overlay = new ItemizedOverlay<OverlayItem>(
				getResources().getDrawable(R.drawable.ffb_icon_location), mapView) {
			@Override
			protected boolean onTap(int index) {
				OverlayItem item = getItem(index);

				title.setText(item.getTitle());

				pop.setVisibility(View.INVISIBLE);

				LayoutParams params = new MapView.LayoutParams(
						MapView.LayoutParams.WRAP_CONTENT,
						MapView.LayoutParams.WRAP_CONTENT, item.getPoint(),
						MapView.LayoutParams.BOTTOM_CENTER);
				mapView.updateViewLayout(pop, params);

				pop.setVisibility(View.VISIBLE);

				return super.onTap(index);
			}
		};
		OverlayItem item = new OverlayItem(new GeoPoint(latitude, longitude),
				cityid, "XXX");
		overlay.addItem(item);
		mapView.getOverlays().add(overlay);
		mapView.getController().setCenter(new GeoPoint(latitude, longitude));
		mapView.refresh();
	}
}
