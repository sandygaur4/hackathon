package com.hnotify.dao.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.hnotify.dao.UserDAO;
import com.hnotify.dao.domain.SysRoleVO;
import com.hnotify.dao.domain.SysUserVO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired private SessionFactory sessionFactory;

	@Override
	public SysUserVO getUser(String login) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SysUserVO.class);
		criteria.add(Restrictions.eq("login", login));
		SysUserVO user = (SysUserVO) criteria.uniqueResult();
		if (user != null) {
			Set<SysRoleVO> lstUserRole = user.getRole();
			for (SysRoleVO objUserRole : lstUserRole) {
				System.out.println("here.." + objUserRole.getRole());
			}
		}
		return user;
	}

	public void addAdminUser(SysUserVO objUser) {
		Session session = sessionFactory.getCurrentSession();

		session.save(objUser);
	}

	public List<SysUserVO> getAdminUsersList() {

		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SysUserVO.class);
		// criteria.add(Restrictions.eq("if_delete", false));
		List<SysUserVO> lstUsers = criteria.list();
		for (SysUserVO objUser : lstUsers) {
			Set<SysRoleVO> lstUserRole = objUser.getRole();
			for (SysRoleVO objUserRole : lstUserRole) {
				System.out.println("here.." + objUserRole.getRole());
			}

		}
		return lstUsers;
	}

	@Override
	public void updateAdminUser(SysUserVO objUser) {
		Session session = sessionFactory.getCurrentSession();
		session.update(objUser);

	}

	@Override
	public SysUserVO getUserById(Integer id) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(SysUserVO.class);
		criteria.add(Restrictions.eq("id", id));
		SysUserVO user = (SysUserVO) criteria.uniqueResult();
		Set<SysRoleVO> lstUserRole = user.getRole();
		for (SysRoleVO objUserRole : lstUserRole) {
			System.out.println("here.." + objUserRole.getRole());
		}
		return user;
	}

}
