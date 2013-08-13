package dao;

public interface BaseDao {
	
    public int save(Object o) throws  Exception;
	
	public int delete(Object o) throws  Exception;
	
	public int update(Object o,String whereStr) throws Exception;
	
	public Object query(Object o) throws Exception;
}
