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
	
	public void integrated(BookDetail detail) {
		
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
			//不存在
			bookDao.save(book);
		} else {
			try {
				//先找到
				Book bbk = bookDao.getBookByISBN(book.getIsbn());
				//数据集成（对象关系映射、重复性校验、重复字段择优）
				bbk = adapteData(bbk, det);
				//存在，跟新就好！！！！
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
		
		//***************************
		//(重复字段择优)
		//使用其他算法进行字段选取1111
		//***************************
		book = filter(book,detail);
		
		return book;
	}
	
	private Book filter(Book book,BookDetail detail){
		// 首先做最长字符串匹配
		book = baseInfoAdapte(book, detail);
		//优先选择豆瓣的信息为准
		if(detail instanceof BookDB){
			//选取豆瓣的字段作为最优方案
			book.setBookname(detail.getBookName());
			book.setOutline(detail.getOutLine());
			book.setCatelog(detail.getCatelog());
			//....
		}
		if(detail instanceof BookDD){
			//价格考虑以当当的价格作为官方价格
			book.setPrice(detail.getPrice());
		}
		
		book = filterFileds(book,detail);
		return book;
	}
	
	/**
	 * 基础处理。主要处理的是，书名，简介
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
		book.setOutline(filter.longestMatch(outline));//最长匹配处理bookname
		
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
	 * @param book
	 * @return
	 */
	protected abstract Book filterFileds(Book book,BookDetail detail);
	
}
