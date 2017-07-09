package com.gress.moneymanager;

import java.util.ArrayList;
import java.util.List;

import com.money.adapter.MainVpAdapter;
import com.money.fragment.PenShouruFragment;
import com.money.fragment.PenZhichuFragment;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class JiZhangpenActivity extends FragmentActivity {
	private ImageButton jizhang_jizhangpen_back;
	
	private TextView pen_zhichu,pen_shouru; //支出和收入的按钮
	
	private ViewPager pen_show; // ViewPager 用来可以左右滑动的容器
	
	private Fragment mfragmentzhichu,mfragmentshouru;
	
	private List<Fragment> mFragments;// 存放Fragment容器的集合
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_ji_zhangpen);
		initview();
		initDate(); // 初始化fragment 建立四个继承Fragemnt文件和布局文件
		initAdpate(); // 建立适配器 Main Adapter
		initListen();
	}

	
		private void initAdpate() {
			// getSupportFragmentManager 这个方法需要将MainActivity
			// 改成继承FragmentActivity才可以使用
			pen_show.setAdapter(new MainVpAdapter(getSupportFragmentManager(),
					mFragments));
		}
	

	private void initDate() {
		// TODO Auto-generated method stub
		mfragmentshouru = new PenShouruFragment();
		mfragmentzhichu = new PenZhichuFragment();
		mFragments = new ArrayList<Fragment>();
		mFragments.add(mfragmentzhichu);
		mFragments.add(mfragmentshouru);
	}

	private void initListen() {
		//返回监听
		jizhang_jizhangpen_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});		
		// ViewPager 的滑动监听
		pen_show.addOnPageChangeListener(new OnPageChangeListener() {
			// 当界面被选择的时候监听，观察者模式
			@Override
			public void onPageSelected(int arg0) {
				switch (arg0) {
				case 0:
					pen_zhichu.setTextColor(Color.BLACK);
					pen_shouru.setTextColor(Color.BLACK);
					pen_zhichu.setTextColor(Color.BLUE);
					break;
				case 1:
					pen_zhichu.setTextColor(Color.BLACK);
					pen_shouru.setTextColor(Color.BLACK);
					pen_shouru.setTextColor(Color.RED);
					break;

				
				}
			}

			// 界面滑动结束的监听
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			// 界面滑动状态改变的监听 用的少
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});
		
		//支出按钮 
		pen_zhichu.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pen_show.setCurrentItem(0);
				pen_zhichu.setTextColor(Color.BLACK);
				pen_shouru.setTextColor(Color.BLACK);
				pen_zhichu.setTextColor(Color.BLUE);
			}
		});
		//收入按钮的监听
		pen_shouru.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				pen_show.setCurrentItem(1);
				pen_zhichu.setTextColor(Color.BLACK);
				pen_shouru.setTextColor(Color.BLACK);
				pen_shouru.setTextColor(Color.RED);
			}
		});
		
	
	}

	private void initview() {
		// TODO Auto-generated method stub
		jizhang_jizhangpen_back = (ImageButton) findViewById(R.id.jizhang_jizhangpen_back);
		pen_shouru = (TextView) findViewById(R.id.pen_shouru);
		pen_zhichu =  (TextView) findViewById(R.id.pen_zhichu);
		pen_show = (ViewPager) findViewById(R.id.pen_vp);
	}

	
}
