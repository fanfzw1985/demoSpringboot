package cn.fzw.service;

import java.util.ArrayList;
import cn.fzw.vo.Records;

public interface RecordService {

	int insertInto(String username,String name, String recordmsg, int flag)throws Exception;

	ArrayList<Records> selectAllByPage(int pageStart, int PageEnd,int flag)throws Exception;

	Records showMessageBoardById(int id)throws Exception;

	int updateBoardById(int id, String messageBoardUpdate)throws Exception;

	ArrayList<Records> selectByNameAndMessage(String name, String updatMsg)throws Exception;

	int deleteRecordById(String checkedId)throws Exception;

}
