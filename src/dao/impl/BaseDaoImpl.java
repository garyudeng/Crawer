package dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import bean.BookDD;
import bean.BookDetail;

import dao.BaseDao;

/**
 * @DATE 13-08-06
 * @author **
 * 1、简单的crud
 * 2、加入父类属性兼容
 * 3、说有对象属性均需要对象类型
 * 5、更正查询bug
 */
@Service("baseDao")
public class BaseDaoImpl implements BaseDao {

	private static String FILE_PATH_NAME = "database.properties";

	public String init() {
		String re = "";
		try {
			InputStream in = getClass().getResourceAsStream(FILE_PATH_NAME);
			Properties props = new Properties();
			props.load(in);
			in.close();
			re = props.getProperty("url");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return re;
	}

	public Connection connection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			// String url =
			// "jdbc:mysql://localhost:3306/test?user=root&password=root&useUnicode=true&characterEncoding=gbk";
			String url = init();
			try {
				connection = DriverManager.getConnection(url);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return connection;
	}

	public int save(Object o) throws Exception {
		int reNumber = -1;
		String sql = "";
		Class<?> baseDao = o.getClass();
		List<Object> params = new ArrayList<Object>();

		Field[] fields = baseDao.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Method m = (Method) baseDao.getMethod(
					"get" + getMethodName(fields[i].getName()));
			Object val = m.invoke(o);
			if (val != null) {
				sql += fields[i].getName() + ",";
				params.add(val);
			}
		}
		
		if(o instanceof BookDetail){
			Class<?> su = o.getClass().getSuperclass();
			Field[] fd = su.getDeclaredFields();
			
			//把fd加到fields里面去
			fields = merge(fields,fd);
			
			for (int k = 0; k < fd.length; k++) {
				Method m = (Method) su.getMethod(
						"get" + getMethodName(fd[k].getName()));
				Object val = m.invoke(o);
				if (val != null) {
					sql += fd[k].getName() + ",";
					params.add(val);
				}
			}
		}
		
		
		if ((!sql.equals("")) && sql != "") {
			sql = sql.substring(0, sql.length() - 1);
			String table = o.getClass().getName();
			String parasStr = "";
			for (int i = 0; i < params.size(); i++) {
				parasStr += "?,";
			}
			parasStr = parasStr.substring(0, parasStr.length() - 1);
			sql = "insert into " + "t_"
					+ table.substring(table.indexOf(".") + 1, table.length())
					+ "(" + sql + ") values (" + parasStr + ")";
			
			System.out.println(sql);
			
			Connection conn = connection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			reNumber = ps.executeUpdate();
			close(conn, ps, null);
		}

		return reNumber;
	}

	public int delete(Object o) throws Exception {
		int reNumber = -1;
		String sql = "";
		Class<?> baseDao = o.getClass();
		List<Object> params = new ArrayList<Object>();
		Field[] fields = baseDao.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Method m = (Method) o.getClass().getMethod(
					"get" + getMethodName(fields[i].getName()));
			Object val = m.invoke(o);
			if (val != null) {
				sql += fields[i].getName() + "=?,";
				params.add(val);
			}
		}
		
		if(o instanceof BookDetail){
			Class<?> su = o.getClass().getSuperclass();
			Field[] fd = su.getDeclaredFields();
			
			//把fd加到fields里面去
			fields = merge(fields,fd);
			
			for (int k = 0; k < fd.length; k++) {
				Method m = (Method) su.getMethod(
						"get" + getMethodName(fd[k].getName()));
				Object val = m.invoke(o);
				if (val != null) {
					sql += fd[k].getName() + "=?,";
					params.add(val);
				}
			}
		}
		
		if ((!sql.equals("")) && sql != "") {
			sql = sql.substring(0, sql.length() - 1);
			String table = o.getClass().getName();
			sql = "delete from  " + "t_"
					+ table.substring(table.indexOf(".") + 1, table.length())
					+ " where " + sql;
			System.out.println(sql);
			Connection conn = connection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			reNumber = ps.executeUpdate();
			
			close(conn, ps, null);
		}

		return reNumber;
	}

	public int update(Object o, String whereStr) throws Exception {
		int reNumber = -1;
		String sql = "";
		Class<?> baseDao = o.getClass();
		List<Object> params = new ArrayList<Object>();
		Field[] fields = baseDao.getDeclaredFields();
		
		for (int i = 0; i < fields.length; i++) {
			Method m = (Method) o.getClass().getMethod(
					"get" + getMethodName(fields[i].getName()));
			Object val = m.invoke(o);
			if (val != null) {
				sql += fields[i].getName() + "=?,";
				params.add(val);
			}
		}
		
		if(o instanceof BookDetail){
			Class<?> su = o.getClass().getSuperclass();
			Field[] fd = su.getDeclaredFields();
			//把fd加到fields里面去
			fields = merge(fields,fd);
			
			for (int k = 0; k < fd.length; k++) {
				Method m = (Method) su.getMethod(
						"get" + getMethodName(fd[k].getName()));
				Object val = m.invoke(o);
				if (val != null) {
					sql += fd[k].getName() + "=?,";
					params.add(val);
				}
			}
		}
		
		if ((!sql.equals("")) && sql != "") {
			sql = sql.substring(0, sql.length() - 1);
			String table = o.getClass().getName();
			sql = "update " + "t_"
					+ table.substring(table.indexOf(".") + 1, table.length())
					+ " set " + sql + " where " + whereStr;
			System.out.println(sql);
			Connection conn = connection();
			PreparedStatement ps = conn.prepareStatement(sql);
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			reNumber = ps.executeUpdate();
			close(conn, ps, null);
		}
		return reNumber;
	}

	public Object query(Object o) throws Exception {
		String sql = "";
		String queryString = "";
		Class<?> baseDao = o.getClass();
		Field[] fields = baseDao.getDeclaredFields();//当前类的字段
		Field[] fd = null;//父类的字段
		Object father = null;
		
		List<Object> params = new ArrayList<Object>();
		
		for (int i = 0; i < fields.length; i++) {
			Method m = (Method) o.getClass().getMethod(
					"get" + getMethodName(fields[i].getName()));
			Object val = m.invoke(o);
			queryString += fields[i].getName() + ",";
			if (val != null) {
				sql += fields[i].getName() + "=? and ";
				params.add(val);
			}
		}
		
		if(o instanceof BookDetail){
			Class<?> su = o.getClass().getSuperclass();
			fd = su.getDeclaredFields();
			father = su.newInstance();
//			//把fd加到fields里面去
//			fields = merge(fields,fd);
			
			for (int k = 0; k < fd.length; k++) {
				Method m = (Method) su.getMethod(
						"get" + getMethodName(fd[k].getName()));
				Object val = m.invoke(o);
				
				queryString += fd[k].getName() + ",";
				if (val != null) {
					sql += fd[k].getName() + "=? and ";
					params.add(val);
				}
			}
		}
				
		if ((!sql.equals("")) && sql != "") {
			sql = sql.substring(0, sql.length() - 4);//4表示‘and ’
			queryString = queryString.substring(0, queryString.length() - 1);
			
			String table = o.getClass().getName();
			sql = "select " + queryString + " from t_"
					+ table.substring(table.indexOf(".") + 1, table.length())
					+ " where " + sql;
			
			System.out.println(sql);
			
			Connection conn = connection();
			PreparedStatement ps = conn.prepareStatement(sql);
			
			for (int i = 0; i < params.size(); i++) {
				ps.setObject(i + 1, params.get(i));
			}
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < fields.length; i++) {
					Method m = (Method) o.getClass().getMethod(
							"set" + getMethodName(fields[i].getName()),
							fields[i].getType());
					
					m.invoke(o, rs.getObject(fields[i].getName()));
				}
				
				if(fd == null) continue;//终止当前循环！！！！
				//父类的属性！！！！！
				for (int i = 0; i < fd.length; i++) {
					Field f=fd[i];//取出每一个属性，如deleteDate  
		            Class<?> type=f.getType(); 
		             
					Method m = father.getClass().getMethod("set" + getMethodName(fd[i].getName()),type);
					
					f.setAccessible(true);
					Object data = rs.getObject(f.getName());
//					f.set(o,rs.getObject(fd[i].getName()));
//					f.set(o, m.invoke(father, rs.getObject(fd[i].getName())));
//					m.invoke(father, rs.getObject(f.getName()));
					f.set(o,data);
				}
			}
			close(conn, ps, rs);
		}
		return o;
	}

	private static String getMethodName(String fildeName) throws Exception {
		byte[] items = fildeName.getBytes();
		items[0] = (byte) ((char) items[0] - 'a' + 'A');
		return new String(items);
	}

	public void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				if (ps != null) {
					try {
						ps.close();
					} catch (SQLException e) {
						e.printStackTrace();
					} finally {
						if (conn != null) {
							try {
								conn.close();
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}
		}

	}
	
	
	public static Field[] merge(Field[] f1,Field[] f2){
		Field[] f3 = new Field[f1.length + f2.length];
		for (int j = 0; j < f1.length; ++j) {
			f3[j] = f1[j];
		}
		for (int j = 0; j < f2.length; ++j) {
			f3[f1.length + j] = f2[j];
		}
		return f3;
	}
}
