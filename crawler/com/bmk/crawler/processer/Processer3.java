package com.bmk.crawler.processer;

import integrated.DDIntegraed;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.bmk.crawler.HttpConnnectionManager;
import com.bmk.crawler.PropertiesUtils;

import bean.BookDD;


/**
 * @Intro 处理单本书籍的数据
 * @author Lee
 * @Date 2013-8-7
 */
public class Processer3 implements Runnable{
	
	private static DDIntegraed ddIntegraed;
	public Processer3(){
		this.ddIntegraed = getIntegraed();
	}
	
	public static boolean isRunning = false;
	
	@Override
	public void run() {
		//待抓取的链接不空
		while (!Step3Link.unVisitedUrlsEmpty() || !isRunning) {
			//System.out.println(isRunning);
			if (Step3Link.unVisitedUrlsEmpty()){
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				continue;
			}
			//从未访问的URL队列取出第一个连接
            String visitUrl = Step3Link.unVisitedUrlDeQueue();   
            process(visitUrl);
		}
		
	}

	/**
	 * 处理详细页面的信息
	 * @param visitUrl
	 */
	public static void process(String visitUrl){
		//下载页面
		Document doc = Jsoup.parse(HttpConnnectionManager.getHtml(visitUrl));//Jsoup.connect(visitUrl).get();
		BookDD book = new BookDD(); 
		//解析数据
		book.setAuthor(doc.select(PropertiesUtils.getProperties().getProperty("author")).text());
		book.setIsbn(doc.select(PropertiesUtils.getProperties().getProperty("isbn")).text());
		book.setPrice(Double.parseDouble(doc.select(PropertiesUtils.getProperties().getProperty("price")).text().substring(1)));
		book.setOutLine(doc.select(PropertiesUtils.getProperties().getProperty("outline")).text());
		book.setBookName(doc.select(PropertiesUtils.getProperties().getProperty("bookName")).text());
		book.setCover_pic(doc.select(PropertiesUtils.getProperties().getProperty("pic")).attr("wsrc").trim());
		
		String outline = doc.select(PropertiesUtils.getProperties().getProperty("outline")).text().trim();
		if(outline.startsWith("<p>")){
			outline = Jsoup.parse(outline).select("p").text();
		}
		book.setOutLine(outline.length() < 2000 ? outline : outline.substring(0, 2000));
		//封装到实体
		System.out.println("bookName---->"+book.getBookName()+"<-->"+book.getAuthor()+"<-->"+book.getOutLine()+"<-->"+book.getIsbn());
		ddIntegraed.integrated(book);
	}
	
	public static void start(int threadCount){
		// 创建一个可重用固定线程数的线程池
		ExecutorService pool = Executors.newFixedThreadPool(threadCount);
		Processer3 processer3 = new Processer3();
		pool.execute(processer3);
	}
	
	public static void main(String[] args) {
		process("http://product.dangdang.com/product.aspx?product_id=22544222#ddclick?act=click&pos=22544222_27_1_p&cat=01.19.00.00.00.00&key=&qinfo=&pinfo=8824_1_48&minfo=&ninfo=&custid=&permid=20130808112126035747584195810198296&ref=&rcount=&type=&t=1375932112000");
	}
	
	public static DDIntegraed getIntegraed(){
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		return (DDIntegraed) ctx.getBean("ddIntegraed");
	}
}
