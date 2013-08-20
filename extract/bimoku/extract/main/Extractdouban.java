package bimoku.extract.main;

import java.io.File;
import java.util.ArrayList;

import bimoku.extract.common.PropertyUtil;
import bimoku.extract.common.exception.ExtractException;
import bimoku.extract.parser.Parser;

import com.bimoku.util.FileUtils;

/**
 * 抽取的线程
 * 
 * @author 梅良
 * @author LPM 跟新优化代码，，使用实现runnable的方法，方便使用线程池
 *
 */
public class Extractdouban implements Runnable{

	private String directory;
	private Parser parser;
	private String configPath;

	public Extractdouban(String directory,Parser parser,String configPath) {
		this.directory = directory;
		this.parser = parser;
		this.configPath  = configPath;
	}

	public void run() {
		
		while (!directory.isEmpty()) {
			
			
			ArrayList<String> htmllist = new ArrayList<String>();//所有的html文件
			File[] files = FileUtils.getFileslist(directory);//获取所有文件
			
			if (files == null || files.length < 1) {
				break;//当前目录没有文件，处理完毕了
			}
			
			for (int i = 0; i < files.length; i++) {
				if (!files[i].isDirectory()) {
					htmllist.add(files[i].getName());
				}
			}
			
			while (!htmllist.isEmpty()) {
				//从文件列表中取出一则数据，取完，结束循环
				String filepath = directory + "/" + htmllist.remove(0);
				try {
					parser.parser(filepath);//调用相关的抽取方法
				}catch(ExtractException e) {
					//抽取异常，把该文件复制到相应的目录下面
					recordError(filepath);
					//在此处对出去失败的记录做处理
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 抽取失败处理
	 * @param filePath
	 */
	private void recordError(String filePath){
		String destPath = PropertyUtil.getProperty(configPath).getProperty("exception") + File.separator + filePath.substring(filePath.lastIndexOf('/'));
		FileUtils.copyFile(filePath, destPath);
	}
}
