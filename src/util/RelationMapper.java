package util;

import java.io.Serializable;
import java.lang.reflect.Field;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import bean.BookAmazon;
import bean.BookDD;
import bean.BookDetail;

@SuppressWarnings("serial")
public class RelationMapper implements Serializable{
	
	public static String DD_NET = "DD";
	public static String DB_NET = "DB";
	public static String AM_NET = "AM";
	
	
	private String DD;
	private String DB;
	private String AM;
	
	
	
	public String getDD() {
		return DD;
	}

	public void setDD(String dD) {
		DD = dD;
	}

	public String getDB() {
		return DB;
	}

	public void setDB(String dB) {
		DB = dB;
	}

	public String getAM() {
		return AM;
	}

	public void setAM(String aM) {
		AM = aM;
	}

	public static String bean2Json(RelationMapper mapper) throws JSONException{
		JSONArray ja = new JSONArray();
		JSONObject jo = new JSONObject(); 
		jo.put(DD_NET, mapper.getDD()==null?"":mapper.getDD());
		jo.put(DB_NET, mapper.getDB()==null?"":mapper.getDB());
		jo.put(AM_NET, mapper.getAM()==null?"":mapper.getAM());
		ja.put(jo);
		
		ja.put(new JSONObject());
		
		System.out.println(jo.toString());
		return jo.toString();
	}
	
	public static RelationMapper str2Bean(String str) throws JSONException{
		
		JSONArray ja = new JSONArray(str);
		JSONObject jo = ja.getJSONObject(0);//new JSONObject(str); 
		
		RelationMapper mapper = new RelationMapper();
		
//		System.out.println(jo.get(AM_NET));
		
		mapper.setAM(jo.get(AM_NET)==null?"":jo.get(AM_NET).toString());
		mapper.setDD(jo.get(DD_NET)==null?"":jo.get(DD_NET).toString());
		mapper.setDB(jo.get(DB_NET)==null?"":jo.get(DB_NET).toString());
		
//		System.out.println(mapper.toString());
		
		return mapper;
	}
	
	
	public static String builtWithClass(BookDetail detail) throws JSONException{
		RelationMapper mapper = new RelationMapper();
		
		if(detail instanceof BookDD){
			mapper.setDD(detail.getIsbn());
		}else if(detail instanceof BookAmazon){
			mapper.setAM(detail.getIsbn());
		}
		
		return bean2Json(mapper);
	}

//	@Override
//	public String toString() {
//		return "{DD=" + DD + ", DB=" + DB + ", AM=" + AM + "}";
//	}
	
}
