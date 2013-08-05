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
	public void insert2BookDetail(BookDetail detail) {
		if(getDao().insert2BookDetail(detail) == -1){
			System.out.println("error!");
			//在此，你可以定义自己的插入规则，，可复杂，可简单！！！
			
		}
	}
	
}
