package dao;

import bean.Book;
import bean.BookDetail;

public interface IntegratedDao{
	public int insert2BookDetail(BookDetail detail);
	public int update2Book(Book book) ;
	public int insert2Book(Book book);
	public boolean isExit(Book book);
}
