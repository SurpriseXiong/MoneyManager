package com.gress.moneymanager;

import java.util.Calendar;

import com.book.utils.ToastUtils;
import com.money.broadcastreceiver.AlarmReceiver;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.widget.TextView;
import android.widget.TimePicker;


public class WarnActivity extends Activity {
   private com.book.utils.FunSwitch box;
   private TextView warn_show;  //显示时间
   private int mfen = 0;  //获取分
   private int hour = 0;	 //获取时
   private NotificationManager nm;  //通知栏
   private Calendar calendar;  //获取时间
 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_warn);
		initView();
		inData();
		initListen();
	}
	public void submit(View view){
		  /*
         *    NotificationManager 通知栏实现
         * */
		  Notification.Builder builder = new Notification.Builder(this);  
	        //小图标  
	        builder.setSmallIcon(R.drawable.ic_launcher);  
	        //主标题  
	        builder.setContentTitle("	闹钟设置成功");  
	        //内容  
	        builder.setContentText(" 记账提示将在"+hour+" : "+mfen+" 提醒");  
	        //提示的信息  
	        builder.setTicker("快播后台下载成功     100%");  
	        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);  
	        //右下的小图标  
	       // builder.setLargeIcon(bitmap);  
	        //时间设置  
	        builder.setWhen(System.currentTimeMillis());  
	        //右下的新闻  
	        builder.setContentInfo("闹钟");  
	        //声音  
	      //  builder.setDefaults(Notification.DEFAULT_ALL);
	        builder.setDefaults(DEFAULT_KEYS_DIALER);  
	        Uri uri = Uri.parse("http://c2.1024mx.me/pw/");
	        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
	       // Intent intent = new Intent(this,LoginActivity.class);  
	        //PendingIntent延迟意图  
	        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);  
	        //设置跳转  
	        builder.setContentIntent(pendingIntent);  
	        //点击后将消息取消  
	        builder.setAutoCancel(true);  
	        //开机到现在的时间  
	        //SystemClock.currentThreadTimeMillis();  
	        //创建notification通知  
	        Notification notification = builder.build();  
	        //显示通知，id，通知  
	        nm.notify(1, notification); 
	        
	       /* 
	         * 闹钟实现
	         * 
	        Calendar mCalendar = Calendar.getInstance();
	        mCalendar.setTimeInMillis(System.currentTimeMillis());

	        //获取当前毫秒值
	        long systemTime = System.currentTimeMillis();

	        //是设置日历的时间，主要是让日历的年月日和当前同步
	        mCalendar.setTimeInMillis(System.currentTimeMillis());
	        // 这里时区需要设置一下，不然可能个别手机会有8个小时的时间差
	        mCalendar.setTimeZone(TimeZone.getTimeZone("GMT+8"));
	        //设置在几点提醒  设置的为13点
	        mCalendar.set(Calendar.HOUR_OF_DAY, 16);
	        //设置在几分提醒  设置的为25分
	        mCalendar.set(Calendar.MINUTE, 38);
	        //下面这两个看字面意思也知道
	        mCalendar.set(Calendar.SECOND, 0);
	        mCalendar.set(Calendar.MILLISECOND, 0);

	        //上面设置的就是13点25分的时间点

	        //获取上面设置的13点25分的毫秒值
	        long selectTime = mCalendar.getTimeInMillis();

	        // 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
	        if(systemTime > selectTime) {
	            mCalendar.add(Calendar.DAY_OF_MONTH, 1);
	        }

	       //AlarmReceiver.class为广播接受者
	        Intent intent2 = new Intent(this, AlarmReceiver.class);
	        PendingIntent pi = PendingIntent.getBroadcast(this, 0, intent, 0);
	        //得到AlarmManager实例
	        AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);

	       //**********注意！！下面的两个根据实际需求任选其一即可*********

	        *//**
	         * 单次提醒
	         * mCalendar.getTimeInMillis() 上面设置的13点25分的时间点毫秒值
	         *//*
	        am.set(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), pi);

	        *//**
	         * 重复提醒
	         * 第一个参数是警报类型；下面有介绍
	         * 第二个参数网上说法不一，很多都是说的是延迟多少毫秒执行这个闹钟，但是我用的刷了MIUI的三星手机的实际效果是与单次提醒的参数一样，即设置的13点25分的时间点毫秒值
	         * 第三个参数是重复周期，也就是下次提醒的间隔 毫秒值 我这里是一天后提醒
	         *//*
	        am.setRepeating(AlarmManager.RTC_WAKEUP, mCalendar.getTimeInMillis(), (1000 * 60 * 60 * 24), pi);
	*/        
	        
	        
	        
	        
	}
	// 弹出框选择时间
	public void warntime(View view){
		
		   TimePickerDialog timePickerDialog = new TimePickerDialog(  
	                this, new OnTimeSetListener() {  
	                      
	                    @Override  
	                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {  
	                        // TODO Auto-generated method stub  
	                         // ToastUtils.showToast(getApplicationContext(), hourOfDay+"==="+minute);
	                         hour =hourOfDay;
	                         mfen=minute ;
	                         if (minute<10) {
	                        	 warn_show.setText(hourOfDay+" : 0"+minute);
							} else {
								warn_show.setText(hourOfDay+" : "+minute);
							}
	                         calendar.setTimeInMillis(System.currentTimeMillis());
	                         calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
	                         calendar.set(Calendar.MINUTE, minute);
	                         calendar.set(Calendar.SECOND, 0);
	                         calendar.set(Calendar.MILLISECOND, 0);

	                         
	                         Intent intent = new Intent(WarnActivity.this, AlarmReceiver.class);
	                         PendingIntent pendingIntent = PendingIntent.getBroadcast(WarnActivity.this, 0, intent, 0);
	                         AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);
	                         alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
	                         alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (10 * 1000),
	                             (24 * 60 * 60 * 1000), pendingIntent);
	                         //String tmps = "设置闹钟时间为" + format(hourOfDay) + ":" +format(minute);
	                         ToastUtils.showToast(getApplicationContext(), "设置成功");
	                         Log.i("闹钟设置", "设置成功");
	                    
	                    }  
	                }, hour, mfen, true);  
		   timePickerDialog.show();
	}
	
	private void initListen() {
		//Siwtch
		box.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				ToastUtils.showToast(getBaseContext(), "敬请期待！！");
				
				return false;
			}
		});
	}
	private void inData() {
		long time=System.currentTimeMillis();
		calendar=Calendar.getInstance();
		calendar.setTimeInMillis(time);
		 mfen=calendar.get(Calendar.MINUTE);
		 hour = calendar.get(Calendar.HOUR_OF_DAY);
			 if (mfen<10) {
				 warn_show.setText(hour+" : 0"+mfen);
			} else {
				warn_show.setText(hour+" : "+mfen);
			}
			 
		}
	
	private void initView() {
		 nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);  
		box =   (com.book.utils.FunSwitch) findViewById(R.id.warn_box);
		warn_show = (TextView) findViewById(R.id.warn_show);
	//	warn_time = (ImageView) findViewById(R.id.warn_time);
	}

	
}
