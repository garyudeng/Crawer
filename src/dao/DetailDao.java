package dao;

import bean.BookDetail;
import bean.CommentDetail;
import bean.SumerizeDetail;
import bean.WeiboDetail;

public interface DetailDao {
	
	//存放一本书相关的所有明细
	int save(BookDetail bookDetail,CommentDetail commentDetail,WeiboDetail weiboDetail,SumerizeDetail sumerizeDetail);
	
}
