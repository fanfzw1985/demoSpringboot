package cn.fzw.weixin.service.impl;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.ExpressUpdateTimeMapper;
import cn.fzw.mapper.LogMapper;
import cn.fzw.mapper.TextMapper;
import cn.fzw.weixin.service.TextService;
import cn.fzw.weixin.utils.ExpressUtils;
import cn.fzw.weixin.utils.JokeUtils;
import cn.fzw.weixin.utils.TextMessageUtil;
import cn.fzw.weixin.utils.TuLingUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@Service
public class TextServiceImpl implements TextService {
	@Autowired
	private TextMapper textMapper;
	@Autowired
	private LogMapper logMapper;
	@Autowired
	private ExpressUpdateTimeMapper expressUpdateTimeMapper;

	protected static Logger logger=LoggerFactory.getLogger(TextServiceImpl.class); 	
	//@Autowired
	//private WxUserInfoMapper wxUserInfoMapper;
	//private static final String EVENT_URL = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	   
	public String selectByContent(String FromUserName, String ToUserName, String MsgId, String Content) {
		// TODO Auto-generated method stub	
		String content=Content;
		try {
			Content=textMapper.selectByContent(Content);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		if(Content==null) {
			return TuLingUtils.tuLingReturnNews(FromUserName, ToUserName, MsgId, content);
			//System.out.println(message);
		}else{
			return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);
		
		}
	}


	@Override
	public String selectUserInfoFromWx(String FromUserName, String ToUserName, String MsgId) 
			throws ClientProtocolException, Exception {
		// TODO Auto-generated method stub
//		String access_token=WeiXinUtil.getAccess_Token();
//		String url = EVENT_URL.replace("ACCESS_TOKEN", access_token);//将URL中的两个参数替换掉
//		JSONObject jsonObject =WeiXinUtil.doGetStr(url);//使用刚刚写的doGet方法接收结果
//		System.out.println(jsonObject);
//		if(jsonObject!=null){ 
//			String subscribe= jsonObject.getString("subscribe");	
//			String openid= jsonObject.getString("openid");
//			String nickname= jsonObject.getString("nickname");
//			String sex= jsonObject.getString("sex");
//			String city= jsonObject.getString("city");
//			String country= jsonObject.getString("country");
//			String province= jsonObject.getString("province");
//			String language= jsonObject.getString("language");
//			String headimgurl= jsonObject.getString("headimgurl");
//			String subscribe_time= jsonObject.getString("subscribe_time");
//			String unionid= jsonObject.getString("unionid");
//			String remark= jsonObject.getString("remark");
//			String groupid= jsonObject.getString("groupid");
//			String tagid_list= jsonObject.getString("tagid_list");
//			String subscribe_scene= jsonObject.getString("subscribe_scene");
//			String qr_scene= jsonObject.getString("qr_scene");
//			String qr_scene_str= jsonObject.getString("qr_scene_str");
//			WxUserInfo  wxUserInfo=wxUserInfoMapper.selectByOpenid(openid);
//			if(wxUserInfo==null) {
//			wxUserInfoMapper.insertInto(subscribe,openid,nickname,sex,city,country,province,language,headimgurl,
//					subscribe_time,unionid,remark,groupid,tagid_list,subscribe_scene,qr_scene,qr_scene_str);
//			}else {
//				int id=wxUserInfo.getId();
//			wxUserInfoMapper.updateById(id,subscribe,openid,nickname,sex,city,country,province,language,headimgurl,
//						subscribe_time,unionid,remark,groupid,tagid_list,subscribe_scene,qr_scene,qr_scene_str);
//			}
//        }  
		String message="用户关注/取消关注公众号！";
		String openid=FromUserName;
		logMapper.insertIntoByOpenid(openid,message);
		String Content="谢谢关注文文乐园，小蚊子随时为您服务，如需帮助请回复“1”。看.....那边来了群外星人！ps：biu--biu--biu--，小蚊子闪开了!留你自己在原地 发呆中。。。。。";
		return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);
	}


	@Override
	public String initMessage(String FromUserName, String ToUserName, String MsgId, String Content) {
		// TODO Auto-generated method stub
		return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);
	}


	@Override
	public String expressSelect(String FromUserName, String ToUserName, String MsgId, String Content) {
		// TODO Auto-generated method stub
		StringBuilder expressInfo=new StringBuilder();
		try {
			String expCode=null;
			String expName=null;
			String result=ExpressUtils.getOrderTracesByJson(Content);
			JSONArray jSONArray=JSONObject.fromObject(result).getJSONArray("Shippers");
			for(int i=0;i<jSONArray.size();i++) {
				try {
				expCode=JSONObject.fromObject(jSONArray.get(i)).getString("ShipperCode");
				expName=JSONObject.fromObject(jSONArray.get(i)).getString("ShipperName");
				result=ExpressUtils.getOrderTracesByJson(expCode, Content);
				JSONArray jSONArrayTo=JSONObject.fromObject(result).getJSONArray("Traces");
				expressInfo.append("快递单号："+Content+"\n"+"快递公司："+expName+"\n");
				String  fromusername=FromUserName;
				String  code=expName;
				String  tracking_number=Content;
				int num=expressUpdateTimeMapper.updateByCodeAndNum(fromusername,code,tracking_number);
				if(num==0){
					expressUpdateTimeMapper.insertInto(fromusername,code,tracking_number);
				}	
				for (int j=jSONArrayTo.size()-1;j<jSONArrayTo.size();j--) {
					expressInfo.append(JSONObject.fromObject(jSONArrayTo.get(j)).getString("AcceptTime")+"\n"
							+JSONObject.fromObject(jSONArrayTo.get(j)).getString("AcceptStation")+"\n\n");
				}
				}catch (Exception e) {
					// TODO Auto-generated catch block
					continue;
				}
				
			}			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		try {			
//		 	String Apikey="d0196304-ce91-43bf-91b7-04700119fd07";	
//			Map<String, String> headerparams = new HashMap<String, String>();
//			headerparams.put("Trackingmore-Api-Key", Apikey);
//			headerparams.put("Content-Type", "application/json");
//			List<String> bodyParams = new ArrayList<String>();
//			String tracking_number=Content;
//			String param="{\"tracking_number\": \""+tracking_number+"\"}";
//			bodyParams.add(param);
//			JSONObject jsonObject =null;
//			jsonObject=JSONObject.fromObject(ExpressSelect
//					.sendPost("http://api.trackingmore.com/v2/carriers/detect", headerparams, bodyParams, "POST"));
//			JSONArray jSONArray=jsonObject.getJSONArray("data");
//			String code=null;
//			String	urlStr =null;
//			String requestData=null;
//			for(int i=0;i<jSONArray.size();i++) {
//				try {
//				code=JSONObject.fromObject(jSONArray.get(i)).getString("code");
//				param="{\"tracking_number\": \""+tracking_number+"\",\"carrier_code\":\""+code+"\"}";
//				String fromusername=FromUserName;
//				int num=expressUpdateTimeMapper.updateByCodeAndNum(fromusername,code,tracking_number);
//				if(num==0){
//					ExpressSelect.orderOnlineByJson(param,urlStr,"post");//创建
//					expressUpdateTimeMapper.insertInto(fromusername,code,tracking_number);
//				}			
//				urlStr="/"+code+"/"+tracking_number;
//				String result =ExpressSelect.orderOnlineByJson(requestData,urlStr,"codeNumberGet").toLowerCase();//得到返回值
//				   //dpsomething		
//				jsonObject=JSONObject.fromObject(result);
//				List<Express> list=new ArrayList<Express>();
//					list=JSONArray.toList(JSONObject.fromObject(JSONObject.fromObject(jsonObject.getString("data"))
//							.getString("origin_info"))
//								.getJSONArray("trackinfo"),new Express(),new JsonConfig());
//					expressInfo.append("快递单号："+tracking_number+"\n"+"——快递公司："+code+"\n");
//					for (Express express : list) {
//					expressInfo.append(express.getDate()+"\n"+"-->"+express.getStatusdescription()+"\n");
//				}
//					//ExpressSelect.orderOnlineByJson(requestData,urlStr,"codeNumberDelete");
//				}
//				catch (Exception e) {
//					// TODO: handle exception
//					//ExpressSelect.orderOnlineByJson(requestData,urlStr,"codeNumberDelete");
//					continue;
//				}
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();			
//		} 
		if(expressInfo.toString()!=null) {
			Content=expressInfo.toString();
			}else  Content="快递单号不正确，请重新输入！"; 
		return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);	
	}


	@Override
	public String getJoke(String FromUserName, String ToUserName, String MsgId, String Content) {
		// TODO Auto-generated method stub
		try {
			Content=JokeUtils.getJoke();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Content=selectByContent(FromUserName,ToUserName,MsgId,"笑话");
		} 
		return TextMessageUtil.initMessage(FromUserName,ToUserName,MsgId,Content);
	}		
}
