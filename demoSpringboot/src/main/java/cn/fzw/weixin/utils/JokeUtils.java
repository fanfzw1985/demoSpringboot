package cn.fzw.weixin.utils;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JokeUtils {
	public static String getJoke() throws ClientProtocolException, IOException{
     	String URL ="https://www.apiopen.top/satinApi?type=2&page=0";
     	JSONObject result = null;
	        result = WeiXinUtil.doGetStr(URL);
         JSONObject json = JSONObject.fromObject(result);
         StringBuilder sb=new StringBuilder();       
             JSONArray list = json.optJSONArray("data");
             int j=0;
             String text=null;
             for(int i=0;i<list.size();i++){ 
             JSONObject obj=(JSONObject)list.opt(i);
             if(obj.getInt("type")==29) {
            	text="-->"+obj.getString("text")+"\n\n";
            	j=j+text.length(); 
            	if(j>675) {
            		break;
            	}
             sb.append(text);
             }
         }
        return sb.toString();
	}
}
