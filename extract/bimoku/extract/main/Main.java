package bimoku.extract.main;

import java.io.File;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.aspectj.org.eclipse.jdt.core.dom.ThisExpression;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import bimoku.extract.common.PropertyUtil;
import bimoku.extract.parser.Parser;
import bimoku.extract.parser.ParserAmazon;


public class Main {
	
	private static ThreadPoolExecutor pool;
	private static Long begain = System.currentTimeMillis();
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
		//ExecutorService pool = Executors.newFixedThreadPool(10);
		pool = new ThreadPoolExecutor(5, 10, 0, TimeUnit.SECONDS, new ArrayBlockingQueue(10));
		
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				String directoryitem = PropertyUtil.getProperty(configPath).getProperty("directory") + File.separator + files[i].getName();
				Extract extract = new Extract(directoryitem,parser,configPath);
				//把任务放到线程池的处理队列里面，等待处理
				pool.execute(extract);
			}
		}
		//处理完成后，关闭线程池
		pool.shutdown();
		isEndTask();
	}
	
	private static boolean isEndTask(){
		while(true){
			if(pool.getActiveCount()==0){
				System.out.println(new Date());
				System.out.printf("\nTIME %d ms", (System.currentTimeMillis() - begain) );
			}
		}
		
	}
	
	public static void main(String[] args) {
		extract("amazonConfig.properties", (ParserAmazon)getContext().getBean("parserAmazon"));
	}
	
}
