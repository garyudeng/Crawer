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
 * @Intro 处理list书籍数据
 * @author Lee
 * @Date 2013-8-7
 */
public class Processer2 implements Runnable{
	
	public static boolean isRunning = false;
	
	@Override
	public void run() {
		//当还有连接未处理，并且没有结束
		while (!Step2Link.unVisitedUrlsEmpty() || !isRunning) {
			//System.out.println(isRunning);
			if (Step2Link.unVisitedUrlsEmpty()){
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			//从未访问的URL队列取出第一个连接
            String visitUrl = Step2Link.unVisitedUrlDeQueue();           
            Set<String> links = this.extracLinks(visitUrl);
            
            for(String link : links){
            	Step2Link.addUnvisitedUrl(link);
            }
		}
		
	}

	/**
	 * 抽取满足第二级条件的连接，返回给调用函数，即抽取下一页数据
	 * 
	 * 抽取满足第三级级条件的加入到第二级队列，即抽取书籍详细数据
	 * 
	 * @param temp
	 */
	private Set<String> extracLinks(String visitUrl) {
		//获取当前文档对象
		Document doc = Jsoup.parse(HttpConnnectionManager.getHtml(visitUrl));//Jsoup.connect(visitUrl).get();
		//获取分页数据
		Set<String> links = new HashSet<String>();
		//获取当页详细书本数据的连接
		setBookDetail(doc,Filter.filter3);
		//获取list页面
		links = getBehindPaging(doc,Filter.filter2);
		return links;
	}

	/**
	 * 获取当页详细书本数据的连接
	 * 将获取到的连接插入到：step3link中
	 */
	public static void setBookDetail(Document doc,LinkFilter filter) {
		//解析左边的
		Elements cas = doc.select(PropertiesUtils.getProperties().getProperty("setBookDetail"));
		if(cas.size() > 0){
        	for(Element e : cas){
        		String url = e.attr("href");
        		if(filter.accept(url)){
	        		Step3Link.addUnvisitedUrl(url);
        		}
        	}
        }
	}

	/**
	 * 获取分下下面的链接
	 * @return
	 */
	public static Set<String> getBehindPaging(Document doc,LinkFilter filter) {
		
		Set<String> links = new HashSet<String>();
		//解析左边的
		Elements cas = doc.select(PropertiesUtils.getProperties().getProperty("getBehindPaging"));
        if(cas.size() > 0){
        	for(Element e : cas){
        		String url = e.attr("href");
        		if(filter.accept(url)){
	        		links.add(PropertiesUtils.getProperties().getProperty("base") + url);
        		}
        	}
        }
		return links;
	}
	
	public static void start(int threadCount){
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		Processer2 processer2 = new Processer2();
		pool.execute(processer2);
	}
	
	public static void main(String[] args) {
		//获取当前文档对象
		Document doc = Jsoup.parse(HttpConnnectionManager.getHtml("http://category.dangdang.com/all/?category_path=01.38.05.00.00.00"));//Jsoup.connect(visitUrl).get();
		//获取当页详细书本数据的连接
		getBehindPaging(doc,Filter.filter2);
	}
}
