package com.hnotify.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hnotify.dao.CrudDao;


@Repository
@Transactional(readOnly=true)
public class CrudDaoImpl implements CrudDao{

	@Autowired private SessionFactory sessionFactory;
	@Transactional(readOnly=false)
	public void save(Object obj) {
		sessionFactory.getCurrentSession().save(obj);		
	}
	@Transactional(readOnly=false)
	public void saveOrUpdate(Object obj) {
		sessionFactory.getCurrentSession().saveOrUpdate(obj);		
	}
	@Transactional(readOnly=false)
	public void update(Object obj) {
		sessionFactory.getCurrentSession().update(obj);		
	}
	@Transactional(readOnly=false)
	public void delete(Object obj) {
		sessionFactory.getCurrentSession().delete(obj);		
	}

	public Object get(Object obj, long id) {
		return sessionFactory.getCurrentSession().get(obj.getClass(), id); 
	}

	public Object get(Object obj, int id) {
		return sessionFactory.getCurrentSession().get(obj.getClass(), id); 
	}

	public Object get(Object obj, String id) {
		return sessionFactory.getCurrentSession().get(obj.getClass(), id); 
	}

	@SuppressWarnings("unchecked")
	public List<Object> getAllObjects(Object obj) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(obj.getClass());
		return criteria.list();
	}

	@Transactional(readOnly=false)
	public void merge(Object obj) {
		sessionFactory.getCurrentSession().merge(obj);		
	}

	
	@Override
	public Object getObjByProperty(Object obj, String property, String value) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(obj.getClass());
		criteria.add(Restrictions.eq(property, value));
		criteria.setMaxResults(1);
		return criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> getAllObjectsByProperty(Object obj, String property, int value) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(obj.getClass());
		criteria.add(Restrictions.eq(property, value));
		return criteria.list();
	}

	@Override
	public List<Object> getAllObjectsByProperty(Object obj, String property, String value) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(obj.getClass());
		criteria.add(Restrictions.eq(property, value));
		return criteria.list();
	}
}
