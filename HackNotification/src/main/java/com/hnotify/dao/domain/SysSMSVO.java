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
@Table(name="sys_sms")
public class SysSMSVO {
	@Id
	@GeneratedValue
	private long pk_sms_id;
	@Column(columnDefinition="TEXT")
	private String smsbody;
	private String mobilenum;
	@Temporal(TemporalType.TIMESTAMP)
	private Date timestamp;
	private int retrycount;

	private String type;
	private String bookingid;
	
	
	public SysSMSVO() {
		super();
	}
	public SysSMSVO(long pk_sms_id, String smsbody, String mobilenum,
			Date timestamp, int retrycount) {
		super();
		this.pk_sms_id = pk_sms_id;
		this.smsbody = smsbody;
		this.mobilenum = mobilenum;
		this.timestamp = timestamp;
		this.retrycount = retrycount;
	}
	public long getPk_sms_id() {
		return pk_sms_id;
	}
	public void setPk_sms_id(long pk_sms_id) {
		this.pk_sms_id = pk_sms_id;
	}
	public String getSmsbody() {
		return smsbody;
	}
	public void setSmsbody(String smsbody) {
		this.smsbody = smsbody;
	}
	public String getMobilenum() {
		return mobilenum;
	}
	public void setMobilenum(String mobilenum) {
		this.mobilenum = mobilenum;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public int getRetrycount() {
		return retrycount;
	}
	public void setRetrycount(int retrycount) {
		this.retrycount = retrycount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getBookingid() {
		return bookingid;
	}
	public void setBookingid(String bookingid) {
		this.bookingid = bookingid;
	}
	
	
}
