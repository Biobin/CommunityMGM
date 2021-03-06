package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date 2018年4月8日
 * @time 上午10:27:33
 *
 */

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CommunalFacilitiesVO {

	private Integer id;
	private String code;
	private String name;
	private Integer communalFaStyleId;
	private String communalFaStyleName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginUsingTime;
	private String details;
	private Integer propertyManagerId;
	private String propertyManagerName;
	private String propertyManagerPhone;
	private String propertyManagerEmail;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Integer getCommunalFaStyleId() {
		return communalFaStyleId;
	}

	public void setCommunalFaStyleId(Integer communalFaStyleId) {
		this.communalFaStyleId = communalFaStyleId;
	}

	public String getCommunalFaStyleName() {
		return communalFaStyleName;
	}

	public void setCommunalFaStyleName(String communalFaStyleName) {
		this.communalFaStyleName = communalFaStyleName;
	}

	public Date getBeginUsingTime() {
		return beginUsingTime;
	}
	
	public void setBeginUsingTime(Date beginUsingTime) {
		this.beginUsingTime = beginUsingTime;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
	public Integer getPropertyManagerId() {
		return propertyManagerId;
	}
	
	public void setPropertyManagerId(Integer propertyManagerId) {
		this.propertyManagerId = propertyManagerId;
	}
	
	public String getPropertyManagerName() {
		return propertyManagerName;
	}
	
	public void setPropertyManagerName(String propertyManagerName) {
		this.propertyManagerName = propertyManagerName;
	}
	
	public String getPropertyManagerPhone() {
		return propertyManagerPhone;
	}
	
	public void setPropertyManagerPhone(String propertyManagerPhone) {
		this.propertyManagerPhone = propertyManagerPhone;
	}
	
	public String getPropertyManagerEmail() {
		return propertyManagerEmail;
	}
	
	public void setPropertyManagerEmail(String propertyManagerEmail) {
		this.propertyManagerEmail = propertyManagerEmail;
	}
	
	public CommunalFacilitiesVO() {
	}

}
