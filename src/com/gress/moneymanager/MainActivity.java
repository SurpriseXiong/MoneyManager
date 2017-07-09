package com.gress.moneymanager;
import java.util.ArrayList;
import java.util.List;

import com.money.adapter.MainVpAdapter;
import com.money.fragment.BaobiaoFragment;
import com.money.fragment.GengduoFragment;
import com.money.fragment.JizhangFragment;
import com.money.fragment.ZijinFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.Window;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class MainActivity extends FragmentActivity {
	ViewPager mvp_show; // ViewPager 用来可以左右滑动的容器
	private RadioButton mRb_jizhang, mRb_baobiao, mRb_zijin, mRb_gengduo;
	private RadioGroup mRg_showRg;
	private Fragment mjizhangFragment, mbaobiaoFragment, mzijinFragment,
			mgengduoFragment;
	private List<Fragment> mFragments;// 存放Fragment容器的集合
	private SharedPreferences sp;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		initView();
		initDate(); // 初始化fragment 建立四个继承Fragemnt文件和布局文件
		initAdpate(); // 建立适配器 Main Adapter
		initListen();
	}

	private void initListen() {
		// ViewPager 的滑动监听
		mvp_show.addOnPageChangeListener(new OnPageChangeListener() {
			// 当界面被选择的时候监听，观察者模式
			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				switch (arg0) {
				case 0:
					close();
					changecolor(mRb_jizhang, R.drawable.tab_accounte, "#00BCFF");
					break;
				case 1:
					close();
					changecolor(mRb_baobiao, R.drawable.tab_form, "#00BCFF");
					break;
				case 2:
					close();
					changecolor(mRb_zijin, R.drawable.tab_founds, "#00BCFF");
					break;
				case 3:
					close();
					changecolor(mRb_gengduo, R.drawable.tab_mine, "#00BCFF");
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
		
			/**
			 * @param bt
			 *            点击的Button的id
			 * @param draw
			 *            点击按钮Button需要更换的图片的名字 如R.drawable.tab_mine
			 * @param textcolor
			 *            传进更改的文字颜色
			 */
			private void changecolor(RadioButton bt, int draw, String textcolor) {
				Drawable drawable_more = bt.getResources().getDrawable(draw);
				drawable_more.setBounds(0, 0, drawable_more.getMinimumWidth(),
						drawable_more.getMinimumHeight());// 必须设置图片大小，否则不显示
				bt.setCompoundDrawables(null, drawable_more, null, null);

				bt.setTextColor(Color.parseColor(textcolor));
			}

			/**
			 * 清空颜色 使用changecolor
			 */
			private void close() {

				changecolor(mRb_jizhang, R.drawable.tab_accounte2, "#000000");
				changecolor(mRb_baobiao, R.drawable.tab_form2, "#000000");
				changecolor(mRb_zijin, R.drawable.tab_founds2, "#000000");
				changecolor(mRb_gengduo, R.drawable.tab_mine2, "#000000");
			}
		
		});

		// Button 的监听
		mRg_showRg.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				switch (checkedId) {
				case R.id.main_bt_jizhang:
					mvp_show.setCurrentItem(0);
					close();
					changecolor(mRb_jizhang, R.drawable.tab_accounte, "#00BCFF");

					break;
				case R.id.main_bt_baobiao:
					mvp_show.setCurrentItem(1);
					close();
					changecolor(mRb_baobiao, R.drawable.tab_form, "#00BCFF");

					break;
				case R.id.main_bt_zijin:
					mvp_show.setCurrentItem(2);
					close();
					changecolor(mRb_zijin, R.drawable.tab_founds, "#00BCFF");

					break;
				case R.id.main_bt_gengduo:
					mvp_show.setCurrentItem(3);
					close();
					changecolor(mRb_gengduo, R.drawable.tab_mine, "#00BCFF");

					break;

				}
			}

			/**
			 * @param bt
			 *            点击的Button的id
			 * @param draw
			 *            点击按钮Button需要更换的图片的名字 如R.drawable.tab_mine
			 * @param textcolor
			 *            传进更改的文字颜色
			 */
			private void changecolor(RadioButton bt, int draw, String textcolor) {
				Drawable drawable_more = bt.getResources().getDrawable(draw);
				drawable_more.setBounds(0, 0, drawable_more.getMinimumWidth(),
						drawable_more.getMinimumHeight());// 必须设置图片大小，否则不显示
				bt.setCompoundDrawables(null, drawable_more, null, null);

				bt.setTextColor(Color.parseColor(textcolor));
			}

			/**
			 * 清空颜色 使用changecolor
			 */
			private void close() {

				changecolor(mRb_jizhang, R.drawable.tab_accounte2, "#000000");
				changecolor(mRb_baobiao, R.drawable.tab_form2, "#000000");
				changecolor(mRb_zijin, R.drawable.tab_founds2, "#000000");
				changecolor(mRb_gengduo, R.drawable.tab_mine2, "#000000");
			}
		});
	}

	private void initAdpate() {
		// getSupportFragmentManager 这个方法需要将MainActivity
		// 改成继承FragmentActivity才可以使用
		mvp_show.setAdapter(new MainVpAdapter(getSupportFragmentManager(),
				mFragments));
	}

	private void initDate() {
		// TODO Auto-generated method stub
		mbaobiaoFragment = new BaobiaoFragment();
		mgengduoFragment = new GengduoFragment();
		mzijinFragment = new ZijinFragment();
		mjizhangFragment = new JizhangFragment();
		mFragments = new ArrayList<Fragment>();// 集合
		mFragments.add(mjizhangFragment);
		mFragments.add(mbaobiaoFragment);
		mFragments.add(mzijinFragment);
		mFragments.add(mgengduoFragment);
		//判断登陆
		sp=getSharedPreferences("user_info",Context.MODE_APPEND);
		Editor edit = sp.edit();
		edit.putString("login", "0"); // 设置 是不是登录 0 1 
		edit.putString("user", "");
		edit.commit();

	}

	private void initView() {
		// TODO Auto-generated method stub
		mvp_show = (ViewPager) findViewById(R.id.main_vp);
		mRg_showRg = (RadioGroup) findViewById(R.id.main_rg);
		mRb_jizhang = (RadioButton) findViewById(R.id.main_bt_jizhang);
		mRb_baobiao = (RadioButton) findViewById(R.id.main_bt_baobiao);
		mRb_zijin = (RadioButton) findViewById(R.id.main_bt_zijin);
		mRb_gengduo = (RadioButton) findViewById(R.id.main_bt_gengduo);

	}



}
