package com.xxm.it.piic.marshal;

import java.lang.reflect.Field;

import javax.xml.bind.Marshaller;
public class MarshallerListener extends Marshaller.Listener{
	public static final String BLANK_CHAR = ""; 
	@Override 
	public void beforeMarshal(Object source) { 
		super.beforeMarshal(source); 
		Field[] fields = source.getClass().getDeclaredFields(); 
		for (Field f : fields) { 
			f.setAccessible(true); 
			try { 
				if (f.getType() == String.class && f.get(source) == null) { 
					f.set(source, BLANK_CHAR); 
				} 
			} catch (IllegalAccessException e) { 
				e.printStackTrace(); 
			} 
		} 
	} 
}
