package com.fs.model;

public class page {
	private int num;//当前页
	private int size=5;//一页多少条数据
	private int start=0;//从都几条开始
	private int sum;//一共有多少条
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public page(int num, int size, int start, int sum) {
		super();
		this.num = num;
		this.size = size;
		this.start = start;
		this.sum = sum;
	}
	@Override
	public String toString() {
		return "page [num=" + num + ", size=" + size + ", start=" + start + ", sum=" + sum + "]";
	}
	

}
