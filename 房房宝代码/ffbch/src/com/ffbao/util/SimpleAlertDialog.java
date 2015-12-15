package com.ffbao.util;

import java.io.IOException;
import java.util.List;

import org.json.JSONArray;
import org.xmlpull.v1.XmlPullParserException;

import com.ffbao.activity.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources.NotFoundException;
import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
/**
 * 
 * @FileName:SimpleAlertDialog.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:SimpleAlertDialog.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: TODO
 */
public class SimpleAlertDialog
{

	private Context context; // 上下文
	private AlertDialog dlg; // 菜单的窗体
	private String title; // 菜单的标题
	private Window window;
	private LinearLayout content;
	private LinearLayout ButtonLinearLayout;
	private LinearLayout popBackground;
	private LinearLayout popBackgroundTop;
	private TextView txtTitle; // 标题的控件
	private Button btnConfrim;
	private Button btnCancel;
	private ImageView imgLogo; // 图标
	private View spview;

	public SimpleAlertDialog(Context context)
	{
		// TODO Auto-generated constructor stub
		this.context = context;
		this.dlg = new AlertDialog.Builder(context).create();
	}

	/**
	 * 设置标题
	 * @param title
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * 关闭菜单
	 */
	public void close()
	{
		dlg.cancel();
		dlg.dismiss();
	}

	/**
	 * 显示菜单
	 */
	public void show()
	{
		dlg.show();
		window = dlg.getWindow();
		window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
		window.setContentView(R.layout.ffb_pop_alert_dialog);
		WindowManager.LayoutParams lp = window.getAttributes();
		lp.alpha = 1f;
		lp.dimAmount = 0.6f;
		lp.gravity = Gravity.CENTER_HORIZONTAL;
		lp.screenOrientation = Gravity.CENTER_HORIZONTAL;
		window.setAttributes(lp);
		txtTitle = (TextView) window.findViewById(R.id.txtTitle);
		content = (LinearLayout) window.findViewById(R.id.content);
		ButtonLinearLayout = (LinearLayout) window.findViewById(R.id.ButtonLinearLayout);
		popBackground = (LinearLayout) window.findViewById(R.id.popBackground);
		popBackgroundTop = (LinearLayout) window.findViewById(R.id.popBackgroundTop);
		spview = (View) window.findViewById(R.id.spview);
		txtTitle.setText(title);
		btnConfrim = (Button) window.findViewById(R.id.btnOK);
		btnCancel = (Button) window.findViewById(R.id.btnCancel);
		imgLogo = (ImageView) window.findViewById(R.id.imgLogo);
	}

	/**
	 * 设置按钮的可见性, 先调用show()方法
	 * @param visible
	 */
	public void setButtonVisible(boolean visible)
	{
		ButtonLinearLayout.setVisibility(View.GONE);
	}

	/**
	 * 设置成为棕色, 先调用show()方法
	 */
	public void setBrown()
	{
		popBackground.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ffb_pop_alert_background_brown));
		popBackgroundTop.setBackgroundDrawable(context.getResources().getDrawable(R.drawable.ffb_pop_alert_background_top_brown));
		spview.setBackgroundColor(Color.rgb(174, 125, 129));
	}

	/**
	 * 设置Logo图标, 先调用show()方法
	 * @param id
	 */
	public void setIcon(int id)
	{
		imgLogo.setImageResource(id);
	}

	/**
	 * 显示内容，单个文本 先调用show()方法
	 * @param content
	 */
	public void setContent(String content)
	{
		TextView textView = new TextView(context);
		textView.setText(content);
		textView.setTextColor(Color.WHITE);
		this.setContent(textView);
	}

	/**
	 * 设置列表，先调用show()方法
	 * @param titles
	 * @param listener
	 */
	public void setItemsForSubmit(List<String> titles, OnClickListener listener)
	{
		for (int i = 0; i < titles.size(); i++)
		{
			TextView textView = new TextView(context);
			textView.setText(titles.get(i));
			try
			{
				textView.setTextColor(ColorStateList.createFromXml(context.getResources(),
								context.getResources().getXml(R.drawable.ffb_common_textcolor_yellow_white)));
			}
			catch (NotFoundException e)
			{
				e.printStackTrace();
			}
			catch (XmlPullParserException e)
			{
				e.printStackTrace();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			textView.setSingleLine(false);
			textView.setOnClickListener(listener);
			textView.setTag(i);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(5, 10, 5, 10);
			textView.setLayoutParams(lp);
			textView.setTextSize(17);
			this.setContent(textView);

			if (i != titles.size() - 1)
			{
				View view = new View(context);
				view.setBackgroundColor(Color.WHITE);
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, 1);
				view.setLayoutParams(params);
				this.setContent(view);
			}
		}
	}

	/**
	 * 设置列表，先调用show()方法
	 * @param titles
	 * @param listener
	 */
	public void setItemsForBuyerList(JSONArray js_Array, OnClickListener listener)
	{		
		for (int i = 0; i < js_Array.length(); i++)
		{
			TextView textView = new TextView(context);
			try
			{
				textView.setText(js_Array.getJSONObject(i).getString("DisplayName"));
				textView.setTag(js_Array.getJSONObject(i).getString("UserID"));

				textView.setTextColor(ColorStateList.createFromXml(context.getResources(),
								context.getResources().getXml(R.drawable.ffb_common_textcolor_yellow_white)));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			textView.setSingleLine(false);
			textView.setOnClickListener(listener);
			LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
							LinearLayout.LayoutParams.WRAP_CONTENT);
			lp.setMargins(5, 10, 5, 10);
			textView.setLayoutParams(lp);
			textView.setTextSize(17);
			this.setContent(textView);

			if (i != js_Array.length() - 1)
			{
				View view = new View(context);
				view.setBackgroundColor(Color.WHITE);
				LayoutParams params = new LayoutParams(LayoutParams.FILL_PARENT, 1);
				view.setLayoutParams(params);
				this.setContent(view);
			}
		}
	}

	/**
	 * 显示内容 先调用show()方法
	 * @param content
	 */
	public void setContent(View child)
	{
		this.content.addView(child);
		dlg.show();
	}

	/**
	 * 确认按钮 先调用show()方法
	 * @param text
	 * @param listener
	 */
	public void setPositiveButton(CharSequence text, OnClickListener listener)
	{
		btnConfrim.setText(text);
		btnConfrim.setOnClickListener(listener);
	}

	/**
	 * 取消按钮
	 * @param text
	 * @param listener
	 */
	public void setNegativeButton(CharSequence text, OnClickListener listener)
	{
		btnCancel.setText(text);
		if (listener == null)
		{
			btnCancel.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View v)
				{
					// TODO Auto-generated method stub
					close();
				}
			});
		}
		else
		{
			btnCancel.setOnClickListener(listener);
		}
	}
}
