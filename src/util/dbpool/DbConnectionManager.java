package util.dbpool;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Vector;

public class DbConnectionManager {

	static private DbConnectionManager instance;//唯一数据库连接池管理实例类
	static private int clients;//客户连接数
	 private Vector drivers=new Vector();//驱动信息
	 private Hashtable pools=new Hashtable();//连接池
	 
	 public DbConnectionManager(){
		 this.init();
	 }
	 
	 /**
	  * 创建连接池
	  * @param dsb
	  */
	 private void createPools(DsConfigBean dsb){
		 DbConnectionPool dbpool=new DbConnectionPool();
		 dbpool.setName(dsb.getName());
		 dbpool.setDriver(dsb.getDriver());
		 dbpool.setUrl(dsb.getUrl());
		 dbpool.setUser(dsb.getUsername());
		 dbpool.setPassword(dsb.getPassword());
		  dbpool.setMaxConn(dsb.getMaxconn());
		  System.out.println("ioio:"+dsb.getMaxconn());
		  pools.put(dsb.getName(), dbpool);
		 
	 }
	 
	 /**
	  * 初始化连接池的参数
	  */
	 private void init(){
		 //加载驱动程序
		 this.loadDrivers();
		 //创建连接池
		 Iterator alldriver=drivers.iterator();
		 while(alldriver.hasNext()){
			 this.createPools((DsConfigBean)alldriver.next());
			 System.out.println("创建连接池....");
		 }
		 System.out.println("创建连接池完毕.....");
	 }
	 /**
	  * 加载驱动程序
	  */
	 private void loadDrivers(){
		 ParseDsConfig pd=new ParseDsConfig();
		 //读取数据库配置文件
		 drivers=pd.readConfigInfo("ds.config.xml");
		 System.out.println("加载驱动程序....");
	 }
	 
	 
	 
}
