package com.bimoku.repository.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.bimoku.common.bean.Book;
import com.bimoku.persistence.dao.BaseDao;
import com.bimoku.persistence.dao.impl.BaseDaoMysqlImpl;


/**
 * book对象基本操作
 * @date 2013-8-20
 * @version v0.1.2[last version]
 * @author LPM
 *
 */
public interface BookDao extends BaseDao<Book, Integer>{
	boolean isExit(String isbn);
	Book getBookByISBN(String isbn);
}

@Repository("bookDao")
class BookDaoImpl extends BaseDaoMysqlImpl<Book, Integer> implements BookDao{
	BookDaoImpl(){
		super(Book.class);
	}

	@Override
	public boolean isExit(String isbn) {
		List<String> keys = new ArrayList<String>();
		List<Object> values = new ArrayList<Object>();
		keys.add("ISBN");
		values.add(isbn);
		return this.isExit(keys, values);
	}

	@Override
	public Book getBookByISBN(String isbn) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("ISBN", isbn);
		return this.searchOne(map);
	}
}
