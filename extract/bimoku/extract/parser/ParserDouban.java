package bimoku.extract.parser;



import java.io.File;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import bimoku.extract.common.PropertyUtil;

import com.bimoku.common.bean.BookDB;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.integrate.DBIntegrated;
import com.bimoku.integrate.Integrated;

@Component("parserDouban")
public class ParserDouban extends Parser{
	
	@Autowired
	DBIntegrated doubanIntegrated;
	
	@Override
	protected Integrated getIntegratedDao() {
		if(doubanIntegrated == null)
			throw new RuntimeException("spring bean 实例化出错");
		return doubanIntegrated;
	}

	@Override
	protected BookDetail fieldFilter(Map<String, String> map) {
		BookDB bookdouban = new BookDB();
		
		//String author_trans = map.get(PropertyUtil.AUTHOR_TRANSLATOR);
		//System.out.println(author_trans);
		//String authorIntro = map.get(PropertyUtil.AUTHOR_INTRO);
		String bookName = map.get(PropertyUtil.BOOKNAME);
		String cover_pic = map.get(PropertyUtil.COVER_PIC);
		//String directory = map.get(PropertyUtil.DIRECTORY);
		//String isbn = map.get(PropertyUtil.ISBN);
		//String press = map.get(PropertyUtil.PRESS);
		//String price = map.get(PropertyUtil.PRICE);
		//String PUBLISHED_PRICE = map.get(PropertyUtil.PUBLISHED_PRICE);
		//String translator = map.get(PropertyUtil.TRANSLATOR);
		//String version = map.get(PropertyUtil.VERSION);
		String intro_clearfix = map.get(PropertyUtil.intro_clearfix);
		String EXTRACT = map.get(PropertyUtil.EXTRACT);
		//TODO 这个过程多处理
		
		
		System.out.println(intro_clearfix);
		String author = "";
		String isbn = "";
		String press = "";
		String translator= "";
		String version = "";
		Double pric = 0.0;
		Double pub_pric = 0.0;
		
//		System.out.println(intro_clearfix);
		String[] infoparam = Patternmatch_Douban.patternmatchContent(intro_clearfix);
		
		 try{
			 author = infoparam[0].trim();
				}catch(NullPointerException e){
					author = "";
				}
		
		 try{
			 press = infoparam[1].trim();
				}catch(NullPointerException e){
					press = "";
				}
		try{
			pric =  Double.valueOf(infoparam[2]);
		}catch(NumberFormatException e){
			pric = 0.0;
		}catch(NullPointerException e){
			pric = 0.0;
		}
		
	    try{
		    isbn = infoparam[3].trim();
			}catch(NullPointerException e){
				isbn = "";
			}
	    try{
	    	translator = infoparam[4].trim();
			}catch(NullPointerException e){
				translator = "";
			}
	    try{
			cover_pic = cover_pic.substring(0, cover_pic.length()>45?45:cover_pic.length());
			}catch(StringIndexOutOfBoundsException e){
				cover_pic = "";
			}
		try{
			bookName = bookName.substring(0, bookName.length()>45?45:bookName.length());
			}catch(StringIndexOutOfBoundsException e){
				bookName = "";
			}
		
			EXTRACT = EXTRACT.substring(0, EXTRACT.length()>2000?2000:EXTRACT.length());
			
		//TODO
		/*String[] infoparam_auth = Patternmatch_Amazon.patternmatchAUT_TRANS(author_trans);
		author = infoparam_auth[0]==null?"": infoparam_auth[0].substring(0, infoparam_auth[0].length()>45?45:infoparam_auth[0].length());;
		*/
		bookdouban.setAuthor(author);
		//bookamazon.setAuthorIntro(authorIntro);
		bookdouban.setBookName(bookName);
		bookdouban.setCover_pic(cover_pic);
		//bookamazon.setDirectory(directory);
		bookdouban.setIsbn(isbn);
		bookdouban.setPress(press);
		bookdouban.setPrice(pric);
		bookdouban.setTranslator(translator);
		//bookdouban.setVersion(version);
//		bookamazon.setPub_price(pub_pric);
		bookdouban.setOutLine(EXTRACT);
		System.out.println(bookdouban.toString());
		
		return bookdouban;
	}

	@Override
	protected Map<String, String> getElementsInfo(String filepath) throws Exception{
		
		Map<String, String> map = new HashedMap();
		File input = new File(filepath);
		Document doc = Jsoup.parse(input, "UTF-8");

	    
			map.put(PropertyUtil.BOOKNAME, doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.BOOKNAME)).first().text());
			//map.put(PropertyUtil.AUTHOR_TRANSLATOR, doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_TRANSLATOR)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.AUTHOR_TRANSLATOR)).first().text());
			//map.put(PropertyUtil.BOOK_DESCIPTION, doc.select(PropertyUtil.readProperty(PropertyUtil.BOOK_DESCIPTION)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.BOOK_DESCIPTION)).first().text());
			//map.put(PropertyUtil.PRESS, doc.select(PropertyUtil.readProperty(PropertyUtil.PRESS)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PRESS)).first().text());
			//map.put(PropertyUtil.VERSION, doc.select(PropertyUtil.readProperty(PropertyUtil.VERSION)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.VERSION)).first().text());
		//TODO	map.put(PropertyUtil.ITEM_ID, doc.select(PropertyUtil.readProperty(PropertyUtil.ITEM_ID)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ITEM_ID)).first().text());
			//map.put(PropertyUtil.ISBN, doc.select(PropertyUtil.readProperty(PropertyUtil.ISBN)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ISBN)).first().text());
			map.put(PropertyUtil.intro_clearfix, doc.select(PropertyUtil.readProperty(PropertyUtil.intro_clearfix)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.intro_clearfix)).first().text());
			//map.put(PropertyUtil.PRICE, doc.select(PropertyUtil.readProperty(PropertyUtil.PRICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PRICE)).first().text());
			//map.put(PropertyUtil.PUBLISHED_PRICE, doc.select(PropertyUtil.readProperty(PropertyUtil.PUBLISHED_PRICE)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.PUBLISHED_PRICE)).first().text());

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
     		if(doc.select(PropertyUtil.readProperty(PropertyUtil.EXTRACT)).last()!=null){
			map.put(PropertyUtil.EXTRACT, doc.select(PropertyUtil.readProperty(PropertyUtil.EXTRACT)).last().text());
     		}else{
     			map.put(PropertyUtil.EXTRACT, doc.select("div.related_info>div#link-report.indent>div>div.intro").last().text());
     		}
			//map.put(PropertyUtil.ATTACH_IMAGE_SHOW, doc.select(PropertyUtil.readProperty(PropertyUtil.ATTACH_IMAGE_SHOW)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.ATTACH_IMAGE_SHOW)).first().text());
			//map.put(PropertyUtil.COMMENTURL, doc.select(PropertyUtil.readProperty(PropertyUtil.COMMENTURL)).first()==null?"":doc.select(PropertyUtil.readProperty(PropertyUtil.COMMENTURL)).first().text());

		
		return map;
	}

}