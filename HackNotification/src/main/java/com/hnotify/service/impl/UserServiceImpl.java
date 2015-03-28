package com.hnotify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnotify.dao.UserDAO;
import com.hnotify.dao.domain.SysUserVO;
import com.hnotify.service.UserService;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired private UserDAO userDAO;

	@Override
	public SysUserVO getUser(String login) {
		return userDAO.getUser(login);
	}

	@Override
	public void addAdminUser(SysUserVO objUser) {
		userDAO.addAdminUser(objUser);
		System.out.println("in service..");
	}

	@Override
	public List<SysUserVO> getAdminUsersList() {
		return userDAO.getAdminUsersList();
	}

	public SysUserVO getUserById(Integer id) {
		return userDAO.getUserById(id);
	}

	public void updateAdminUser(SysUserVO objUser) {
		userDAO.updateAdminUser(objUser);

	}

}
