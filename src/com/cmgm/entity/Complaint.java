package com.cmgm.entity;
/**
 *
 * @author Bio
 * @date   2018年3月25日
 * @time   上午12:36:20
 * 投诉信息实体类
 *
 */

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="CMGM_Complaint")
public class Complaint {

	private Integer id;
	private String content;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;	//提交日期
	private PropertyManager propertyManager;	//投诉受理人
	private Owner owner;	//投诉人
	private State state;
	private String returnContent;	//对投诉内容进行回应
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime finishTime;	//投诉解决日期
	
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

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@JoinColumn(name="propertyManagerId",foreignKey=@ForeignKey(name="propertyManager_complaint_Id"))
	@ManyToOne
	public PropertyManager getPropertyManager() {
		return propertyManager;
	}

	public void setPropertyManager(PropertyManager propertyManager) {
		this.propertyManager = propertyManager;
	}

	@JoinColumn(name="OwnerId",foreignKey=@ForeignKey(name="Owner_complaint_Id"))
	@ManyToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	@ManyToOne(cascade={CascadeType.MERGE,CascadeType.REFRESH},  
            fetch=FetchType.EAGER,optional=false)
	@JoinColumn(name="stateId", foreignKey=@ForeignKey(name="state_complaint_Id"))
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	public String getReturnContent() {
		return returnContent;
	}

	public void setReturnContent(String returnContent) {
		this.returnContent = returnContent;
	}

	public LocalDateTime getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(LocalDateTime finishTime) {
		this.finishTime = finishTime;
	}
	
}
