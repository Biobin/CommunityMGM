package com.cmgm.VO;
/**
 *
 * @author Bio
 * @date   2018年4月8日
 * @time   上午1:06:55
 * 车辆信息VO类
 */

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class CarVO {

	private Integer id;
	private String plateNumber;
	private Integer carStyleId;
	private String carStyleName;
	private Integer ownerId;
	private String ownerName;
	private String ownerPhone;
	private String ownerEmail;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getPlateNumber() {
		return plateNumber;
	}
	
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	
	public Integer getCarStyleId() {
		return carStyleId;
	}
	
	public void setCarStyleId(Integer carStyleId) {
		this.carStyleId = carStyleId;
	}
	
	public String getCarStyleName() {
		return carStyleName;
	}
	
	public void setCarStyleName(String carStyleName) {
		this.carStyleName = carStyleName;
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
	
	public Date getCreateTime() {
		return createTime;
	}
	
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	public CarVO() {
	}
	
}
