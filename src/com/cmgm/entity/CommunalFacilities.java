package com.cmgm.entity;

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Bio
 * 小区公共设施实体类
 *
 */

@Entity
@Table(name="CMGM_CommunalFacilities")
public class CommunalFacilities {

	private Integer id;
	private String code;	//编号
	private String name;
	private CommunalFaStyle communalFaStyle;	//设施类型
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalTime BeginUsingTime;	//开始使用时间
	private String details;		//备注
	private PropertyManager propertyManager;	//维修负责人
	
	public CommunalFacilities() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_communalFacilities")
	@SequenceGenerator(name = "seq_communalFacilities", sequenceName = "seq_communalFacilities", allocationSize = 1, initialValue = 1)
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

	@JoinColumn(name="communalFaStyleId", foreignKey=@ForeignKey(name="communalFaStyleId"))
	@ManyToOne
	public CommunalFaStyle getCommunalFaStyle() {
		return communalFaStyle;
	}

	public void setCommunalFaStyle(CommunalFaStyle communalFaStyle) {
		this.communalFaStyle = communalFaStyle;
	}

	public LocalTime getBeginUsingTime() {
		return BeginUsingTime;
	}

	public void setBeginUsingTime(LocalTime beginUsingTime) {
		BeginUsingTime = beginUsingTime;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	@JoinColumn(name="propertyManagerId", foreignKey=@ForeignKey(name="propertyManagerId"))
	@ManyToOne
	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	public void setPropertyManager(PropertyManager propertyManager) {
		this.propertyManager = propertyManager;
	}

}
