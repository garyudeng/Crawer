package integrated;

import org.springframework.stereotype.Service;


import bean.Book;
import bean.BookDB;
import bean.BookDetail;

/**
 * @des:For integrated
 * @author:Lee
 * @Date:13-8-13 update	
 */

@Service("ddIntegraed")
public class DDIntegraed extends Integrated{
	
	
	@Override
	public BookDetail insert2BookDetail(BookDetail detail) {
		//先判断是否存在
		if(isExit(detail)){
			//做数据跟新流程！！！！！！！！
			//TODO
			return null;
		}
		return getDao().insert2BookDetail(detail);
	}

	private boolean isExit(BookDetail detail) {
		return getDao().isExit("t_"+detail.getClass().getSimpleName(), detail.getIsbn());
	}

	@Override
	public Book filterFileds(Book book,BookDetail detail) {
		// TODO
		if(detail instanceof BookDB){
			//选取豆瓣的字段作为最优方案
			book.setBookname(detail.getBookName());
			book.setCatelog(detail.getCatelog());
			//....
		}
		return book;
	}
	
}
