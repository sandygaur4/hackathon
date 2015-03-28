package com.hnotify.cms.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hnotify.cms.validator.AdminUserValidationForm;
import com.hnotify.dao.domain.SysMultiUserVO;
import com.hnotify.dao.domain.SysRoleVO;
import com.hnotify.dao.domain.SysUserVO;
import com.hnotify.service.CrudService;
import com.hnotify.service.EmailCreateService;
import com.hnotify.service.ExecpEmailService;
import com.hnotify.service.UserService;
import com.hnotify.util.RandomPasswordGenerator;

/**
 * UserController class to perform crud operations on user and role management
 * 
 * @author H0078
 * 
 */
@Controller
@SessionAttributes({ "username" })
public class UserController {
	Logger slf4jLogger = LoggerFactory.getLogger("cms");
	@Autowired UserService userService;
	@Autowired ExecpEmailService execpEmailService;
	@Autowired EmailCreateService emailCreateService;
	@Autowired CrudService crudService;
	@Autowired AdminUserValidationForm adminUserValidationForm;
	
	@Value("${MAIL_IMAGE_URL}") private String MAIL_IMAGE_URL;
	@Value("${MAX_EMAIL_TRY_ALLOWED}") private String MAX_EMAIL_TRY_ALLOWED;
	@Value("${MAX_SMS_TRY_ALLOWED}") private String MAX_SMS_TRY_ALLOWED;
	@Value("${ADD_SUCCESS}") private String ADD_SUCCESS;
	@Value("${EDIT_SUCCESS}") private String EDIT_SUCCESS;
	@Value("${DELETED_SUCCESS}") private String DELETED_SUCCESS;

	@RequestMapping("/admin/users")
	public String getUserList() {
		return "users";
	}

	@RequestMapping("/admin/adduser")
	public String addAdminUser(Map<String, Object> map) {
		slf4jLogger.debug(" {} : addAdminUser", this.getClass().getName());
		map.put("sysUserVO", new SysUserVO());
		return "addUser";
	}

	@RequestMapping(value = "/admin/saveuser", method = RequestMethod.POST)
	public String saveAdminUser(@ModelAttribute("sysUserVO") SysUserVO sysUserVO, BindingResult result, Map<String, Object> map) {

		try {
			slf4jLogger.debug("{} : saveAdminUser: Started", this.getClass().getName());

			adminUserValidationForm.validateAdd(sysUserVO, result);
			if (result.hasErrors()) {
				slf4jLogger.debug("{} :saveAdminUser: Validation Failed", this.getClass().getName());

				map.put("userForm", sysUserVO);
				return "addUser";
			}

			SysUserVO objUser = userService.getUser(sysUserVO.getLogin());

			if (objUser == null) {

			} else {
				map.put("userForm", sysUserVO);
				map.put("addError", "Email Id Already Exist");
				return "addUser";
			}

			// Generate Password

			int noOfCAPSAlpha = 1;
			int noOfDigits = 1;
			int noOfSplChars = 0;
			int minLen = 8;
			int maxLen = 20;

			char[] pswd = RandomPasswordGenerator.generatePswd(minLen, maxLen, noOfCAPSAlpha, noOfDigits, noOfSplChars);

			sysUserVO.setPassword(new String(pswd));
			sysUserVO.setUpdateTime(new Date());

			sysUserVO.setIfDeleted(false);
			// add user
			userService.addAdminUser(sysUserVO);

			// send email to user

			// Mark for Email and SMS
			/*SysEmailSmsTemplateVO sysEmailSmsTemplateVO = (SysEmailSmsTemplateVO)crudService.get(new SysEmailSmsTemplateVO(), "ADMIN_USER");

			SysEmailVO sysEmailVO = emailCreateService.getAdminUserEmail(sysEmailSmsTemplateVO, sysUserVO, MAX_EMAIL_TRY_ALLOWED, MAIL_IMAGE_URL);
			crudService.saveOrUpdate(sysEmailVO);*/

			slf4jLogger.debug("{} :saveAdminUser: Ended", this.getClass().getName());
		} catch (DataIntegrityViolationException dex) {
			slf4jLogger.error("saveAdminUser : DataIntegrityViolationException", dex);
			map.put("userForm", sysUserVO);
			map.put("addError", "User already exist");
			dex.printStackTrace();
			return "addUser";
		} catch (Exception e) {
			execpEmailService.printAndSendExecption(this.getClass().getName(), e, "CMS");
			map.put("userForm", sysUserVO);
			map.put("addError", "Sorry !! User cannot be added");
			return "addUser";
		}

		map.put("addMsg", "User \"" + sysUserVO.getLogin() + "\" " + ADD_SUCCESS);
		return "forward:userlist";
	}

	@RequestMapping(value = "/admin/userlist")
	public String userList(Map<String, Object> map) {
		slf4jLogger.debug("{} :userList: Started", this.getClass().getName());
		List<SysUserVO> lstUser = userService.getAdminUsersList();
		// System.out.println("tblCinemaList size" + tblCinemaList.size());
		slf4jLogger.debug("{} :User List Size , {} ", this.getClass().getName(), lstUser.size());

		List<SysUserVO> lstUserNew = new ArrayList<SysUserVO>();

		// ifDisplayed
		for (SysUserVO objUser : lstUser) {
			objUser.setIfDisplayed(true);

			List lstUserRoles = new ArrayList();
			Set<SysRoleVO> colRoles = objUser.getRole();

			for (SysRoleVO objRoleVO : colRoles) {
				if (objRoleVO.getRole().equals("ROLE_ADMIN")) {

					objUser.setIfDisplayed(false);
				}
			}
			if (objUser.getIfDeleted() == false) {
				lstUserNew.add(objUser);
			}
		}
		map.put("userList", lstUserNew);
		if (lstUserNew.size() == 0) {
			map.put("addError", "No Records Found");
		}
		slf4jLogger.debug("{} :userList: Ended", this.getClass().getName());
		return "adminUserList";

	}

	@RequestMapping(value = "/admin/editauser/{id}")
	public String editAdminUser(Map<String, Object> map, @PathVariable Integer id) {

		slf4jLogger.debug("{} : editAdminUser : Started", this.getClass().getName());

		SysUserVO objUser = userService.getUserById(id);

		map.put("userForm", objUser);
		slf4jLogger.debug("{} :editAdminUser : Ended", this.getClass().getName());

		return "editUser";

	}

	/**
	 * updateAdminUser method to update user details
	 * 
	 * @param userForm
	 * @param result
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/admin/updateuser", method = RequestMethod.POST)
	public String updateAdminUser(@ModelAttribute("userForm") SysUserVO userForm, BindingResult result, Map<String, Object> map) {

		try {

			slf4jLogger.debug("{} :updateAdminUser: Started", this.getClass().getName());

			// System.out.println(userForm.getId().getLicenseStrType() + ":" +
			// userForm.getId().getCinemaStrId());
			adminUserValidationForm.validate(userForm, result);
			if (result.hasErrors()) {
				slf4jLogger.debug("{} :Update Admin User: Validation Failed", this.getClass().getName());
				map.put("userForm", userForm);
				return "editUser";
			}

			SysUserVO objUser = userService.getUserById(userForm.getId());

			if (objUser.getIfDeleted() == false) {
				userForm.setRole(objUser.getRole());
			} else {
				userForm.setRole(null);
			}
			userForm.setUpdateTime(new Date());
			userService.updateAdminUser(userForm);
			slf4jLogger.debug("{} :updateAdminUser: Ended", this.getClass().getName());
			if (objUser.getIfDeleted() == false) {
				map.put("addMsg", "User " + userForm.getUserName() + " " + EDIT_SUCCESS);
			} else {
				map.put("addMsg", "User " + userForm.getUserName() + " " + DELETED_SUCCESS);
			}
		} catch (DataIntegrityViolationException dex) {
			slf4jLogger.error("updateAdminUser : DataIntegrityViolationException", dex);
			map.put("userForm", userForm);
			map.put("addError", "Sorry !! User Details cannot be changed");

			return "editUser";
		} catch (Exception e) {
			execpEmailService.printAndSendExecption(this.getClass().getName(), e, "CMS");
			map.put("userForm", userForm);
			map.put("addError", "Sorry !! User Details cannot be changed");
			return "editUser";
		}

		return "forward:userlist";
	}

	/**
	 * userRolesList method to display list of user roles
	 * 
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/admin/userroles")
	public String userRolesList(Map<String, Object> map, Principal principal) {
		slf4jLogger.debug("{} :userRolesList: Started", this.getClass().getName());
		List<SysUserVO> lstUser = userService.getAdminUsersList();
		LinkedList<SysUserVO> lstUserNew = new LinkedList<SysUserVO>();
		SysMultiUserVO sysMultiUserVO = new SysMultiUserVO();

		// Getting Roles
		@SuppressWarnings("unchecked")
		List<SysRoleVO> lstRoles = (List<SysRoleVO>)(Object)crudService.getAllObjects(new SysRoleVO());

		List<String> lstAllRoles = new LinkedList<String>();

		Map<String, String> mapRoles = new HashMap<String, String>();
		Map<String, String> mapRolesNew = new HashMap<String, String>();
		for (SysRoleVO objRole : lstRoles) {
			lstAllRoles.add(objRole.getRole_detail());
			mapRoles.put(String.valueOf(objRole.getId()), objRole.getRole_detail());
			mapRolesNew.put(String.valueOf(objRole.getId()), "");
		}
		map.put("roleList", mapRoles);
		map.put("roleListNoLabel", mapRolesNew);
		int addUser = 1;

		for (SysUserVO objUser : lstUser) {
			addUser = 1;
			List lstUserRoles = new ArrayList();
			Set<SysRoleVO> colRoles = objUser.getRole();

			for (SysRoleVO objRoleVO : colRoles) {
				if (objRoleVO.getRole().equals("ROLE_ADMIN")) {
					addUser = 0;
				}
				lstUserRoles.add(String.valueOf(objRoleVO.getId()));
			}
			int isize = lstUserRoles.size();
			String[] arrRoles = new String[isize];
			for (int j = 0; j < lstUserRoles.size(); j++) {

				arrRoles[j] = (String) lstUserRoles.get(j);
			}

			objUser.setLstUserRoles(arrRoles);

			objUser.setLstAllRoles(lstAllRoles);
			if (addUser == 1 || objUser.getLogin().equals(principal.getName())) {
				lstUserNew.add(objUser);
			}

		}

		sysMultiUserVO.setLstUsers(lstUserNew);
		if (lstUserNew.size() == 0) {
			map.put("addError", "No Records Found");
		}

		slf4jLogger.debug("{} :User List Size , {} ", this.getClass().getName(), lstUser.size());
		map.put("userList", lstUserNew);
		map.put("userForm", sysMultiUserVO);
		slf4jLogger.debug("{} :userRolesList: Ended", this.getClass().getName());
		return "adminRoleList";

	}

	// changepermissionauser
	@RequestMapping(value = "/admin/changepermissionauser/{id}")
	public String changeUserRoles(Map<String, Object> map, Principal principal, @PathVariable Integer id) {
		slf4jLogger.debug("{} :changeUserRoles: Started", this.getClass().getName());

		SysUserVO objUser = userService.getUserById(id);

		// Getting Roles
		@SuppressWarnings("unchecked")
		List<SysRoleVO> lstRoles = (List<SysRoleVO>)(Object)crudService.getAllObjects(new SysRoleVO());

		List<String> lstAllRoles = new LinkedList<String>();

		Map<String, String> mapRoles = new HashMap<String, String>();

		for (SysRoleVO objRole : lstRoles) {
			lstAllRoles.add(objRole.getRole_detail());
			if (objRole.getRole().equals("ROLE_ADMIN")) {

			} else {
				mapRoles.put(String.valueOf(objRole.getId()), objRole.getRole_detail());
			}

		}
		map.put("roleList", mapRoles);

		List lstUserRoles = new ArrayList();
		Set<SysRoleVO> colRoles = objUser.getRole();
		int i = mapRoles.size();

		for (SysRoleVO objRoleVO : colRoles) {
			lstUserRoles.add(String.valueOf(objRoleVO.getId()));
		}
		int isize = lstUserRoles.size();
		String[] arrRoles = new String[isize];
		for (int j = 0; j < lstUserRoles.size(); j++) {

			arrRoles[j] = (String) lstUserRoles.get(j);
		}

		objUser.setLstUserRoles(arrRoles);

		objUser.setLstAllRoles(lstAllRoles);

		// objMultiUsers.setLstUsers(lstUserNew);

		// map.put("userList", lstUserNew);
		map.put("userForm", objUser);
		slf4jLogger.debug("{} :changeUserRoles: Ended", this.getClass().getName());
		return "adminRoleList";

	}

	/**
	 * saveUserRoles method to save user roles
	 * 
	 * @param userForm
	 * @param result
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/admin/saveuserroles")
	public String saveUserRoles(@ModelAttribute("userForm") SysUserVO userForm, BindingResult result, Map<String, Object> map) {

		try {

			slf4jLogger.debug("{} :saveUserRoles: Started", this.getClass().getName());

			// licenseValidationForm.validate(userForm, result);
			if (result.hasErrors()) {
				slf4jLogger.debug("{} :saveUserRoles: Validation Failed", this.getClass().getName());
				// map.put("userForm", userForm);
				return "forward:userlist";
			}

			SysUserVO objAdminUser = userService.getUser(userForm.getLogin());

			@SuppressWarnings("unchecked")
			List<SysRoleVO> lstRoles = (List<SysRoleVO>)(Object)crudService.getAllObjects(new SysRoleVO());
			Map<Integer, String> mapRoles = new HashMap<Integer, String>();
			// LinkedList<SysUserVO> lstUsers = new LinkedList<SysUserVO>();

			for (SysRoleVO objRoles : lstRoles) {
				mapRoles.put(objRoles.getId(), objRoles.getRole());

			}

			String[] arrRoles = new String[] {};
			arrRoles = userForm.getLstUserRoles();

			Set<SysRoleVO> setRoles = new HashSet<SysRoleVO>();
			for (int i = 0; i < arrRoles.length; i++) {

				SysRoleVO objRoleTemp = new SysRoleVO();
				objRoleTemp.setRole(mapRoles.get(Integer.parseInt(arrRoles[i])));
				objRoleTemp.setId(Integer.parseInt(arrRoles[i]));

				for (SysRoleVO objRoles : lstRoles) {
					if (objRoles.getId() == Integer.parseInt(arrRoles[i])) {
						objRoleTemp.setRole_detail(objRoles.getRole_detail());
					}
				}

				setRoles.add(objRoleTemp);
				slf4jLogger.debug(" {} : saveUserRoles:adding role for email id: {}", this.getClass().getName(),
						objAdminUser.getLogin() + ", Role: " + objRoleTemp.getRole_detail());
			}

			objAdminUser.setRole(setRoles);

			userService.updateAdminUser(objAdminUser);
			slf4jLogger.debug("{} :updateAdminUser: Ended", this.getClass().getName());
		} catch (DataIntegrityViolationException dex) {
			slf4jLogger.error("updateAdminUser : DataIntegrityViolationException", dex);

			map.put("addError", "Sorry !! User Details cannot be changed");
			return "forward:userlist";

		} catch (Exception e) {
			execpEmailService.printAndSendExecption(this.getClass().getName(), e, "CMS");

			map.put("addError", "Sorry !! User Details cannot be changed");

		}
		map.put("addMsg", "Role for User: " + userForm.getUserName() + " " + EDIT_SUCCESS);

		slf4jLogger.debug("{} :saveUserRoles: Ended", this.getClass().getName());
		return "forward:userlist";

	}

	@RequestMapping(value = "/admin/changepassword", method = RequestMethod.GET)
	public String changeAdminPassword(Map<String, Object> map, Principal principal) {

		try {

			slf4jLogger.debug("{} : changeAdminPassword: Started", this.getClass().getName());

			String name = "";

			name = principal.getName();

			SysUserVO objAdminUser = userService.getUser(name);

			objAdminUser.setLogin(name);

			map.put("userForm", objAdminUser);

		} catch (Exception e) {

			return "login";
		}

		return "changePassword";

	}

	@RequestMapping(value = "/admin/cpassword", method = RequestMethod.POST)
	public String changePassword(@ModelAttribute("userForm") SysUserVO userForm, BindingResult result, Map<String, Object> map, Principal principal) {

		try {

			slf4jLogger.debug("{} : changePassword: Started", this.getClass().getName());

			adminUserValidationForm.validatePassword(userForm, result, result);
			if (result.hasErrors()) {
				slf4jLogger.debug("{} :changePassword: Validation Failed", this.getClass().getName());

				String name = principal.getName();

				userForm.setLogin(name);
				return "changePassword";
			}

			userForm.setPassword(userForm.getNewPassword());
			SysUserVO objAdminUser = userService.getUser(principal.getName());
			Set<SysRoleVO> setRoles = objAdminUser.getRole();

			userForm.setRole(setRoles);

			userForm.setUpdateTime(new Date());

			// update user
			userService.updateAdminUser(userForm);

			map.put("addMsg", "Password changed successfully");
			String name = principal.getName();

			userForm.setLogin(name);

			map.put("userForm", new SysUserVO());

			slf4jLogger.debug("{} :changePassword: Ended", this.getClass().getName());

		} catch (DataIntegrityViolationException dex) {
			slf4jLogger.error("changePassword : DataIntegrityViolationException", dex);
			// map.put("userForm", userForm);
			map.put("addError", "User already exist");
			dex.printStackTrace();
			String name = principal.getName();

			userForm.setLogin(name);
			return "changePassword";
		} catch (Exception e) {
			execpEmailService.printAndSendExecption(this.getClass().getName(), e, "CMS");
			// map.put("userForm", userForm);
			map.put("addError", "Sorry !! User cannot be added");
			String name = principal.getName();

			userForm.setLogin(name);
			return "changePassword";
		}

		return "changePassword";
	}

}
