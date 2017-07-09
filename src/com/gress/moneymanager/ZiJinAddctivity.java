package com.gress.moneymanager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.book.utils.ToastUtils;
import com.gress.moneymanager.R;
import com.gress.moneymanager.R.id;
import com.gress.moneymanager.R.layout;
import com.gress.moneymanager.R.menu;
import com.money.bean.Account;
import com.money.db.AccountDao;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ZiJinAddctivity extends Activity {
	private ImageButton addtype_back; // 返回按钮
	private EditText account_Et_name, account_Et_money; // 资金名字和金钱
	private AccountDao dao;
	private ImageView account_selectcolor; // 选择资金类型图片
	private TextView account_Tv_pattern;  //资金类型
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_typle);
		initView();
		initlisten();
	}

	private void initView() {
		dao = new AccountDao(this);
		account_Tv_pattern = (TextView) findViewById(R.id.account_Tv_pattern);
		account_selectcolor = (ImageView) findViewById(R.id.account_selectcolor);
		addtype_back = (ImageButton) findViewById(R.id.addtype_back);
		account_Et_money = (EditText) findViewById(R.id.account_Et_money);
		account_Et_name = (EditText) findViewById(R.id.account_Et_name);
		
	}

	private void initlisten() {
		// 增加资金类型的返回键
		addtype_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	/**
	 * 数据库提交增加一个资金类型
	 * 
	 * @param view
	 */
	public void submit(View view) {
		// dao.addBook(new Book(name,Double.valueOf(price)));
		String name = account_Et_name.getText().toString();
		String money = account_Et_money.getText().toString();
		String type = account_Tv_pattern.getText().toString();
		//去数据库查询防止重名
		Account queryUserByName = dao.queryAccountByName(name);
		if (name.equals("")) {
			ToastUtils.showToast(this, "请输入账户类型");
			Log.i("submit","");
		} else {
			if (queryUserByName!=null) {
				//说明用户在 对象不能等于 0 或者”“
				ToastUtils.showToast(getApplication(),  "账户已经存在");
			}
			else if (money.equals("")) {
				ToastUtils.showToast(this, "请输入这个账户余额");
			} else {
				Map<String, Integer> map = new HashMap<String, Integer>();
				map.put("现金", R.drawable.ft_cash1);
				map.put("储蓄卡", R.drawable.ft_chuxuka1);
				map.put("信用卡", R.drawable.ft_creditcard1);
				map.put("购物卡", R.drawable.ft_shiwuka);
				map.put("支付宝", R.drawable.ft_wangluochongzhi1);
				map.put("应用收款", R.drawable.ft_yingshouqian);
				int photo = map.get(type);
				
				Account account = new Account(name, Double.valueOf(money),type,photo);
				dao.addAccount(account);
				//完成添加自己类型跳转
				ToastUtils.showToast(this, "增加账户类型成功 账号： " + name + "  余额： "
						+ money);
				Intent intent = new Intent();
				setResult(2, intent);
				finish();
			}
		}
		
		
		
	}

	/**
	 * 弹出框 事件 添加资金类型的按钮实现
	 */
	public void selectcolor(View vie) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		View view = View.inflate(this, R.layout.item_account_photo, null);
		// View view = View.inflate(this, R.layout.itemgengduo_yijifankui,
		// null);
		final AlertDialog alertDialog = builder.create();
		alertDialog.setView(view);
		alertDialog.show();

		Button account_cancel; // 弹出框退出
		final LinearLayout xianjin, chuxuka, xinyongka;
		final LinearLayout gouwuka;
		final LinearLayout zhifubao, yingyong;
		xianjin = (LinearLayout) view.findViewById(R.id.account_cxianjin); // 现金
		chuxuka = (LinearLayout) view.findViewById(R.id.account_cchuxuka); // 储蓄卡
		xinyongka = (LinearLayout) view.findViewById(R.id.account_cxinyongka); // 信用卡
		gouwuka = (LinearLayout) view.findViewById(R.id.account_cgouwuka); // 购物卡
		zhifubao = (LinearLayout) view.findViewById(R.id.account_czhifubao); // 支付宝
		yingyong = (LinearLayout) view.findViewById(R.id.account_cyingyong); // 应用
		account_cancel = (Button) view.findViewById(R.id.account_cancel); // 取消按钮
		// 取消按钮
		account_cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();
			}
		});
		// 将购物卡类型返回
		gouwuka.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_selectcolor
						.setBackgroundResource(R.drawable.ft_shiwuka);
				//获取资金类型和图片
				//int photo =R.drawable.ft_shiwuka ;
				String pattern = "购物卡";
				account_Tv_pattern.setText(pattern);
				alertDialog.dismiss();
			}
		});
		// 将现金类型返回
		xianjin.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_selectcolor
						.setBackgroundResource(R.drawable.ft_cash1);
				//int photo =R.drawable.ft_cash1 ;
				String pattern = "现金";
				account_Tv_pattern.setText(pattern);
				alertDialog.dismiss();
			}
		});
		// 将储蓄卡类型返回
		chuxuka.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_selectcolor
						.setBackgroundResource(R.drawable.ft_chuxuka1);
				//int photo =R.drawable.ft_chuxuka1 ;
				String pattern = "储蓄卡";
				account_Tv_pattern.setText(pattern);
				alertDialog.dismiss();
			}
		});
		// 将信用卡类型返回
		xinyongka.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_selectcolor
						.setBackgroundResource(R.drawable.ft_creditcard1);
				//int photo =R.drawable.ft_creditcard1 ;
				String pattern = "信用卡";
				account_Tv_pattern.setText(pattern);
				alertDialog.dismiss();
			}
		});
		// 将支付宝卡类型返回
		zhifubao.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_selectcolor
						.setBackgroundResource(R.drawable.ft_wangluochongzhi1);
				//int photo =R.drawable.ft_wangluochongzhi1 ;
				String pattern = "支付宝";
				account_Tv_pattern.setText(pattern);
				alertDialog.dismiss();
			}
		});
		// 将应用收款卡类型返回
		yingyong.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				account_selectcolor
						.setBackgroundResource(R.drawable.ft_yingshouqian);
				//int photo =R.drawable.ft_yingshouqian ;
				String pattern = "应用收款";
				account_Tv_pattern.setText(pattern);
				alertDialog.dismiss();
			}
		});
	}

}
