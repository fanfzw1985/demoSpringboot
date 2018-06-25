package cn.fzw.weixin.utils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class WeatherUtil {
	public static String selectWeather(String Label,String location) {
		final String KEY="pa2zxszmnpf4yanv";
		//final String ID="U4229171F0";
		final String LOCATION=location;
		final String URL = "https://api.seniverse.com/v3/weather/daily.json?key=KEY&location=LOCATION&language=zh-Hans&unit=c&start=0&days=3";  //最近三日天气
		//final String URL = "https://api.seniverse.com/v3/weather/now.json?key=KEY&location=LOCATION&language=zh-Hans&unit=c";  //现在天气情况
		//final String URL = "https://api.seniverse.com/v3/life/suggestion.json?key=KEY&location=LOCATION&language=zh-Hans"; //生活指数
		//final String URL = "https://api.seniverse.com/v3/location/search.json?key=pa2zxszmnpf4yanv&q=beijing"; //搜索城市
		String url = URL.replace("KEY", KEY).replace("LOCATION", LOCATION);//将URL中的参数替换掉		
		JSONObject jSONObject=null;
		try {
			jSONObject = WeiXinUtil.doGetStr(url);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		JSONObject jSONObj=JSONObject.fromObject(jSONObject.getJSONArray("results").get(0));
		StringBuilder sb=new StringBuilder();		
		if(Label==null||"".equals(Label)) {
			Label=JSONObject.fromObject(jSONObj.getString("location")).getString("path");
		}
		sb.append(Label+"\n");
		JSONArray jSONArray = JSONArray.fromObject
				(jSONObj.getJSONArray("daily"));
//						.getString("location"))
//						.get("daily"));
		for(int i=0;i<jSONArray.size();i++) {
			jSONObj=JSONObject.fromObject(jSONArray.get(i));
			sb.append(jSONObj.getString("date"));
			sb.append("\n白天:");
			sb.append(jSONObj.getString("text_day")+"\t    ");
			sb.append("晚上:");
			sb.append(jSONObj.getString("text_night")+"\n");
			sb.append("气温:"+jSONObj.getString("low")+"度~"+jSONObj.getString("high")+"度\n\n");			
		}
		return sb.toString();
	}
}
