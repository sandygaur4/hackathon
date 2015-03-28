package com.hnotify.dao;

import java.util.List;

import com.hnotify.dao.domain.SysUserVO;

public interface UserDAO{

	public SysUserVO getUser(String login);
	
	public void addAdminUser(SysUserVO objUser);
	
	public List<SysUserVO> getAdminUsersList();

	public void updateAdminUser(SysUserVO objUser);

	public SysUserVO getUserById(Integer id);

}
