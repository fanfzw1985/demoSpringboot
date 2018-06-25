package cn.fzw.service.impl;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.RecordMapper;
import cn.fzw.service.RecordService;
import cn.fzw.vo.Records;
@Service
public class RecordServiceImpl implements RecordService {
	@Autowired
	private RecordMapper recordmapper;
	
	public int insertInto(String username,String name, String recordmsg,int flag) throws Exception {
		int num=recordmapper.insertInto(username,name,recordmsg,flag);
		return num;
	}
	public ArrayList<Records> selectAllByPage(int pageStart, int PageEnd,int flag) throws Exception{
		ArrayList<Records> recordsList=recordmapper.selectAllByPage(pageStart,PageEnd,flag);
		return recordsList;
	}
	public Records showMessageBoardById(int id) throws Exception {
		// TODO Auto-generated method stub
		Records records=recordmapper.showMessageBoardById(id);
		return records;
	}
	public int updateBoardById(int id, String messageBoardUpdate)
			throws Exception {
		// TODO Auto-generated method stub
		int num=recordmapper.updateBoardById(id,messageBoardUpdate);
		return num;
	}
	public ArrayList<Records> selectByNameAndMessage(String name,
			String updatMsg) throws Exception {
		// TODO Auto-generated method stub
		ArrayList<Records> recordList=recordmapper.selectByNameAndMessage(name,updatMsg);
		return recordList;
	}
	public int deleteRecordById(String checkedId) throws Exception {
		// TODO Auto-generated method stub
		String[] idString=checkedId.split(",");
		int num = 0;
		for(int i=0;i<idString.length;i++){
			String IdStr=idString[i];
			int id=Integer.parseInt(IdStr);
			int deleteNum=recordmapper.deleteRecordById(id);
			if(deleteNum>0){num++;}else{continue;}			
		}
		return num;
	}
}
