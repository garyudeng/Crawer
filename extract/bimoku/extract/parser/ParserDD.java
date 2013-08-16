package bimoku.extract.parser;

import integrated.DDIntegraed;
import integrated.Integrated;
import integrated.JDIntegrated;

import java.io.File;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bean.BookDD;
import bean.BookDetail;
import bean.BookJD;
import bimoku.extract.common.PropertyUtil;

@Component("parserDD")
public class ParserDD extends Parser{
	
	@Autowired
	DDIntegraed ddIntegrated;
	
	@Override
	protected Integrated getIntegratedDao() {
		if(ddIntegrated == null)
			throw new RuntimeException("spring bean 实例化出错");
		return ddIntegrated;
	}

	@Override
	protected BookDetail fieldFilter(Map<String, String> map) {
		BookDD bookDD = new BookDD();
		
		String author = map.get(PropertyUtil.AUTHOR);
		String authorIntro = map.get(PropertyUtil.AUTHOR_INTRO);
		String bookName = map.get(PropertyUtil.BOOKNAME);
		String catelog = map.get(PropertyUtil.CLASSFY);
		String cover_pic = map.get(PropertyUtil.COVER_PIC);
		String directory = map.get(PropertyUtil.DIRECTORY);
		String isbn = map.get(PropertyUtil.ISBN);
		String press = map.get(PropertyUtil.PRESS);
		String price = map.get(PropertyUtil.PRICE);
		String translator = map.get(PropertyUtil.TRANSLATOR);
		String version = map.get(PropertyUtil.VERSION);
		
		Double pric = 0.0;
		//TODO 这个过程多处理
		
		
		
		
		
		bookDD.setAuthor(author);
		bookDD.setAuthorIntro(authorIntro);
		bookDD.setBookName(bookName);
		bookDD.setCatelog(catelog);
		bookDD.setCover_pic(cover_pic);
		bookDD.setDirectory(directory);
		bookDD.setIsbn(isbn);
		bookDD.setPress(press);
		bookDD.setPrice(pric);
		bookDD.setTranslator(translator);
		bookDD.setVersion(version);
		
		System.out.println(bookDD.toString());
		
		return bookDD;
	}

	@Override
	protected Map<String, String> getElementsInfo(String filepath) throws Exception{
		
		Map<String, String> map = new HashedMap();
		File input = new File(filepath);
		Document doc = Jsoup.parse(input, "gb2312");

		map.put(PropertyUtil.BOOKNAME, doc.select(PropertyUtil.BOOKNAME).first()==null?"":doc.select(PropertyUtil.BOOKNAME).first().text());
		map.put(PropertyUtil.AUTHOR, doc.select(PropertyUtil.AUTHOR).first()==null?"":doc.select(PropertyUtil.AUTHOR).first().text());
		map.put(PropertyUtil.TRANSLATOR, doc.select(PropertyUtil.TRANSLATOR).first()==null?"":doc.select(PropertyUtil.TRANSLATOR).first().text());
		map.put(PropertyUtil.PRESS, doc.select(PropertyUtil.PRESS).first()==null?"":doc.select(PropertyUtil.PRESS).first().text());
		map.put(PropertyUtil.VERSION, doc.select(PropertyUtil.VERSION).first()==null?"":doc.select(PropertyUtil.VERSION).first().text());
		map.put(PropertyUtil.ITEM_ID, doc.select(PropertyUtil.ITEM_ID).first()==null?"":doc.select(PropertyUtil.ITEM_ID).first().text());
		map.put(PropertyUtil.ISBN, doc.select(PropertyUtil.ISBN).first()==null?"":doc.select(PropertyUtil.ISBN).first().text());
		map.put(PropertyUtil.intro_clearfix, doc.select(PropertyUtil.intro_clearfix).first()==null?"":doc.select(PropertyUtil.intro_clearfix).first().text());
		map.put(PropertyUtil.PRICE, doc.select(PropertyUtil.PRICE).first()==null?"":doc.select(PropertyUtil.PRICE).first().text());
		
		Elements linksElements = doc.select(PropertyUtil.PUBLISHED_PRICE);
		String published_price = "";
		for (Element ele : linksElements) {
			published_price += ele.text() + ">";
		}
		map.put(PropertyUtil.PUBLISHED_PRICE, published_price);
		
		map.put(PropertyUtil.COVER_PIC, doc.select(PropertyUtil.COVER_PIC).first()==null?"":doc.select(PropertyUtil.COVER_PIC).first().text());
		map.put(PropertyUtil.CLASSFY, doc.select(PropertyUtil.CLASSFY).first()==null?"":doc.select(PropertyUtil.CLASSFY).first().text());
//		map.put(PropertyUtil.EDITOR_CHOICE, doc.select(PropertyUtil.EDITOR_CHOICE).first()==null?"":doc.select(PropertyUtil.EDITOR_CHOICE).first().text());
//		map.put(PropertyUtil.CONTENT_CHOICE, doc.select(PropertyUtil.CONTENT_CHOICE).first()==null?"":doc.select(PropertyUtil.CONTENT_CHOICE).first().text());
		map.put(PropertyUtil.AUTHOR_INTRO, doc.select(PropertyUtil.AUTHOR_INTRO).first()==null?"":doc.select(PropertyUtil.AUTHOR_INTRO).first().text());
		map.put(PropertyUtil.DIRECTORY, doc.select(PropertyUtil.DIRECTORY).first()==null?"":doc.select(PropertyUtil.DIRECTORY).first().text());

//		map.put(PropertyUtil.MEDIA_REVIEWS, doc.select(PropertyUtil.MEDIA_REVIEWS).first()==null?"":doc.select(PropertyUtil.MEDIA_REVIEWS).first().text());
		map.put(PropertyUtil.EXTRACT, doc.select(PropertyUtil.EXTRACT).first()==null?"":doc.select(PropertyUtil.EXTRACT).first().text());
		map.put(PropertyUtil.ATTACH_IMAGE_SHOW, doc.select(PropertyUtil.ATTACH_IMAGE_SHOW).first()==null?"":doc.select(PropertyUtil.ATTACH_IMAGE_SHOW).first().text());
		map.put(PropertyUtil.COMMENTURL, doc.select(PropertyUtil.COMMENTURL).first()==null?"":doc.select(PropertyUtil.COMMENTURL).first().text());

		
		return map;
	}

}