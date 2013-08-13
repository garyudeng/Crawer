package util.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Db {

	/**
	 * 
	 * @param sql
	 * @return
	 */
	public List<HashMap<String,Object>> ExecuteQuery(String sql){
		List<HashMap<String,Object>> datas=null;
		PreparedStatement sta=null;
		ResultSet rs=null;
		Connection conn=null;
		try{
			sta=conn.prepareStatement(sql);
			rs=sta.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int recount = rsmd.getColumnCount();
			String[] colLabels = new String[recount];
			for (int i = 0; i < recount; i++) {
				colLabels[i] = rsmd.getColumnLabel(i + 1);
			}
			datas=new ArrayList<HashMap<String,Object>>();
			while (rs.next()) {
				rs.getObject(1);
				HashMap<String, Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colLabels.length; i++) {
					data.put(colLabels[i], rs.getObject(colLabels[i]));
					// System.out.println(colLabels[i]
					// +"----->"+data.get(colLabels[i]));
				}
				datas.add(data);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return datas;
		
	}
	
	/**
	 * 
	 * @param ps
	 * @param rs
	 * @param conn
	 */
	public void close(Statement ps,ResultSet rs,Connection conn){
			try {
				if(rs!=null)rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try{
					if(ps!=null)ps.close();
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					try{
					if(conn!=null)conn.close();
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
		
	}
	
	/**
	 * 
	 * @param sql
	 * @return
	 */
	public int ExecuteNonQuery(String sql){
		int reNum=-1;
		Connection conn=null;
		Statement stat=null;
		try{
			stat=conn.createStatement();
			reNum=stat.executeUpdate(sql);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return reNum;
	}
	
	/**
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int ExecuteNonQuery(String sql,Object[] params){
		int reNum=-1;
		Connection conn=null;
		PreparedStatement ps=null;
		try{
			ps=conn.prepareStatement(sql);
			for(int i=0;i<params.length;i++){
				ps.setObject(i+1, params[i]);
			}
			reNum=ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(ps, null, conn);
		}
		
		return reNum;
	}
	
	/**
	 * 执行带参数的sql语句返回List<HashMap<String,Object>>
	 * @param sql 传入SQL语句
	 * @param params 参数
	 * @return List<HashMap<String,Object>>
	 */
	public List<HashMap<String,Object>> ExecuteQuery(String sql,Object[] params){
		List<HashMap<String,Object>> datas=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Connection conn=null;
		try{
			ps=conn.prepareStatement(sql);
			for (int i = 0; i < params.length; i++) {
				ps.setObject(i + 1, params[i]);
			}
			rs = ps.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
			int recount = rsmd.getColumnCount();
			String[] colLabels = new String[recount];
			for (int i = 0; i < recount; i++) {
				colLabels[i] = rsmd.getColumnLabel(i + 1);
			}
			datas = new ArrayList<HashMap<String, Object>>();
			while (rs.next()) {
				HashMap<String, Object> data = new HashMap<String, Object>();
				for (int i = 0; i < colLabels.length; i++) {
					data.put(colLabels[i], rs.getObject(colLabels[i]));
				}
				datas.add(data);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.close(ps, rs, conn);
		}
		return datas;
	}
	
	
	
}
