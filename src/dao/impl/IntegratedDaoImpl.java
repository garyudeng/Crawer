package dao.impl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import util.RelationMapper;

import bean.Book;
import bean.BookDetail;
import dao.IntegratedDao;

/**
 * 集成类数据库方法实现
 * @author Administrator
 *
 */
@Repository("integratedDao")
public class IntegratedDaoImpl implements IntegratedDao{
	
	private static String FILE_PATH_NAME = "database.properties";
	
	@Autowired
	BaseDaoImpl baseDao;
	
	@Override
	public int insert2BookDetail(BookDetail detail){
		try {
			return baseDao.save(detail);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int update2Book(Book o) {
		try {
			return baseDao.update(o, " isbn = '"+o.getIsbn()+"'");
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	@Override
	public int insert2Book(Book book) {
		try {
			return baseDao.save(book);
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	//根据当前的书籍的isbn,在基本表里面查找是否存在，如果不存在则插入一则数据（基本数据）
	@Override
	public boolean isExit(Book book) {
		try{
			String isbn = book.getIsbn();
			//自己实现！！！
			String sql = "select count(*) myCount  from t_book where ISBN = ? ";
			System.out.println(sql);
			Connection conn = getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setObject(1, isbn);
			ResultSet rs = ps.executeQuery();
			int myCount = -1;
			
			while(rs.next()){
				myCount =rs.getInt("myCount");
			}
			if(myCount == 1) return true;
			
			//关闭资源
			close(conn, ps, rs);
		}catch(Exception e){
			e.printStackTrace();
		}
		return false;
	}
	
	public Connection getConnection() {
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
	
	
	public static void main(String[] args) throws JSONException {
		RelationMapper mapper = new RelationMapper();
//		mapper.setAM(312);
//		mapper.setDB(412);
//		mapper.setDD(512);
//		System.out.println(RelationMapper.bean2Json(mapper));
//		
		String str = "[{\"DD\":512,\"DB\":412,\"AM\":312}]";
		mapper = RelationMapper.str2Bean(str);
		System.out.println(mapper.getAM());
	}



}
