package com.hnotify.ws.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hnotify.dao.domain.LocationVO;
import com.hnotify.dao.domain.UserDataVO;
import com.hnotify.service.CrudService;
import com.hnotify.util.Utilities;
import com.hnotify.ws.domain.WSReturnObj;

@Controller
public class UserDataControllerWS {

	@Autowired CrudService crudService;
	

	@Value("${SUCCESS}") private String SUCCESS;
	@Value("${ERROR}") private String ERROR;
	@Value("${DIALOG}") private String DIALOG;
	@Value("${SYS_ERROR}") private String SYS_ERROR;
	@Value("${GOOGLE_GEO_API}") private String GOOGLE_GEO_API;
	
	

	@RequestMapping("/internal/checkws")
	public String checkws(Map<String, Object> map) {
		return "checkws";
	}
	
	
	@RequestMapping(value = "/userdata", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody()
	public WSReturnObj getCategories(
			@RequestParam String deviceid,
			@RequestParam String lat,
			@RequestParam String lng,			
			@RequestParam String notifyid,
			@RequestParam String platform,
			@RequestParam(required=false,defaultValue="") String param1,
			@RequestParam(required=false,defaultValue="") String param2,
			@RequestParam(required=false,defaultValue="") String param3){		
		WSReturnObj returnObj = new WSReturnObj();
		try {
			LocationVO locationVO = Utilities.getInstance().getGeoLocation(GOOGLE_GEO_API, lat, lng);
			System.out.println("country is::" + locationVO.getCountry());
			System.out.println("state is::" + locationVO.getState());
			System.out.println("city is::" + locationVO.getCity());
			
			crudService.save(new UserDataVO(deviceid,lat,lng,notifyid,platform,locationVO.getCountry(),locationVO.getState(),locationVO.getCity(),new Date(),new Date(),true));
			returnObj.setOutput("successfully added");
			returnObj.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			returnObj.setResult(ERROR);
			returnObj.setMsg("Duplicate deviceid");
		}
		return returnObj;
	}
	
	/*
	@RequestMapping("/newcategory")
	public String newCategory() {
//		addCategory("Vehicles");
		
//		CategoryVO categoryVO = getCategory(2);
//		addProductType(categoryVO,"Cars");
//		addProductType(categoryVO,"Motorcycles - Scooters");
//		addProductType(categoryVO,"Bycycles");
		
//		addAttributeType("make","varchar",4);
//		addAttributeType("model","varchar",4);
		
		Product_TypeVO product_TypeVO = getProductType(4);
		addProduct("Brand new i20 car",product_TypeVO); 
//		
		
		return "index";
	}
	
	@RequestMapping(value = "/categories", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody()
	public WSReturnObj getCategories(){		
		WSReturnObj returnObj = new WSReturnObj();
		try {
			returnObj = productService.getAllCategories();
			returnObj.setResult(SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			returnObj.setResult(ERROR);
			returnObj.setMsg(SYS_ERROR);
		}
		return returnObj;
	}
	

	private void addProduct(String name, Product_TypeVO product_TypeVO) {
		ProductVO productVO = new ProductVO();
		productVO.setName(name);
		productVO.setProduct_Type(product_TypeVO);
		crudService.saveOrUpdate(productVO);
		
		Attribute_TypeVO attribute_TypeVO = getAttributeType(1);
		Product_AttributeVO product_AttributeVO = new Product_AttributeVO();
		product_AttributeVO.setValue("Hyundai");
		product_AttributeVO.setProductVO(productVO);
		product_AttributeVO.setAttribute_TypeVO(attribute_TypeVO);
		crudService.saveOrUpdate(product_AttributeVO);
		
		attribute_TypeVO = getAttributeType(2);
		product_AttributeVO = new Product_AttributeVO();
		product_AttributeVO.setValue("i20");
		product_AttributeVO.setProductVO(productVO);
		product_AttributeVO.setAttribute_TypeVO(attribute_TypeVO);
		crudService.saveOrUpdate(product_AttributeVO);
		
		
	}


	private Attribute_TypeVO getAttributeType(long id) {
		return (Attribute_TypeVO)crudService.get(new Attribute_TypeVO(), id);
	}


	private void addAttributeType(String name, String dtype, int product_id) {
		
		Attribute_TypeVO attribute_TypeVO = new Attribute_TypeVO();
		attribute_TypeVO.setName(name);
		attribute_TypeVO.setDtype(dtype);
		productService.saveProductAttribute(attribute_TypeVO,product_id);
	}

	private Product_TypeVO getProductType(int id) {
		return (Product_TypeVO)crudService.get(new Product_TypeVO(), id);
	}

	private void addProductType(CategoryVO categoryVO, String name) {
		Product_TypeVO product_TypeVO = new Product_TypeVO();
		product_TypeVO.setName(name);
		product_TypeVO.setCategory(categoryVO);
		crudService.save(product_TypeVO);
	}

	private CategoryVO getCategory(int id) {
		return (CategoryVO)crudService.get(new CategoryVO(), id);
	}

	private void addCategory(String name) {
		CategoryVO categoryVO = new CategoryVO();
		categoryVO.setName(name);
		crudService.save(categoryVO);
	}
*/}
