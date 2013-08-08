package com.bmk.crawler;

/**
 * @Intro 是否满足链接路由规则
 * @author Lee
 * @Date 2013-8-7
 */
public class Filter {
	
	private static String regex1 = "http://category.dangdang.com/all/?category_path=";
	private static String regex2 = "/all/?category_path=";
	private static String regex3 = "http://product.dangdang.com/product.aspx?product_id=";
	
	public static int category_min_size = "http://category.dangdang.com/all/?category_path=01.38.00.00.00.00".length();
	public static int product_min_size = "http://product.dangdang.com/product.aspx?product_id=23230517".length();
	
	public static LinkFilter filter1 = new LinkFilter() {
		@Override
		public boolean accept(String url) {
			//url.matches(regex1)
			if(url.contains(regex1) && url.startsWith(regex1)){
				return true;
			}
			return false;
		}
	};
	public static LinkFilter filter2 = new LinkFilter() {
		@Override
		public boolean accept(String url) {
			if(url.contains(regex2) && url.startsWith(regex2)){
				return true;
			}
			return false;
		}
	};
	public static LinkFilter filter3 = new LinkFilter() {
		@Override
		public boolean accept(String url) {
			if(url.contains(regex3) && url.startsWith(regex3)){
				return true;
			}
			return false;
		}
	};

}
