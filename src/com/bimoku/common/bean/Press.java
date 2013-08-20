package com.bimoku.common.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Press implements Serializable{
	private int id;//主键
	private String uuId;//UUID
	private String name;
	private String address;
	private String phone;
	public Press(int id, String uuId, String name, String address, String phone) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.name = name;
		this.address = address;
		this.phone = phone;
	}
	public Press() {
		super();
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
