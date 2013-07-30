package dao.impl;

import dao.BookDao;

public class BookDaoImpl implements BookDao {
	@Override
	public int save(Object o) throws Exception {
		
		return new BaseDaoImpl().save(o);
	}
	
	@Override
	public int delete(Object o) throws  Exception {
		return new BaseDaoImpl().delete(o);
	}

	@Override
	public int update(Object o,String whereStr) throws Exception {
		return new BaseDaoImpl().update(o,whereStr);
	}
	


	@Override
	public Object query(Object o) throws Exception {
		
		return new BaseDaoImpl().query(o);
	}
	
}
