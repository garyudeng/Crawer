package com.bimoku.util.xml;

import java.util.ArrayList;
import java.util.List;
/**
 * 
 * bcab
 * 
 * bcab-->dab-->cb-->a
 * 
 * bcab-->bbb
 * 
 * bcab-->bcd-->ad-->c
 * 
 * bcab-->bcd-->ba-->c
 * @author Lee
 *
 */
public class MinLength {
	
	private final static String base = "abc";
	
	public static void main(String[] args) {
		//acbb//AABCCC
		System.out.println(minLength("aabcbb"));
	}
	
	/**
	 * 逻辑处理主函数
	 * @param str
	 * @return
	 */
	private static int minLength(String str){
		List<Integer> list = convert(str);
		List<Integer> stack = new ArrayList<Integer>();
		while (!list.isEmpty()) {
			//从元序列中拿出一个元素放到待排序的序列
			stack = fix(stack, list.remove(0));
		}
		System.out.println(stack);
		return stack.size();
	}
	
	
	/**
	 * 用于维护已经最优的队列
	 * @param src
	 * @param news
	 * @return
	 */
	private static List<Integer> fix(List<Integer> src,Integer news){
		//加到队列尾部
		src.add(news);
		//保证长度大于等于2
		if(src.size() < 2)
			return src;
		if(!isEnd(src)) {
			Integer newsData = src.remove(src.size()-1);
			Integer lastData = src.remove(src.size()-1);
			
			Integer temp = replace(newsData, lastData);
			if(temp==0){
				src.add(lastData);
				src.add(newsData);
			}else{
				fix(src,temp);
			}
		}
		//倒序处理
		return src;
	}
	
	
	/**
	 * 终止符判断
	 * @param src
	 * @return
	 */
	private static boolean isEnd(List<Integer> src){
		Object[] arr =  src.toArray();
		for(int a=0;a<arr.length-1;a++){
			if(arr[a] != arr[a+1]){
				return false;
			}
		}
		return true;	
	}
	
	
	/**
	 * 转换
	 * @param a
	 * @param b
	 * @return
	 */
	private static int replace(int a,int b){
		if((a+b)%3 == 0)//1+2
			return 3;
		if((a+b)%2 == 0)//1+3
			return 2;
		if((a+b)%5 == 0)//2+3
			return 1;
		return 0;
	}
	
	/**
	 * 字符串转换成数字的数组
	 * @param str
	 * @return
	 */
	private static List<Integer> convert(String str){
		List<Integer> list = new ArrayList<Integer>();
		char[] arr = str.toCharArray();
		for(int i=0;i<arr.length;i++){
			list.add(mapping(arr[i]));
		}
		return list;
	}
	/**
	 * 映射关系
	 * @param a
	 * @return
	 */
	private static int mapping(char a){
		int result = 0;
		switch (a) {
		case 'a':
			result = 1;
			break;
		case 'b':
			result = 2;
			break;
		case 'c':
			result = 3;
			break;
		default:
			result = 1;
			break;
		}
		return result;
	}
}
