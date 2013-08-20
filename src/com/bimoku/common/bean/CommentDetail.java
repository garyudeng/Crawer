package com.bimoku.common.bean;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class CommentDetail implements Serializable {
	private int id;// 主键
	private String uuId;// UUID
	private int bookid;//所属书籍
	private int site;// 站点
	
	private String com_user;// 称谓
	private String com_avatar;// 头像
	private String com_content;// 头像
	
	private Date create_at;// 称谓
	private Date update_at;// 称谓
	
	public CommentDetail(int id, String uuId, int bookid, int site,
			String com_user, String com_avatar,String com_content) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.bookid = bookid;
		this.site = site;
		this.com_user = com_user;
		this.com_avatar = com_avatar;
		this.com_content = com_content;
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
	public int getBookid() {
		return bookid;
	}
	public void setBookid(int bookid) {
		this.bookid = bookid;
	}
	public int getSite() {
		return site;
	}
	public void setSite(int site) {
		this.site = site;
	}
	public String getCom_user() {
		return com_user;
	}
	public void setCom_user(String com_user) {
		this.com_user = com_user;
	}
	public String getCom_avatar() {
		return com_avatar;
	}
	public void setCom_avatar(String com_avatar) {
		this.com_avatar = com_avatar;
	}
	public Date getCreate_at() {
		return create_at;
	}
	public void setCreate_at(Date create_at) {
		this.create_at = create_at;
	}
	public Date getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(Date update_at) {
		this.update_at = update_at;
	}
	public String getCom_content() {
		return com_content;
	}
	public void setCom_content(String com_content) {
		this.com_content = com_content;
	}
	
	
}
