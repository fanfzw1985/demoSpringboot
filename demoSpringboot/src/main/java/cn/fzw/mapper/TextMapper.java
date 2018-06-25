package cn.fzw.mapper;

import org.apache.ibatis.annotations.Param;


public interface TextMapper {

	public String selectByContent(@Param("Content")String Content)throws Exception;
	
}
