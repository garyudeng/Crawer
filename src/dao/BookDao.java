package dao;

import java.util.HashMap;
import java.util.List;

import bean.Book;


public interface BookDao extends BaseDao{
	
	
	public List<HashMap<String,Object>> queryAll();

	public List<HashMap<String, Object>> queryAll(Integer id, Integer size);
	
	public Book query(Object o) throws Exception;
}
