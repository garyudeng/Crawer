package generate;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.map.HashedMap;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

/**
 * 写xml
 * @author LPM
 *
 */
public class WriteXml {

	/**
	 * 写数据到xml
	 * 
	 * @param datas
	 * @throws Exception
	 */
	public static void witeXml(@SuppressWarnings("rawtypes") Map map,String xmlFilePath)
			throws Exception {
		Document doc = DomHelper.createDomFJ();
		doc.addComment("以utf-8的编码");
		Element books = DomHelper.appendChile("books", doc);
		/* 格式化输出 */
		OutputFormat format = OutputFormat.createPrettyPrint();// 紧缩
		format.setEncoding("utf-8"); // 设置utf-8编码
		Element book = DomHelper.appendChile("book", books);
		
		//构建树形结构
		rescue(book, map);
		
		//输出
		FileOutputStream fos = new FileOutputStream(xmlFilePath);
		XMLWriter writer=new XMLWriter(fos,format);
		//写结构
		writer.write(doc);
		//关闭流
		writer.close();
	}
	
	/**
	 * 迭代
	 * @param root
	 * @param map
	 */
	private static void rescue(Element root,Map map){
		Iterator iterator = map.keySet().iterator();
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			if(key.equals("read_statis") || key.equals("rating")){
				//解析map
				Element el = DomHelper.appendChile(key, root);
				Map data = (Map) map.get(key);
				//迭代
				rescue(el, data);
			}else{
				String value = (String) map.get(key);
				DomHelper.appendChile(key, root).setText(value);
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		Map datas = new HashedMap();
		datas.put("url", "aa");
		datas.put("website", "bb");
		datas.put("title", "cc");
		datas.put("title_en", "dd");
		datas.put("press", "ee");
		datas.put("pages", "ff");
		datas.put("date", "gg");
		datas.put("isbn", "hh");
		
		witeXml(datas, "d:/11.xml");
	}
}
