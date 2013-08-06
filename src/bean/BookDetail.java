package bean;

import java.io.Serializable;

import org.codehaus.jettison.json.JSONException;
import util.RelationMapper;

/**
 * 图书明细
 * @author LPM
 * @Date 13-07-31
 */
@SuppressWarnings("serial")
public class BookDetail implements Serializable{
	private Integer id;//主键
	private String uuId;//UUID
	private String bookName;//图书名
	private String author;//作者
	private String translator;//译者
	private String press;//出版社
	private String version;//版本
	private String isbn;//图书编号
	private String directory;//目录
	private String cover_pic;//封面图片
	private String outLine;//概述
	private Double price;//单价
	
//	private NetType netType = NetType.Default;//网站类型
	
	//private String netType = NET_DD;//网站类型
	
	public BookDetail(){}


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


	public String getOutLine() {
		return outLine;
	}


	public void setOutLine(String outLine) {
		this.outLine = outLine;
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
		b.setTranslator(detail.getTranslator());
		
		b.setRelationship(RelationMapper.builtWithClass(detail));
		return b;
	}


	@Override
	public String toString() {
		return "BookDetail [id=" + id + ", uuId=" + uuId + ", bookName="
				+ bookName + ", author=" + author + ", translator="
				+ translator + ", press=" + press + ", version=" + version
				+ ", isbn=" + isbn + ", directory=" + directory
				+ ", cover_pic=" + cover_pic + ", price=" + price
				+ ", outLine=" + outLine + "]";
	}
}
