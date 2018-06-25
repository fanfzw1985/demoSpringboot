package cn.fzw.utils;

import java.io.File;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import cn.fzw.vo.PhotoUpLoad;

public class photoUtils {
	public static ArrayList<PhotoUpLoad> photoPath2Thum(ArrayList<PhotoUpLoad> photoThumList){
		for (PhotoUpLoad photoUpLoad : photoThumList) {
			String photoPath=photoUpLoad.getPhotoPath();
			String photoPathFirst=StringUtils.substringBeforeLast(photoPath,File.separator);
			String photoPathLast=StringUtils.substringAfterLast(photoPath,File.separator);
			photoPath=photoPathFirst+File.separator+"thumbnails"+File.separator+photoPathLast;
			photoUpLoad.setPhotoPath(photoPath);
		}		
		return photoThumList;		
	}
	public static String photoPath2Thum(String photoPath) {
		String photoPathFirst=StringUtils.substringBeforeLast(photoPath,File.separator);
		String photoPathLast=StringUtils.substringAfterLast(photoPath,File.separator);
		photoPath=photoPathFirst+File.separator+"thumbnails"+File.separator+photoPathLast;
		return photoPath;
	}
}
