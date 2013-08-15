package generate;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.DefaultResourceLoader;


import util.FileUtils;
import util.FreeMarkers;


import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 代码生成器
 * 【使用fremaker的代码生成器】
 * @author LPM
 *
 */
public class Generate {
	private static Logger log = LoggerFactory.getLogger(Generate.class);
	
	private static Configuration cfg;
	private static Template template;
	private static String separator;
	private static String classPath;
	private static String templatePath;
	
	
	public Generate(){}

	/**
	 * 初始化方法
	 * 
	 * @throws IOException
	 */
	public static Template buildTemplate() throws Exception{
		if(template == null){
			separator = File.separator;
			classPath = new DefaultResourceLoader().getResource("").getFile().getPath();
			templatePath = classPath + separator + "template";
			// 获取freemarker的Configuration实例
			cfg = new Configuration();
			// 设置模板文件目录
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
			// 取得模板文件
			template = cfg.getTemplate("template.ftl");
		}
		return template;
	}
	
	
	/**
	 * 生成文件xml
	 * @param datas
	 * @param filePath
	 * @throws Exception
	 */
	public static void process(Map datas,String filePath) throws Exception{
		//render内容
		String content = FreeMarkers.renderTemplate(buildTemplate(), datas);
		//写到文件
		writeFile(content, filePath);
	}
	
	/**
	 * 将内容写入文件
	 * @param content
	 * @param filePath
	 */
	public static void writeFile(String content, String filePath) {
		try {
			if (FileUtils.createFile(filePath)){
				FileWriter fileWriter = new FileWriter(filePath, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.write(content);
				bufferedWriter.close();
				fileWriter.close();
			}else{
				log.info("生成失败，文件已存在！");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		Map datas = new HashedMap();
		datas.put("url", "aa");
		datas.put("website", "bb");
		datas.put("title", "cc");
		datas.put("title_en", "dd");
		datas.put("press", "ee");
		datas.put("pages", "ff");
		datas.put("date", "gg");
		datas.put("isbn", "hh");
		try {
			process(datas, "D:/aa.xml");
			log.info("数据装载成功");
		} catch (Exception e) {
			log.info("err!"+e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
}
