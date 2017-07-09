package com.money.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class UserSqlLietHelper extends SQLiteOpenHelper {

	public UserSqlLietHelper(Context context) {
		super(context, "moneyManger.db", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql = "create table tbl_user(_id integer primary key autoincrement,username,passwd)";
		String sql2 = "create table tbl_account(_id integer primary key autoincrement,name,price,pattern,photo)";
		String sql3 ="create table tbl_record(_id integer primary key autoincrement,money,content,photo,day,type,aim)";
		db.execSQL(sql);
		db.execSQL(sql2);
		db.execSQL(sql3);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
