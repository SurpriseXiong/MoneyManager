package com.money.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.book.utils.ToastUtils;
import com.gress.moneymanager.JiZhangpenActivity;
import com.gress.moneymanager.LoginActivity;
import com.gress.moneymanager.R;
import com.money.adapter.RecondAdapter;
import com.money.bean.Record;
import com.money.db.RecordDao;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class JizhangFragment extends Fragment {
	RecordDao dao;
	private ImageView jizhang_pen;  //记账页面的笔
	private ListView recond_listview;  	//要替代背景图片的List View
	private ImageView recond_background;  //背景图片
	private TextView jizhang_sun_zhichu,jizhang_sun_shouru;  //记账收入支出总和
	private SharedPreferences sp;
	List<Record> list = new ArrayList<Record>();  //记账的集合
	private RecondAdapter adapter;  //记账  适配器
	private ImageView rili_jizhang; //点击弹出日历框
	private TextView rili_day;  //更新时间
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_jizhang, container,
				false);
		initView(view);
		initDate();
		initListen();
		return view;
	}
	
	
	private void initDate() {
		//判断登陆  未登录不显示listView
		if (!sp.getString("login", "").equals("0")) {
			//显示listview
		list= dao.queryAllRecond();
		if (list.size()!=0) {
			//当有记账记录的时候  去掉背景图片
			recond_background.setVisibility(View.GONE); 
			adapter = new RecondAdapter(getContext(),list);
			recond_listview.setAdapter(adapter);
			//当有记账的时候更新记账
			double sumzhichu = 0;
			double sumshouru = 0;
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getType().toString().equals("in")) {
					//收入综合
					sumshouru  = sumshouru +list.get(i).getMoney();
				} else {
					sumzhichu  = sumzhichu +list.get(i).getMoney();
				}
				}
			jizhang_sun_zhichu.setText(sumshouru+"");
			jizhang_sun_shouru.setText(sumzhichu+"");
			}
		}
		
		//更新当前日历  先要获取当前时间
		Calendar calendar = Calendar.getInstance(); 
		final int day = calendar.get(Calendar.DAY_OF_MONTH); 
		rili_day.setText(day+"");
		
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if (requestCode==1&&resultCode==1) {
			initDate();
		}
	}
	private void initListen() {
		//跳转去记账
		jizhang_pen.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if (!sp.getString("login", "").equals("0")) {
					Intent intent = new Intent();
					intent.setClass(getActivity(), JiZhangpenActivity.class);
					startActivityForResult(intent, 1);
				} else {
					
					ToastUtils.showToast(getContext(), "请登录");
				}
			}
		});
		//listView的监听事件
		recond_listview.setOnItemLongClickListener(new OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Record record = list.get(position);
			
				dao.deleteRecond(record.getId());
				list.remove(position);
				adapter.notifyDataSetChanged();
				ToastUtils.showToast(getContext(),position+" position       " +record.getId()+"getId  ");
				return true;
			}
		});
		recond_listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				ToastUtils.showToast(getContext(), "长按将删除数据，并且资金也会更改回原来的数据");
			}
		});
		//日历弹出狂
		rili_jizhang.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Calendar calendar = Calendar.getInstance();  
		        final int year = calendar.get(Calendar.YEAR);  
		        final int month = calendar.get(Calendar.MONTH);  
		        final int day = calendar.get(Calendar.DAY_OF_MONTH); 
				DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						rili_day.setText(dayOfMonth+"");
					}
				}, year, month, day);
				datePickerDialog.show();
			}
		});
		
	}

	private void initView(View view) {
		sp=getActivity().getSharedPreferences("user_info",Context.MODE_APPEND);
		dao = new RecordDao(getContext());
		jizhang_pen =  (ImageView) view.findViewById(R.id.jizhang_pen);
		recond_background = (ImageView) view.findViewById(R.id.recond_background);
		recond_listview = (ListView) view.findViewById(R.id.recond_listview);
		jizhang_sun_zhichu = (TextView) view.findViewById(R.id.jizhang_sun_zhichu);
		jizhang_sun_shouru = (TextView) view.findViewById(R.id.jizhang_sun_shouru);
		rili_jizhang = (ImageView) view.findViewById(R.id.rili_jizhang);
		rili_day = (TextView) view.findViewById(R.id.rili_day);
	}

}
