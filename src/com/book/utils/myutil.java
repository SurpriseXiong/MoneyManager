package com.book.utils;

import java.util.UUID;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

/**
 * 自定义工具类
 * @author Administrator
 *
 */
public class myutil {
	/**
	 * 
	 * @return 一个32位的string类型的uuid,可用于主键生成
	 */
	public static String getUuid(){
		UUID id= UUID.randomUUID();
		String temp = id.toString().replaceAll("-", "");
		return temp;
	}
	
	/**
	 * 检测shareperference中是否存有用户数据,
	 * @param context 表示上下文，用于获得SharedPreferences对象
	 * @return 用户登录状态 true 表示已经登陆，false表示未登录
	 */
	public static boolean checkLogin(Context context){
		SharedPreferences sp = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		if(!TextUtils.isEmpty(sp.getString("username", null))){
			return true;
		}
		return false;
	}
	
	/**
	 * 
	 * @param context 表示上下文，用于获得SharedPreferences对象
	 * @return 如果用户登录了，返回用户名，否则返回null
	 */
	public static String getLogin_User(Context context){
		SharedPreferences sp = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		return sp.getString("username", null);
	}
	
	/**
	 * 
	 * @param context 表示上下文，用于获得SharedPreferences对象
	 * @return 如果用户登录了，返回用户名，否则返回null
	 */
	public static void removeUser(Context context){
		SharedPreferences sp = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.remove("username");
		editor.commit();
	}
	
	/**
	 * 
	 * @param context 表示上下文，用于获得SharedPreferences对象
	 * @param username 用户名
	 * @param value 值
	 */
	public static void addUser(Context context,String username,String value){
		SharedPreferences sp = context.getSharedPreferences("userinfo",Context.MODE_PRIVATE);
		Editor editor = sp.edit();
		editor.putString(username, value);
		editor.commit();
	}
	
}
