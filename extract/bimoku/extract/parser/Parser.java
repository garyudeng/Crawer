package bimoku.extract.parser;


import java.util.Map;

import org.apache.commons.collections.map.HashedMap;

import com.bimoku.common.bean.BookDetail;
import com.bimoku.integrate.Integrated;

import bimoku.extract.common.PropertyUtil;
import bimoku.extract.common.exception.ExtractException;

/**
 * 抽取主程序
 * @author 梅良--
 * @author LPM
 *
 */
public abstract class Parser {
	
	/**
	 * 抽取主方法：抽取分为这样几个步骤
	 * 1、定位抽取，，，，这个部分主要就是根据抽取规则得到一个大概的字段数据
	 * 比如，我要抽取标题：Element el = doc.select(PropertyUtil.get("title"));
	 * 
	 * 2、字段过滤
	 * 比如抽取出来的title：<b>我爱你中国【特价书籍】</b>
	 * 过滤之后应该得到：我爱你中国特价书籍 或者 我爱你中国【特价书籍】
	 * 
	 * 3、数据持久化
	 * 调用集成接口
	 * 
	 */
	public void parser(String filepath) throws ExtractException{
		//抽取
		Map<String, String> map = new HashedMap();
		try {
			map = getElementsInfo(filepath);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		//没有抓到书名或者isbn的，都不再进行后续操作
		if(map.get(PropertyUtil.BOOKNAME) == null || map.get(PropertyUtil.ISBN) == null){
			throw new ExtractException();
		}
		//过滤，精确抽取
		BookDetail bookDetail = fieldFilter(map);
		if(bookDetail.getBookName() == null || bookDetail.getIsbn() == null){
			throw new ExtractException();
		}
		//数据持久化
		getIntegratedDao().integrated(bookDetail);
	}
	
	/**
	 * 从子类传递一个集成接口给父类
	 * @return
	 */
	protected abstract Integrated getIntegratedDao();

	/**
	 * 字段信息过滤
	 * @param map
	 * @return
	 */
	protected abstract BookDetail fieldFilter(Map<String, String> map);

	/**
	 * 抽取
	 * @return
	 */
	protected abstract Map<String, String> getElementsInfo(String filepath) throws Exception;
	
}
