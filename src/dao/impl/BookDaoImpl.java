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
/**
 * 	return new BaseDaoImpl().update(o,whereStr);
 * 	以上写法，会创建大量实体，增加代码耦合度，
 * 	使用spring ioc管理对象生命周期。
 * @author Lee
 *
 */
public class BookDaoImpl implements BookDao {
	
	
	@Override
	public List<HashMap<String, Object>> queryAll() {
		return null;//new Db().ExecuteQuery("select * from t_book");
	}

	@Override
	public List<HashMap<String, Object>> queryAll(Integer id, Integer size) {
		return null;//new Db().ExecuteQuery("select * from t_book limit "+id+", "+size);
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
	public Book query(Object o) throws Exception {
		
		return (Book) new BaseDaoImpl().query(o);
	}
	
}
