package cn.fzw.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.fzw.service.UpLoadService;
import cn.fzw.vo.Users;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

@Controller
@RequestMapping("/upload")
public class UpLoadController {
	protected static Logger logger=LoggerFactory.getLogger(UpLoadController.class); 
	@Value("${web.upload-path}")
    private String uploadPath;
	@Autowired
	private UpLoadService upLoadService;

	
	
	@RequestMapping("/upLoad")
	public String  showUpLoad() {
		return "/upLoad";
	}
	
	@RequestMapping("/up")
	@ResponseBody
	public String up(@RequestParam("multiFiles")MultipartFile singleFile,HttpServletRequest request) throws IOException {
		request.removeAttribute("msg");
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		String msg=null;
		//if(superadmin2.equals(username)||superadmin1.equals(username)){
			//String savePath=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			String savePath=uploadPath+"upload"+File.separator+username+File.separator; //+++
			if(singleFile!=null&&!singleFile.isEmpty()){
				String photoRealName=singleFile.getOriginalFilename();
				String nameEnd=photoRealName.substring(photoRealName.lastIndexOf("."));					
				  if(nameEnd.equals(".png")||nameEnd.equals(".jpg")){
					  String photoName=System.currentTimeMillis()+nameEnd;
//					  int num=upLoadService.selectByPhotoName(fileName);
//					  if(num>0) {
//						  fileName+="Sec";
//					  }
					  String photoDir=savePath+File.separator+photoName;
						File targetFile=new File(photoDir);
						  if(!targetFile.getParentFile().exists()){ //判断文件父目录是否存在  
							  targetFile.getParentFile().mkdir();  
					        } 
					
							try {
								singleFile.transferTo(targetFile);
							} catch (IllegalStateException | IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
					
					Thumbnails.of(photoDir).size(1024,768).toFile(photoDir);
						File targetFile2=new File(savePath+"//thumbnails//");
						if(!targetFile2.exists()){  
							  targetFile2.mkdir();  
					        }
							try {
								Thumbnails.of(savePath+"/"+photoName).
								    //scalingMode(ScalingMode.BICUBIC).
								            scale(0.3). // 图片缩放80%, 不能和size()一起使用
								   toFiles(targetFile2, Rename.NO_CHANGE);
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}//指定的目录一定要存在,否则报错
						String photoPath="upload"+File.separator+username+File.separator+photoName; 
							try {
								upLoadService.insertByusernameAndPath(username,photoRealName,photoName,photoPath);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						logger.info(username+"上传成功"+photoRealName);
				  }
									
					return "success";
				  
				}else{
					msg="上传格式不正确或没有选择图片，请正确上传JPG/PNG的图片！";
					request.setAttribute("msg", msg);
					return "photowall";		
				}
//		}else{
//			msg="你没有权限上传图片，请等后续功能开放，谢谢！";
//			return "photowall";
//		}		
	}

}
