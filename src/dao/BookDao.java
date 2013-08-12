package dao;

import java.util.HashMap;
import java.util.List;


public interface BookDao extends BaseDao{
	
	
	public List<HashMap<String,Object>> queryAll();

	public List<HashMap<String, Object>> queryAll(Integer id, Integer size);
}
