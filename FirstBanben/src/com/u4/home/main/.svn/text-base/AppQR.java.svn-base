package com.u4.home.main;

import java.util.Hashtable;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.u4.home.R;
import com.u4.home.common.Appcontext;
import com.u4.home.common.Base;

public class AppQR extends Base{
	private ImageView app_url,app_mac;
	private int QR_WIDTH=200;
	private int QR_HEIGHT=200;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.frame_0);
		res_top_finish = R.string.phone;
		findId();
	}

	public void findId() {
		// add header
		FrameLayout inc_header = (FrameLayout) findViewById(R.id.inc_header);
		View view_header = inflater.inflate(R.layout.inc_header_finish, null);
		inc_header.addView(view_header, new LayoutParams(inc_header.getLayoutParams().width, inc_header.getLayoutParams().height));

		// add main
		FrameLayout inc_middle = (FrameLayout) findViewById(R.id.inc_middle);
		View view_middle = inflater.inflate(R.layout.appqr, null);
		inc_middle.addView(view_middle, new LayoutParams(inc_middle.getLayoutParams().width, inc_middle.getLayoutParams().height));

		app_url=(ImageView)findViewById(R.id.app_url);
		app_mac=(ImageView)findViewById(R.id.app_mac);

		createImage("http://192.168.1.91:8080/download/u4_android_phone_last.apk", app_url);
		createImage(Appcontext.mainInstance.getDeviceCode(), app_mac);

	}
	// 生成QR图
	private void createImage(String str,ImageView imageView) {
		try {
			// 需要引入core包
			QRCodeWriter writer = new QRCodeWriter();

			String text =str;

			if (text == null || "".equals(text) || text.length() < 1) {
				return;
			}

			// 把输入的文本转为二维码
			BitMatrix martix = writer.encode(text, BarcodeFormat.QR_CODE,
					QR_WIDTH, QR_HEIGHT);

			System.out.println("w:" + martix.getWidth() + "h:"
					+ martix.getHeight());

			Hashtable<EncodeHintType, String> hints = new Hashtable<EncodeHintType, String>();
			hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
			BitMatrix bitMatrix = new QRCodeWriter().encode(text,
					BarcodeFormat.QR_CODE, QR_WIDTH, QR_HEIGHT, hints);
			int[] pixels = new int[QR_WIDTH * QR_HEIGHT];
			for (int y = 0; y < QR_HEIGHT; y++) {
				for (int x = 0; x < QR_WIDTH; x++) {
					if (bitMatrix.get(x, y)) {
						pixels[y * QR_WIDTH + x] = 0xff000000;
					} else {
						pixels[y * QR_WIDTH + x] = 0xffffffff;
					}

				}
			}

			Bitmap bitmap = Bitmap.createBitmap(QR_WIDTH, QR_HEIGHT,
					Bitmap.Config.ARGB_8888);

			bitmap.setPixels(pixels, 0, QR_WIDTH, 0, 0, QR_WIDTH, QR_HEIGHT);
			imageView.setImageBitmap(bitmap);

		} catch (WriterException e) {
			e.printStackTrace();
		}
	}
}
