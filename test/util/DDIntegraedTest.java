package util;

import integrated.DDIntegraed;
import integrated.Integrated;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bean.BookAmazon;
import bean.BookDD;

public class DDIntegraedTest {
	
	Integrated in ;
	
	@Before
	public void setUp() throws Exception {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/beans.xml");
		in = (DDIntegraed) ctx.getBean("ddIntegraed");
	}

	@Test
	public void testIntegrated() {
		BookDD bd = new BookDD();
		BookAmazon ba = new BookAmazon();
				
		bd.setBookName("cavdvas2222@@@@@@@@我是擦拭");
		bd.setPrice(22.0);
		bd.setAuthor("31k擦擦撒j");
		bd.setIsbn("22222141B");
		
		ba.setBookName("vvvvvvvvaaaaaaaaaaa#$@$@");
		ba.setPrice(22.0);
		ba.setAuthor("31kj");
		ba.setIsbn("42141B");
		
		in.integrated(bd);
		//in.integrated(ba);
	}

}
