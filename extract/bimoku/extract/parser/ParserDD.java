package bimoku.extract.parser;

import java.io.File;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bimoku.extract.common.PropertyUtil;

import com.bimoku.common.bean.BookDD;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.integrate.DDIntegrated;
import com.bimoku.integrate.Integrated;

@Component("parserDD")
public class ParserDD extends Parser{
	
	@Autowired
	DDIntegrated ddIntegrated;
	
	@Override
	protected Integrated getIntegratedDao() {
		if(ddIntegrated == null)
			throw new RuntimeException("spring bean 实例化出错");
		return ddIntegrated;
	}

	@Override
	protected BookDetail fieldFilter(Map<String, String> map) {
		BookDD bookDD = new BookDD();
		
		//String author = map.get(PropertyUtil.AUTHOR);
		String authorIntro = map.get(PropertyUtil.AUTHOR_INTRO);
		String bookName = map.get(PropertyUtil.BOOKNAME);
		String catelog = map.get(PropertyUtil.CLASSFY);
		String cover_pic = map.get(PropertyUtil.COVER_PIC);
		String directory = map.get(PropertyUtil.DIRECTORY);
		//String isbn = map.get(PropertyUtil.ISBN);
		//String press = map.get(PropertyUtil.PRESS);
		String price = map.get(PropertyUtil.PRICE);
		//String publicprice = map.get(PropertyUtil.PUBLISHED_PRICE);
		//String translator = map.get(PropertyUtil.TRANSLATOR);
		//String version = map.get(PropertyUtil.VERSION);
		String intro_clearfix = map.get(PropertyUtil.intro_clearfix);
		
		//TODO 这个过程多处理
		String author = new String();
		String isbn = null;
		String press = null;
		String translator = null;
		String version = null;
		Double pric = 0.0;
		String[] infoparam = PatternmatchDD.patternmatch(intro_clearfix);
		
		//基本信息模块用模式匹配
		author = infoparam[0];
		translator = infoparam[1];
		press = infoparam[2];
		version = infoparam[3];
		try{
	    isbn = infoparam[4].trim();
		}catch(NullPointerException e){
			isbn = "";
		}
	    pric = Double.valueOf(price.replaceAll("\\?|¥", ""));
	    //控制字段长度
		authorIntro = trimTag(authorIntro);
		try {
			authorIntro = authorIntro.substring(0, authorIntro.length()>2000?2000:authorIntro.length()-1);
		}catch(StringIndexOutOfBoundsException e){
			authorIntro = "";
		}
		
		directory = trimTag(directory);
		try{
		directory = directory.substring(0, directory.length()>2000?2000:directory.length()-1);
		}catch(StringIndexOutOfBoundsException e){
			directory = "";
		}
		try{
			cover_pic = cover_pic.substring(0, cover_pic.length()>250?250:cover_pic.length()-1);
			}catch(StringIndexOutOfBoundsException e){
				cover_pic = "";
			}
		try{
			bookName = bookName.substring(0, bookName.length()>252?252:bookName.length()-1);
			}catch(StringIndexOutOfBoundsException e){
				bookName = "";
			}
		try{
			catelog = catelog.substring(0, catelog.length()>252?252:catelog.length()-1);
			}catch(StringIndexOutOfBoundsException e){
				catelog = "";
			}
		
		
		
		
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
		
		//System.out.println(bookDD.toString());
		
		return bookDD;
	}

	@Override
	protected Map<String, String> getElementsInfo(String filepath) throws Exception{
		
		Map<String, String> map = new HashedMap();
		File input = new File(filepath);
		Document doc = Jsoup.parse(input, "gb2312");
		
		//System.out.println(doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first().text());
         
		map.put(PropertyUtil.BOOKNAME, doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first().text());
		//map.put(PropertyUtil.AUTHOR, doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR)).first().text());
		//map.put(PropertyUtil.TRANSLATOR, doc.select(PropertyUtil.readProperty(PropertyUtil.TRANSLATOR)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.TRANSLATOR)).first().text());
		//map.put(PropertyUtil.PRESS, doc.select(PropertyUtil.readProperty(PropertyUtil.PRESS)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PRESS)).first().text());
		//map.put(PropertyUtil.VERSION, doc.select(PropertyUtil.readProperty(PropertyUtil.VERSION)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.VERSION)).first().text());
		map.put(PropertyUtil.ITEM_ID, doc.select(PropertyUtil.readProperty(PropertyUtil.ITEM_ID)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ITEM_ID)).first().text());
		//map.put(PropertyUtil.ISBN, doc.select(PropertyUtil.readProperty(PropertyUtil.ISBN)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ISBN)).first().text());
		map.put(PropertyUtil.intro_clearfix, doc.select(PropertyUtil.readProperty(PropertyUtil.intro_clearfix)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.intro_clearfix)).first().text());
		map.put(PropertyUtil.PRICE, doc.select(PropertyUtil.readProperty(PropertyUtil.PRICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PRICE)).first().text());
		map.put(PropertyUtil.PUBLISHED_PRICE, doc.select(PropertyUtil.readProperty(PropertyUtil.PUBLISHED_PRICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PUBLISHED_PRICE)).first().text());

		Elements linksElements = doc.select(PropertyUtil.readProperty(PropertyUtil.CLASSFY));
		String CLASSFY = "";
		for (Element ele : linksElements) {
			CLASSFY += ele.text() + ">";
		}
		map.put(PropertyUtil.CLASSFY, CLASSFY);
		
		map.put(PropertyUtil.COVER_PIC, doc.select(PropertyUtil.readProperty(PropertyUtil.COVER_PIC)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.COVER_PIC)).first().attr("src"));
//		map.put(PropertyUtil.EDITOR_CHOICE, doc.select(PropertyUtil.readProperty(PropertyUtil.EDITOR_CHOICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.EDITOR_CHOICE)).first().text());
//		map.put(PropertyUtil.CONTENT_CHOICE, doc.select(PropertyUtil.readProperty(PropertyUtil.CONTENT_CHOICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.CONTENT_CHOICE)).first().text());
		map.put(PropertyUtil.AUTHOR_INTRO, doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_INTRO)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_INTRO)).first().text());
		map.put(PropertyUtil.DIRECTORY, doc.select(PropertyUtil.readProperty(PropertyUtil.DIRECTORY)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.DIRECTORY)).first().text());

//		map.put(PropertyUtil.MEDIA_REVIEWS, doc.select(PropertyUtil.readProperty(PropertyUtil.MEDIA_REVIEWS)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.MEDIA_REVIEWS)).first().text());
		//map.put(PropertyUtil.EXTRACT, doc.select(PropertyUtil.readProperty(PropertyUtil.EXTRACT)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.EXTRACT)).first().text());
		//map.put(PropertyUtil.ATTACH_IMAGE_SHOW, doc.select(PropertyUtil.readProperty(PropertyUtil.ATTACH_IMAGE_SHOW)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ATTACH_IMAGE_SHOW)).first().text());
		//map.put(PropertyUtil.COMMENTURL, doc.select(PropertyUtil.readProperty(PropertyUtil.COMMENTURL)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.COMMENTURL)).first().text());

		
		return map;
	}
	
	//处理掉大字段中的标签
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
}