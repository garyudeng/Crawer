package com.bimoku.repository.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookDD;
import com.bimoku.persistence.dao.BaseDao;
import com.bimoku.persistence.dao.impl.BaseDaoMysqlImpl;


/**
 * book对象基本操作
 * @author LPM
 *
 */
public interface BookDDDao extends BaseDao<BookDD, Integer>{
	public boolean isExit(String isbn) ;
}

@Repository("bookDDDao")
class BookDDDaoImpl extends BaseDaoMysqlImpl<BookDD, Integer> implements BookDDDao{
	public BookDDDaoImpl(){
		super(BookDD.class);
	}
	
	@Override
	public boolean isExit(String isbn) {
		List<String> keys = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		keys.add("ISBN");
		values.add(isbn);
		return this.isExit(keys, values);
	}
}
