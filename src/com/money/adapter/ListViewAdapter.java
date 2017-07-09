package com.money.adapter;


import java.util.List;

import com.gress.moneymanager.R;
import com.money.bean.Account;
import com.money.db.AccountDao;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter{
	
	private Context context;
	private AccountDao dao;
	private List<Account> list;
	public ListViewAdapter(Context context) {
		this.context = context;
		dao = new AccountDao(context);
		 list = dao.queryAllAccont();
		}

	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
		
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View view=View.inflate(context,R.layout.itean_listview, null);
		TextView iteam_lv_name = (TextView) view.findViewById(R.id.iteam_lv_name);
		TextView iteam_lv_money = (TextView) view.findViewById(R.id.iteam_lv_money);
		ImageView iteam_ig_photo = (ImageView) view.findViewById(R.id.iteam_ig_photo);
		iteam_lv_name.setText(list.get(position).getName());
		iteam_lv_money.setText(list.get(position).getMoney()+"");
		iteam_ig_photo.setBackgroundResource(list.get(position).getPhoto());
		
		
		return view;
	}

}
