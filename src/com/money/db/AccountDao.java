package com.money.db;

import java.util.ArrayList;
import java.util.List;







import com.money.bean.Account;
import com.money.bean.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class AccountDao {
	private UserSqlLietHelper helper;
	private SQLiteDatabase db ;
	/**
	 * 
	 */
	public AccountDao( Context context) {
		helper = new UserSqlLietHelper(context);
	}
	
	
	/**
	 * 增加资金类型  不需要返回值，但需要传一个Account accoun对象
	 */
	public void addAccount(Account account){
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("name", account.getName());
		values.put("price", account.getMoney());
		values.put("pattern", account.getPattern());
		values.put("photo", account.getPhoto());
		db.insert("tbl_account", null, values );
		Log.i("AccountDao", "addaccount success");
	}
	
	/**
	 * 查找所以的资金类型  返回一个List<Account> list = new ArrayList<Account>();
	 * @return
	 */
	public List<Account> queryAllAccont(){
		
		List<Account> list = new ArrayList<Account>();
		db = helper.getReadableDatabase();
		Cursor cursor = db.query("tbl_account", null, null, null, null, null, null);
		while (cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			double money = cursor.getDouble(cursor.getColumnIndex("price"));
			int photo = cursor.getInt(cursor.getColumnIndex("photo"));
			String pattern = cursor.getString(cursor.getColumnIndex("pattern"));
			Account account = new Account(name, money,pattern,photo);
			list.add(account);
		}
		return list;
	}

	
	/**
	 * 根据名字去数据库查询数据，防止账户重名
	 * 有account 就有数，没有就是null
	 * @param onename
	 * @return account 
	 */
	public Account queryAccountByName(String onename) {
		
		Account account = null;
		db = helper.getReadableDatabase();
		Cursor cursor = db.query("tbl_account", null, "name=?", new String[]{onename}, null, null, null);
		while(cursor.moveToNext()){
			String name = cursor.getString(cursor.getColumnIndex("name"));
			double price = cursor.getDouble(cursor.getColumnIndex("price"));
			account = new Account(name,price);
		}
		return account;
	}
	 
	/**
	 * 求资金总数
	 * @return  double sum ;
	 */
	public double  SumAccount(){
		
		List<Account> summoney = new ArrayList<Account>();
		summoney = queryAllAccont();   //查到所以的资金对象
		double sum = 0;
		//判断当没有资金的时候
		if (summoney!=null) {
			for (int i = 0; i < summoney.size(); i++) {
				sum = sum + summoney.get(i).getMoney();
			}
			
		}
		return sum;
	}
	
	
	public int findIdByName(String name){
		
		int id =0;
		db = helper.getReadableDatabase();
		Cursor cursor = db.query("tbl_account", new String[]{"_id"}, "name=?", new String []{name}, null, null, null);
		//int id = cursor.getInt(0);
		while (cursor.moveToNext()) {
			 id = cursor.getInt(cursor.getColumnIndex("_id"));
		}
		return id;
	}
	
	/**
	 * 新增记账改变资金数
	 * 根据资金名字去查询资金的id和资金余额，然后在用update更新数据
	 * money 是根据类型传*-1  *1
	 * 用到Account account = queryAccountByName(aim);	
	 * int id = findIdByName(aim);
	 */
	public void updaterecond(double money,String aim){
		db = helper.getWritableDatabase();
		Account account = queryAccountByName(aim);
		int id = findIdByName(aim);
		money = account.getMoney()+money;
		ContentValues values = new ContentValues();
		values.put("price", money);
		db.update("tbl_account", values, "_id=?", new String[]{id+""});
	}
	
	
	
	
}
