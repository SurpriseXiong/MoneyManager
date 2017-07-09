package com.money.db;

import com.money.bean.User;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class UserDao {
		private UserSqlLietHelper helper;
		private SQLiteDatabase db ;
		/**
		 * 
		 */
		public UserDao( Context context) {
			helper = new UserSqlLietHelper(context);
		}
		
		/**
		 * 	新增加用户
		 * @param user
		 */
		public void addUser(User user ){
			db = helper.getWritableDatabase();
			ContentValues values = new ContentValues();
			values.put("username", user.getUsername());
			values.put("passwd", user.getPasswd());
			db.insert("tbl_user", null, values );
			Log.i("UserDao", "addUser success");
		}
		
		
		/*
		 * db.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy)
		 * distinct：是否查询（重复）相同的值☞，
		 * table ： 表名
		 * columns ： 需要查询的（程序）字段名或者（数据库中）  null 列名  select id name
		 * selection ： 查询条件 where name = ?
		 * selectionArgs ： 占位符的值，数值类型
		 * groupBy : 分组查询
		 * having ： 组函数的过滤  区别where 
		 * orderBy ： 升序或者降序排列   用的较多desc 降序 	asc 升序
		 * limit : 分页查询从（处理） "5，10"
		 * 
		*/
		/**
		 * 根据名字去数据库查询数据，防止用户重名
		 * 有username 就有数，没有就是null
		 * @param onename
		 * @return
		 */
		public User queryUserByName(String onename) {
			
			User user = null;
			db = helper.getReadableDatabase();
			Cursor cursor = db.query("tbl_user", null, "username=?", new String[]{onename}, null, null, null);
			while(cursor.moveToNext()){
				String name = cursor.getString(cursor.getColumnIndex("username"));
				String passwd = cursor.getString(cursor.getColumnIndex("passwd"));
				user = new User(name, passwd);
			}
			return user;
		}
		
		//	根据书名查询书的id用于修改图书  这里还是拿while循环，因为这里常常返回的是id的集合
		public int findIdByName(String name){
			int id =0;
			db = helper.getReadableDatabase();
			Cursor cursor = db.query("tbl_user", new String[]{"_id"}, "username=?", new String []{name}, null, null, null);
			while (cursor.moveToNext()) {
				 id = cursor.getInt(cursor.getColumnIndex("_id"));
			}
			return id;
		}
		
		//根据用户 name  查找Id去 修改密码
		public void UpdatepwdByName(String name,String pwd){
			db = helper.getWritableDatabase();
			int id = findIdByName(name);
			ContentValues values  = new ContentValues();
			values.put("username", name);
			values.put("passwd", pwd);
			db.update("tbl_user", values  , "_id=?", new String[]{id+""});
		};

}
