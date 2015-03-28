package com.hnotify.dao.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="user_data")
public class UserDataVO {
	@Id
	private String deviceid;
	@Column(length=20)
	private String lat;
	@Column(length=20)
	private String lng;
	@Column(length=150)
	private String notifyid;
	@Column(length=10)
	private String platform;
	@Column(length=30)
	private String country;
	@Column(length=30)
	private String state;
	@Column(length=30)
	private String city;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedate;
	private boolean active=true;
	
	
	public UserDataVO(String deviceid, String lat, String lng, String notifyid, String platform, String country, String state, String city, Date createdate,
			Date updatedate, boolean active) {
		super();
		this.deviceid = deviceid;
		this.lat = lat;
		this.lng = lng;
		this.notifyid = notifyid;
		this.platform = platform;
		this.country = country;
		this.state = state;
		this.city = city;
		this.createdate = createdate;
		this.updatedate = updatedate;
		this.active = active;
	}



	public UserDataVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	

	
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDeviceid() {
		return deviceid;
	}
	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
	public String getNotifyid() {
		return notifyid;
	}
	public void setNotifyid(String notifyid) {
		this.notifyid = notifyid;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public Date getCreatedate() {
		return createdate;
	}
	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	public Date getUpdatedate() {
		return updatedate;
	}
	public void setUpdatedate(Date updatedate) {
		this.updatedate = updatedate;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	
}
