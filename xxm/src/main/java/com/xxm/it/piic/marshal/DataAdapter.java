package com.xxm.it.piic.marshal;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class DataAdapter extends XmlAdapter<Object, Object>{

	 //java→xml时的处理，ob是Java生成xml时传入的属性值，ob可以数组、集合等其它复杂类型，  
    //在这个方法中可以对其进行处理，return返回需要的结果类型  
    @Override  
    public Object marshal(Object ob) throws Exception {  
          
        if(ob == null || ob.equals("")){  
            ob=" ";  
        }  
        return ob;  
    }  
  
    //xml→java时的处理  
    @Override  
    public Object unmarshal(Object arg0) throws Exception {  
        // TODO Auto-generated method stub  
        return arg0;  
    }
}
