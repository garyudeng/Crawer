package bean;

import java.io.Serializable;

/**
 * 图书明细
 * @author LPM
 * @Date 13-07-31
 */
@SuppressWarnings("serial")
public class BookDetail implements Serializable{
	private int id;//主键
	private String uuId;//UUID
	private String bookName;//图书名
	private String author;//作者
	private String translator;//译者
	private String press;//出版社
	private String version;//版本
	private String isbn;//图书编号
	private String directory;//目录
	private int level;//评级
	private String cover_pic;//封面图片
	private float price;//单价
	
	public BookDetail(){}

	public BookDetail(int id, String uuId, String bookName, String author,
			String translator, String press, String version, String isbn,
			String directory, int level, String cover_pic, float price) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.bookName = bookName;
		this.author = author;
		this.translator = translator;
		this.press = press;
		this.version = version;
		this.isbn = isbn;
		this.directory = directory;
		this.level = level;
		this.cover_pic = cover_pic;
		this.price = price;
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

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCover_pic() {
		return cover_pic;
	}

	public void setCover_pic(String cover_pic) {
		this.cover_pic = cover_pic;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}
	
	
}
