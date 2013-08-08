package com.bmk.crawler.processer;

import java.util.HashSet;
import java.util.Set;

import com.bmk.crawler.Filter;
import com.bmk.crawler.Queue;

/**
 * @Intro 一级分类链接
 * @author Lee
 * @Date 2013-8-7
 */
public class Step3Link{
	//已访问的 url 集合
    private static Set<String> visitedUrl = new HashSet<String>();
    //待访问的 url 集合
    private static Queue<String> unVisitedUrl = new Queue<String>();

    public static Queue<String> getUnVisitedUrl() {
        return unVisitedUrl;
    }
    public static Set<String> getVistedUrl(){
    	return visitedUrl;
    }

    public static void addVisitedUrl(String url) {
        visitedUrl.add(url);
    }

    public static void removeVisitedUrl(String url) {
        visitedUrl.remove(url);
    }

    public static String unVisitedUrlDeQueue() {
        return unVisitedUrl.deQueue();
    }


    public static int getVisitedUrlNum() {
        return visitedUrl.size();
    }

    public static boolean unVisitedUrlsEmpty() {
        return unVisitedUrl.empty();
    }
	public static void addUnvisitedUrl(String url) {
		if (url != null && !url.trim().equals("") && !getUnVisitedUrl().contians(url) && !getVistedUrl().contains(url)) {
			String cutStr = url.substring(0, Filter.category_min_size);
			getUnVisitedUrl().enQueue(cutStr);
			//开启p3---打开开关
			if(!Processer3.isRunning){
    			Processer3.isRunning = true;
    		}
			//System.out.println("3---->"+cutStr);
        }
	}
}