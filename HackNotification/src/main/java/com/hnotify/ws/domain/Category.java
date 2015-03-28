package com.hnotify.ws.domain;

import java.util.List;
import java.util.Set;


public class Category {
	private int categoryId;
	private String name;
	private List<Product_Type> product_Types;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Product_Type> getProduct_Types() {
		return product_Types;
	}
	public void setProduct_Types(List<Product_Type> product_Types) {
		this.product_Types = product_Types;
	}
	
	
}
