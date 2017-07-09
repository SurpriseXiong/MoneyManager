package com.gress.moneymanager;

import java.io.UnsupportedEncodingException;

import com.book.utils.ToastUtils;
import com.money.bean.User;
import com.money.db.UserDao;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class RegisterActivity extends Activity {
	
	private ImageButton register_back; // 返回按钮
	
	private EditText register_name,register_passwd,register_passwdagain;//三个输入框
	
	Button register_register,register_reset; // 注册和重置
	private UserDao userdao;
	//重置按钮
	public void registereset(View view ){
		register_name.setText("");
		register_passwd.setText("");
		register_passwdagain.setText("");
	}
	public void registeregiste (View  view ){
		String name = register_name.getText().toString();
		String passwd = register_passwd.getText().toString();
		String passwdagain = register_passwdagain.getText().toString();
		User user = new User(name, passwdagain);
		//查找用户是不是已经存在返回一个user的queryUserByName对象
		User queryUserByName = userdao.queryUserByName(name);
		if (name.equals("")) {
			ToastUtils.showToast(getApplication(),  "请输入用户名");
		} else {
		if ( queryUserByName!=null) {
			ToastUtils.showToast(getApplication(),  "用户已经存在");
		} else if (!passwd.equals(passwdagain)||passwd.equals("")) {
			ToastUtils.showToast(getApplication(),"输入 的两次密码不相同或者密码为空");
		}else {
			userdao.addUser(user);
			ToastUtils.showToast(getApplication(),"注册成功:账号是"+name+"   密码是;"+passwd);
			finish();
		}
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		initView();
		initListener();
	}
	private void initListener() {
		//返回按钮
		register_back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//重置数据按钮
		/*register_reset.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				register_name.setText("");
				register_passwd.setText("");
				register_passwdagain.setText("");
			}
		});*/
		// 注册按钮监听
		/*register_register.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String name = register_name.getText().toString();
				String passwd = register_passwd.getText().toString();
				String passwdagain = register_passwdagain.getText().toString();
				User user = new User(name, passwdagain);
				//查找用户是不是已经存在返回一个user的queryUserByName对象
				User queryUserByName = userdao.queryUserByName(name);
				if ( queryUserByName.getUsername().equals(name)) {
					ToastUtils.showToast(getApplication(),  "用户已经存在");
				} else if (!passwd.equals(passwdagain)) {
					ToastUtils.showToast(getApplication(),"输入 的两次密码不相同");
				}else {
					userdao.addUser(user);
					ToastUtils.showToast(getApplication(),"注册成功");
				}
			}
		});*/
	}
	
	private void initView() {
		userdao = new UserDao(getBaseContext());
		register_back = (ImageButton) findViewById(R.id.registe_back);
		register_name = (EditText) findViewById(R.id.registe_name);
		register_passwd= (EditText) findViewById(R.id.registe_passwd);
		register_passwdagain = (EditText) findViewById(R.id.registe_passwdagain);
		register_register = (Button) findViewById(R.id.registe_registe);
		register_reset = (Button) findViewById(R.id.registe_reset);
	}

	
	
	
	
}
