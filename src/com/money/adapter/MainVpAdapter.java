package com.money.adapter;

import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MainVpAdapter extends FragmentPagerAdapter{
	private List<Fragment> mfragemnts;

	public MainVpAdapter(FragmentManager fm) {
		super(fm);
	}
	
	 public MainVpAdapter(FragmentManager fm ,List<Fragment> fragments) {
		 super(fm);
		this.mfragemnts=fragments;
	}

	/*
	 * 返回每个界面的Fragment对象
	 */
	@Override
	public Fragment getItem(int poisite) {

		return mfragemnts.get(poisite);
	}

	/*
	 * 返回Fragemnt的数量
	 */
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mfragemnts.size();
	}

}
