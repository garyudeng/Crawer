package com.bimoku.common.bean;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * @Intro 集成书库数据类型
 * @author LPM
 * @date 2013-8-20
 * @version last v0.1.2
 */
@Table(name="t_book")
@SuppressWarnings("serial")
public class Book implements Serializable{
	private Integer id;//主键
	private String uuId;//UUID
	private String isbn;//图书编号<15
	private String bookname;//图书名<45
	private String author;//作者<45
	private String translator;//译者<45
	private String press;//出版社<45
	private String version;//版本<4
	private String outline;//图书概述<2k
	private Integer wantRead;//想读人数
	private Integer reading;//在读人数
	private Integer hasread;//读过人数
	private String directory;//目录<1k
	private Double price;//单价
	private Double pub_price;//定价
	
	private String catelog;//分类<50
	private String authorIntro;//作者简介<215
	private String relationship;//关系列，存放：{'dd':'20','amazon':'231',}<50
	
	public Book(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
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

	public String getCatelog() {
		return catelog;
	}

	public void setCatelog(String catelog) {
		this.catelog = catelog;
	}

	public String getAuthorIntro() {
		return authorIntro;
	}

	public void setAuthorIntro(String authorIntro) {
		this.authorIntro = authorIntro;
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

	public Double getPub_price() {
		return pub_price;
	}

	public void setPub_price(Double pub_price) {
		this.pub_price = pub_price;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", uuId=" + uuId + ", isbn=" + isbn
				+ ", bookname=" + bookname + ", author=" + author
				+ ", translator=" + translator + ", press=" + press
				+ ", version=" + version + ", outline=" + outline
				+ ", wantRead=" + wantRead + ", reading=" + reading
				+ ", hasread=" + hasread + ", directory=" + directory
				+ ", price=" + price + ", pub_price=" + pub_price
				+ ", catelog=" + catelog + ", authorIntro=" + authorIntro
				+ ", relationship=" + relationship + "]";
	}
	
	
}
