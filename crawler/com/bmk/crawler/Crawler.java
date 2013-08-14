package com.bmk.crawler;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.bmk.crawler.processer.Processer1;
import com.bmk.crawler.processer.Processer2;
import com.bmk.crawler.processer.Processer3;

/**
 * @Intro 抓取主程序
 * @author Lee
 * @Date 2013-8-7
 */
public class Crawler {
	
	/**
	 * 处理程序：[大类：小类：分页]
	 * 分三步走：
	 * 第一级：抓取所有进入List页面的链接【进入当当图书：http://category.dangdang.com/all/?category_path=01.00.00.00.00.00】
	 * 		|
	 *		|解析分页连接，（左侧的图书分类）
	 *		|
	 * 第二级：抓取所有List页面的连接
	 *		|
	 *		|解析每一个书籍信息的list页面
	 *		| 
	 * 第三集抓取详细页面的连接
	 * 		|
	 *		|解析每一个书籍详细信息
	 *		|
	 * 	 解析书籍信息
	 * @throws InterruptedException 
	 */
	public static void process() throws InterruptedException{
		Processer1.start(1);
		Processer2.start(1);
		Processer3.start(10);
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		process();
	}
}

