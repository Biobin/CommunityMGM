package com.cmgm.entity;
/**
 *
 * @author Bio
 * @date   2018年3月25日
 * @time   上午12:36:20
 * 投诉信息实体类
 *
 */

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CMGM_Complaint")
public class Complaint {

	private Integer id;
	private String content;
	private LocalTime createTime;
	private PropertyManager propertyManager;	//投诉受理人
	private Owner owner;	//投诉人
	
	public Complaint() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_complaint")
	@SequenceGenerator(name = "seq_complaint", sequenceName = "seq_complaint", allocationSize = 1, initialValue = 1)
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

	public LocalTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalTime createTime) {
		this.createTime = createTime;
	}

	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	public void setPropertyManager(PropertyManager propertyManager) {
		this.propertyManager = propertyManager;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}
	
}
