package dao.impl;

import integrated.RelationMapper;

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


import bean.Book;
import bean.BookDD;
import bean.BookDetail;
import dao.IntegratedDao;

/**
 * 集成类数据库方法实现
 * @author Administrator
 *
 */
@Repository("integratedDao")
public class IntegratedDaoImpl implements IntegratedDao{
	
	@Autowired
	BaseDaoImpl baseDao;
	
	@Override
	public BookDetail insert2BookDetail(BookDetail detail){
		try {
			if(baseDao.save(detail) != -1){
				Object object = baseDao.query(detail);
				return (BookDetail) object;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
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

	/**
	 * 根据当前的书籍的isbn,在基本表里面查找是否存在，
	 * 如果不存在则插入一则数据（基本数据）
	 * 也可查询详细表如：t_bookdd表中是否存在当前记录
	 */
	@Override
	public boolean isExit(String table,String isbn) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		int myCount = -1;
		
		try{
			//自己实现！！！
			String sql = "select count(*) myCount  from "+table+" where ISBN = ? ";
			
			System.out.println(sql);
			
			conn = ConnectionFactory.getConnection();//getConnection();
			ps = conn.prepareStatement(sql);
			
			ps.setObject(1, isbn);
			rs = ps.executeQuery();
			
			while(rs.next()){
				myCount = rs.getInt("myCount");
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			ConnectionFactory.close(conn, ps, rs);
		}
		
		if(myCount > 0)
			return true;
		return false;
	}
	
	
	public static void main(String[] args) throws JSONException {
		RelationMapper mapper = new RelationMapper();
//		mapper.setAM(312);
//		mapper.setDB(412);
//		mapper.setDD(512);
//		System.out.println(RelationMapper.bean2Json(mapper));
//		
//		String str = "[{\"DD\":512,\"DB\":412,\"AM\":312}]";
//		mapper = RelationMapper.str2Bean(str);
//		System.out.println(mapper.getAM());
	}



}
