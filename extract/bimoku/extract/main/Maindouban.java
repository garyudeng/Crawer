package bimoku.extract.main;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import bimoku.extract.common.PropertyUtil;
import bimoku.extract.parser.Parser;
import bimoku.extract.parser.ParserAmazon;
import bimoku.extract.parser.ParserDD;
import bimoku.extract.parser.ParserDouban;

public class Maindouban {
	
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
		
		//创建一个固定大小[10]的线程池
		ExecutorService pool = Executors.newFixedThreadPool(2);
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				String directoryitem = PropertyUtil.getProperty(configPath).getProperty("directory") +  files[i].getName();
				Extractdouban extract = new Extractdouban(directoryitem,parser,configPath);
				//把任务放到线程池的处理队列里面，等待处理
				pool.execute(extract);
			}
		}
		//处理完成后，关闭线程池
		pool.shutdown();
	}
	
	public static void main(String[] args) {
		extract("doubanConfig.properties", (ParserDouban)getContext().getBean("parserDouban"));
	}
	
}
