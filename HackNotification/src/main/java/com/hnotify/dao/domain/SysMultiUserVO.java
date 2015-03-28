package com.hnotify.dao.domain;

import java.util.LinkedList;
import java.util.List;


import javax.persistence.Entity;

public class SysMultiUserVO {
	
	private LinkedList<SysUserVO> lstUsers;
	
	
	private String userChosen;

	/**
	 * @return the lstUsers
	 */
	public LinkedList<SysUserVO> getLstUsers() {
		return lstUsers;
	}

	/**
	 * @param lstUsers the lstUsers to set
	 */
	public void setLstUsers(LinkedList<SysUserVO> lstUsers) {
		this.lstUsers = lstUsers;
	}

	/**
	 * @return the userChosen
	 */
	public String getUserChosen() {
		return userChosen;
	}

	/**
	 * @param userChosen the userChosen to set
	 */
	public void setUserChosen(String userChosen) {
		this.userChosen = userChosen;
	}

}
