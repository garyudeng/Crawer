package bimoku.extract.main;

import java.io.File;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import bimoku.extract.common.PropertyUtil;
import bimoku.extract.parser.Parser;
import bimoku.extract.parser.ParserDD;

public class Main {
	
	private static ApplicationContext ctx = null;
	private static Parser parser = null;
	
	public static ApplicationContext getContext(){
		if(ctx == null){
			ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		}
		return ctx;
	}
	
	
	public static void extract(String configPath,Parser parser){
		File file = new File(PropertyUtil.getProperty(configPath).getProperty("directory"));
		File[] files = file.listFiles();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				String directoryitem = PropertyUtil.getProperty().getProperty("directory") + files[i].getName();
				Extract extract = new Extract(directoryitem,parser);
				extract.start();
			}
		}
	}
	
	public static void main(String[] args) {
		extract("ddConfig.properties", (ParserDD)getContext().getBean("parserDD"));
	}
	
}
