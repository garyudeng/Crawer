package integrated;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.BookAmazon;
import bean.BookDB;
import bean.BookDD;
import bean.BookJD;

public class IntegratedTest {
	
	Integrated indd;
	Integrated indb;
	Integrated inam;
	Integrated injd;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		indd = (DDIntegraed) ctx.getBean("ddIntegraed");
		indb = (DBIntegrated) ctx.getBean("dbIntegrated");
		inam = (AmazonIntegrated) ctx.getBean("amazonIntegrated");
		injd = (JDIntegrated) ctx.getBean("jdIntegrated");
	}

	@Test
	public void testIntegrated() {
		
		for(int i = 0;i < 30000 ; i++){
			
			BookDD bdd = new BookDD();
			BookAmazon ba = new BookAmazon();
			BookDB bdb = new BookDB();
			BookJD bjd = new BookJD();
			
			bdd.setBookName("111当当网图书。。。。哈哈");
			bdd.setPrice(234.0);
			bdd.setAuthor("111当当网图书");
			bdd.setIsbn("111111111111B"+i);
			
			ba.setBookName("22亚马逊网图书。。。。哈哈");
			ba.setPrice(221.0);
			ba.setAuthor("亚马逊网图书亚马逊网图书");
			ba.setIsbn("22222222222222B"+i);
			
			bdb.setBookName("3333豆瓣网图书。。。。哈哈");
			bdb.setPrice(22.0);
			bdb.setAuthor("豆瓣网图书豆瓣网图书豆瓣网图书");
			bdb.setIsbn("333333333333B"+i);
			
			bjd.setBookName("111京东网图书。。。。哈哈");
			bjd.setPrice(234.0);
			bjd.setAuthor("111京东网图书");
			bjd.setIsbn("444444444B"+i);
			
			indd.integrated(bdd);
			indb.integrated(bdb);
			inam.integrated(ba);
			injd.integrated(bjd);
		}
		
//		bdd.setBookName("111当当网图书。。。。哈哈");
//		bdd.setPrice(234.0);
//		bdd.setAuthor("111当当网图书");
//		bdd.setIsbn("111111111111B");
//		
//		ba.setBookName("22亚马逊网图书。。。。哈哈");
//		ba.setPrice(221.0);
//		ba.setAuthor("亚马逊网图书亚马逊网图书");
//		ba.setIsbn("22222222222222B");
//		
//		bdb.setBookName("3333豆瓣网图书。。。。哈哈");
//		bdb.setPrice(22.0);
//		bdb.setAuthor("豆瓣网图书豆瓣网图书豆瓣网图书");
//		bdb.setIsbn("333333333333B");
//		
//		
//		indd.integrated(bdd);
//		indb.integrated(bdb);
//		inam.integrated(ba);
	}

}
