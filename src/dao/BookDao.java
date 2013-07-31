package dao;

import bean.Book;

public interface BookDao{
	public int save(Book o) throws  Exception;
	
	public int delete(Book o) throws  Exception;
	
	public int update(Book o,String whereStr) throws Exception;
	
	public Book query(Book o) throws Exception;
}
