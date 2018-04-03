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
 * @date   2018年3月27日
 * @time   上午12:19:56
 * 状态实体类（1.待受理，2.正在处理中，3.已完成）
 *
 */

@Entity
@Table(name="CMGM_State")
public class State {

	private Integer id;
	private String name;
	
	public State() {
	}

	public State(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_state")
	@SequenceGenerator(name = "seq_state", sequenceName = "seq_state", allocationSize = 1, initialValue = 1)
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
