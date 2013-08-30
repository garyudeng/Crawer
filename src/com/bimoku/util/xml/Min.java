package com.bimoku.util.xml;

import java.util.ArrayList;
import java.util.List;

public class Min {
	public static void main(String[] args) {
		Long t1 = System.currentTimeMillis();
		String s = "aabcbbaaaaaaa";
		System.out.println("string:"+s);
		System.out.println(minLength(s));
		System.out.println("need time:"+(System.currentTimeMillis() - t1));
	}
	
	/**
	 * 处理方法
	 * @param s
	 * @return
	 */
	public static int minLength(String s){
		//边界值处理
		int min = s.length();
		if(min < 2)
			return 1;
		if(isEnd(s))
			return s.length();
		List<String> group = group(s);
		while (!group.isEmpty()) {
			String one = "";
			String other = "";
			
			one = group.remove(0);
			if(!group.isEmpty())
				other = group.remove(0);
			
			//求出这条记录的最短路径就是三个中间最小的
			min = Math.min(min,Math.min(minLength(one), minLength(other)));
		}
		return min;
	}
	
	/**
	 * 一个字符串有多少种分组方式
	 * @param s
	 * @return
	 */
	public static List<String> group(String s){
		List<String> arr = new ArrayList<String>();
		if(isEnd(s)){
			arr.add(s);
		}else{
			//开始做处理,非结束，则长度大于等于2
			for(int a=2;a<=s.length();a++){
				String head = s.substring(0, a-2);
				String nearTwo = s.substring(a-2, a);
				String tail = s.substring(a, s.length());
				
				String temp = replace(nearTwo);
				if(temp != null && temp.length() == 1){
					String newStr = head + temp + tail;
					arr.add(newStr);
				}
			}
		}
		return arr;
	}
	
	
	
	/**
	 * 结束标志，字符串序列只出现一种类型的字母
	 * @param s
	 * @return
	 */
	private static boolean isEnd(String s){
		if(s.length() > 2 || s.length() == 2){
			for(int a=1;a<s.length();a++){
				if(s.charAt(a-1) != s.charAt(a))
					return false;
			}
		}
		//长度小于2,直接结束
		return true;
	}
	
	/**
	 * 替换规则
	 * @param s
	 * @return
	 */
	private static String replace(String s){
		if(s == null || "".equals(s) || s.length() > 2 || isEnd(s))
			return null;
		if(!s.contains("a")){
			return "a";
		}
		else if(!s.contains("b")){
			return "b";
		}
		else if(!s.contains("c")){
			return "c";
		}
		return null;
	}
}
