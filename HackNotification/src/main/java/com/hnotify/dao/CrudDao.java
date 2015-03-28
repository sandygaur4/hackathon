package com.hnotify.dao;

import java.util.List;

public interface CrudDao {
	public void save(Object obj);
	public void saveOrUpdate(Object obj);
	public void update(Object obj);
	public void delete(Object obj);
	public Object get(Object obj,long id);
	public Object get(Object obj,int id);
	public Object get(Object obj,String id) ;
	public List<Object> getAllObjects(Object obj);
	public void merge(Object obj);
	public Object getObjByProperty(Object obj, String property, String value);
	public List<Object> getAllObjectsByProperty(Object obj, String property, int value);
	public List<Object> getAllObjectsByProperty(Object obj, String property, String value);
}
