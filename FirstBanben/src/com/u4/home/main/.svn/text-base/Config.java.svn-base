package com.u4.home.main;

import java.io.File;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;
import com.u4.home.common.XitongDialog;
import com.u4.home.http.AsyncHttpClient;
import com.u4.home.http.JsonHttpResponseHandler;
import com.u4.home.http.RequestParams;

/**
 * 系统设置
 * 
 * @author Administrator
 * 
 */
public class Config extends Base implements OnClickListener {
	private TextView tv_password, tv_phone_one, tv_phone_two, tv_phone_three, tv_ring_warning, tv_ring_call, tv_ring_tip, tv_server, tv_version;
	private RelativeLayout rl_password, rl_phone_one, rl_phone_two, rl_phone_three, rl_volume, rl_ring_warning, rl_ring_call, rl_ring_tip, rl_contrast, rl_server, rl_version,rl_app;
	private XitongDialog dialog;

	private String ringPath = "/ring";
	private ArrayList<Object> ringList;
	private AudioManager audioManager;

	private int debug = 0;
	private RingAdapter adapter = new RingAdapter();
	private MediaPlayer mediaPlayer = null;

	private LayoutInflater inflater;
	private AsyncHttpClient asyncHttpClient = new AsyncHttpClient();

	private String currentItem = "";
	private String conf_ring_call = "", conf_ring_tip = "", conf_ring_warning = "", conf_server_ip = "", conf_server_port = "";
	private int conf_volume_talk = 0, conf_volume_beep = 0, conf_brightness = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.setting;
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		dialog = new XitongDialog(context);

		findId();
	}

	@Override
	protected void onResume() {
		super.onResume();

		serverGetMobile();

		ringList = new ArrayList<Object>();
		if (!Appcontext.basepath.equals("")) {
			getFiles(Appcontext.basepath + ringPath);
		}

		rl_server.setVisibility(Appcontext.debug ? View.VISIBLE : View.GONE);

		conf_ring_call = getConfigString("conf_ring_call");
		conf_ring_tip = getConfigString("conf_ring_tip");
		conf_ring_warning = getConfigString("conf_ring_warning");
		conf_server_ip = getConfigString("conf_server_ip");
		conf_server_port = getConfigString("conf_server_port");

		// showLog("conf_ring_tip=" + conf_ring_tip + ", conf_ring_warning=" +
		// conf_ring_warning + ", conf_server_ip=" + conf_server_ip +
		// ", conf_server_port=" + conf_server_port);

		tv_password.setText(getConfigString("conf_unlock_pwd"));
		tv_ring_call.setText(getFilename(conf_ring_call));
		tv_ring_tip.setText(getFilename(conf_ring_tip));
		tv_ring_warning.setText(getFilename(conf_ring_warning));
		tv_server.setText(conf_server_ip + ":" + conf_server_port);

		tv_version.setText(Appcontext.getVerName(context));
	}

	public void findId() {
		inflater = LayoutInflater.from(context);

		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.config, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		tv_password = (TextView) findViewById(R.id.tv_password);
		tv_phone_one = (TextView) findViewById(R.id.tv_phone_one);
		tv_phone_two = (TextView) findViewById(R.id.tv_phone_two);
		tv_phone_three = (TextView) findViewById(R.id.tv_phone_three);
		tv_ring_warning = (TextView) findViewById(R.id.tv_ring_warning);
		tv_ring_call = (TextView) findViewById(R.id.tv_ring_call);
		tv_ring_tip = (TextView) findViewById(R.id.tv_ring_tip);
		tv_server = (TextView) findViewById(R.id.tv_server);
		tv_version = (TextView) findViewById(R.id.tv_version);

		rl_password = (RelativeLayout) findViewById(R.id.rl_password);
		rl_phone_one = (RelativeLayout) findViewById(R.id.rl_phone_one);
		rl_phone_two = (RelativeLayout) findViewById(R.id.rl_phone_two);
		rl_phone_three = (RelativeLayout) findViewById(R.id.rl_phone_three);
		rl_volume = (RelativeLayout) findViewById(R.id.rl_volume);
		rl_ring_warning = (RelativeLayout) findViewById(R.id.rl_ring_warning);
		rl_ring_call = (RelativeLayout) findViewById(R.id.rl_ring_call);
		rl_ring_tip = (RelativeLayout) findViewById(R.id.rl_ring_tip);
		rl_contrast = (RelativeLayout) findViewById(R.id.rl_contrast);
		rl_server = (RelativeLayout) findViewById(R.id.rl_server);
		rl_version = (RelativeLayout) findViewById(R.id.rl_version);
		rl_app = (RelativeLayout) findViewById(R.id.rl_app);

		rl_password.setOnClickListener(this);
		rl_phone_one.setOnClickListener(this);
		rl_phone_two.setOnClickListener(this);
		rl_phone_three.setOnClickListener(this);
		rl_volume.setOnClickListener(this);
		rl_ring_warning.setOnClickListener(this);
		rl_ring_call.setOnClickListener(this);
		rl_ring_tip.setOnClickListener(this);
		rl_contrast.setOnClickListener(this);
		rl_server.setOnClickListener(this);
		rl_version.setOnClickListener(this);
		rl_app.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		//手机客户端
		case R.id.rl_app:
			Intent intent=new Intent(Config.this, AppQR.class);
			startActivity(intent);
			break;
		case R.id.rl_password:
			dialog.show("password");
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					String str = dialog.et_password.getText().toString();
					setConfig("conf_unlock_pwd", str);
					tv_password.setText(str);
				}
			});
			break;
		case R.id.rl_phone_one:
			dialog.show("mobile");
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					String str = dialog.et_mobile.getText().toString().trim();
					serverSetMobile("1", str);
					tv_phone_one.setText(str);
				}
			});
			break;
		case R.id.rl_phone_two:
			dialog.show("mobile");
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					String str = dialog.et_mobile.getText().toString().trim();
					serverSetMobile("2", str);
					tv_phone_two.setText(str);
				}
			});
			break;
		case R.id.rl_phone_three:
			dialog.show("mobile");
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					String str = dialog.et_mobile.getText().toString().trim();
					serverSetMobile("3", str);
					tv_phone_three.setText(str);
				}
			});
			break;
		case R.id.rl_volume:
			dialog.show("volume");

			conf_volume_talk = audioManager.getStreamVolume(AudioManager.STREAM_VOICE_CALL); // 通话音量
			conf_volume_beep = audioManager.getStreamVolume(AudioManager.STREAM_SYSTEM); // 提示音量

			dialog.sb_volume_talk.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_VOICE_CALL));
			dialog.sb_volume_talk.setProgress(conf_volume_talk);

			dialog.sb_volume_beep.setMax(audioManager.getStreamMaxVolume(AudioManager.STREAM_SYSTEM));
			dialog.sb_volume_beep.setProgress(conf_volume_beep);

			dialog.sb_volume_talk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					conf_volume_talk = progress;
				}
			});
			dialog.sb_volume_beep.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					conf_volume_beep = progress;
				}
			});
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					audioManager.setStreamVolume(AudioManager.STREAM_VOICE_CALL, conf_volume_talk, 0);
					audioManager.setStreamVolume(AudioManager.STREAM_SYSTEM, conf_volume_beep, 0);
				}
			});
			break;
		case R.id.rl_ring_warning:
			currentItem = "ring_warning";
			dialog.show("list");
			dialog.lv_list.setAdapter(adapter);
			dialog.lv_list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					conf_ring_warning = ringList.get(position).toString();
					playRing(conf_ring_warning);
					adapter.notifyDataSetChanged();
				}
			});
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopRing();
					dialog.dismiss();
					if (!conf_ring_warning.equals("")) {
						tv_ring_warning.setText(getFilename(conf_ring_warning));
						setConfig("conf_ring_warning", conf_ring_warning);
					}
				}
			});
			dialog.btn_cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopRing();
					dialog.dismiss();
					conf_ring_warning = getConfigString("conf_ring_warning");
				}
			});
			break;
		case R.id.rl_ring_tip:
			currentItem = "ring_tip";
			dialog.show("list");
			dialog.lv_list.setAdapter(adapter);
			dialog.lv_list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					conf_ring_tip = ringList.get(position).toString();
					playRing(conf_ring_tip);
					adapter.notifyDataSetChanged();
				}
			});
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopRing();
					dialog.dismiss();
					if (!conf_ring_tip.equals("")) {
						tv_ring_tip.setText(getFilename(conf_ring_tip));
						setConfig("conf_ring_tip", conf_ring_tip);
					}
				}
			});
			dialog.btn_cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopRing();
					dialog.dismiss();
					conf_ring_tip = getConfigString("conf_ring_tip");
				}
			});
			break;
		case R.id.rl_ring_call:
			currentItem = "ring_call";
			dialog.show("list");
			dialog.lv_list.setAdapter(adapter);
			dialog.lv_list.setOnItemClickListener(new OnItemClickListener() {
				@Override
				public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
					conf_ring_call = ringList.get(position).toString();
					playRing(conf_ring_call);
					adapter.notifyDataSetChanged();
				}
			});
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopRing();
					dialog.dismiss();
					if (!conf_ring_call.equals("")) {
						tv_ring_call.setText(getFilename(conf_ring_call));
						setConfig("conf_ring_call", conf_ring_call);
					}
				}
			});
			dialog.btn_cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					stopRing();
					dialog.dismiss();
					conf_ring_call = getConfigString("conf_ring_call");
				}
			});
			break;
		case R.id.rl_contrast:
			final ContentResolver resolver = getContentResolver();
			Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
			conf_brightness = Settings.System.getInt(resolver, android.provider.Settings.System.SCREEN_BRIGHTNESS, 255);

			dialog.show("brightness");
			dialog.sb_brightness.setMax(255);
			dialog.sb_brightness.setProgress(conf_brightness);
			dialog.sb_brightness.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
					if (fromUser) {
						int bright = seekBar.getProgress();
						if (20 < bright)
							bright = 20;
						if (bright > 235)
							bright = 235;
						Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, bright); // 0-255
					}
				}

				@Override
				public void onStartTrackingTouch(SeekBar seekBar) {
				}

				@Override
				public void onStopTrackingTouch(SeekBar seekBar) {
				}
			});
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					conf_brightness = Settings.System.getInt(resolver, android.provider.Settings.System.SCREEN_BRIGHTNESS, 255);
				}
			});
			dialog.btn_cancel.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					Settings.System.putInt(resolver, Settings.System.SCREEN_BRIGHTNESS, conf_brightness);
				}
			});
			break;
		case R.id.rl_server:
			dialog.show("server");
			dialog.et_server_ip.setText(Appcontext.conf_server_ip);
			dialog.btn_ok.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					dialog.dismiss();
					conf_server_ip = dialog.et_server_ip.getText().toString().trim();
					conf_server_port = dialog.et_server_port.getText().toString().trim();
					tv_server.setText(conf_server_ip + ":" + conf_server_port);
					setConfig("conf_server_ip", conf_server_ip);
					setConfig("conf_server_port", conf_server_port);
					Appcontext.mainInstance.setServer(conf_server_ip, conf_server_port);
					serverSetIP(Appcontext.myurl);
				}
			});
			break;
		case R.id.rl_version:
			debug++;
			if (debug >= 9) {
				debug = 0;
				Appcontext.debug = !Appcontext.debug;
				Toast.makeText(this, Appcontext.debug ? R.string.debug_open : R.string.debug_close, Toast.LENGTH_SHORT).show();
			}
			rl_server.setVisibility(Appcontext.debug ? View.VISIBLE : View.GONE);
			break;
		default:
			break;
		}
	}

	public class RingAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return ringList.size();
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
			String fn = ringList.get(position).toString();
			convertView = inflater.inflate(R.layout.dialog_radio_adapter, null);
			RadioButton radioButton = (RadioButton) convertView.findViewById(R.id.radioButton);
			radioButton.setText(getFilename(fn));
			if (currentItem.equals("ring_call")) {
				radioButton.setChecked(fn.equals(conf_ring_call));
			} else if (currentItem.equals("ring_tip")) {
				radioButton.setChecked(fn.equals(conf_ring_tip));
			} else if (currentItem.equals("ring_warning")) {
				radioButton.setChecked(fn.equals(conf_ring_warning));
			} else {
				radioButton.setChecked(false);
			}
			return convertView;
		}
	}

	public void playRing(String path) {
		if (path.equals(""))
			return;
		try {
			if (mediaPlayer == null) {
				mediaPlayer = new MediaPlayer();
				mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
				mediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					@Override
					public void onCompletion(MediaPlayer mp) {
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
					}
				});
			}
			if (mediaPlayer.isPlaying())
				mediaPlayer.stop();
			mediaPlayer.reset();
			mediaPlayer.setDataSource(path);
			mediaPlayer.prepareAsync();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopRing() {
		if (mediaPlayer == null)
			return;
		if (mediaPlayer.isPlaying())
			mediaPlayer.stop();
		mediaPlayer.reset();
		mediaPlayer.release();
		mediaPlayer = null;
	}

	private String getConfigString(String key) {
		if (key.equals(""))
			return "";
		return Appcontext.preferences.getString(key, "");
	}

	private void setConfig(String key, String value) {
		SharedPreferences.Editor editor = Appcontext.preferences.edit();
		editor.putString(key, value);
		editor.commit();
	}

	private void getFiles(String path) {
		File file = new File(path);
		if (!file.exists())
			file.mkdir();
		File[] files = file.listFiles();
		for (File filenames : files) {
			String fileName = filenames.getName();
			if (fileName.endsWith(".mp3") || fileName.endsWith(".ogg")) {
				ringList.add(path + "/" + fileName);
			}
		}
	}

	private String getFilename(String path) {
		int start = path.lastIndexOf("/");
		int end = path.lastIndexOf(".");
		if (start != -1 && end != -1) {
			return path.substring(start + 1, end);
		}
		return null;
	}

	/**
	 * 获取绑定手机号码
	 */
	public void serverGetMobile() {
		RequestParams params = new RequestParams();
		params.put("c", "getmobile");
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				try {
					if (1 == response.getInt("status")) {
						JSONArray data = response.getJSONArray("data");
						tv_phone_one.setText(data.getJSONObject(0).getString("mobile"));
						tv_phone_two.setText(data.getJSONObject(1).getString("mobile"));
						tv_phone_three.setText(data.getJSONObject(2).getString("mobile"));
					} else {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * 设置绑定手机号码
	 * 
	 * @param index
	 * @param mobile
	 */
	public void serverSetMobile(String index, String mobile) {
		RequestParams params = new RequestParams();
		params.put("c", "setmobile");
		params.put("index", index);
		params.put("mobile", mobile);
		asyncHttpClient.get(Appcontext.myurl, params, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(JSONObject response) {
				super.onSuccess(response);
				try {
					if (1 != response.getInt("status")) {
						showToast(response.getString("message"));
					}
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		});
	}
}
