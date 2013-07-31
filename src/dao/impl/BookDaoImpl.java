package dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import bean.Book;
import dao.BaseDao;
import dao.BookDao;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	
	@Autowired
	BaseDao baseDao;
	
	@Override
	public int save(Book o) throws Exception {
		
		return baseDao.save(o);
	}
	
	@Override
	public int delete(Book o) throws  Exception {
		return baseDao.delete(o);
	}

	@Override
	public int update(Book o,String whereStr) throws Exception {
		return baseDao.update(o,whereStr);
	}
	


	@Override
	public Book query(Book o) throws Exception {
		return (Book)baseDao.query(o);
	}
	
}
