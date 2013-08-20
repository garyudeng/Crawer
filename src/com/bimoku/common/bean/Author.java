package com.bimoku.common.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Author implements Serializable{
	private int id;//主键
	private String uuId;//UUID
	private String name;//称谓
	private int is_translator;//是译者？缺省为0,表示身份为作者\n如果为1,表示是译者\n如说是2,表示两个身份
	public Author(){}
	public Author(int id, String uuId, String name, int is_translator) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.name = name;
		this.is_translator = is_translator;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getIs_translator() {
		return is_translator;
	}
	public void setIs_translator(int is_translator) {
		this.is_translator = is_translator;
	}
	
}
