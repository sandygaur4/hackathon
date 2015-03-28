package com.hnotify.service;

import java.util.Map;

import com.hnotify.dao.domain.SysEmailSmsTemplateVO;
import com.hnotify.dao.domain.SysEmailVO;
import com.hnotify.dao.domain.SysUserVO;


public interface EmailCreateService {

	/*SysEmailVO getRegisterEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mAX_EMAIL_TRY_ALLOWED);

	SysEmailVO getForgotPass(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mAX_EMAIL_TRY_ALLOWED);

	SysEmailVO getFBDeviceNfEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String mAX_EMAIL_TRY_ALLOWED);

	SysEmailVO getEditProfile(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysActualUserVO sysActualUserVO, String email,
			String mAX_EMAIL_TRY_ALLOWED);
*/
	SysEmailVO getAdminUserEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysUserVO userForm, String mAX_EMAIL_TRY_ALLOWED,
			String mAIL_IMAGE_URL);
	
	/*SysEmailVO getCMSLoginEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			SysUserVO objUser, String mAX_EMAIL_TRY_ALLOWED, String strIpAddress);

	SysEmailVO getCMSForgetPasswordEmail(
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysUserVO objUser,
			String mAX_EMAIL_TRY_ALLOWED);*/
	/*
	SysEmailVO getAdvertiseEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			String advertiseType, String customerComments, String city,
			String email, String mobile, String region,String userName,
			String mAX_EMAIL_TRY_ALLOWED, String string);

	SysEmailVO getCareerEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,
			String mAX_EMAIL_TRY_ALLOWED, String string, String exactFileName, String city, String location);

	SysEmailVO getFeedbackEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO,SysFeedbackVO objFeedback ,
			String mAX_EMAIL_TRY_ALLOWED, String strEmailTo);
*/	
}
