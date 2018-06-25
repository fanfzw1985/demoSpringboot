/**
 * 
 */
package cn.fzw.weixin.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

/*** <p>Title:TrainTicketCotroller </p>
* <p>Description: </p>
* <p>Company: </p> 
* @author 樊志文
* @date 2018年6月6日*/
public class TrainTicketUtil {
	public static Map<String, String> placeMap=null;
	static {
		placeMap=new HashMap<String, String>();
		String url="https://kyfw.12306.cn/otn/resources/js/framework/station_name.js";
		CloseableHttpClient client = HttpClients.createDefault(); //获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        HttpResponse response = null;
		try {
			response = client.execute(httpGet);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        String result=null;
        if(entity != null){
           try {
			result = EntityUtils.toString(entity,"UTF-8");
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}//HttpEntity转为字符串类型
        } 
       String[] split = StringUtils.split(result, "|"); 
       for (int i=0;i<split.length-1;i=i+5) {
    	   placeMap.put(split[i+2], split[i+1]);//key为代码，value为地名 
    	   placeMap.put(split[i+1], split[i+2]);//key为代码，value为地名 
       }		
	}
	public static String findTicket(String startStation,String endStation,String time) throws Exception, IOException {		
		String url="https://kyfw.12306.cn/otn/leftTicket/query?leftTicketDTO.train_date=DATE&leftTicketDTO.from_station=STARTSTATION&leftTicketDTO.to_station=ENDSTATION&purpose_codes=ADULT";
		String pstartStation=placeMap.get(startStation);
		String pendStation=placeMap.get(endStation);
		url = url.replace("DATE", time).replace("STARTSTATION",pstartStation)
		  .replace("ENDSTATION", pendStation);
		CloseableHttpClient client = HttpClients.createDefault(); //获取DeaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        String result=null;
        JSONObject jsonObject = null;
        if(entity != null){
           result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
           jsonObject = JSONObject.fromObject(result);
        }
       String[] splitDate = JSONObject.fromObject(jsonObject.getString("data")).getString("result").split(",");
       StringBuilder expressInfo=new StringBuilder();
       expressInfo.append(startStation+"——"+endStation+"("+time+")\n");
       for (String string : splitDate) {
    	   String[] splitString = StringUtils.splitByWholeSeparatorPreserveAllTokens(string, "|");
    	   if(splitString.length>31) {
    		   String trainNumber=splitString[3];
    	   if(trainNumber.startsWith("C")||trainNumber.startsWith("D")||trainNumber.startsWith("G")) {
    		   String noSite=splitString[26];
    		   if(noSite==null||noSite=="") {
    			   noSite="无";
    		   }
        	   expressInfo.append(trainNumber+":"+placeMap.get(splitString[4])+"-"+placeMap.get(splitString[5])+"("+splitString[8]+"-"+splitString[9]+")\n"+"一等座："+splitString[31]+"\t二等座："+splitString[30]+"\t无座："+noSite+"\n\n");  
    	   }else {
    	   expressInfo.append(trainNumber+":"+placeMap.get(splitString[4])+"-"+placeMap.get(splitString[5])+"("+splitString[8]+"-"+splitString[9]+")\n"+"硬卧："+splitString[28]+"\t硬座："+splitString[29]+"\t无座："+splitString[26]+"\n\n");
    	   	}
    	   }    	   
    	   } 
       return expressInfo.toString();
}
}