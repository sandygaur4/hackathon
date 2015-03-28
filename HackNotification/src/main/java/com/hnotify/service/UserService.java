package com.hnotify.service;

import java.util.List;

import com.hnotify.dao.domain.SysUserVO;


public interface UserService {
	
	public SysUserVO getUser(String login);
	
	public void addAdminUser(SysUserVO objUser);
	
	public List<SysUserVO> getAdminUsersList();
	
	public SysUserVO getUserById(Integer id);
	
	public void updateAdminUser(SysUserVO objUser);


}
