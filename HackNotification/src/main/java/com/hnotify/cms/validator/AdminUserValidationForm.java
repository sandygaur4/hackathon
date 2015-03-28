package com.hnotify.cms.validator;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import com.hnotify.dao.domain.SysUserVO;

@Component("adminUserValidationForm")
public class AdminUserValidationForm {

	@Value("${NOTEMPTY_DENOMINATION_VALUE}")
	private String NOTEMPTY_DENOMINATION_VALUE;

	@Value("${NOTEMPTY_COUNT}")
	private String NOTEMPTY_COUNT;

	public boolean supports(Class<?> klass) {
		return SysUserVO.class.isAssignableFrom(klass);
	}

	/**
	 * @param target
	 * @param errors
	 */
	public void validate(Object target, Errors errors) {

		SysUserVO userForm = (SysUserVO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
				"NotEmpty.userForm.userName", "Name cannot be blank");

		System.out.println("......" + userForm.getMobileNumber());

		if (userForm.getMobileNumber() == null) {

		} else if (userForm.getMobileNumber().equals("")) {

		} else if (userForm.getMobileNumber().length() < 10) {
			System.out.println("error occured");
			errors.rejectValue("mobileNumber",
					"NotEmpty.userForm.mobileNumber",
					"Mobile number should be minimum of 10 characters");

		}

	}

	/**
	 * @param target
	 * @param errors
	 */
	public void validateAdd(Object target, Errors errors) {

		validate(target, errors);
		SysUserVO userForm = (SysUserVO) target;

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "login",
				"NotEmpty.userForm.login", "Email ID cannot be blank");

	}

	public void validatePassword(Object target, BindingResult result,
			Errors errors) {

		SysUserVO userForm1 = (SysUserVO) target;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "uPassword",
				"NotEmpty.userForm.uPassword", "Old Password cannot be blank");
		ValidationUtils
				.rejectIfEmptyOrWhitespace(errors, "newPassword",
						"NotEmpty.userForm.newPassword",
						"New Password cannot be blank");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "confirmPassword",
				"NotEmpty.userForm.confirmPassword",
				"Confirm Password cannot be blank");

		if (userForm1.getPassword().equals(userForm1.getuPassword())) {

		} else {
			errors.rejectValue("uPassword",
					"NotEmpty.userForm.uPassword", "Incorrect Old Password");
		}
		
		if ((userForm1.getNewPassword().trim().length() > 0) && (userForm1.getNewPassword().trim().length() < 8)) {
			errors.rejectValue("newPassword", "NotEmpty.userForm.newPassword",
					"Please enter Minimum of 8 characters");
		}
		if ((userForm1.getConfirmPassword().trim().length() > 0)&& (userForm1.getConfirmPassword().trim().length() < 8)) {
			errors.rejectValue("confirmPassword",
					"NotEmpty.userForm.confirmPassword",
					"Please enter Minimum of 8 characters");
		}
		if ((userForm1.getNewPassword().trim().length() > 0) && (userForm1.getNewPassword().trim().length() != userForm1.getNewPassword().length())){
			errors.rejectValue("newPassword", "NotEmpty.userForm.newPassword",
					"Leading and Trailing Spaces are not allowed");
		}
		if ((userForm1.getConfirmPassword().trim().length() > 0)&& (userForm1.getConfirmPassword().trim().length() != userForm1.getConfirmPassword().length())){
			errors.rejectValue("confirmPassword", "NotEmpty.userForm.confirmPassword",
					"Leading and Trailing Spaces are not allowed");
		}
		if ((userForm1.getNewPassword().trim().length() > 0) && (userForm1.getuPassword().trim().length() > 0) && (userForm1.getNewPassword().equals(userForm1.getuPassword()))) {
			errors.rejectValue("newPassword", "NotEmpty.userForm.newPassword",
					"Old and New Password cannot be be same");	
		}
		if ((userForm1.getNewPassword().trim().length() > 0) && (userForm1.getConfirmPassword().trim().length() > 0) && (userForm1.getNewPassword().equals(userForm1.getConfirmPassword()))) {

		} else {
			errors.rejectValue("newPassword", "NotEmpty.userForm.newPassword",
					"New and Confirm Password should be same");
		}
	}

}
