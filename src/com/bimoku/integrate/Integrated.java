package com.bimoku.integrate;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;

import com.bimoku.common.bean.Book;
import com.bimoku.common.bean.BookDB;
import com.bimoku.common.bean.BookDD;
import com.bimoku.common.bean.BookDetail;
import com.bimoku.util.AllPriceMapper;
import com.bimoku.util.RelationMapper;
import com.bimoku.repository.dao.BookDao;
import com.bimoku.util.filter.FieldFilter;

/**
 * @Intro 系统集成模块
 * @author LPM
 * @date 2013-8-20
 * 	
 * 目标：抽取数据插入数据库时，执行此操作！！
 * 1、明细库数据插入操作
 * 2、根据当前的书籍的isbn,在基本表里面查找是否存在，如果不存在则插入一则数据（基本数据）
 * 3、如果存在这条数据的基本数据，则跟新RELATIONSHIP字段
 * 
 */
public abstract class Integrated {
	
	@Autowired
	BookDao bookDao;
	@Autowired
	FieldFilter filter;
	
	/**
	 * 集成接口
	 * @param detail
	 * @throws IntegratedException
	 */
	public void integrated(BookDetail detail) throws IntegratedException{
		
		BookDetail det = this.putDataIntoDetail(detail);
		if(det == null) return;//当前程序终止！！！
		
		Book book = new Book();
		if(det.getIsbn() == null || det.getIsbn().equals("")) return;//终止集成库操作
		book.setIsbn(det.getIsbn());
		
		if (!bookDao.isExit(book.getIsbn())) {
			try {
				book = BookDetail.convert2Book(det);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//给集成数据枷锁
			book= Book.lockBook(book, det);
			//不存在
			bookDao.save(book);
		} else {
			try {
				//先找到
				Book bbk = bookDao.getBookByISBN(book.getIsbn());
				//数据集成（对象关系映射、重复性校验、重复字段择优）
				bbk = adapteData(bbk, det);
				//给集成数据枷锁
				bbk = Book.lockBook(bbk, det);
				//存在，更新就好！！！！
				bookDao.update(bbk);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * （对象关系映射、重复性校验、重复字段择优）
	 * 数据装载，目的是为了建立本表与明细表的关系
	 * 
	 * 也就是book表的整合relationship这个字段！！！！！！,
	 * 存放的是isbn作为唯一的标识！！！！！会改成主键：id
	 * 
	 * @param book
	 * @param detail
	 * @return
	 * @throws JSONException
	 */
	private Book adapteData(Book book,BookDetail detail) throws JSONException{
		
		//***************************
		// （对象关系映射）
		//relationship字段构造！！！！！！
		//***************************
		
		String newRelation = RelationMapper.update(book, detail);
		book.setRelationship(newRelation);
		
		//***************************
		// （价格更新）
		//all_price字段构造！！！！！！
		//***************************
		String newPrice = AllPriceMapper.update(book, detail);
		book.setAll_price(newPrice);
		
		//*******************************************
		//先处理空字段信息,如果某个字段是空，不论何种情况，允许修改
		//*******************************************
		book = fileBlank(book,detail);
		
		//***************************
		//(重复字段择优)
		//使用其他算法进行字段选取1111
		//数据编辑条件
		//如果当前书籍已经枷锁，（说明已经被豆瓣操作过，）
		//则主要更新关系和价格
		//【但是，如果某些字段没有时，可以向没有的字段更新数据】
		//***************************
		if(!Book.isLock(book)){
			book = filter(book,detail);
		}
		return book;
	}
	
	/**
	 * 1、填补book的空字段
	 * 2、调用子类的特殊处理
	 * @param book
	 * @param detail
	 * @return
	 */
	private Book fileBlank(Book book, BookDetail detail) {
		
		if(book.getAuthor() == null || "".equals(book.getAuthor()))
			book.setAuthor(detail.getAuthor());
		if(book.getAuthorIntro() == null || "".equals(book.getAuthorIntro()))
			book.setAuthorIntro(detail.getAuthorIntro());
		if(book.getCatelog() == null || "".equals(book.getCatelog()))
			book.setCatelog(detail.getCatelog());
		if(book.getCover_pic() == null || "".equals(book.getCover_pic()))
			book.setCover_pic(detail.getCover_pic());
		if(book.getDirectory() == null || "".equals(book.getDirectory()))
			book.setDirectory(detail.getDirectory());
		if(book.getPress() == null || "".equals(book.getPress()))
			book.setPress(detail.getPress());
		if(book.getTranslator() == null || "".equals(book.getTranslator()))
			book.setTranslator(detail.getTranslator());
		if(book.getPrice() == null || book.getPrice() == 0)
			book.setPrice(detail.getPrice());
		
		return filterFileds(book,detail);
	}

	/**
	 * 优先处理字段
	 * @param book
	 * @param detail
	 * @return
	 */
	private Book filter(Book book,BookDetail detail){
		// 首先做最长字符串匹配
		book = baseInfoAdapte(book, detail);
		//优先选择豆瓣的信息为准
		if(detail instanceof BookDB){
			//选取豆瓣的字段作为最优方案,bookName为基本字段，必须存在
			if(detail.getBookName() != null)
				book.setBookname(detail.getBookName());
			if(detail.getOutLine() != null)
				book.setOutline(detail.getOutLine());
			if(detail.getCatelog() != null)
				book.setCatelog(detail.getCatelog());
			
			//TODO 待补冲....
		}
		if(detail instanceof BookDD){
			//价格考虑以当当的价格作为官方价格//TODO 暂定
			book.setPrice(detail.getPrice());
		}
		return book;
	}
	
	/**
	 * 基础处理。主要处理的是，【书名，简介】
	 * @param book
	 * @param detail
	 * @return
	 */
	protected Book baseInfoAdapte(Book book,BookDetail detail){
		String[] names = {
				book.getBookname(),
				detail.getBookName()
		};
		book.setBookname(filter.longestMatch(names));//最长匹配处理bookname
		
		String[] outline = {
				book.getOutline(),
				detail.getOutLine()
		};
		book.setOutline(filter.longestMatch(outline));//最长匹配处理outline
		
		return book;
	}
	
	/**
	 * 明细数据插入
	 * @param detail
	 * @return
	 */
	protected abstract BookDetail putDataIntoDetail(BookDetail detail);
	/**
	 * (重复字段择优)
	 * 只能更新book中没有的字段。
	 * @param book
	 * @return
	 */
	protected abstract Book filterFileds(Book book,BookDetail detail);
	
}
