package com.ffbao.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ffbao.APP;
import com.ffbao.activity.R;
import com.ffbao.net.HttpClientRequest;
import com.ffbao.ui.widget.RoundImageView;
import com.ffbao.util.Constant;
import com.ffbao.util.ExecuteJSONUtils;
import com.ffbao.util.ExitActivity;
import com.ffbao.util.RichfitAlertDialog;
import com.ffbao.util.SharedPrefConstance;
import com.ffbao.util.ToastUtils;
import com.ffbao.util.UpdateImage;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.bitmap.BitmapDisplayConfig;
import com.lidroid.xutils.bitmap.callback.BitmapLoadCallBack;
import com.lidroid.xutils.bitmap.callback.BitmapLoadFrom;
import com.loopj.android.http.AsyncHttpResponseHandler;

/**
 * 
 * @FileName:SettingInfoActivity.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SettingInfoActivity.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author:
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 个人资料
 */
public class SettingInfoActivity extends BaseActivity implements
OnClickListener {

	private LinearLayout infoAvatar;// activity_info_avatar
	private RelativeLayout infoName;// activity_info_name
	private LinearLayout infoGender;// activity_info_gender
	// private RelativeLayout infoSignature;// activity_info_signature
	private LinearLayout infoPhone;// activity_info_phone
	private RelativeLayout infoStore;// activity_info_store
	private LinearLayout infoPassword;// activity_info_password
	private LinearLayout QRcode;// activity_info_password

	private LinearLayout addupdbank;//修改和添加银行卡
	private RoundImageView infoHeader;// activity_info_password
	private ImageView infoHeader1;// activity_info_password

	private TextView username;
	private TextView gener;
	private TextView phone;
	private TextView store;
	private TextView left_info_tag;

	private String selectbank;

	private TextView select_bank;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);
		setContentView(R.layout.ffb_activity_setting_info);
		setActionBarTitle("个人资料");
		initView();
		ExitActivity.addActivity(this);

	}

	private void initView() {

		infoAvatar = (LinearLayout) findViewById(R.id.activity_info_avatar);
		infoName = (RelativeLayout) findViewById(R.id.activity_info_name);
		infoGender = (LinearLayout) findViewById(R.id.activity_info_gender);
		// infoSignature = (RelativeLayout)
		// findViewById(R.id.activity_info_signature);
		infoPhone = (LinearLayout) findViewById(R.id.activity_info_phone);
		infoStore = (RelativeLayout) findViewById(R.id.activity_info_store);
		infoPassword = (LinearLayout) findViewById(R.id.activity_info_password);
		QRcode = (LinearLayout) findViewById(R.id.activity_info_qrcode);

		addupdbank = (LinearLayout) findViewById(R.id.addupdbank);
		select_bank=(TextView) findViewById(R.id.select_bank);

		infoHeader = (RoundImageView) findViewById(R.id.activity_info_head);
		infoHeader1 = (ImageView) findViewById(R.id.activity_info_head1);

		username = (TextView) findViewById(R.id.activity_info_name_text);
		gener = (TextView) findViewById(R.id.activity_info_gender_text);
		phone = (TextView) findViewById(R.id.activity_info_phone_text);
		store = (TextView) findViewById(R.id.activity_info_store_text);
		left_info_tag=(TextView) findViewById(R.id.left_info_tag);

		findViewById(R.id.scrollView1).setOnTouchListener(
				new OnTouchListener() {

					@Override
					public boolean onTouch(View v, MotionEvent event) {
						if (ClickApp()) {
							return true;
						}
						return false;
					}
				});
		addupdbank.setOnClickListener(this);
		infoAvatar.setOnClickListener(this);
		infoName.setOnClickListener(this);
		infoGender.setOnClickListener(this);
		// infoSignature.setOnClickListener(this);
		infoPhone.setOnClickListener(this);
		infoStore.setOnClickListener(this);
		infoPassword.setOnClickListener(this);
		QRcode.setOnClickListener(this);
		// initViewValue();

	}

	@Override
	protected void onResume() {
		super.onResume();
		initViewValue();
		//这里来判断现在是有无银行卡的情况0没卡，1有卡
		selectbank=SharedPrefConstance.getStringValue(SettingInfoActivity.this, SharedPrefConstance.isBank);
		select_bank.setText("0".equals(selectbank)?"添加银行卡":"银行卡信息");
	}

	private void initViewValue() {

		String myselfimg = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.myselfimg);
		if (myselfimg != null && myselfimg.length()!=0
				&& !"null".equals(myselfimg)) {
			BitmapUtils utils = new BitmapUtils(this);
			utils.display(infoHeader1, myselfimg,
					new BitmapLoadCallBack<View>() {

				@Override
				public void onLoadCompleted(View arg0, String arg1,
						Bitmap bitmap, BitmapDisplayConfig config,
						BitmapLoadFrom arg4) {

					infoHeader.setImageBitmap(bitmap);
				}

				@Override
				public void onLoadFailed(View arg0, String arg1,
						Drawable arg2) {
					// ToastUtils.showToast(SettingInfoActivity.this,
					// "头像获取失败");

				}

			});
		}

		String name = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.myselfname, "");
		String myPhone = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.myselfphone, "");
		String sex = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.sex, "");
		String companyName = SharedPrefConstance.getStringValue(this,
				SharedPrefConstance.companyname, "");
		// String companyID = SharedPrefConstance.getStringValue(this,
		// SharedPrefConstance.companyid, "");
		username.setText(name);
		gener.setText(sex);
		phone.setText(myPhone);
		// if (companyID.length() > 4) {
		// store.setText("[" + companyID + "] " + companyName);
		// store.setText(companyName);
		// }else{
		if(companyName.equals(APP.independentname)){
			left_info_tag.setText("绑定门店");
			store.setText("");
		}else {
			store.setText(companyName);
		}

		// }
	}

	@Override
	public void onClick(View view) {
		if (!ClickApp()) {
			return;
		}
		Intent intent = new Intent();
		switch (view.getId()) {
		case R.id.activity_info_avatar:
			if (dialog == null || !dialog.isShow()) {
				ShowPickDialog();
			}
			break;
		case R.id.activity_info_name:

			intent.setClass(SettingInfoActivity.this, ChangeNameActivity.class);
			startActivity(intent);
			break;
		case R.id.activity_info_gender:

			intent.setClass(SettingInfoActivity.this, ChangeGenerActivity.class);
			startActivity(intent);
			break;
		case R.id.activity_info_signature:

			intent.setClass(SettingInfoActivity.this,
					ChangeServiceActivity.class);
			startActivity(intent);
			break;
		case R.id.activity_info_phone:

			intent.setClass(SettingInfoActivity.this, ChangePhoneActivity.class);
			startActivity(intent);
			break;
		case R.id.activity_info_store:

			intent.setClass(SettingInfoActivity.this,
					ChangecompanyActivity.class);
			startActivity(intent);
			break;
		case R.id.activity_info_password:

			intent.setClass(SettingInfoActivity.this, ChangePWDActivity.class);
			startActivity(intent);
			break;
			//添加和修改银行卡
		case R.id.addupdbank:
			intent.setClass(SettingInfoActivity.this, AddUpdbankActivity.class);
			if("1".equals(selectbank)){
				intent.putExtra("type", "1");
			}else {
				intent.putExtra("type", "0");
			}

			startActivity(intent);

			break;
		case R.id.activity_info_qrcode:

			intent.setClass(SettingInfoActivity.this, MyQRCodeActivity.class);
			startActivity(intent);
			break;

		default:
			break;
		}
	}

	private void ShowPickDialog() {
		if (dialog == null) {
			dialog = new RichfitAlertDialog(this);
		}
		if (!dialog.isShow()) {
			dialog.show();
			dialog.setTitle("提示");
			dialog.setContent("设置头像");
			dialog.setPositiveButton("相册", new OnClickListener() {

				@Override
				public void onClick(View v) {

					dialog.close();
					Intent intent = new Intent(Intent.ACTION_PICK, null);
					intent.setDataAndType(
							MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
							"image/*");
					startActivityForResult(intent, 1);
				}
			});
			dialog.setNegativeButton("拍照", new OnClickListener() {

				@Override
				public void onClick(View v) {

					dialog.close();
					if (!Environment.getExternalStorageState().equals(
							android.os.Environment.MEDIA_MOUNTED)) {
						Toast.makeText(SettingInfoActivity.this,
								"您的手机没有SD卡，请安装SD卡后再操作s", Toast.LENGTH_LONG)
								.show();
					} else {
						doTakePhoto();
					}
				}
			});
		}
	}

	private static final File PHOTO_DIR = new File(
			Environment.getExternalStorageDirectory() + "/DCIM/Camera");
	private File mCurrentPhotoFile;
	/*** The launch code when taking a picture */
	private static final int CAMERA_WITH_DATA = 2;
	private AlertDialog show;
	private RichfitAlertDialog dialog;

	/***
	 * Create a file name for the icon photo using current time.����ͼƬ���
	 */
	private String getPhotoFileName() {
		return Constant.headImageName + ".jpg";
	}

	/***
	 * Launches Camera to take a picture and store it in a file.
	 */
	protected void doTakePhoto() {
		try {

			PHOTO_DIR.mkdirs();
			mCurrentPhotoFile = new File(PHOTO_DIR, getPhotoFileName());
			mCurrentPhotoFile.delete();
			final Intent intent = getTakePickIntent(mCurrentPhotoFile);
			startActivityForResult(intent, CAMERA_WITH_DATA);
		} catch (ActivityNotFoundException e) {
			Toast.makeText(this, "not find photo", Toast.LENGTH_LONG).show();
		}
	}

	/***
	 * Constructs an intent for capturing a photo and storing it in a temporary
	 * file.
	 */
	public static Intent getTakePickIntent(File f) {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE, null);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
		return intent;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		switch (requestCode) {
		case 1:

			if (data != null)
				startPhotoZoom(data.getData());
			break;
		case 2:
			// File temp = new File(getFile() + File.separator + "richfit" +
			// File.separator + "QiXin" + File.separator + "avatar" +
			// File.separator
			// + headImageName);
			// File temp = new File(PHOTO_DIR, getPhotoFileName());
			if (mCurrentPhotoFile != null && mCurrentPhotoFile.exists())
				startPhotoZoom(Uri.fromFile(mCurrentPhotoFile));
			break;
		case 3:
			if (data != null)
				setPicToView(data);
			break;
		default:
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	/**
	 * 
	 * @param uri
	 */
	public void startPhotoZoom(Uri uri) {
		Intent intent = new Intent("com.android.camera.action.CROP");
		intent.setDataAndType(uri, "image/*");
		intent.putExtra("crop", "true");
		intent.putExtra("aspectX", 1);
		intent.putExtra("aspectY", 1);
		intent.putExtra("outputX", 64);
		intent.putExtra("outputY", 64);
		intent.putExtra("return-data", true);
		startActivityForResult(intent, 3);
	}

	/**
	 * @param picdata
	 */
	private void setPicToView(Intent picdata) {
		Bundle extras = picdata.getExtras();
		if (HttpClientRequest.checkNetworkStatus(context)) {
			if (extras != null) {
				Bitmap photo = extras.getParcelable("data");
				if (photo != null) {
					Bitmap photo2 = compressImage(photo);
					saveBitmap(photo2);
					infoHeader.setImageBitmap(null);
					ByteArrayOutputStream stream = new ByteArrayOutputStream();
					photo2.compress(Bitmap.CompressFormat.JPEG, 100, stream);
					infoHeader.setImageBitmap(photo2);
					// UpdateImage.setUpdateImage(this, "test", photo2, null);
					// File f = new File(getFile() + File.separator + "richfit"
					// + File.separator + "QiXin" + File.separator + "avatar",
					// headImageName);
					File f = new File(getFile() + File.separator
							+ Constant.fileName + File.separator + "avatar",
							Constant.headImageName);
					UpdateImage.uploadFile(f, this,
							new AsyncHttpResponseHandler() {
						@Override
						public void onStart() {
							super.onStart();

						}

						@Override
						public void onSuccess(String arg0) {
							super.onSuccess(arg0);
							// getMyselfImg

							try {
								if (ExecuteJSONUtils.getMyselfImg(
										context, arg0)) {
									String myselfimg = SharedPrefConstance
											.getStringValue(
													context,
													SharedPrefConstance.myselfimg);
									if (myselfimg != null
											&& myselfimg.length()!=0
											&& !"null"
											.equals(myselfimg)) {
										BitmapUtils utils = new BitmapUtils(
												context);
										utils.display(
												infoHeader1,
												myselfimg,
												new BitmapLoadCallBack<View>() {

													@Override
													public void onLoadCompleted(
															View arg0,
															String arg1,
															Bitmap bitmap,
															BitmapDisplayConfig config,
															BitmapLoadFrom arg4) {

														infoHeader
														.setImageBitmap(bitmap);
													}

													@Override
													public void onLoadFailed(
															View arg0,
															String arg1,
															Drawable arg2) {
														ToastUtils
														.showToast(
																SettingInfoActivity.this,
																"头像获取失败");

													}

												});
									}
								}
							} catch (Exception e) {
								e.printStackTrace();
							}
						}

						@Override
						public void onFailure(Throwable arg0,
								String arg1) {
							super.onFailure(arg0, arg1);

						}
					});

				}
			}
		} else {
			// ToastUtils.showToast(context, "网络不可用");
		}
	}

	private static File getFile() {
		File file = Environment.getExternalStorageDirectory();
		return file;
	}

	public static void saveBitmap(Bitmap bitmap) {
		File path = new File(getFile() + File.separator + Constant.fileName
				+ File.separator + "avatar");
		if (!path.exists()) {
			path.mkdirs();
		}
		File f = new File(getFile() + File.separator + Constant.fileName
				+ File.separator + "avatar", Constant.headImageName);
		if (f.exists()) {
			f.delete();
		}
		try {
			FileOutputStream out = new FileOutputStream(f);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
			out.flush();
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private static Bitmap compressImage(Bitmap image) {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		image.compress(Bitmap.CompressFormat.JPEG, 100, baos);// ����ѹ������������100��ʾ��ѹ������ѹ�������ݴ�ŵ�baos��
		int options = 100;
		while (baos.toByteArray().length / 1024 > 8) { // ѭ���ж����ѹ����ͼƬ�Ƿ����8kb,���ڼ���ѹ��
			baos.reset();// ����baos�����baos
			image.compress(Bitmap.CompressFormat.JPEG, options, baos);// ����ѹ��options%����ѹ�������ݴ�ŵ�baos��
			options -= 10;// ÿ�ζ�����10
		}
		ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());// ��ѹ��������baos��ŵ�ByteArrayInputStream��
		Bitmap bitmap = BitmapFactory.decodeStream(isBm, null, null);// ��ByteArrayInputStream������ͼƬ
		return bitmap;
	}

}
