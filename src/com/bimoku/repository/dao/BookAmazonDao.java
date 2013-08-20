package com.bimoku.repository.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookAmazon;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.persistence.dao.BaseDao;
import com.bimoku.persistence.dao.impl.BaseDaoMysqlImpl;


/**
 * book对象基本操作
 * @author LPM
 *
 */
public interface BookAmazonDao extends BaseDao<BookAmazon, Integer>{
	public boolean isExit(String isbn) ;
}

@Repository("bookAmazonDao")
class BookAmazonDaoImpl extends BaseDaoMysqlImpl<BookAmazon, Integer> implements BookAmazonDao{
	public BookAmazonDaoImpl(){
		super(BookAmazon.class);
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
