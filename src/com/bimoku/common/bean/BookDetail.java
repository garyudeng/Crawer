package com.bimoku.common.bean;


import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.codehaus.jettison.json.JSONException;

import com.bimoku.util.AllPriceMapper;
import com.bimoku.util.RelationMapper;

/**
 * @Intro 明细书库数据类型
 * @author LPM
 * @date 2013-8-20
 * @version last v0.1.2
 */
@SuppressWarnings("serial")
public abstract class BookDetail implements Serializable{
	private Integer id;//主键
	private String uuId;//UUID
	private String isbn;//图书编号<15
	private String bookName;//图书名<45
	private String author;//作者<45
	private String translator;//译者<45
	private String press;//出版社<45
	private String version;//版本<4
	private String cover_pic;//封面图片<45
	private Double price;//单价
	private Double pub_price;//单价
	
	private String outLine;//概述<2000
	private String directory;//目录<1000【值得是：图书-》小说-》文学】
	private String catelog;//分类<50
	private String authorIntro;//作者简介<215
	
	public BookDetail(){}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	public String getIsbn() {
		return isbn;
	}
	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
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


	public String getCover_pic() {
		return cover_pic;
	}
	public void setCover_pic(String cover_pic) {
		this.cover_pic = cover_pic;
	}
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getPub_price() {
		return pub_price;
	}
	public void setPub_price(Double pub_price) {
		this.pub_price = pub_price;
	}
	public String getOutLine() {
		return outLine;
	}
	public void setOutLine(String outLine) {
		this.outLine = outLine;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
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
	
	public static Book convert2Book(BookDetail detail) throws JSONException {
		
		Book b = new Book();
		
		b.setAuthor(detail.getAuthor());
		b.setBookname(detail.getBookName());
		b.setDirectory(detail.getDirectory());
		b.setIsbn(detail.getIsbn());
		b.setOutline(detail.getOutLine());
		b.setPress(detail.getPress());
		b.setPrice(detail.getPrice());
		b.setPub_price(detail.getPub_price());
		b.setTranslator(detail.getTranslator());
		b.setAuthorIntro(detail.getAuthorIntro());
		b.setCatelog(detail.getCatelog());
		b.setCover_pic(detail.getCover_pic());
		
		b.setRelationship(RelationMapper.builtWithClass(detail));
		b.setAll_price(AllPriceMapper.builtWithClass(detail));
		
		return b;
	}
	

	@Override
	public String toString() {
		return "BookDetail [id=" + id + ", uuId=" + uuId + ", isbn=" + isbn
				+ ", bookName=" + bookName + ", author=" + author
				+ ", translator=" + translator + ", press=" + press
				+ ", version=" + version + ", cover_pic=" + cover_pic
				+ ", price=" + price + ", pub_price=" + pub_price
				+ ", outLine=" + outLine + ", directory=" + directory
				+ ", catelog=" + catelog + ", authorIntro=" + authorIntro + "]";
	}

}
