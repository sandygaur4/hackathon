package com.hnotify.dao.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "sys_role")
public class SysRoleVO implements java.io.Serializable {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Integer id;

	@Column(name = "role")
	private String role;
	
	@Column(name = "role_detail")
	private String role_detail;

	@javax.persistence.ManyToMany
	@JoinTable(name = "sys_user_role", joinColumns = { @JoinColumn(name = "role_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "user_id", referencedColumnName = "id") })
	private Set<SysUserVO> userRoles;

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRole() {
		return this.role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Set<SysUserVO> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<SysUserVO> userRoles) {
		this.userRoles = userRoles;
	}

	/**
	 * @return the role_detail
	 */
	public String getRole_detail() {
		return role_detail;
	}

	/**
	 * @param role_detail the role_detail to set
	 */
	public void setRole_detail(String role_detail) {
		this.role_detail = role_detail;
	}

}