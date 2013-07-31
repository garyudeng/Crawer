package dao;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.Book;

public class BookDaoTest {
	
	BaseDao baseDao;
	BookDao bookDao;
	Book book  = new Book();
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		System.out.println(ctx == null?"y":"n");
		
		bookDao = (BookDao) ctx.getBean("bookDao");
		baseDao = (BaseDao) ctx.getBean("baseDao");
		
		book.setBookName("ddddddd");
		book.setPrice(22);
		
		book.setAuthor("31kj");
		book.setUuId("3124u091");
		book.setIsbn("32141B");
		
	}

	@Test
	public void testSave() throws Exception {
		bookDao.save(book);
	}
	
	
	@Test
	public void query() throws Exception{
		Book b = bookDao.query(book);
		assertEquals(book, b);
	}
	
	@Test
	public void update() throws Exception{
		book.setBookName("update_name");
		bookDao.update(book,"BOOKNAME = ddddddd");
		
		Book b = bookDao.query(book);
		assertEquals(book, b);
		
		assertEquals(book, b);
	}
	
	@Test
	public void delete() throws  Exception{
		bookDao.delete(book);
	}
}
