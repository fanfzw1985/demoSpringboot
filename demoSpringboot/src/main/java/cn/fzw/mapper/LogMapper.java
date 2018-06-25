package cn.fzw.mapper;

import org.apache.ibatis.annotations.Param;

public interface LogMapper {

	void insertIntoByOpenid(@Param("openid")String openid,@Param("message")String message);

}
