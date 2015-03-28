package com.hnotify.ws.domain;

public class Product_Type {
	private int productTypeId;
	private String name;
//	private CategoryVO category;
//	private Set<ProductVO> productVOs;
//	private Set<Attribute_TypeVO> attribute_Types = new HashSet<Attribute_TypeVO>();
	public int getProductTypeId() {
		return productTypeId;
	}
	public void setProductTypeId(int productTypeId) {
		this.productTypeId = productTypeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	}
