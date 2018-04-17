package com.cmgm.entity;

/**
 * 
 * @author Bio 
 * 物业管理员实体类
 */

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="CMGM_PropertyManager")
public class PropertyManager {
	
	private Integer id;
	private String name;
	private String phone;
	private String email;
	private User user;
	
	public PropertyManager() {
	}

	public PropertyManager(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public PropertyManager(Integer id, String name, String phone, String email) {
		super();
		this.id = id;
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "seq_propertyManage")
	@SequenceGenerator(name = "seq_propertyManage", sequenceName = "seq_propertyManage", allocationSize = 1, initialValue = 1)
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@JoinColumn(name="userId", foreignKey=@ForeignKey(name="User_PropertyManager_Id"))
    @OneToOne
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
}
