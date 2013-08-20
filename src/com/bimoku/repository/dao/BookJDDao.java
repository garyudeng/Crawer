package com.bimoku.repository.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.common.bean.BookJD;
import com.bimoku.persistence.dao.BaseDao;
import com.bimoku.persistence.dao.impl.BaseDaoMysqlImpl;


/**
 * book对象基本操作
 * @author LPM
 *
 */
public interface BookJDDao extends BaseDao<BookJD, Integer>{
	public boolean isExit(String isbn);
}

@Repository("bookJDDao")
class BookJDDaoImpl extends BaseDaoMysqlImpl<BookJD, Integer> implements BookJDDao{
	public BookJDDaoImpl(){
		super(BookJD.class);
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
