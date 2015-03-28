package com.hnotify.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnotify.dao.CrudDao;
import com.hnotify.service.CrudService;

@Service
@Transactional
public class CrudServiceImpl implements CrudService {
	@Autowired CrudDao crudDao;

	public void save(Object obj) {
		crudDao.save(obj);
	}

	public void saveOrUpdate(Object obj) {
		crudDao.saveOrUpdate(obj);
	}

	public void update(Object obj) {
		crudDao.update(obj);
	}

	public void delete(Object obj) {
		crudDao.delete(obj);
	}

	public Object get(Object obj, long id) {
		return crudDao.get(obj, id);
	}

	public Object get(Object obj, int id) {
		return crudDao.get(obj, id);
	}

	public Object get(Object obj, String id) {
		return crudDao.get(obj, id);
	}

	public List<Object> getAllObjects(Object obj) {
		return crudDao.getAllObjects(obj);
	}

	public void merge(Object obj) {
		crudDao.merge(crudDao);
	}

	@Override
	public Object getObjByProperty(Object obj, String property, String value) {
		return crudDao.getObjByProperty(obj, property, value);
	}

	
	@Override
	public List<Object> getAllObjectsByProperty(Object obj, String property, int value) {
		return crudDao.getAllObjectsByProperty(obj, property, value);
	}

	@Override
	public List<Object> getAllObjectsByProperty(Object obj, String property, String value) {
		return crudDao.getAllObjectsByProperty(obj, property, value);
	}

}
