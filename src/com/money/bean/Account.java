package com.money.bean;

import java.io.Serializable;

public class Account  implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pattern;
	private int photo;
	private String name;
	private double money;
	
	
	/**
	 * @param name
	 */

	/**
	 * @param name
	 * @param money
	 */
	public Account(String name, double money,String pattern,int photo) {
		this.name = name;
		this.money = money;
		this.pattern = pattern;
		this.photo = photo;
	}
	/**
	 * @param name
	 * @param money
	 */
	public Account(String name, double money) {
		this.name = name;
		this.money = money;
	}
	/**
	 * 
	 */
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getPattern() {
		return pattern;
	}
	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	public int getPhoto() {
		return photo;
	}
	public void setPhoto(int photo) {
		this.photo = photo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	

	
}
