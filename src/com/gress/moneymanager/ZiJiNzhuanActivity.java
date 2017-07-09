package com.gress.moneymanager;

import java.util.ArrayList;
import java.util.List;

import com.book.utils.ToastUtils;
import com.money.adapter.ListViewAdapter;
import com.money.bean.Account;
import com.money.db.AccountDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class ZiJiNzhuanActivity extends Activity {
	private AccountDao accdao;
	private  ImageButton zijin_zhuang_back; //返回按钮
	private LinearLayout in_lv,out_lv;	//转进转出的弹出listview
	private TextView in_name,out_name;	 //更新的资金账户名字
	private ImageView in_photo,out_photo; //更新的资金名字
	private EditText money; //输入的钱
	String inname = "";
	String outname = "";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_zi_ji_nzhuan);
		initView();
		initListen();
	}

	//提交转账
	public void submit (View view ){
		  String price = money.getText().toString();
		  double parseDouble = Double.parseDouble(money.getText().toString());
		//进行各种判断
		if (inname.equals("")||outname.equals("")) {
			ToastUtils.showToast(this, "请选择转账或收转账户~~");
		} else if(inname.equals(outname)){
			ToastUtils.showToast(this, "不能同时选择同一账户");
		}else if(price.equals("")){
			ToastUtils.showToast(this, "输入金额·。·");
		}else {
			ToastUtils.showToast(this, "success");
			accdao.updaterecond(parseDouble, inname);
			accdao.updaterecond(-parseDouble, outname);
			finish();
		}
	}
	private void initListen() {
		//返回按钮
		zijin_zhuang_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		//
		out_lv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ZiJiNzhuanActivity.this); 
				View view = View.inflate(ZiJiNzhuanActivity.this, R.layout.iteam_listview_account, null);
		       // View view = View.inflate(this, R.layout.itemgengduo_yijifankui, null);  
		        final AlertDialog alertDialog = builder.create();  
		        alertDialog.setView(view);  
		        alertDialog.show(); 
		        ListView listview =  (ListView) view.findViewById(R.id.iteam_listview);
		        listview.setAdapter(new ListViewAdapter(ZiJiNzhuanActivity.this));
		        Button cancel = (Button) view.findViewById(R.id.iteam_listview_cancel);
		        //点击每一个ListView的监听
		        listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						List<Account> account = new ArrayList<Account>();
						account=  accdao.queryAllAccont();
						outname = account.get(position).getName();
						out_name.setText(outname+"   "+account.get(position).getMoney());
						out_photo.setImageResource(account.get(position).getPhoto());
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
		in_lv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder builder = new AlertDialog.Builder(ZiJiNzhuanActivity.this); 
				View view = View.inflate(ZiJiNzhuanActivity.this, R.layout.iteam_listview_account, null);
		       // View view = View.inflate(this, R.layout.itemgengduo_yijifankui, null);  
		        final AlertDialog alertDialog = builder.create();  
		        alertDialog.setView(view);  
		        alertDialog.show(); 
		        ListView listview =  (ListView) view.findViewById(R.id.iteam_listview);
		        listview.setAdapter(new ListViewAdapter(ZiJiNzhuanActivity.this));
		        Button cancel = (Button) view.findViewById(R.id.iteam_listview_cancel);
		        //点击每一个ListView的监听
		        listview.setOnItemClickListener(new OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent,
							View view, int position, long id) {
						List<Account> account = new ArrayList<Account>();
						account=  accdao.queryAllAccont();
						inname = account.get(position).getName();
						in_name.setText(inname+"   "+account.get(position).getMoney());
						in_photo.setImageResource(account.get(position).getPhoto());
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

	private void initView() {
		accdao = new AccountDao(getApplicationContext());
		zijin_zhuang_back  = (ImageButton) findViewById(R.id.zijin_zhuang_back);
		in_lv = (LinearLayout) findViewById(R.id.in_listview);
		out_lv = (LinearLayout) findViewById(R.id.out_listview);
		in_name = (TextView) findViewById(R.id.in_name);
		out_name = (TextView) findViewById(R.id.out_name);
		in_photo =(ImageView) findViewById(R.id.in_photo);
		out_photo =(ImageView) findViewById(R.id.out_photo);
		money = (EditText) findViewById(R.id.money_zhuang);
	}

	
}
