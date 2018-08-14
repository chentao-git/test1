package com.xxm.it.piic.marshal;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.xxm.it.system.util.XxmUtils;


public class JaxbWriteXml {
	//java bean 转 xml
	public static <T> Marshaller getMarshaller(Class<T> c) throws JAXBException{  
        JAXBContext context = JAXBContext.newInstance(c);  
        Marshaller marshaller = context.createMarshaller();   
        
        //编码格式   
        marshaller.setProperty(Marshaller.JAXB_ENCODING,XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "Charset"));  
        //是否格式化生成的xml串      
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
        //设置监听 将空的对象属性也转换为xml节点
        MarshallerListener marlistener = new MarshallerListener();
        marshaller.setListener(marlistener); 
        //是否省略xml头信息（<?xml version="1.0" encoding="gb2312" standalone="yes"?>）  
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, false);  
        marshaller.setProperty("jaxb.encoding", XxmUtils.getBaseDataExtendField1("SERVICE_INTERFACE_CONFIG", "Charset")); 
        return marshaller;  
    }  
	
//  public static void main(String[] args) {
//	  try {
//		  Marshaller marshaller = JaxbWriteXml.getMarshaller(People.class);
//		  People people = new People("001","灰太狼",22);    
//          marshaller.marshal(people, System.out);  
//	  } catch (JAXBException e) {
//		// TODO Auto-generated catch block
//		e.printStackTrace();
//	}
//}
}
