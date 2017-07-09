package com.gress.moneymanager;

import com.book.utils.ToastUtils;
import com.money.db.UserDao;

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
import android.widget.TextView;

public class MycenterActivity extends Activity {
	private UserDao dao ;
	private TextView mycenter_name;
	private SharedPreferences sp;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mycenter);
		initView();
		initDate();
	}
	
	//返回更多fragment界面
	public void myback(View view ){
		finish();
	}
	//修改密码 事件
	public void udpatepwd(View view ){
		AlertDialog.Builder builder = new AlertDialog.Builder(this); 
		View view1 = View.inflate(this, R.layout.iteam_updatepsd, null);
        final AlertDialog alertDialog = builder.create();  
        alertDialog.setView(view1);  
        alertDialog.show(); 
        Button update_back = (Button) view1.findViewById(R.id.update_back);
        Button update_submit = (Button) view1.findViewById(R.id.update_update);
        //弹出框的内输入的两次密码
        final EditText userupdate_1psd = (EditText) view1.findViewById(R.id.userupdate_1psd);
        final EditText userupdate_2psd = (EditText) view1.findViewById(R.id.userupdate_2psd);
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
				String thisname =  mycenter_name.getText().toString();
				String pwd1 = userupdate_1psd.getText().toString();
				String pwd2 = userupdate_2psd.getText().toString();
				if (pwd1.equals("")) {
					ToastUtils.showToast(getBaseContext(), "请输入要更改的密码");
				} else if (!pwd1.equals(pwd2)) {
					ToastUtils.showToast(getBaseContext(), "输入的两次密码不相同");
				}else {
					dao.UpdatepwdByName(thisname, pwd1);
					ToastUtils.showToast(getBaseContext(), "修改成功");
					alertDialog.dismiss();  
				}
				
				
				
			}
		});
	}
	//退出按钮
	public void Btback(View view){
		String name = "请登录";
		Intent intent = new Intent();
		intent.putExtra("name", name);
		setResult(3,intent);	// 反馈码resultCode= 3
		sp=getSharedPreferences("user_info",Context.MODE_APPEND);
		Editor ed = sp.edit();
		ed.putString("login", "0");
		ed.putString("user", "");
		ed.commit();
		finish();
	}
	
	private void initDate() {
		// TODO Auto-generated method stub
		Intent intent=getIntent();
		String name=intent.getStringExtra("name");
		mycenter_name.setText(name);
	}
	
	private void initView() {
		dao = new UserDao(getApplicationContext());
		mycenter_name = (TextView) findViewById(R.id.mycenter_name);
	}

	
}
