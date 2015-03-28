package com.hnotify.cms.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hnotify.cms.validator.CategoryValidationForm;
import com.hnotify.dao.domain.CityVO;
import com.hnotify.dao.domain.LocationVO;
import com.hnotify.dao.domain.UserDataVO;
import com.hnotify.service.CrudService;
import com.hnotify.service.ExecpEmailService;
import com.hnotify.util.Utilities;

@Controller
public class SendNotificationController {

	@Autowired CrudService crudService;
	@Autowired CategoryValidationForm categoryValidationForm;
	@Autowired ExecpEmailService execpEmailService;

	@Value("${ADD_SUCCESS}") private String ADD_SUCCESS;
	@Value("${EDIT_SUCCESS}") private String EDIT_SUCCESS;
	@Value("${DELETED_SUCCESS}") private String DELETED_SUCCESS;
	@Value("${EXCEPTION_OCCUR}") private String EXCEPTION_OCCUR;
	@Value("${GOOGLE_GEO_API}") private String GOOGLE_GEO_API;

	@RequestMapping("/admin/getusercities")
	public String getusercities(Map<String, Object> map) {
		map.put("citylist", crudService.getAllObjects(new CityVO()));
		return "citylist";

	}
	
	@RequestMapping("/admin/notifycity")
	public String notifycity(@RequestParam long id, Map<String, Object> map) {
		map.put("city", crudService.get(new CityVO(), id));		
		return "notifymap";

	}
	
	@RequestMapping(value="/admin/notify", method = RequestMethod.POST)
	public String notify(@RequestParam String latlng,@RequestParam int radius, Map<String, Object> map) {
		String[] lt = latlng.split(",");
		LocationVO locationVO = Utilities.getInstance().getGeoLocation(GOOGLE_GEO_API, lt[0], lt[1]);
		System.out.println("country is::" + locationVO.getCountry());
		System.out.println("state is::" + locationVO.getState());
		System.out.println("city is::" + locationVO.getCity());
		
		List<UserDataVO> dataVOs = (List<UserDataVO>)(Object)crudService.getAllObjectsByProperty(new UserDataVO(), "city", locationVO.getCity());
		List<String> androidNotify = new ArrayList<String>();
		List<String> iosNotify = new ArrayList<String>();
		List<String> windowNotify = new ArrayList<String>();
		for (UserDataVO userDataVO : dataVOs) {
			int dis = Utilities.getInstance().calcDistance(Double.valueOf(lt[0]), Double.valueOf(lt[1]), Double.valueOf(userDataVO.getLat()), Double.valueOf(userDataVO.getLng()));
			if(dis<=radius){
				if(userDataVO.getPlatform().equalsIgnoreCase("IOS")){
					iosNotify.add(userDataVO.getNotifyid());
				}else if(userDataVO.getPlatform().equalsIgnoreCase("ANDROID")){
					androidNotify.add(userDataVO.getNotifyid());
				}
				else if(userDataVO.getPlatform().equalsIgnoreCase("WINDOWS")){
					windowNotify.add(userDataVO.getNotifyid());
				}
			}
		}
		pushNotification(androidNotify,iosNotify,windowNotify);
		
		
		
		return "notify";

	}

	private void pushNotification(List<String> androidNotify, List<String> iosNotify, List<String> windowNotify) {
		// TODO Auto-generated method stub
		
	}

	
}
