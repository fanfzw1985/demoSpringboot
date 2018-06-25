package cn.fzw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.fzw.weixin.vo.ExpressUpdateTime;

public interface ExpressUpdateTimeMapper {

	int updateByCodeAndNum(@Param("fromusername")String fromusername, @Param("code")String code, @Param("tracking_number")String tracking_number);

	void insertInto(@Param("fromusername")String fromusername, @Param("code")String code, @Param("tracking_number")String tracking_number);

	List<ExpressUpdateTime> selectByTime();

	void deleteById(@Param("id")int id);

}
