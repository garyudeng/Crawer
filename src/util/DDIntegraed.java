package util;

import org.springframework.stereotype.Service;

import bean.BookDetail;

/**
 * 
 * @author Administrator
 *
 */

@Service("ddIntegraed")
public class DDIntegraed extends Integrated{
	
	
	@Override
	public BookDetail insert2BookDetail(BookDetail detail) {
		//先判断是否存在
		if(isExit(detail)){
			//做数据跟新流程！！！！！！！！
			
			return null;
		}
		return getDao().insert2BookDetail(detail);
	}

	private boolean isExit(BookDetail detail) {
		return getDao().isExit("t_"+detail.getClass().getSimpleName(), detail.getIsbn());
	}
	
}
