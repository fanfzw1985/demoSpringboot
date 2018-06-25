package cn.fzw.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.PhotoMapper;
import cn.fzw.service.PhotoService;
import cn.fzw.utils.photoUtils;
import cn.fzw.vo.PhotoUpLoad;
@Service
public class PhotoServiceImpl implements PhotoService {
	@Autowired
	private PhotoMapper photoMapper;
	@Value("${superadmin1}")  
	private String superadmin1;
	@Value("${superadmin2}")  
	private String superadmin2;
	
	@Override
	public ArrayList<PhotoUpLoad> selectAll() throws Exception {
		// TODO Auto-generated method stub
		ArrayList<PhotoUpLoad> photoList=photoMapper.selectAll(superadmin1,superadmin2);
		return photoUtils.photoPath2Thum(photoList);
	}

	@Override
	public int selectAllInPhotos(String username) throws Exception {
		// TODO Auto-generated method stub
		return photoMapper.selectAllInPhotos(username);
	}

	@Override
	public ArrayList<PhotoUpLoad> selectAllByPage(String username,int pageStart, int photoWallNum) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<PhotoUpLoad> photoThumList=photoMapper.selectAllByPage(username,pageStart,photoWallNum);
		return photoUtils.photoPath2Thum(photoThumList);
	}

	@Override
	public PhotoUpLoad selectPhotoById(int id) throws Exception {
		// TODO Auto-generated method stub
		return photoMapper.selectPhotoById(id);
	}

	@Override
	public void deleteById(int id) throws Exception {
		// TODO Auto-generated method stub
		photoMapper.deleteById(id);
		
	}

	@Override
	public PhotoUpLoad selectSidePhoto(int id, int flag,String username) throws Exception {
		// TODO Auto-generated method stub
		if(flag==0) {
		return photoMapper.selectLastSidePhoto(id,username);
		}else {
		return photoMapper.selectNextSidePhoto(id,username);
		}
	}
}
