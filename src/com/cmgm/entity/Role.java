package com.cmgm.entity;


import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 
 * @author Bio
 * 角色实体类：区分不同角色
 * 暂定角色有：物业管理人员，业主，系统维护管理人员（大概是不需要的）
 *
 */
@Entity
@Table(name="CMGM_Role")
public class Role {
	
	private Integer id;
	private String name;
	@Temporal(TemporalType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date createTime;
	private Set<User> users;

	public Role() {
	}

	public Role(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public Role(Integer id, String name, Date createTime) {
		super();
		this.id = id;
		this.name = name;
		this.createTime = createTime;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_role")
	@SequenceGenerator(name = "seq_role", sequenceName = "seq_role", allocationSize = 1, initialValue = 1)
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@JoinTable(name="role_user",joinColumns={@JoinColumn(name="role_id",referencedColumnName="id")},
			inverseJoinColumns={@JoinColumn(name="user_id",referencedColumnName="id")})
	@OneToMany(fetch=FetchType.LAZY,cascade=CascadeType.PERSIST)
	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}
	
}
