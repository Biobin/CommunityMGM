package com.cmgm.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Bio
 * @date   2018年3月26日
 * @time   下午11:19:48
 * 车辆类型实体类
 *
 */

@Entity
@Table(name="CMGM_CarStyle")
public class CarStyle {

	private Integer id;
	private String name;
	
	public CarStyle() {
	}

	public CarStyle(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_carStyle")
	@SequenceGenerator(name = "seq_carStyle", sequenceName = "seq_carStyle", allocationSize = 1, initialValue = 1)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
