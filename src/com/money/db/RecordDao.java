package com.money.db;

import java.util.ArrayList;
import java.util.List;

import com.money.bean.Record;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class RecordDao {
	
	
	private UserSqlLietHelper helper;
	private SQLiteDatabase db;
	private AccountDao accountdao;
	public RecordDao(Context context){
		helper = new UserSqlLietHelper(context);
		 accountdao =new AccountDao(context);
	}
	
	
	
	//新增的记账类型
	public void addRecord(Record record){
		Log.i("dao层插入数据", "1");
		db = helper.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("money", record.getMoney());
		values.put("content", record.getContent());
		values.put("photo", record.getPhoto());
		values.put("day", record.getDay());
		values.put("type", record.getType());
		values.put("aim", record.getFrom());
		Log.i("dao层插入数据", "2");
		db.insert("tbl_record", null, values );
		Log.i("dao层插入数据", "3");
	}
	
	//查找所有的Recond 
	public List<Record> queryAllRecond(){
		db = helper.getReadableDatabase();
		List<Record> list = new ArrayList<Record>();
		Cursor cursor = db.query("tbl_record", null, null, null, null, null, null);
		while (cursor.moveToNext()) {
			int id = cursor.getInt(cursor.getColumnIndex("_id"));
			double money = cursor.getDouble(cursor.getColumnIndex("money")); // 收入或支出入钱
			String content = cursor.getString(cursor.getColumnIndex("content")); // 什么样的消费或者收入
			String day= cursor.getString(cursor.getColumnIndex("day")); // 日期
			String type= cursor.getString(cursor.getColumnIndex("type")); // 判断是收入还是支出 in是收入 out是支出
			int photo = cursor.getInt(cursor.getColumnIndex("photo")); // 图片
			String from = cursor.getString(cursor.getColumnIndex("aim"));
			Record record = new Record(money, content, photo, day, type, from);
			record.setId(id);
			list.add(record);
		}
		
		return list;
	}
	
	/**
	 * 
	 * 通过Id来查找一条记录的金钱数
	 * @param id
	 * @return
	 */
	public Record findMoneyById(int id ){
		Record record = null;
		Cursor cursor = db.query("tbl_record", null, "_id=?", new String[]{id+""}, null, null, null);
		while (cursor.moveToNext()){
			double money = cursor.getDouble(cursor.getColumnIndex("money"));
			String type= cursor.getString(cursor.getColumnIndex("type"));
			String from = cursor.getString(cursor.getColumnIndex("aim"));
			record = new Record(from, money, type);
		}
		return record;
	};

	/** 	查找ID
	 * @param content
	 * @return
	 */
	public int findIdByName(String content){
		int id =0;
		db = helper.getReadableDatabase();
		Cursor cursor = db.query("tbl_record", new String[]{"_id"}, "name=?", new String []{content}, null, null, null);
		//int id = cursor.getInt(0);
		while (cursor.moveToNext()) {
			 id = cursor.getInt(cursor.getColumnIndex("_id"));
		}
		return id;
	}
	
	/**
	 *  	 删除长按的记账数据
	 */
	public void deleteRecond( int id){
		db = helper.getWritableDatabase();
		Record record = findMoneyById(id);
		db.delete("tbl_record", "_id=?", new String[]{id+""});
		double money = record.getMoney();
		String aim = record.getFrom();
		if (record.getType().equals("in")) {
			//删除收入，所以需要减掉money
			
		accountdao.updaterecond(-money, aim);
		} else {
			//删除支出 ，所以需要加上money
			accountdao.updaterecond(money, aim);
		}
	}
	
	
	

	
	
}
