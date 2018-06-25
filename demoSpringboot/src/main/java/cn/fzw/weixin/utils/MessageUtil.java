package cn.fzw.weixin.utils;
/**
 * 消息处理工具
 * @author 小樊
 *
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class MessageUtil {
	public static Map<String, String> xmlToMap(HttpServletRequest request){
		Map<String, String> map=new HashMap<String,String>();
		SAXReader reader=new SAXReader();
		InputStream in=null;
		try {
			in=request.getInputStream();
			Document doc=reader.read(in);
			Element root=doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> list=root.elements();
			for (Element element : list) {
				map.put(element.getName(), element.getText());
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally {
			try {
				in.close();
			}catch (IOException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return map;
		
	}
}
