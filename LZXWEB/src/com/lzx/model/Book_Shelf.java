package com.lzx.model;

public class Book_Shelf {	//书架类
	private int primary_key;	//主键
	private String storey_number;	//楼层号
	private String area_number;	//区号
	private String shelf_number;		//书架号
	private String search_number_type;  //索书号类型
	private String search_number_start; //索书号开始
	private String search_number_end;	//索书号结束
	private int zhuzi;
	private String search_information="AA";
	
	public String getSearch_information() {
		return search_information;
	}
	public void setSearch_information(String search_information) {
		this.search_information = search_information;
	}
	public int getZhuzi() {
		return zhuzi;
	}
	public void setZhuzi(int zhuzi) {
		this.zhuzi = zhuzi;
	}
	//构造函数
	//空构造
	public Book_Shelf(){
		
	}
	//带参构造
	public Book_Shelf(int primary_key,String storey_number,String area_number,String shelf_number,String search_number_type,String search_number_start,String search_number_end,int zhuzi){
		this.primary_key = primary_key;
		this.storey_number = storey_number;
		this.area_number = area_number;
		this.shelf_number = shelf_number;
		this.search_number_type = search_number_type;
		this.search_number_start = search_number_start;
		this.search_number_end = search_number_end;	
		this.zhuzi = zhuzi;
	}
	
	public int getPrimary_key() {
		return primary_key;
	}
	public void setPrimary_key(int primary_key) {
		this.primary_key = primary_key;
	}
	public String getStorey_number() {
		return storey_number;
	}
	public void setStorey_number(String storey_number) {
		this.storey_number = storey_number;
	}
	@Override
	public String toString() {
		return "Book_Shelf [primary_key=" + primary_key + ", storey_number=" + storey_number + ", area_number="
				+ area_number + ", shelf_number=" + shelf_number + ", search_number_type=" + search_number_type
				+ ", search_number_start=" + search_number_start + ", search_number_end=" + search_number_end
				+ ", zhuzi=" + zhuzi + "]";
	}
	public String getArea_number() {
		return area_number;
	}
	public void setArea_number(String area_number) {
		this.area_number = area_number;
	}
	public String getShelf_number() {
		return shelf_number;
	}
	public void setShelf_number(String shelf_number) {
		this.shelf_number = shelf_number;
	}
	public String getSearch_number_type() {
		return search_number_type;
	}
	public void setSearch_number_type(String search_number_type) {
		this.search_number_type = search_number_type;
	}
	public String getSearch_number_start() {
		return search_number_start;
	}
	public void setSearch_number_start(String search_number_start) {
		this.search_number_start = search_number_start;
	}
	public String getSearch_number_end() {
		return search_number_end;
	}
	public void setSearch_number_end(String search_number_end) {
		this.search_number_end = search_number_end;
	}

}
