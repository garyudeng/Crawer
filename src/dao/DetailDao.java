package dao;

import bean.BookDD;
import bean.CommentDetail;
import bean.SumerizeDetail;
import bean.WeiboDetail;

public interface DetailDao {
	
	//存放一本书相关的所有明细
	int save(BookDD bookDetail,CommentDetail commentDetail,WeiboDetail weiboDetail,SumerizeDetail sumerizeDetail);
	
}
