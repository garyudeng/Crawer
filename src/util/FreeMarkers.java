package util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import freemarker.ext.dom.NodeModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import generate.Generate;

public class FreeMarkers {

	/**
	 * 向字符串中绑定一个变量值
	 * 
	 * @param templateString
	 * @param model
	 * @return
	 */
	public static String renderString(String templateString,
			Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			Template t = new Template("name", new StringReader(templateString),
					new Configuration());
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 
	 * @param t
	 * @param model
	 * @return
	 */
	public static String renderString(Template t, Map<String, ?> model) {
		try {
			StringWriter result = new StringWriter();
			t.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 向一个tempalte文件中绑定数据
	 * 
	 * @param template
	 * @param model
	 * @return
	 */
	public static String renderTemplate(Template template, Object model) {
		try {
			StringWriter result = new StringWriter();
			template.process(model, result);
			return result.toString();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String readXml(String xmlPath) throws Exception {
		Template temp = Generate.buildTemplate();
		// 文件流
		InputStream in = new FileInputStream(new File(xmlPath));
		// 以便读取ddd.xml文件
		InputSource ins = new org.xml.sax.InputSource(in);

		Map root = new HashMap();
		// 特别注意此时的doc,这个符号将是，FreeMarker模板中取数的根！！！！！
		// 这里读取xml文件，并处理成root对象
		try {
			root.put("doc", NodeModel.parse(ins));
			// 建立内存字符串流
			StringWriter w = new StringWriter();
			// 模板开始按模板中的要求把用户输入的数据进行转换，并输出到字符串流中
			temp.process(root, w);
			// 字符串流输出到屏幕
			System.out.println(w.toString());

			return w.toString();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 初始化配置
	 * 
	 * @param directory
	 * @return
	 * @throws IOException
	 */
	public static Configuration buildConfiguration(String directory)
			throws IOException {
		Configuration cfg = new Configuration();
		Resource path = new DefaultResourceLoader().getResource(directory);
		cfg.setDirectoryForTemplateLoading(path.getFile());
		return cfg;
	}

	public static void main(String[] args) throws Exception {
		// // renderString
		// Map<String, String> model =
		// com.google.common.collect.Maps.newHashMap();
		// model.put("userName", "calvin");
		// String result = FreeMarkers.renderString("hello ${userName}", model);
		// System.out.println(result);
		// // renderTemplate
		// Configuration cfg = FreeMarkers.buildConfiguration("classpath:/");
		// Template template = cfg.getTemplate("testTemplate.ftl");
		// String result2 = FreeMarkers.renderTemplate(template, model);
		// System.out.println(result2);

		// Map<String, String> model = new HashMap();
		// model.put("userName", "calvin");
		// String result =
		// FreeMarkers.renderString("hello ${userName} ${r'${userName}'}",
		// model);
		// System.out.println(result);

		// Map<String, String> datas = new HashMap();
		// datas.put("url", "aa");
		// datas.put("website", "bb");
		// datas.put("title", "cc");
		// datas.put("title_en", "dd");
		// datas.put("press", "ee");
		// datas.put("pages", "ff");
		// datas.put("date", "gg");
		// datas.put("isbn", "hh");
		// String result = renderString(Generate.buildTemplate(), datas);
		// System.out.println(result);

		try {
			System.out.println(readXml("D:/aa.xml"));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
