package generate;

import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * 读取xml
 * @author LPM
 *
 */
public class ReadXml {
	/**
	 * 读取xml
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static Map readXml(String xmlFilePath) throws Exception{
		//读取xml文档
    	SAXReader saxReader=new SAXReader();
    	Document doc=null;
    	doc =saxReader.read(new FileInputStream(new File(xmlFilePath)));
		List<Element> els = doc.getRootElement().elements();
		//装载数据
		return rescue(els, new HashMap());
	}
	
	private static Map rescue(List<Element> els,Map map){
		for(Element el : els){
			String key = el.getName();
			String value = el.getTextTrim();
			
			if(el.elements().size() > 0){
				map.put(key, rescue(el.elements(),new HashMap()));
			}else{
				map.put(key, value);
			}
			
		}
		return map;
	}
	
	public static void main(String[] args) throws Exception {
		System.out.println(readXml("d:/11.xml"));
	}
}
