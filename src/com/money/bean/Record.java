package com.money.bean;

import java.io.Serializable;

public class Record implements Serializable {
	private String from;
	double money; // 收入或支出入钱
	String content; // 什么样的消费或者收入
	String day; // 日期
	String type; // 判断是收入还是支出 in是收入 out是支出
	int photo; // 图片

	/**
	 * 有 id
	 * @param from
	 * @param id
	 * @param money
	 * @param content
	 * @param day
	 * @param type
	 * @param photo
	 */
	public Record(String from, int id, double money, String content,
			String day, String type, int photo) {
		this.from = from;
		this.id = id;
		this.money = money;
		this.content = content;
		this.day = day;
		this.type = type;
		this.photo = photo;
	}

	/**
	 * 没有Id
	 * @param from
	 * @param money
	 * @param content
	 * @param day
	 * @param type
	 * @param photo
	 */
	public Record(double money, String content, int photo, String day,
			String type,String from) {
		this.from = from;
		this.money = money;
		this.content = content;
		this.day = day;
		this.type = type;
		this.photo = photo;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



	/**
	 * @param money
	 *            收入或支出入钱
	 * @param content
	 *            什么样的消费或者收入
	 * @param day
	 *            日期
	 * @param type
	 *            判断是收入还是支出 in是收入 out是支出
	 * @param photo
	 *            图片
	 */
	public Record(double money, String content, int photo, String day,
			String type) {
		this.money = money;
		this.content = content;
		this.day = day;
		this.type = type;
		this.photo = photo;
	}

	/**
	 * @param from
	 * @param money
	 * @param type
	 */
	public Record(String from, double money, String type) {
		this.from = from;
		this.money = money;
		this.type = type;
	}

	/**
	 * 
	 */
	public Record() {
		super();
		// TODO Auto-generated constructor stub
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPhoto() {
		return photo;
	}

	public void setPhoto(int photo) {
		this.photo = photo;
	}

}
