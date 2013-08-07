package util;

import org.codehaus.jettison.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import dao.BookDao;
import dao.IntegratedDao;
import dao.impl.IntegratedDaoImpl;

import bean.Book;
import bean.BookDD;
import bean.BookDetail;
import bean.BookAmazon;

/**
 * 系统集成模块
 * @author LPM
 *	
 * 目标：抽取数据插入数据库时，执行此操作！！
 * 1、明细库数据插入操作
 * 2、根据当前的书籍的isbn,在基本表里面查找是否存在，如果不存在则插入一则数据（基本数据）
 * 3、如果存在这条数据的基本数据，则跟新RELATIONSHIP字段
 * 4、
 */
public abstract class Integrated {
	
	@Autowired
	IntegratedDao integratedDao;
	@Autowired
	BookDao bookDao;
	
	public void integrated(BookDetail detail) {
		
		//存到detail数据库！！
		//如果这条记录存在（存在的判断标准是isbn是否存在）
		//则进入细则数据跟新流程
		BookDetail det = this.insert2BookDetail(detail);
		
		if(det == null) return;//当前程序终止！！！
		
		Book book = new Book();
		book.setIsbn(det.getIsbn());
		
		if (!isExit(book)) {
			try {
				book = BookDetail.convert2Book(det);
			} catch (JSONException e) {
				e.printStackTrace();
			}
			//不存在
			insert2Book(book);
		} else {
			try {
				//先找到
				Book bbk = bookDao.query(book);
				//数据跟新
				bbk = adapteData(bbk, det);
				//存在，跟新就好！！！！
				update2Book(bbk);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 跟新书籍信息
	 * @param book
	 */
	public void update2Book(Book book){
		integratedDao.update2Book(book);
	}
	
	/**
	 * 插入一则数据
	 * @param book
	 */
	public void insert2Book(Book book){
		integratedDao.insert2Book(book);
	}

	/**
	 * 根据isbn，【按段该书籍是否存在
	 * @param book
	 * @return
	 */
	public boolean isExit(Book book){
		System.out.println("t_"+book.getClass().getSimpleName());
		return integratedDao.isExit("t_"+book.getClass().getSimpleName(),book.getIsbn());
	}
	
	//子类必须实现的
	public abstract BookDetail insert2BookDetail(BookDetail detail);
	
	/**
	 * 数据张载，目的是为了建立本表与明细表的关系
	 * 
	 * 也就是book表的整合relationship这个字段！！！！！！,
	 * 存放的是isbn作为唯一的标识！！！！！会改成主键：id
	 * 
	 * @param book
	 * @param detail
	 * @return
	 * @throws JSONException
	 */
	public Book adapteData(Book book,BookDetail detail) throws JSONException{
	
		//***************************
		//relationship字段构造！！！！！！
		//***************************
		
		String relation = book.getRelationship();
		String constructor = (relation==null || relation.equals(""))?"[]":"["+book.getRelationship()+"]"; 
		
		RelationMapper mapper = RelationMapper.str2Bean(constructor);
		
		if(detail instanceof BookDD){
			mapper.setDD(detail.getId()+"");
		}else if(detail instanceof BookAmazon){
			mapper.setAM(detail.getId()+"");
		}
		
		book.setRelationship(RelationMapper.bean2Json(mapper));
		
		//***************************
		//使用其他算法进行字段选取1111
		//***************************
		//TODO
		
		return book;
	}
	
	/**
	 * 获取单一数据操纵访问对象（非线程安全的单例）
	 * @return
	 */
	public IntegratedDao getDao(){
		if(integratedDao == null){
			return new IntegratedDaoImpl();
		}
		return this.integratedDao;
	}
}
