package bean;

import java.io.Serializable;

/***************************************
File Name：图书
Author:孙武斌
Last Modified Time: 2013/07/30
Utility:Bean
***************************************/

@SuppressWarnings("serial")
public class Book implements Serializable{
	private Integer id;//主键
	private String uuId;//UUID
	private String bookname;//图书名
	private String author;//作者
	private String translator;//译者
	private String press;//出版社
	private String version;//版本
	private String isbn;//图书编号
	private String outline;//图书概述
	private Integer wantRead;//想读人数
	private Integer reading;//在读人数
	private Integer hasread;//读过人数
	private String directory;//目录
	private Double price;//单价
	
	private String relationship;//关系列，存放：{'dd':'20','amazon':'231',}
	
	public Book(){}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUuId() {
		return uuId;
	}

	public void setUuId(String uuId) {
		this.uuId = uuId;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String getPress() {
		return press;
	}

	public void setPress(String press) {
		this.press = press;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getOutline() {
		return outline;
	}

	public void setOutline(String outline) {
		this.outline = outline;
	}

	public Integer getWantRead() {
		return wantRead;
	}

	public void setWantRead(Integer wantRead) {
		this.wantRead = wantRead;
	}

	public Integer getReading() {
		return reading;
	}

	public void setReading(Integer reading) {
		this.reading = reading;
	}

	public Integer getHasread() {
		return hasread;
	}

	public void setHasread(Integer hasread) {
		this.hasread = hasread;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}
	
	
}
