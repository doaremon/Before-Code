package com.ffbao.ui.widget.time;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import com.ffbao.activity.R;

import android.R.integer;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Toast;


public class WheelMain {

	private View view;
	private WheelView wv_year;
	private WheelView wv_month;
	private WheelView wv_day;
	private WheelView wv_hours;
	private WheelView wv_mins;
	public int screenheight;
	private boolean hasSelectTime;
	private static int START_YEAR = 1990, END_YEAR = 2100;

	public boolean isHasSelectTime() {
		return hasSelectTime;
	}

	public void setHasSelectTime(boolean hasSelectTime) {
		this.hasSelectTime = hasSelectTime;
	}
	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
	}

	public static int getSTART_YEAR() {
		return START_YEAR;
	}

	public static void setSTART_YEAR(int sTART_YEAR) {
		START_YEAR = sTART_YEAR;
	}

	public static int getEND_YEAR() {
		return END_YEAR;
	}

	public static void setEND_YEAR(int eND_YEAR) {
		END_YEAR = eND_YEAR;
	}

	public WheelMain(View view) {
		super();
		this.view = view;
		hasSelectTime = true;
		setView(view);
	}
	public WheelMain(View view,boolean hasSelectTime) {
		super();
		this.view = view;
		this.hasSelectTime = hasSelectTime;
		setView(view);
	}
	public void initDateTimePicker(int year ,int month,int day){
		this.initDateTimePicker(year, month, day, 0, 0, 0);
	}
	public void initDateTimePicker(int year ,int month,int day,int week){
		this.initDateTimePicker(year, month, day, 0, 0,week);
	}

	/**
	 * @Description: TODO 弹出日期时间选择器
	 */
	public void initDateTimePicker(int year ,int month ,int day,int h,int m,int week) {

		// 添加大小月月份并将其转换为list,方便之后的判断
		String[] months_big = { "1", "3", "5", "7", "8", "10", "12" };
		String[] months_little = { "4", "6", "9", "11" };
		//星期显示
		String[] week_string ={"SUNDAY","MONDAY","TUESDAY","WEDNESDAY","THURSDAY", "FRIDAY", "SATURDAY"};
		String[] week_sampleString ={"SUN","MON","TUES","WEDNES","THURS", "FRI", "SATUR"};
		String[] week_cycle ={"1","2","3","4","5", "6", "7"};

		final  List<String> list_week_cycle = Arrays.asList(week_cycle);
		final  List<String> list_week_sampleString = Arrays.asList(week_sampleString);
		final  List<String> list_week_string_ = Arrays.asList(week_string);


		final List<String> list_big = Arrays.asList(months_big);
		final List<String> list_little = Arrays.asList(months_little);

		wv_year = (WheelView) view.findViewById(R.id.year);
		wv_month = (WheelView) view.findViewById(R.id.month);
		wv_day = (WheelView) view.findViewById(R.id.day);
		wv_hours = (WheelView)view.findViewById(R.id.hour);
		wv_mins = (WheelView)view.findViewById(R.id.min);
//		wv_hours.setLabel("时");
//		wv_mins.setLabel("分");
		wv_year.setAdapter(new NumericWheelAdapter(START_YEAR, END_YEAR));// 设置"年"的显示数据
		wv_year.setCyclic(true);// 可循环滚动
		wv_year.setCurrentItem(year - START_YEAR);// 初始化时显示的数据
		wv_year.setLabel("年");
		wv_month.setAdapter(new NumericWheelAdapter(1, 12));
		wv_month.setCyclic(true);
		wv_month.setCurrentItem(month-1);
		wv_month.setLabel("月");
		wv_day.setCyclic(true);
		wv_day.setLabel("日");
		// 判断大小月及是否闰年,用来确定"日"的数据
		if (list_big.contains(String.valueOf(month))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 31));
		} else if (list_little.contains(String.valueOf(month ))) {
			wv_day.setAdapter(new NumericWheelAdapter(1, 30));
		} else {
			// 闰年
			if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0)
				wv_day.setAdapter(new NumericWheelAdapter(1, 29));
			else
				wv_day.setAdapter(new NumericWheelAdapter(1, 28));
		}
		wv_day.setCurrentItem(day-1);

		// 添加"年"监听
		OnWheelChangedListener wheelListener_year = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int year_num = newValue + START_YEAR;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big
						.contains(String.valueOf(wv_month.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
				} else if (list_little.contains(String.valueOf(wv_month
						.getCurrentItem() + 1))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
				} else {
					if ((year_num % 4 == 0 && year_num % 100 != 0)
							|| year_num % 400 == 0)
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
					else
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
				}
			}
		};
		// 添加"月"监听
		OnWheelChangedListener wheelListener_month = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
				int month_num = newValue + 1;
				int dayNum =wv_day.getCurrentItem();
				int bigNum =0;
				// 判断大小月及是否闰年,用来确定"日"的数据
				if (list_big.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 31));
					bigNum =31;
				} else if (list_little.contains(String.valueOf(month_num))) {
					wv_day.setAdapter(new NumericWheelAdapter(1, 30));
					bigNum = 30;
				} else {
					if (((wv_year.getCurrentItem() + START_YEAR) % 4 == 0 && (wv_year
							.getCurrentItem() + START_YEAR) % 100 != 0)
							|| (wv_year.getCurrentItem() + START_YEAR) % 400 == 0){
						wv_day.setAdapter(new NumericWheelAdapter(1, 29));
						bigNum = 29;
					}
					else{
						wv_day.setAdapter(new NumericWheelAdapter(1, 28));
						bigNum = 28;
					}
				}
				wv_day.setCurrentItem(setCurrentDay(dayNum,bigNum));
			}
		};

		// 添加"日"监听
		OnWheelChangedListener wheelListener_day = new OnWheelChangedListener() {
			public void onChanged(WheelView wheel, int oldValue, int newValue) {
//				Calendar calendar = Calendar.getInstance();
//				calendar.set((wv_year.getCurrentItem() + START_YEAR),
//						(wv_month.getCurrentItem() + 1),
//						(wv_day.getCurrentItem() + 1));
//				calendar.get(Calendar.DAY_OF_WEEK);
//				Toast.makeText(view.getContext(), ""+calendar.get(Calendar.DAY_OF_WEEK), 1).show();
			}
		};
		wv_year.addChangingListener(wheelListener_year);
		wv_month.addChangingListener(wheelListener_month);
		wv_day.addChangingListener(wheelListener_day);


		if(hasSelectTime){
			wv_hours.setVisibility(View.VISIBLE);
			wv_mins.setVisibility(View.VISIBLE);

			wv_hours.setAdapter(new NumericWheelAdapter(0, 23));
			wv_hours.setCyclic(true);// 可循环滚动
			wv_hours.setCurrentItem(h);


			wv_mins.setAdapter(new NumericWheelAdapter(0, 59));
			wv_mins.setCyclic(true);// 可循环滚动
			wv_mins.setCurrentItem(m);
		}else{
			wv_hours.setVisibility(View.GONE);
			wv_mins.setVisibility(View.GONE);
		}

		// 根据屏幕密度来指定选择器字体的大小(不同屏幕可能不同)
		int textSize = 0;
		//让时分滚轮正常显示。
//		if(hasSelectTime)
//			textSize = (screenheight / 100) * 3;
//		else
			textSize = (screenheight / 100) * 4;

		wv_day.TEXT_SIZE = textSize;
		wv_month.TEXT_SIZE = textSize;
		wv_year.TEXT_SIZE = textSize;
		wv_hours.TEXT_SIZE = textSize;
		wv_mins.TEXT_SIZE = textSize;
	}

	private int  setCurrentDay(int oldDay,int bigDay){
		oldDay = oldDay+1;
		if(oldDay >bigDay){
			return bigDay - 1;
		}else{
			return oldDay - 1;
		}
	}

	public String getTime() {
		StringBuffer sb = new StringBuffer();
		sb.append((wv_year.getCurrentItem() + START_YEAR)).append("-");
		addZERO(sb,wv_month);
		sb.append((wv_month.getCurrentItem() + 1)).append("-");
		addZERO(sb,wv_day);
		sb.append((wv_day.getCurrentItem() + 1));
		if(hasSelectTime){
			sb.append(" ");
			addZERO(sb,wv_hours);
			sb.append(wv_hours.getCurrentItem()).append(":");
			addZERO(sb,wv_mins);
			sb.append(wv_mins.getCurrentItem());
			sb.append(":00");
		}
		return sb.toString();
	}

	private StringBuffer  addZERO(StringBuffer sb,WheelView view){
		if((view.getCurrentItem() + 1) <10){
			sb.append(0);
		}
		return sb;
	}
}
