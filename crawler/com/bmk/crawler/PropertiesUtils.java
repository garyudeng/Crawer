package com.bmk.crawler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Intro descrption here
 * @author Lee
 * @Date 2013-8-7
 */
public class PropertiesUtils {
	public static String FILE_PATH_NAME = "rule.properties";
	public static PropertiesUtils proUtils = new PropertiesUtils();
	
	public Properties getProperties(String fileName){
		InputStream in = getClass().getResourceAsStream(fileName);
		Properties props = new Properties();
		try {
			props.load(in);
			in.close();
			return props;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Properties getProperties(){
		if(proUtils==null){
			proUtils = new PropertiesUtils();	
		}
		return proUtils.getProperties(FILE_PATH_NAME);
	}
	
	public static void main(String[] args) {
		System.out.println(getProperties().get("bookName"));
	}
}
