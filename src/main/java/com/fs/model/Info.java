package com.fs.model;

public class Info implements Cloneable {
	@Override
	public Object clone() throws CloneNotSupportedException {
		Info info = (Info) super.clone();
		return super.clone();
	}
	private int id;
	private int num;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Info() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Info(int id, int num, String name) {
		super();
		this.id = id;
		this.num = num;
		this.name = name;
	}
	@Override
	public String toString() {
		return "Info [id=" + id + ", num=" + num + ", name=" + name + "]";
	}
	
	

}
