package com.cmgm.entity;

import java.time.LocalDateTime;

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
 * @date   2018年3月25日
 * @time   上午12:48:26
 * 车辆信息实体类
 */

@Entity
@Table(name="CMGM_Car")
public class Car {

	private Integer id;
	private String plateNumber;		//车牌号
	private CarStyle carStyle;	//车辆类型
	private Owner owner;	//车主
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createTime;	//开始停放时间
	
	public Car() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_car")
	@SequenceGenerator(name = "seq_car", sequenceName = "seq_car", allocationSize = 1, initialValue = 1)
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

	@JoinColumn(name="CarStyleId",foreignKey=@ForeignKey(name="CarStyleId"))
	@ManyToOne
	public CarStyle getCarStyle() {
		return carStyle;
	}

	public void setCarStyle(CarStyle carStyle) {
		this.carStyle = carStyle;
	}

	@JoinColumn(name="OwnerId",foreignKey=@ForeignKey(name="OwnerId"))
	@ManyToOne
	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}
	
}
