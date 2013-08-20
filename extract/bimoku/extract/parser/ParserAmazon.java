package bimoku.extract.parser;


import java.io.File;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bimoku.extract.common.PropertyUtil;

import com.bimoku.common.bean.BookAmazon;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.integrate.AmazonIntegrated;
import com.bimoku.integrate.Integrated;

@Component("parserAmazon")
public class ParserAmazon extends Parser{
	
	@Autowired
	AmazonIntegrated amazonIntegrated;
	
	@Override
	protected Integrated getIntegratedDao() {
		if(amazonIntegrated == null)
			throw new RuntimeException("spring bean 实例化出错");
		return amazonIntegrated;
	}

	@Override
	protected BookDetail fieldFilter(Map<String, String> map) {
		BookAmazon bookamazon = new BookAmazon();
		
		String author_trans = map.get(PropertyUtil.AUTHOR_TRANSLATOR);
		System.out.println(author_trans);
		//String authorIntro = map.get(PropertyUtil.AUTHOR_INTRO);
		String bookName = map.get(PropertyUtil.BOOKNAME);
		String cover_pic = map.get(PropertyUtil.COVER_PIC);
		//String directory = map.get(PropertyUtil.DIRECTORY);
		//String isbn = map.get(PropertyUtil.ISBN);
		//String press = map.get(PropertyUtil.PRESS);
		String price = map.get(PropertyUtil.PRICE);
		String PUBLISHED_PRICE = map.get(PropertyUtil.PUBLISHED_PRICE);
		//String translator = map.get(PropertyUtil.TRANSLATOR);
		//String version = map.get(PropertyUtil.VERSION);
		String intro_clearfix = map.get(PropertyUtil.intro_clearfix);
		String book_desciption = map.get(PropertyUtil.BOOK_DESCIPTION);
		//TODO 这个过程多处理
		
		
		String author = "";
		String isbn = "";
		String press = "";
		String translator= "";
		String version = "";
		Double pric = 0.0;
		Double pub_pric = 0.0;
//		System.out.println(intro_clearfix);
		String[] infoparam = Patternmatch_Amazon.patternmatchContent(intro_clearfix);
	    press = infoparam[0];
	    version = infoparam[1];
	    try{
		    isbn = infoparam[3].trim();
			}catch(NullPointerException e){
				isbn = "";
			}
	    try{
	    	pric = Double.valueOf(price.replaceAll("\\?|￥ ", ""));
	    }catch(NumberFormatException e){
	    	pric = 0.0;
	    }
	    try{
	    	pub_pric = Double.valueOf(PUBLISHED_PRICE.replaceAll("\\?|￥ ", ""));
	    }catch(NumberFormatException e){
	    	pub_pric = 0.0;
	    }
	    try{
			cover_pic = cover_pic.substring(0, cover_pic.length()>252?252:cover_pic.length()-1);
			}catch(StringIndexOutOfBoundsException e){
				cover_pic = "";
			}
		try{
			bookName = bookName.substring(0, bookName.length()>252?252:bookName.length()-1);
			}catch(StringIndexOutOfBoundsException e){
				bookName = "";
			}
		try{
			book_desciption = book_desciption.substring(0, book_desciption.length()>2000?2000:book_desciption.length()-1);
			}catch(StringIndexOutOfBoundsException e){
				book_desciption = "";
			}
		//TODO
		String[] infoparam_auth = Patternmatch_Amazon.patternmatchAUT_TRANS(author_trans);
		author = infoparam_auth[0]==null?"": infoparam_auth[0].substring(0, infoparam_auth[0].length()>45?45:infoparam_auth[0].length()-1);;
		
		bookamazon.setAuthor(author);
		//bookamazon.setAuthorIntro(authorIntro);
		bookamazon.setBookName(bookName);
		bookamazon.setCover_pic(cover_pic);
		//bookamazon.setDirectory(directory);
		bookamazon.setIsbn(isbn);
		bookamazon.setPress(press);
		bookamazon.setPrice(pric);
		bookamazon.setTranslator(translator);
		bookamazon.setVersion(version);
//		bookamazon.setPub_price(pub_pric);
		bookamazon.setOutLine(book_desciption);
		System.out.println(bookamazon.toString());
		
		return bookamazon;
	}

	@Override
	protected Map<String, String> getElementsInfo(String filepath) throws Exception{
		
		Map<String, String> map = new HashedMap();
		File input = new File(filepath);
		Document doc = Jsoup.parse(input, "UTF-8");

	    
			map.put(PropertyUtil.BOOKNAME, doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first().text());
			map.put(PropertyUtil.AUTHOR_TRANSLATOR, doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_TRANSLATOR)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_TRANSLATOR)).first().text());
			map.put(PropertyUtil.BOOK_DESCIPTION, doc.select(PropertyUtil.readProperty(PropertyUtil.BOOK_DESCIPTION)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.BOOK_DESCIPTION)).first().text());
			//map.put(PropertyUtil.PRESS, doc.select(PropertyUtil.readProperty(PropertyUtil.PRESS)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PRESS)).first().text());
			//map.put(PropertyUtil.VERSION, doc.select(PropertyUtil.readProperty(PropertyUtil.VERSION)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.VERSION)).first().text());
		//TODO	map.put(PropertyUtil.ITEM_ID, doc.select(PropertyUtil.readProperty(PropertyUtil.ITEM_ID)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ITEM_ID)).first().text());
			//map.put(PropertyUtil.ISBN, doc.select(PropertyUtil.readProperty(PropertyUtil.ISBN)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ISBN)).first().text());
			map.put(PropertyUtil.intro_clearfix, doc.select(PropertyUtil.readProperty(PropertyUtil.intro_clearfix)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.intro_clearfix)).first().text());
			map.put(PropertyUtil.PRICE, doc.select(PropertyUtil.readProperty(PropertyUtil.PRICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PRICE)).first().text());
			map.put(PropertyUtil.PUBLISHED_PRICE, doc.select(PropertyUtil.readProperty(PropertyUtil.PUBLISHED_PRICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PUBLISHED_PRICE)).first().text());

//			Elements linksElements = doc.select(PropertyUtil.readProperty(PropertyUtil.CLASSFY));
//			String CLASSFY = "";
//			for (Element ele : linksElements) {
//				CLASSFY += ele.text() + ">";
//			}
//			map.put(PropertyUtil.CLASSFY, CLASSFY);
			
     		map.put(PropertyUtil.COVER_PIC, doc.select(PropertyUtil.readProperty(PropertyUtil.COVER_PIC)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.COVER_PIC)).first().attr("src"));
//			map.put(PropertyUtil.EDITOR_CHOICE, doc.select(PropertyUtil.readProperty(PropertyUtil.EDITOR_CHOICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.EDITOR_CHOICE)).first().text());
//			map.put(PropertyUtil.CONTENT_CHOICE, doc.select(PropertyUtil.readProperty(PropertyUtil.CONTENT_CHOICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.CONTENT_CHOICE)).first().text());
//			map.put(PropertyUtil.AUTHOR_INTRO, doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_INTRO)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_INTRO)).first().text());
//			map.put(PropertyUtil.DIRECTORY, doc.select(PropertyUtil.readProperty(PropertyUtil.DIRECTORY)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.DIRECTORY)).first().text());

//			map.put(PropertyUtil.MEDIA_REVIEWS, doc.select(PropertyUtil.readProperty(PropertyUtil.MEDIA_REVIEWS)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.MEDIA_REVIEWS)).first().text());
			//map.put(PropertyUtil.EXTRACT, doc.select(PropertyUtil.readProperty(PropertyUtil.EXTRACT)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.EXTRACT)).first().text());
			//map.put(PropertyUtil.ATTACH_IMAGE_SHOW, doc.select(PropertyUtil.readProperty(PropertyUtil.ATTACH_IMAGE_SHOW)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ATTACH_IMAGE_SHOW)).first().text());
			//map.put(PropertyUtil.COMMENTURL, doc.select(PropertyUtil.readProperty(PropertyUtil.COMMENTURL)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.COMMENTURL)).first().text());

		
		return map;
	}

}