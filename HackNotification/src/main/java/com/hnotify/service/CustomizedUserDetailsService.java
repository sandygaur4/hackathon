package com.hnotify.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ContextLoader;

import com.hnotify.dao.domain.SysRoleVO;
import com.hnotify.dao.domain.SysUserVO;

@Service
@Transactional(readOnly = true)
public class CustomizedUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {

		ApplicationContext ctx = ContextLoader.getCurrentWebApplicationContext();

		SessionFactory sessionFactory = (SessionFactory) ctx.getBean("sessionFactory");

		Session session = sessionFactory.openSession();
//		Transaction transaction = session.beginTransaction();
		Criteria criteria = session.createCriteria(SysUserVO.class);

		criteria.add(Restrictions.eq("login", login));

		SysUserVO domainUser = (SysUserVO) criteria.uniqueResult();

		if (domainUser.getIfActive() == false) {
			throw new BadCredentialsException("Invalid User!");
		}
		Set<SysRoleVO> colRole = domainUser.getRole();
		if (colRole.size() == 0) {
			throw new BadCredentialsException("Invalid User!");
		}
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		return new User(domainUser.getLogin(), domainUser.getPassword(), enabled, accountNonExpired, credentialsNonExpired, accountNonLocked,
				getAuthorities(domainUser.getRole()));
	}

	public Collection<? extends GrantedAuthority> getAuthorities(Set<SysRoleVO> setRoles) {
		List<GrantedAuthority> authList = getGrantedAuthorities(getRoles(setRoles));
		return authList;
	}

	public List<String> getRoles(Set<SysRoleVO> setRoles) {

		List<String> roles = new ArrayList<String>();

		for (SysRoleVO objSysRole : setRoles) {
			String strRole = objSysRole.getRole();

			roles.add(strRole);

		}

		return roles;
	}

	public static List<GrantedAuthority> getGrantedAuthorities(List<String> roles) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return authorities;
	}

}
