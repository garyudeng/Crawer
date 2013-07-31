package bean;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class WeiboDetail implements Serializable{
	private int id;// 主键
	private String uuId;// UUID
	private int bookid;//所属书籍
	private int site;// 站点
	
	private String weibo_user;// 称谓
	private String weibo_avatar;// 头像
	private String weibo_content;//内容
	
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
	public String getWeibo_user() {
		return weibo_user;
	}
	public void setWeibo_user(String weibo_user) {
		this.weibo_user = weibo_user;
	}
	public String getWeibo_avatar() {
		return weibo_avatar;
	}
	public void setWeibo_avatar(String weibo_avatar) {
		this.weibo_avatar = weibo_avatar;
	}
	public String getWeibo_content() {
		return weibo_content;
	}
	public void setWeibo_content(String weibo_content) {
		this.weibo_content = weibo_content;
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
	public WeiboDetail(int id, String uuId, int bookid, int site,
			String weibo_user, String weibo_avatar, String weibo_content) {
		super();
		this.id = id;
		this.uuId = uuId;
		this.bookid = bookid;
		this.site = site;
		this.weibo_user = weibo_user;
		this.weibo_avatar = weibo_avatar;
		this.weibo_content = weibo_content;
	}
	public WeiboDetail() {
		super();
	}
	
}
