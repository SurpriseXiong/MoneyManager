package com.money.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class PenShouruFragment extends Fragment{
	private AccountDao accdao;	//资金的dao
	private RecordDao dao;   //记录的dao
	private GridView gridview_shouru; //九宫格
	private ImageView rili_shouru; //在收入页面的日历
	private ImageView head_icon2; //收入的类型头像
	private TextView rili_shouru_day;  //日期显示
	private ImageView shouru_submit;   //收入提交按钮
	private EditText shouru_money; //收入的money
	private ImageView shouru_sr_zijn;  //查找资金的点击图片
	private TextView shouru_sr_zijn2 ;//获取资金
	private TextView shouru_signgridview; //用来记录点击时GridView的位置,方便查找类型和图标,默认值是0，就是工资
	List<Map<String, Object>> date;
	int[] icon= {R.drawable.bt_wages,R.drawable.bt_bouns,R.drawable.bt_fuli,R.drawable.bt_invest,R.drawable.bt_hongbao,R.drawable.bt_jianzhi,R.drawable.bt_shenghuofei,R.drawable.bt_baoxiao,R.drawable.bt_tuikuan,R.drawable.bt_gongjijin,R.drawable.bt_shebao,R.drawable.bt_yiwai};
	String[] name = {"工资","奖金","福利","投资收益","红包","兼职","生活费","报销","退款","公积金","社保金","意外收获"};
	
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_penshouru, container, false);
			initView(view);
			initDate(view);  // 数据初始化
			initListen(view);
			return view;
		}
		
		private void initView(View view) {
			accdao = new AccountDao(getContext());
			dao = new RecordDao(getContext());
			shouru_sr_zijn2 = (TextView) view.findViewById(R.id.shouru_sr_zijn2);
			shouru_sr_zijn = (ImageView) view.findViewById(R.id.shouru_sr_zijn);
			shouru_signgridview = (TextView) view.findViewById(R.id.shouru_signgridview);
			shouru_money = (EditText) view.findViewById(R.id.shouru_money);
			shouru_submit = (ImageView) view.findViewById(R.id.shouru_submit);
			rili_shouru_day = (TextView) view.findViewById(R.id.rili_shouru_day);
			rili_shouru = (ImageView) view.findViewById(R.id.rili_shouru);
			head_icon2 = (ImageView) view.findViewById(R.id.head_icon2);
			gridview_shouru = (GridView) view.findViewById(R.id.gridview_shouru);
			date = new ArrayList<Map<String,Object>>();
		}
		
		private void initDate(View view) {
			//日历更新
			Calendar calendar = Calendar.getInstance(); 
			final int day = calendar.get(Calendar.DAY_OF_MONTH); 
			rili_shouru_day.setText(day+"");
			//适配GirdView 的Adapter
			for (int i = 0; i < icon.length; i++) {
				Map<String,	 Object> map = new HashMap<String, Object>();
				map.put("icon", icon[i]);
				map.put("name", name[i]);     
				date.add(map);
			} 
			gridview_shouru.setAdapter(new SimpleAdapter(getContext(), date, R.layout.gv_item, new String[]{"icon","name" }, new int[]{ R.id.iv_icon, R.id.tv_name}));
			//资金账户更新到第一个账户
			List<Account> account = new ArrayList<Account>();
			account=  accdao.queryAllAccont();
			if (account.size()!=0) {
				
				shouru_sr_zijn2.setText(account.get(0).getName());
				shouru_sr_zijn.setImageResource(account.get(0).getPhoto());
			}
			
		}
		
		private void initListen(View view) {
			//提交按钮监听，	并进行数据库插入数据
			shouru_submit.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					List<Account> account = new ArrayList<Account>();
					account=  accdao.queryAllAccont();
					if (account.size()==0) {
						ToastUtils.showToast(getContext(), "您还没有添加资金账户，快去添加吧");
					} else {
					if (shouru_money.getText().toString().equals("")) {
						ToastUtils.showToast(getContext(), "请输入金额！！");
					}else {
						double money = Double.parseDouble(shouru_money.getText().toString()); //钱
						String day =rili_shouru_day.getText().toString(); //时间
						int i = Integer.parseInt(shouru_signgridview.getText().toString());
						String content = 	name[i];	//干了什么
						int photo = icon[i];  //图片
						String type = "in";  // 收入type
						String from = shouru_sr_zijn2.getText().toString();
						Record record = new Record(money, content, photo, day, type, from);
						accdao.updaterecond( money, from);
						
						dao.addRecord(record);
						ToastUtils.showToast(getContext(), "增加收入记录成功");
						shouru_money.setText("");
						getActivity().setResult(1);
						getActivity().finish();
					}
					}
				}
			});
			// 九宫格监听 GridView
			gridview_shouru.setOnItemClickListener( new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					//head_icon2.setImageResource(icon[position]);
					head_icon2.setImageResource(icon[position]);
					//记录位置去记录是那个收入类型和对应的图标
					shouru_signgridview.setText(position+"");
				}
			});
			//日历
			rili_shouru.setOnClickListener(new OnClickListener() {
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
							rili_shouru_day.setText(dayOfMonth+"");
						}
					}, year, month, day);
					datePickerDialog.show();
				}
			});
			//资金类型的查找监听 shouru_im_zijn2是Text  shouru_im_zijn是Image
			shouru_sr_zijn.setOnClickListener(new OnClickListener() {
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
							 
							shouru_sr_zijn2.setText(account.get(position).getName());
							shouru_sr_zijn.setImageResource(account.get(position).getPhoto());
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
			
		
		
		}

}
