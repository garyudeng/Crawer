package bimoku.extract.parser;

import integrated.DDIntegraed;
import integrated.Integrated;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import bean.BookDD;
import bimoku.extract.common.PropertyUtil;
import bimoku.extract.main.Main;


@Component("parse")
public class ParseDD00 {

	private final static int num = 19;
	@Autowired
	DDIntegraed ddIntegraed;

	public void parse(String filepath) throws IOException, SQLException {
		BookDD bookcontent = new BookDD();
		File input = new File(filepath);
		Document doc = Jsoup.parse(input, "gb2312");
		String[] getStr = getStr();
		String[] param = new String[num];

		for (int i = 0; i < 11; i++) {
			// System.out.println(param[i]+"断点");
			if (i == 4) {
				Elements linksElements = doc.select(getStr[4]);
				for (Element ele : linksElements) {
					if (param[4] == null) {
						param[4] = ele.text() + ">";
						continue;
					}
					param[4] = param[4] + ele.text() + ">";

				}
				continue;
			}
			Element linksElements = doc.select(getStr[i]).first();

			if (linksElements != null) {
				param[i] = linksElements.text();
				if (param[i] == null)
					param[i] = "null";

			} else {
				param[i] = "null";
			}
			// System.out.println(param[i]+"断点");
		}
		// System.out.println(param[2]);
		// System.out.println(param[3]);

		param[2] = param[2].toString().replaceAll("\\?|¥", "");
		param[3] = param[3].toString().replaceAll("\\?|¥", "");

		param[2] = param[2].replaceAll("\\?|¥", "");
		param[3] = param[3].replaceAll("\\?|¥", "");

		// System.out.println(param[2]);
		// System.out.println(param[3]);
		// System.out.println(param[4]);
		for (int i = 5; i < 11; i++) {
			param[i] = trimTag(param[i]);
		}
		String content = new String();
		try {
			Element linksElementcontent = doc.select(getStr[11]).first();
			content = linksElementcontent.text();
			System.out.println(content);
			String[] paramcontent = Patternmatch.patternmatch(content);
			for (int i = 11; i < num - 3; i++) {
				param[i] = paramcontent[i - 11];
				// System.out.println(param[i]);
			}
		} catch (java.lang.NullPointerException e) {
			// System.out.println(param[0]);
			for (int i = 11; i < num - 3; i++) {
				param[i] = "null";
			}
		}

		try {
			Element linksElementimg = doc.select(getStr[12]).first();
			param[num - 3] = linksElementimg.attr("src");
		} catch (NullPointerException e) {
			param[num - 3] = "null";
		}
		Elements linksElements = doc.select(getStr[13]);

		for (Element ele : linksElements) {
			if (param[num - 2] == null) {
				param[num - 2] = ele.attr("src");

			} else {
				param[num - 2] = param[num - 2] + "  " + ele.attr("src");
			}
		}
		param[num - 1] = getStr[14].trim() + param[1];
		
		setParam(bookcontent, param);
		ddIntegraed.integrated(bookcontent);
	}

	public static String[] getStr() {
		Properties p = new Properties();
		p = PropertyUtil.getProperty();
		String[] getStr = { p.getProperty("BOOKNAME"),
				p.getProperty("ITEM_ID"), p.getProperty("PRICE"),
				p.getProperty("PUBLISHED_PRICE"), p.getProperty("CLASSFY"),
				p.getProperty("EDITOR_ CHOICE"),
				p.getProperty("CONTENT_ CHOICE"),
				p.getProperty("AUTHOR_INTRO"), p.getProperty("DIRECTORY"),
				p.getProperty("MEDIA_ REVIEWS"), p.getProperty("EXTRACT"),
				p.getProperty("intro_clearfix"),
				p.getProperty("ATTACH_IMAGE_SHOW"), p.getProperty("COVER_PIC"),
				p.getProperty("COMMENTURL") };
		return getStr;
	}

	public static String trimTag(String content) {
		String regEx = "<[^>]+>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(content);
		String result = content;
		if (m.find()) {
			result = m.replaceAll("");
		}
		result = result.replace("<", "").replace(">", "");
		return result;
	}

	
	public static void setParam(BookDD bookcontent, String[] param) {
		// bookcontent =new BookDD();
		bookcontent.setBookName(param[0].substring(0, param[0].length()>200?200:param[0].length()-1));
		bookcontent.setAuthor(param[11]);
		bookcontent.setPrice(Double.valueOf(param[2]));
		bookcontent.setDirectory(param[4]);
		bookcontent.setIsbn(param[15]);
		bookcontent.setCover_pic(param[17]);
		bookcontent.setOutLine(param[5]);
		bookcontent.setPress(param[13]);
		bookcontent.setTranslator(param[12]);
		bookcontent.setVersion(param[14]);
	}
}
