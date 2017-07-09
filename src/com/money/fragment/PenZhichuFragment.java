package com.money.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.book.utils.ToastUtils;
import com.gress.moneymanager.R;
import com.money.adapter.ListViewAdapter;
import com.money.bean.Account;
import com.money.bean.Record;
import com.money.db.AccountDao;
import com.money.db.RecordDao;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PenZhichuFragment extends Fragment {
	private AccountDao accdao;
	private RecordDao dao;
	private GridView gridview_zhichu; // 支出的GridView
	private ImageView  rili_zhichu;  //日历点击
	private TextView 	rili_zhichu_day;//日历更新 的textView
	private TextView zhichu_signgridview;  //记录GridView 点击的位置
	private EditText zhichu_money; //支出的金额
	private ImageView zhichu_submit; // 支出的提交按钮
	private ImageView shouru_zc_zijn ;//查询资金类型的点击图片
	private  TextView shouru_zc_zijn1 ;//资金类型
	private ImageView head_icon;
	List<Map<String, Object>> data;
	int[] icon = { R.drawable.bt_food, R.drawable.bt_wine,
			R.drawable.bt_car, R.drawable.bt_shopping, R.drawable.bt_yule,
			R.drawable.bt_kuisun, R.drawable.bt_service,
			R.drawable.bt_chongzhi, R.drawable.bt_madecine,
			R.drawable.bt_house, R.drawable.bt_water, R.drawable.bt_shicai };
	String[] name = { "餐饮", "烟酒", "交通", "购物", "娱乐", "投资亏损", "生活服务", "充值",
			"医药", "住房", "水电煤", "食材" };

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.fragment_penzhichu, container,
				false);

		initView(view);
		initDate(view);
		initListen(view);
		return view;
	}

	private void initListen(View view) {
		//gridview的点击监听事件
		gridview_zhichu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				head_icon.setImageResource(icon[position]);
				//记录位置去记录是那个收入类型和对应的图标
				zhichu_signgridview.setText(position+"");
			}
		});
		//记账日历时间更新
		rili_zhichu.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();  
		        final int year = calendar.get(Calendar.YEAR);  
		        final int month = calendar.get(Calendar.MONTH);  
		        final int day = calendar.get(Calendar.DAY_OF_MONTH); 
				DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {
						rili_zhichu_day.setText(dayOfMonth+"");
					}
				}, year, month, day);
				datePickerDialog.show();
			}
		});
		//资金事件的监听 
		shouru_zc_zijn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity()); 
				View view = View.inflate(getActivity(), R.layout.iteam_listview_account, null);
		       // View view = View.inflate(this, R.layout.itemgengduo_yijifankui, null);  
		        final AlertDialog alertDialog = builder.create();  
		        alertDialog.setView(view);  
		        alertDialog.show(); 
		        
		        ListView listview =  (ListView) view.findViewById(R.id.iteam_listview);
		        listview.setAdapter(new ListViewAdapter(getContext()));
		        Button cancel = (Button) view.findViewById(R.id.iteam_listview_cancel);
		        //点击每一个ListView的监听
		        listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						List<Account> account = new ArrayList<Account>();
						account=  accdao.queryAllAccont();
						shouru_zc_zijn1.setText(account.get(position).getName());
						shouru_zc_zijn.setImageResource(account.get(position).getPhoto());
						alertDialog.dismiss();
						
					}
				});
		        //注销弹出框的监听事件
				cancel.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						alertDialog.dismiss();
					}
				});
			}
		});
		
		
		//提交按钮，进行支出记录的插入数据
		zhichu_submit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				List<Account> account = new ArrayList<Account>();
				account=  accdao.queryAllAccont();
				if (account.size()==0) {
					ToastUtils.showToast(getContext(), "您还没有添加资金账户，快去添加吧");
				} else {
				
				
				if (zhichu_money.getText().toString().equals("")) {
					ToastUtils.showToast(getContext(), "请输入金额！！");
				}else {
					double money = Double.parseDouble(zhichu_money.getText().toString()); //钱
					String day =rili_zhichu_day.getText().toString(); //时间
					int i = Integer.parseInt(zhichu_signgridview.getText().toString());
					String content = 	name[i];	//干了什么
					int photo = icon[i];  //图片
					String type = "out";  // 收入type
					String from = shouru_zc_zijn1.getText().toString();
					Log.i("view插入数据", "1"+from);
					Record record = new Record(money, content, photo, day, type, from);
					Log.i("view插入数据", "2");
					accdao.updaterecond(-money, from);
					dao.addRecord(record);
					Log.i("view插入数据", "3");
					zhichu_money.setText("");
					ToastUtils.showToast(getContext(), "增加支出记录成功");
					getActivity().setResult(1);
					getActivity().finish();
				}
				}
			}
		});
	}
	
	private void initDate(View view) {
		//日历更新
		Calendar calendar = Calendar.getInstance(); 
		final int day = calendar.get(Calendar.DAY_OF_MONTH); 
		rili_zhichu_day.setText(day+"");
		//资金账户更新到第一个账户
		List<Account> account = new ArrayList<Account>();
		account=  accdao.queryAllAccont();
		if (account.size()!=0) {
			shouru_zc_zijn1.setText(account.get(0).getName());
			shouru_zc_zijn.setImageResource(account.get(0).getPhoto());
		}
		//适配GirdView 的Adapter
		for (int i = 0; i < name.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("icon", icon[i]);
			map.put("name", name[i]);
			data.add(map);
		}
		gridview_zhichu.setAdapter(new SimpleAdapter(getContext(), data,
				R.layout.gv_item, new String[] { "icon", "name" }, new int[] {
						R.id.iv_icon, R.id.tv_name }));

	}

	private void initView(View view) {
		accdao = new AccountDao(getContext());
		dao = new RecordDao(getContext());
		shouru_zc_zijn = (ImageView) view.findViewById(R.id.shouru_zc_zijn);
		shouru_zc_zijn1 = (TextView) view.findViewById(R.id.shouru_zc_zijn1); 
		zhichu_money = (EditText) view.findViewById(R.id.zhichu_money);
		zhichu_submit = (ImageView) view.findViewById(R.id.zhichu_submit);
		zhichu_signgridview = (TextView) view.findViewById(R.id.zhichu_signgridview);
		rili_zhichu_day = (TextView) view.findViewById(R.id.rili_zhichu_day);
		rili_zhichu = (ImageView) view.findViewById(R.id.rili_zhichu);
		gridview_zhichu = (GridView) view.findViewById(R.id.gridview_zhichu);
		head_icon = (ImageView) view.findViewById(R.id.head_icon);
		data = new ArrayList<Map<String, Object>>();
	}

}
