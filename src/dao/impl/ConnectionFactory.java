package dao.impl;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	
	private ConnectionFactory() {}

	private static ComboPooledDataSource ds = null;

	static {
		try {
			// Logger log = Logger.getLogger("com.mchange"); // 日志
			// log.setLevel(Level.WARNING);
			ds = new ComboPooledDataSource();
			// 设置JDBC的Driver类
			ds.setDriverClass("com.mysql.jdbc.Driver"); // 参数由 Config
			// 类根据配置文件读取
			// 设置JDBC的URL
			ds.setJdbcUrl("jdbc:mysql://localhost:3306/bimokudb?useUnicode=true&characterEncoding=utf-8");
			// 设置数据库的登录用户名
			ds.setUser("root");
			// 设置数据库的登录用户密码
			ds.setPassword("195891");
			// 设置连接池的最大连接数
			ds.setMaxPoolSize(200);
			// 设置连接池的最小连接数
			ds.setMinPoolSize(20);
			//有时候会产生死锁，将maxStatements设置为0
			//ds.setMaxStatements(0);
			//定义在从数据库获取新连接失败后重复尝试的次数。Default: 30
			ds.setAcquireRetryAttempts(20);
			//当连接池中的连接耗尽的时候c3p0一次同时获取的连接数。Default: 3
			ds.setAcquireIncrement(50);
			//当连接池用完时客户端调用getConnection()后等待获取新连接的时间，超时后将抛出 
			ds.setCheckoutTimeout(20* 1000);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
	}

	public static synchronized Connection getConnection() {
		Connection con = null;
		try {
			con = ds.getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return con;
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				if (ps != null) 
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				try {
					if (conn != null) 
						conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// C3P0 end
}
