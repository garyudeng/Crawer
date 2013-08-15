package dao;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import util.db.Db;

public class test {
	public static void main(String[] args) throws SQLException {
		Db db = new Db();
		for(int a = 100; a<10000 ;a++){
			List<HashMap<String, Object>> list = db.ExecuteQuery("select count(*) from emp where empno = "+a);
			System.out.println(list);
		}
	}
}
