package bimoku.extract.common;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyUtil {
	
	public final static String BOOKNAME = "BOOKNAME";
	public final static String AUTHOR="AUTHOR";
	public final static String TRANSLATOR="TRANSLATOR";
	public final static String PRESS="PRESS";
	public final static String VERSION="VERSION";
	public final static String ITEM_ID="ITEM_ID"; 
	public final static String ISBN="ISBN";
	public final static String intro_clearfix="intro_clearfix";
	public final static String PRICE = "PRICE";
	public final static String PUBLISHED_PRICE="PUBLISHED_PRICE";
	public final static String COVER_PIC = "COVER_PIC";
	public final static String CLASSFY="CLASSFY";
	public final static String EDITOR_CHOICE="EDITOR_"+"\\"+" CHOICE";
	public final static String CONTENT_CHOICE="CONTENT_"+"\\"+" CHOICE";
	public final static String AUTHOR_INTRO="AUTHOR_INTRO";
	public final static String DIRECTORY="DIRECTORY";
	public final static String MEDIA_REVIEWS = "MEDIA_"+"\\"+" REVIEWS";
	public final static String EXTRACT="EXTRACT";
	public final static String ATTACH_IMAGE_SHOW="ATTACH_IMAGE_SHOW";
	public final static String COMMENTURL="COMMENTURL";

	
	//property
	private static Properties p;
	// 使用类路径来连接配置文件
	private static PropertyUtil install;
	//配置文件路径
	private static String defaultLoacal = "ddConfig.properties";
	private static String configPath = "";
	
	static {
		InputStream i = getPropertyUtill().getClass().getResourceAsStream(defaultLoacal);
		try {
			getProperty().load(i);
		} catch (IOException e) {
			System.out.println("配置文件路径不正确" + e.getMessage());
		}
	}
	
	public static Properties getProperty(){
		if(p == null){
			p = new Properties();
		}
		return p;
	}
	
	public static Properties getProperty(String configPath){
		//如果不是当前路径。则强制修改配置文件路径
		if(!PropertyUtil.configPath.equals(configPath)){
			PropertyUtil.configPath = configPath;
			p = null;
		}
		
		if(p == null){
			p = getProperty();
			InputStream i = getPropertyUtill().getClass().getResourceAsStream(configPath);
			try {
				p.load(i);
			} catch (IOException e) {
				System.out.println("配置文件路径不正确" + e.getMessage());
			}
		}
		return p;
	}
	
	public static PropertyUtil getPropertyUtill(){
		if(install == null){
			install = new PropertyUtil();
		}
		return install;
	}
	
	/**
	 * 读取键值数据
	 * @param key
	 * @return
	 */
	public static String readProperty(String key){
		return getProperty().getProperty(key);
	}
	/**
	 * 读取键值数据
	 * @param key
	 * @return
	 */
	public static Object readProperty(Object key){
		return getProperty().get(key);
	}
}
