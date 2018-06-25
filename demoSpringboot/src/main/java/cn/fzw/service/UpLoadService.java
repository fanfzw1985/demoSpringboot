package cn.fzw.service;


public interface UpLoadService {

	public int insertByusernameAndPath(String username,String photoRealName, String fileName, String photoPath) throws Exception;

	public int selectByPhotoName(String photoName)throws Exception;



}
