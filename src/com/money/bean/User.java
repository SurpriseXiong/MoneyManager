package com.money.bean;

import java.io.Serializable;

public class User  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;
	private String passwd;
	/**
	 * @param username
	 * @param passwd
	 */
	public User(String username, String passwd) {
		this.username = username;
		this.passwd = passwd;
	}
	
	
	/**
	 * 无参数构造器
	 */
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPasswd() {
		return passwd;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	
	
	
}
