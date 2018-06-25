package cn.fzw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.PhotoMapper;
import cn.fzw.service.UpLoadService;


@Service
public class UpLoadServiceImpl implements UpLoadService {
	@Autowired
	private PhotoMapper photoMapper;
	
	public int insertByusernameAndPath(String username, String photoRealName,String fileName,
			String photoPath) throws Exception {
		// TODO Auto-generated method stub
		String photoName=fileName;
		return photoMapper.insertByusernameAndPath(username,photoRealName,photoName,photoPath);
	}
	@Override
	public int selectByPhotoName(String photoName) throws Exception {
		// TODO Auto-generated method stub
		return photoMapper.selectByPhotoName(photoName);
	}


}
