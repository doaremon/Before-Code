package com.ffbao.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.app.Activity;
import android.app.Dialog;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * 
 * @FileName:ViewHelper.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:ViewHelper.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 根据注解  初始化 控件
 */
public class ViewHelper {
	/**
	 * ��ʼ������
	 * 
	 * @param activity
	 */
	public static void init(Object object) {
		init(object, null);
	}

	/**
	 * ��ʼ��fragment
	 */
	public static void init(Object object, View view) {
//		initTitle(object);
		initView(object, view);
//		initClick(object);
	}

	/**
	 * ��ʼ���ؼ�����¼�
	 * 
	 * @param object
	 */
//	private static void initClick(Object object) {
//		if (null != object && object instanceof OnClickListener) {
//			ViewClick viewClick = object.getClass().getAnnotation(
//					ViewClick.class);
//			if (null != viewClick && null != viewClick.ids()
//					&& 0 < viewClick.ids().length) {
//				int[] ids = viewClick.ids();
//				View findView = null;
//				if (object instanceof Activity) {
//					for (int i = 0; i < ids.length; i++) {
//						findView = ((Activity) object).findViewById(ids[i]);
//						if (null != findView) {
//							setOnClickListener(object, findView);
//						}
//					}
//				} else if (object instanceof Dialog) {
//					for (int i = 0; i < viewClick.ids().length; i++) {
//						findView = ((Dialog) object).findViewById(ids[i]);
//						if (null != findView) {
//							setOnClickListener(object, findView);
//						}
//					}
//				}
//			}
//		}
//	}

	/**
	 * ��ʼ��������
	 * 
	 * @param activity
	 */
//	private static void initTitle(Object object) {
//		if (object instanceof Activity) {
//			Activity activity = (Activity) object;
//			NavigationBar navigationBar = activity.getClass().getAnnotation(
//					NavigationBar.class);
//			if (null != navigationBar) {
//				// ���ñ���
//				View view = activity.findViewById(R.id.navigation_back);
//				if (-1 != navigationBar.title()) {
//					if (null != view && view instanceof TextView) {
//						((TextView) view).setText(navigationBar.title());
//					}
//				}
//				// ���ñ������¼�
//				if (navigationBar.click() && null != view) {
//					setOnClickListener(activity, view);
//				}
//				// �����ұ߲˵�ͼƬ
//				if (-1 != navigationBar.resId()) {
//					View menuItem = activity
//							.findViewById(R.id.iv_navigation_menu);
//					if (null != menuItem && menuItem instanceof ImageView) {
//						((ImageView) menuItem).setImageResource(navigationBar
//								.resId());
//						// �����ұ߲˵�����¼�
//						if (navigationBar.leftClick()) {
//							setOnClickListener(activity, menuItem);
//						}
//					}
//				}
//			}
//		}
//	}

	/**
	 * 
	 * @Deprecatred:
	 * @param object
	 * @param view
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:初始化控件
	 */
	private static void initView(Object object, View view) {
		for (Field field : object.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			ID id = field.getAnnotation(ID.class);
			if (null != id) {
				// ���ÿؼ�id
				if (0 != id.resId()) {
					try {
						View findView = null;
						if (object instanceof Activity) {
							// activity
							findView = ((Activity) object).findViewById(id
									.resId());
							System.out.println(findView);
						} else if (object instanceof Dialog) {
							// �Ի���---
							findView = ((Dialog) object).findViewById(id
									.resId());
						} else if (null != view) {
							// view---����fragment/�������������
							findView = view.findViewById(id.resId());
						}
						if (null != findView) {
							field.set(object, findView);
//							if (id.click() && object instanceof OnClickListener) {
//								Method method = findView.getClass().getMethod(
//										"setOnClickListener",
//										OnClickListener.class);
//								if (null != method) {
//									method.invoke(findView, object);
//								}
//							}
//
						}
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
//					} catch (InvocationTargetException e) {
//						e.printStackTrace();
//					} catch (NoSuchMethodException e) {
//						e.printStackTrace();
					}
				}
			}
		}
	}

	/**
	 * ���ÿؼ�onClick�¼�����ǰacivity----activity����ʵ��onclick�ӿڣ�
	 * 
	 * @param activity
	 * @param view
	 * @throws NoSuchMethodException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static void setOnClickListener(Object object, View view) {
		try {
			Method method = view.getClass().getMethod("setOnClickListener",
					OnClickListener.class);
			if (null != method) {
				method.invoke(view, object);
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
