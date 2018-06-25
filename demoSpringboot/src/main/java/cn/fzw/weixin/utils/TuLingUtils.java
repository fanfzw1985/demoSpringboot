package cn.fzw.weixin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.fzw.weixin.controller.WeiXinController;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TuLingUtils {
	protected static Logger logger=LoggerFactory.getLogger(WeiXinController.class); 
	public static String tuLingReturnNews(String FromUserName, String ToUserName, String MsgId, String Content){
			String content=Content;
			JSONObject outObj=new JSONObject();        
	        JSONObject perception=new JSONObject();
	        JSONObject inputText=new JSONObject();
	        inputText.put( "text", Content);
	        perception.put("inputText", inputText);
	        
	        JSONObject inputImage=new JSONObject();
	        inputImage.put( "url", "");
	       // perception.put("inputImage", inputImage);
	        
	        JSONObject selfInfo=new JSONObject();
	        JSONObject location=new JSONObject();
	        location.put( "city", "");
	        location.put( "province", "");
	        location.put( "street", "");
	        selfInfo.put( "location", location);       
	       // perception.put("selfInfo", selfInfo);
	        outObj.put("perception", perception);
	        
	        JSONObject userInfo=new JSONObject();
	        userInfo.put( "apiKey", "329501cc4a8d4555a18ba7894d73dd54");
	        userInfo.put( "userId", "13688176532");
	        outObj.put("userInfo", userInfo);
	        outObj.put("reqType", "0");
	    	String url="http://openapi.tuling123.com/openapi/api/v2";
	    	//logger.info(outObj.toString());
	    	try {
	    	JSONObject jSONObject=WeiXinUtil.doPostStr(url, outObj.toString());
	    	//logger.info(jSONObject.toString());
	    	JSONArray jSONArray=jSONObject.getJSONArray("results");    
	    	//JSONObject jSONObj=JSONObject.fromObject(jSONObject.getJSONArray("results").get(0));  //
	    	//JSONArray jSONArray = JSONArray.fromObject(jSONObject.get("results"));
	    	jSONObject.getString("results");
	    	StringBuilder sb=new StringBuilder();
	    	String resultType=null;
	    	JSONObject jsbject=null;	    	
	    	for(int i=0;i<jSONArray.size();i++) {
	    		jsbject=JSONObject.fromObject(jSONArray.get(i));
	    		resultType=jsbject.getString("resultType");
	    		jsbject=JSONObject.fromObject(jsbject.getString("values"));
	    		if(resultType.equals("text")) {
	    			sb.append(jsbject.getString("text"));
	    		}
	    		if(resultType.equals("url")) {
	    			sb.append(jsbject.getString("url"));
	    		}
	    		if(resultType.equals("voice")) {
	    			sb.append(jsbject.getString("voice"));
	    		}
	    		if(resultType.equals("video")) {
	    			sb.append(jsbject.getString("video"));
	    		}
	    		if(resultType.equals("image")) {
	    			sb.append(jsbject.getString("image"));
	    		}
	    		if(resultType.equals("news")) {
	    			sb.append(jsbject.getString("news"));
	    		}
	    	}
	    	Content=sb.toString();
	    	//System.out.println(Content+"_"+content);
	    	}catch (Exception e) {
				// TODO: handle exception
	    		e.printStackTrace();
			}
	    	if(content.equals(Content)) {
	    		Content="不好意思，文文还比较小，你说的什么我不是很明白！如需帮助请回复“1”。请重新输入。。。";
	    	}
	    	return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);
	    	
//	    	int ArticleCount1=0;
//	    	
//	    	String Title="没有图片相关标题",PicUrl=null,Description="没有图片相关描述",Articles=null,Url=null;
//	    	if(jSONArray.size()==1) {
//	    		if(JSONObject.fromObject(jSONArray.get(0)).getString("resultType").equals("text")) {
//	    			Content=JSONObject.fromObject(
//	    					JSONObject.fromObject(jSONArray.get(0)).getString("values")).getString("text");	    		   
//	    		}
//	    		 return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);
//	    	}else {
//	    	for(int i=0;i<jSONArray.size();i++) {
//	    		JSONObject jsbject=JSONObject.fromObject(jSONArray.get(i));
//	    		if(jsbject.getString("resultType").equals("text")&&i==0) {
//	    			Title=JSONObject.fromObject(
//	    					JSONObject.fromObject(jSONArray.get(i)).getString("values")).getString("text");
//	    		}else if(jsbject.getString("resultType").equals("image")) {
//	    			ArticleCount1++;
//	    			PicUrl=JSONObject.fromObject(
//	    					JSONObject.fromObject(jSONArray.get(i)).getString("values")).getString("image");
//	    				Url=PicUrl;
//	        			Articles=PicUrl;
//	    		}else {
//	    			Description=JSONObject.fromObject(
//	    					JSONObject.fromObject(jSONArray.get(i)).getString("values")).getString("text");
//	    		}   		
//	    	}
//	    	String ArticleCount=""+ArticleCount1;
//	    	return TextMessageUtil.initMessage(ToUserName, FromUserName, 
//		    		ArticleCount, Articles, Title, Description, PicUrl, Url,MsgId);
//	    	} 			
	}
}
