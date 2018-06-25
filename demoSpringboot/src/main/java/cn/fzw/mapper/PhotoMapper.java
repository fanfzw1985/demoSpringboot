package cn.fzw.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.PhotoUpLoad;

public interface PhotoMapper {
	public ArrayList<PhotoUpLoad> selectAllByPage(@Param("username")String username,@Param("pageStart")int pageStart, @Param("photoWallNum")int photoWallNum)throws Exception;

	public int selectAllInPhotos(@Param("username")String username)throws Exception;

	public PhotoUpLoad selectPhotoById(@Param("id")int id)throws Exception;

	public ArrayList<PhotoUpLoad> selectAll(@Param("superadmin1")String superadmin1, @Param("superadmin2")String superadmin2)throws Exception;

	public void deleteById(@Param("id")int id)throws Exception;

	public PhotoUpLoad selectLastSidePhoto(@Param("id")int id,@Param("username")String username)throws Exception;

	public PhotoUpLoad selectNextSidePhoto(@Param("id")int id,@Param("username")String username)throws Exception;
	
	public int insertByusernameAndPath(@Param("username")String username,@Param("photoRealName")String photoRealName, @Param("photoName")String photoName,
			@Param("photoPath")String photoPath)throws Exception;

	public int selectByPhotoName(@Param("photoName")String photoName)throws Exception;


}
