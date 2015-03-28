package com.hnotify.cms.controller;

import java.security.Principal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.hnotify.dao.domain.SysUserVO;
import com.hnotify.service.ExecpEmailService;
import com.hnotify.service.UserService;

@Controller
@SessionAttributes({ "username" })
public class LoginController {
	@Autowired
	UserService userService;
	
	@Autowired ExecpEmailService execpEmailService;
	
	@Value("${MAX_EMAIL_TRY_ALLOWED}") private String MAX_EMAIL_TRY_ALLOWED;

	Logger slf4jLogger = LoggerFactory.getLogger("cms");

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String directUser(ModelMap model, Principal principal, HttpServletRequest request) {
			
	String name = "";
	
		try {
			if (principal.getName() == null){
				return "login";
			}
			name = principal.getName();
			
			SysUserVO objUser = userService.getUser(name);
			
			
			if(objUser.getIfDeleted() == true){
				model.addAttribute("message", "Invalid email ID");
				return "login";
			}

			
			
			model.addAttribute("username", name);
			model.addAttribute("username1", objUser.getUserName());
		} catch (Exception e) {
			execpEmailService.printAndSendExecption(this.getClass().getName(),e,"CMS");
			model.addAttribute("message", "Please login again");
			return "login";
		}
			
		
		slf4jLogger.debug(this.getClass().getName(),"{} , logged in", name);
		
		return "welcome";

	}

	@RequestMapping(value = "/loginfailed", method = RequestMethod.GET)
	public String errorLogin(ModelMap model) {

		model.addAttribute("error", "true");
		//model.addAttribute("message", "Please login again");
		return "login";

	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(ModelMap model) {

		model.addAttribute("message", "You have been successfully logged out"); 
		return "login";

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginForm() {

			return "login";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String error(ModelMap model, HttpServletRequest request,
			HttpSession session) {
	
		//model.addAttribute("error", "true");
		model.addAttribute("message", "Please login again");

		session.invalidate();
		return "login";

	}
	
	
	@RequestMapping(value = "/forgotpassword")
	public @ResponseBody String forgotPassword(ModelMap model,Map<String, Object> map, @RequestParam("username") String username) {
			//, method = RequestMethod.GET, produces = "application/json"
				
	
	
		try {
			
			
			SysUserVO objUser = userService.getUser(username);
			
			if (objUser == null){
				return "You are not authorized to login";
			}
			
			if (objUser.getIfDeleted()){
				model.addAttribute("message", "Invalid email id");
				return "login";
			}
			
		} catch (Exception e) {
			//model.addAttribute("message", "Please provide valid email id");
			execpEmailService.printAndSendExecption(this.getClass().getName(),e,"CMS");
			return "You are not authorized to login";
		}
			
		
		slf4jLogger.debug(this.getClass().getName(),"{} , password email sent for email id", username);
		//model.addAttribute("message", "Please check your email for Login Credentials");
		
		return "Please check your email for Login Credentials";

	}

	
/*	@RequestMapping(value = "/forgotpassword", method = RequestMethod.GET, produces = "application/json")
	public String forgotPassword(ModelMap model,Map<String, Object> map, @RequestParam String emailid) {
			
				
	
	
		try {
			
			System.out.println("got email id.."+emailid);
			SysUserVO objUser = userService.getUser(emailid);
			
			System.out.println("got det.."+objUser.getPassword() + objUser.getIfActive());
			
			if (objUser.getIfDeleted()){
				model.addAttribute("message", "Invalid email id");
				return "login";
			}
			
			// Mark for Email and SMS
			SysEmailSmsTemplateVO sysEmailSmsTemplateVO = emailSmsTemplateService
					.getEmailSmsByName("CMS_FORGET_PASSWORD");
			SysEmailVO sysEmailVO = emailCreateService
					.getCMSForgetPasswordEmail(sysEmailSmsTemplateVO,
							objUser, MAX_EMAIL_TRY_ALLOWED);
			emailCreateService.saveOrUpdateEmail(sysEmailVO);
			
		} catch (Exception e) {
			model.addAttribute("message", "Please provide valid email id");
			return "login";
		}
			
		
		slf4jLogger.debug(this.getClass().getName(),"{} , password email sent for email id", emailid);
		model.addAttribute("message", "Please check your email for Login Credentials");
		
		return "login";

	}
*/

}
