package bean;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class NoteDetail implements Serializable{
	private int id;// 主键
	private String uuId;// UUID
	private int bookid;//所属书籍
	private int site;// 站点
	
	private String note_user;//记录人称谓
	private String note_content;//内容
	private String note_avatar;//头像
	
	private Date create_at;
	private Date update_at;
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
	public String getNote_user() {
		return note_user;
	}
	public void setNote_user(String note_user) {
		this.note_user = note_user;
	}
	public String getNote_content() {
		return note_content;
	}
	public void setNote_content(String note_content) {
		this.note_content = note_content;
	}
	public String getNote_avatar() {
		return note_avatar;
	}
	public void setNote_avatar(String note_avatar) {
		this.note_avatar = note_avatar;
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
	public NoteDetail(int id, String uuId, int bookid, int site,
			String note_user, String note_content, String note_avatar) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.bookid = bookid;
		this.site = site;
		this.note_user = note_user;
		this.note_content = note_content;
		this.note_avatar = note_avatar;
	}
	public NoteDetail() {
		
	}
	
	
}
