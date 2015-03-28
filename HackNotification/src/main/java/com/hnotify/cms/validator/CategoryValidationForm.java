package com.hnotify.cms.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hnotify.service.CrudService;

@Component("categoryValidationForm")
public class CategoryValidationForm {
	@Autowired CrudService crudService;
/*
	public boolean supports(Class<?> klass) {
		return CategoryVO.class.isAssignableFrom(klass);
	}

	public void validate(Object target, Errors errors) {
		CategoryVO categoryVO = (CategoryVO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.target.name", "Name cannot be blank");
		if (categoryVO.getCategoryId() != 0) {
			CategoryVO categoryVO2 = (CategoryVO) crudService.getObjByProperty(new CategoryVO(), "name", categoryVO.getName());
			if (categoryVO2 != null) {
				if (categoryVO2.getCategoryId() != categoryVO.getCategoryId())
					errors.rejectValue("name", "NotEmpty.categoryVO.name", "This Category already exist.");
			}
		} else {
			CategoryVO categoryVO2 = (CategoryVO) crudService.getObjByProperty(new CategoryVO(), "name", categoryVO.getName());
			if (categoryVO2 != null)
				errors.rejectValue("name", "NotEmpty.categoryVO.name", "This Category already exist.");
		}
	}

	@SuppressWarnings("unchecked")
	public void validateProductType(Object target, Errors errors) {
		Product_TypeVO product_TypeVO = (Product_TypeVO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.target.name", "Name cannot be blank");
		if (product_TypeVO.getProductTypeId() != 0) {
			List<Product_TypeVO> product_TypeVOs = (List<Product_TypeVO>) (Object) crudService.getAllObjectsByProperty(new Product_TypeVO(), "name",
					product_TypeVO.getName());
			if (product_TypeVOs.size() != 0) {
				boolean flag = false;
				for (Product_TypeVO product_TypeVO2 : product_TypeVOs) {
					if (product_TypeVO2.getProductTypeId() != product_TypeVO.getProductTypeId()) {
						if (product_TypeVO2.getCategory().getCategoryId() == product_TypeVO.getCategory().getCategoryId()){
							flag = true;
							break;
						}							
					}
				}
				if (flag) {
					errors.rejectValue("name", "NotEmpty.categoryVO.name", "This Product type already exist.");
				}
			}
		} else {
			List<Product_TypeVO> product_TypeVOs = (List<Product_TypeVO>) (Object) crudService.getAllObjectsByProperty(new Product_TypeVO(), "name",
					product_TypeVO.getName());
			if (product_TypeVOs.size() != 0) {
				for (Product_TypeVO product_TypeVO2 : product_TypeVOs) {
						if (product_TypeVO2.getCategory().getCategoryId() == product_TypeVO.getCategory().getCategoryId()){
							errors.rejectValue("name", "NotEmpty.categoryVO.name", "This Product type already exist.");
							break;
						}							
				}
			}
		}
	}
*/}
