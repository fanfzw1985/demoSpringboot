package cn.fzw.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;

import cn.fzw.vo.Records;

public interface RecordMapper {

	public int insertInto(@Param("username")String username,@Param("name")String name,
			@Param("recordmsg")String recordmsg, @Param("flag")int flag)throws Exception;

	public ArrayList<Records> selectAllByPage(@Param("pageStart")int pageStart, 
			@Param("pageEnd")int pageEnd,@Param("flag")int flag)throws Exception;

	public Records showMessageBoardById(@Param("id")int id)throws Exception;

	public int updateBoardById(@Param("id")int id, 
			@Param("messageBoardUpdate")String messageBoardUpdate)throws Exception;

	public ArrayList<Records> selectByNameAndMessage(@Param("name")String name,
			@Param("updatMsg")String updatMsg)throws Exception;

	public int deleteRecordById(@Param("id")int id)throws Exception;

}
