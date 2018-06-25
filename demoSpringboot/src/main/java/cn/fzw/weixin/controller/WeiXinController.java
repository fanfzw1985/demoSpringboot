package cn.fzw.weixin.controller;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.fzw.weixin.service.TextService;
import cn.fzw.weixin.utils.CheckWXCodeUtil;
import cn.fzw.weixin.utils.MessageUtil;
import cn.fzw.weixin.utils.TrainTicketUtil;
import cn.fzw.weixin.utils.TrandsApi;
import cn.fzw.weixin.utils.WeatherUtil;

@Controller
@RequestMapping("/weixin")
public class WeiXinController {
	protected static Logger logger=LoggerFactory.getLogger(WeiXinController.class); 	

	@Autowired
	private TextService textService;
	@RequestMapping("/WeiXinTestConnection")
	public void WeiXinTestConnection(HttpServletRequest request,HttpServletResponse response) throws Exception {
	String signature = request.getParameter("signature");//微信加密签名（token、timestamp、nonce。）
    String timestamp = request.getParameter("timestamp");
    String nonce = request.getParameter("nonce"); // 随机数 
   // String echostr = request.getParameter("echostr");// 随机字符串  
  //  PrintWriter out = response.getWriter();
    if(CheckWXCodeUtil.checkSignature(signature, timestamp, nonce)){
        //out.print(echostr);  	
    	WxReturnText(request,response);
    }
 }
	public void  WxReturnText(HttpServletRequest request,HttpServletResponse response) throws ClientProtocolException, Exception{
		response.setCharacterEncoding("utf-8");
		PrintWriter out=null;
		Map<String, String> map=MessageUtil.xmlToMap(request);
		String ToUserName=map.get("ToUserName");
		String FromUserName=map.get("FromUserName");
		String MsgType=map.get("MsgType");
		String Content=map.get("Content");
		String MsgId=map.get("MsgId");
		String message=null;
		if("text".equals(MsgType)) {
			if("1".equals(Content)) {
				Content="一、如果想查询快递发送情况或想跟小蚊子说说话，请直接回复快递单号或聊天内容；"+"\n"
						+" 二、如果想要翻译，请用“ / ”+需要翻译的内容，如想翻译:“ 文文乐园 ”，请回复:“ /文文乐园 ”。如果没能翻译请检查斜杠格式是否英文状态；"
						+"\n"+" 三、如果想要查询火车票情况，请用“！”+出发站+空+目的站+空+时间的格式，其中不输入时间表示查询当日车次，时间格式如为20180607,如需查询2018年6月7日祁东至广州的火车票，可以发送“！祁东  广州    20180607”"
						+"\n"+" 四、想发钱请直接打卡就是，不用问，我不会嫌少的；"
						+"\n"+" 五、网页记账服务、上传图片服务请登陆:http://47.98.162.28:80/login.jsp；"+"\n"
						+" 六、查询本地天气直接发送位置即可，如果想要发呆，请继续。。。";
                message = textService.initMessage(FromUserName, ToUserName,MsgId,Content);  
			}else if(Content.startsWith("/")) {
				 //将翻译开头置为""  
                String query = Content.replaceAll("/", "").trim();  
                if(query==""){  
                    //如果查询字段为空，调出使用指南  
                	Content="一、如果想查询快递发送情况或想跟小蚊子说说话，请直接回复快递单号或聊天内容；"+"\n"
    						+" 二、如果想要翻译，请用“ / ”+需要翻译的内容，如想翻译:“ 文文乐园 ”，请回复:“ /文文乐园 ”。如果没能翻译请检查斜杠格式是否英文状态；"
    						+"\n"+" 三、如果想要查询火车票情况，请用“！”+出发站+空+目的站+空+时间的格式，其中不输入时间表示查询当日车次，时间格式如为20180607,如需查询2018年6月7日祁东至广州的火车票，可以发送“！祁东  广州    20180607”"
    						+"\n"+" 四、想发钱请直接打卡就是，不用问，我不会嫌少的；"
    						+"\n"+" 五、网页记账服务、上传图片服务请登陆:http://47.98.162.28:80/login.jsp；"+"\n"
    						+" 六、查询本地天气直接发送位置即可，如果想要发呆，请继续。。。";
                	message = textService.initMessage(FromUserName, ToUserName,MsgId,Content);  
                    }else{  
                    		Content=TrandsApi.getTransResult(query);
                            message = textService.initMessage(FromUserName, ToUserName,MsgId,Content);  
                        }  
			}else if(Content!= null && Content.matches("[A-Za-z0-9]+")){
				if("1".equals(Content)) {
					Content="一、如果想查询快递发送情况或想跟小蚊子说说话，请直接回复快递单号或聊天内容；"+"\n"
							+" 二、如果想要翻译，请用“ / ”+需要翻译的内容，如想翻译:“ 文文乐园 ”，请回复:“ /文文乐园 ”。如果没能翻译请检查斜杠格式是否英文状态；"
							+"\n"+" 三、如果想要查询火车票情况，请用“！”+出发站+空+目的站+空+时间的格式，其中不输入时间表示查询当日车次，时间格式如为20180607,如需查询2018年6月7日祁东至广州的火车票，可以发送“！祁东  广州    20180607”"
							+"\n"+" 四、想发钱请直接打卡就是，不用问，我不会嫌少的；"
							+"\n"+" 五、网页记账服务、上传图片服务请登陆:http://47.98.162.28:80/login.jsp；"+"\n"
							+" 六、查询本地天气直接发送位置即可，如果想要发呆，请继续。。。"; 
					message = textService.initMessage(FromUserName, ToUserName,MsgId,Content);  
				}else {
					message = textService.expressSelect(FromUserName, ToUserName,MsgId,Content); 			
				}
				
			}else if(Content.contains("笑话")){
				message = textService.getJoke(FromUserName, ToUserName,MsgId,Content); 
			}else if(Content.startsWith("！")){
				 //将翻译开头置为""  
               String query = Content.replaceAll("！", "").trim();  
               if(query==""){  
                   //如果查询字段为空，调出使用指南  
            	   Content="一、如果想查询快递发送情况或想跟小蚊子说说话，请直接回复快递单号或聊天内容；"+"\n"
   						+" 二、如果想要翻译，请用“ / ”+需要翻译的内容，如想翻译:“ 文文乐园 ”，请回复:“ /文文乐园 ”。如果没能翻译请检查斜杠格式是否英文状态；"
   						+"\n"+" 三、如果想要查询火车票情况，请用“！”+出发站+空+目的站+空+时间的格式，其中不输入时间表示查询当日车次，时间格式如为20180607,如需查询2018年6月7日祁东至广州的火车票，可以发送“！祁东  广州    20180607”"
   						+"\n"+" 四、想发钱请直接打卡就是，不用问，我不会嫌少的；"
   						+"\n"+" 五、网页记账服务、上传图片服务请登陆:http://47.98.162.28:80/login.jsp；"+"\n"
   						+" 六、查询本地天气直接发送位置即可，如果想要发呆，请继续。。。";
               	message = textService.initMessage(FromUserName, ToUserName,MsgId,Content);  
                   }else{ 
                	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
               			Long longTime=System.currentTimeMillis();
               			String time=sdf.format(longTime);	
                	   String[] splitContent = StringUtils.split(query);
                	   if(splitContent.length==3) {
                		   StringBuilder sb=new StringBuilder(splitContent[2]);
                    	   sb.insert(4, "-").insert(7, "-");  
                		   String time2=sb.toString();
                		   long longTime2=sdf.parse(time2).getTime();
                		   if(longTime2>longTime) {
                			   time=time2;
                		   }
                	   }
                	   try {
						Content=TrainTicketUtil.findTicket(splitContent[0], splitContent[1],time); 
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
						Content="格式输入不正确，请重新输入，谢谢！";
					}						                 		
                        message = textService.initMessage(FromUserName, ToUserName,MsgId,Content);  
                       }  
			
			}else{
				try {
					message=textService.selectByContent(FromUserName,ToUserName,MsgId,Content);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
			}
		}else if("event".equals(MsgType)) {
			message=textService.selectUserInfoFromWx(FromUserName,ToUserName,MsgId);
		}else if("voice".equals(MsgType)) {
			Content=map.get("Recognition");
			if(Content.contains("笑话")) {
				message = textService.getJoke(FromUserName, ToUserName,MsgId,Content);
			}else {
			message=textService.selectByContent(FromUserName,ToUserName,MsgId,Content);
			}
		}else if("location".equals(MsgType)) {
			String location=map.get("Location_X")+":"+map.get("Location_Y");
			String Label=map.get("Label");
			Content=WeatherUtil.selectWeather(Label,location);
			message=textService.initMessage(FromUserName,ToUserName,MsgId,Content);
		}
		try {
			out=response.getWriter();
			out.write(message);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			out.close();
		}
	}
}
