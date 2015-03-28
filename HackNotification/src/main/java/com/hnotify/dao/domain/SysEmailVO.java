package com.hnotify.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "send_email")
public class SysEmailVO {

	@Id @GeneratedValue private long pk_email_id;
	@Column(columnDefinition = "TEXT") private String emailbody;
	private String email_subject;
	private String email_from;
	private String email_to;
	private String email_cc;
	private String email_bcc;
	private String email_attachment_url;
	@Temporal(TemporalType.TIMESTAMP) private Date email_timestamp;
	private int retrycount;
	private String type;
	private String bookingid;

	public SysEmailVO() {
		super();

	}

	public SysEmailVO(String emailbody, String email_subject, String email_from, String email_to, String email_cc, Date email_timestamp, int retrycount) {
		this.emailbody = emailbody;
		this.email_subject = email_subject;
		this.email_from = email_from;
		this.email_to = email_to;
		this.email_cc = email_cc;
		this.email_timestamp = email_timestamp;
		this.retrycount = retrycount;
	}

	public long getPk_email_id() {
		return pk_email_id;
	}

	public void setPk_email_id(long pk_email_id) {
		this.pk_email_id = pk_email_id;
	}

	public String getEmailbody() {
		return emailbody;
	}

	public void setEmailbody(String emailbody) {
		this.emailbody = emailbody;
	}

	public String getEmail_subject() {
		return email_subject;
	}

	public void setEmail_subject(String email_subject) {
		this.email_subject = email_subject;
	}

	public String getEmail_from() {
		return email_from;
	}

	public void setEmail_from(String email_from) {
		this.email_from = email_from;
	}

	public String getEmail_to() {
		return email_to;
	}

	public void setEmail_to(String email_to) {
		this.email_to = email_to;
	}

	public String getEmail_cc() {
		return email_cc;
	}

	public void setEmail_cc(String email_cc) {
		this.email_cc = email_cc;
	}

	public Date getEmail_timestamp() {
		return email_timestamp;
	}

	public void setEmail_timestamp(Date email_timestamp) {
		this.email_timestamp = email_timestamp;
	}

	public int getRetrycount() {
		return retrycount;
	}

	public void setRetrycount(int retrycount) {
		this.retrycount = retrycount;
	}

	public String getEmail_bcc() {
		return email_bcc;
	}

	public void setEmail_bcc(String email_bcc) {
		this.email_bcc = email_bcc;
	}

	public String getEmail_attachment_url() {
		return email_attachment_url;
	}

	public void setEmail_attachment_url(String email_attachment_url) {
		this.email_attachment_url = email_attachment_url;
	}

	public String getBookingid() {
		return bookingid;
	}

	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
