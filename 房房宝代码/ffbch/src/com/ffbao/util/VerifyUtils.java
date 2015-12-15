package com.ffbao.util;

import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

/**
 * 
 * @FileName:VerifyUtils.java
 * @Deprecatred:
 * @CopyRright (c) 2014-ffbmobile1.0.0
 * @File Numbers:VerifyUtils.java
 * @author lee
 * @create Date2014-11-4
 * @Update Author: 
 * @Update Date:
 * @version 1.0.0
 * @Funtion: 验证手机发送验证 限制
 */
public class VerifyUtils {

	public static int changeNum = 1;
	private int changeState = 2;

//	Handler handler = new Handler() {
//
//		@Override
//		public void handleMessage(Message msg) {
//
//			switch (msg.what) {
//			case 1:
//
//				break;
//			case 2:
//
//				break;
//			default:
//				break;
//			}
//			super.handleMessage(msg);
//		}
//	};
	/**
	 * 
	 * @Deprecatred:
	 * @param view
	 * @param handler
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:注册手机号
	 */
	public static void setRegiestValue(final TextView view ,final Handler handler ) {

		view.setEnabled(false);
		final String content = view.getText().toString();
		new Thread() {

			@Override
			public void run() {
				super.run();

				while (Constant.verifyNum > 0) {

					handler.post(new Runnable() {

						@Override
						public void run() {
							view.setText(content + "(" + Constant.verifyNum
									+ ")");
						}
					});
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.post(new Runnable() {

					@Override
					public void run() {
						view.setText(content);
						view.setEnabled(true);
					}
				});

			}

		}.start();
	}
	/**
	 * 
	 * @Deprecatred:
	 * @param view
	 * @param handler
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:修改手机号
	 */
	public static void setChangePhoneValue(final TextView view ,final Handler handler ) {
		
		view.setEnabled(false);
		final String content = view.getText().toString();
		new Thread() {
			
			@Override
			public void run() {
				super.run();
				
				while (Constant.verifyNum > 0) {
					
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							view.setText(content + "(" + Constant.verifyNum
									+ ")");
						}
					});
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						view.setText(content);
						view.setEnabled(true);
					}
				});
				
			}
			
		}.start();
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param view
	 * @param handler
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:忘记密码
	 */
	public static void setFotgetValue(final TextView view ,final Handler handler ) {
		
		view.setEnabled(false);
		final String content = view.getText().toString();
		new Thread() {
			
			@Override
			public void run() {
				super.run();
				
				while (Constant.verifyNum > 0) {
					
					handler.post(new Runnable() {
						
						@Override
						public void run() {
							view.setText(content + "(" + Constant.verifyNum
									+ ")");
						}
					});
					try {
						sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						view.setText(content);
						view.setEnabled(true);
					}
				});
				
			}
			
		}.start();
	}
	
	/**
	 * 
	 * @Deprecatred:
	 * @param view
	 * @param handler
	 * @param type
	 * @date:2014-11-4
	 * @author:lee
	 * @Funtion:设置界面，获取验证码
	 */
	public static void setActivityValue(final TextView view ,final Handler handler ,final int type) {
		
		view.setEnabled(false);
		final String content = view.getText().toString();
		new Thread() {
			
			@Override
			public void run() {
				super.run();
				
				control(view, handler, content , type);
				handler.post(new Runnable() {
					
					@Override
					public void run() {
						view.setText(content);
						view.setEnabled(true);
					}
				});
				
			}

			private void control(final TextView view, final Handler handler,
					final String content ,int type) {
				switch (type) {
				case 1:
					
					while (Constant.RegiestVerifyNum > 0) {
						
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								view.setText(content + "(" + Constant.RegiestVerifyNum
										+ ")");
							}
						});
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				case 3:
					while (Constant.changVerifyNum > 0) {
						
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								view.setText(content + "(" + Constant.changVerifyNum
										+ ")");
							}
						});
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
				case 2:
					while (Constant.forgetVerifyNum > 0) {
						
						handler.post(new Runnable() {
							
							@Override
							public void run() {
								view.setText(content + "(" + Constant.forgetVerifyNum
										+ ")");
							}
						});
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;

				default:
					break;
				}
			}
			
		}.start();
	}

	public static void counter(final int type) {
		
		new Thread() {
			public void run() {

				super.run();
				switch (type) {
				case 1:
					
					while(Constant.RegiestVerifyNum>0){
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Constant.RegiestVerifyNum = Constant.RegiestVerifyNum-1;
					}
					
					break;
				case 2:
					
					while(Constant.forgetVerifyNum>0){
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Constant.forgetVerifyNum = Constant.forgetVerifyNum - 1;
					}
					
					break;
				case 3:
					
					while(Constant.changVerifyNum >0){
						try {
							sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						Constant.changVerifyNum = Constant.changVerifyNum-1;
					}
					
					break;

				default:
					break;
				}
			};
		}.start();
	}
}
