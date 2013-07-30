package bean;

/***************************************
File Name：图书
Author:孙武斌
Last Modified Time: 2013/07/30
Utility:Bean
***************************************/

public class Book {
	private int id;//主键
	private String uuId;//UUID
	private String bookName;//图书名
	private String author;//作者
	private String translAtors;//译者
	private String press;//出版社
	private String version;//版本
	private String isbn;//图书编号
	private String outline;//图书概述
	private Integer wantRead;//想读人数
	private Integer reading;//在读人数
	private Integer read;//读过人数
	private String directory;//目录
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
	public String getTranslAtors() {
		return translAtors;
	}
	public void setTranslAtors(String translAtors) {
		this.translAtors = translAtors;
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
	public Integer getRead() {
		return read;
	}
	public void setRead(Integer read) {
		this.read = read;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}

}
