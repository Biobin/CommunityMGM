package com.cmgm.entity;
/**
 *
 *@author Bio
 *@date   2018年3月25日
 *@time   上午12:39:44
 *
 */

import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CMGM_Payment")
public class Payment {

	private Integer id;
	private LocalTime createTime;	//账单产生日期
	private Double receivableFee;	//应收金额
	private Double owedFee;		//欠款金额
	private Double collectFee;	//实收金额
	private State state;		//缴费状态
	private Owner owner;		//需缴费业主
	private String chargingItem;	//收费项目
	private String details;		//收费详情
	
	public Payment() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_payment")
	@SequenceGenerator(name = "seq_payment", sequenceName = "seq_payment", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalTime createTime) {
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

	@OneToOne
	@JoinColumn(name="stateId", foreignKey=@ForeignKey(name="state_payment_Id"))
	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

	@JoinColumn(name="OwnerId",foreignKey=@ForeignKey(name="Owner_payment_Id"))
	@ManyToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
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
