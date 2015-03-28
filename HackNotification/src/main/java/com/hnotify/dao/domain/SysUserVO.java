package com.hnotify.dao.domain;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name = "sys_user")
public class SysUserVO {

	@Id @GeneratedValue @Column(name = "id", unique = true, nullable = false) private Integer id;

	@Column(name = "user_name", length = 150, nullable = false) private String userName;

	@Column(name = "email_id", length = 150, nullable = false, unique = true) private String login;

	@Column(name = "password", length = 20, nullable = false) private String password;

	@Column(name = "mobile_number", length = 20) private String mobileNumber;

	@javax.persistence.ManyToMany @JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }) private Set<SysRoleVO> role;

	@Column(name = "is_active", nullable = false) private Boolean ifActive = true;

	@Column(name = "is_deleted", nullable = false, columnDefinition = "tinyint default false") private Boolean ifDeleted;

	@Temporal(TemporalType.TIMESTAMP) @Column(name = "update_time") private Date updateTime;

	@Transient String[] lstUserRoles;

	@Transient List<String> lstAllRoles;

	@Transient boolean ifDisplayed;

	public boolean isIfDisplayed() {
		return ifDisplayed;
	}

	public void setIfDisplayed(boolean ifDisplayed) {
		this.ifDisplayed = ifDisplayed;
	}

	public List<String> getLstAllRoles() {
		return lstAllRoles;
	}

	public void setLstAllRoles(List<String> lstAllRoles) {
		this.lstAllRoles = lstAllRoles;
	}

	@Transient private String newPassword;

	@Transient private String confirmPassword;

	@Transient private String uPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public String[] getLstUserRoles() {
		return lstUserRoles;
	}

	public void setLstUserRoles(String[] lstUserRoles) {
		this.lstUserRoles = lstUserRoles;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Boolean getIfActive() {
		return ifActive;
	}

	public void setIfActive(Boolean ifActive) {
		this.ifActive = ifActive;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	public Set<SysRoleVO> getRole() {
		return role;
	}
	public void setRole(Set<SysRoleVO> role) {
		this.role = role;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getuPassword() {
		return uPassword;
	}

	public void setuPassword(String uPassword) {
		this.uPassword = uPassword;
	}

	public Boolean getIfDeleted() {
		return ifDeleted;
	}

	public void setIfDeleted(Boolean ifDeleted) {
		this.ifDeleted = ifDeleted;
	}

}
