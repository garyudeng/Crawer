package integrated;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.BookAmazon;
import bean.BookDB;
import bean.BookDD;

public class IntegratedTest {
	
	Integrated indd;
	Integrated indb;
	Integrated inam;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		indd = (DDIntegraed) ctx.getBean("ddIntegraed");
		indb = (DBIntegrated) ctx.getBean("dbIntegrated");
		inam = (AmazonIntegrated) ctx.getBean("amazonIntegrated");
	}

	@Test
	public void testIntegrated() {
		BookDD bdd = new BookDD();
		BookAmazon ba = new BookAmazon();
		BookDB bdb = new BookDB();
				
		bdd.setBookName("111当当网图书。。。。哈哈");
		bdd.setPrice(234.0);
		bdd.setAuthor("111当当网图书");
		bdd.setIsbn("2222222222222B");
		
		ba.setBookName("22亚马逊网图书。。。。哈哈");
		ba.setPrice(221.0);
		ba.setAuthor("亚马逊网图书亚马逊网图书");
		ba.setIsbn("333333333333B");
		
		bdb.setBookName("3333豆瓣网图书。。。。哈哈");
		bdb.setPrice(22.0);
		bdb.setAuthor("豆瓣网图书豆瓣网图书豆瓣网图书");
		bdb.setIsbn("111111111111B");
		
		indd.integrated(bdd);
		indb.integrated(bdb);
		inam.integrated(ba);
	}

}
