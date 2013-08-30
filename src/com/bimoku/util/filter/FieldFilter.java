package com.bimoku.util.filter;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

/**
 * 字段取优算法filter
 * 
 * 1:规定不同站点权重【如：当当：0.2，京东：0.1，亚马逊：0.2，豆瓣0.5】 2:算出每个站点该字段的结果 3:统计，得分高的字段被定位最后的结果
 * 
 * @author Lee
 * 
 */
public abstract class FieldFilter {
	/**
	 * 来自不同站点的同一字段，选取最优的结果
	 * 
	 * 1、相似度判断，把所有站点字段相似度达到一定数值的时候，认为是同一排序结果 a:根据相似度分组【相似度超过0.5的记录分为一组，称谓一个结果集】
	 * b：根据不同的结果集，结合权重，得出最终结果 2、根据不通站点的权重值，做计算。 3、权重值高的，对应的结果作为最终结果返回
	 * 
	 * @param map
	 *            : key:站点 value:字段字符串
	 * @return
	 */
	public String netFilter(Map<String, String> map) {
		String result = "";

		return result;
	}

	/**
	 * 同一个网页页面范围内，根据不同区域内出现同一字段内容，选取最优内容
	 * 
	 * 
	 * 
	 * @param map
	 * @return
	 */
	public String pageFilter(Map<String, String> map) {
		String result = "";

		return result;
	}

	/**
	 * 一则数据的处理，如：
	 * 《朱镕基上海讲话实录（平装）：再现朱镕基总理主政上海期间的重要讲话、谈话、信件等106篇，首次公开珍贵照片和手迹影印件！》《朱镕基上海讲话实录
	 * 》编辑组 编_简介_书评_在线阅读-当当图书 将其处理为：朱镕基上海讲话实录
	 * 
	 * @param field
	 * @return
	 */
	protected abstract String fieldPriority(String field);

	
	//public 
	
	/**
	 * 找出多个字符串中匹配最长的字符串。
	 * 
	 * @param list
	 * @return
	 */
	public static String longestMatch(String[] arr) {
		for(int i = 1; i < arr.length; i++){
			arr[i] = longestMatch(arr[i-1], arr[i]);
		}
		return arr[arr.length - 1];
	}
	
	/**
	 * 找出两个字符串中匹配最长的字符串。
	 * @update 2013-8-27
	 * ！！！！！！当两个字段进来，一个字段唯恐或者为空时，而另一字段不空时，取不空字段！！！！！！
	 * @param fields
	 * @return
	 */
	private static String longestMatch(String str1, String str2) {
		if((str1==null||str1.equals("")) && str2!=null && !str2.equals("")){
			return str2;
		}
		if((str2==null||str2.equals("")) && str1!=null && !str1.equals("")){
			return str1;
		}
		// 使用最短的字符串去分割，可以实现效率更优。
		String bigStr = str1.length() > str2.length() ? str1 : str2;
		String smaStr = str1.length() > str2.length() ? str2 : str1;
		String maxStr = "";

		// 对短的字符串先进行逐个分割。
		for (int i = 0; i < smaStr.length(); i++) {
			String temp = smaStr.substring(i, i + 1);
			
			if (bigStr.indexOf(temp) != -1) {// 如果第一个字符匹配上，则开始往后匹配。
				String temTest = smaStr.substring(i, smaStr.length());
				if (maxStr.length() < temp.length())
					maxStr = temp;
				int k = 1;
				while (k < temTest.length()) {
					k++;
					String strT = temTest.substring(0, k);
					// 如果出现匹配不上了，说明增加最后一个字符之后就匹配不上了，那么增加最后一个之前都还能匹配。
					if (bigStr.indexOf(strT) == -1) {
						String maxtemp = strT.substring(0, k - 1);
						if (maxStr.length() < maxtemp.length())
							maxStr = maxtemp;
						break;
					} else if (k == temTest.length()) {
						/*
						 * 如果匹配到最后没有字符串了还是能匹配上，说明从开始进入循环到最后一个字符都能匹配。
						 * 其实在这里还可以优化一下程序。就是程序匹配到最后一个字符串了，那么外层的for循环就没必要再做了。
						 */
						if (bigStr.indexOf(temTest) != -1) {
							if (maxStr.length() < temTest.length())
								maxStr = temTest;
						}
					}
				}
			}
		}
		return maxStr;
	}
	
	/**
	 * 得到纯净的字符串，去除：标点，空格等
	 * @param inp
	 * @return
	 */
	private static String pureStr(String inp){
		
		return "";
	}
	
	/**
	 * 去掉<>之间的部分
	 * @param content
	 * @return
	 */
	public static String trimTag(String content) {
		String regEx = "<[^>]+>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content.trim());
		String result = "";
		if (m.find()) {
			result = m.replaceAll("");
		}
		result = result.replace("<", "").replace(">", "");
		return result;
	}
	
	public static void main(String[] args) {
		String[] arr = {
				"朱镕基上海讲话实录（平装）：再现朱镕基总理主政上海期间的重要讲话、谈话、信件等106篇，首次公开珍贵照片和手迹影印件",
				"朱镕基上海讲话实录（平装）：再现朱镕基总理主政上海期间的重要讲话、谈话、",
				"《朱镕基上海讲话实录》编辑组 编,人民出版社,9787010124025"};
		String[] arr2 = {
				"dongcai当当网图书[csaacascacbaksbcakcabkcica好熟啊 啊]",
				null
		};
		System.out.println(longestMatch(arr2));
		//String[] arr = {"好声音，中国","中国好声音","中国，好声音"};
		//System.out.println(longestMatch(arr));
		System.out.println(trimTag(" <aaaaaaa> aa>".trim()));
	}
}
