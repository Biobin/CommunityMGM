package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   上午11:42:24
 * 投诉信息VO
 */

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ComplaintVO {

	private Integer id;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	private Integer ownerId;
	private String ownerName;
	private String ownerPhone;
	private String ownerEmail;
	private Integer propertyManagerId;
	private String propertyManagerName;
	private String propertyManagerPhone;
	private String propertyManagerEmail;
	private Integer stateId;
	private String stateName;
	private String returnContent;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date finishTime;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public Integer getOwnerId() {
		return ownerId;
	}
	
	public void setOwnerId(Integer ownerId) {
		this.ownerId = ownerId;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
	
	public String getOwnerPhone() {
		return ownerPhone;
	}
	
	public void setOwnerPhone(String ownerPhone) {
		this.ownerPhone = ownerPhone;
	}
	
	public String getOwnerEmail() {
		return ownerEmail;
	}
	
	public void setOwnerEmail(String ownerEmail) {
		this.ownerEmail = ownerEmail;
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
	
	public Integer getStateId() {
		return stateId;
	}
	
	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
	public String getStateName() {
		return stateName;
	}
	
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getReturnContent() {
		return returnContent;
	}

	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		this.finishTime = finishTime;
	}
	
	public ComplaintVO() {
	}
	
}


