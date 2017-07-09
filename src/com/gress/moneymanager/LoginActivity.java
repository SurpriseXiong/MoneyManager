package com.gress.moneymanager;

import java.io.FileOutputStream;
import java.io.PrintStream;

import com.book.utils.ToastUtils;
import com.money.bean.User;
import com.money.db.UserDao;
import com.money.fragment.GengduoFragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginActivity extends Activity {
	private ImageButton login_back;  // 返回按钮
	private Button login_register,login_login; //登陆界面的注册按钮,和登录按钮
	private EditText login_name,login_passwd;  //用户输入的账户和密码
	private LinearLayout login_jqqd;
	private UserDao userdao;
	private SharedPreferences sp;
	//登陆判断
	public void loginlogin(View view){
		String name = login_name.getText().toString();
		String passwd = login_passwd.getText().toString();
		//查找用户是不是已经存在返回一个user的queryUserByName对象
		User queryUserByName = userdao.queryUserByName(name);
		if ( queryUserByName!=null) {
			if (queryUserByName.getPasswd().equals(passwd)) {
				ToastUtils.showToast(getApplication(),  "登陆成功");
				//判断登陆
				sp=getSharedPreferences("user_info",Context.MODE_APPEND);
				Editor ed = sp.edit();
				ed.putString("login", "登录");
				ed.putBoolean("name", false);
				ed.putString("user", name);
				ed.commit();
				Intent intent = new Intent();
				intent.putExtra("name", name);
				setResult(2,intent); // 反馈码resultCode= 2
				finish();
			}else {
				ToastUtils.showToast(getApplication(),  "密码错误");
			}
		} else {
			ToastUtils.showToast(getApplication(),  "用户不存在");
			
		}
		
	}
	
	//忘记密码 弹出框
	public void loginforgetpasswd(View view){
	
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		View view1 = View.inflate(this, R.layout.iteam_updatepsd2, null);
       // View view = View.inflate(this, R.layout.itemgengduo_yijifankui, null);  
        final AlertDialog alertDialog = builder.create();  
        alertDialog.setView(view1);  
        alertDialog.show(); 
        Button update_back = (Button) view1.findViewById(R.id.update_back);
        Button update_submit = (Button) view1.findViewById(R.id.update_update);
       final EditText userforget_1 = (EditText) view1.findViewById(R.id.userforget_1);  //用户名
       final EditText userforget_2 = (EditText) view1.findViewById(R.id.userforget_2); //第一次密码
       final EditText userforget_3 = (EditText) view1.findViewById(R.id.userforget_3); //第二次密码
        update_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				alertDialog.dismiss();  
			}
		});
        update_submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//ToastUtils.showToast(getBaseContext(), "修改成功");
				
				String username = userforget_1.getText().toString();
				String pwd1 = userforget_2.getText().toString();
				String pwd2 = userforget_3.getText().toString();
				User queryUserByName = userdao.queryUserByName(username);
				if (queryUserByName!=null) {
					//存在用户  改密码
					//ToastUtils.showToast(getApplicationContext(), "queryUserByName!=null");
					if (pwd1.equals(pwd2)) {
						if (pwd1.equals("")) {
							ToastUtils.showToast(getApplicationContext(), "请输入密码");
						} else {
							userdao.UpdatepwdByName(username, pwd1);
							ToastUtils.showToast(getApplicationContext(), "恭喜账户 "+username +" 修改密码成功");
							alertDialog.dismiss();  
						}
					} else {
						ToastUtils.showToast(getApplicationContext(), "两次密码不相等");
					}
				} else {
					//存在不用户  改密码
					ToastUtils.showToast(getApplicationContext(), "存在不用户");
				}
				
			}
		});
       
        
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		initView();
		initlisten();
		
	}

	private void initlisten() {
		login_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			finish(); // 销毁页面登录
			}
		});
		login_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				//获取一个context对象 和一个要去的地方
				intent.setClass(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});
		login_jqqd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ToastUtils.showToast(getBaseContext(), "敬请期待！！！");
			}
		});
	}

	private void initView() {
		// TODO Auto-generated method stub
		userdao = new UserDao(getBaseContext());
		login_back = (ImageButton) findViewById(R.id.login_back);
		login_register = (Button) findViewById(R.id.login_register);
		login_login = (Button) findViewById(R.id.login_login);
		login_name = (EditText) findViewById(R.id.login_name);
		login_passwd = (EditText) findViewById(R.id.login_passwd);
		login_jqqd = (LinearLayout) findViewById(R.id.login_jqqd);
	}

	
}
