package cn.fzw.weixin.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import cn.fzw.weixin.vo.AccessToken;
import net.sf.json.JSONObject;
import redis.clients.jedis.Jedis;

public class WeiXinUtil {
	private static final String APPID="wx366c45dc693dbcd3";
	private static final String APPSECRET="8c4f1de9c559a79b70e85d560964554d";
  private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
   /**
     * 编写Get请求的方法。但没有参数传递的时候，可以使用Get请求
     * 
     * @param url 需要请求的URL
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doGetStr(String url) throws ClientProtocolException, IOException {
    	CloseableHttpClient client = HttpClients.createDefault(); //获取DefaultHttpClient请求
        HttpGet httpGet = new HttpGet(url);//HttpGet将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        HttpResponse response = client.execute(httpGet);//使用HttpResponse接收client执行httpGet的结果
        HttpEntity entity = response.getEntity();//从response中获取结果，类型为HttpEntity
        if(entity != null){
            String result = EntityUtils.toString(entity,"UTF-8");//HttpEntity转为字符串类型
            jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        }
        return jsonObject;
    }
    
    /**
     * 编写Post请求的方法。当我们需要参数传递的时候，可以使用Post请求
     * 
     * @param url 需要请求的URL
     * @param outStr  需要传递的参数
     * @return 将请求URL后返回的数据，转为JSON格式，并return
     */
    public static JSONObject doPostStr(String url,String outStr) throws ClientProtocolException, IOException {
    	CloseableHttpClient client = HttpClients.createDefault(); //获取DefaultHttpClient请求
        HttpPost httpost = new HttpPost(url);//HttpPost将使用Get方式发送请求URL
        JSONObject jsonObject = null;
        httpost.setEntity(new StringEntity(outStr,"UTF-8"));//使用setEntity方法，将我们传进来的参数放入请求中
        HttpResponse response = client.execute(httpost);//使用HttpResponse接收client执行httpost的结果
        String result = EntityUtils.toString(response.getEntity(),"UTF-8");//HttpEntity转为字符串类型
        jsonObject = JSONObject.fromObject(result);//字符串类型转为JSON类型
        return jsonObject;
    }
    
    /**
     * 获取AccessToken
     * @return 返回拿到的access_token及有效期
     */
    public static AccessToken getAccessToken(){
    	Jedis jedis  = RedisUtil.getJedis(); 
        AccessToken token = new AccessToken();
        String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);//将URL中的两个参数替换掉
        JSONObject jsonObject;
		try {
			jsonObject = doGetStr(url);
			if(jsonObject!=null){  
	            token.setAcces_token(jsonObject.getString("access_token"));  
	            token.setExpires_in(jsonObject.getInt("expires_in"));            
	            jedis.set("access_token", jsonObject.getString("access_token"));  
	            jedis.expire("access_token", 60*60*2);  
	        } 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			RedisUtil.returnBrokenResource(jedis);
			e.printStackTrace();
		} //使用刚刚写的doGet方法接收结果
		finally {
			RedisUtil.returnResource(jedis);
		}
         
        RedisUtil.returnResource(jedis);  
        return token;
    }

    /** 
     * 获取凭证 
     * @return 
     * @throws IOException 
     * @throws ClientProtocolException 
     */  
    public static String  getAccess_Token() throws ClientProtocolException, IOException{
    	Jedis jedis= RedisUtil.getJedis();  
       String access_token = jedis.get("access_token");  
        if(access_token==null){  
            AccessToken token = getAccessToken();  
            access_token = token.getAcces_token();  
        }  
        RedisUtil.returnResource(jedis);  
        return access_token;  
    } 
}
