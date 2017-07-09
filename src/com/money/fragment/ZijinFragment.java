package com.money.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.book.utils.ToastUtils;
import com.gress.moneymanager.R;
import com.gress.moneymanager.ZiJiNzhuanActivity;
import com.gress.moneymanager.ZiJinAddctivity;
import com.money.adapter.ListViewAdapter;
import com.money.db.AccountDao;

public class ZijinFragment extends Fragment {
	private SharedPreferences sp;
	private AccountDao dao;
	private TextView zijin_sum;  //资金总和
	private LinearLayout ziji_addtype;//增加资金账户
	private Button zijin_zhuangzhang; //转账
	private ListView zijin_listview ; //资金用listView表示
	private  ListViewAdapter adapter;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_zijin, container, false);
		initView(view);
		initdate();
		initListen();
		return view;
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode==1&&resultCode==2) 
			
		super.onActivityResult(requestCode, resultCode, data);
		initdate();
	}
	
	private void initdate() {
		if (!sp.getString("login", "").equals("0")) {
			//更新资金综合
			zijin_sum.setText(dao.SumAccount()+"");
			//更新资金的listView
			zijin_listview.setAdapter( (ListAdapter) new ListViewAdapter(getContext()));
		}
	}

	private void initListen() {
		//增加资金账户
		ziji_addtype.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!sp.getString("login", "").equals("0")) {
					Intent intent = new Intent();
					intent.setClass(getActivity(), ZiJinAddctivity.class);
					startActivityForResult(intent, 1);
				}else {
					ToastUtils.showToast(getContext(), "请登陆");
				}
			}
		});
		//转账
		zijin_zhuangzhang.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!sp.getString("login", "").equals("0")) {
				Intent intent = new Intent();
				intent.setClass(getActivity(), ZiJiNzhuanActivity.class);
				startActivityForResult(intent, 1);
			}else {
				ToastUtils.showToast(getContext(), "请登陆");
			}
				}
		});
		
		
		
	}

	private void initView(View view) {
		sp=getActivity().getSharedPreferences("user_info",Context.MODE_APPEND);
		dao = new AccountDao(getContext());
		zijin_sum = (TextView) view.findViewById(R.id.zijin_sum);
		zijin_listview = (ListView) view.findViewById(R.id.zijin_listview);
		ziji_addtype =  (LinearLayout) view.findViewById(R.id.ziji_addteye);
		zijin_zhuangzhang = (Button) view.findViewById(R.id.zijin_zhuangzhang);
	}
}
