package cn.fzw.service;

public interface RegisterService {

	public int register(String username, String password,String name, String email)throws Exception;

	public int alterById(int id, String username, String password,String password1)throws Exception;

	public int alterNameById(int id, String name)throws Exception;

}
