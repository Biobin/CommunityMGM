package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date   2018年4月7日
 * @time   下午3:24:04
 * 账单VO类
 */

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class PaymentVO {

	private Integer id;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;
	private Double receivableFee;	//应收金额
	private Double owedFee;		//欠款金额
	private Double collectFee;	//实收金额
	private Integer stateId;	//缴费状态
	private String stateName;	
	private Integer ownerId;	//需缴费业主
	private String ownerName;
	private String ownerPhone;
	private String ownerEmail;
	private String chargingItem;	//收费项目
	private String details;		//收费详情
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public LocalDateTime getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
	public Double getReceivableFee() {
		return receivableFee;
	}
	
	public void setReceivableFee(Double receivableFee) {
		this.receivableFee = receivableFee;
	}
	
	public Double getOwedFee() {
		return owedFee;
	}
	
	public void setOwedFee(Double owedFee) {
		this.owedFee = owedFee;
	}
	
	public Double getCollectFee() {
		return collectFee;
	}
	
	public void setCollectFee(Double collectFee) {
		this.collectFee = collectFee;
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
	
	public String getChargingItem() {
		return chargingItem;
	}
	
	public void setChargingItem(String chargingItem) {
		this.chargingItem = chargingItem;
	}
	
	public String getDetails() {
		return details;
	}
	
	public void setDetails(String details) {
		this.details = details;
	}
	
}
