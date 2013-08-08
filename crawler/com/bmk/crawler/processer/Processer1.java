package com.bmk.crawler.processer;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bmk.crawler.Filter;
import com.bmk.crawler.HttpConnnectionManager;
import com.bmk.crawler.LinkFilter;
import com.bmk.crawler.PropertiesUtils;

/**
 * @Intro 处理书籍分类的信息
 * @author Lee
 * @Date 2013-8-7
 */
public class Processer1 implements Runnable{
	
	public Processer1(){
		Step1Link.addUnvisitedUrl("http://category.dangdang.com/all/?category_path=01.00.00.00.00.00");
	}
	
	@Override
	public void run() {
		//当还有连接未处理，并且没有结束
		while (!Step1Link.unVisitedUrlsEmpty() && Step1Link.getVisitedUrlNum() <= 1000) {
			//从未访问的URL队列取出第一个连接
			if (Step1Link.unVisitedUrlsEmpty()) break;
            String visitUrl = Step1Link.unVisitedUrlDeQueue();
            
            Set<String> links = getLeftCategroy(visitUrl,Filter.filter1);
            
            for(String link : links){
            	//当向分类集合中插入一则数据，则会向向list集合中插入一则数据，从而想p2发起任务！！！
            	Step1Link.addUnvisitedUrl(link);
            }
            //休息几秒钟
            try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 获取左边分类栏的链接
	 * @return
	 */

	public static Set<String> getLeftCategroy(String visitUrl,LinkFilter filter) {
		Set<String> links = new HashSet<String>();
		Document doc = Jsoup.parse(HttpConnnectionManager.getHtml(visitUrl));//Jsoup.connect(visitUrl).get();
		//解析左边的
		Elements cas = doc.select(PropertiesUtils.getProperties().getProperty("getLeftCategroy"));
        if(cas.size() > 0){
        	for(Element e : cas){
        		links.add(e.attr("href"));
        	}
        }
		return links;
	}
	
	public static void start(int threadCount){
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		Processer1 processer1 = new Processer1();
//		new Thread(processer1).start();
		pool.execute(processer1);
		// 关闭启动线程
//		if(Step1Link.unVisitedUrlsEmpty()){
//			pool.shutdown();
//			System.out.println("关闭启动线程p2");
//		}
		
		// 等待子线程结束，再继续执行下面的代码
//		try {
//			pool.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		System.out.println("all thread complete");
	}
	
	public static void main(String[] args) throws InterruptedException {
		Processer1.start(1);
		Processer2.start(2);
		Processer3.start(5);
	}
	
}
