package cn.fzw.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CodeUtil {
	private static int width=100;//图片宽度
	private static int height=30;//图片高度
	private static int codeCount=4;//验证码位数
	 private static int fontHeight = 20;
	  private static int xx = 18; //code位置横坐标
	  private static  int codeY = 20; //code位置纵坐标
	private static char[] codeSequence={ 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
			'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
	private static Map<String,Object> generateCodeAndPic(){
		BufferedImage buffImg=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		Graphics gd=buffImg.getGraphics();
		// 创建一个随机数生成器类
		Random random=new Random();
		 // 将图像填充为白色
		gd.setColor(Color.WHITE);
		gd.fillRect(0, 0, width, height);
		// 创建字体，字体的大小应该根据图片的高度来定。
		Font font=new Font("Fixedsys", Font.BOLD, fontHeight);
		gd.setFont(font);
		//画边框
		gd.setColor(Color.YELLOW);
		gd.drawRect(0, 0, width-1, height-1);
		// 随机产生4条干扰线，使图象中的认证码不易被其它程序探测到
		gd.setColor(Color.RED);
		 for (int i = 0; i < 4; i++) {
			 int x = random.nextInt(30);
	         int y = random.nextInt(height);
		     int xl = random.nextInt(100-x);
		     int yl = random.nextInt(12);
	          gd.drawLine(x, y, x + xl, y + yl); }
		// randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
		StringBuffer randomCode=new   StringBuffer();
		int red=0,green=0,blue=0;
		 // 随机产生codeCount数字的验证码。
		for(int i=0;i<codeCount;i++){
			String code=String.valueOf(codeSequence[random.nextInt(36)]);
			// 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
			red=random.nextInt(255);
			green = random.nextInt(255);
			blue = random.nextInt(255);
			// 用随机产生的颜色将验证码绘制到图像中。
			gd.setColor(new Color(red,green,blue));
			gd.drawString(code, (i+1)*xx, codeY);
			randomCode.append(code);}
			Map<String,Object> map=new HashMap<String,Object>();
			  //存放验证码
			map.put("code", randomCode);
			//存放生成的验证码BufferedImage对象
			map.put("codePic", buffImg);
			
			return map;
		}
		public void getCode(HttpServletRequest request,HttpServletResponse response)throws Exception{
			 // 调用工具类生成的验证码和验证码图片(里面包含验证码：code；图片：codePic)
	        Map<String, Object> codeMap = generateCodeAndPic();
	        request.getSession().setAttribute("code", codeMap.get("code").toString());
	        response.setHeader("Pragma", "no-cache");
	        response.setHeader("Cache-Control", "no-cache");
	        response.setDateHeader("Expires", -1);
	        response.setContentType("image/jpeg");
	        ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpg", response.getOutputStream());
			response.getOutputStream().close();
		}
	
	}

