package bimoku.extract.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternmatchDD {

	public static void main(String[] args) {
		String content = "作 者：（德）路德维希　著，梁锡江　等译 出 版 社：浙江文艺出版社 出版时间：2008-1-1 版 次：1 页 数：594 字 数：460000 印刷时间：2008-1-1 开 本：32开 纸 张：胶版纸 印 次：1 I S B N：9787533925505 包 装：平装装";
		patternmatch(content);
	}

	public static String[] patternmatch(String content) {
		String[] paramcontent = new String[5];
		Pattern p1 = Pattern
				.compile("作 者：[^/s]*[\u4e00-\u9fa5]*[^/s]*[\u4e00-\u9fa5]+[/s]*[^/s]*[著|编著|主编|编]");
		Matcher m1 = p1.matcher(content);
		boolean result1 = m1.find();
		while (result1) {
			paramcontent[0] = m1.group(0).replaceAll("作 者：", "")
					.replaceAll("著|主|编", "");
			System.out.println(paramcontent[0]);
			result1 = m1.find();
		}

		Pattern p2 = Pattern
				.compile("著[\\pP|\\pS]*[\u4e00-\u9fa5]+[^/s]*[译|校注]");
		Matcher m2 = p2.matcher(content);
		boolean result2 = m2.find();
		while (result2) {
			paramcontent[1] = m2.group(0)
					.replaceAll("作 者|著|主|编|校注|\\pP|\\pS", " ")
					.replaceAll("译", "");
			result2 = m2.find();
			System.out.println(paramcontent[1]);
		}
		Pattern p3 = Pattern.compile(
				"出 版 社：[\\s|]*[\u4e00-\u9fa5|^/s|\\pP|\\pS]*[\\s]+出版时间",
				Pattern.CASE_INSENSITIVE);
		Matcher m3 = p3.matcher(content);
		boolean result3 = m3.find();
		while (result3) {
			paramcontent[2] = m3.group(0).replaceAll("出 版 社：", "")
					.replaceAll("出版时间", "");
			result3 = m3.find();
			System.out.println(paramcontent[2]);
		}
		Pattern p4 = Pattern
				.compile("版 次：[/s]*.+页 数", Pattern.CASE_INSENSITIVE);
		Matcher m4 = p4.matcher(content);
		boolean result4 = m4.find();
		while (result4) {
			paramcontent[3] = m4.group(0).replaceAll("版 次：", "")
					.replaceAll("页 数", "");
			result4 = m4.find();
			System.out.println(paramcontent[3]);
		}
		Pattern p5 = Pattern.compile(" I S B N：[0123456789]+",
				Pattern.CASE_INSENSITIVE);
		Matcher m5 = p5.matcher(content);
		boolean result5 = m5.find();
		while (result5) {
			paramcontent[4] = m5.group(0).replaceAll("I S B N：", "");
			System.out.println(paramcontent[4]);
			result5 = m5.find();
		}

		return paramcontent;
	}
}
