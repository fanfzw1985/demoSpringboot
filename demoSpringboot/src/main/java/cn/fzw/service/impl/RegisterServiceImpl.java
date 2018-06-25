package cn.fzw.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.fzw.mapper.LoginMapper;
import cn.fzw.mapper.RegisterMapper;
import cn.fzw.service.RegisterService;
import cn.fzw.vo.Users;

@Service
public class RegisterServiceImpl implements RegisterService {

	@Autowired
	private RegisterMapper registermapper;
	@Autowired
	private LoginMapper loginmapper;
	public int register(String username, String password,String name,String email) throws Exception {
		// TODO Auto-generated method stub
		Users user=registermapper.selectByUsername(username);
		if(user!=null){
			return -1;
		}else{
		int num=registermapper.insertinto(username,password,name,email);
		return num;}
	}
	public int alterById(int id,String username,String password,String password1) throws Exception{
		Users user=loginmapper.selectByUsernameAndPassword(username,password);
		if(user!=null){
			int num=registermapper.alterById(id,password1);
			if(num==1){
				return 1;
			}else{return -1;}
		}else{
		return 0;}
	}
	public int alterNameById(int id,String name) throws Exception {
		// TODO Auto-generated method stub
		return registermapper.alterNameById(id,name);
	}

}
