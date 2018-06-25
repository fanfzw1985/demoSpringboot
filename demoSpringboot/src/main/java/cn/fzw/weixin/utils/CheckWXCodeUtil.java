package cn.fzw.weixin.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.apache.commons.codec.binary.Hex;


public class CheckWXCodeUtil {
	private static final String TOKEN="forlovehome";
	public static boolean checkSignature(String signature,String timestamp,String nonce) throws NoSuchAlgorithmException {
		String[] params = new String[] { TOKEN, timestamp, nonce };  
        Arrays.sort(params);  
        // 将三个参数字符串拼接成一个字符串进行sha1加密  
        String clearText = params[0] + params[1] + params[2];  
        String algorithm = "SHA-1";  
        String sign = new String(Hex.encodeHex(MessageDigest.getInstance(algorithm).digest((clearText).getBytes()), true));  
        return signature.equals(sign);
	}
	
	
	public static boolean checkSignature2(String signature,String timestamp,String nonce) {
		String[] arr=new String[] {TOKEN,timestamp,nonce};
		Arrays.sort(arr);//排序
		//生成字符串
		StringBuffer content=new StringBuffer();
		for (String string : arr) {
			content.append(string);
		}
		//sha-1加密
		String temp=getSha1Code(content.toString());
		System.out.println(temp);
		return temp.equals(signature);		
	}
	private static String getSha1Code(String string) {
		if(string==null||string.length()==0) {
			return null;
		}
		char hexDigits[]= {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
		MessageDigest mdTemp;
		
		try {
			mdTemp = MessageDigest.getInstance("SHA1");
			mdTemp.update(string.getBytes("UTF-8"));
			byte[] md=mdTemp.digest();
			int j=md.length;
			char[] buff=new char[j*2];
			int k=0;
			for(int i=0;i<j;i++) {
				byte byte0=md[i];
				buff[k++]=hexDigits[(byte0 >>> 4) & 0x0f];
				buff[k++]=hexDigits[byte0 & 0x0f];

			}
			return new String(buff);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return null;
		}
	}
	
  
			 public static boolean checkSignature3(String signature,String timestamp,String nonce){
			  //1.将token,timestamp,nonce三个参数进行排序
			  String[] str = new String[]{TOKEN,timestamp,nonce};
			  Arrays.sort(str);
			   
			  //2.将三个参数字符串拼接成一个字符串
			  StringBuilder buff = new StringBuilder();
			  for(int i=0;i<buff.length();i++){
			   buff.append(str[i]);
			  }
			   
			  //3.进行sha1加密
			  MessageDigest md = null;
			  String result = "";
			  try {
			   md = MessageDigest.getInstance("SHA-1");
			   byte[] data = md.digest(buff.toString().getBytes());
			    
			   //将字节数组转换成字符串
			   result = bytesToString(data);
			    
			  } catch (NoSuchAlgorithmException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
			  }
			   
			  return result!=null?(result.equals(signature.toUpperCase())):false;
			 }
			  
			 /**
			  * 将字节数组转换成十六进制字符串
			  * @param byteArray
			  * @return
			  */
			 private static String bytesToString(byte[] byteArray){
			  String stringDigest = "";
			  for(int i=0;i<byteArray.length;i++){
			   stringDigest += byteToHexString(byteArray[i]);
			  }
			  return stringDigest;
			 }
			  
			 /**
			  * 将一个字节转换为十六进制字符串
			  * @param mByte
			  * @return
			  */
			 private static String byteToHexString(byte mByte){
			  char[] digit = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
			  char[] temp = new char[2];
			   
			  temp[0] = digit[(mByte>>>4) & 0X0F];
			  temp[1] = digit[mByte & 0X0F];
			   
			  String str = new String(temp);
			  return str;
			 }
}
