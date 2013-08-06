package dao;

import bean.Book;
import bean.BookDetail;

public interface IntegratedDao{
	public BookDetail insert2BookDetail(BookDetail detail);
	public int update2Book(Book book) ;
	public int insert2Book(Book book);
	
	/**
	 * 判断在某张表里面是否存在某本书！！
	 * @param table
	 * @param isbn
	 * @return
	 */
	public boolean isExit(String table,String isbn);
}
