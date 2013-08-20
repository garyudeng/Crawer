package com.bimoku.common.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SumerizeDetail implements Serializable{
	private int id;// 主键
	private String uuId;// UUID
	private int bookid;//所属书籍
	private int site;// 站点
	
	private String content_intro;//内容介绍
	private String series_intro;//系列丛书介绍
	private String edit_intro;//编辑介绍
	private String media_intro;//媒体介绍
	private String author_intro;//作者介绍
	private String thranlator_intro;//译者介绍
	public SumerizeDetail(int id, String uuId, int bookid, int site,
			String content_intro, String series_intro, String edit_intro,
			String media_intro, String author_intro, String thranlator_intro) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.bookid = bookid;
		this.site = site;
		this.content_intro = content_intro;
		this.series_intro = series_intro;
		this.edit_intro = edit_intro;
		this.media_intro = media_intro;
		this.author_intro = author_intro;
		this.thranlator_intro = thranlator_intro;
	}
	public SumerizeDetail() {
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
	public String getContent_intro() {
		return content_intro;
	}
	public void setContent_intro(String content_intro) {
		this.content_intro = content_intro;
	}
	public String getSeries_intro() {
		return series_intro;
	}
	public void setSeries_intro(String series_intro) {
		this.series_intro = series_intro;
	}
	public String getEdit_intro() {
		return edit_intro;
	}
	public void setEdit_intro(String edit_intro) {
		this.edit_intro = edit_intro;
	}
	public String getMedia_intro() {
		return media_intro;
	}
	public void setMedia_intro(String media_intro) {
		this.media_intro = media_intro;
	}
	public String getAuthor_intro() {
		return author_intro;
	}
	public void setAuthor_intro(String author_intro) {
		this.author_intro = author_intro;
	}
	public String getThranlator_intro() {
		return thranlator_intro;
	}
	public void setThranlator_intro(String thranlator_intro) {
		this.thranlator_intro = thranlator_intro;
	}
	
}
