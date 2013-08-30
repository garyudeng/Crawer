package com.bimoku.util.xml;

import java.io.ByteArrayInputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.txw2.Document;

public class ParseXML {
	
  public static String ParsingXML(String in) throws Exception{
	  //校验
	  if(in == null || "".equals(in))
		  return "";
	  //获取顶级element
	  DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();  
      DocumentBuilder builder = factory.newDocumentBuilder();  
      org.w3c.dom.Document document = builder.parse(new ByteArrayInputStream(in.getBytes()));
      
	  return "";
  }
  
  
}
