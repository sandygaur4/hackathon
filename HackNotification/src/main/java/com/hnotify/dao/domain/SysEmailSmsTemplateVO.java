package com.hnotify.dao.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "email_sms_template")
public class SysEmailSmsTemplateVO {

	@Id
	@Column(name = "id", length = 50)
	private String confName;

	@Column(name = "email_from", length = 150)
	private String emailFrom;

	@Column(name = "email_subject", length = 250)
	private String emailSubject;

	@Column(name = "email_body",columnDefinition = "TEXT")
	private String emailBody;

	@Column(name = "sms_template", length = 2050)
	private String smsTemplate;

	public String getConfName() {
		return confName;
	}

	public void setConfName(String confName) {
		this.confName = confName;
	}

	public String getEmailFrom() {
		return emailFrom;
	}

	public void setEmailFrom(String emailFrom) {
		this.emailFrom = emailFrom;
	}

	public String getEmailSubject() {
		return emailSubject;
	}

	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}

	public String getEmailBody() {
		return emailBody;
	}

	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	public String getSmsTemplate() {
		return smsTemplate;
	}

	public void setSmsTemplate(String smsTemplate) {
		this.smsTemplate = smsTemplate;
	}

}
