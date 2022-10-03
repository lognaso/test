package kr.co.cwit.common.util;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@SuppressWarnings("rawtypes")
public class MyBatisEmptyCheckUtil	{
	public static boolean isEmpty(Object obj){
        if( obj instanceof String ) return "".equals(obj.toString().trim());
        else if( obj instanceof List ) return ((List)obj).isEmpty();
        else if( obj instanceof Map ) return ((Map)obj).isEmpty();
        else if( obj instanceof JSONObject ) return ((JSONObject)obj).isEmpty();
        else if( obj instanceof JSONArray ) return ((JSONArray)obj).isEmpty();
        else if( obj instanceof Object[] ) return Array.getLength(obj)==0;
        else return obj==null;
    }
}