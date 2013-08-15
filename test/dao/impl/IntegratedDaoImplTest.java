package dao.impl;

import static org.junit.Assert.*;
import integrated.DDIntegraed;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.IntegratedDao;

import bean.BookDD;

public class IntegratedDaoImplTest {
	
	@Autowired
	IntegratedDao dao;
	
	
//	@Before
//	public void setUp() throws Exception {
//		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
//		dao = (IntegratedDao) ctx.getBean("integratedDao");
//	}

//	@Test
	public static void main(String[] args) {
		
		for(int a = 1000 ; a < 1001 ; a++){
			BookDD bdd = new BookDD();
			bdd.setBookName(a + "111当当网图书。。。。哈哈");
			bdd.setPrice(234.0+a);
			bdd.setAuthor( a + "111当当网图书");
			bdd.setIsbn(a + "2222222222222B");
//			dao.insert2BookDetail(bdd);
		}
	}

//	@Test
//	public void testUpdate2Book() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testIsExit() {
//		fail("Not yet implemented");
//	}

}
