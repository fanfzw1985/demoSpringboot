package cn.fzw.service;

import java.util.ArrayList;

import cn.fzw.vo.PhotoUpLoad;

public interface PhotoService {
	public ArrayList<PhotoUpLoad> selectAll() throws Exception;

	public int selectAllInPhotos(String username)throws Exception;

	public ArrayList<PhotoUpLoad> selectAllByPage(String username,int pageStart, int photoWallNum) throws Exception;

	public PhotoUpLoad selectPhotoById(int id)throws Exception;

	public void deleteById(int id)throws Exception;

	public PhotoUpLoad selectSidePhoto(int id, int flag,String username)throws Exception;
}
