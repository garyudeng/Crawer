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
public class Step1Link{
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
	// 保证每个 url 只被访问一次
    public static void addUnvisitedUrl(String url) {
        if (url != null && !url.trim().equals("") && !getUnVisitedUrl().contians(url) && !getVistedUrl().contains(url)) {
            String cutStr = url.substring(0, Filter.category_min_size);
        	getUnVisitedUrl().enQueue(cutStr);
            //想第2级目录添加未被访问的url
            Step2Link.addUnvisitedUrl(cutStr);
            //System.out.println("1---->"+url);
        }
    }
}
