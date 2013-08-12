package dao.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import util.db.Db;

import bean.Book;
import dao.BaseDao;
import dao.BookDao;

@Repository("bookDao")
public class BookDaoImpl implements BookDao {
	

	


	
	@Override
	public List<HashMap<String, Object>> queryAll() {
		return new Db().ExecuteQuery("select * from t_book");
	}

	@Override
	public List<HashMap<String, Object>> queryAll(Integer id, Integer size) {
		return new Db().ExecuteQuery("select * from t_book limit "+id+", "+size);
	}

	@Override
	public int save(Object o) throws Exception {
		
		return new BaseDaoImpl().save(o);
	}

	@Override
	public int delete(Object o) throws Exception {
		
		return new BaseDaoImpl().delete(o);
	}

	@Override
	public int update(Object o, String whereStr) throws Exception {
		
		return new BaseDaoImpl().update(o,whereStr);
	}
	@Override
	public Object query(Object o) throws Exception {
		
		return new BaseDaoImpl().query(o);
	}
	
}
