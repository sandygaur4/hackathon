package com.hnotify.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hnotify.dao.domain.SysEmailSmsTemplateVO;
import com.hnotify.dao.domain.SysEmailVO;
import com.hnotify.dao.domain.SysUserVO;
import com.hnotify.service.EmailCreateService;
import com.hnotify.service.ExecpEmailService;

@Service
@Transactional
public class EmailCreateServiceImpl implements EmailCreateService {
	@Autowired ExecpEmailService execpEmailService;
	

	Logger slf4jLogger = LoggerFactory.getLogger("ws");
/*
	@Override
	public SysEmailVO getRegisterEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysActualUserVO sysActualUserVO, String mAX_EMAIL_TRY_ALLOWED) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(sysActualUserVO.getEmail());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<USER_NAME>", sysActualUserVO.getUserName());
		body = body.replace("<EMAIL_REGISTRATION_IMAGES_URL>", EMAIL_REGISTRATION_IMAGES_URL);
		body = body.replace("<EMAIL>", sysActualUserVO.getEmail());
		if (sysActualUserVO.getPassword() != null && !sysActualUserVO.getPassword().equalsIgnoreCase("")) {
			body = body.replace("<PASSWORD>", TokenUtil.getValidPassword(sysActualUserVO.getPassword()));
		} else {
			body = body.replace("<PASSWORD>", "");
		}

		body = body.replace("<GENDER>", sysActualUserVO.getGender());
		body = body.replace("<CITY>", sysActualUserVO.getCity());
		if (sysActualUserVO.getDob() != null) {
			body = body.replace("<DOB>", formatter.format(sysActualUserVO.getDob()));
		}
		sysEmailVO.setEmailbody(body);

		sysEmailVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysEmailVO.setType("REGISTRATION");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;
	}

	@Override
	public SysEmailVO getFBDeviceNfEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysActualUserVO sysActualUserVO, String mAX_EMAIL_TRY_ALLOWED) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(sysActualUserVO.getEmail());

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<OTP>", sysActualUserVO.getOpt());

		body = body.replace("<USER_NAME>", sysActualUserVO.getUserName());

		SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yy");

		body = body.replace("<CREATE_TIME>", formatter1.format(new Date()));

		body = body.replace("<CREATE_DATE>", formatter2.format(new Date()));

		body = body.replace("<EMAIL_OTP_IMAGES_URL>", EMAIL_OTP_IMAGES_URL);

		sysEmailVO.setEmailbody(body);

		sysEmailVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysEmailVO.setType("FB_DEVICE_NF_OTP");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;

	}

	@Override
	public SysEmailVO getForgotPass(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysActualUserVO sysActualUserVO, String mAX_EMAIL_TRY_ALLOWED) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(sysActualUserVO.getEmail());

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<OTP>", sysActualUserVO.getOpt());

		body = body.replace("<USER_NAME>", sysActualUserVO.getUserName());

		SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yy");

		body = body.replace("<CREATE_TIME>", formatter1.format(new Date()));

		body = body.replace("<CREATE_DATE>", formatter2.format(new Date()));

		body = body.replace("<EMAIL_OTP_IMAGES_URL>", EMAIL_OTP_IMAGES_URL);
		sysEmailVO.setEmailbody(body);

		sysEmailVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysEmailVO.setType("FORGOTPASSWORD");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;
	}

	@Override
	public SysEmailVO getEditProfile(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysActualUserVO sysActualUserVO, String email, String mAX_EMAIL_TRY_ALLOWED) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		SimpleDateFormat formatter1 = new SimpleDateFormat("hh:mm:ss");
		SimpleDateFormat formatter2 = new SimpleDateFormat("dd-MM-yy");

		sysEmailVO.setBookingid(String.valueOf(sysActualUserVO.getPkProfilerId()));
		sysEmailVO.setType("EDIT_PROFILE_OTP");

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(email);

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<OTP>", sysActualUserVO.getOpt());

		body = body.replace("<USER_NAME>", sysActualUserVO.getUserName());

		body = body.replace("<CREATE_TIME>", formatter1.format(new Date()));

		body = body.replace("<CREATE_DATE>", formatter2.format(new Date()));

		body = body.replace("<EMAIL_OTP_IMAGES_URL>", EMAIL_OTP_IMAGES_URL);
		sysEmailVO.setEmailbody(body);
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;
	}

	@Override
	public SysEmailVO getFeedbackEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysFeedbackVO objFeedback, String mAX_EMAIL_TRY_ALLOWED, String strEmailTo) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setBookingid(String.valueOf(""));
		sysEmailVO.setType("FEEDBACK");

		sysEmailVO.setEmail_to(strEmailTo);
		if (objFeedback.getCopySelf() == 1) {
			sysEmailVO.setEmail_cc(objFeedback.getEmail());
		}

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		// sysEmailVO.setEmail_to(userForm.getLogin());

		sysEmailVO.setEmailbody(createFeedbackEmail(sysEmailSmsTemplateVO.getEmailBody(), objFeedback));
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;

	}
	
	@Override
	public SysEmailVO getAdvertiseEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, String advertiseType, String customerComments, String city, String email,
			String mobile, String region, String userName, String mAX_EMAIL_TRY_ALLOWED, String emailTo) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(emailTo);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<USER_NAME>", userName);

		body = body.replace("<EMAIL_PREBOOK_IMAGES_URL>", EMAIL_PREBOOK_IMAGES_URL);
		body = body.replace("<EMAIL_ID>", email);
		body = body.replace("<MOBILE>", mobile);

		body = body.replace("<CITY>", city);
		body = body.replace("<REGION>", region);
		body = body.replace("<COMMENTS>", customerComments);
		body = body.replace("<ADVERTISE_TYPE>", advertiseType);

		sysEmailVO.setEmailbody(body);

		sysEmailVO.setBookingid(String.valueOf("1"));

		sysEmailVO.setType("ADVERTISE");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;

	}

	@Override
	public SysEmailVO getCareerEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, String mAX_EMAIL_TRY_ALLOWED, String emailTo, String exactFileName, String city,
			String location) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setBookingid(exactFileName.substring(exactFileName.lastIndexOf("/") + 1, exactFileName.lastIndexOf(".")));
		sysEmailVO.setEmail_attachment_url(exactFileName);
		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(emailTo);
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		// String body = emailBody.replace("<USER_NAME>", userName);

		String body = emailBody.replace("<EMAIL_PREBOOK_IMAGES_URL>", EMAIL_PREBOOK_IMAGES_URL);

		body = body.replace("<LOCATION>", location);
		body = body.replace("<CITY>", city);

		sysEmailVO.setEmailbody(body);

		// sysEmailVO.setBookingid(String.valueOf("1"));

		sysEmailVO.setType("CAREER");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;

	}
	*/
	@Override
	public SysEmailVO getAdminUserEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysUserVO userForm, String mAX_EMAIL_TRY_ALLOWED, String mAIL_IMAGE_URL) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setBookingid(String.valueOf(userForm.getId()));
		sysEmailVO.setType("ADMIN_USER");

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(userForm.getLogin());

		sysEmailVO.setEmailbody(createEmailBodyAdminUser(userForm, sysEmailSmsTemplateVO.getEmailBody(), mAIL_IMAGE_URL));
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;

	}

	private String createEmailBodyAdminUser(SysUserVO objUser, String emailBody, String mAIL_IMAGE_URL) {
		String body = emailBody.replace("<USER_NAME>", objUser.getUserName());
		body = body.replace("<USER_PASSWORD>", objUser.getPassword());
		body = body.replace("<USER_EMAIL>", objUser.getLogin());

		return body;
	}
/*
	private String createFeedbackEmail(String emailBody, SysFeedbackVO objFeedback) {

		String comments = objFeedback.getCustomerComments();
		comments = comments.replace("\n", "<br>");
		String body = emailBody.replace("<USER_NAME>", objFeedback.getUserName());

		body = body.replace("<FEEDBACK_TYPE>", objFeedback.getFeedbackType());
		body = body.replace("<CUSTOMER_COMMENTS>", comments);
		body = body.replace("<CUSTOMER_NAME>", objFeedback.getUserName());
		body = body.replace("<MOBILE_NUMBER>", objFeedback.getMobile());
		body = body.replace("<EMAIL>", objFeedback.getEmail());

		// na
		if (String.valueOf(objFeedback.getPlatform()).equalsIgnoreCase("WAP") || String.valueOf(objFeedback.getPlatform()).equalsIgnoreCase("WEBSITE")) {
			body = body.replace("<PLATFORM>", String.valueOf(objFeedback.getPlatform()).toUpperCase());
		} else {
			body = body.replace("<PLATFORM>", String.valueOf(objFeedback.getPlatform().toUpperCase()) + " APP");
		}

		body = body.replace("<EMAIL_PREBOOK_IMAGES_URL>", EMAIL_PREBOOK_IMAGES_URL);

		return body;

	}

	@Override
	public SysEmailVO getCMSLoginEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysUserVO objUser, String mAX_EMAIL_TRY_ALLOWED, String strIpAddress) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(objUser.getLogin());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<USER_NAME>", objUser.getUserName());

		body = body.replace("<EMAIL_PREBOOK_IMAGES_URL>", EMAIL_PREBOOK_IMAGES_URL);
		body = body.replace("<LOGIN_TIME>", formatter.format(new Date()));
		body = body.replace("<IP_ADDRESS>", strIpAddress);

		sysEmailVO.setEmailbody(body);

		sysEmailVO.setBookingid(String.valueOf(objUser.getId()));

		sysEmailVO.setType("CMS_LOGIN");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;
	}

	@Override
	public SysEmailVO getCMSForgetPasswordEmail(SysEmailSmsTemplateVO sysEmailSmsTemplateVO, SysUserVO objUser, String mAX_EMAIL_TRY_ALLOWED) {
		SysEmailVO sysEmailVO = new SysEmailVO();

		sysEmailVO.setEmail_from(sysEmailSmsTemplateVO.getEmailFrom());
		sysEmailVO.setEmail_subject(sysEmailSmsTemplateVO.getEmailSubject());
		sysEmailVO.setEmail_timestamp(new java.util.Date());
		sysEmailVO.setEmail_to(objUser.getLogin());
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");

		String emailBody = sysEmailSmsTemplateVO.getEmailBody();
		String body = emailBody.replace("<USER_NAME>", objUser.getUserName());

		body = body.replace("<EMAIL_PREBOOK_IMAGES_URL>", EMAIL_PREBOOK_IMAGES_URL);
		body = body.replace("<EMAIL_ID>", objUser.getLogin());
		body = body.replace("<PASSWORD>", objUser.getPassword());
		if (objUser.getIfActive()) {
			body = body.replace("<STATUS>", "ACTIVE");
		} else {
			body = body.replace("<STATUS>", "INACTIVE");
		}

		sysEmailVO.setEmailbody(body);

		sysEmailVO.setBookingid(String.valueOf(objUser.getId()));

		sysEmailVO.setType("CMS_FORGET_PASSWORD");
		sysEmailVO.setRetrycount(Integer.parseInt(mAX_EMAIL_TRY_ALLOWED));

		return sysEmailVO;
	}

	*/


}
