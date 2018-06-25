package cn.fzw.controller;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fzw.service.PhotoService;
import cn.fzw.utils.photoUtils;
import cn.fzw.vo.PhotoUpLoad;
import cn.fzw.vo.Users;

@Controller
@RequestMapping("/photo")
public class PhotoController {
	protected static Logger logger=LoggerFactory.getLogger(PhotoController.class); 
	@Value("${photoWallNum}")  
	private int photoWallNum;
	@Value("${web.upload-path}")
    private String uploadPath;
	@Autowired
	private PhotoService photoService;

	@RequestMapping("/selectSidePhoto")
	public String selectSidePhoto(Model model,HttpServletRequest request,@RequestParam("flag")int flag,@RequestParam("id")int id) throws Exception{
		request.removeAttribute("msg");
		String msg=null;
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		PhotoUpLoad photoUpLoad=photoService.selectSidePhoto(id,flag,username);
		if(photoUpLoad.getId()==id&&flag==0){
			msg="已经是第一张图片了，没有更多的了！";
		}
		if(photoUpLoad.getId()==id&&flag==1){
			msg="已经是最后一张图片了，没有更多的了！";
		}
		request.setAttribute("msg", msg);
		request.setAttribute("photoUpLoad", photoUpLoad);
		return "/photoShow";
	}
	@RequestMapping("/deletePhotoById")
	public String deletePhotoById(Model model,HttpServletRequest request,@RequestParam("id")int id) throws Exception{
		request.removeAttribute("msg");
		PhotoUpLoad photoUpLoad=photoService.selectPhotoById(id);
		String photoPath=photoUpLoad.getPhotoPath();
		String photoName=photoUpLoad.getPhotoName();
		File filePhotoPath=new File(uploadPath+photoPath);
		filePhotoPath.delete();
		String photoThumpath=photoUtils.photoPath2Thum(uploadPath+photoPath);
		File filePhotoThumpath=new File(photoThumpath);
		filePhotoThumpath.delete();
		photoService.deleteById(id);
		logger.info(photoUpLoad.getUsername()+"成功删除照片："+photoName);
		return selectPhotoPathInThum(model,request);
	}	
	@RequestMapping("/showPhotoDetail")
	public String showPhotoDetail(Model model,HttpServletRequest request,@RequestParam("id")int id) throws Exception{
		request.removeAttribute("msg");
		PhotoUpLoad photoUpLoad=photoService.selectPhotoById(id);
		request.setAttribute("photoUpLoad", photoUpLoad);
		return "/photoShow";
	}
	
	@RequestMapping("/selectPhotoPath")
	@ResponseBody
	public ArrayList<PhotoUpLoad> selectPhotoPath() throws Exception{
		ArrayList<PhotoUpLoad> photoList=photoService.selectAll();
		return photoList;	
	}
	
	@RequestMapping("/selectPhotoPathInThum")
	public String selectPhotoPathInThum(Model model,HttpServletRequest request) throws Exception{
		request.removeAttribute("msg");
		int pageNum = 1;
		String _pageNum = request.getParameter("pageNum");		
		if (_pageNum != null) {
			pageNum = Integer.parseInt(_pageNum);
		}
		Users user=(Users) request.getSession().getAttribute("user");
		String username=user.getUsername();
		int num=photoService.selectAllInPhotos(username);
		int totalPage = (num%photoWallNum==0)?(num/photoWallNum):num/photoWallNum+1;
		request.setAttribute("totalPage", totalPage);
		request.setAttribute("pageNum", pageNum);	
		int pageStart=(pageNum-1)*photoWallNum;
		ArrayList<PhotoUpLoad> photoThumList=photoService.selectAllByPage(username,pageStart,photoWallNum);
		request.setAttribute("photoThumList", photoThumList);
		return "/photowall";	
	}
	
}
