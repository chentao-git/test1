package com.xxm.it.piic.marshal;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JaxbReadXml {
	    @SuppressWarnings("rawtypes")
		public static Object convertXmlStrToObject(Class clazz, String xmlStr) {  
	        Object xmlObject = null;  
	        try {  
	            JAXBContext context = JAXBContext.newInstance(clazz);  
	            // 进行将Xml转成对象的核心接口  
	            Unmarshaller unmarshaller = context.createUnmarshaller();  
	            StringReader sr = new StringReader(xmlStr);  
	            xmlObject = unmarshaller.unmarshal(sr);  
	        } catch (JAXBException e) {  
	            e.printStackTrace();  
	        }  
	        return xmlObject;  
	    }  
	    @SuppressWarnings("unchecked")
		public static <T> T xml2Object(String xmlStr,Class<T> c)
	    { 
	        try
	        { 
	            JAXBContext context = JAXBContext.newInstance(c); 
	            Unmarshaller unmarshaller = context.createUnmarshaller(); 
	             
	            T t = (T) unmarshaller.unmarshal(new StringReader(xmlStr)); 
	             
	            return t; 
	             
	        } catch (JAXBException e) {  e.printStackTrace();  return null; } 
	         
	    } 
//	    public static Object convertXmlFileToObject(Class clazz, String xmlPath) {  
//	        Object xmlObject = null;  
//	        try {  
//	            JAXBContext context = JAXBContext.newInstance(clazz);  
//	            Unmarshaller unmarshaller = context.createUnmarshaller();  
//	            FileReader fr = null;  
//	            try {  
//	                fr = new FileReader(xmlPath);  
//	            } catch (FileNotFoundException e) {  
//	                e.printStackTrace();  
//	            }  
//	            xmlObject = unmarshaller.unmarshal(fr);  
//	        } catch (JAXBException e) {  
//	            e.printStackTrace();  
//	        }  
//	        return xmlObject;  
//	    } 
//	    private static <T> List<T> unmarshal(javax.xml.bind.Unmarshaller unmarshaller,
//
//	            Class<T> clazz, String xmlLocation) throws JAXBException {
//
//	        StreamSource xml = new StreamSource(xmlLocation);
//
//	        Wrapper<T> wrapper = (Wrapper<T>) unmarshaller.unmarshal(xml,
//
//	                Wrapper.class).getValue();
//
//	        return wrapper.getItems();
//
//	    }
}
