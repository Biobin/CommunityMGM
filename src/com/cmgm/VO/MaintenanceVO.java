package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午9:50:16
 * 报修信息VO类
 */

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class MaintenanceVO {

	private Integer id;
	private String code;	//报修单号
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;	//报修时间
	private String details;		//报修信息细节
	
	private String repairPersonnel;		//维修人员
	private String repairPerPhone;		//维修人员电话
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date repairTime;	//维修时间
	private String repairRemarks;		//维修后备注
	
	//保修业主信息
	private Integer ownerId;
	private String ownerName;
	private String ownerPhone;
	private String ownerEmail;
	//下拉列表,选取公共设施类型，然后再下拉中选取具体公共设施或私人设施
	private Integer communalFacilitiesIds;
	private String communalFacilitiesNames;
	private Integer communalFaStyleId;
	private String communalFaStyleName;
	//设施对应负责人信息
	private Integer propertyManagerId;
	private String propertyManagerName;
	private String propertyManagerPhone;
	private String propertyManagerEmail;
	//设施信息
	private String communalFacilitiesCode;	//设施编号
	private String communalFacilitiesDetails;	//设施备注信息
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date beginUsingTime;	//开始使用时间
	//状态
	private Integer stateId;
	private String stateName;
	
	public MaintenanceVO() {
	}

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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public String getRepairPersonnel() {
		return repairPersonnel;
	}

	public void setRepairPersonnel(String repairPersonnel) {
		this.repairPersonnel = repairPersonnel;
	}

	public String getRepairPerPhone() {
		return repairPerPhone;
	}

	public void setRepairPerPhone(String repairPerPhone) {
		this.repairPerPhone = repairPerPhone;
	}

	public Date getRepairTime() {
		return repairTime;
	}

	public void setRepairTime(Date repairTime) {
		this.repairTime = repairTime;
	}

	public String getRepairRemarks() {
		return repairRemarks;
	}

	public void setRepairRemarks(String repairRemarks) {
		this.repairRemarks = repairRemarks;
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

	public Integer getCommunalFacilitiesIds() {
		return communalFacilitiesIds;
	}

	public void setCommunalFacilitiesIds(Integer communalFacilitiesIds) {
		this.communalFacilitiesIds = communalFacilitiesIds;
	}

	public String getCommunalFacilitiesNames() {
		return communalFacilitiesNames;
	}

	public void setCommunalFacilitiesNames(String communalFacilitiesNames) {
		this.communalFacilitiesNames = communalFacilitiesNames;
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

	public String getCommunalFacilitiesCode() {
		return communalFacilitiesCode;
	}

	public void setCommunalFacilitiesCode(String communalFacilitiesCode) {
		this.communalFacilitiesCode = communalFacilitiesCode;
	}

	public String getCommunalFacilitiesDetails() {
		return communalFacilitiesDetails;
	}

	public void setCommunalFacilitiesDetails(String communalFacilitiesDetails) {
		this.communalFacilitiesDetails = communalFacilitiesDetails;
	}

	public Date getBeginUsingTime() {
		return beginUsingTime;
	}

	public void setBeginUsingTime(Date beginUsingTime) {
		this.beginUsingTime = beginUsingTime;
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
	
}
