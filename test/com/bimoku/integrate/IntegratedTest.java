package com.bimoku.integrate;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bimoku.common.bean.BookAmazon;
import com.bimoku.common.bean.BookDB;
import com.bimoku.common.bean.BookDD;
import com.bimoku.common.bean.BookJD;
import com.bimoku.common.bean.BookPub;

public class IntegratedTest {
	
	DDIntegrated ddIntegrated;
	JDIntegrated jdIntegrated;
	AmazonIntegrated amazonIntegrated;
	DBIntegrated dbIntegrated;
	PubIntegrated pubIntegrated;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		ddIntegrated = (DDIntegrated) ctx.getBean("ddIntegrated");
		jdIntegrated = (JDIntegrated) ctx.getBean("jdIntegrated");
		amazonIntegrated = (AmazonIntegrated) ctx.getBean("amazonIntegrated");
		dbIntegrated = (DBIntegrated) ctx.getBean("dbIntegrated");
		pubIntegrated = (PubIntegrated) ctx.getBean("pubIntegrated");
	}

	@Test 
	public void testIntegrated() {
		for(int a=1;a<2;a++){
			BookDD dd = new BookDD();
			BookDB db = new BookDB();
			BookJD jd = new BookJD();
			BookAmazon am = new BookAmazon();
			BookPub pub = new BookPub();
			
			
			dd.setBookName("love u"+a);
			dd.setIsbn("isbn:am+"+a);
			dd.setPrice(22.3+a);
			dd.setOutLine(a+"我爱你中国【当当】");
			
			db.setBookName("love u"+a);
			db.setIsbn("isbn:jd+"+a);
			db.setPrice(22.3+a);
			db.setOutLine(a+"我爱你中国【豆瓣】");
			
			jd.setBookName("love u"+a);
			jd.setIsbn("isbn:jd+"+a);
			jd.setPrice(22.3+a);
			jd.setOutLine(null);
			jd.setCover_pic("c:/img/aa.jpg");
			
			am.setBookName("love u"+a);
			am.setIsbn("isbn:am+"+a);
			am.setPrice(22.3+a);
			am.setOutLine(a+"我爱你中国【亚马逊】");
			
			pub.setBookName("love u"+a);
			pub.setIsbn("isbn:jd+"+a);
			pub.setPrice(22.3+a);
			pub.setOutLine(a+"我爱你中国【中国互动】");
			
			//==================
			ddIntegrated.integrated(dd);
			jdIntegrated.integrated(jd);
			amazonIntegrated.integrated(am);
			pubIntegrated.integrated(pub);
			dbIntegrated.integrated(db);
			//==================
			
			dd.setBookName("love u"+a);
			dd.setIsbn("isbn:pub+"+a);
			dd.setPrice(22.3+a);
			dd.setOutLine(a+"我爱你中国【当当】");
			
			db.setBookName("love u"+a);
			db.setIsbn("isbn:dd+"+a);
			db.setPrice(22.3+a);
			db.setOutLine(a+"我爱你中国【豆瓣】");
			
			jd.setBookName("love u"+a);
			jd.setIsbn("isbn:db+"+a);
			jd.setPrice(22.3+a);
			jd.setOutLine(a+"我爱你中国【京东】");
			
			am.setBookName("love u"+a);
			am.setIsbn("isbn:jd+"+a);
			am.setPrice(22.3+a);
			am.setOutLine(a+"我爱你中国【亚马逊】");
			
			pub.setBookName("love u"+a);
			pub.setIsbn("isbn:am+"+a);
			pub.setPrice(22.3+a);
			pub.setOutLine(a+"我爱你中国【中国互动】");
			
			ddIntegrated.integrated(dd);
			jdIntegrated.integrated(jd);
			amazonIntegrated.integrated(am);
			pubIntegrated.integrated(pub);
			dbIntegrated.integrated(db);
		}
	}
}